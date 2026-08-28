package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.java.en.Then;
import java.util.Map;
import tasks.PortalEmpresas.*;
import utils.TestData;

/**
 * Autogestion de la linea movil: SIM, numero, datos personales, roaming, servicio tecnico y consumos.
 */
public class SolucionesMovilesSteps {

    @Then("^el usuario realiza la solicitud de cambio de SIM$")
    public void CambioDeSIM() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(CambioDeSIM.cambioDeSIM(datos));
    }

    @Then("^el usuario realiza la solicitud de cambio de número$")
    public void CambioDeNumero() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(CambioDeNumero.cambioDeNumero(datos));
    }

    @Then("^el usuario actualiza su información de datos personales$")
    public void ActualizacionDeDatos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ActualizacionDeDatos.actualizacionDeDatos(datos));
    }

    @Then("^el usuario accede a la opción de roaming internacional$")
    public void Roaming() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(Roaming.roaming(datos));
    }

    @Then("^el usuario realiza la solicitud de reposición de SIM$")
    public void ReposicionSIM() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ReposicionSIM.reposicionSIM(datos));
    }

    @Then("^el usuario accede a la opción de servicio técnico$")
    public void ServicioTecnico() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ServicioTecnico.servicioTecnico(datos));
    }

    @Then("^el usuario visualiza los paquetes de datos disponibles$")
    public void PaquetesDatos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(PaquetesDatos.paquetesDatos(datos));
    }

    @Then("^el usuario consulta el detalle de sus consumos$")
    public void ConsultarConsumos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ConsultarConsumos.consultarConsumos(datos));
    }

    @Then("^el usuario visualiza el detalle de su plan activo$")
    public void DetalleDelPlan() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(DetalleDelPlan.detalleDelPlan(datos));
    }

    @Then("^el usuario visualiza el detalle de su cuenta maestra$")
    public void DetalleCuentaMaestra() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(DetalleCuentaMaestra.detalleCuentaMaestra(datos));
    }

    @Then("^el usuario accede a la opción de solicitudes a domicilio$")
    public void SolicitudesDomicilio() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolicitudesDomicilio.solicitudesDomicilio(datos));
    }
}
