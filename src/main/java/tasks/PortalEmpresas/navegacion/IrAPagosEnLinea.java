package tasks.PortalEmpresas.navegacion;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.PAGO_SOLUCIONES_MOVILES;
import static userinterfaces.CmaxPage.PAGOS_EN_LINEA;

import interactions.SmartClick;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

/**
 * Entrada a la seccion "Pagos en linea" del portal.
 *
 * <p>Es el punto de partida comun de todos los escenarios de pago, tarjetas registradas
 * y pago de otras cuentas, asi que vive aqui una sola vez en lugar de repetirse al
 * comienzo de cada Task de pago.
 */
public class IrAPagosEnLinea implements Task {

  private static final String PASO = "Selecciona Pagos en linea";

  public static Performable ahora() {
    return instrumented(IrAPagosEnLinea.class);
  }

  @Override
  @Step("Ingresar a Pagos en linea")
  public <T extends Actor> void performAs(T actor) {
    // No basta con document.readyState: el portal lo completa y DESPUES pinta el
    // contenido, asi que la captura salia con el spinner "Espera un momento". Se espera
    // a que el destino sea visible, que ademas es mas rapido que un reloj fijo.
    actor.attemptsTo(
        SmartClick.on(PAGOS_EN_LINEA),
        WaitUntil.the(PAGO_SOLUCIONES_MOVILES, isVisible()).forNoMoreThan(30).seconds());

    EvidenciaUtils.registrarCaptura(PASO);
  }
}
