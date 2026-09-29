package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.*;

import interactions.*;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Redireccionamientos implements Task {

    Map<String, String> data = new HashMap<>();
    private static final String paso1 = "Se validan redireccionamientos whatsapp";
    private static final String paso2 = "Se validan redireccionamientos www.claro.com";
    private static final String paso3 = "Se validan redireccionamientos Google Play";

    private static final String URL_WHATSAPP = "https://api.whatsapp.com/send?phone=573112000000";
    private static final String URL_CLARO_5G = "https://www.claro.com.co/5g/";
    private static final String URL_PLAY_STORE =
            "https://play.google.com/store/apps/details?id=com.clarocolombia.miclaro&hl=en_US";

    private static final By IFRAME_CARRUSEL = By.cssSelector("iframe[src*='carrusel-accesos-rapido']");

    public Redireccionamientos(Map<String, String> data) {
        this.data = data;
    }

    public static Performable redireccionamientos(Map<String, String> data) {
        return Instrumented.instanceOf(Redireccionamientos.class).withProperties(data);
    }

    @Override
    @Step("Validar redireccionamientos del portal")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        String ventanaPrincipal = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        cerrarCampanaYAbrirMenu(actor);
        validarBannerWhatsapp(actor, driver, ventanaPrincipal, wait);
        validarBannerClaro(actor, driver, ventanaPrincipal, wait);
        validarBannerPlayStore(actor, driver, ventanaPrincipal, wait);
    }

    @Step("Cerrar campana y abrir menu")
    private <T extends Actor> void cerrarCampanaYAbrirMenu(T actor) {
        actor.attemptsTo(
                SmartClick.on(CAMPANA)
        );
        WaitFor.silencioso(2000);
        actor.attemptsTo(
                SmartClick.on(CAMPANA_X),
                WaitForResponse.withTarget(MENU_DESPLEGABLE),
                SmartClick.on(MENU_DESPLEGABLE)
        );
        WaitFor.silencioso(2000);
        actor.attemptsTo(
                WaitUntil.the(BTN_CERRAR_MENU, isVisible()).forNoMoreThan(10).seconds(),
                SmartClick.on(BTN_CERRAR_MENU),
                ScrollDown.by(300)
        );
    }

    @Step("Validar redireccionamiento a WhatsApp")
    private <T extends Actor> void validarBannerWhatsapp(T actor, WebDriver driver, String ventanaPrincipal, WebDriverWait wait) {
        validarBanner(actor, driver, ventanaPrincipal, wait, URL_WHATSAPP, paso1);
    }

    @Step("Validar redireccionamiento a Claro.com")
    private <T extends Actor> void validarBannerClaro(T actor, WebDriver driver, String ventanaPrincipal, WebDriverWait wait) {
        validarBanner(actor, driver, ventanaPrincipal, wait, URL_CLARO_5G, paso2);
    }

    @Step("Validar redireccionamiento a Play Store")
    private <T extends Actor> void validarBannerPlayStore(T actor, WebDriver driver, String ventanaPrincipal, WebDriverWait wait) {
        validarBanner(actor, driver, ventanaPrincipal, wait, URL_PLAY_STORE, paso3);
    }

    private <T extends Actor> void validarBanner(T actor, WebDriver driver, String ventanaPrincipal,
                                                 WebDriverWait wait, String urlDestino, String paso) {
        int indice = indiceDelBanner(driver, urlDestino);

        actor.attemptsTo(
                ClickEnCarrusel.en(indice)
        );
        WaitFor.silencioso(300);
        actor.attemptsTo(
                ClickEnImagenCarrusel.en(indice, urlDestino)
        );
        EvidenciaUtils.registrarCaptura(paso);

        wait.until(d -> d.getWindowHandles().size() > 1);
        cambiarPestana(driver, ventanaPrincipal);
        WaitFor.silencioso(1000);
        actor.attemptsTo(CerrarPestañaYVolver.ahora(ventanaPrincipal));
        WaitFor.silencioso(1000);
    }

    /**
     * Posicion en el carrusel del banner que lleva a urlDestino.
     *
     * <p>Se busca por destino y no por una posicion fija: el portal agrego "Pagos en linea"
     * como primer banner, todos los demas se corrieron un puesto y la posicion 0, que la
     * prueba daba por WhatsApp, empezo a abrir pagos-linea. Cada diapositiva empieza por
     * su propio banner, asi que basta con mirar el primer enlace de cada una.
     */
    private int indiceDelBanner(WebDriver driver, String urlDestino) {
        WebElement iframe = driver.findElement(IFRAME_CARRUSEL);
        driver.switchTo().frame(iframe);
        try {
            List<WebElement> diapositivas = driver.findElements(By.cssSelector(".carousel-item"));
            for (int i = 0; i < diapositivas.size(); i++) {
                List<WebElement> enlaces = diapositivas.get(i).findElements(By.cssSelector("a.card-item"));
                if (!enlaces.isEmpty() && String.valueOf(enlaces.get(0).getAttribute("href")).contains(urlDestino)) {
                    return i;
                }
            }
        } finally {
            driver.switchTo().defaultContent();
        }
        throw new AssertionError("El carrusel del inicio ya no tiene un banner que lleve a " + urlDestino);
    }

    private void cambiarPestana(WebDriver driver, String ventanaPrincipal) {
        for (String ventana : driver.getWindowHandles()) {
            if (!ventana.equals(ventanaPrincipal)) {
                driver.switchTo().window(ventana);
                break;
            }
        }
    }
}
