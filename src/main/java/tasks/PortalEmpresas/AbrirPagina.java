package tasks.PortalEmpresas;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import questions.EnPantallaDeLogin;

/**
 * Abre el portal dejando el navegador sin rastro de la sesion anterior.
 *
 * <p>El proyecto corre con {@code serenity.restart.browser.for.each=never}, asi que en una
 * ejecucion con varios escenarios el navegador se reutiliza. Con la eleccion de cuenta por
 * tag eso rompia: el escenario que entraba con la segunda cuenta heredaba la sesion viva
 * de la primera, el portal no volvia a pedir credenciales y el login fallaba.
 *
 * <p>Smart Tester lanza un tag por ejecucion, con navegador nuevo cada vez, asi que alli
 * no se daba. Pero una corrida local con varios tags si, y un escenario debe dar el mismo
 * resultado corriendo solo que acompanado.
 */
public class AbrirPagina implements Task {

  private static final Logger LOG = LoggerFactory.getLogger(AbrirPagina.class);

  private static final String LIMPIAR_ALMACENAMIENTO =
      "try { window.localStorage.clear(); window.sessionStorage.clear(); } catch (e) { }";

  private final String url;

  public AbrirPagina(String url) {
    this.url = url;
  }

  public static AbrirPagina en(String url) {
    return Tasks.instrumented(AbrirPagina.class, url);
  }

  @Override
  @Step("Abrir el portal con el navegador limpio")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Open.url(url));

    // Solo hay que limpiar si el portal NO nos dejo en la pantalla de login: eso significa
    // que reconocio una sesion viva de un escenario anterior. Comprobarlo evita recargar
    // el portal en el caso normal, que es casi siempre y cuesta varios segundos.
    if (!actor.asksFor(EnPantallaDeLogin.ahora())) {
      LOG.info("Habia una sesion previa activa; se limpia y se vuelve al login");
      limpiarSesionAnterior(actor);
      actor.attemptsTo(Open.url(url));
    }

    LOG.info("Portal abierto en {}", url);
  }

  private <T extends Actor> void limpiarSesionAnterior(T actor) {
    try {
      WebDriver driver = BrowseTheWeb.as(actor).getDriver();
      driver.manage().deleteAllCookies();

      // El almacenamiento local solo es accesible con el dominio ya cargado; por eso se
      // limpia despues del Open y no antes.
      ((JavascriptExecutor) driver).executeScript(LIMPIAR_ALMACENAMIENTO);

    } catch (RuntimeException noSePudoLimpiar) {
      LOG.warn("No se pudo limpiar la sesion anterior: {}", noSePudoLimpiar.getMessage());
    }
  }
}
