package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptSmartClick implements Task {

    private final Target target;
    private final String descripcion;

    public JavaScriptSmartClick(Target target, String descripcion) {
        this.target = target;
        this.descripcion = descripcion;
    }

    public static JavaScriptSmartClick on(Target target) {
        return instrumented(JavaScriptSmartClick.class, target, target.getName());
    }

    @Override
    @Step("Clic (JS) en #descripcion")
    public <T extends Actor> void performAs(T actor) {
        WebElement elemento = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("arguments[0].click();", elemento);
    }
}