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

public class SolicitudesDomicilio implements Task {


    private static final Logger log = LoggerFactory.getLogger(SolicitudesDomicilio.class);
    Map<String, String> data = new HashMap<>();

    public SolicitudesDomicilio(Map<String, String> data) {
        this.data = data;
    }

    public static Performable solicitudesDomicilio(Map<String, String> data) {
        return Instrumented.instanceOf(SolicitudesDomicilio.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                ScrollToTarget.to(CONSULTAR_SOLICITUDES_DOMICILIO)
        );

        CapturasPantallasWeb.capturaPantalla("Soluciones moviles", "Soluciones moviles");

        actor.attemptsTo(
                Click.on(CONSULTAR_SOLICITUDES_DOMICILIO),
                WaitForResponse.withTarget(HEADER_TIPO_SOLICITUD)
        );

        CapturasPantallasWeb.capturaPantalla("Solicitudes en proceso", "Solicitudes en proceso");

    }
}