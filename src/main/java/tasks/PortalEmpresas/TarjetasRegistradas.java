package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CapturasPantallasWeb;

import java.util.HashMap;
import java.util.Map;

public class TarjetasRegistradas implements Task {


    private static final Logger log = LoggerFactory.getLogger(SolucionesFijasTarjetas.class);
    Map<String, String> data = new HashMap<>();

    public TarjetasRegistradas(Map<String, String> data) {
        this.data = data;
    }

    public static Performable tarjetasRegistradas(Map<String, String> data) {
        return Instrumented.instanceOf(TarjetasRegistradas.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(PAGOS_EN_LINEA)
        );

        CapturasPantallasWeb.capturaPantalla("Pagos en linea", "Pagos en linea");

        actor.attemptsTo(
                Click.on(TARJETAS_REGISTRADAS),

                SwitchToSurveyIframe.now(),
                WaitForResponse.withTarget(BOTON_CERRAR_ENCUESTA),
                Click.on(BOTON_CERRAR_ENCUESTA),
                SwitchToDefaultContent.now()


        );

        CapturasPantallasWeb.capturaPantalla("Tarjetas registradas", "Tarjetas registradas");

        actor.attemptsTo(
                WaitFor.aTime(300),
                Click.on(BOTON_SUMAR_TARJETA),
                WaitForResponse.withTarget(LABEL_NRO_DOCUMENTO_TITULAR)
                       );

        CapturasPantallasWeb.capturaPantalla(
                "Tarjetas",
                "Tarjetas"
        );


    }
}