package tasks.PortalEmpresas.pagos;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

/**
 * Comprueba que la pestana de la pasarela muestre el contenido esperado y deja la
 * evidencia correspondiente.
 *
 * <p>Nunca falla el escenario: la validacion es informativa (queda en el log y en el
 * titulo de la captura), igual que en las Tasks de pago originales. Si en algun momento
 * se quiere que un contenido inesperado tumbe la prueba, el cambio se hace aqui una sola
 * vez y aplica a los diez metodos de pago.
 */
public class ValidarVentanaDePago implements Task {

  private static final Logger LOG = LoggerFactory.getLogger(ValidarVentanaDePago.class);

  /** Presente en todas las pasarelas, con y sin tilde segun el proveedor. */
  private static final List<String> TEXTOS_COMUNES =
      Arrays.asList("Número de Factura", "Numero de Factura");

  private static final String SUFIJO_NO_ENCONTRADO = " - CONTENIDO NO ENCONTRADO";

  private final String paso;
  private final List<String> textosEsperados;

  public ValidarVentanaDePago(String paso, List<String> textosEsperados) {
    this.paso = paso;
    this.textosEsperados = textosEsperados;
  }

  /**
   * @param paso titulo de la evidencia
   * @param textosPropios textos especificos del metodo de pago ("PSE", "Bancolombia"...);
   *     los textos comunes de factura se agregan siempre
   */
  public static Performable con(String paso, String... textosPropios) {
    List<String> esperados = new ArrayList<>(Arrays.asList(textosPropios));
    esperados.addAll(TEXTOS_COMUNES);
    return instrumented(ValidarVentanaDePago.class, paso, esperados);
  }

  @Override
  @Step("#paso")
  public <T extends Actor> void performAs(T actor) {
    String contenido = BrowseTheWeb.as(actor).getDriver().getPageSource();

    boolean encontrado = textosEsperados.stream().anyMatch(contenido::contains);

    if (encontrado) {
      LOG.info("Ventana de confirmacion validada: {}", paso);
      EvidenciaUtils.registrarCaptura(paso);
    } else {
      LOG.warn("No se encontro el contenido esperado {} en: {}", textosEsperados, paso);
      EvidenciaUtils.registrarCaptura(paso + SUFIJO_NO_ENCONTRADO);
    }
  }
}
