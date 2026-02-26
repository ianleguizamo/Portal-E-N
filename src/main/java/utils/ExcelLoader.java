package utils;

import cucumber.api.DataTable;

import java.util.List;
import java.util.Map;

public class ExcelLoader {

    public static Map<String, String> cargarDatosDesdeExcel(DataTable dataExcel, int row) throws Exception {
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);
        Excel excel = new Excel(
                dataFeature.get(0).get("Ruta Excel"),
                dataFeature.get(0).get("Pestana"),
                true,
                row
        );

        return new DataDrivenExcel().leerExcel(excel);
    }
}
