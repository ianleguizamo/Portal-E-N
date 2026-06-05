package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class WordWeb {

    private static final Logger LOGGER = Logger.getLogger(WordWeb.class.getName());
    private static final String RUTA_BASE = System.getProperty("user.dir") + File.separator + "reportes";
    private static final String TEMPLATE_PATH = System.getProperty("user.dir") + File.separator + "ruta" + File.separator + "EXXO.docx";
    private static final String CAPTURAS_DIR = "Capturas/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void generarReporte(String nombreEscenario, String[] pasosEjecutados, String nombreFeature, String duracion, String pasoFallido, String estadoFinal) {
        File[] capturasFiles = new File(CAPTURAS_DIR).listFiles();

        File template = new File(TEMPLATE_PATH);
        if (!template.exists()) {
            LOGGER.warning("El archivo EXXO.docx no existe en la ruta: " + TEMPLATE_PATH);
            return;
        }

        File carpetaReportes = new File(RUTA_BASE);
        carpetaReportes.mkdirs();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        String nombreArchivo = nombreEscenario.replaceAll("[\\\\/:*?\"<>|]", "_").trim()
                + "_" + timestamp + ".docx";
        String outputPath = carpetaReportes.getAbsolutePath() + File.separator + nombreArchivo;

        try (FileInputStream fis = new FileInputStream(template);
             XWPFDocument document = new XWPFDocument(fis);
             FileOutputStream fos = new FileOutputStream(outputPath)) {

            reemplazarTexto(document, "{{ESCENARIO}}", nombreEscenario);
            reemplazarTexto(document, "{{FECHA}}", FORMATTER.format(LocalDateTime.now()));
            reemplazarTexto(document, "{{FEATURE}}", nombreFeature != null ? nombreFeature : "No definido");
            reemplazarTexto(document, "{{DURACION}}", duracion);
            reemplazarTextoConclusion(document, pasosEjecutados, pasoFallido);

            if (capturasFiles != null && capturasFiles.length > 0) {
                agregarPasosYCapturas(document, pasosEjecutados, capturasFiles);
            } else {
                LOGGER.info("No se encontraron capturas para agregar al documento.");
            }

            document.write(fos);
            LOGGER.info("Reporte generado correctamente en: " + outputPath);

        } catch (IOException | InvalidFormatException e) {
            LOGGER.severe("Error al generar el reporte: " + e.getMessage());
        }
    }

    private static void reemplazarTextoConclusion(XWPFDocument document, String[] pasos, String pasoFallido) {
        boolean fallo = pasoFallido != null && !pasoFallido.trim().isEmpty();

        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        if (paragraph.getText() != null && paragraph.getText().contains("{{CONCLUSION}}")) {

                            // Limpiar el marcador
                            for (XWPFRun run : paragraph.getRuns()) {
                                String t = run.getText(0);
                                if (t != null && t.contains("{{CONCLUSION}}")) {
                                    run.setText("", 0);
                                }
                            }

                            // Línea de estado general
                            XWPFRun estadoRun = paragraph.createRun();
                            estadoRun.setFontFamily("Calibri");
                            estadoRun.setFontSize(11);
                            estadoRun.setBold(true);
                            estadoRun.setText(fallo ? "Estado de la prueba: FALLIDO" : "Estado de la prueba: EXITOSO");
                            estadoRun.addBreak();
                            estadoRun.addBreak();

                            // Lista de pasos
                            boolean fallado = false;
                            int numero = 1;
                            for (String paso : pasos) {
                                XWPFRun pasoRun = paragraph.createRun();
                                pasoRun.setFontFamily("Calibri");
                                pasoRun.setFontSize(11);
                                pasoRun.setBold(false);

                                if (!fallo) {
                                    pasoRun.setText(numero + ". " + paso + ": Ejecutado correctamente.");
                                } else {
                                    if (fallado) {
                                        pasoRun.setText(numero + ". " + paso + ": No ejecutado.");
                                    } else if (paso.equalsIgnoreCase(pasoFallido)) {
                                        pasoRun.setBold(true);
                                        pasoRun.setText(numero + ". " + paso + ": FALLO - Paso donde se detuvo la ejecucion.");
                                        fallado = true;
                                    } else {
                                        pasoRun.setText(numero + ". " + paso + ": Ejecutado correctamente.");
                                    }
                                }
                                pasoRun.addBreak();
                                numero++;
                            }

                            // Conclusión final
                            XWPFRun conclusionRun = paragraph.createRun();
                            conclusionRun.setFontFamily("Calibri");
                            conclusionRun.setFontSize(11);
                            conclusionRun.setBold(true);
                            conclusionRun.addBreak();
                            conclusionRun.setText(fallo
                                    ? "Resultado: La prueba finalizo con errores. Se recomienda revisar el paso fallido y ejecutar nuevamente."
                                    : "Resultado: La prueba finalizo satisfactoriamente. Todos los pasos fueron completados sin errores.");
                        }
                    }
                }
            }
        }
    }

    private static void agregarPasosYCapturas(XWPFDocument doc, String[] pasos, File[] capturas) throws IOException, InvalidFormatException {
        for (String paso : pasos) {
            File imagen = buscarImagen(paso, capturas);

            if (imagen != null) {
                XWPFParagraph textoParrafo = doc.createParagraph();
                textoParrafo.setAlignment(ParagraphAlignment.BOTH);
                XWPFRun textoRun = textoParrafo.createRun();
                textoRun.setText(paso);
                textoRun.setFontSize(12);
                textoRun.setFontFamily("Calibri");
                textoRun.setBold(true);

                doc.createParagraph();

                XWPFParagraph parrafoImg = doc.createParagraph();
                parrafoImg.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun imagenRun = parrafoImg.createRun();
                try (FileInputStream is = new FileInputStream(imagen)) {
                    imagenRun.addPicture(is, Document.PICTURE_TYPE_PNG, imagen.getName(), Units.toEMU(500), Units.toEMU(300));
                }

                doc.createParagraph();

            } else {
                XWPFParagraph noImg = doc.createParagraph();
                noImg.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run = noImg.createRun();
                run.setText("Paso sin evidencia: " + paso);
                run.setItalic(true);
                run.setFontSize(10);
                run.setFontFamily("Calibri");
            }
        }
    }

    private static File buscarImagen(String paso, File[] capturas) {
        String normalizado = paso.replaceAll("[^a-zA-Z0-9]", "_");
        for (File img : capturas) {
            if (img.getName().startsWith(normalizado)) {
                return img;
            }
        }
        return null;
    }

    private static void reemplazarTexto(XWPFDocument document, String marcador, String nuevoTexto) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null && text.contains(marcador)) {
                    run.setText(text.replace(marcador, nuevoTexto), 0);
                }
            }
        }

        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        for (XWPFRun run : paragraph.getRuns()) {
                            String text = run.getText(0);
                            if (text != null && text.contains(marcador)) {
                                run.setText(text.replace(marcador, nuevoTexto), 0);
                            }
                        }
                    }
                }
            }
        }
    }
}