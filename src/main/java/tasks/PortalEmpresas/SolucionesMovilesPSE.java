package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.BOTON_CONTINUAR;
import static userinterfaces.CmaxPage.BOTON_PAGAR;
import static userinterfaces.CmaxPage.CHECKBOX_CUSTOM;
import static userinterfaces.CmaxPage.METODO_PSE;

import interactions.CambiarANuevaPestana;
import interactions.CerrarPestañaYVolver;
import interactions.SmartClick;
import interactions.WaitFor;
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
import utils.EvidenciaUtils;

/**
 * Pago de soluciones moviles por PSE.
 *
 * <p>Arranca ya dentro de "Pagos en linea": ese paso lo aporta el Antecedentes del feature
 * (ver IrAPagosEnLinea), no esta Task.
 */
public class SolucionesMovilesPSE implements Task {

  private static final String SECCION = "soluciones moviles";
  private static final String PASO_METODO = "Selecciona metodo de pago PSE";

  private final Map<String, String> data;

  public SolucionesMovilesPSE(Map<String, String> data) {
    this.data = data;
  }

  public static Performable solucionesMovilesPSE(Map<String, String> data) {
    return Instrumented.instanceOf(SolucionesMovilesPSE.class).withProperties(data);
  }

  @Override
  @Step("Validar pago de soluciones moviles por PSE")
  public <T extends Actor> void performAs(T actor) {

    String ventanaPrincipal = BrowseTheWeb.as(actor).getDriver().getWindowHandle();

    actor.attemptsTo(IrAPagoDeSoluciones.moviles());

    if (actor.asksFor(EstadoDeFacturas.enLaPagina()).sinFacturasPendientes(SECCION)) {
      actor.attemptsTo(RegistrarSinFacturas.en(SECCION));
      return;
    }

    actor.attemptsTo(
        Click.on(CHECKBOX_CUSTOM),
        WaitFor.aTime(2000),
        WaitForResponse.withTarget(BOTON_PAGAR),
        Click.on(BOTON_PAGAR),
        WaitFor.aTime(2000),
        WaitForResponse.withTarget(METODO_PSE),
        Click.on(METODO_PSE),
        WaitFor.aTime(2000));

    EvidenciaUtils.registrarCaptura(PASO_METODO);

    actor.attemptsTo(
        SmartClick.on(BOTON_CONTINUAR),
        CambiarANuevaPestana.desde(ventanaPrincipal),
        CerrarPestañaYVolver.ahora(ventanaPrincipal));
  }
}
