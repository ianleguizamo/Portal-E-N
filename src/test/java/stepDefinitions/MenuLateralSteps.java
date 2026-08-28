package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import java.util.Map;
import tasks.PortalEmpresas.*;
import utils.TestData;

/**
 * Menu desplegable: mi cuenta, gestion de usuarios, NITs, consultor, documentacion y centro de ayuda.
 */
public class MenuLateralSteps {

    @And("^el usuario ingresa al menú desplegable$")
    public void seIngresaAlMenuDesplegable() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(IngresarMenuDesplegable.ingresarMenuDesplegable(datos));
    }

    @Then("^el usuario valida la información de su cuenta$")
    public void seIngresaAMiCuenta() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(MiCuenta.miCuenta(datos));
    }

    @Then("^el usuario valida la gestión de usuarios$")
    public void seIngresaAGestionDeUsuarios() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(GestionDeUsuarios.gestionDeUsuarios(datos));
    }

    @Then("^el usuario valida los NITs del grupo empresarial$")
    public void NITS() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(NITS.nITS(datos));
    }

    @Then("^el usuario visualiza la información del consultor asignado$")
    public void ConsultorAsignado() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ConsultorAsignado.consultorAsignado(datos));
    }

    @Then("^el usuario accede a la documentación de Claro Colombia$")
    public void DocumentacionClaroColombia() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(DocumentacionClaroColombia.documentacionClaroColombia(datos));
    }

    @Then("^el usuario navega por todas las opciones del centro de ayuda$")
    public void CentroDeAyuda() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(CentroDeAyuda.centroDeAyuda(datos));
    }
}
