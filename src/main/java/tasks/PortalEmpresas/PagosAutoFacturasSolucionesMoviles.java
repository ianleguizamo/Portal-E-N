package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;
import static utils.Constants.PAGOS_AUTOMATICOS;

import interactions.*;

import questions.ValidarTexto;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Scroll;
import net.thucydides.core.annotations.Step;
import utils.CerrarEncuestaQualtrics;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class PagosAutoFacturasSolucionesMoviles implements Task {

    Map<String, String> data = new HashMap<>();

    private static final String paso1 = "Valida seccion pagos en linea";
    private static final String paso2 = "Valida seccion Pagos automaticos";
    private static final String paso3 = "Ingreso exitoso a Pagos automaticos";
    private static final String paso4 = "Selecciona Soluciones Moviles en Pagos automaticos";

    public PagosAutoFacturasSolucionesMoviles(Map<String, String> data) {
        this.data = data;
    }

    public static Performable pagosAutoFacturas(Map<String, String> data) {
        return Instrumented.instanceOf(PagosAutoFacturasSolucionesMoviles.class)
                .withProperties(data);
    }

    @Override
    @Step("Validar pago automatico de facturas")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitFor.aTime(2000),
                SmartClick.on(PAGOS_EN_LINEA),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                SmartClick.on(PAGO_AUTOMATICO_FACTURAS)
        );
        WaitFor.silencioso(2000);
        EvidenciaUtils.registrarCaptura(paso2);

        CerrarEncuestaQualtrics.siAparece(actor);

        ValidarTexto.contiene(actor, PAGOS_AUTOMATICOS, paso3);

        actor.attemptsTo(
                WaitFor.aTime(1000),
                SmartClick.on(SOLUCIONES_MOVILES2),
                Scroll.to(DESCARGAR_ARCHIVO)
        );
        EvidenciaUtils.registrarCaptura(paso4);

    }
}