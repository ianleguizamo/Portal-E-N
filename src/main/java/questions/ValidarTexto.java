package questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EvidenciaUtils;

public class ValidarTexto {

    private static final Logger log = LoggerFactory.getLogger(ValidarTexto.class);

    public static void contiene(Actor actor, String texto, String descripcion) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        boolean textoPresente = driver.getPageSource().contains(texto);

        if (textoPresente) {
            log.info("Texto validado correctamente: " + descripcion);
            EvidenciaUtils.registrarCaptura(descripcion + " - OK");
        } else {
            EvidenciaUtils.registrarCaptura(descripcion + " - TEXTO NO ENCONTRADO");
            throw new AssertionError("FALLO: No se encontro el texto esperado: '" + texto + "' en: " + descripcion);
        }
    }
}