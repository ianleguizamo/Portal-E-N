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
                SmartClick.on(SOLUCIONES_MOVILES),
                WaitForResponse.withTarget(OPCION_REPOSICION_SIM_CARD)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                SmartClick.on(OPCION_REPOSICION_SIM_CARD),
                WaitForResponse.withTarget(CUENTA_MAESTRA)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                SmartClick.on(CUENTA_MAESTRA),
                WaitForResponse.withTarget(TARJETA_LINEA_SELECCIONABLE)
        );

        EvidenciaUtils.registrarCaptura("Datos de la cuenta");

        actor.attemptsTo(
                SmartClick.on(TARJETA_LINEA_SELECCIONABLE),
                SmartClick.on(BOTON_CONTINUAR1),
                WaitForResponse.withTarget(HEADER_TABLA_LINEAS)

        );

        EvidenciaUtils.registrarCaptura("reposicion sim");
    }
}