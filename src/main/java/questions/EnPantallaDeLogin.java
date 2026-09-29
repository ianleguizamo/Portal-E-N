package questions;

import static userinterfaces.CmaxPage.TXT_USUARIO;

import java.time.temporal.ChronoUnit;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * Responde si el portal esta mostrando el formulario de login.
 *
 * <p>Al abrir /login con una sesion ya iniciada el portal no pinta el formulario: redirige al
 * inicio. Por eso "no hay formulario" equivale a "ya estamos dentro", y lo usan tanto
 * AbrirPagina (para limpiar una sesion heredada) como RealizarIngreso (para no reintentar un
 * login que en realidad ya entro).
 */
public class EnPantallaDeLogin implements Question<Boolean> {

  /** Margen para que el portal decida si pinta el login o redirige al inicio. */
  private static final int ESPERA_SEGUNDOS = 15;

  public static Question<Boolean> ahora() {
    return new EnPantallaDeLogin();
  }

  @Override
  public Boolean answeredBy(Actor actor) {
    try {
      return TXT_USUARIO
          .resolveFor(actor)
          .withTimeoutOf(ESPERA_SEGUNDOS, ChronoUnit.SECONDS)
          .isCurrentlyVisible();

    } catch (RuntimeException noHayFormulario) {
      return false;
    }
  }
}
