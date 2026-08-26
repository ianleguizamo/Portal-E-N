package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.Scenario;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Deja constancia, escenario por escenario, de CON QUE DATOS se corrio la prueba: con que usuario
 * (correo) y sobre que linea. Es el lado "proyecto" del contrato st-context con Smart Tester.
 *
 * <p>Como funciona: al terminar cada escenario se escribe UN archivo JSON en {@code
 * target/st-context/}. El orquestador lee esa carpeta antes de archivar el workspace, la mete en
 * report_metadata.json y la publica en /api/status; Smart Tester la convierte en las variables de
 * plantilla {@code {{correoPrueba}}} y {@code {{lineaPrueba}}}.
 *
 * <p>Aqui los datos NO salen de real-user.json (ese archivo existe pero no lo lee nadie): salen de
 * la fila del Excel que carga {@link TestData}, con las columnas Usuario / Contrasena / Numero. Se
 * registran al cargarla y {@code loginConfirmado} se marca cuando el portal deja entrar de verdad
 * (ver RealizarIngreso), para no afirmar que se uso una cuenta con la que nunca se pudo entrar.
 *
 * <p>REGLA: la columna Contrasena NO se escribe aqui. Este archivo viaja a Smart Tester y su
 * contenido termina en mensajes de WhatsApp/Teams.
 */
public class ContextoST {

  private static final String CARPETA = "target/st-context";

  private static String correo = null;
  private static String linea = null;
  private static boolean loginConfirmado = false;

  private ContextoST() {}

  /** Arranque de escenario: olvidar lo del anterior (los estaticos sobreviven la corrida). */
  public static synchronized void reiniciar() {
    correo = null;
    linea = null;
    loginConfirmado = false;
  }

  /** La fila de datos con la que se configuro el escenario (la carga TestData.cargarDatos). */
  public static synchronized void registrarDatos(Map<String, String> datos) {
    if (datos == null) {
      return;
    }
    correo = valor(datos, "Usuario", "usuario", "Correo", "correo", "email");
    linea = valor(datos, "Numero", "numero", "Linea", "linea");
  }

  /** El portal dejo entrar con esos datos: el correo pasa de "configurado" a "confirmado". */
  public static synchronized void confirmarLogin() {
    loginConfirmado = true;
  }

  /** Lo que identifica esta prueba: la linea si la hay, si no el correo. "" si no hay nada. */
  public static synchronized String identificacionUsada() {
    if (linea != null && !linea.trim().isEmpty()) {
      return linea.trim();
    }
    return correo == null ? "" : correo.trim();
  }

  /**
   * Escribe el contexto del escenario que acaba de terminar. Nunca lanza: si algo falla, el
   * escenario no se entera (esto es telemetria, no parte de la prueba).
   */
  public static synchronized void registrarEscenario(Scenario scenario) {
    try {
      List<String> tags = new ArrayList<>();
      if (scenario != null && scenario.getSourceTagNames() != null) {
        tags.addAll(scenario.getSourceTagNames());
      }

      Map<String, Object> datos = new LinkedHashMap<>();
      datos.put("escenario", scenario == null ? null : scenario.getName());
      datos.put("tags", tags);
      datos.put("correo", correo);
      datos.put("linea", linea);
      datos.put("loginConfirmado", loginConfirmado);
      datos.put("resultado", scenario != null && scenario.isFailed() ? "FAILED" : "PASSED");
      datos.put("registradoEn", LocalDateTime.now().toString());

      escribir(datos);

      System.out.println(
          "[ContextoST] Escenario registrado | correo="
              + correo
              + " | linea="
              + linea
              + (loginConfirmado ? "" : " (sin login confirmado)"));

    } catch (Exception e) {
      System.err.println("[ContextoST] No se pudo registrar el contexto del escenario: " + e);
    }
  }

  /** Primer valor no vacio entre varios nombres de columna posibles. */
  private static String valor(Map<String, String> datos, String... claves) {
    for (String clave : claves) {
      String v = datos.get(clave);
      if (v != null && !v.trim().isEmpty()) {
        return v.trim();
      }
    }
    return null;
  }

  private static void escribir(Map<String, Object> datos) throws Exception {
    Path carpeta = Paths.get(CARPETA);
    Files.createDirectories(carpeta);
    String nombre =
        System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8) + ".json";
    File destino = carpeta.resolve(nombre).toFile();
    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(destino, datos);
  }
}
