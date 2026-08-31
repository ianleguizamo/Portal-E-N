package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.BOTON_CONTINUAR;
import static userinterfaces.CmaxPage.BOTON_PAGAR;
import static userinterfaces.CmaxPage.CHECKBOX_FILA;
import static userinterfaces.CmaxPage.METODO_GOOOGLE_PLAY;

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
import net.thucydides.core.annotations.Step;
import questions.EstadoDeFacturas;
import tasks.PortalEmpresas.navegacion.IrAPagoDeSoluciones;
import tasks.PortalEmpresas.pagos.RegistrarSinFacturas;
import tasks.PortalEmpresas.pagos.ValidarVentanaDePago;
import utils.EvidenciaUtils;

/** Pago de soluciones moviles con Google Play. Ver nota de navegacion en SolucionesMovilesPSE. */
public class SolucionesMovilesGooglePlay implements Task {

  private static final String SECCION = "soluciones moviles";
  private static final String PASO_METODO = "Selecciona metodo de pago Google Play";
  private static final String PASO_CONFIRMACION = "Validacion ventana confirmacion Google Play";

  private final Map<String, String> data;

  public SolucionesMovilesGooglePlay(Map<String, String> data) {
    this.data = data;
  }

  public static Performable solucionesMovilesGooglePlay(Map<String, String> data) {
    return Instrumented.instanceOf(SolucionesMovilesGooglePlay.class).withProperties(data);
  }

  @Override
  @Step("Validar pago de soluciones moviles con Google Play")
  public <T extends Actor> void performAs(T actor) {

    String ventanaPrincipal = BrowseTheWeb.as(actor).getDriver().getWindowHandle();

    actor.attemptsTo(IrAPagoDeSoluciones.moviles());

    if (actor.asksFor(EstadoDeFacturas.enLaPagina()).sinFacturasPendientes(SECCION)) {
      actor.attemptsTo(RegistrarSinFacturas.en(SECCION));
      return;
    }

    actor.attemptsTo(
        SmartClick.on(CHECKBOX_FILA),
        WaitFor.aTime(2000),
        WaitForResponse.withTarget(BOTON_PAGAR),
        SmartClick.on(BOTON_PAGAR));

    actor.attemptsTo(WaitFor.aTime(2000), SmartClick.on(METODO_GOOOGLE_PLAY), WaitFor.aTime(2000));

    EvidenciaUtils.registrarCaptura(PASO_METODO);

    actor.attemptsTo(
        JavaScriptSmartClick.on(BOTON_CONTINUAR),
        CambiarANuevaPestana.desde(ventanaPrincipal),
        ValidarVentanaDePago.con(PASO_CONFIRMACION, "Google Play"),
        CerrarPestañaYVolver.ahora(ventanaPrincipal));
  }
}
