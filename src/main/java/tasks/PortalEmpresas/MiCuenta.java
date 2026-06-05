package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import interactions.SmartClick;
import net.serenitybdd.screenplay.actions.Scroll;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class MiCuenta implements Task {

    Map<String, String> data = new HashMap<>();

    public MiCuenta(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Selecciona Mi cuenta en el menu hamburguesa";
    private static final String paso2 = "Se validan Informacion del usuario";
    private static final String paso3 = "Selecciona Informacion del usuario";

    public static Performable miCuenta(Map<String, String> data) {
        return Instrumented.instanceOf(MiCuenta.class).withProperties(data);
    }

    @Override
    @Step("Validar informacion de mi cuenta")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SmartClick.on(MI_CUENTA),
                WaitForResponse.withTarget(INFORMACION_USUARIO)
        );
        EvidenciaUtils.registrarCaptura(paso1);
        WaitFor.silencioso(2000);
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitForResponse.withTarget(INFORMACION_USUARIO),
                Scroll.to(INFORMACION_USUARIO)
        );
        WaitFor.silencioso(1000);
        actor.attemptsTo(SmartClick.on(INFORMACION_USUARIO));
        EvidenciaUtils.registrarCaptura(paso3);
    }
}