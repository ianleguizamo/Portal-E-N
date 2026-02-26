package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;
import static userinterfaces.CmaxPage.BTN_OK;

import interactions.WaitFor;

import java.util.HashMap;
import java.util.Map;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.EnterValueIntoBy;
import utils.EvidenciaUtils;

public class RealizarIngreso implements Task {
    Map<String, String> data = new HashMap<String, String>();
    private static final String paso = "Verificar el estado inicial de la línea";
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
                Click.on(BOTON_CONTINUAR1),
                WaitFor.aTime(10000),
                Click.on(BTN_ACEPTAR)
                );

        EvidenciaUtils.registrarCaptura(paso);
    }
}