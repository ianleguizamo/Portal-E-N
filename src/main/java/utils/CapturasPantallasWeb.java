package utils;

import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CapturasPantallasWeb {

    private static final Logger logger = Logger.getLogger(CapturasPantallasWeb.class.getName());
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");

    private static int contador = 1;
    private static final String RUTA_CAPTURAS = "Capturas/";
    // Captura del momento del fallo. Va aparte de las capturas de pasos porque tiene
    // otro ciclo de vida: el informe Word la inserta al final y Smart Tester lee esta
    // misma carpeta para adjuntarla a la alerta (projects.json: screenshotFolder=Error).
    private static final String RUTA_ERROR = "Error/";
    private static final String ARCHIVO_ERROR = "error.png";
    private static final Map<String, String> titulosCapturas = new HashMap<>();

    public static String capturaPantalla(String nombreCaptura, String titulo) {
        try {
            File folder = new File(RUTA_CAPTURAS);
            if (!folder.exists()) folder.mkdirs();

            String fecha = dtf.format(LocalDateTime.now());
            String nombreArchivo = nombreCaptura + "_" + contador + "_" + fecha + ".png";
            contador++;

            File screenshot = ((TakesScreenshot) ThucydidesWebDriverSupport.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(screenshot.toPath(),
                    new File(RUTA_CAPTURAS + nombreArchivo).toPath());

            titulosCapturas.put(nombreArchivo, titulo);

            logger.info("Captura guardada: " + nombreArchivo);

            return nombreArchivo;

        } catch (Exception e) {
            logger.severe("Error al capturar pantalla: " + e.getMessage());
            return null;
        }
    }

    /**
     * Guarda la pantalla tal como quedo al fallar el escenario, en Error/error.png.
     * Devuelve false si no se pudo (driver ya cerrado, por ejemplo): es evidencia, no
     * parte de la prueba, asi que nunca lanza.
     */
    public static boolean capturarError() {
        try {
            File folder = new File(RUTA_ERROR);
            if (!folder.exists()) folder.mkdirs();

            File screenshot = ((TakesScreenshot) ThucydidesWebDriverSupport.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(screenshot.toPath(),
                    new File(RUTA_ERROR + ARCHIVO_ERROR).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            logger.info("Captura del fallo guardada: " + RUTA_ERROR + ARCHIVO_ERROR);
            return true;

        } catch (Exception e) {
            logger.severe("Error al capturar la pantalla del fallo: " + e.getMessage());
            return false;
        }
    }

    /** Borra la captura de fallo del escenario anterior (se llama al arrancar cada uno). */
    public static void limpiarError() {
        File archivo = new File(RUTA_ERROR + ARCHIVO_ERROR);
        if (archivo.exists() && !archivo.delete()) {
            logger.warning("No se pudo eliminar la captura de fallo anterior.");
        }
    }

    public static String obtenerTitulo(String nombreArchivo) {
        return titulosCapturas.getOrDefault(nombreArchivo, "Sin título");
    }

    // Elimina todas las capturas de la carpeta y resetea el contador
    public static void limpiarCapturas() {
        File carpeta = new File(RUTA_CAPTURAS);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.getName().endsWith(".png")) {
                        if (archivo.delete()) {
                            logger.info("Captura eliminada: " + archivo.getName());
                        } else {
                            logger.warning("No se pudo eliminar: " + archivo.getName());
                        }
                    }
                }
            }
        }
        titulosCapturas.clear();
        contador = 1;
        logger.info("Carpeta Capturas limpiada correctamente.");
    }
}