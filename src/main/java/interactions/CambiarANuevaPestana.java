package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Espera a que la pasarela abra su pestana y pasa el foco a ella.
 *
 * <p>Complemento de CerrarPestanaYVolver: uno entra a la pestana nueva y el otro
 * la cierra devolviendo el foco a la ventana original.
 */
public class CambiarANuevaPestana implements Task {

  private static final int TIMEOUT_SEGUNDOS = 10;

  /** La pasarela suele pintar el contenido despues de cargar; sin esto la captura sale en blanco. */
  private static final int ESPERA_ESTABILIZACION_MS = 3000;

  private final String ventanaPrincipal;

  public CambiarANuevaPestana(String ventanaPrincipal) {
    this.ventanaPrincipal = ventanaPrincipal;
  }

  public static Performable desde(String ventanaPrincipal) {
    return instrumented(CambiarANuevaPestana.class, ventanaPrincipal);
  }

  @Override
  @Step("Cambiar a la nueva pestana")
  public <T extends Actor> void performAs(T actor) {
    WebDriver driver = BrowseTheWeb.as(actor).getDriver();

    new WebDriverWait(driver, TIMEOUT_SEGUNDOS).until(d -> d.getWindowHandles().size() > 1);

    driver.getWindowHandles().stream()
        .filter(ventana -> !ventana.equals(ventanaPrincipal))
        .findFirst()
        .ifPresent(ventana -> driver.switchTo().window(ventana));

    WaitFor.silencioso(ESPERA_ESTABILIZACION_MS);
  }
}
