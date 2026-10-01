package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import questions.LimiteDeCambiosAlcanzado;
import tasks.PortalEmpresas.datos.RegistrarLimiteDeCambios;
import utils.CerrarEncuestaQualtrics;
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
                SmartClick.on(SOLUCIONES_MOVILES),
                WaitForResponse.withTarget(ACTUALIZACION_DATOS_MOVILES)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                SmartClick.on(ACTUALIZACION_DATOS_MOVILES),
                WaitForResponse.withTarget(CUENTA_MAESTRA)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                SmartClick.on(CUENTA_MAESTRA)
        );

        CerrarEncuestaQualtrics.enIframeSiAparece(actor);

        // La cuenta puede haber agotado su cupo de cambios del trimestre. En ese caso el
        // portal responde con un aviso en vez del formulario: se documenta y se termina
        // en verde, porque no es un fallo del portal ni de la prueba.
        if (actor.asksFor(LimiteDeCambiosAlcanzado.enLaPagina())) {
            actor.attemptsTo(RegistrarLimiteDeCambios.alcanzado());
            return;
        }

        EvidenciaUtils.registrarCaptura("Datos de la cuenta");

        actor.attemptsTo(
                SmartClick.on(DEPARTMENT_INPUT)
        );

        EvidenciaUtils.registrarCaptura("Cambio de departamento");
    }
}