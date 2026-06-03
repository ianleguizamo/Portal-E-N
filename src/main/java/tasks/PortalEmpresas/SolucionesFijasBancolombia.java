package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.conditions.Check;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.CapturasPantallasWeb;

import java.util.HashMap;
import java.util.Map;

public class SolucionesFijasBancolombia implements Task {

    private static final Logger log =
            LoggerFactory.getLogger(SolucionesFijasBancolombia.class);

    Map<String, String> data = new HashMap<>();

    public SolucionesFijasBancolombia(Map<String, String> data) {
        this.data = data;
    }

    public static Performable solucionesFijasBancolombia(
            Map<String, String> data
    ) {

        return Instrumented.instanceOf(SolucionesFijasBancolombia.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Ventana principal

        String ventanaPrincipal = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        actor.attemptsTo(
                Click.on(PAGOS_EN_LINEA)
        );

        CapturasPantallasWeb.capturaPantalla(
                "Pagos en linea",
                "Pagos en linea"
        );

        actor.attemptsTo(
                Click.on(PAGO_SOLUCIONES_FIJAS_HFC)
        );

        CapturasPantallasWeb.capturaPantalla(
                "Soluciones moviles",
                "Soluciones moviles"
        );

        actor.attemptsTo(
                Check.whether(CHECKBOX_FILA.resolveFor(actor).isPresent())
                        .andIfSo(

                                Click.on(CHECKBOX_FILA),

                                WaitForResponse.withTarget(BOTON_PAGAR),

                                Click.on(BOTON_PAGAR),

                                WaitForResponse.withTarget(BOTON_BANCOLOMBIA),

                                Click.on(BOTON_BANCOLOMBIA)

                        )
                        .otherwise(
                                WaitFor.aTime(100)
                        )
        );

        CapturasPantallasWeb.capturaPantalla(
                "BANCOLOMBIA",
                "BANCOLOMBIA"
        );

        actor.attemptsTo(
                SmartClick.on(BOTON_CONTINUAR)
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

        actor.attemptsTo(
                WaitFor.aTime(1000)
        );

        CapturasPantallasWeb.capturaPantalla(
                "Redireccionamiento BANCOLOMBIA",
                "Redireccionamiento BANCOLOMBIA"
        );

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal)
        );
    }
}