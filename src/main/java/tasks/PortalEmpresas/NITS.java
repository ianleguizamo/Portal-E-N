package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import interactions.SmartClick;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class NITS implements Task {

    Map<String, String> data = new HashMap<>();

    public NITS(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Administrar NITS";

    public static Performable nITS(Map<String, String> data) {
        return Instrumented.instanceOf(NITS.class).withProperties(data);
    }

    @Override
    @Step("Validar administracion de NITs del grupo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SmartClick.on(ADMINISTRAR_NITS));
        WaitFor.silencioso(5000);
        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(SmartClick.on(ICONO_CHEVRON_LEFT));
    }
}