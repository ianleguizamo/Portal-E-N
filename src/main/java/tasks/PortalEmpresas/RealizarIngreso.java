package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.WaitFor;
import interactions.EnterPasswordSecure;
import interactions.IngresarTexto;
import interactions.JavaScriptSmartClick;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.EvidenciaUtils;

public class RealizarIngreso implements Task {

    private static final Logger LOGGER = Logger.getLogger(RealizarIngreso.class);
    private static final String paso = "Realizar inicio de sesion";
    private static final String URL_LOGIN = "https://miclaroempresas.com.co/login";

    /** Cantidad de intentos de login ante el bloqueo anti-bot. Se puede ajustar con -Dlogin.maxIntentos=N */
    private static final int MAX_INTENTOS = Integer.getInteger("login.maxIntentos", 3);

    /** Texto (sin tildes, para no depender del encoding) que identifica el modal de bloqueo del portal. */
    private static final String TEXTO_MODAL_ERROR = "al procesar tu solicitud";

    Map<String, String> data = new HashMap<>();

    public RealizarIngreso(Map<String, String> data) {
        this.data = data;
    }

    public static Performable realizarIngreso(Map<String, String> data) {
        return Instrumented.instanceOf(RealizarIngreso.class).withProperties(data);
    }

    @Override
    @Step("Realizar inicio de sesion en el portal")
    public <T extends Actor> void performAs(T actor) {
        boolean ingresoOk = false;

        for (int intento = 1; intento <= MAX_INTENTOS && !ingresoOk; intento++) {
            LOGGER.info("[Login Portal E&N] Intento " + intento + " de " + MAX_INTENTOS);

            actor.attemptsTo(
                    IngresarTexto.con(data.get("Usuario"), "correo electronico", TXT_USUARIO),
                    EnterPasswordSecure.into(TXT_CONTRASENA, data.get("Contrasena")),
                    JavaScriptSmartClick.on(BTN_INGRESAR)
            );

            // Damos tiempo a que el portal responda (o muestre el modal de error).
            WaitFor.silencioso(6000);

            if (modalErrorPresente(actor)) {
                LOGGER.warn("[Login Portal E&N] Intento " + intento
                        + ": el portal mostro 'Algo salio mal al procesar tu solicitud' "
                        + "(probable deteccion anti-bot). Se cierra el modal en 'Aceptar'.");
                cerrarModalConAceptar(actor);
                WaitFor.silencioso(3000);

                // Si quedan intentos, recargamos el login para un intento limpio.
                if (intento < MAX_INTENTOS) {
                    actor.attemptsTo(Open.url(URL_LOGIN));
                    WaitFor.silencioso(4000);
                }
            } else {
                ingresoOk = true;
            }
        }

        // Compatibilidad: cierra algun otro modal informativo posterior al login, si aparece.
        try {
            if (BTN_ACEPTAR.resolveFor(actor).isPresent()) {
                actor.attemptsTo(JavaScriptSmartClick.on(BTN_ACEPTAR));
            }
        } catch (Exception e) {
        }

        WaitFor.silencioso(3000);
        EvidenciaUtils.registrarCaptura(paso);

        if (!ingresoOk) {
            throw new AssertionError(
                    "No fue posible iniciar sesion en el Portal Empresas y Negocios: el portal bloqueo el ingreso con "
                    + "'Algo salio mal al procesar tu solicitud' tras " + MAX_INTENTOS + " intentos "
                    + "(probable deteccion anti-bot / captcha).");
        }
    }

    /** Revisa rapidamente si el modal de bloqueo esta en pantalla, sin esperar el implicit wait completo. */
    private boolean modalErrorPresente(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        try {
            List<WebElement> coincidencias = driver.findElements(
                    By.xpath("//*[contains(normalize-space(.),'" + TEXTO_MODAL_ERROR + "')]"));
            return !coincidencias.isEmpty();
        } catch (Exception e) {
            return false;
        } finally {
            // Restaura el implicit wait configurado en serenity.properties.
            driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        }
    }

    /** Da clic en el boton "Aceptar" del modal de error (por texto o por clase), de forma tolerante. */
    private void cerrarModalConAceptar(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            List<WebElement> botones = driver.findElements(By.xpath(
                    "//button[normalize-space(.)='Aceptar' or normalize-space(.)='ACEPTAR' or contains(@class,'acept')]"));
            for (WebElement boton : botones) {
                if (boton.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", boton);
                    return;
                }
            }
            LOGGER.warn("[Login Portal E&N] No se encontro un boton 'Aceptar' visible para cerrar el modal.");
        } catch (Exception e) {
            LOGGER.warn("[Login Portal E&N] Error cerrando el modal de error: " + e.getMessage());
        }
    }
}
