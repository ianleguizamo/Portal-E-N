package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.BOTON_BANCOLOMBIA;
import static userinterfaces.CmaxPage.BOTON_CONTINUAR;
import static userinterfaces.CmaxPage.BOTON_PAGAR;
import static userinterfaces.CmaxPage.CHECKBOX_FILA;

import interactions.CambiarANuevaPestana;
import interactions.CerrarPestañaYVolver;
import interactions.SmartClick;
import interactions.WaitForResponse;
import java.util.Map;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import questions.EstadoDeFacturas;
import tasks.PortalEmpresas.navegacion.IrAPagoDeSoluciones;
import tasks.PortalEmpresas.pagos.RegistrarSinFacturas;
import tasks.PortalEmpresas.pagos.ValidarVentanaDePago;
import utils.EvidenciaUtils;

/** Pago de soluciones moviles por Bancolombia. Ver nota de navegacion en SolucionesMovilesPSE. */
public class SolucionesMovilesBancolombia implements Task {

  private static final String PASO_PAGAR = "Selecciona Boton Pagar";
  private static final String SECCION = "soluciones moviles";
  private static final String PASO_METODO = "Selecciona metodo de pago Bancolombia";
  private static final String PASO_CONFIRMACION = "Validacion ventana confirmacion pago";

  private final Map<String, String> data;

  public SolucionesMovilesBancolombia(Map<String, String> data) {
    this.data = data;
  }

  public static Performable solucionesMovilesBancolombia(Map<String, String> data) {
    return Instrumented.instanceOf(SolucionesMovilesBancolombia.class).withProperties(data);
  }

  @Override
  @Step("Validar pago de soluciones moviles por Bancolombia")
  public <T extends Actor> void performAs(T actor) {

    String ventanaPrincipal = BrowseTheWeb.as(actor).getDriver().getWindowHandle();

    actor.attemptsTo(IrAPagoDeSoluciones.moviles());

    if (actor.asksFor(EstadoDeFacturas.enLaPagina()).sinFacturasPendientes(SECCION)) {
      actor.attemptsTo(RegistrarSinFacturas.en(SECCION));
      return;
    }

    actor.attemptsTo(
        Click.on(CHECKBOX_FILA),
        WaitForResponse.withTarget(BOTON_PAGAR),
        Click.on(BOTON_PAGAR));

    EvidenciaUtils.registrarCaptura(PASO_PAGAR);

    actor.attemptsTo(
        WaitForResponse.withTarget(BOTON_BANCOLOMBIA),
        Click.on(BOTON_BANCOLOMBIA));

    EvidenciaUtils.registrarCaptura(PASO_METODO);

    actor.attemptsTo(
        SmartClick.on(BOTON_CONTINUAR),
        CambiarANuevaPestana.desde(ventanaPrincipal),
        ValidarVentanaDePago.con(PASO_CONFIRMACION),
        CerrarPestañaYVolver.ahora(ventanaPrincipal));
  }
}
