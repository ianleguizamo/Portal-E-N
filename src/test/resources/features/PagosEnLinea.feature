# language: es

Característica: Portal Empresas Claro - EyN

  Antecedentes:
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa a Pagos en línea

  @EyN_PORT_Pagos_PSE
  Escenario: Realizar pago de soluciones móviles mediante PSE
    Entonces el usuario accede a pagos de soluciones móviles por PSE

  @EyN_PORT_Pagos_Bancolombia
  Escenario: Realizar pago de soluciones móviles mediante Bancolombia
    Entonces el usuario accede a pagos de soluciones móviles por Bancolombia

  @EyN_PORT_Pagos_Tarjeta
  Escenario: Realizar pago de soluciones móviles con tarjeta de crédito
    Entonces el usuario accede a pagos de soluciones móviles con tarjeta

  @EyN_PORT_Pagos_Google_Play
  Escenario: Realizar pago de soluciones móviles con Google Play
    Entonces el usuario accede a pagos de soluciones móviles con Google Play

  @EyN_PORT_Pagos_Tarjeta_Codensa
  Escenario: Realizar pago de soluciones móviles con tarjeta Codensa
    Entonces el usuario accede a pagos de soluciones móviles con Tarjeta Codensa

  @EyN_PORT_Pagos_Fija_PSE
  Escenario: Realizar pago de soluciones fijas mediante PSE
    Entonces el usuario accede a pagos de soluciones fijas por PSE

  @EyN_PORT_Pagos_Fija_Bancolombia
  Escenario: Realizar pago de soluciones fijas mediante Bancolombia
    Entonces el usuario accede a pagos de soluciones fijas por Bancolombia

  @EyN_PORT_Pagos_Fija_Tarjeta
  Escenario: Realizar pago de soluciones fijas con tarjeta de crédito
    Entonces el usuario accede a pagos de soluciones fijas con tarjeta

  @EyN_PORT_Pagos_Fija_Google_Play
  Escenario: Realizar pago de soluciones fijas con Google Play
    Entonces el usuario accede a pagos de soluciones fijas con google play

  @EyN_PORT_Pagos_Fija_Tarjeta_Codensa
  Escenario: Realizar pago de soluciones fijas con tarjeta Codensa
    Entonces el usuario accede a pagos de soluciones fijas con tarjeta codensa

  @EyN_PORT_Tarjetas_Registradas
  Escenario: Consultar tarjetas registradas en el portal
    Entonces el usuario visualiza las tarjetas registradas en su cuenta

  @EyN_PORT_Pagar_Otras_Cuentas
  Escenario: Pagar otras facturas desde el portal
    Entonces el usuario accede a la opción de pagar otras facturas

  @EyN_PORT_Pago_Auto_Facturas_Soluciones_Moviles
  Escenario: Pagos automáticos de facturas en soluciones móviles
    Entonces el usuario accede a la opción Pago automático de facturas
