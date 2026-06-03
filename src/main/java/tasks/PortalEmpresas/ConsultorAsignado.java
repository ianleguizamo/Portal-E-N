package tasks.PortalEmpresas;

import interactions.WaitFor;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

import java.util.Map;

import static userinterfaces.CmaxPage.CONSULTOR_ASIGNADO;
import static userinterfaces.CmaxPage.ICONO_CHEVRON_LEFT;

public class ConsultorAsignado implements Task {

    private static final Logger log = LoggerFactory.getLogger(ConsultorAsignado.class);

    private static final String paso1 = "Consultor asignado";

    public static Performable consultorAsignado(Map<String, String> data) {
        return Instrumented.instanceOf(ConsultorAsignado.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CONSULTOR_ASIGNADO),
                WaitFor.aTime(3000)
        );

        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(
                Click.on(ICONO_CHEVRON_LEFT)
        );
    }
}