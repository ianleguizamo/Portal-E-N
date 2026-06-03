package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import interactions.scroll.ScrollMenuLateral;
import interactions.scroll.ScrollUserTable;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CapturasPantallasWeb;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class NITS implements Task {


    private static final Logger log = LoggerFactory.getLogger(NITS.class);
    Map<String, String> data = new HashMap<>();

    public NITS(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Administrar NITS";

    public static Performable nITS(Map<String, String> data) {
        return Instrumented.instanceOf(NITS.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(ADMINISTRAR_NITS),
                WaitFor.aTime(5000)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                Click.on(ICONO_CHEVRON_LEFT)
        );

    }

}
