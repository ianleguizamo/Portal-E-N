package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;


import interactions.scroll.ScrollUserTable;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class GestionDeUsuarios implements Task {


    private static final Logger log = LoggerFactory.getLogger(GestionDeUsuarios.class);
    Map<String, String> data = new HashMap<>();

    public GestionDeUsuarios(Map<String, String> data) {
        this.data = data;
    }
    private static final String paso1 = "Selecciona Gestión de Usuario del menu hamburguesa";
    private static final String paso2 = "Selecciona crear usuario";
    private static final String paso3 = "Se valida descarga del documento usuarios";

    public static Performable gestionDeUsuarios(Map<String, String> data) {
        return Instrumented.instanceOf(GestionDeUsuarios.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(GESTION_USUARIOS),
                WaitForResponse.withTarget(CREAR_USUARIO)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                Click.on(CREAR_USUARIO),
                WaitForResponse.withTarget(FLECHA_VOLVER_GU)

        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                Click.on(FLECHA_VOLVER_GU),
                WaitFor.aTime(4000),
                ScrollUserTable.by(300)
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                Click.on(BOTON_DESCARGAR),
                WaitFor.aTime(5000),
                ScrollUserTable.by(-300),
                Click.on(FLECHA_VOLVER_GU),
                Click.on(MENU_DESPLEGABLE)
                );


    }

}
