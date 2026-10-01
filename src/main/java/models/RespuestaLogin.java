package models;

/**
 * Lo que respondio el portal al pulsar "Ingresar".
 *
 * <p>Se lee de que modal queda VISIBLE, no de su texto: la pagina de login trae todos sus
 * modales de error en el DOM desde que carga, ocultos, asi que buscar "Algo salio mal al
 * procesar tu solicitud" daba positivo siempre. Y la captura tampoco basta, porque el modal
 * del error 500 y el del reCAPTCHA (429) muestran exactamente el mismo texto.
 *
 * <p>Las descripciones acaban en el mensaje de error que analiza Smart Tester. Su regla
 * "captcha-bloquea-automatizacion" manda a fallo de automatizacion cualquier mensaje que
 * contenga "captcha", sin consultar a la IA: esa palabra solo puede aparecer cuando de verdad
 * fue el reCAPTCHA. Por decir "probable captcha" ante cualquier error, la caida del portal del
 * 1-oct-2026 quedo clasificada como fallo de desarrollo.
 */
public enum RespuestaLogin {

  /** Salio de /login: el portal dejo entrar. */
  INGRESO("el portal dejo entrar", false),

  /** Modal #errorServer2: el servicio de login respondio codigo 500. */
  ERROR_SERVIDOR(
      "el portal respondio con error del servidor (codigo 500, modal 'Algo salio mal al procesar"
          + " tu solicitud')",
      true),

  /** El portal no contesto dentro de la espera (o la pagina no reacciono al boton). */
  SIN_RESPUESTA("el portal no respondio al boton Ingresar", true),

  /** Otro modal de error del login (codigos 400, 403 u otro con mensaje propio). */
  OTRO_ERROR("el portal mostro un error", true),

  /** Modal #errorRecaptcha: el portal rechazo el ingreso por su reCAPTCHA (codigo 429). */
  RECHAZO_RECAPTCHA("el portal rechazo el ingreso por reCAPTCHA (codigo 429)", false),

  /** Modal #errorServerShort con "Credenciales incorrectas" (codigo 401). */
  CREDENCIALES_INCORRECTAS("el portal rechazo las credenciales (codigo 401)", false);

  private final String descripcion;
  private final boolean fallaDelPortal;

  RespuestaLogin(String descripcion, boolean fallaDelPortal) {
    this.descripcion = descripcion;
    this.fallaDelPortal = fallaDelPortal;
  }

  public String descripcion() {
    return descripcion;
  }

  /** El portal fallo por su cuenta: la automatizacion hizo su parte y pulso Ingresar. */
  public boolean esFallaDelPortal() {
    return fallaDelPortal;
  }
}
