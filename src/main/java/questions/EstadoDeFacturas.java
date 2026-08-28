package questions;

import static userinterfaces.CmaxPage.CHECKBOX_FILA;
import static userinterfaces.CmaxPage.TEXTO_FACTURAS_POR_PAGAR;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.EstadoGrillaFacturas;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lee en que estado quedo la grilla de "Pago de soluciones" al entrar.
 *
 * <p>La fuente principal es el contador que el portal pinta sobre la tabla ("Facturas por
 * pagar: N"), que es el mismo dato que lee una persona. Solo ese contador permite afirmar
 * que la cuenta esta al dia: es la diferencia entre "el portal dice 0" y "no se ve nada".
 *
 * <p>Si el contador no aparece se mira si hay filas seleccionables. Ese respaldo solo
 * sirve para confirmar que SI hay facturas —por ejemplo si soluciones fijas cambiara el
 * marcado del contador—; nunca para concluir que no las hay. Sin contador y sin filas la
 * respuesta es {@link EstadoGrillaFacturas#NO_DISPONIBLE}, que hace fallar el escenario.
 */
public class EstadoDeFacturas implements Question<EstadoGrillaFacturas> {

  private static final Logger LOG = LoggerFactory.getLogger(EstadoDeFacturas.class);

  private static final Pattern PRIMER_NUMERO = Pattern.compile("(\\d+)");

  /** Margen para que la grilla termine de pintarse tras entrar a la seccion. */
  private static final int ESPERA_CONTADOR_SEGUNDOS = 10;

  /** Corto a proposito: si no hubo contador, el respaldo no debe volver a esperar 10 s. */
  private static final int ESPERA_RESPALDO_SEGUNDOS = 2;

  public static Question<EstadoGrillaFacturas> enLaPagina() {
    return new EstadoDeFacturas();
  }

  @Override
  public EstadoGrillaFacturas answeredBy(Actor actor) {
    Optional<Integer> contador = leerContador(actor);

    if (contador.isPresent()) {
      LOG.info("El portal reporta {} facturas por pagar", contador.get());
      return contador.get() > 0
          ? EstadoGrillaFacturas.CON_FACTURAS
          : EstadoGrillaFacturas.SIN_FACTURAS;
    }

    if (hayFilaSeleccionable(actor)) {
      LOG.warn(
          "No se encontro el contador 'Facturas por pagar', pero si hay filas de factura. "
              + "Se continua el pago; conviene revisar si cambio el marcado de la pagina.");
      return EstadoGrillaFacturas.CON_FACTURAS;
    }

    LOG.error("Ni contador ni filas de factura: no se puede determinar el estado de la grilla");
    return EstadoGrillaFacturas.NO_DISPONIBLE;
  }

  private Optional<Integer> leerContador(Actor actor) {
    try {
      WebElementFacade texto =
          TEXTO_FACTURAS_POR_PAGAR
              .resolveFor(actor)
              .withTimeoutOf(ESPERA_CONTADOR_SEGUNDOS, ChronoUnit.SECONDS);

      if (!texto.isPresent()) {
        return Optional.empty();
      }

      Matcher numero = PRIMER_NUMERO.matcher(texto.getText());
      return numero.find() ? Optional.of(Integer.valueOf(numero.group(1))) : Optional.empty();

    } catch (RuntimeException contadorNoLegible) {
      LOG.debug("No se pudo leer el contador de facturas", contadorNoLegible);
      return Optional.empty();
    }
  }

  private boolean hayFilaSeleccionable(Actor actor) {
    try {
      return CHECKBOX_FILA
          .resolveFor(actor)
          .withTimeoutOf(ESPERA_RESPALDO_SEGUNDOS, ChronoUnit.SECONDS)
          .isPresent();

    } catch (RuntimeException sinFilas) {
      LOG.debug("No hay filas de factura seleccionables", sinFilas);
      return false;
    }
  }
}
