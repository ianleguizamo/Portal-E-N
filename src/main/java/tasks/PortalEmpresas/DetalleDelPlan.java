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
import utils.CerrarEncuestaQualtrics;
import utils.EvidenciaUtils;

public class DetalleDelPlan implements Task {


    private static final Logger log = LoggerFactory.getLogger(DetalleDelPlan.class);
    Map<String, String> data = new HashMap<>();

    public DetalleDelPlan(Map<String, String> data) {
        this.data = data;
    }

    public static Performable detalleDelPlan(Map<String, String> data) {
        return Instrumented.instanceOf(DetalleDelPlan.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                SmartClick.on(SOLUCIONES_MOVILES),
                ScrollToTarget.to(DETALLE_DE_TU_PLAN)
        );

        EvidenciaUtils.registrarCaptura("Soluciones moviles");

        actor.attemptsTo(
                SmartClick.on(DETALLE_DE_TU_PLAN),
                WaitForResponse.withTarget(ACCOUNT_ITEM)
        );

        EvidenciaUtils.registrarCaptura("elegir cuenta");

        actor.attemptsTo(
                SmartClick.on(ACCOUNT_ITEM),
                WaitForResponse.withTarget(LINE_ITEM_RADIO)
        );

        EvidenciaUtils.registrarCaptura("Elegir numero");

        // Continuar por JavaScript: en esta pantalla el boton esta en el DOM y activo, pero el
        // portal no lo pinta (no sale en la captura) y Selenium lo rechaza con "element not
        // interactable". El clic por JS dispara igual su manejador.
        actor.attemptsTo(
                SmartClick.on(LINE_ITEM_RADIO),
                JavaScriptSmartClick.on(BTN_CONTINUAR)
        );

        CerrarEncuestaQualtrics.enIframeSiAparece(actor);

        // Antes esperaba CUENTA_MAESTRA_CONSUMOS, que es de la pagina de Consultar consumos y
        // aqui no existe: el tag no podia pasar aunque el detalle cargara bien.
        actor.attemptsTo(
                WaitForResponse.withTarget(PLAN_ASIGNADO)
        );

        EvidenciaUtils.registrarCaptura("detalle del plan");
    }
}