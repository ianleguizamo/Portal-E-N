package utils;

import net.serenitybdd.core.Serenity;
import java.util.Map;

public class TestData {

    // Cargar datos desde Excel y guardarlos en la sesión de Serenity
    public static void cargarDatos(String rutaArchivo, String hoja, int filaNum) {
        Map<String, String> datos = ExcelReader.leerDatos(rutaArchivo, hoja, filaNum);
        Serenity.setSessionVariable("datosUsuario").to(datos);
    }

    // Obtener los datos desde cualquier Step
    @SuppressWarnings("unchecked")
    public static Map<String, String> obtenerDatos() {
        return Serenity.sessionVariableCalled("datosUsuario");
    }
}
