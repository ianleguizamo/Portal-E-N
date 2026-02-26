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

public class MiCuenta implements Task {


    private static final Logger log = LoggerFactory.getLogger(MiCuenta.class);
    Map<String, String> data = new HashMap<>();

    public MiCuenta(Map<String, String> data) {
        this.data = data;
    }

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

        CapturasPantallasWeb.capturaPantalla("Mi cuenta click", "mi cuenta click");

        actor.attemptsTo(
                Click.on(INFORMACION_USUARIO),
                ScrollMenuLateral.by(200),
                WaitFor.aTime(200)

        );

        CapturasPantallasWeb.capturaPantalla("Informacion del usuario", "Info user");

        actor.attemptsTo(

                ScrollMenuLateral.by(-200),
                WaitForResponse.withTarget(GESTION_CUENTA),
                Click.on(GESTION_CUENTA),
                WaitFor.aTime(200),
                Click.on(GESTION_DE_CUENTA)
        );


    }

}
