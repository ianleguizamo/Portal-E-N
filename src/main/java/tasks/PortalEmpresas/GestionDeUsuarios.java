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

import java.util.HashMap;
import java.util.Map;

public class GestionDeUsuarios implements Task {


    private static final Logger log = LoggerFactory.getLogger(GestionDeUsuarios.class);
    Map<String, String> data = new HashMap<>();

    public GestionDeUsuarios(Map<String, String> data) {
        this.data = data;
    }

    public static Performable gestionDeUsuarios(Map<String, String> data) {
        return Instrumented.instanceOf(GestionDeUsuarios.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        CapturasPantallasWeb.capturaPantalla("gestion de usuarios", "gestion de usuarios");

        actor.attemptsTo(
                Click.on(GESTION_USUARIOS),
                WaitForResponse.withTarget(CREAR_USUARIO)
        );

        CapturasPantallasWeb.capturaPantalla("gestion de usuario abierta", "gestion de usuario abierta");

        actor.attemptsTo(
                Click.on(CREAR_USUARIO),
                WaitForResponse.withTarget(FLECHA_VOLVER_GU)

        );

        CapturasPantallasWeb.capturaPantalla("Crear usuario", "formulario creacion usuario");

        actor.attemptsTo(
                Click.on(FLECHA_VOLVER_GU),
                WaitFor.aTime(400),
                ScrollUserTable.by(300)
        );

        CapturasPantallasWeb.capturaPantalla("boton descargar", "boton descargar");

        actor.attemptsTo(
                Click.on(BOTON_DESCARGAR),
                WaitFor.aTime(300),
                ScrollUserTable.by(-300),
                Click.on(FLECHA_VOLVER_GU),
                Click.on(BTN_ACEPTAR),
                Click.on(MENU_DESPLEGABLE)
        );


    }

}
