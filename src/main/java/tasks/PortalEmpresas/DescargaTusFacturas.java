package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CerrarEncuestaQualtrics;
import utils.EvidenciaUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescargaTusFacturas implements Task {

    private static final Logger log = LoggerFactory.getLogger(DescargaTusFacturas.class);

    Map<String, String> data = new HashMap<>();

    private static final String paso1 = "Selecciona Consulta tus facturas";
    private static final String paso2 = "Selecciona Descarga tu factura";
    private static final String paso3 = "Selecciona Ver factura";

    public DescargaTusFacturas(Map<String, String> data) {
        this.data = data;
    }

    public static Performable descargaTusFacturas(Map<String, String> data) {
        return Instrumented.instanceOf(DescargaTusFacturas.class)
                .withProperties(data);
    }

    @Override
    @Step("Validar descarga de facturas")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                SmartClick.on(CONSULTA_TUS_FACTURAS),
                WaitForResponse.withTarget(DESCARGA_TU_FACTURA)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                SmartClick.on(DESCARGA_TU_FACTURA)
        );

        WaitFor.silencioso(8000);
        EvidenciaUtils.registrarCaptura(paso2);

        // DEBUG: listar iframes y verificar encuesta
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        log.info("Cantidad de iframes encontrados: " + iframes.size());
        for (WebElement iframe : iframes) {
            log.info("iframe -> id: " + iframe.getAttribute("id")
                    + " | name: " + iframe.getAttribute("name")
                    + " | src: " + iframe.getAttribute("src"));
        }

        boolean contieneVerFactura = driver.getPageSource().contains("btnBillRegister");
        boolean contieneEncuesta = driver.getPageSource().contains("Tu opinion");
        log.info("¿Contiene boton ver factura en DOM principal? " + contieneVerFactura);
        log.info("¿Contiene texto encuesta en DOM principal? " + contieneEncuesta);

        CerrarEncuestaQualtrics.siAparece(actor);
    }
}