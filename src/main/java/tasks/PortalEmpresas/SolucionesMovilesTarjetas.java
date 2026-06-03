package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.conditions.Check;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CapturasPantallasWeb;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SolucionesMovilesTarjetas implements Task {

    Map<String, String> data = new HashMap<>();

    public SolucionesMovilesTarjetas(Map<String, String> data) {
        this.data = data;
    }

    public static Performable solucionesMovilesTarjetas(Map<String, String> data) {
        return Instrumented.instanceOf(SolucionesMovilesTarjetas.class)
                .withProperties(data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        actor.attemptsTo(
                Click.on(PAGOS_EN_LINEA)
        );

        CapturasPantallasWeb.capturaPantalla(
                "Pagos en linea",
                "Pagos en linea"
        );

        actor.attemptsTo(
                Click.on(PAGO_SOLUCIONES_MOVILES)
        );

        CapturasPantallasWeb.capturaPantalla(
                "Soluciones moviles",
                "Soluciones moviles"
        );

        actor.attemptsTo(
                Check.whether(CHECKBOX_FILA.resolveFor(actor).isPresent())
                        .andIfSo(
                                Click.on(CHECKBOX_FILA),
                                WaitForResponse.withTarget(BOTON_PAGAR),
                                Click.on(BOTON_PAGAR),
                                WaitForResponse.withTarget(METODO_TARJETA),
                                Click.on(METODO_TARJETA)
                        )
                        .otherwise(
                                WaitFor.aTime(100)
                        )
        );

        CapturasPantallasWeb.capturaPantalla(
                "Tarjetas",
                "Tarjetas"
        );

        // Guardar ventana principal

        String ventanaPrincipal = driver.getWindowHandle();

        actor.attemptsTo(
                SmartClick.on(BOTON_CONTINUAR)
        );

        // Eesperar una ueva pestaña

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(d -> d.getWindowHandles().size() > 1);

        // Cambiar a una nueva pestaña

        for (String ventana : driver.getWindowHandles()) {

            if (!ventana.equals(ventanaPrincipal)) {

                driver.switchTo().window(ventana);

                break;
            }
        }

        CapturasPantallasWeb.capturaPantalla(
                "Redireccionamiento Tarjetas",
                "Redireccionamiento tarjetas"
        );

        actor.attemptsTo(
                CerrarPestañaYVolver.ahora(ventanaPrincipal)
        );
    }
}