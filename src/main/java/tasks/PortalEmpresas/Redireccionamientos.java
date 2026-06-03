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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CapturasPantallasWeb;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class Redireccionamientos implements Task {

    Map<String, String> data = new HashMap<>();
    private static final String paso1 = "Se validan redireccionamientos whatsapp ";
    private static final String paso2 = "Se validan redireccionamientos claro.com";
    private static final String paso3 = "Se validan redireccionamientos play store";

    public Redireccionamientos(Map<String, String> data) {
        this.data = data;
    }


    public static Performable redireccionamientos(Map<String, String> data) {

        return Instrumented.instanceOf(Redireccionamientos.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Ventana principal

        String ventanaPrincipal = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        actor.attemptsTo(
                Click.on(CAMPANA),
                WaitFor.aTime(2000)
        );

        actor.attemptsTo(
                Click.on(CAMPANA_X),
                WaitForResponse.withTarget(MENU_DESPLEGABLE),
                Click.on(MENU_DESPLEGABLE),
                WaitFor.aTime(2000)
        );

        actor.attemptsTo(
                WaitUntil.the(BTN_CERRAR_MENU, isVisible())
                        .forNoMoreThan(10)
                        .seconds(),

                Click.on(BTN_CERRAR_MENU)
        );

        // BANNER 1

        actor.attemptsTo(
                ScrollDown.by(300),
                ClickEnCarrusel.en(0),
                WaitFor.aTime(300),
                ClickEnImagenCarrusel.en(
                        0,
                        "https://api.whatsapp.com/send?phone=573112000000"
                )
        );
        EvidenciaUtils.registrarCaptura(paso1);


        // Esperar nueva pestaña
        wait.until(d -> d.getWindowHandles().size() > 1);

        // Cambiar a nueva pestaña
        for (String ventana : driver.getWindowHandles()) {

            if (!ventana.equals(ventanaPrincipal)) {

                driver.switchTo().window(ventana);

                break;
            }
        }

        actor.attemptsTo(
                WaitFor.aTime(1000)
        );

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal),
                WaitFor.aTime(1000)
        );

        // BANNER 2

        actor.attemptsTo(
                ClickEnCarrusel.en(1),
                WaitFor.aTime(300),
                ClickEnImagenCarrusel.en(
                        1,
                        "https://www.claro.com.co/5g/"
                )
        );

        EvidenciaUtils.registrarCaptura(paso2);

        // Esperar nueva pestaña
        wait.until(d -> d.getWindowHandles().size() > 1);

        // Cambiar a nueva pestaña
        for (String ventana : driver.getWindowHandles()) {

            if (!ventana.equals(ventanaPrincipal)) {

                driver.switchTo().window(ventana);

                break;
            }
        }

        actor.attemptsTo(
                WaitFor.aTime(1000)
        );

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal),
                WaitFor.aTime(1000)
        );

        // BANNER 3

        actor.attemptsTo(
                ClickEnCarrusel.en(2),
                WaitFor.aTime(300),
                ClickEnImagenCarrusel.en(
                        2,
                        "https://play.google.com/store/apps/details?id=com.clarocolombia.miclaro&hl=en_US"
                )
        );

        EvidenciaUtils.registrarCaptura(paso3);

        // Esperar nueva pestaña
        wait.until(d -> d.getWindowHandles().size() > 1);

        // Cambiar a nueva pestaña
        for (String ventana : driver.getWindowHandles()) {

            if (!ventana.equals(ventanaPrincipal)) {

                driver.switchTo().window(ventana);

                break;
            }
        }

        actor.attemptsTo(
                WaitFor.aTime(1000)
        );

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal)
        );
    }
}