package tasks.PortalEmpresas.pagos;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

/**
 * Cierra el escenario cuando la cuenta no tiene facturas pendientes.
 *
 * <p>No es un fallo: la cuenta de pruebas puede estar al dia. Se deja la captura de la
 * grilla vacia (que va al informe Word) y una nota en el reporte de Serenity, y el
 * escenario termina en verde.
 */
public class RegistrarSinFacturas implements Task {

  private static final Logger LOG = LoggerFactory.getLogger(RegistrarSinFacturas.class);

  private static final String TITULO_NOTA = "Escenario finalizado sin ejecutar el pago";

  private final String seccion;

  public RegistrarSinFacturas(String seccion) {
    this.seccion = seccion;
  }

  /**
   * @param seccion como aparece en el portal: "soluciones moviles" o "soluciones fijas"
   */
  public static Performable en(String seccion) {
    return instrumented(RegistrarSinFacturas.class, seccion);
  }

  @Override
  @Step("No hay facturas pendientes: se cierra el escenario sin fallar")
  public <T extends Actor> void performAs(T actor) {
    String detalle =
        "La cuenta no tiene facturas pendientes en "
            + seccion
            + ", asi que no habia nada que pagar. "
            + "El flujo de pago no se ejecuto y el escenario termina sin fallar. "
            + "La captura adjunta muestra la grilla vacia tal como la devolvio el portal.";

    LOG.info(detalle);

    // Queda como bloque de datos en el reporte HTML de Serenity, junto al escenario.
    Serenity.recordReportData().withTitle(TITULO_NOTA).andContents(detalle);

    // registrarCaptura ademas anota el paso en ReportHooksWeb, que es lo que alimenta el
    // informe Word: sin esto la evidencia no aparece en el documento.
    EvidenciaUtils.registrarCaptura("Sin facturas pendientes en " + seccion);
  }
}
