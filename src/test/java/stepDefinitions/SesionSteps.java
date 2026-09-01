package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Map;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.PortalEmpresas.*;
import utils.ConfigurarDriver;
import utils.TestData;

/**
 * Apertura del portal, login y redireccionamientos: la entrada comun a todo escenario.
 */
public class SesionSteps {

    private java.util.Collection<String> tagsDelEscenario =
            java.util.Collections.emptyList();

    // Venia de SaldoSteps. Monta el escenario de Screenplay antes de cada escenario, asi
    // que tiene que seguir estando en el glue: sin esto no hay actor en escena.
    @Before
    public void prepararEscenario(Scenario scenario) {
        // Antes de que se abra el navegador: deja listo el chromedriver que
        // corresponde al Chrome instalado. Es idempotente, solo trabaja la 1a vez.
        ConfigurarDriver.chrome();

        OnStage.setTheStage(new OnlineCast());

        // Los tags del escenario deciden con que cuenta se corre; se guardan
        // aqui porque el paso que carga los datos no recibe el Scenario.
        tagsDelEscenario = scenario.getSourceTagNames();
    }

    @Given("^que el usuario abre el portal de Claro Empresas$")
    public void abrirPortal() {
        OnStage.theActorCalled("Usuario").wasAbleTo(
                AbrirPagina.en("https://miclaroempresas.com.co/login")
        );
        TestData.cargarDatos(tagsDelEscenario);
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
