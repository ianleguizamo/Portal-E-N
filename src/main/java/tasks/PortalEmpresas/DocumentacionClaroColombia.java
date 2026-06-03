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

import static userinterfaces.CmaxPage.*;

public class DocumentacionClaroColombia implements Task {

    private static final Logger log = LoggerFactory.getLogger(DocumentacionClaroColombia.class);

    private static final String paso1 = "Consultar Documentacion Claro colombia ";

    public static Performable documentacionClaroColombia(Map<String, String> data) {
        return Instrumented.instanceOf(DocumentacionClaroColombia.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(DOC_CLARO_COL),
                WaitFor.aTime(3000)
        );

        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(
                Click.on(ICONO_CHEVRON_LEFT)
        );
    }
}

