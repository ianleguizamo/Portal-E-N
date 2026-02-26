package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

  public static String getCellValue(
      String filePath, String sheetName, int rowNumber, int columnNumber) {
    String cellValue = null;
    try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
      Sheet sheet = workbook.getSheet(sheetName);
      Row row = sheet.getRow(rowNumber);
      Cell cell = row.getCell(columnNumber);
      cellValue = cell.getStringCellValue();
    } catch (IOException | EncryptedDocumentException e) {
      e.printStackTrace();
    }
    return cellValue;
  }

  public static Row getRowWithFilter(String filePath, String sheetName) {
    int paqueteComprarColumn = getColumnNumberByName(filePath, sheetName, "paqueteComprar");

    try {
      FileInputStream file = new FileInputStream(new File(filePath));
      Workbook workbook = new XSSFWorkbook(file);
      Sheet sheet = workbook.getSheet(sheetName);

      for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(paqueteComprarColumn);
        if (cell != null
            && cell.getStringCellValue()
                .equalsIgnoreCase("X")) { // Cambia "X" por el valor que indique la fila filtrada
          workbook.close();
          return row;
        }
      }

      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    throw new IllegalArgumentException("No se encontró la fila con el valor de filtro.");
  }

  public static int getColumnNumberByName(String filePath, String sheetName, String columnName) {
    try {
      FileInputStream file = new FileInputStream(new File(filePath));
      Workbook workbook = new XSSFWorkbook(file);
      Sheet sheet = workbook.getSheet(sheetName);

      Row headerRow = sheet.getRow(0);
      for (int columnIndex = 0; columnIndex < headerRow.getLastCellNum(); columnIndex++) {
        Cell cell = headerRow.getCell(columnIndex);
        if (cell != null && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
          workbook.close();
          return columnIndex;
        }
      }

      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    throw new IllegalArgumentException("No se encontró la columna con el nombre especificado.");
  }

  public static int getRowNumberWithFilter(
      String filePath, String sheetName, String columnName, String filterValue) {
    try {
      FileInputStream fis = new FileInputStream(new File(filePath));
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      XSSFSheet sheet = workbook.getSheet(sheetName);

      int columnNumber = getColumnNumber(sheet, columnName);

      if (columnNumber == -1) {
        System.out.println("No se encontró la columna con el nombre especificado.");
        return -1;
      }

      for (Row row : sheet) {
        Cell cell = row.getCell(columnNumber);
        if (cell != null && cell.getStringCellValue().equalsIgnoreCase(filterValue)) {
          return row.getRowNum();
        }
      }

      System.out.println("No se encontró la fila con el valor de filtro.");
      return -1;

    } catch (IOException e) {
      System.out.println("Error al leer el archivo: " + e.getMessage());
      return -1;
    }
  }

  private static int getColumnNumber(XSSFSheet sheet, String columnName) {
    Row firstRow = sheet.getRow(0);
    for (Cell cell : firstRow) {
      if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
        return cell.getColumnIndex();
      }
    }
    return -1;
  }
}
