package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import cucumber.api.java.es.Dado;
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

    @Dado("^que el usuario abre la página$")
    public void abrirPortal() {
        OnStage.theActorCalled("Cortana").wasAbleTo(
                AbrirPagina.en("https://miclaroempresas.com.co/login")
        );

        // Cargar datos Excel UNA vez
        TestData.cargarDatos("src/test/resources/data/Usuario2.xlsx", "Datos", 1);
    }

    @When("^inicio de sesion$")
    public void seIngresaElUsuarioYLaContrasena() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight().attemptsTo(RealizarIngreso.realizarIngreso(datos));
    }

    @When("^Validaciones iniciales$")
    public void seIngresaElNumeroDeLaLineaAConsultarConsultaInicial() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(IngresarNumeroConsultaInicial.ingresarNumeroConsultaInicial(datos));
    }

    @When("^Ingreso al menu desplegable$")
    public void seIngresaAlMenuDesplegable() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(IngresarMenuDesplegable.ingresarMenuDesplegable(datos));
    }

    @When("^Valida mi cuenta$")
    public void seIngresaAMiCuenta() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(MiCuenta.miCuenta(datos));
    }

    @When("^Valida gestion de usuarios$")
    public void seIngresaAGestionDeUsuarios() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(GestionDeUsuarios.gestionDeUsuarios(datos));
    }

    @When("^Valida NITS del grupo$")
    public void NITS() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(NITS.nITS(datos));
    }

    @When("^ingreso a soluciones moviles PSE$")
    public void SolucionesMovilesPSE() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesPSE.solucionesMovilesPSE(datos));
    }

    @When("^ingreso a soluciones moviles Bancolombia$")
    public void SolucionesMovilesBancolombia() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesBancolombia.solucionesMovilesBancolombia(datos));
    }

    @When("^ingreso a soluciones moviles Tarjetas$")
    public void SolucionesMovilesTarjetas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesMovilesTarjetas.solucionesMovilesTarjetas(datos));
    }

    @When("^ingreso a soluciones fijas PSE$")
    public void SolucionesFijasPSE() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasPSE.solucionesFijasPSE(datos));
    }

    @When("^ingreso a soluciones fijas Bancolombia$")
    public void SolucionesFijasBancolombia() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasBancolombia.solucionesFijasBancolombia(datos));
    }

    @When("^ingreso a soluciones fijas tarjetas$")
    public void SolucionesFijasTarjetas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolucionesFijasTarjetas.solucionesFijasTarjetas(datos));
    }

    @When("^ingreso a tarjetas registradas$")
    public void TarjetasRegistradas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(TarjetasRegistradas.tarjetasRegistradas(datos));
    }

    @When("^pagar otras facturas$")
    public void PagarOtrasFacturas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(PagarOtrasFacturas.pagarOtrasFacturas(datos));
    }

    @When("^descarga tus facturas$")
    public void DescargaTusFacturas() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(DescargaTusFacturas.descargaTusFacturas(datos));
    }

    @When("^resumen graficos$")
    public void ResumenGraficos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ResumenGraficos.resumenGraficos(datos));
    }

    @When("^cambio de SIM$")
    public void CambioDeSIM() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(CambioDeSIM.cambioDeSIM(datos));
    }

    @When("^cambio de numero$")
    public void CambioDeNumero() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(CambioDeNumero.cambioDeNumero(datos));
    }

    @When("^actualizacion de datos$")
    public void ActualizacionDeDatos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ActualizacionDeDatos.actualizacionDeDatos(datos));
    }

    @When("^roaming internacional$")
    public void Roaming() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(Roaming.roaming(datos));
    }

    @When("^reposicion SIM$")
    public void ReposicionSIM() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ReposicionSIM.reposicionSIM(datos));
    }

    @When("^servicio tecnico$")
    public void ServicioTecnico() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ServicioTecnico.servicioTecnico(datos));
    }

    @When("^paquetes de datos$")
    public void PaquetesDatos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(PaquetesDatos.paquetesDatos(datos));
    }

    @When("^consultar consumos$")
    public void ConsultarConsumos() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(ConsultarConsumos.consultarConsumos(datos));
    }

    @When("^detalle del plan$")
    public void DetalleDelPlan() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(DetalleDelPlan.detalleDelPlan(datos));
    }

    @When("^detalle cuenta maestra$")
    public void DetalleCuentaMaestra() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(DetalleCuentaMaestra.detalleCuentaMaestra(datos));
    }

    @When("^solicitudes a domicilio$")
    public void SolicitudesDomicilio() {
        Map<String, String> datos = TestData.obtenerDatos();
        theActorInTheSpotlight()
                .attemptsTo(SolicitudesDomicilio.solicitudesDomicilio(datos));
    }
}


