package interactions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.serenitybdd.core.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Espera activa hasta que aparezca un elemento (por Target) o alguno de unos textos.
 *
 * <p>Notas de mantenimiento:
 *
 * <ul>
 *   <li>La version anterior recorria {@code expectedTexts} incluso cuando se construia con
 *       un Target, con lo que {@code withTarget(...)} lanzaba NullPointerException en
 *       cuanto el elemento no estaba presente en el primer intento. Ahora cada modo mira
 *       solo su propia fuente.
 *   <li>Tambien buscaba los textos con {@code MobileBy.AndroidUIAutomator}, un localizador
 *       de Appium que en un navegador nunca puede resolver. Se cambio por XPath.
 *   <li>El bucle no dormia entre intentos: consumia un nucleo al 100% durante toda la
 *       espera. Ahora pausa {@link #INTERVALO_MS} entre sondeos.
 * </ul>
 */
public class WaitForResponse implements Interaction {

  /**
   * 30 s cubre de sobra las respuestas del portal. El valor anterior (120 s) nunca se
   * llegaba a agotar porque la espera reventaba antes con el NullPointerException; ahora
   * que espera de verdad, 120 s solo servirian para alargar los escenarios que ya fallan.
   */
  private static final int DEFAULT_TIMEOUT = 30;

  private static final int INTERVALO_MS = 250;

  private final List<String> expectedTexts;
  private final Target target;
  private final int timeout;

  public WaitForResponse(List<String> expectedTexts, int timeout) {
    this.expectedTexts = expectedTexts;
    this.timeout = timeout;
    this.target = null;
  }

  public WaitForResponse(Target target, int timeout) {
    this.target = target;
    this.timeout = timeout;
    this.expectedTexts = Collections.emptyList();
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    long limite = System.currentTimeMillis() + timeout * 1000L;

    do {
      if (estaPresente(actor)) {
        return;
      }
      new InternalSystemClock().pauseFor(INTERVALO_MS);
    } while (System.currentTimeMillis() < limite);

    throw new AssertionError("No aparecio " + descripcion() + " en " + timeout + " segundos.");
  }

  private <T extends Actor> boolean estaPresente(T actor) {
    try {
      if (target != null) {
        return target.resolveFor(actor).isPresent();
      }
      return expectedTexts.stream().anyMatch(texto -> contieneTexto(actor, texto));
    } catch (RuntimeException noDisponibleTodavia) {
      return false;
    }
  }

  private <T extends Actor> boolean contieneTexto(T actor, String texto) {
    By locator = By.xpath("//*[contains(normalize-space(.), \"" + texto + "\")]");
    return !BrowseTheWeb.as(actor).getDriver().findElements(locator).isEmpty();
  }

  private String descripcion() {
    return target != null ? target.getName() : "ninguno de los textos " + expectedTexts;
  }

  /* ===== Espera por elemento ===== */

  public static WaitForResponse withTarget(Target target) {
    return new WaitForResponse(target, DEFAULT_TIMEOUT);
  }

  public static WaitForResponse withTarget(Target target, int timeoutSeconds) {
    return new WaitForResponse(target, timeoutSeconds);
  }

  /* ===== Espera por texto ===== */

  public static WaitForResponse withText(String text) {
    return new WaitForResponse(Collections.singletonList(text), DEFAULT_TIMEOUT);
  }

  public static WaitForResponse withText(String text, int timeoutSeconds) {
    return new WaitForResponse(Collections.singletonList(text), timeoutSeconds);
  }

  public static WaitForResponse withAnyText(String... texts) {
    return new WaitForResponse(Arrays.asList(texts), DEFAULT_TIMEOUT);
  }

  public static WaitForResponse withAnyText(List<String> texts) {
    return new WaitForResponse(texts, DEFAULT_TIMEOUT);
  }

  public static WaitForResponse withAnyText(List<String> texts, int timeoutSeconds) {
    return new WaitForResponse(texts, timeoutSeconds);
  }

  public static WaitForResponse withAnyText(int timeoutSeconds, String... texts) {
    return new WaitForResponse(Arrays.asList(texts), timeoutSeconds);
  }
}
