package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickJS implements Performable {

    private final Target target;

    public ClickJS(Target target) {
        this.target = target;
    }

    public static ClickJS on(Target target) {
        return new ClickJS(target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement element = target.resolveFor(actor);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}