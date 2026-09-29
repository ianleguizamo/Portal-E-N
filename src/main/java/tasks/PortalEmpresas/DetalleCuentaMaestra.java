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
import utils.EvidenciaUtils;

public class DetalleCuentaMaestra implements Task {


    private static final Logger log = LoggerFactory.getLogger(DetalleCuentaMaestra.class);
    Map<String, String> data = new HashMap<>();

    public DetalleCuentaMaestra(Map<String, String> data) {
        this.data = data;
    }

    public static Performable detalleCuentaMaestra(Map<String, String> data) {
        return Instrumented.instanceOf(DetalleCuentaMaestra.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                SmartClick.on(SOLUCIONES_MOVILES),
                ScrollToTarget.to(DETALLE_CUENTAS_MAESTRAS)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                SmartClick.on(DETALLE_CUENTAS_MAESTRAS),
                WaitForResponse.withTarget(CUENTA_MAESTRA)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                SmartClick.on(CUENTA_MAESTRA),
                SmartClick.on(BTN_CONTINUAR_SELECCION)
        );

        EvidenciaUtils.registrarCaptura("Elegir numero");

    }
}