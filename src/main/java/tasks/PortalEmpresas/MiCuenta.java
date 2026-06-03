package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;
import interactions.scroll.ScrollMenuLateral;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class MiCuenta implements Task {

    private static final Logger log = LoggerFactory.getLogger(MiCuenta.class);
    Map<String, String> data = new HashMap<>();

    public MiCuenta(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Selecciona Mi cuenta en el menu hamburguesa";
    private static final String paso2 = "Se validan Información del usuario ";
    private static final String paso3 = "Selecciona Información del usuario";

    public static Performable miCuenta(Map<String, String> data) {
        return Instrumented.instanceOf(MiCuenta.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(MI_CUENTA),
                WaitForResponse.withTarget(INFORMACION_USUARIO)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitForResponse.withTarget(INFORMACION_USUARIO),
                Scroll.to(INFORMACION_USUARIO),
                WaitFor.aTime(1000),
                Click.on(INFORMACION_USUARIO)
        );
        EvidenciaUtils.registrarCaptura(paso3);
    }
}