package tasks.PortalEmpresas;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterfaces.CmaxPage.*;

import interactions.*;
import interactions.scroll.ScrollMenuLateral;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class IngresarMenuDesplegable implements Task {

    private static final Logger log = LoggerFactory.getLogger(IngresarMenuDesplegable.class);
    Map<String, String> data = new HashMap<>();

    public IngresarMenuDesplegable(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Selecciona termininos y condiciones";
    private static final String paso2 = "Validacion de termininos y condiciones";
    private static final String paso3 = "Seleccion y validacion de Politica de privacidad";

    public static Performable ingresarMenuDesplegable(Map<String, String> data) {
        return Instrumented.instanceOf(IngresarMenuDesplegable.class).withProperties(data);
    }

    @Override
    @Step("Ingresar al menu desplegable y validar terminos y politicas")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        String ventanaPrincipal = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        abrirMenu(actor);
        validarTerminos(actor, driver, ventanaPrincipal, wait);
        validarPoliticas(actor, driver, ventanaPrincipal, wait);
    }

    @Step("Abrir menu desplegable")
    private <T extends Actor> void abrirMenu(T actor) {
        actor.attemptsTo(
                SmartClick.on(MENU_DESPLEGABLE),
                ScrollMenuLateral.by(300)
        );
        WaitFor.silencioso(3000);
        EvidenciaUtils.registrarCaptura(paso1);
    }

    @Step("Validar terminos y condiciones")
    private <T extends Actor> void validarTerminos(T actor, WebDriver driver, String ventanaPrincipal, WebDriverWait wait) {
        actor.attemptsTo(
                WaitUntil.the(TERMINOS_Y_CONDICIONES, isVisible()).forNoMoreThan(5).seconds(),
                SmartClick.on(TERMINOS_Y_CONDICIONES)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String ventana : driver.getWindowHandles()) {
            if (!ventana.equals(ventanaPrincipal)) {
                driver.switchTo().window(ventana);
                break;
            }
        }
        WaitFor.silencioso(3000);
        actor.attemptsTo(CerrarPestañaYVolver.ahora(ventanaPrincipal));
    }

    @Step("Validar politicas de privacidad")
    private <T extends Actor> void validarPoliticas(T actor, WebDriver driver, String ventanaPrincipal, WebDriverWait wait) {
        actor.attemptsTo(SmartClick.on(POLITICAS_PRIVACIDAD));
        EvidenciaUtils.registrarCaptura(paso3);

        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String ventana : driver.getWindowHandles()) {
            if (!ventana.equals(ventanaPrincipal)) {
                driver.switchTo().window(ventana);
                break;
            }
        }
        WaitFor.silencioso(3000);
        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal),
                ScrollMenuLateral.by(-300)
        );
        WaitFor.silencioso(3000);
    }
}