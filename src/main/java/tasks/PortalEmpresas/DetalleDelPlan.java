package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import utils.CerrarEncuestaQualtrics;
import utils.EvidenciaUtils;

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
                SmartClick.on(SOLUCIONES_MOVILES),
                ScrollToTarget.to(DETALLE_DE_TU_PLAN)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                SmartClick.on(DETALLE_DE_TU_PLAN),
                WaitForResponse.withTarget(ACCOUNT_ITEM)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                SmartClick.on(ACCOUNT_ITEM),
                WaitForResponse.withTarget(LINE_ITEM_RADIO)
        );

        EvidenciaUtils.registrarCaptura("Elegir numero");

        actor.attemptsTo(
                SmartClick.on(LINE_ITEM_RADIO),
                SmartClick.on(BTN_CONTINUAR)
        );

        CerrarEncuestaQualtrics.enIframeSiAparece(actor);

        actor.attemptsTo(
                WaitForResponse.withTarget(CUENTA_MAESTRA_CONSUMOS)
        );

        EvidenciaUtils.registrarCaptura("detalle del plan");
    }
}