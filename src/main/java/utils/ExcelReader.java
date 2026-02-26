package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> leerDatos(String rutaArchivo, String hoja, int filaNum) {
        Map<String, String> data = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(hoja);
            Row headerRow = sheet.getRow(0);  // Primera fila con nombres de columna
            Row dataRow = sheet.getRow(filaNum);

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell headerCell = headerRow.getCell(i);
                Cell valueCell = dataRow.getCell(i);

                String key = headerCell.getStringCellValue();
                String value = valueCell != null ? valueCell.toString() : "";

                data.put(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
