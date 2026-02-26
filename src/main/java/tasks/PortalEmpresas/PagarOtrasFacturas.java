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

public class PagarOtrasFacturas implements Task {


    private static final Logger log = LoggerFactory.getLogger(PagarOtrasFacturas.class);
    Map<String, String> data = new HashMap<>();

    public PagarOtrasFacturas(Map<String, String> data) {
        this.data = data;
    }

    public static Performable pagarOtrasFacturas(Map<String, String> data) {
        return Instrumented.instanceOf(PagarOtrasFacturas.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(PAGOS_EN_LINEA)
        );

        CapturasPantallasWeb.capturaPantalla("Pagos en linea", "Pagos en linea");

        actor.attemptsTo(
                SmartClick.on(PAGAR_OTRAS_CUENTAS),
                WaitFor.aTime(600)
        );

        CapturasPantallasWeb.capturaPantalla("Otras cuentas", "Otras cuentas");


    }
}