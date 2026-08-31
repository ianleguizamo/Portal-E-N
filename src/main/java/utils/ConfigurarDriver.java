package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Deja listo el chromedriver que corresponde al Chrome instalado en la maquina.
 *
 * <p>Antes la ruta del driver estaba fija en serenity.properties apuntando a
 * C:/WebDrivers/chromedriver/chromedriver.exe. Como Chrome se actualiza solo, cada pocas
 * semanas el driver se quedaba atras y la suite entera moria en el arranque del navegador
 * con "This version of ChromeDriver only supports Chrome version N".
 *
 * <p>WebDriverManager detecta la version de Chrome instalada, descarga el driver que le
 * toca y lo cachea en ~/.cache/selenium. Solo baja algo la primera vez tras cada
 * actualizacion de Chrome.
 *
 * <p>OJO con la version de WebDriverManager: hasta la 5.0.3 preguntaba al repositorio
 * antiguo (chromedriver.storage.googleapis.com), que se quedo en Chrome 114. Con Chrome
 * moderno devolvia 404 y —lo peor— en vez de fallar caia a la 114 y reportaba exito. Hace
 * falta una version que hable con los endpoints de Chrome for Testing.
 */
public class ConfigurarDriver {

  private static final Logger LOG = LoggerFactory.getLogger(ConfigurarDriver.class);

  /** Escape para servidores sin salida a internet: -Dchromedriver.ruta=C:/ruta/chromedriver.exe */
  private static final String RUTA_MANUAL = "chromedriver.ruta";

  private static final String PROPIEDAD_SELENIUM = "webdriver.chrome.driver";

  private static boolean yaConfigurado = false;

  private ConfigurarDriver() {
    // Clase de utilidad
  }

  /**
   * Resuelve el driver una sola vez por ejecucion.
   *
   * @throws IllegalStateException si no se pudo resolver, con la indicacion de que hacer.
   *     Es preferible a dejar que el fallo aparezca despues como un error de arranque del
   *     navegador, que no dice nada de la causa.
   */
  public static synchronized void chrome() {
    if (yaConfigurado) {
      return;
    }

    String rutaManual = System.getProperty(RUTA_MANUAL);

    if (rutaManual != null && !rutaManual.trim().isEmpty()) {
      System.setProperty(PROPIEDAD_SELENIUM, rutaManual.trim());
      LOG.warn("Usando el chromedriver indicado a mano: {}", rutaManual.trim());
      yaConfigurado = true;
      return;
    }

    try {
      WebDriverManager.chromedriver().setup();
      LOG.info("chromedriver resuelto en {}", System.getProperty(PROPIEDAD_SELENIUM));
      yaConfigurado = true;

    } catch (RuntimeException noSePudoResolver) {
      throw new IllegalStateException(
          "No se pudo obtener el chromedriver para el Chrome instalado. "
              + "Si la maquina no tiene salida a internet, descarga el driver que toque y "
              + "pasalo con -D"
              + RUTA_MANUAL
              + "=<ruta al chromedriver.exe>.",
          noSePudoResolver);
    }
  }
}
