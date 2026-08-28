package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Map;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.PortalEmpresas.*;
import utils.TestData;

/**
 * Apertura del portal, login y redireccionamientos: la entrada comun a todo escenario.
 */
public class SesionSteps {

    // Venia de SaldoSteps. Monta el escenario de Screenplay antes de cada escenario, asi
    // que tiene que seguir estando en el glue: sin esto no hay actor en escena.
    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que el usuario abre el portal de Claro Empresas$")
    public void abrirPortal() {
        OnStage.theActorCalled("Usuario").wasAbleTo(
                AbrirPagina.en("https://miclaroempresas.com.co/login")
        );
        TestData.cargarDatos();
    }

    @When("^el usuario inicia sesión con sus credenciales$")
    public void seIngresaElUsuarioYLaContrasena() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight().attemptsTo(RealizarIngreso.realizarIngreso(datos));
    }

    @Then("^el sistema redirige correctamente al usuario$")
    public void seRedireccionamientos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(Redireccionamientos.redireccionamientos(datos));
    }
}
