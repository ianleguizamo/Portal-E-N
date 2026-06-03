package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import org.openqa.selenium.WebDriver;

public class CerrarPestañaYVolver implements Task {

    private final String ventanaPrincipal;

    public CerrarPestañaYVolver(String ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Cerrar pestaña secundaria
        driver.close();

        // Volver a la principal
        driver.switchTo().window(ventanaPrincipal);
    }

    public static Performable ahora(String ventanaPrincipal) {
        return new CerrarPestañaYVolver(ventanaPrincipal);
    }
}