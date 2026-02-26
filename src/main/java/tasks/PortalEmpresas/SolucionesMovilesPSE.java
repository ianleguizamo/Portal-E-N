package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CapturasPantallasWeb;

import java.util.HashMap;
import java.util.Map;

public class SolucionesMovilesPSE implements Task {


    private static final Logger log = LoggerFactory.getLogger(SolucionesMovilesPSE.class);
    Map<String, String> data = new HashMap<>();

    public SolucionesMovilesPSE(Map<String, String> data) {
        this.data = data;
    }

    public static Performable solucionesMovilesPSE(Map<String, String> data) {
        return Instrumented.instanceOf(SolucionesMovilesPSE.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(PAGOS_EN_LINEA)
        );

        CapturasPantallasWeb.capturaPantalla("Pagos en linea", "Pagos en linea");

        actor.attemptsTo(
                Click.on(PAGO_SOLUCIONES_MOVILES)
        );

        CapturasPantallasWeb.capturaPantalla("Soluciones moviles", "Soluciones moviles");

        actor.attemptsTo(
                Click.on(CHECKBOX_CUSTOM),
                WaitForResponse.withTarget(BOTON_PAGAR),
                Click.on(BOTON_PAGAR),
                WaitForResponse.withTarget(METODO_PSE),
                Click.on(METODO_PSE)
        );

        CapturasPantallasWeb.capturaPantalla("PSE","PSE");

        actor.attemptsTo(
                SmartClick.on(BOTON_CONTINUAR),
                WaitFor.aTime(400)
        );

        CapturasPantallasWeb.capturaPantalla("Redireccionamiento PSE","Redireccionamiento PSE");

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora()
        );



    }
}