package interactions.scroll;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;
import utils.AndroidObject;

public class ScrollHorizontal extends AndroidObject implements Interaction {

  private String labelText;
  private String instance;

  public ScrollHorizontal(String instance, String labelText) {
    this.labelText = labelText;
    this.instance = instance;
  }

  @Override
  @Step(
      "Realiza scroll horizontal hasta encontrar el texto '#labelText' en la instancia #instance .")
  public <T extends Actor> void performAs(T actor) {
    ScrollElement(actor, instance, labelText);
  }

  public static Interaction scrollElement(String instance, String labelText) {
    return instrumented(ScrollHorizontal.class, instance, labelText);
  }
}
