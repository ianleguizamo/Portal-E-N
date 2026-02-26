package interactions.scroll;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollMenuLateral implements Task {

    public enum ScrollType {
        BY_PIXELS,
        TO_TOP,
        TO_BOTTOM
    }

    private final ScrollType type;
    private final Integer pixels;

    private ScrollMenuLateral(ScrollType type, Integer pixels) {
        this.type = type;
        this.pixels = pixels;
    }

    public static Performable by(int pixels) {
        return new ScrollMenuLateral(ScrollType.BY_PIXELS, pixels);
    }

    public static Performable toTop() {
        return new ScrollMenuLateral(ScrollType.TO_TOP, null);
    }

    public static Performable toBottom() {
        return new ScrollMenuLateral(ScrollType.TO_BOTTOM, null);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        switch (type) {

            case BY_PIXELS:
                js.executeScript(
                        "document.querySelector('nav.quick-links')" +
                                ".scrollBy(0, arguments[0]);",
                        pixels
                );
                break;

            case TO_TOP:
                js.executeScript(
                        "let menu = document.querySelector('nav.quick-links');" +
                                "if(menu) menu.scrollTo({ top: 0, behavior: 'smooth' });"
                );
                break;

            case TO_BOTTOM:
                js.executeScript(
                        "let menu = document.querySelector('nav.quick-links');" +
                                "if(menu) menu.scrollTo({ top: menu.scrollHeight, behavior: 'smooth' });"
                );
                break;
        }
    }
}
