package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import utils.CapturasPantallasWeb;

import java.util.HashMap;
import java.util.Map;

public class IngresarNumeroConsultaInicial implements Task {


    Map<String, String> data = new HashMap<>();

    public IngresarNumeroConsultaInicial(Map<String, String> data) {
        this.data = data;
    }

    public static Performable ingresarNumeroConsultaInicial(Map<String, String> data) {
        return Instrumented.instanceOf(IngresarNumeroConsultaInicial.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CAMPANA),
                WaitFor.aTime(2000)

        );

        CapturasPantallasWeb.capturaPantalla("click_campana", "Se hizo click en la campana");

        actor.attemptsTo(
                Click.on(CAMPANA_X),
                WaitForResponse.withTarget(MENU_DESPLEGABLE),
                Click.on(MENU_DESPLEGABLE),
                WaitFor.aTime(2000)
        );

        CapturasPantallasWeb.capturaPantalla("click_menu", "Se hizo click en menu desplegable");

        actor.attemptsTo(
                WaitUntil.the(BTN_CERRAR_MENU, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(BTN_CERRAR_MENU)
        );

        CapturasPantallasWeb.capturaPantalla("pantalla principal", "se valida todos los menus cerrados");


        actor.attemptsTo(
                ScrollDown.by(300),
                ClickEnCarrusel.en(0),
                WaitFor.aTime(300),
                ClickEnImagenCarrusel.en(0, "https://api.whatsapp.com/send?phone=573112000000"),
                WaitFor.aTime(1000)
        );


       CapturasPantallasWeb.capturaPantalla("banner 1", "redirecciona");

        actor.attemptsTo(
                WaitFor.aTime(1000),
                CerrarPestañaYVolver.ahora(),
                WaitFor.aTime(1000),
                ClickEnCarrusel.en(1),
                WaitFor.aTime(300),
                ClickEnImagenCarrusel.en(1, "https://www.claro.com.co/5g/")

        );

        CapturasPantallasWeb.capturaPantalla("banner 2", "redirecciona");


        actor.attemptsTo(
                WaitFor.aTime(1000),
                CerrarPestañaYVolver.ahora(),
                WaitFor.aTime(1000),
                ClickEnCarrusel.en(2),
                WaitFor.aTime(300),
                ClickEnImagenCarrusel.en(2, "https://play.google.com/store/apps/details?id=com.clarocolombia.miclaro&hl=en_US%20https://apps.apple.com/co/app/mi-claro-colombia/id1046909245")

        );

        CapturasPantallasWeb.capturaPantalla("banner 3", "redirecciona");

        actor.attemptsTo(
                WaitFor.aTime(1000),
                CerrarPestañaYVolver.ahora());

    }

}
