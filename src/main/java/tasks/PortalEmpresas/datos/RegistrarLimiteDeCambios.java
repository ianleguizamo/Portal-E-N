package tasks.PortalEmpresas.datos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.CmaxPage.BOTON_ACEPTAR_AVISO;

import interactions.SmartClick;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

/**
 * Cierra el escenario cuando la cuenta agoto los cambios de datos del trimestre.
 *
 * <p>No es un fallo: el portal esta respondiendo correctamente a una cuenta que ya uso su
 * cupo. Se deja la captura del aviso (que va al informe Word) y una nota en el reporte de
 * Serenity, y el escenario termina en verde.
 *
 * <p>Mismo criterio que RegistrarSinFacturas: un limite de negocio alcanzado se documenta,
 * no se convierte en rojo.
 */
public class RegistrarLimiteDeCambios implements Task {

  private static final Logger LOG = LoggerFactory.getLogger(RegistrarLimiteDeCambios.class);

  private static final String TITULO_NOTA = "Escenario finalizado sin actualizar datos";

  private static final String PASO = "Aviso de limite de cambios del trimestre";

  public static Performable alcanzado() {
    return instrumented(RegistrarLimiteDeCambios.class);
  }

  @Override
  @Step("Limite de cambios alcanzado: se cierra el escenario sin fallar")
  public <T extends Actor> void performAs(T actor) {
    String detalle =
        "El portal mostro el aviso 'Superaste el limite de cambios permitidos': la cuenta "
            + "ya agoto los cambios de datos que admite este trimestre, asi que el formulario "
            + "de actualizacion no llega a mostrarse. El portal respondio como debia y el "
            + "escenario termina sin fallar. La captura adjunta muestra el aviso tal cual.";

    LOG.info(detalle);

    Serenity.recordReportData().withTitle(TITULO_NOTA).andContents(detalle);

    EvidenciaUtils.registrarCaptura(PASO);

    // Se cierra el aviso para no dejar el navegador con un modal encima si despues se
    // reutiliza en la misma ejecucion.
    cerrarAviso(actor);
  }

  private <T extends Actor> void cerrarAviso(T actor) {
    try {
      actor.attemptsTo(SmartClick.on(BOTON_ACEPTAR_AVISO));
    } catch (RuntimeException noSePudoCerrar) {
      LOG.debug("No se pudo cerrar el aviso, se continua", noSePudoCerrar);
    }
  }
}
