package tasks.PortalEmpresas;

import static userinterfaces.CmaxPage.*;

import interactions.*;
import interactions.scroll.ScrollUserTable;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import interactions.SmartClick;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GestionDeUsuarios implements Task {

    private static final Logger log = Logger.getLogger(GestionDeUsuarios.class.getName());

    Map<String, String> data = new HashMap<>();

    public GestionDeUsuarios(Map<String, String> data) {
        this.data = data;
    }

    private static final String paso1 = "Selecciona Gestion de Usuario del menu hamburguesa";
    private static final String paso2 = "Selecciona crear usuario";
    private static final String paso3 = "Se valida descarga del documento usuarios";
    private static final String paso4 = "Documento de usuarios descargado";

    // Carpeta destino donde se guardara el documento descargado
    private static final String CARPETA_DESTINO = System.getProperty("user.dir")
            + File.separator + "usuariosdoc";

    // Carpeta de descargas del sistema
    private static final String CARPETA_DESCARGAS = System.getProperty("user.home")
            + File.separator + "Downloads";

    public static Performable gestionDeUsuarios(Map<String, String> data) {
        return Instrumented.instanceOf(GestionDeUsuarios.class).withProperties(data);
    }

    @Override
    @Step("Validar gestion de usuarios")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SmartClick.on(GESTION_USUARIOS),
                WaitForResponse.withTarget(CREAR_USUARIO)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                SmartClick.on(CREAR_USUARIO),
                WaitForResponse.withTarget(FLECHA_VOLVER_GU)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                SmartClick.on(FLECHA_VOLVER_GU),
                ScrollUserTable.by(300)
        );
        WaitFor.silencioso(4000);
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(SmartClick.on(BOTON_DESCARGAR));

        // Esperar que el archivo se descargue y moverlo a usuariosdoc
        WaitFor.silencioso(5000);
        moverArchivoDescargado();
        EvidenciaUtils.registrarCaptura(paso4);

        actor.attemptsTo(
                ScrollUserTable.by(-300),
                SmartClick.on(FLECHA_VOLVER_GU),
                SmartClick.on(MENU_DESPLEGABLE)
        );
    }

    @Step("Mover documento descargado a carpeta usuariosdoc")
    private void moverArchivoDescargado() {
        try {
            // Crear carpeta destino si no existe
            File carpetaDestino = new File(CARPETA_DESTINO);
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs();
                log.info("Carpeta creada: " + CARPETA_DESTINO);
            }

            // Buscar el archivo más reciente en Descargas
            File carpetaDescargas = new File(CARPETA_DESCARGAS);
            File[] archivos = carpetaDescargas.listFiles(archivo ->
                    archivo.isFile() &&
                            (archivo.getName().endsWith(".xlsx")
                                    || archivo.getName().endsWith(".xls")
                                    || archivo.getName().endsWith(".csv"))
            );

            if (archivos == null || archivos.length == 0) {
                log.warning("No se encontro ningun archivo descargado en: " + CARPETA_DESCARGAS);
                return;
            }

            // Tomar el archivo más reciente
            File archivoMasReciente = Arrays.stream(archivos)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElse(null);

            if (archivoMasReciente == null) {
                log.warning("No se pudo determinar el archivo mas reciente.");
                return;
            }

            // Mover el archivo a usuariosdoc
            File destino = new File(CARPETA_DESTINO + File.separator + archivoMasReciente.getName());
            Files.move(archivoMasReciente.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            log.info("Archivo movido correctamente a: " + destino.getAbsolutePath());

        } catch (Exception e) {
            log.severe("Error al mover el archivo descargado: " + e.getMessage());
        }
    }
}