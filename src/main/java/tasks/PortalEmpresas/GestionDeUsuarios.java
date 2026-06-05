package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;
import interactions.scroll.ScrollUserTable;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import interactions.SmartClick;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class GestionDeUsuarios implements Task {

    Map<String, String> data = new HashMap<>();

    public GestionDeUsuarios(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Selecciona Gestion de Usuario del menu hamburguesa";
    private static final String paso2 = "Selecciona crear usuario";
    private static final String paso3 = "Se valida descarga del documento usuarios";

    public static Performable gestionDeUsuarios(Map<String, String> data) {
        return Instrumented.instanceOf(GestionDeUsuarios.class).withProperties(data);
    }

    @Override
    @Step("Validar gestion de usuarios")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SmartClick.on(GESTION_USUARIOS),
                WaitForResponse.withTarget(CREAR_USUARIO)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                SmartClick.on(CREAR_USUARIO),
                WaitForResponse.withTarget(FLECHA_VOLVER_GU)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                SmartClick.on(FLECHA_VOLVER_GU),
                ScrollUserTable.by(300)
        );
        WaitFor.silencioso(4000);
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                SmartClick.on(BOTON_DESCARGAR)
        );
        WaitFor.silencioso(5000);
        actor.attemptsTo(
                ScrollUserTable.by(-300),
                SmartClick.on(FLECHA_VOLVER_GU),
                SmartClick.on(MENU_DESPLEGABLE)
        );
    }
}