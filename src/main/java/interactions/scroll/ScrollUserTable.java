package interactions.scroll;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollUserTable implements Task {

    private final int pixels;

    public ScrollUserTable(int pixels) {
        this.pixels = pixels;
    }

    public static Performable by(int pixels) {
        return new ScrollUserTable(pixels);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        JavascriptExecutor js =
                (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        // Scroll del navegador, no de un contenedor
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }
}
