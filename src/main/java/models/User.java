package models;

public class User {

  private String opcion;
  private String password;
  private String nombreUsuario;
  private String segmentacion;
  private String numero;
  private String documento;
  private String email2;
  private String password2;
  private String email;

  private String tipoPaquete;
  private String paqueteComprar;
  private String nombrePaquete;

  private String paquete;
  private String datos;
  private String minutos;
  private String precio;

  public String getNombrePaquete() {
    return nombrePaquete;
  }

  public void setNombrePaquete(String nombrePaquete) {
    this.nombrePaquete = nombrePaquete;
  }


  public String getPaquete() {
    return paquete;
  }

  public void setPaquete(String paquete) {
    this.paquete = paquete;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public String getOpcion() {
    return opcion;
  }

  public void setOpcion(String opcion) {
    this.opcion = opcion;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public void setSegmentacion(String segmentacion) {
    this.segmentacion = segmentacion;
  }

  public String getSegmentacion() {
    return segmentacion;
  }

  public String getEmail2() {
    return email2;
  }

  public void setEmail2(String email2) {
    this.email2 = email2;
  }

  public String getPassword2() {
    return password2;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  public String getTipoPaquete() {
    return tipoPaquete;
  }

  public void setTipoPaquete(String tipoPaquete) {
    this.tipoPaquete = tipoPaquete;
  }

  public String getPaqueteComprar() {
    return paqueteComprar;
  }

  public void setPaqueteComprar(String paqueteComprar) {
    this.paqueteComprar = paqueteComprar;
  }

  public String getDatos() {
    return datos;
  }

  public void setDatos(String datos) {
    this.datos = datos;
  }

  public String getMinutos() {
    return minutos;
  }

  public void setMinutos(String minutos) {
    this.minutos = minutos;
  }

  public String getPrecio() {
    return precio;
  }

  public void setPrecio(String precio) {
    this.precio = precio;
  }
}
