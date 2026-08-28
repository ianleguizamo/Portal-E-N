package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.java.en.Then;
import java.util.Map;
import tasks.PortalEmpresas.*;
import utils.TestData;

/**
 * Pagos en linea: metodos de pago de soluciones moviles y fijas, tarjetas y otras cuentas.
 */
public class PagosSteps {

    @Then("^el usuario accede a pagos de soluciones móviles por PSE$")
    public void SolucionesMovilesPSE() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesPSE.solucionesMovilesPSE(datos));
    }

    @Then("^el usuario accede a pagos de soluciones móviles por Bancolombia$")
    public void SolucionesMovilesBancolombia() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesBancolombia.solucionesMovilesBancolombia(datos));
    }

    @Then("^el usuario accede a pagos de soluciones móviles con tarjeta$")
    public void SolucionesMovilesTarjetas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesTarjetas.solucionesMovilesTarjetas(datos));
    }

    @Then("^el usuario accede a pagos de soluciones móviles con Google Play$")
    public void SolucionesMovilesGooglePlay() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesGooglePlay.solucionesMovilesGooglePlay(datos));
    }

    @Then("^el usuario accede a pagos de soluciones móviles con Tarjeta Codensa$")
    public void SolucionesMovilesTarjetaCodensa() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesTarjetaCodensa.solucionesMovilesTarjetaCodensa(datos));
    }

    @Then("^el usuario accede a pagos de soluciones fijas por PSE$")
    public void SolucionesFijasPSE() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasPSE.solucionesFijasPSE(datos));
    }

    @Then("^el usuario accede a pagos de soluciones fijas por Bancolombia$")
    public void SolucionesFijasBancolombia() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasBancolombia.solucionesFijasBancolombia(datos));
    }

    @Then("^el usuario accede a pagos de soluciones fijas con tarjeta$")
    public void SolucionesFijasTarjetas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasTarjetas.solucionesFijasTarjetas(datos));
    }

    @Then("^el usuario accede a pagos de soluciones fijas con google play$")
    public void SolucionesFijasGooglePlay() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasGooglePlay.solucionesFijasGooglePlay(datos));
    }

    @Then("^el usuario accede a pagos de soluciones fijas con tarjeta codensa$")
    public void SolucionesFijasTarjetaCodensa() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasTarjetaCodensa.solucionesFijasTarjetaCodensa(datos));
    }

    @Then("^el usuario visualiza las tarjetas registradas en su cuenta$")
    public void TarjetasRegistradas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(TarjetasRegistradas.tarjetasRegistradas(datos));
    }

    @Then("^el usuario accede a la opción de pagar otras facturas$")
    public void PagarOtrasFacturas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(PagarOtrasFacturas.pagarOtrasFacturas(datos));
    }

    @Then("^el usuario accede a la opción Pago automático de facturas$")
    public void PagoAutoFacturas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(PagosAutoFacturasSolucionesMoviles.pagosAutoFacturas(datos));
    }
}
