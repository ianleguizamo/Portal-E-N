package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickEnCarrusel implements Task {

    private final int indice;

    public ClickEnCarrusel(int indice) {
        this.indice = indice;
    }

    public static Performable en(int indice) {
        return new ClickEnCarrusel(indice);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // 1. Cambiar al iframe del carrusel
        WebElement iframe = driver.findElement(By.cssSelector("iframe[src*='carrusel-accesos-rapido']"));
        driver.switchTo().frame(iframe);

        // 2. Esperar y dar click en el botón del carrusel
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement boton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".botonesini[data-slide-to='" + indice + "']")
                )
        );

        boton.click();

        // 3. Volver al DOM principal
        driver.switchTo().defaultContent();
    }
}
