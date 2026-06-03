package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.WaitFor;

import java.util.HashMap;
import java.util.Map;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.Ensure;
import utils.EvidenciaUtils;

public class RealizarIngreso implements Task {
    Map<String, String> data = new HashMap<String, String>();
    private static final String paso = "Realizar inicio de sesion";

    public RealizarIngreso(Map<String, String> data) {
        this.data = data;
    }

    public static Performable realizarIngreso(Map<String, String> data) {
        return Instrumented.instanceOf(RealizarIngreso.class).withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(data.get("Usuario")).into(TXT_USUARIO),
                Enter.theValue(data.get("Contrasena")).into(TXT_CONTRASENA),
                Ensure.that(BTN_INGRESAR).isDisplayed(),
                JavaScriptClick.on(BTN_INGRESAR),
                WaitFor.aTime(5000),
                JavaScriptClick.on(BTN_ACEPTAR),
                WaitFor.aTime(5000)
        );

        EvidenciaUtils.registrarCaptura(paso);
    }
}