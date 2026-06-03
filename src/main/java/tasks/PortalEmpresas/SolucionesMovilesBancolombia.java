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
import utils.EvidenciaUtils;


import java.util.HashMap;
import java.util.Map;

public class SolucionesMovilesBancolombia implements Task {

    private static final Logger log =
            LoggerFactory.getLogger(SolucionesMovilesBancolombia.class);

    Map<String, String> data = new HashMap<>();

    public SolucionesMovilesBancolombia(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Selecciona Pagos en linea PSE";
    private static final String paso2 = "Selecciona Pago de soluciones moviles";
    private static final String paso3 = "Selecciona Boton Pagar";
    private static final String paso4 = "Selecciona metodo de pago Bancolombia";

    public static Performable solucionesMovilesBancolombia(
            Map<String, String> data
    ) {

        return Instrumented.instanceOf(SolucionesMovilesBancolombia.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Ventana principal

        String ventanaPrincipal = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, 1);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(PAGOS_EN_LINEA),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(PAGO_SOLUCIONES_MOVILES),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                Check.whether(CHECKBOX_FILA.resolveFor(actor).isPresent())
                        .andIfSo(
                                Click.on(CHECKBOX_FILA),
                                WaitForResponse.withTarget(BOTON_PAGAR),
                                Click.on(BOTON_PAGAR)
                        )
                        .otherwise(
                                WaitFor.aTime(1000)
                        )
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                Check.whether(BOTON_BANCOLOMBIA.resolveFor(actor).isPresent())
                        .andIfSo(
                                WaitForResponse.withTarget(BOTON_BANCOLOMBIA),
                                Click.on(BOTON_BANCOLOMBIA)
                        )
                        .otherwise(
                                WaitFor.aTime(1000)
                        )
        );

        EvidenciaUtils.registrarCaptura(paso4);

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



        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal)
        );
    }
}