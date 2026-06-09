package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.conditions.Check;
import net.thucydides.core.annotations.Step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class SolucionesFijasBancolombia implements Task {

    private static final Logger log =
            LoggerFactory.getLogger(SolucionesFijasBancolombia.class);

    Map<String, String> data = new HashMap<>();

    public SolucionesFijasBancolombia(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Selecciona Pagos en linea";
    private static final String paso2 = "Selecciona Pago de soluciones fijas";
    private static final String paso3 = "Selecciona metodo de pago Bancolombia";
    private static final String paso4 = "Validacion ventana confirmacion Bancolombia soluciones fijas";

    public static Performable solucionesFijasBancolombia(Map<String, String> data) {
        return Instrumented.instanceOf(SolucionesFijasBancolombia.class)
                .withProperties(data);
    }

    @Override
    @Step("Validar pago de soluciones fijas por Bancolombia")
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        String ventanaPrincipal = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                SmartClick.on(PAGOS_EN_LINEA),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                SmartClick.on(PAGO_SOLUCIONES_FIJAS_HFC),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                Check.whether(CHECKBOX_FILA.resolveFor(actor).isPresent())
                        .andIfSo(
                                SmartClick.on(CHECKBOX_FILA),
                                WaitFor.aTime(2000),
                                WaitForResponse.withTarget(BOTON_PAGAR),
                                SmartClick.on(BOTON_PAGAR),
                                WaitFor.aTime(2000),
                                SmartClick.on(BOTON_BANCOLOMBIA)
                        )
                        .otherwise(
                                WaitFor.aTime(1000)
                        )
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                JavaScriptSmartClick.on(BOTON_CONTINUAR)
        );

        // Esperar nueva pestaña
        wait.until(d -> d.getWindowHandles().size() > 1);

        // Cambiar a nueva pestaña
        for (String ventana : driver.getWindowHandles()) {
            if (!ventana.equals(ventanaPrincipal)) {
                driver.switchTo().window(ventana);
                break;
            }
        }

        WaitFor.silencioso(3000);

        // Validar contenido de la ventana
        String contenidoPagina = driver.getPageSource();
        boolean paginaPresente = contenidoPagina.contains("Bancolombia")
                || contenidoPagina.contains("Número de Factura")
                || contenidoPagina.contains("Numero de Factura");

        if (paginaPresente) {
            log.info("Ventana de confirmacion Bancolombia soluciones fijas validada correctamente");
            EvidenciaUtils.registrarCaptura(paso4);
        } else {
            log.warn("No se encontro contenido esperado en la ventana de confirmacion Bancolombia");
            EvidenciaUtils.registrarCaptura(paso4 + " - CONTENIDO NO ENCONTRADO");
        }

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal)
        );
    }
}