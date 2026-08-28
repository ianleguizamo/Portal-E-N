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

import java.util.HashMap;
import java.util.Map;
import utils.EvidenciaUtils;

public class PaquetesDatos implements Task {


    private static final Logger log = LoggerFactory.getLogger(PaquetesDatos.class);
    Map<String, String> data = new HashMap<>();

    public PaquetesDatos(Map<String, String> data) {
        this.data = data;
    }

    public static Performable paquetesDatos(Map<String, String> data) {
        return Instrumented.instanceOf(PaquetesDatos.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                WaitForResponse.withTarget(PAQUETES_DE_DATOS)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                Click.on(PAQUETES_DE_DATOS),
                WaitForResponse.withTarget(ACCOUNT_ITEM)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                Click.on(ACCOUNT_ITEM),
                WaitForResponse.withTarget(LINE_CHECKBOX)
        );

        EvidenciaUtils.registrarCaptura("Datos de la cuenta");

        actor.attemptsTo(
                Click.on(LINE_CHECKBOX)
        );

        EvidenciaUtils.registrarCaptura("Paquetes de datos");
    }
}