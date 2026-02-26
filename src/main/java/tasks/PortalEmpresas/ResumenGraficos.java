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

public class ResumenGraficos implements Task {


    private static final Logger log = LoggerFactory.getLogger(ResumenGraficos.class);
    Map<String, String> data = new HashMap<>();

    public ResumenGraficos(Map<String, String> data) {
        this.data = data;
    }

    public static Performable resumenGraficos(Map<String, String> data) {
        return Instrumented.instanceOf(ResumenGraficos.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CONSULTA_TUS_FACTURAS),
                WaitForResponse.withTarget(RESUMEN_GRAFICO_FACTURAS)
        );

        CapturasPantallasWeb.capturaPantalla("Consulta tus facturas", "Consulta tus facturas");

        actor.attemptsTo(
                SmartClick.on(RESUMEN_GRAFICO_FACTURAS),

                SwitchToSurveyIframe.now(),
                WaitForResponse.withTarget(BOTON_CERRAR_ENCUESTA),
                Click.on(BOTON_CERRAR_ENCUESTA),
                SwitchToDefaultContent.now()

        );

        CapturasPantallasWeb.capturaPantalla("graficos", "graficos");


    }
}