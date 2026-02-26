package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SmartClick implements Task {

    private final Target target;

    public SmartClick(Target target) {
        this.target = target;
    }

    public static SmartClick on(Target target) {
        return new SmartClick(target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        String originalWindow = driver.getWindowHandle();
        int ventanasAntes = driver.getWindowHandles().size();

        // Click normal
        target.resolveFor(actor).click();

        // Pequeña pausa para detectar nueva pestaña
        try { Thread.sleep(1200); } catch (Exception ignored) {}

        int ventanasDespues = driver.getWindowHandles().size();

        // Si se abrió una nueva pestaña → cambiar a ella
        if (ventanasDespues > ventanasAntes) {
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            tabs.remove(originalWindow);
            driver.switchTo().window(tabs.get(0));
        }
    }
}
