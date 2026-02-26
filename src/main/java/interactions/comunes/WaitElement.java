package interactions.comunes;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.targets.Target;

public class WaitElement {

  private WaitElement() {}

  public static Clickable isClickable(Target element) {
    return instrumented(Clickable.class, element);
  }

  public static Visible isVisible(Target element) {
    return instrumented(Visible.class, element);
  }

  public static Present isPresente(Target element) {
    return instrumented(Present.class, element);
  }

  public static Enable isEnable(Target element) {
    return instrumented(Enable.class, element);
  }
}
