package models;

/**
 * Los tres estados en que puede quedar la grilla de "Pago de soluciones" al entrar.
 *
 * <p>Distinguir el tercero del segundo es lo importante: una cuenta al dia y una pagina
 * que no cargo se ven igual (tabla vacia), pero solo la primera es un resultado valido.
 * Si se tratan igual, un fallo real pasa en verde sin haber probado nada.
 */
public enum EstadoGrillaFacturas {

  /** Hay al menos una factura pendiente: el flujo de pago debe continuar. */
  CON_FACTURAS,

  /** El portal confirma que no hay nada que pagar. No es un error. */
  SIN_FACTURAS,

  /** No se pudo determinar: ni contador ni filas. Casi siempre, la pagina no cargo. */
  NO_DISPONIBLE;

  /**
   * Responde si el escenario debe cerrarse por no tener facturas que pagar.
   *
   * @throws AssertionError si el estado es {@link #NO_DISPONIBLE}; ese caso es un fallo
   *     real y no debe confundirse con una cuenta al dia
   */
  public boolean sinFacturasPendientes(String seccion) {
    if (this == NO_DISPONIBLE) {
      throw new AssertionError(
          "No se pudo leer el estado de la grilla de "
              + seccion
              + ": no aparecio el contador 'Facturas por pagar' ni ninguna fila de factura. "
              + "Normalmente significa que la pagina no termino de cargar. "
              + "Revisa la captura del fallo en Error/error.png.");
    }
    return this == SIN_FACTURAS;
  }
}
