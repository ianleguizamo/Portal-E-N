Feature: EyN

  @EyN_PORT_01
  Scenario: validaciones iniciales portal empresas
    Given que el usuario abre la página
    When inicio de sesion
    When Validaciones iniciales


  @EyN_PORT_02
  Scenario: Menu desplegable
    Given que el usuario abre la página
    When inicio de sesion
    When Ingreso al menu desplegable
    When Valida mi cuenta
    When Valida gestion de usuarios
    When Valida NITS del grupo


  @EyN_PORT_03
  Scenario: Pagos en linea - Soluciones moviles PSE
    Given que el usuario abre la página
    When inicio de sesion
    When ingreso a soluciones moviles PSE

  @EyN_PORT_04
  Scenario: Pagos en linea - Soluciones moviles Bancolombia
    Given que el usuario abre la página
    When inicio de sesion
    When ingreso a soluciones moviles Bancolombia

  @EyN_PORT_05
  Scenario: Pagos en linea - Soluciones moviles Tarjetas
    Given que el usuario abre la página
    When inicio de sesion
    When ingreso a soluciones moviles Tarjetas

  @EyN_PORT_06
  Scenario: Pagos en linea - Soluciones fijas
    Given que el usuario abre la página
    When inicio de sesion
    When ingreso a soluciones fijas PSE

  @EyN_PORT_07
  Scenario: Pagos en linea - Soluciones fijas Bancolombia
    Given que el usuario abre la página
    When inicio de sesion
    When ingreso a soluciones fijas Bancolombia

  @EyN_PORT_08
  Scenario: Pagos en linea - Soluciones fijas tarjetas
    Given que el usuario abre la página
    When inicio de sesion
    When ingreso a soluciones fijas tarjetas

  @EyN_PORT_09
  Scenario: Pagos en linea - tarjetas registradas
    Given que el usuario abre la página
    When inicio de sesion
    When ingreso a tarjetas registradas

  @EyN_PORT_10
  Scenario: Pagos en linea - tarjetas registradas
    Given que el usuario abre la página
    When inicio de sesion
    When pagar otras facturas

  @EyN_PORT_11
  Scenario: consulta tus facturas - tarjetas registradas
    Given que el usuario abre la página
    When inicio de sesion
    When descarga tus facturas

  @EyN_PORT_11
  Scenario: consulta tus facturas - tarjetas registradas
    Given que el usuario abre la página
    When inicio de sesion
    When resumen graficos

  @EyN_PORT_12
  Scenario: soluciones moviles - cambio de sim
    Given que el usuario abre la página
    When inicio de sesion
    When cambio de SIM

  @EyN_PORT_13
  Scenario: soluciones moviles - cambio de numero
    Given que el usuario abre la página
    When inicio de sesion
    When cambio de numero

  @EyN_PORT_14
  Scenario: soluciones moviles - actualizacion de datos
    Given que el usuario abre la página
    When inicio de sesion
    When actualizacion de datos


  @EyN_PORT_15
  Scenario: soluciones moviles - roaming
    Given que el usuario abre la página
    When inicio de sesion
    When roaming internacional

  @EyN_PORT_16
  Scenario: soluciones moviles - reposicion SIM
    Given que el usuario abre la página
    When inicio de sesion
    When reposicion SIM

  @EyN_PORT_17
  Scenario: soluciones moviles - servicio tecnico
    Given que el usuario abre la página
    When inicio de sesion
    When servicio tecnico

  @EyN_PORT_18
  Scenario: soluciones moviles - paquetes de datos
    Given que el usuario abre la página
    When inicio de sesion
    When paquetes de datos

  @EyN_PORT_19
  Scenario: soluciones moviles - consultar consumos
    Given que el usuario abre la página
    When inicio de sesion
    When consultar consumos

  @EyN_PORT_20
  Scenario: soluciones moviles - detalle del plan
    Given que el usuario abre la página
    When inicio de sesion
    When detalle del plan

  @EyN_PORT_21
  Scenario: soluciones moviles - detalle cuenta maestra
    Given que el usuario abre la página
    When inicio de sesion
    When detalle cuenta maestra

  @EyN_PORT_22
  Scenario: soluciones moviles - solicitudes a domicilio
    Given que el usuario abre la página
    When inicio de sesion
    When solicitudes a domicilio



