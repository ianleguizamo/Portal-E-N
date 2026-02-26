package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class ScrollToTarget implements Interaction {

    private final Target target;

    public ScrollToTarget(Target target) {
        this.target = target;
    }

    public static ScrollToTarget to(Target target) {
        return Tasks.instrumented(ScrollToTarget.class, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = target.resolveFor(actor);
        new net.serenitybdd.screenplay.actions.ScrollToWebElement(element).performAs(actor);
    }
}
