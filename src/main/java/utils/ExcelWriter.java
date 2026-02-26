package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelWriter {

    private static final String OUTPUT_FILE = "src/test/resources/data/output/ResultadosConsultaSaldo.xlsx";

    public static synchronized void registrarSaldo(String numero, String saldo, String paquetes) {
        try {
            Files.createDirectories(Paths.get("src/test/resources/data/output/"));
            File file = new File(OUTPUT_FILE);
            Workbook workbook;
            Sheet sheet;

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Resultados");

                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Fecha");
                header.createCell(1).setCellValue("Número");
                header.createCell(2).setCellValue("Saldo");
                header.createCell(3).setCellValue("Paquetes");
            }

            if (saldo == null || saldo.trim().isEmpty() || saldo.equalsIgnoreCase("Pendiente")) return;

            // Limpiar texto del saldo
            saldo = saldo.replace("COP", "").replace(",", "").trim();

            // Fecha sin hora
            String fechaHoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // Evitar duplicado del número
            boolean existe = false;
            for (Row r : sheet) {
                Cell c = r.getCell(1);
                if (c != null && c.getStringCellValue().equals(numero)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                int lastRow = sheet.getLastRowNum() + 1;
                Row row = sheet.createRow(lastRow);
                row.createCell(0).setCellValue(fechaHoy);
                row.createCell(1).setCellValue(numero);
                row.createCell(2).setCellValue(saldo);
                row.createCell(3).setCellValue(paquetes);
            }

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook.close();
            fos.close();

            System.out.println("📘 Registro guardado en Excel: " + numero + " → " + paquetes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
