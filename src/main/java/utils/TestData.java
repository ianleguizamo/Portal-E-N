package utils;

import java.util.HashMap;
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

  private TestData() {
    // Clase de utilidad
  }

  /**
   * Carga el usuario indicado por la propiedad {@code -Dusuario}; si no se indica ninguna,
   * el primero de real-user.json.
   */
  public static void cargarDatos() {
    String alias = System.getProperty(PROPIEDAD_ALIAS);

    User usuario =
        (alias == null || alias.trim().isEmpty())
            ? TestDataProvider.getRealUser()
            : TestDataProvider.getRealUser(alias.trim());

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
