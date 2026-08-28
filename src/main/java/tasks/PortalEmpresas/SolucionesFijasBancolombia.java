package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.BOTON_BANCOLOMBIA;
import static userinterfaces.CmaxPage.BOTON_CONTINUAR;
import static userinterfaces.CmaxPage.BOTON_PAGAR;
import static userinterfaces.CmaxPage.CHECKBOX_FILA;

import interactions.CambiarANuevaPestana;
import interactions.CerrarPestañaYVolver;
import interactions.JavaScriptSmartClick;
import interactions.SmartClick;
import interactions.WaitFor;
import interactions.WaitForResponse;
import java.util.Map;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.conditions.Check;
import net.thucydides.core.annotations.Step;
import questions.EstadoDeFacturas;
import tasks.PortalEmpresas.navegacion.IrAPagoDeSoluciones;
import tasks.PortalEmpresas.pagos.RegistrarSinFacturas;
import tasks.PortalEmpresas.pagos.ValidarVentanaDePago;
import utils.EvidenciaUtils;

/** Pago de soluciones fijas por Bancolombia. Ver nota de navegacion en SolucionesMovilesPSE. */
public class SolucionesFijasBancolombia implements Task {

  private static final String SECCION = "soluciones fijas";
  private static final String PASO_METODO = "Selecciona metodo de pago Bancolombia";
  private static final String PASO_CONFIRMACION =
      "Validacion ventana confirmacion Bancolombia soluciones fijas";

  private final Map<String, String> data;

  public SolucionesFijasBancolombia(Map<String, String> data) {
    this.data = data;
  }

  public static Performable solucionesFijasBancolombia(Map<String, String> data) {
    return Instrumented.instanceOf(SolucionesFijasBancolombia.class).withProperties(data);
  }

  @Override
  @Step("Validar pago de soluciones fijas por Bancolombia")
  public <T extends Actor> void performAs(T actor) {

    String ventanaPrincipal = BrowseTheWeb.as(actor).getDriver().getWindowHandle();

    actor.attemptsTo(IrAPagoDeSoluciones.fijas());

    if (actor.asksFor(EstadoDeFacturas.enLaPagina()).sinFacturasPendientes(SECCION)) {
      actor.attemptsTo(RegistrarSinFacturas.en(SECCION));
      return;
    }

    actor.attemptsTo(
        Check.whether(CHECKBOX_FILA.resolveFor(actor).isPresent())
            .andIfSo(
                SmartClick.on(CHECKBOX_FILA),
                WaitFor.aTime(2000),
                WaitForResponse.withTarget(BOTON_PAGAR),
                SmartClick.on(BOTON_PAGAR),
                WaitFor.aTime(2000),
                SmartClick.on(BOTON_BANCOLOMBIA))
            .otherwise(WaitFor.aTime(1000)));

    EvidenciaUtils.registrarCaptura(PASO_METODO);

    actor.attemptsTo(
        JavaScriptSmartClick.on(BOTON_CONTINUAR),
        CambiarANuevaPestana.desde(ventanaPrincipal),
        ValidarVentanaDePago.con(PASO_CONFIRMACION, "Bancolombia"),
        CerrarPestañaYVolver.ahora(ventanaPrincipal));
  }
}
