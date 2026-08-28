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

import java.util.HashMap;
import java.util.Map;
import utils.EvidenciaUtils;

public class ActualizacionDeDatos implements Task {


    private static final Logger log = LoggerFactory.getLogger(ActualizacionDeDatos.class);
    Map<String, String> data = new HashMap<>();

    public ActualizacionDeDatos(Map<String, String> data) {
        this.data = data;
    }

    public static Performable actualizacionDeDatos(Map<String, String> data) {
        return Instrumented.instanceOf(ActualizacionDeDatos.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(SOLUCIONES_MOVILES),
                WaitForResponse.withTarget(ACTUALIZACION_DATOS_MOVILES)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                Click.on(ACTUALIZACION_DATOS_MOVILES),
                WaitForResponse.withTarget(CUENTA_MAESTRA)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                Click.on(CUENTA_MAESTRA),


                SwitchToSurveyIframe.now(),
                WaitForResponse.withTarget(BOTON_CERRAR_ENCUESTA),
                Click.on(BOTON_CERRAR_ENCUESTA),
                SwitchToDefaultContent.now()


        );

        EvidenciaUtils.registrarCaptura("Datos de la cuenta");

        actor.attemptsTo(
                Click.on(DEPARTMENT_INPUT)
        );

        EvidenciaUtils.registrarCaptura("Cambio de departamento");
    }
}