package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwitchToIframe implements Interaction {

    private final Target iframe;

    public SwitchToIframe(Target iframe) {
        this.iframe = iframe;
    }

    public static SwitchToIframe with(Target iframe) {
        return new SwitchToIframe(iframe);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement iframeElement = iframe.resolveFor(actor);
        driver.switchTo().frame(iframeElement);
    }
}
