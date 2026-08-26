package utils;

import net.serenitybdd.core.Serenity;
import java.util.Map;

public class TestData {

    // Cargar datos desde Excel y guardarlos en la sesión de Serenity
    public static void cargarDatos(String rutaArchivo, String hoja, int filaNum) {
        Map<String, String> datos = ExcelReader.leerDatos(rutaArchivo, hoja, filaNum);
        Serenity.setSessionVariable("datosUsuario").to(datos);
        // Contrato st-context: esta es la fila con la que va a correr el escenario, asi
        // que es el sitio natural para saber con que usuario y linea se probo.
        ContextoST.registrarDatos(datos);
    }

    // Obtener los datos desde cualquier Step
    @SuppressWarnings("unchecked")
    public static Map<String, String> obtenerDatos() {
        return Serenity.sessionVariableCalled("datosUsuario");
    }
}
