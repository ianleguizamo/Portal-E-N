package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CapturasPantallasWeb;

import java.util.HashMap;
import java.util.Map;

public class Roaming implements Task {


    private static final Logger log = LoggerFactory.getLogger(Roaming.class);
    Map<String, String> data = new HashMap<>();

    public Roaming(Map<String, String> data) {
        this.data = data;
    }

    public static Performable roaming(Map<String, String> data) {
        return Instrumented.instanceOf(Roaming.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                WaitForResponse.withTarget(ROAMING_INTERNACIONAL)
        );

        CapturasPantallasWeb.capturaPantalla("Soluciones moviles", "Soluciones moviles");

        actor.attemptsTo(
                Click.on(ROAMING_INTERNACIONAL),
                WaitForResponse.withTarget(CUENTA_CARD_CONTENEDOR)
        );

        CapturasPantallasWeb.capturaPantalla("elegir cuenta", "elegir cuenta");

        actor.attemptsTo(
                Click.on(CUENTA_CARD_CONTENEDOR),
                WaitForResponse.withTarget(BOTON_ACEPTAR),
                Click.on(BOTON_ACEPTAR)

        );

        CapturasPantallasWeb.capturaPantalla("Datos de la cuenta", "Datos de la cuenta");

        actor.attemptsTo(
                WaitForResponse.withTarget(TEXTO_DESCRIPCION_ROAMING)
        );

        CapturasPantallasWeb.capturaPantalla("Cambio de departamento", "Cambio de departamento");
    }
}