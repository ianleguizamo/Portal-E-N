package models;

/**
 * Un usuario de pruebas de real-user.json.
 *
 * <p>Solo tiene los campos que la suite usa de verdad. La version anterior arrastraba
 * dieciseis campos copiados de otro proyecto (paquetes, recargas, IMEI...) que ninguna
 * clase leia y que ni siquiera coincidian con las claves del JSON.
 */
public class User {

  /** Nombre con el que se elige este usuario; ver TestData.cargarDatos. */
  private String alias;

  /** Correo con el que se inicia sesion en el portal. */
  private String usuario;

  private String contrasena;

  /** Linea asociada. Puede ir vacia: solo se usa para el contexto de Smart Tester. */
  private String numero;

  public User() {
    // Requerido por Jackson
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }
}
