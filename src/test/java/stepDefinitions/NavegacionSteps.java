package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.java.en.And;
import tasks.PortalEmpresas.navegacion.IrAPagosEnLinea;

/**
 * Pasos de navegacion compartidos entre features.
 *
 * <p>Van aparte de los pasos de negocio porque los usan varios escenarios de features
 * distintos; mantenerlos aqui evita que un cambio de navegacion obligue a tocar la clase
 * de pasos de cada modulo.
 */
public class NavegacionSteps {

  @And("^el usuario ingresa a Pagos en línea$")
  public void ingresarAPagosEnLinea() {
    theActorInTheSpotlight().attemptsTo(IrAPagosEnLinea.ahora());
  }
}
