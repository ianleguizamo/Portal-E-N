package tasks.PortalEmpresas;

import interactions.SmartClick;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

import java.util.Map;

import static userinterfaces.CmaxPage.*;

public class CentroDeAyuda implements Task {

    private static final String paso1 = "Centro de ayuda";
    private static final String paso2 = "Autoregistro";
    private static final String paso3 = "Pagos en Linea";
    private static final String paso4 = "Consulta de facturas";
    private static final String paso5 = "Soluciones Moviles";
    private static final String paso6 = "Menu Lateral";
    private static final String paso7 = "Soluciones fijas HFC o FO";
    private static final String paso8 = "Solicitudes y Procesos";

    public static Performable centroDeAyuda(Map<String, String> data) {
        return Instrumented.instanceOf(CentroDeAyuda.class).withProperties(data);
    }

    @Override
    @Step("Navegar por todas las opciones del centro de ayuda")
    public <T extends Actor> void performAs(T actor) {
        seleccionarCentroDeAyuda(actor);
        seleccionarAutoregistro(actor);
        seleccionarPagosEnLinea(actor);
        seleccionarConsultaFacturas(actor);
        seleccionarSolucionesMoviles(actor);
        seleccionarMenuLateral(actor);
        seleccionarSolucionesFijas(actor);
        seleccionarSolicitudesProcesos(actor);
        actor.attemptsTo(SmartClick.on(ICONO_CHEVRON_LEFT));
    }

    @Step("Seleccionar Centro de Ayuda")
    private <T extends Actor> void seleccionarCentroDeAyuda(T actor) {
        actor.attemptsTo(SmartClick.on(CENTRO_DE_AYUDA));
        EvidenciaUtils.registrarCaptura(paso1);
    }

    @Step("Seleccionar Autoregistro")
    private <T extends Actor> void seleccionarAutoregistro(T actor) {
        actor.attemptsTo(SmartClick.on(AUTOREGISTRO));
        EvidenciaUtils.registrarCaptura(paso2);
    }

    @Step("Seleccionar Pagos en Linea")
    private <T extends Actor> void seleccionarPagosEnLinea(T actor) {
        actor.attemptsTo(SmartClick.on(PAGO_EN_LINEA));
        EvidenciaUtils.registrarCaptura(paso3);
    }

    @Step("Seleccionar Consulta de Facturas")
    private <T extends Actor> void seleccionarConsultaFacturas(T actor) {
        actor.attemptsTo(SmartClick.on(CONSULTA_FACTURA));
        EvidenciaUtils.registrarCaptura(paso4);
    }

    @Step("Seleccionar Soluciones Moviles")
    private <T extends Actor> void seleccionarSolucionesMoviles(T actor) {
        actor.attemptsTo(SmartClick.on(SOLUCION_MOVIL));
        EvidenciaUtils.registrarCaptura(paso5);
    }

    @Step("Seleccionar Menu Lateral")
    private <T extends Actor> void seleccionarMenuLateral(T actor) {
        actor.attemptsTo(SmartClick.on(MENU_LATERAL));
        EvidenciaUtils.registrarCaptura(paso6);
    }

    @Step("Seleccionar Soluciones Fijas HFC o FO")
    private <T extends Actor> void seleccionarSolucionesFijas(T actor) {
        actor.attemptsTo(SmartClick.on(SOLUCION_FIJA_HFC));
        EvidenciaUtils.registrarCaptura(paso7);
    }

    @Step("Seleccionar Solicitudes y Procesos")
    private <T extends Actor> void seleccionarSolicitudesProcesos(T actor) {
        actor.attemptsTo(SmartClick.on(SOLICITUD_PROCESO));
        EvidenciaUtils.registrarCaptura(paso8);
    }
}