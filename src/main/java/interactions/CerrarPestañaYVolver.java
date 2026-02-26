package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

public class CerrarPestañaYVolver implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Guardar la ventana principal
        String principal = driver.getWindowHandles().iterator().next();

        // Cerrar la ventana actual (la del banner)
        driver.close();

        // Volver a la principal
        driver.switchTo().window(principal);
    }

    public static Performable ahora() {
        return new CerrarPestañaYVolver();
    }
}

