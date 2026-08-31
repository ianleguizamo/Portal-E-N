package utils;

import java.util.function.BooleanSupplier;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Espera a que el portal quite su pantalla de carga ("Espera un momento").
 *
 * <p>Existe porque {@code document.readyState} no sirve en este portal: lo completa y
 * DESPUES pinta el contenido por AJAX. Sin esta espera las capturas de evidencia salian
 * con el spinner en vez de la pantalla, y el escenario pasaba igual en verde porque los
 * pasos siguientes tienen sus propias esperas: el fallo solo se veia abriendo el Word.
 *
 * <p>Anclarse al "siguiente elemento que se va a pulsar" no basta: algunos botones (como
 * Continuar) siguen visibles debajo del velo de carga, asi que la espera pasaba de
 * inmediato. El velo es el unico indicador fiable de que el portal termino.
 */
public class EsperarFinDeCarga {

  private static final Logger LOG = LoggerFactory.getLogger(EsperarFinDeCarga.class);

  /**
   * Comprueba el velo por JavaScript y no con findElements para no chocar con el
   * implicitlywait de 10 s: aqui interesa una respuesta inmediata en cada sondeo.
   */
  private static final String VELO_OCULTO =
      "var l = document.getElementById('modalLoader');"
          + "if (!l) { return true; }"
          + "if (l.classList && l.classList.contains('hide')) { return true; }"
          + "var e = window.getComputedStyle(l);"
          + "return e.display === 'none' || e.visibility === 'hidden' || l.offsetParent === null;";

  private static final long TIMEOUT_MS = 15000;
  private static final long INTERVALO_MS = 150;

  /** El velo puede tardar en aparecer; se confirma que sigue oculto tras este margen. */
  private static final long CONFIRMACION_MS = 350;

  private EsperarFinDeCarga() {
    // Clase de utilidad
  }

  /**
   * Bloquea hasta que el portal termine de cargar, como mucho {@value #TIMEOUT_MS} ms.
   *
   * <p>Nunca lanza: esto acompana a la evidencia, y una captura imperfecta no debe tumbar
   * un escenario que por lo demas iba bien. Si se agota el tiempo lo deja en el log.
   */
  public static void ahora() {
    try {
      WebDriver driver = ThucydidesWebDriverSupport.getDriver();
      if (driver == null) {
        return;
      }

      JavascriptExecutor js = (JavascriptExecutor) driver;
      BooleanSupplier oculto = () -> Boolean.TRUE.equals(js.executeScript(VELO_OCULTO));

      if (!esperarHasta(oculto, TIMEOUT_MS)) {
        LOG.warn("El portal seguia cargando tras {} ms; la captura puede salir incompleta", TIMEOUT_MS);
        return;
      }

      // El clic pudo ser tan reciente que el velo aun no habia aparecido: si asoma en
      // este margen, se vuelve a esperar a que se vaya.
      if (!seMantieneOculto(oculto) && !esperarHasta(oculto, TIMEOUT_MS)) {
        LOG.warn("El portal volvio a cargar y no termino a tiempo");
      }

    } catch (RuntimeException noSePudoComprobar) {
      LOG.debug("No se pudo comprobar la pantalla de carga", noSePudoComprobar);
    }
  }

  private static boolean esperarHasta(BooleanSupplier condicion, long topeMs) {
    long limite = System.currentTimeMillis() + topeMs;

    while (System.currentTimeMillis() < limite) {
      if (condicion.getAsBoolean()) {
        return true;
      }
      dormir(INTERVALO_MS);
    }
    return condicion.getAsBoolean();
  }

  private static boolean seMantieneOculto(BooleanSupplier oculto) {
    dormir(CONFIRMACION_MS);
    return oculto.getAsBoolean();
  }

  private static void dormir(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException interrumpido) {
      Thread.currentThread().interrupt();
    }
  }
}
