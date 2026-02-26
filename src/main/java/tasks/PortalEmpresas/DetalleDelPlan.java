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

public class DetalleDelPlan implements Task {


    private static final Logger log = LoggerFactory.getLogger(DetalleDelPlan.class);
    Map<String, String> data = new HashMap<>();

    public DetalleDelPlan(Map<String, String> data) {
        this.data = data;
    }

    public static Performable detalleDelPlan(Map<String, String> data) {
        return Instrumented.instanceOf(DetalleDelPlan.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                ScrollToTarget.to(DETALLE_DE_TU_PLAN)
        );

        CapturasPantallasWeb.capturaPantalla("Soluciones moviles", "Soluciones moviles");

        actor.attemptsTo(
                Click.on(DETALLE_DE_TU_PLAN),
                WaitForResponse.withTarget(ACCOUNT_ITEM)
        );

        CapturasPantallasWeb.capturaPantalla("elegir cuenta", "elegir cuenta");

        actor.attemptsTo(
                Click.on(ACCOUNT_ITEM),
                WaitForResponse.withTarget(LINE_CHECKBOX)
        );

        CapturasPantallasWeb.capturaPantalla("Elegir numero", "Elegir numero");

        actor.attemptsTo(
                Click.on(LINE_CHECKBOX),
                Click.on(BTN_CONTINUAR),

                SwitchToSurveyIframe.now(),
                WaitForResponse.withTarget(BOTON_CERRAR_ENCUESTA),
                Click.on(BOTON_CERRAR_ENCUESTA),
                SwitchToDefaultContent.now(),
                WaitForResponse.withTarget(CUENTA_MAESTRA_CONSUMOS)
        );

        CapturasPantallasWeb.capturaPantalla("detalle del plan", "detalle del plan");
    }
}