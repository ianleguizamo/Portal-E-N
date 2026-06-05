package tasks.PortalEmpresas;

import interactions.WaitFor;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import interactions.SmartClick;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

import java.util.Map;

import static userinterfaces.CmaxPage.*;

public class DocumentacionClaroColombia implements Task {

    private static final String paso1 = "Consultar Documentacion Claro Colombia";

    public static Performable documentacionClaroColombia(Map<String, String> data) {
        return Instrumented.instanceOf(DocumentacionClaroColombia.class).withProperties(data);
    }

    @Override
    @Step("Acceder a la documentacion de Claro Colombia")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SmartClick.on(DOC_CLARO_COL));
        WaitFor.silencioso(3000);
        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(SmartClick.on(ICONO_CHEVRON_LEFT));
    }
}