package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Raiz de real-user.json.
 *
 * <p>Los demas proyectos guardan un unico usuario plano. Aqui se anida bajo "usuarios"
 * porque la suite necesita poder repartir escenarios entre varias cuentas y no saturar
 * las sesiones de una sola: anadir un usuario es anadir un objeto a la lista.
 */
public class DatosDePrueba {

  private List<User> usuarios = new ArrayList<>();

  public List<User> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(List<User> usuarios) {
    this.usuarios = usuarios;
  }
}
