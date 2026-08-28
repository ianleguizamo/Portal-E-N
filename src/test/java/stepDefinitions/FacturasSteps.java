package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.java.en.Then;
import java.util.Map;
import tasks.PortalEmpresas.*;
import utils.TestData;

/**
 * Consulta tus facturas: descarga y resumen grafico de consumos.
 */
public class FacturasSteps {

    @Then("^el usuario descarga sus facturas correctamente$")
    public void DescargaTusFacturas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(DescargaTusFacturas.descargaTusFacturas(datos));
    }

    @Then("^el usuario visualiza el resumen gráfico de sus consumos$")
    public void ResumenGraficos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ResumenGraficos.resumenGraficos(datos));
    }
}
