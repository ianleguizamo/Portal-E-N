package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.*;
import static utils.Constants.PAGOS_AUTOMATICOS;

import interactions.*;

import questions.ValidarTexto;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CerrarEncuestaQualtrics;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Pagos automaticos de facturas, opcion Soluciones moviles.
 *
 * <p>Todo el contenido de esta pantalla lo sirve el portal de pagos externo dentro del iframe
 * IFRAME_PAGOS_AUTOMATICOS. La version anterior buscaba el titulo en el HTML de la pagina
 * principal, donde nunca esta, asi que el tag no habia pasado nunca en Smart Tester.
 */
public class PagosAutoFacturasSolucionesMoviles implements Task {

    private static final int ESPERA_SEGUNDOS = 30;

    Map<String, String> data = new HashMap<>();

    private static final String paso2 = "Valida seccion Pagos automaticos";
    private static final String paso3 = "Ingreso exitoso a Pagos automaticos";
    private static final String paso4 = "Selecciona Soluciones Moviles en Pagos automaticos";

    public PagosAutoFacturasSolucionesMoviles(Map<String, String> data) {
        this.data = data;
    }

    public static Performable pagosAutoFacturas(Map<String, String> data) {
        return Instrumented.instanceOf(PagosAutoFacturasSolucionesMoviles.class)
                .withProperties(data);
    }

    @Override
    @Step("Validar pago automatico de facturas")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                SmartClick.on(PAGO_AUTOMATICO_FACTURAS)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        // La encuesta vive en la pagina principal: se cierra antes de entrar al iframe.
        CerrarEncuestaQualtrics.enIframeSiAparece(actor);

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        new WebDriverWait(driver, ESPERA_SEGUNDOS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IFRAME_PAGOS_AUTOMATICOS));

        try {
            actor.attemptsTo(
                    WaitUntil.the(TITULO_PAGOS_AUTOMATICOS, isVisible()).forNoMoreThan(ESPERA_SEGUNDOS).seconds()
            );
            ValidarTexto.contiene(actor, PAGOS_AUTOMATICOS, paso3);

            actor.attemptsTo(
                    SmartClick.on(SOLUCIONES_MOVILES2),
                    WaitUntil.the(DESCARGAR_ARCHIVO, isVisible()).forNoMoreThan(ESPERA_SEGUNDOS).seconds(),
                    Scroll.to(DESCARGAR_ARCHIVO)
            );
            EvidenciaUtils.registrarCaptura(paso4);

        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
