package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.WaitFor;
import interactions.EnterPasswordSecure;
import interactions.IngresarTexto;
import interactions.JavaScriptSmartClick;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.RespuestaLogin;
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
import questions.EnPantallaDeLogin;
import utils.ContextoST;
import utils.EvidenciaUtils;

public class RealizarIngreso implements Task {

    private static final Logger LOGGER = Logger.getLogger(RealizarIngreso.class);
    private static final String paso = "Realizar inicio de sesion";
    private static final String URL_LOGIN = "https://miclaroempresas.com.co/login";
    private static final String RUTA_LOGIN = "/login";

    /** Intentos de login ante un error del portal. Se puede ajustar con -Dlogin.maxIntentos=N */
    private static final int MAX_INTENTOS = Integer.getInteger("login.maxIntentos", 3);

    /** Tope para que el portal conteste al boton Ingresar. */
    private static final int ESPERA_RESPUESTA_MS = 30000;

    /**
     * Espera minima tras pulsar Ingresar. Es lo que antes se esperaba siempre en fijo: aunque la
     * respuesta llegue antes, el inicio tarda en pintarse junto con el modal de bienvenida que
     * se cierra mas abajo.
     */
    private static final int MARGEN_MINIMO_MS = 6000;

    private static final int SONDEO_MS = 500;

    private static final String CARGANDO = "CARGANDO";
    private static final String SIN_REACCION = "SIN_REACCION";
    private static final String PREFIJO_OTRO_ERROR = "OTRO_ERROR|";

    /**
     * Lee que respondio el login segun el modal que quedo visible. Calcado de LOGIN.loginEmpresas
     * en el JS del portal: 500 -> #errorServer2, 429 -> #errorRecaptcha, 401 y codigos sin
     * modal propio -> #errorServerShort, 400 y fallo del segundo paso -> #errorServer, 403 ->
     * #errorServerUser. Mientras espera la respuesta muestra #loaderModal.
     */
    private static final String LEER_RESPUESTA =
            "var vis = function (id) { var e = document.getElementById(id); if (!e) { return false; }"
                    + " var s = window.getComputedStyle(e);"
                    + " return s.display !== 'none' && s.visibility !== 'hidden' && e.getClientRects().length > 0; };"
                    + "var txt = function (id) { var e = document.getElementById(id);"
                    + " return e ? e.innerText.replace(/\\s+/g, ' ').trim() : ''; };"
                    + "if (location.pathname.indexOf('" + RUTA_LOGIN + "') !== 0) { return 'INGRESO'; }"
                    + "if (vis('errorRecaptcha')) { return 'RECHAZO_RECAPTCHA'; }"
                    + "if (vis('errorServer2')) { return 'ERROR_SERVIDOR'; }"
                    + "if (vis('errorServerShort')) { var m = txt('json-message-short');"
                    + " return /credenciales/i.test(m) ? 'CREDENCIALES_INCORRECTAS' : '" + PREFIJO_OTRO_ERROR + "' + m; }"
                    + "if (vis('errorServerUser')) { return '" + PREFIJO_OTRO_ERROR + "' + txt('errorServerUser'); }"
                    + "if (vis('errorServer')) { return '" + PREFIJO_OTRO_ERROR + "' + txt('errorTextModal'); }"
                    + "if (vis('loaderModal')) { return '" + CARGANDO + "'; }"
                    + "return '" + SIN_REACCION + "';";

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
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        List<Respuesta> fallidos = new ArrayList<>();
        boolean ingresoOk = false;

