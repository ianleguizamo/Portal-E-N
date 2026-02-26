package interactions;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class WaitForResponse implements Interaction {

    private final List<String> expectedTexts;
    private final int timeout;
    private static final int DEFAULT_TIMEOUT = 120;

    // NUEVO
    private final Target target;

    /* ===== CONSTRUCTORES ===== */

    // ORIGINAL (NO MODIFICADO)
    public WaitForResponse(List<String> expectedTexts, int timeout) {
        this.expectedTexts = expectedTexts;
        this.timeout = timeout;
        this.target = null;
    }

    // NUEVO (Target)
    public WaitForResponse(Target target, int timeout) {
        this.target = target;
        this.timeout = timeout;
        this.expectedTexts = null;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriverWait wait = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), timeout);
        boolean found = false;
        long startTime = System.currentTimeMillis();

        while ((System.currentTimeMillis() - startTime) < timeout * 1000 && !found) {

            // ===== NUEVO: espera por Target (FORMA CORRECTA) =====
            if (target != null) {
                try {
                    if (target.resolveFor(actor).isPresent()) {
                        found = true;
                        break;
                    }
                } catch (Exception ignored) {
                }
            }

            // ===== LÓGICA ORIGINAL (NO TOCADA) =====
            for (String text : expectedTexts) {
                try {
                    By locator = MobileBy.AndroidUIAutomator(
                            String.format("new UiSelector().textContains(\"%s\")", text)
                    );
                    List<WebElement> elements =
                            BrowseTheWeb.as(actor).getDriver().findElements(locator);

                    if (!elements.isEmpty()) {
                        found = true;
                        break;
                    }
                } catch (Exception ignored) {
                }
            }
        }

        if (!found) {
            throw new RuntimeException(
                    "Ninguno de los textos esperados fue encontrado en el tiempo dado."
            );
        }
    }

    /* ===== FACTORY METHODS ORIGINALES ===== */

    public static WaitForResponse withText(String text, int timeoutSeconds) {
        return new WaitForResponse(Arrays.asList(text), timeoutSeconds);
    }

    public static WaitForResponse withAnyText(List<String> texts, int timeoutSeconds) {
        return new WaitForResponse(texts, timeoutSeconds);
    }

    public static WaitForResponse withAnyText(int timeoutSeconds, String... texts) {
        return new WaitForResponse(Arrays.asList(texts), timeoutSeconds);
    }

    public static WaitForResponse withText(String text) {
        return new WaitForResponse(Arrays.asList(text), DEFAULT_TIMEOUT);
    }

    public static WaitForResponse withAnyText(String... texts) {
        return new WaitForResponse(Arrays.asList(texts), DEFAULT_TIMEOUT);
    }

    public static WaitForResponse withAnyText(List<String> texts) {
        return new WaitForResponse(texts, DEFAULT_TIMEOUT);
    }

    /* ===== NUEVOS FACTORY METHODS (Target) ===== */

    public static WaitForResponse withTarget(Target target, int timeoutSeconds) {
        return new WaitForResponse(target, timeoutSeconds);
    }

    public static WaitForResponse withTarget(Target target) {
        return new WaitForResponse(target, DEFAULT_TIMEOUT);
    }
}
