package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.BOTON_CONTINUAR;
import static userinterfaces.CmaxPage.BOTON_PAGAR;
import static userinterfaces.CmaxPage.CHECKBOX_FILA;
import static userinterfaces.CmaxPage.METODO_TARJETA_CODENSA;

import interactions.CambiarANuevaPestana;
import interactions.CerrarPestañaYVolver;
import interactions.JavaScriptSmartClick;
import interactions.SmartClick;
import interactions.WaitFor;
import java.util.Map;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import questions.EstadoDeFacturas;
import tasks.PortalEmpresas.navegacion.IrAPagoDeSoluciones;
import tasks.PortalEmpresas.pagos.RegistrarSinFacturas;
import tasks.PortalEmpresas.pagos.ValidarVentanaDePago;
import utils.EvidenciaUtils;

/** Pago de soluciones fijas con Tarjeta Codensa. Ver nota de navegacion en SolucionesMovilesPSE. */
public class SolucionesFijasTarjetaCodensa implements Task {

  /** Tope para cada espera por elemento; el portal responde muy por debajo. */
  private static final int ESPERA_SEGUNDOS = 30;

  private static final String SECCION = "soluciones fijas";
  private static final String PASO_METODO = "Selecciona metodo de pago Tarjeta Codensa";
  private static final String PASO_CONFIRMACION =
      "Validacion ventana confirmacion Tarjeta Codensa soluciones fijas";

  private final Map<String, String> data;

  public SolucionesFijasTarjetaCodensa(Map<String, String> data) {
    this.data = data;
  }

  public static Performable solucionesFijasTarjetaCodensa(Map<String, String> data) {
    return Instrumented.instanceOf(SolucionesFijasTarjetaCodensa.class).withProperties(data);
  }

  @Override
  @Step("Validar pago de soluciones fijas con Tarjeta Codensa")
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
                WaitUntil.the(BOTON_PAGAR, isVisible()).forNoMoreThan(ESPERA_SEGUNDOS).seconds(),
                SmartClick.on(BOTON_PAGAR),
                WaitUntil.the(METODO_TARJETA_CODENSA, isVisible()).forNoMoreThan(ESPERA_SEGUNDOS).seconds(),
                SmartClick.on(METODO_TARJETA_CODENSA),
                WaitUntil.the(BOTON_CONTINUAR, isVisible()).forNoMoreThan(ESPERA_SEGUNDOS).seconds())
            .otherwise(WaitFor.aTime(1000)));
    EvidenciaUtils.registrarCaptura(PASO_METODO);

    actor.attemptsTo(
        JavaScriptSmartClick.on(BOTON_CONTINUAR),
        CambiarANuevaPestana.desde(ventanaPrincipal),
        ValidarVentanaDePago.con(PASO_CONFIRMACION, "Codensa"),
        CerrarPestañaYVolver.ahora(ventanaPrincipal));
  }
}
