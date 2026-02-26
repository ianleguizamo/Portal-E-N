package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SwitchToSurveyIframe implements Interaction {

    public static SwitchToSurveyIframe now() {
        return new SwitchToSurveyIframe();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        for (WebElement iframe : iframes) {
            driver.switchTo().frame(iframe);
            if (!driver.findElements(By.xpath("//img[contains(@src,'close')]")).isEmpty()) {
                return; // iframe correcto
            }
            driver.switchTo().defaultContent();
        }
    }
}
