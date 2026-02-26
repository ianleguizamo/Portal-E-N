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

public class DescargaTusFacturas implements Task {


    private static final Logger log = LoggerFactory.getLogger(DescargaTusFacturas.class);
    Map<String, String> data = new HashMap<>();

    public DescargaTusFacturas(Map<String, String> data) {
        this.data = data;
    }

    public static Performable descargaTusFacturas(Map<String, String> data) {
        return Instrumented.instanceOf(DescargaTusFacturas.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CONSULTA_TUS_FACTURAS),
                WaitForResponse.withTarget(DESCARGA_TU_FACTURA)
        );

        CapturasPantallasWeb.capturaPantalla("Consulta tus facturas", "Consulta tus facturas");

        actor.attemptsTo(
                SmartClick.on(DESCARGA_TU_FACTURA),

                SwitchToSurveyIframe.now(),
                WaitForResponse.withTarget(BOTON_CERRAR_ENCUESTA),
                Click.on(BOTON_CERRAR_ENCUESTA),
                SwitchToDefaultContent.now()

        );

        CapturasPantallasWeb.capturaPantalla("Tarjetas registradas", "Tarjetas registradas");

        actor.attemptsTo(

                Click.on(BOTON_VER_FACTURA)
        );

        CapturasPantallasWeb.capturaPantalla("Informacion de la factura", "Informacion de la factura");


    }
}