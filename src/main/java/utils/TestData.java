package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import models.User;
import net.serenitybdd.core.Serenity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Carga el usuario de la corrida y lo deja disponible para cualquier paso. */
public class TestData {

  private static final Logger LOG = LoggerFactory.getLogger(TestData.class);

  private static final String VARIABLE_SESION = "datosUsuario";

  /** Permite elegir cuenta sin tocar codigo: gradlew test -Dusuario=secundario */
  private static final String PROPIEDAD_ALIAS = "usuario";

  /** Tag del feature que elige cuenta: @usuario_secundario -> alias "secundario". */
  private static final String PREFIJO_TAG = "usuario_";

  private TestData() {
    // Clase de utilidad
  }

  /**
   * Carga el usuario indicado por la propiedad {@code -Dusuario}; si no se indica ninguna,
   * el primero de real-user.json.
   */
  public static void cargarDatos() {
    cargarDatos(Collections.emptyList());
  }

  /**
   * Carga el usuario del escenario.
   *
   * <p>Prioridad: {@code -Dusuario} manda sobre todo (util para una corrida puntual);
   * si no, el tag {@code @usuario_<alias>} del escenario; si tampoco, el primero de
   * real-user.json.
   *
   * <p>La via del tag existe porque Smart Tester lanza un comando fijo, tomado de
   * projects.json, y no se le puede pasar una propiedad distinta por escenario. Marcando
   * el .feature se reparte la carga entre cuentas sin tocar nada en ST.
   *
   * @param tagsDelEscenario tags tal como los da Cucumber, con la arroba incluida
   */
  public static void cargarDatos(Collection<String> tagsDelEscenario) {
    String alias = aliasDesdePropiedad();

    if (alias == null) {
      alias = aliasDesdeTags(tagsDelEscenario);
    }

    User usuario =
        (alias == null) ? TestDataProvider.getRealUser() : TestDataProvider.getRealUser(alias);

    LOG.info("Escenario ejecutandose con el usuario '{}'", usuario.getAlias());

    Serenity.setSessionVariable(VARIABLE_SESION).to(comoMapa(usuario));

    // Contrato st-context: con que usuario y linea corrio el escenario.
    ContextoST.registrarDatos(comoMapa(usuario));
  }

  /**
   * Los datos del usuario en el formato que esperan las Tasks.
   *
   * <p>Se mantiene el mapa de claves en vez de pasar el {@link User} directamente porque
   * mas de treinta Tasks reciben este parametro y solo RealizarIngreso lo lee; cambiarles
   * la firma a todas seria mucho movimiento para ningun beneficio. Las claves son las
   * mismas que tenia el Excel, asi que ni las Tasks ni ContextoST notan el cambio.
   */
  private static String aliasDesdePropiedad() {
    String alias = System.getProperty(PROPIEDAD_ALIAS);
    return (alias == null || alias.trim().isEmpty()) ? null : alias.trim();
  }

  private static String aliasDesdeTags(Collection<String> tags) {
    if (tags == null) {
      return null;
    }

    return tags.stream()
        .map(t -> t.startsWith("@") ? t.substring(1) : t)
        .filter(t -> t.toLowerCase(Locale.ROOT).startsWith(PREFIJO_TAG))
        .map(t -> t.substring(PREFIJO_TAG.length()))
        .filter(a -> !a.isEmpty())
        .findFirst()
        .orElse(null);
  }

  private static Map<String, String> comoMapa(User usuario) {
    Map<String, String> datos = new HashMap<>();
    datos.put("Usuario", texto(usuario.getUsuario()));
    datos.put("Contrasena", texto(usuario.getContrasena()));
    datos.put("Numero", texto(usuario.getNumero()));
    return datos;
  }

  private static String texto(String valor) {
    return valor == null ? "" : valor;
  }

  /** Los datos del usuario de la corrida, desde cualquier Step. */
  @SuppressWarnings("unchecked")
  public static Map<String, String> obtenerDatos() {
    return Serenity.sessionVariableCalled(VARIABLE_SESION);
  }
}
