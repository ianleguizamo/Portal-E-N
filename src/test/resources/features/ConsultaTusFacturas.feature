# language: es

Característica: Portal Empresas Claro - EyN

  Antecedentes:
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales

  @EyN_PORT_Descarga_Facturas
  Escenario: Descargar facturas desde el portal
    Entonces el usuario descarga sus facturas correctamente

  @EyN_PORT_Resumen_Graficos
  Escenario: Visualizar resumen gráfico de consumos
    Entonces el usuario visualiza el resumen gráfico de sus consumos