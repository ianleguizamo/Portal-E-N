package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenExcel {

  public Map<String, String> leerExcel(Excel excel) {

    Map<String, String> datosExcel = new HashMap<>();
    DataFormatter formatter = new DataFormatter(); // Formatea cualquier celda a String

    try {
      FileInputStream arcExcel = new FileInputStream(new File(excel.getRutaExcel()));
      Workbook libroExcel = new XSSFWorkbook(arcExcel);
      Sheet hojaArcExcel = libroExcel.getSheet(excel.getHojaExcel());
      Iterator<Row> iterator = hojaArcExcel.iterator();
      ArrayList<String> cabeceras = new ArrayList<>();

      while (iterator.hasNext()) {
        Row filaActual = iterator.next();
        int numFila = filaActual.getRowNum();

        if ((excel.isContieneCabecera() && numFila == 0) || numFila == excel.getFilaLeer()) {
          for (Cell celdaActual : filaActual) {
            String valorCelda = formatter.formatCellValue(celdaActual); // Siempre String

            if (excel.isContieneCabecera()) {
              if (numFila == 0) {
                cabeceras.add(valorCelda);
              } else {
                datosExcel.put(cabeceras.get(celdaActual.getColumnIndex()), valorCelda);
              }
            } else {
              if (numFila == excel.getFilaLeer()) {
                datosExcel.put(String.valueOf(celdaActual.getColumnIndex()), valorCelda);
              }
            }
          }
        }
      }

      libroExcel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return datosExcel;
  }
}
