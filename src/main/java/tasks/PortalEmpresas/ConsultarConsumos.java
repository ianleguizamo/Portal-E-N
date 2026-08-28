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

public class ConsultarConsumos implements Task {


    private static final Logger log = LoggerFactory.getLogger(ConsultarConsumos.class);
    Map<String, String> data = new HashMap<>();

    public ConsultarConsumos(Map<String, String> data) {
        this.data = data;
    }

    public static Performable consultarConsumos(Map<String, String> data) {
        return Instrumented.instanceOf(ConsultarConsumos.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                ScrollToTarget.to(CONSULTAR_CONSUMOS),
                WaitForResponse.withTarget(CONSULTAR_CONSUMOS)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                Click.on(CONSULTAR_CONSUMOS),
                WaitForResponse.withTarget(CUENTA_MAESTRA)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                Click.on(CUENTA_MAESTRA),

                SwitchToSurveyIframe.now(),
                WaitForResponse.withTarget(BOTON_CERRAR_ENCUESTA),
                Click.on(BOTON_CERRAR_ENCUESTA),
                SwitchToDefaultContent.now(),
                WaitForResponse.withTarget(CUENTA_MAESTRA_CONSUMOS)
        );

        EvidenciaUtils.registrarCaptura("Consumos de minutos");

        actor.attemptsTo(
                Click.on(CONSUMO_DATOS),
                WaitForResponse.withTarget(CUENTA_MAESTRA_CONSUMOS)
        );

        EvidenciaUtils.registrarCaptura("Consumo de datos");
    }
}