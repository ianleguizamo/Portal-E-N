package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;

public class EnterPasswordSecure implements Interaction {

    private final Target target;
    private final String password;

    public EnterPasswordSecure(Target target, String password) {
        this.target = target;
        this.password = password;
    }

    public static EnterPasswordSecure into(Target target, String password) {
        return instrumented(EnterPasswordSecure.class, target, password);
    }

    @Override
    @Step("Ingresa contraseña: ****")
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade element = target.resolveFor(actor);
        element.clear();
        element.sendKeys(password);

        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element
        );
    }
}