package utils;

import static utils.Constants.*;

import interactions.WaitFor;
import interactions.comunes.ClickElementByText;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CompraPaqueteGenerico extends AndroidObject implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompraPaqueteGenerico.class);
    private final Map<String, String> data;

    public CompraPaqueteGenerico(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String nombrePaqueteEsperado = data.get("nombrePaquete");
        LOGGER.info("Buscando paquete: '{}'", nombrePaqueteEsperado);

        // Buscar el paquete por coincidencias clave
        if (buscarYSeleccionarPaquete(actor, nombrePaqueteEsperado)) {
            procesarCompraPaquete(actor, nombrePaqueteEsperado);
        } else {
            throw new AssertionError("No se encontró el paquete: " + nombrePaqueteEsperado);
        }
    }

    private boolean buscarYSeleccionarPaquete(Actor actor, String nombreEsperado) {
        // Extraer palabras clave del nombre esperado
        String[] palabrasClave = extraerPalabrasClave(nombreEsperado);

        // Buscar elementos de paquetes en pantalla
        Target paquetes = Target.the("Paquetes disponibles")
                .located(By.xpath("//*[contains(@text,'GB') or contains(@text,'MB') or contains(@text,'Incluido')]"));

        List<WebElementFacade> elementosPaquetes = paquetes.resolveAllFor(actor);

        for (WebElementFacade elemento : elementosPaquetes) {
            String textoPaquete = elemento.getText();

            if (coincidePaquete(textoPaquete, palabrasClave)) {
                LOGGER.info("Paquete encontrado: '{}' coincide con '{}'", textoPaquete, nombreEsperado);

                // Buscar botón "Ver detalle" o "Comprar" asociado
                if (buscarYHacerClicDetalle(actor, elemento)) {
                    return true;
                }
            }
        }

        return false;
    }

    private String[] extraerPalabrasClave(String nombre) {
        // Extraer: GB/MB, números, "Todo Incluido", "Minutos", etc.
        return nombre.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", " ")
                .split("\\s+");
    }

    private boolean coincidePaquete(String textoPaquete, String[] palabrasClave) {
        String textoLower = textoPaquete.toLowerCase();

        int coincidencias = 0;
        for (String palabra : palabrasClave) {
            if (palabra.length() > 2 && textoLower.contains(palabra)) {
                coincidencias++;
            }
        }

        // Requiere al menos 2 coincidencias significativas
        return coincidencias >= 2;
    }

    private boolean buscarYHacerClicDetalle(Actor actor, WebElementFacade elementoPaquete) {
        try {
            // Buscar botón "Ver detalle del paquete" cerca del elemento
            actor.attemptsTo(ClickElementByText.clickElementByText(VER_DETALLE_DEL_PAQUETE));
            return true;
        } catch (Exception e) {
            LOGGER.debug("No se encontró botón 'Ver detalle' para este paquete");
            return false;
        }
    }

    private void procesarCompraPaquete(Actor actor, String nombrePaquete) {
        // Validar que estamos en el paquete correcto
        actor.attemptsTo(WaitFor.aTime(2000));

        // Capturar evidencia
        CapturaDePantallaMovil.tomarCapturaPantalla("detalle_paquete_" + nombrePaquete);

        // Hacer clic en Comprar
        actor.attemptsTo(
                ClickElementByText.clickElementByText(COMPRAR),
                WaitFor.aTime(2000)
        );

        LOGGER.info("Compra iniciada para paquete: '{}'", nombrePaquete);
    }

}