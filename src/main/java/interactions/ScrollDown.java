package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollDown implements Task {

    private final int pixels;

    public ScrollDown(int pixels) {
        this.pixels = pixels;
    }

    public static Performable by(int pixels) {
        return new ScrollDown(pixels);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        js.executeScript(
                "document.querySelector('.main-scroll').scrollBy(0, arguments[0]);",
                pixels
        );
    }
}
