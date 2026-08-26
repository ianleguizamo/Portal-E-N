# language: es

Característica: Portal Empresas Claro - EyN

  @EyN_PORT_Descarga_Facturas
  Escenario: Descargar facturas desde el portal
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario descarga sus facturas correctamente

  @EyN_PORT_Resumen_Graficos
  Escenario: Visualizar resumen gráfico de consumos
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza el resumen gráfico de sus consumos