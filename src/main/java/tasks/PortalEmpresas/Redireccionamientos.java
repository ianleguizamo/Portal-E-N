package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.*;

import interactions.*;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class Redireccionamientos implements Task {

    Map<String, String> data = new HashMap<>();
    private static final String paso1 = "Se validan redireccionamientos whatsapp";
    private static final String paso2 = "Se validan redireccionamientos www.claro.com";
    private static final String paso3 = "Se validan redireccionamientos Google Play";

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
        actor.attemptsTo(
                ClickEnCarrusel.en(0)
        );
        WaitFor.silencioso(300);
        actor.attemptsTo(
                ClickEnImagenCarrusel.en(0, "https://api.whatsapp.com/send?phone=573112000000")
        );
        EvidenciaUtils.registrarCaptura(paso1);

        wait.until(d -> d.getWindowHandles().size() > 1);
        cambiarPestana(driver, ventanaPrincipal);
        WaitFor.silencioso(1000);
        actor.attemptsTo(CerrarPestañaYVolver.ahora(ventanaPrincipal));
        WaitFor.silencioso(1000);
    }

    @Step("Validar redireccionamiento a Claro.com")
    private <T extends Actor> void validarBannerClaro(T actor, WebDriver driver, String ventanaPrincipal, WebDriverWait wait) {
        actor.attemptsTo(
                ClickEnCarrusel.en(1)
        );
        WaitFor.silencioso(300);
        actor.attemptsTo(
                ClickEnImagenCarrusel.en(1, "https://www.claro.com.co/5g/")
        );
        EvidenciaUtils.registrarCaptura(paso2);

        wait.until(d -> d.getWindowHandles().size() > 1);
        cambiarPestana(driver, ventanaPrincipal);
        WaitFor.silencioso(1000);
        actor.attemptsTo(CerrarPestañaYVolver.ahora(ventanaPrincipal));
        WaitFor.silencioso(1000);
    }

    @Step("Validar redireccionamiento a Play Store")
    private <T extends Actor> void validarBannerPlayStore(T actor, WebDriver driver, String ventanaPrincipal, WebDriverWait wait) {
        actor.attemptsTo(
                ClickEnCarrusel.en(2)
        );
        WaitFor.silencioso(300);
        actor.attemptsTo(
                ClickEnImagenCarrusel.en(2, "https://play.google.com/store/apps/details?id=com.clarocolombia.miclaro&hl=en_US")
        );
        EvidenciaUtils.registrarCaptura(paso3);

        wait.until(d -> d.getWindowHandles().size() > 1);
        cambiarPestana(driver, ventanaPrincipal);
        WaitFor.silencioso(1000);
        actor.attemptsTo(CerrarPestañaYVolver.ahora(ventanaPrincipal));
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