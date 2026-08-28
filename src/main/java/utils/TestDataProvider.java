package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import models.DatosDePrueba;
import models.User;

/**
 * Lee los usuarios de prueba de real-user.json.
 *
 * <p>Mismo patron que el resto de proyectos (Jackson + POJO en models + este proveedor).
 * Sustituye a la lectura del Excel: los datos son texto plano revisable en un diff, y
 * anadir una cuenta ya no depende de acordarse de que fila del .xlsx le tocaba.
 */
public class TestDataProvider {

  private static final String RUTA = "src/test/resources/config/real-user.json";

  private TestDataProvider() {
    // Clase de utilidad
  }

  /** El primer usuario del archivo: el que se usa si nadie pide otro. */
  public static User getRealUser() {
    List<User> usuarios = leerUsuarios();
    return usuarios.get(0);
  }

  /**
   * El usuario cuyo alias coincide, para repartir escenarios entre cuentas.
   *
   * @throws IllegalStateException si no hay ninguno con ese alias
   */
  public static User getRealUser(String alias) {
    List<User> usuarios = leerUsuarios();

    return usuarios.stream()
        .filter(u -> alias.equalsIgnoreCase(u.getAlias()))
        .findFirst()
        .orElseThrow(
            () ->
                new IllegalStateException(
                    "No hay ningun usuario con alias '"
                        + alias
                        + "' en "
                        + RUTA
                        + ". Disponibles: "
                        + usuarios.stream().map(User::getAlias).collect(Collectors.joining(", "))));
  }

  private static List<User> leerUsuarios() {
    File archivo = new File(RUTA);

    try {
      DatosDePrueba datos = new ObjectMapper().readValue(archivo, DatosDePrueba.class);
      List<User> usuarios = datos.getUsuarios();

      if (usuarios == null || usuarios.isEmpty()) {
        throw new IllegalStateException(RUTA + " no tiene ningun usuario en la lista 'usuarios'");
      }

      return usuarios;

    } catch (IOException noSePudoLeer) {
      throw new IllegalStateException(
          "Error leyendo el archivo real-user.json (" + archivo.getAbsolutePath() + ")",
          noSePudoLeer);
    }
  }
}
