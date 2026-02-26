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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CapturasPantallasWeb;

import java.util.HashMap;
import java.util.Map;

public class SolucionesFijasTarjetas implements Task {


    private static final Logger log = LoggerFactory.getLogger(SolucionesFijasTarjetas.class);
    Map<String, String> data = new HashMap<>();

    public SolucionesFijasTarjetas(Map<String, String> data) {
        this.data = data;
    }

    public static Performable solucionesFijasTarjetas(Map<String, String> data) {
        return Instrumented.instanceOf(SolucionesFijasTarjetas.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(PAGOS_EN_LINEA)
        );

        CapturasPantallasWeb.capturaPantalla("Pagos en linea", "Pagos en linea");

        actor.attemptsTo(
                Click.on(PAGO_SOLUCIONES_FIJAS_HFC)
        );

        CapturasPantallasWeb.capturaPantalla("Soluciones moviles", "Soluciones moviles");

        actor.attemptsTo(
                Check.whether(CHECKBOX_FILA.resolveFor(actor).isPresent())
                        .andIfSo(
                                Click.on(CHECKBOX_FILA),
                                WaitForResponse.withTarget(BOTON_PAGAR),
                                Click.on(BOTON_PAGAR),
                                WaitForResponse.withTarget(METODO_TARJETA),
                                Click.on(METODO_TARJETA)
                        )
                        .otherwise(
                                WaitFor.aTime(100)
                        )
        );

        CapturasPantallasWeb.capturaPantalla(
                "Tarjetas",
                "Tarjetas"
        );

        actor.attemptsTo(
                SmartClick.on(BOTON_CONTINUAR),
                WaitFor.aTime(400)
        );

        CapturasPantallasWeb.capturaPantalla(
                "Redireccionamiento Tarjetas",
                "Redireccionamiento tarjetas"
        );

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora()
        );

    }
}