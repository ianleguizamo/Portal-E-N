package tasks.PortalEmpresas;

import interactions.WaitFor;
import interactions.SmartClick;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

import java.util.Map;

import static userinterfaces.CmaxPage.*;

public class ConsultorAsignado implements Task {

    private static final String paso1 = "Consultor asignado";

    public static Performable consultorAsignado(Map<String, String> data) {
        return Instrumented.instanceOf(ConsultorAsignado.class).withProperties(data);
    }

    @Override
    @Step("Visualizar informacion del consultor asignado")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SmartClick.on(CONSULTOR_ASIGNADO));
        WaitFor.silencioso(3000);
        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(SmartClick.on(ICONO_CHEVRON_LEFT));
    }
}