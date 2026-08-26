package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.CausaFallo;
import utils.CapturasPantallasWeb;
import utils.ContextoST;
import utils.WordWeb;

import java.util.ArrayList;
import java.util.List;

public class ReportHooksWeb {

    private static final List<String> pasosEjecutados = new ArrayList<>();
    private static String nombreFeature = "Sin tag";
    private static String ultimoPaso = "";

    public static void registrarPaso(String paso) {
        pasosEjecutados.add(paso);
        ultimoPaso = paso;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        EstadoPrueba.inicio = System.currentTimeMillis();

        // Estos registros son estaticos y sobreviven toda la corrida: sin limpiarlos, el
        // escenario hereda los datos y la captura de fallo del anterior.
        pasosEjecutados.clear();
        ultimoPaso = "";
        ContextoST.reiniciar();
        CapturasPantallasWeb.limpiarError();

        nombreFeature = scenario.getSourceTagNames()
                .stream()
                .filter(t -> t.startsWith("@"))
                .findFirst()
                .orElse("Sin tag")
                .replace("@", "");
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("[DEBUG] Se ejecutó afterScenario con escenario: " + scenario.getName());

        EstadoPrueba.fin = System.currentTimeMillis();
        long duracionTotalSegundos = (EstadoPrueba.fin - EstadoPrueba.inicio) / 1000;
        long minutos = duracionTotalSegundos / 60;
        long segundos = duracionTotalSegundos % 60;
        String duracionFormato = minutos + " min " + segundos + " seg";

        boolean fallo = scenario.isFailed();
        String estadoFinal = fallo ? "FAILED" : "PASSED";
        String pasoFallido = fallo ? (ultimoPaso.isEmpty() ? "Paso no identificado" : ultimoPaso) : null;

        // La captura del fallo se toma AQUI, antes de generar el informe y con el
        // navegador todavia abierto: es la evidencia de como quedo la pantalla.
        String motivoFallo = "";
        if (fallo) {
            CapturasPantallasWeb.capturarError();
            motivoFallo = CausaFallo.descripcionCorta();
        }

        // Contrato st-context: con que usuario y linea corrio el escenario, para las
        // alertas de Smart Tester. Va antes del Word para que un fallo del informe no
        // se lleve por delante el dato.
        ContextoST.registrarEscenario(scenario);

        System.out.println("Generando reporte Word con pasos: " + pasosEjecutados.size());

        WordWeb.generarReporte(
                scenario.getName(),
                pasosEjecutados.toArray(new String[0]),
                nombreFeature,
                duracionFormato,
                pasoFallido,
                estadoFinal,
                motivoFallo
        );

        // Limpiar capturas después de generar el Word
        CapturasPantallasWeb.limpiarCapturas();

        // Limpiar para el siguiente escenario
        pasosEjecutados.clear();
        nombreFeature = "Sin tag";
        ultimoPaso = "";
        EstadoPrueba.fallo = false;
        EstadoPrueba.pasoFallido = "";
    }
}