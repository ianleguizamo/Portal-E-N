package questions;

import static userinterfaces.CmaxPage.AVISO_LIMITE_CAMBIOS;

import java.time.temporal.ChronoUnit;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * Responde si el portal esta mostrando el aviso "Superaste el limite de cambios
 * permitidos".
 *
 * <p>La cuenta de pruebas solo admite unos pocos cambios de datos por trimestre. Cuando se
 * agotan, el portal responde con ese modal en vez de con el formulario. No es un fallo del
 * portal ni de la prueba: es el estado real de la cuenta.
 */
public class LimiteDeCambiosAlcanzado implements Question<Boolean> {

  /** Corto: el modal sale a la vez que la pantalla, no despues. */
  private static final int ESPERA_SEGUNDOS = 3;

  public static Question<Boolean> enLaPagina() {
    return new LimiteDeCambiosAlcanzado();
  }

  @Override
  public Boolean answeredBy(Actor actor) {
    try {
      return AVISO_LIMITE_CAMBIOS
          .resolveFor(actor)
          .withTimeoutOf(ESPERA_SEGUNDOS, ChronoUnit.SECONDS)
          .isCurrentlyVisible();

    } catch (RuntimeException noEstaElAviso) {
      return false;
    }
  }
}
