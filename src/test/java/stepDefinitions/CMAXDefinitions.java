package stepDefinitions;

import cucumber.api.DataTable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import freemarker.log.Logger;
import hooks.ReportHooksWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import tasks.PortalEmpresas.*;
import utils.*;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CMAXDefinitions {

    DataDrivenExcel dataDriverExcel = new DataDrivenExcel();
    Map<String, String> data = new HashMap<>();
    private String URL;
    private EnvironmentVariables environmentVariables;

    private ChromeDriver driver;
    private static final Logger LOGGER = Logger.getLogger(CMAXDefinitions.class.getName());

    @Managed
    private Actor user = Actor.named("user");

    @Before
    public void getConfiguration() {

        OnStage.setTheStage(new OnlineCast());
        URL = EnviaromentProperties.getProperty("url_prueba");
        LOGGER.info("Limpiando carpeta de capturas...");
        File folder = new File("Capturas");

        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    file.delete();
                }
            }
        }
    }

    @Given("^Se ingresa a la URL de CMAX (\\d+)$")
    public void seIngresaALaURLDeCMAX(int row, DataTable dataExcel) throws Exception {
        data = ExcelLoader.cargarDatosDesdeExcel(dataExcel, row);

        URL = environmentVariables.getProperty("url_prueba");


        if (URL == null || URL.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL de prueba no está definida.");
        }

        theActorCalled("cmax").attemptsTo(Open.url(URL));
    }




    @When("^Se ingresa el numero de la linea en consulta inicial$")
    public void seIngresaElNumeroDeLaLineaAConsultarConsultaInicial() {
        ReportHooksWeb.setLineaDesdeData(data.get("Numero"));
        theActorInTheSpotlight()
                .attemptsTo(IngresarNumeroConsultaInicial.ingresarNumeroConsultaInicial(data));
    }




}