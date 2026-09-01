package utils;

import static userinterfaces.CmaxPage.*;

import interactions.SmartClick;
import interactions.WaitFor;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CerrarEncuestaQualtrics {

    private static final Logger log = LoggerFactory.getLogger(CerrarEncuestaQualtrics.class);

    private static final String pasoEncuesta = "Validacion encuesta Qualtrics";

    /** El mismo valor que webdriver.timeouts.implicitlywait de serenity.properties. */
    private static final int ESPERA_IMPLICITA_SEGUNDOS = 10;

    public static void siAparece(Actor actor) {
        try {
            if (BOTON_CERRAR_ENCUESTA.resolveFor(actor).isPresent()) {
                actor.attemptsTo(SmartClick.on(BOTON_CERRAR_ENCUESTA));
                log.info("Encuesta Qualtrics cerrada correctamente");
                WaitFor.silencioso(3000);
                EvidenciaUtils.registrarCaptura(pasoEncuesta + " - CERRADA");
            } else {
                log.info("La encuesta Qualtrics no aparecio, continuando sin cerrarla");
                EvidenciaUtils.registrarCaptura(pasoEncuesta + " - NO APARECIO");
            }
        } catch (Exception e) {
            log.info("No se pudo verificar la encuesta Qualtrics, continuando: " + e.getMessage());
            EvidenciaUtils.registrarCaptura(pasoEncuesta + " - ERROR DE VERIFICACION");
        }
    }

    /**
     * Cierra la encuesta cuando vive dentro de un iframe, y no hace nada si no aparecio.
     *
     * <p>Cuatro Tasks hacian este recorrido a mano y SIN condicional: cambiaban al iframe,
     * esperaban el boton de cerrar y lo pulsaban. Como la encuesta es intermitente, las
     * veces que no salia el escenario moria esperando un boton que nunca iba a existir,
     * aunque el flujo de negocio estuviera perfecto.
     *
     * <p>Durante el barrido se pone el implicitlywait a 0: findElements espera el timeout
     * completo cuando no encuentra nada, y recorrer varios iframes a 10 s cada uno hacia
     * la comprobacion carisima.
     */
    public static void enIframeSiAparece(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.switchTo().defaultContent();

            for (WebElement iframe : driver.findElements(By.tagName("iframe"))) {
                driver.switchTo().frame(iframe);

                List<WebElement> cerrar =
                        driver.findElements(By.xpath("//img[contains(@src,'close')]"));

                if (!cerrar.isEmpty()) {
                    cerrar.get(0).click();
                    log.info("Encuesta Qualtrics cerrada correctamente");
                    driver.switchTo().defaultContent();
                    WaitFor.silencioso(2000);
                    EvidenciaUtils.registrarCaptura(pasoEncuesta + " - CERRADA");
                    return;
                }
                driver.switchTo().defaultContent();
            }

            log.info("La encuesta Qualtrics no aparecio, continuando sin cerrarla");

        } catch (RuntimeException noSePudoVerificar) {
            log.info("No se pudo verificar la encuesta Qualtrics, continuando: "
                    + noSePudoVerificar.getMessage());
        } finally {
            try {
                driver.switchTo().defaultContent();
            } catch (RuntimeException ignored) {
                // el driver decide; no es motivo para tumbar la prueba
            }
            driver.manage().timeouts().implicitlyWait(ESPERA_IMPLICITA_SEGUNDOS, TimeUnit.SECONDS);
        }
    }
}
