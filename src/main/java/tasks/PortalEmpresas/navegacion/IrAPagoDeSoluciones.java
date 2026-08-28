package tasks.PortalEmpresas.navegacion;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.PAGO_SOLUCIONES_FIJAS_HFC;
import static userinterfaces.CmaxPage.PAGO_SOLUCIONES_MOVILES;
import static userinterfaces.CmaxPage.TEXTO_FACTURAS_POR_PAGAR;

import interactions.SmartClick;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

/**
 * Segundo salto del flujo de pago: desde "Pagos en linea" hacia la grilla de facturas de
 * soluciones moviles o de soluciones fijas.
 *
 * <p>No se expone como paso de Gherkin a proposito: es navegacion interna del flujo de
 * pago, no lenguaje de negocio. Se reutiliza desde las Tasks de pago.
 */
public class IrAPagoDeSoluciones implements Task {

  private final Target opcion;
  private final String paso;

  public IrAPagoDeSoluciones(Target opcion, String paso) {
    this.opcion = opcion;
    this.paso = paso;
  }

  public static Performable moviles() {
    return instrumented(
        IrAPagoDeSoluciones.class, PAGO_SOLUCIONES_MOVILES, "Selecciona Pago de soluciones moviles");
  }

  public static Performable fijas() {
    return instrumented(
        IrAPagoDeSoluciones.class, PAGO_SOLUCIONES_FIJAS_HFC, "Selecciona Pago de soluciones fijas");
  }

  @Override
  @Step("#paso")
  public <T extends Actor> void performAs(T actor) {
    // Se espera al contador de la grilla, que es justo lo que lee EstadoDeFacturas a
    // continuacion: si es visible, la pagina termino de pintarse y la captura sirve.
    actor.attemptsTo(
        SmartClick.on(opcion),
        WaitUntil.the(TEXTO_FACTURAS_POR_PAGAR, isVisible()).forNoMoreThan(30).seconds());

    EvidenciaUtils.registrarCaptura(paso);
  }
}
