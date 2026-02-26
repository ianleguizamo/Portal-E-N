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

public class ReposicionSIM implements Task {


    private static final Logger log = LoggerFactory.getLogger(ReposicionSIM.class);
    Map<String, String> data = new HashMap<>();

    public ReposicionSIM(Map<String, String> data) {
        this.data = data;
    }

    public static Performable reposicionSIM(Map<String, String> data) {
        return Instrumented.instanceOf(ReposicionSIM.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                WaitForResponse.withTarget(OPCION_REPOSICION_SIM_CARD)
        );

        CapturasPantallasWeb.capturaPantalla("Soluciones moviles", "Soluciones moviles");

        actor.attemptsTo(
                Click.on(OPCION_REPOSICION_SIM_CARD),
                WaitForResponse.withTarget(CUENTA_CARD_CONTENEDOR)
        );

        CapturasPantallasWeb.capturaPantalla("elegir cuenta", "elegir cuenta");

        actor.attemptsTo(
                Click.on(CUENTA_CARD_CONTENEDOR),
                WaitForResponse.withTarget(TARJETA_LINEA_SELECCIONABLE)
        );

        CapturasPantallasWeb.capturaPantalla("Datos de la cuenta", "Datos de la cuenta");

        actor.attemptsTo(
                Click.on(TARJETA_LINEA_SELECCIONABLE),
                Click.on(BOTON_CONTINUAR1),
                WaitForResponse.withTarget(HEADER_TABLA_LINEAS)

        );

        CapturasPantallasWeb.capturaPantalla("reposicion sim", "reposicion sim");
    }
}