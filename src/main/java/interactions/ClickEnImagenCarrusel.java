package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class ClickEnImagenCarrusel implements Task {

    private final int indice;
    private final String urlEsperada;  // <-- AGREGADO

    public ClickEnImagenCarrusel(int indice, String urlEsperada) {   // <-- AGREGADO
        this.indice = indice;
        this.urlEsperada = urlEsperada; // <-- AGREGADO
    }

    public static Performable en(int indice, String urlEsperada) {   // <-- AGREGADO
        return new ClickEnImagenCarrusel(indice, urlEsperada);       // <-- AGREGADO
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Guardar ventana actual
        String ventanaActual = driver.getWindowHandle();

        // Entrar al iframe del carrusel
        WebElement iframe = driver.findElement(By.cssSelector("iframe[src*='carrusel-accesos-rapido']"));
        driver.switchTo().frame(iframe);

        WebDriverWait wait = new WebDriverWait(driver, 15);

        // Click en la imagen correcta
        WebElement imagen = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".carousel-item:nth-child(" + (indice + 1) + ") img")
                )
        );

        try {
            imagen.click();
        } catch (ElementClickInterceptedException e) {
            new Actions(driver).moveToElement(imagen).click().perform();
        }

        // Volver al DOM principal
        driver.switchTo().defaultContent();

        // Esperar que se abra nueva ventana
        WebDriverWait waitWindow = new WebDriverWait(driver, 10);
        waitWindow.until(driver1 -> driver1.getWindowHandles().size() > 1);

        // Cambiar a la nueva ventana
        Set<String> ventanas = driver.getWindowHandles();
        for (String ventana : ventanas) {
            if (!ventana.equals(ventanaActual)) {
                driver.switchTo().window(ventana);
                break;
            }
        }

        // ---------------------------------------------------------
        // 🔥 VALIDACIÓN DE LA URL (ÚNICO CÓDIGO AGREGADO)
        // ---------------------------------------------------------
        String urlActual = driver.getCurrentUrl();

        if (!urlActual.contains(urlEsperada)) {
            throw new AssertionError(
                    "La URL (" + urlActual + ") no contiene el texto esperado: " + urlEsperada
            );
        }
    }
}
