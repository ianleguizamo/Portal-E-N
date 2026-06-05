package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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

        // Extrae el primer tag del escenario
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

        System.out.println("Generando reporte Word con pasos: " + pasosEjecutados.size());

        WordWeb.generarReporte(
                scenario.getName(),
                pasosEjecutados.toArray(new String[0]),
                nombreFeature,
                duracionFormato,
                pasoFallido,
                estadoFinal
        );

        // Limpiar para el siguiente escenario
        pasosEjecutados.clear();
        nombreFeature = "Sin tag";
        ultimoPaso = "";
        EstadoPrueba.fallo = false;
        EstadoPrueba.pasoFallido = "";
    }
}