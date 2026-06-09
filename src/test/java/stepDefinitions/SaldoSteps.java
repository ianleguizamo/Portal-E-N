package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.PortalEmpresas.*;
import utils.DataDrivenExcel;
import utils.TestData;

import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SaldoSteps {

    private DataDrivenExcel dataDriverExcel = new DataDrivenExcel();

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que el usuario abre el portal de Claro Empresas$")
    public void abrirPortal() {
        OnStage.theActorCalled("Usuario").wasAbleTo(
                AbrirPagina.en("https://miclaroempresas.com.co/login")
        );
        TestData.cargarDatos("src/test/resources/data/Usuario2.xlsx", "Datos", 1);
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
    public void SolucionesFijasGooglePlay() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasGooglePlay.solucionesFijasGooglePlay(datos));
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