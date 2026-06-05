package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SmartClick implements Task {

    private final Target target;
    private final String descripcion;

    public SmartClick(Target target, String descripcion) {
        this.target = target;
        this.descripcion = descripcion;
    }

    // Con descripcion personalizada
    public static SmartClick on(Target target, String descripcion) {
        return instrumented(SmartClick.class, target, descripcion);
    }

    // Sin descripcion — usa el nombre del Target automaticamente
    public static SmartClick on(Target target) {
        return instrumented(SmartClick.class, target, target.getName());
    }

    @Override
    @Step("Clic en #descripcion")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        String originalWindow = driver.getWindowHandle();
        int ventanasAntes = driver.getWindowHandles().size();

        target.resolveFor(actor).click();

        try { Thread.sleep(1200); } catch (Exception ignored) {}

        int ventanasDespues = driver.getWindowHandles().size();

        if (ventanasDespues > ventanasAntes) {
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            tabs.remove(originalWindow);
            driver.switchTo().window(tabs.get(0));
        }
    }
}