        for (int intento = 1; intento <= MAX_INTENTOS && !ingresoOk; intento++) {
            LOGGER.info("[Login Portal E&N] Intento " + intento + " de " + MAX_INTENTOS);

            actor.attemptsTo(
                    IngresarTexto.con(data.get("Usuario"), "correo electronico", TXT_USUARIO),
                    EnterPasswordSecure.into(TXT_CONTRASENA, data.get("Contrasena")),
                    JavaScriptSmartClick.on(BTN_INGRESAR)
            );

            Respuesta respuesta = esperarRespuesta(driver);
            LOGGER.info("[Login Portal E&N] Intento " + intento + ": " + respuesta.texto());

            if (respuesta.tipo == RespuestaLogin.INGRESO) {
                ingresoOk = true;
                break;
            }

            fallidos.add(respuesta);

            // Reintentar con la misma contrasena no la arregla y puede bloquear la cuenta.
            if (respuesta.tipo == RespuestaLogin.CREDENCIALES_INCORRECTAS) {
                break;
            }

            cerrarModalConAceptar(actor);
            WaitFor.silencioso(3000);

            // Si quedan intentos, recargamos el login para un intento limpio.
            if (intento < MAX_INTENTOS) {
                actor.attemptsTo(Open.url(URL_LOGIN));
                WaitFor.silencioso(4000);
            }

            // Red de seguridad: si pese al error el portal dejo la sesion iniciada, /login
            // redirige al inicio y el intento siguiente moriria buscando el campo de correo.
            if (sesionYaIniciada(actor)) {
                LOGGER.info("[Login Portal E&N] Intento " + intento
                        + ": pese al error la sesion quedo iniciada; se continua.");
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

        if (ingresoOk) {
            // Contrato st-context: el portal dejo entrar de verdad con esos datos.
            ContextoST.confirmarLogin();
        }

        if (!ingresoOk) {
            throw new AssertionError(mensajeDeFallo(fallidos));
        }
    }

    /**
     * Sondea la pagina hasta que el portal conteste al boton Ingresar o se agote la espera.
     *
     * <p>Antes se esperaban 6 s fijos y se buscaba el texto del modal: un login lento (spinner
     * todavia girando) se contaba como bloqueo, igual que un error real.
     */
    private Respuesta esperarRespuesta(WebDriver driver) {
        long inicio = System.currentTimeMillis();
        String estado = SIN_REACCION;

        while (System.currentTimeMillis() - inicio < ESPERA_RESPUESTA_MS) {
            estado = leerEstado(driver);
            if (!CARGANDO.equals(estado) && !SIN_REACCION.equals(estado)) {
                break;
            }
            WaitFor.silencioso(SONDEO_MS);
        }

        long transcurrido = System.currentTimeMillis() - inicio;
        if (transcurrido < MARGEN_MINIMO_MS) {
            WaitFor.silencioso((int) (MARGEN_MINIMO_MS - transcurrido));
        }

        return Respuesta.de(estado);
    }

    private String leerEstado(WebDriver driver) {
        try {
            return String.valueOf(((JavascriptExecutor) driver).executeScript(LEER_RESPUESTA));
        } catch (RuntimeException paginaEnTransicion) {
            // Tipicamente el portal esta navegando al inicio tras un login correcto.
            return CARGANDO;
        }
    }

    /**
     * Mensaje que termina en Smart Tester: dice que paso de verdad en cada intento para que su
     * analisis no tenga que adivinar. Ver en RespuestaLogin por que no debe decir "captcha"
     * salvo que lo haya sido.
     */
    private static String mensajeDeFallo(List<Respuesta> fallidos) {
        StringBuilder mensaje = new StringBuilder(
                "No fue posible iniciar sesion en el Portal Empresas y Negocios: ");

        boolean todosIguales = fallidos.stream().map(Respuesta::texto).distinct().count() == 1;
        if (todosIguales) {
            mensaje.append(fallidos.get(0).texto())
                    .append(fallidos.size() == 1 ? "." : " en los " + fallidos.size() + " intentos.");
        } else {
            for (int i = 0; i < fallidos.size(); i++) {
                mensaje.append(i == 0 ? "" : "; ")
                        .append("intento ").append(i + 1).append(": ").append(fallidos.get(i).texto());
            }
            mensaje.append('.');
        }

        if (fallidos.stream().allMatch(r -> r.tipo.esFallaDelPortal())) {
            mensaje.append(" Falla del portal: la automatizacion completo el formulario y pulso Ingresar.");
        } else if (fallidos.stream().anyMatch(r -> r.tipo == RespuestaLogin.CREDENCIALES_INCORRECTAS)) {
            mensaje.append(" Revisar la contrasena de la cuenta en config/real-user.json.");
        }
        return mensaje.toString();
    }

    /** Tipo de respuesta del portal mas el detalle que la acompana, si lo hay. */
    private static final class Respuesta {

        private final RespuestaLogin tipo;
        private final String detalle;

        private Respuesta(RespuestaLogin tipo, String detalle) {
            this.tipo = tipo;
            this.detalle = detalle;
        }

        static Respuesta de(String estado) {
            if (estado.startsWith(PREFIJO_OTRO_ERROR)) {
                return new Respuesta(RespuestaLogin.OTRO_ERROR,
                        "'" + estado.substring(PREFIJO_OTRO_ERROR.length()) + "'");
            }
            int segundos = ESPERA_RESPUESTA_MS / 1000;
            if (CARGANDO.equals(estado)) {
                return new Respuesta(RespuestaLogin.SIN_RESPUESTA,
                        "el indicador de carga siguio activo " + segundos + " s");
            }
            if (SIN_REACCION.equals(estado)) {
                return new Respuesta(RespuestaLogin.SIN_RESPUESTA,
                        "la pagina no reacciono en " + segundos + " s");
            }
            return new Respuesta(RespuestaLogin.valueOf(estado), "");
        }

        String texto() {
            return detalle.isEmpty() ? tipo.descripcion() : tipo.descripcion() + ": " + detalle;
        }
    }

    /**
     * El portal nos tiene dentro aunque haya mostrado un error.
     *
     * <p>Se exigen las dos senales para no dar por bueno un login que solo va lento: que la URL
     * haya salido de /login (el portal redirigio) y que el formulario no este. Si la URL sigue
     * en /login se responde al instante, sin gastar la espera del formulario.
     */
    private boolean sesionYaIniciada(Actor actor) {
        if (rutaActual(actor).startsWith(RUTA_LOGIN)) {
            return false;
        }
        return !actor.asksFor(EnPantallaDeLogin.ahora());
    }

    private String rutaActual(Actor actor) {
        try {
            String ruta = URI.create(BrowseTheWeb.as(actor).getDriver().getCurrentUrl()).getPath();
            return ruta == null ? RUTA_LOGIN : ruta;
        } catch (RuntimeException urlIlegible) {
            // Ante la duda, como si siguiera en el login: se reintenta igual que antes.
            return RUTA_LOGIN;
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
