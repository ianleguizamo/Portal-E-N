package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.core.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;

public class WaitFor implements Interaction {

  private final int var;

  protected WaitFor(int var) {
    this.var = var;
  }

  @Override
  @Step("Espera de {0} milisegundos")
  public <T extends Actor> void performAs(T actor) {
    new InternalSystemClock().pauseFor(var);
  }

  /** Aparece en el reporte de Serenity */
  public static WaitFor aTime(int var) {
    return instrumented(WaitFor.class, var);
  }

  /** NO aparece en el reporte de Serenity */
  public static void silencioso(int var) {
    new InternalSystemClock().pauseFor(var);
  }
}