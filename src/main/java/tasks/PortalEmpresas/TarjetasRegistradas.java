package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import utils.CerrarEncuestaQualtrics;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.Map;

public class TarjetasRegistradas implements Task {

    Map<String, String> data = new HashMap<>();

    private static final String paso2 = "Selecciona Tarjetas registradas";
    private static final String paso3 = "Clic en boton registrar nueva tarjeta";

    public TarjetasRegistradas(Map<String, String> data) {
        this.data = data;
    }

    public static Performable tarjetasRegistradas(Map<String, String> data) {
        return Instrumented.instanceOf(TarjetasRegistradas.class)
                .withProperties(data);
    }

    @Override
    @Step("Validar tarjetas registradas")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                SmartClick.on(TARJETAS_REGISTRADAS)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        WaitFor.silencioso(3000);

        CerrarEncuestaQualtrics.siAparece(actor);

        WaitFor.silencioso(5000);

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.switchTo().frame("my_iframe");

        WaitFor.silencioso(2000);

        actor.attemptsTo(
                JavaScriptSmartClick.on(BOTON_SUMAR_TARJETA),
                WaitForResponse.withTarget(LABEL_NRO_DOCUMENTO_TITULAR)
        );
        EvidenciaUtils.registrarCaptura(paso3);
    }
}