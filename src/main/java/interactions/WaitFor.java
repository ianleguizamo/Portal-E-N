package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.core.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;

/**
 * Espera de reloj.
 *
 * <p>Preferir siempre {@link WaitForResponse}, que espera por una condicion real de la
 * pagina. Esta clase solo tiene sentido cuando no hay nada concreto que esperar.
 */
public class WaitFor implements Interaction {

  private final int milisegundos;

  protected WaitFor(int milisegundos) {
    this.milisegundos = milisegundos;
  }

  @Override
  // Antes decia "Espera de {0} milisegundos": en Serenity {0} es el primer argumento del
  // metodo, que aqui es el actor, asi que el informe mostraba "Espera de Usuario
  // milisegundos". Con #milisegundos se referencia el campo y sale el numero real.
  @Step("Espera fija de #milisegundos ms")
  public <T extends Actor> void performAs(T actor) {
    new InternalSystemClock().pauseFor(milisegundos);
  }

  /** Aparece en el reporte de Serenity. */
  public static WaitFor aTime(int milisegundos) {
    return instrumented(WaitFor.class, milisegundos);
  }

  /** NO aparece en el reporte de Serenity. */
  public static void silencioso(int milisegundos) {
    new InternalSystemClock().pauseFor(milisegundos);
  }
}
