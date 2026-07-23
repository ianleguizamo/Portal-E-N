package utils;

import static userinterfaces.CmaxPage.*;

import interactions.SmartClick;
import interactions.WaitFor;
import net.serenitybdd.screenplay.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CerrarEncuestaQualtrics {

    private static final Logger log = LoggerFactory.getLogger(CerrarEncuestaQualtrics.class);

    private static final String pasoEncuesta = "Validacion encuesta Qualtrics";

    public static void siAparece(Actor actor) {
        try {
            if (BOTON_CERRAR_ENCUESTA.resolveFor(actor).isPresent()) {
                actor.attemptsTo(SmartClick.on(BOTON_CERRAR_ENCUESTA));
                log.info("Encuesta Qualtrics cerrada correctamente");
                WaitFor.silencioso(3000);
                EvidenciaUtils.registrarCaptura(pasoEncuesta + " - CERRADA");
            } else {
                log.info("La encuesta Qualtrics no aparecio, continuando sin cerrarla");
                EvidenciaUtils.registrarCaptura(pasoEncuesta + " - NO APARECIO");
            }
        } catch (Exception e) {
            log.info("No se pudo verificar la encuesta Qualtrics, continuando: " + e.getMessage());
            EvidenciaUtils.registrarCaptura(pasoEncuesta + " - ERROR DE VERIFICACION");
        }
    }
}