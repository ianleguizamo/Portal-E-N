package tasks.PortalEmpresas;

import interactions.WaitFor;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

import java.util.Map;

import static userinterfaces.CmaxPage.*;

public class CentroDeAyuda implements Task {

    private static final Logger log = LoggerFactory.getLogger(CentroDeAyuda.class);

    private static final String paso1= "Centro de ayuda";
    private static final String paso2 = "Autoregistro";
    private static final String paso3 = "Pagos en Linea";
    private static final String paso4 = "Consulta de facturas";
    private static final String paso5 = "Soluciones Móviles";
    private static final String paso6 = "Menú Lateral";
    private static final String paso7 = "Soluciones fijas HFC o FO";
    private static final String paso8 = "Solicitudes y Procesos";

    public static Performable centroDeAyuda(Map<String, String> data) {
        return Instrumented.instanceOf(CentroDeAyuda.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CENTRO_DE_AYUDA)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                Click.on(AUTOREGISTRO)
        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                Click.on(PAGO_EN_LINEA)
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                Click.on(CONSULTA_FACTURA)
        );

        EvidenciaUtils.registrarCaptura(paso4);

        actor.attemptsTo(
                Click.on(SOLUCION_MOVIL)
        );

        EvidenciaUtils.registrarCaptura(paso5);

        actor.attemptsTo(
                Click.on(MENU_LATERAL)
        );

        EvidenciaUtils.registrarCaptura(paso6);

        actor.attemptsTo(
                Click.on(SOLUCION_FIJA_HFC)
        );

        EvidenciaUtils.registrarCaptura(paso7);

        actor.attemptsTo(
                Click.on(SOLICITUD_PROCESO)
        );

        EvidenciaUtils.registrarCaptura(paso8);

        actor.attemptsTo(
                Click.on(ICONO_CHEVRON_LEFT)
        );
    }
}