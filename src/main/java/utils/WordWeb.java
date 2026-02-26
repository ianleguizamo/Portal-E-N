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
    private static final String OUTPUT_PATH = System.getProperty("user.dir") + File.separator + "ruta" + File.separator + "EXXO.docx";
    private static final String CAPTURAS_DIR = "Capturas/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void generarReporte(String nombreEscenario, String[] pasosEjecutados, String linea, String duracion, String pasoFallido, String estadoFinal) {
        File[] capturasFiles = new File(CAPTURAS_DIR).listFiles();

        File template = new File(OUTPUT_PATH);
        if (!template.exists()) {
            LOGGER.warning("❌ El archivo EXXO.docx no existe en la ruta: " + OUTPUT_PATH);
            return;
        }

        try (FileInputStream fis = new FileInputStream(template);
             XWPFDocument document = new XWPFDocument(fis);
             FileOutputStream fos = new FileOutputStream(OUTPUT_PATH)) {

            reemplazarTexto(document, "{{ESCENARIO}}", nombreEscenario);
            reemplazarTexto(document, "{{FECHA}}", FORMATTER.format(LocalDateTime.now()));
            reemplazarTexto(document, "{{LINEA}}", linea != null ? linea : "No definida");
            reemplazarTexto(document, "{{DURACION}}", duracion);
            reemplazarTexto(document, "{{CONCLUSION}}", generarConclusion(nombreEscenario, pasosEjecutados, linea, pasoFallido, estadoFinal));

            if (capturasFiles != null && capturasFiles.length > 0) {
                agregarPasosYCapturas(document, pasosEjecutados, capturasFiles);
            } else {
                LOGGER.info("📌 No se encontraron capturas para agregar al documento.");
            }

            document.write(fos);
            LOGGER.info("✅ Reporte generado correctamente en EXXO.docx");

        } catch (IOException | InvalidFormatException e) {
            LOGGER.severe("❌ Error al generar el reporte: " + e.getMessage());
        }
    }

    private static void agregarPasosYCapturas(XWPFDocument doc, String[] pasos, File[] capturas) throws IOException, InvalidFormatException {
        for (String paso : pasos) {
            File imagen = buscarImagen(paso, capturas);

            if (imagen != null) {
                // Texto centrado y en negrita
                XWPFParagraph textoParrafo = doc.createParagraph();
                textoParrafo.setAlignment(ParagraphAlignment.BOTH);
                XWPFRun textoRun = textoParrafo.createRun();
                textoRun.setText(paso);
                textoRun.setFontSize(12);
                textoRun.setFontFamily("Calibri");
               // textoRun.setBold(true);

                // Espacio entre texto e imagen
                doc.createParagraph();

                // Imagen centrada
                XWPFParagraph parrafoImg = doc.createParagraph();
                parrafoImg.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun imagenRun = parrafoImg.createRun();
                try (FileInputStream is = new FileInputStream(imagen)) {
                    imagenRun.addPicture(is, Document.PICTURE_TYPE_PNG, imagen.getName(), Units.toEMU(500), Units.toEMU(300));
                }

                // Espacio después de imagen
                doc.createParagraph();

            } else {
                XWPFParagraph noImg = doc.createParagraph();
                noImg.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run = noImg.createRun();
                run.setText("Paso sin imagen: " + paso);
                run.setItalic(true);
                run.setFontSize(10);
            }
        }
    }

    private static File buscarImagen(String paso, File[] capturas) {
        String normalizado = paso.toLowerCase().replaceAll("[^a-z0-9]", "_");
        for (File img : capturas) {
            if (img.getName().toLowerCase().contains(normalizado)) {
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

    private static String generarConclusion(String escenario, String[] pasos, String linea, String pasoFallido, String estadoFinal) {
        boolean fallo = pasoFallido != null && !pasoFallido.trim().isEmpty();
        StringBuilder conclusion = new StringBuilder();

        // Conclusión narrativa si la prueba fue exitosa
        if (!fallo && "Consulta inicial del estado de la linea en CmaxPage".equalsIgnoreCase(escenario.trim())) {
            conclusion.append(" ✅ Se realiza la validación del estado de la línea en el sistema CmaxPage. ")
                    .append("Durante la ejecución, se verificaron exitosamente el resumen de cuenta, ")
                    .append("las ofertas activas, las cuentas dedicadas y los acumuladores de uso para la línea ")
                    .append(linea != null ? linea : "no definida")
                    .append(". Todos los pasos fueron completados sin errores.");
            return conclusion.toString();
        }

        // Si hubo fallo o no es el escenario esperado, usar la lógica anterior
        conclusion.append("Línea validada: ").append(linea != null ? linea : "No definida").append("\n\n");

        boolean fallado = false;
        for (String paso : pasos) {
            if (fallado) {
                conclusion.append("⏭️ Paso no ejecutado: ").append(paso).append("\n");
            } else {
                conclusion.append("✅ ").append(paso).append("\n");
                if (fallo && paso.equalsIgnoreCase(pasoFallido)) {
                    conclusion.append("❌ Falló en: ").append(paso).append("\n");
                    fallado = true;
                }
            }
        }

        conclusion.append("\n");
        conclusion.append("FAILED".equalsIgnoreCase(estadoFinal) ? "⚠️ Prueba fallida." : "✅ Prueba exitosa.");
        return conclusion.toString();
    }

}
