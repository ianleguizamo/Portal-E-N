package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.WaitFor;
import interactions.EnterPasswordSecure;
import interactions.IngresarTexto;
import interactions.JavaScriptSmartClick;

import java.util.HashMap;
import java.util.Map;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

public class RealizarIngreso implements Task {
    Map<String, String> data = new HashMap<>();
    private static final String paso = "Realizar inicio de sesion";

    public RealizarIngreso(Map<String, String> data) {
        this.data = data;
    }

    public static Performable realizarIngreso(Map<String, String> data) {
        return Instrumented.instanceOf(RealizarIngreso.class).withProperties(data);
    }

    @Override
    @Step("Realizar inicio de sesion en el portal")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                IngresarTexto.con(data.get("Usuario"), "correo electronico", TXT_USUARIO),
                EnterPasswordSecure.into(TXT_CONTRASENA, data.get("Contrasena")),
                JavaScriptSmartClick.on(BTN_INGRESAR)
        );

        WaitFor.silencioso(5000);

        try {
            if (BTN_ACEPTAR.resolveFor(actor).isPresent()) {
                actor.attemptsTo(JavaScriptSmartClick.on(BTN_ACEPTAR));
            }
        } catch (Exception e) {
        }

        WaitFor.silencioso(5000);
        EvidenciaUtils.registrarCaptura(paso);
    }
}