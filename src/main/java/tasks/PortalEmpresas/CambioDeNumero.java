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

public class CambioDeNumero implements Task {


    private static final Logger log = LoggerFactory.getLogger(CambioDeNumero.class);
    Map<String, String> data = new HashMap<>();

    public CambioDeNumero(Map<String, String> data) {
        this.data = data;
    }

    public static Performable cambioDeNumero(Map<String, String> data) {
        return Instrumented.instanceOf(CambioDeNumero.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                WaitForResponse.withTarget(CAMBIO_DE_NUMERO)
        );

        CapturasPantallasWeb.capturaPantalla("Soluciones moviles", "Soluciones moviles");

        actor.attemptsTo(
                Click.on(CAMBIO_DE_NUMERO)
        );

        CapturasPantallasWeb.capturaPantalla("elegir cuenta", "elegir cuenta");

        actor.attemptsTo(
                Click.on(ACCOUNT_ITEM),
                Click.on(CHECKBOX_CUSTOM_RADIO)
        );

        CapturasPantallasWeb.capturaPantalla("elegir numero", "elegir numero");

        actor.attemptsTo(
                Click.on(BTN_CONTINUAR),
                WaitForResponse.withTarget(INFO_CAMBIO_NUMERO)
        );

        CapturasPantallasWeb.capturaPantalla(".", ".");
    }
}