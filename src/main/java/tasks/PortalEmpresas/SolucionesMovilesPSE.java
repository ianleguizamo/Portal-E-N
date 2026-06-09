package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;


import javax.lang.model.element.ElementVisitor;
import java.util.HashMap;
import java.util.Map;

public class SolucionesMovilesPSE implements Task {

    private static final Logger log =
            LoggerFactory.getLogger(SolucionesMovilesPSE.class);

    Map<String, String> data = new HashMap<>();

    public SolucionesMovilesPSE(Map<String, String> data) {
        this.data = data;
    }
    private static final String paso1 = "Selecciona Pagos en linea PSE";
    private static final String paso2 = "Selecciona Pago de soluciones móviles";
    private static final String paso3 = "Selecciona metodo de pago PSE";

    public static Performable solucionesMovilesPSE(
            Map<String, String> data
    ) {

        return Instrumented.instanceOf(SolucionesMovilesPSE.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // VEentana principal

        String ventanaPrincipal = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, 10);

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
                Click.on(CHECKBOX_CUSTOM),

                WaitFor.aTime(2000),

                WaitForResponse.withTarget(BOTON_PAGAR),

                Click.on(BOTON_PAGAR)

        );


        actor.attemptsTo(
                WaitFor.aTime(2000),
                WaitForResponse.withTarget(METODO_PSE),
                Click.on(METODO_PSE),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                SmartClick.on(BOTON_CONTINUAR)
        );

        // Esperar nueva pestaña

        wait.until(d -> d.getWindowHandles().size() > 1);

        // Cambiar a  nueva pestaña

        for (String ventana : driver.getWindowHandles()) {

            if (!ventana.equals(ventanaPrincipal)) {

                driver.switchTo().window(ventana);

                break;
            }
        }

        actor.attemptsTo(
                WaitFor.aTime(2000)
        );



        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal)
        );
    }
}