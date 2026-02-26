package interactions.comunes;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.WebDriver;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;

import java.util.Set;

public class CerrarNuevaPestana implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = ThucydidesWebDriverSupport.getDriver();
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        // Buscar la nueva pestaña (la que no es la original)
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                driver.close();            // Cerrar la nueva pestaña
                break;
            }
        }

        // Regresar a la pestaña original
        driver.switchTo().window(originalWindow);
    }

    public static CerrarNuevaPestana ahora() {
        return Tasks.instrumented(CerrarNuevaPestana.class);
    }
}

