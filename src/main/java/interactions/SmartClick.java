package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.Set;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Clic que ademas se hace cargo de la pestana nueva, si el enlace abre una.
 *
 * <p>La version anterior dormia 1200 ms fijos despues de cada clic para darle tiempo a una
 * posible pestana. Con 87 usos eso son ~104 s por corrida gastados casi siempre en balde,
 * porque la gran mayoria de los clics no abren nada; y a la vez se quedaba corto con una
 * pestana que tardara mas de 1200 ms en aparecer.
 *
 * <p>Ahora se mira el elemento ANTES de pulsarlo:
 *
 * <ul>
 *   <li>Si abre en pestana nueva (target="_blank" o un window.open en el onclick), se
 *       espera por la pestana y se salta a ella en cuanto aparece, hasta {@link
 *       #ESPERA_PESTANA_SEGUNDOS} s. Mas fiable que los 1200 ms de antes.
 *   <li>Si no, no se espera ninguna pestana. Solo se espera a que el documento quede
 *       listo, que en una pagina ya cargada devuelve en milisegundos.
 * </ul>
 *
 * <p>Por si algun clic abriera una pestana desde JavaScript sin declararlo, despues del
 * clic se comprueba igualmente el numero de ventanas: si ya hay una nueva, se cambia a
 * ella. Esa comprobacion no cuesta espera.
 */
public class SmartClick implements Task {

  /** Margen para que el navegador materialice la pestana del enlace. */
  private static final int ESPERA_PESTANA_SEGUNDOS = 10;

  /** Tope para que termine una navegacion disparada por el clic. */
  private static final int ESPERA_DOCUMENTO_SEGUNDOS = 10;

  /**
   * Colchon minimo para que el portal reaccione al clic (animaciones, AJAX corto). Es lo
   * que queda de los 1200 ms originales: si algun flujo se vuelve inestable, este es el
   * numero a subir, no el resto.
   */
  private static final int COLCHON_MS = 300;

  private static final String ABRE_EN_PESTANA_NUEVA =
      "var e = arguments[0];"
          + "var a = (e.closest ? e.closest('a, form') : null) || e;"
          + "var t = a.getAttribute && a.getAttribute('target');"
          + "if (t === '_blank') { return true; }"
          + "var click = e.getAttribute && e.getAttribute('onclick');"
          + "return !!click && click.indexOf('window.open') >= 0;";

  private final Target target;
  private final String descripcion;

  public SmartClick(Target target, String descripcion) {
    this.target = target;
    this.descripcion = descripcion;
  }

  /** Con descripcion personalizada. */
  public static SmartClick on(Target target, String descripcion) {
    return instrumented(SmartClick.class, target, descripcion);
  }

  /** Sin descripcion: usa el nombre del Target. */
  public static SmartClick on(Target target) {
    return instrumented(SmartClick.class, target, target.getName());
  }

  @Override
  @Step("Clic en #descripcion")
  public <T extends Actor> void performAs(T actor) {
    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
    Set<String> ventanasAntes = driver.getWindowHandles();

    boolean abrePestana = abreEnPestanaNueva(driver, target.resolveFor(actor));

    // Se resuelve otra vez: entre la inspeccion y el clic la pagina puede repintar y
    // dejar obsoleta la referencia anterior.
    target.resolveFor(actor).click();

    if (abrePestana) {
      esperarPestanaNueva(driver, ventanasAntes);
    } else {
      esperarDocumentoListo(driver);
    }

    WaitFor.silencioso(COLCHON_MS);

    cambiarSiHayPestanaNueva(driver, ventanasAntes);
  }

  private boolean abreEnPestanaNueva(WebDriver driver, WebElementFacade elemento) {
    try {
      Object declara =
          ((JavascriptExecutor) driver).executeScript(ABRE_EN_PESTANA_NUEVA, elemento);
      return Boolean.TRUE.equals(declara);

    } catch (RuntimeException noSePudoInspeccionar) {
      // Ante la duda se asume que si: se comporta como la version anterior.
      return true;
    }
  }

  private void esperarPestanaNueva(WebDriver driver, Set<String> ventanasAntes) {
    try {
      new WebDriverWait(driver, ESPERA_PESTANA_SEGUNDOS)
          .until(d -> d.getWindowHandles().size() > ventanasAntes.size());

    } catch (RuntimeException noAbrio) {
      // El enlace lo declaraba pero no abrio nada: no es motivo para tumbar la prueba.
    }
  }

  private void esperarDocumentoListo(WebDriver driver) {
    try {
      new WebDriverWait(driver, ESPERA_DOCUMENTO_SEGUNDOS)
          .until(
              d ->
                  "complete"
                      .equals(((JavascriptExecutor) d).executeScript("return document.readyState")));

    } catch (RuntimeException sigueCargando) {
      // Se continua: el paso siguiente tiene sus propias esperas.
    }
  }

  private void cambiarSiHayPestanaNueva(WebDriver driver, Set<String> ventanasAntes) {
    driver.getWindowHandles().stream()
        .filter(ventana -> !ventanasAntes.contains(ventana))
        .findFirst()
        .ifPresent(nueva -> driver.switchTo().window(nueva));
  }
}
