package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import interactions.scroll.ScrollMenuLateral;
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

public class IngresarMenuDesplegable implements Task {


    private static final Logger log = LoggerFactory.getLogger(IngresarMenuDesplegable.class);
    Map<String, String> data = new HashMap<>();

    public IngresarMenuDesplegable(Map<String, String> data) {
        this.data = data;
    }

    public static Performable ingresarMenuDesplegable(Map<String, String> data) {
        return Instrumented.instanceOf(IngresarMenuDesplegable.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {



        actor.attemptsTo(
                Click.on(MENU_DESPLEGABLE),
                WaitFor.aTime(2000),
                ScrollMenuLateral.by(300)
        );

        CapturasPantallasWeb.capturaPantalla("pantalla principal", "se valida todos los menus cerrados");

        actor.attemptsTo(
                WaitUntil.the(TERMINOS_Y_CONDICIONES, isVisible()).forNoMoreThan(5).seconds(),
                SmartClick.on(TERMINOS_Y_CONDICIONES),
                WaitFor.aTime(200)

        );

        CapturasPantallasWeb.capturaPantalla("terminos y condiciones","TyC");

        actor.attemptsTo(
                WaitFor.aTime(200),
                CerrarPestañaYVolver.ahora(),
                SmartClick.on(POLITICAS_PRIVACIDAD)
        );

        CapturasPantallasWeb.capturaPantalla("Politicas de privacidad", "Politicas de privacidad");

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(),
                WaitFor.aTime(200),
                ScrollMenuLateral.by(-300),
                WaitFor.aTime(200)

        );


    }

}
