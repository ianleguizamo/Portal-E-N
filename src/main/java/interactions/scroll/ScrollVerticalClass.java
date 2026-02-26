package interactions.scroll;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;
import utils.AndroidObject;

public class ScrollVerticalClass extends AndroidObject implements Interaction {

  private String labelText;

  public ScrollVerticalClass(String labelText) {
    this.labelText = labelText;
  }

  @Override
  @Step(
      "Realiza scroll vertical al dirreccionar la tele letal hasta encontrar el texto de '#labelText'")
  public <T extends Actor> void performAs(T actor) {
    ScrollVerticalClass(actor, labelText);
  }

  public static Interaction scrollVerticalClass(String labelText) {
    return instrumented(ScrollVerticalClass.class, labelText);
  }
}
