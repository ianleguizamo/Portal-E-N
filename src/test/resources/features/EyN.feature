Feature: EyN

  @EyN_PORT_InicicioSesion
  Scenario: Inicio de Sesion y redireccionamientos
    Given El usuario abre la página
    When Inicio de sesion
    When Redireccionamientos


  @EyN_PORT_ValidarMiCuenta
  Scenario: Menu desplegable - Valida mi cuenta
    Given El usuario abre la página
    When Inicio de sesion
    When Ingreso al menu desplegable
    When Valida mi cuenta


  @EyN_PORT_GestionUsuarios
    Scenario:  Menu desplegable - Gestion de Usuarios
    Given El usuario abre la página
    When Inicio de sesion
    When Ingreso al menu desplegable
    When Valida gestion de usuarios

  @EyN_PORT_AdministrarNITS
  Scenario:  Menu desplegable - Administrar NIts del grupo
    Given El usuario abre la página
    When Inicio de sesion
    When Ingreso al menu desplegable
    When Valida NITS del grupo

  @EyN_PORT_ConsultorAsignado
  Scenario:  Menu desplegable - Consultor Asignado
    Given El usuario abre la página
    When Inicio de sesion
    When Ingreso al menu desplegable
    When Consultor Asignado

  @EyN_PORT_DocClaroCol
  Scenario:  Menu desplegable - Documentacio Claro Colombia
    Given El usuario abre la página
    When Inicio de sesion
    When Ingreso al menu desplegable
    When Documentación Claro Colombia

  @EyN_PORT_CentroDeAyuda
  Scenario:  Menu desplegable - centro de Ayuda Todas las Opciones
    Given El usuario abre la página
    When Inicio de sesion
    When Ingreso al menu desplegable
    When Centro De Ayuda

  @EyN_PORT_PagosPSE
  Scenario: Pagos en linea - Soluciones moviles PSE
    Given El usuario abre la página
    When Inicio de sesion
    When ingreso a soluciones moviles PSE

  @EyN_PORT_PagosBancolombia
  Scenario: Pagos en linea - Soluciones moviles Bancolombia
    Given El usuario abre la página
    When Inicio de sesion
    When ingreso a soluciones moviles Bancolombia

  @EyN_PORT_05
  Scenario: Pagos en linea - Soluciones moviles Tarjetas
    Given El usuario abre la página
    When Inicio de sesion
    When ingreso a soluciones moviles Tarjetas

  @EyN_PORT_06
  Scenario: Pagos en linea - Soluciones fijas
    Given El usuario abre la página
    When Inicio de sesion
    When ingreso a soluciones fijas PSE

  @EyN_PORT_07
  Scenario: Pagos en linea - Soluciones fijas Bancolombia
    Given El usuario abre la página
    When Inicio de sesion
    When ingreso a soluciones fijas Bancolombia

  @EyN_PORT_08
  Scenario: Pagos en linea - Soluciones fijas tarjetas
    Given El usuario abre la página
    When Inicio de sesion
    When ingreso a soluciones fijas tarjetas

  @EyN_PORT_09
  Scenario: Pagos en linea - tarjetas registradas
    Given El usuario abre la página
    When Inicio de sesion
    When ingreso a tarjetas registradas

  @EyN_PORT_10
  Scenario: Pagos en linea - tarjetas registradas
    Given El usuario abre la página
    When Inicio de sesion
    When pagar otras facturas

  @EyN_PORT_11
  Scenario: consulta tus facturas - tarjetas registradas
    Given El usuario abre la página
    When Inicio de sesion
    When descarga tus facturas

  @EyN_PORT_11
  Scenario: consulta tus facturas - tarjetas registradas
    Given El usuario abre la página
    When Inicio de sesion
    When resumen graficos

  @EyN_PORT_12
  Scenario: soluciones moviles - cambio de sim
    Given El usuario abre la página
    When Inicio de sesion
    When cambio de SIM

  @EyN_PORT_13
  Scenario: soluciones moviles - cambio de numero
    Given El usuario abre la página
    When Inicio de sesion
    When cambio de numero

  @EyN_PORT_14
  Scenario: soluciones moviles - actualizacion de datos
    Given El usuario abre la página
    When Inicio de sesion
    When actualizacion de datos


  @EyN_PORT_15
  Scenario: soluciones moviles - roaming
    Given El usuario abre la página
    When Inicio de sesion
    When roaming internacional

  @EyN_PORT_16
  Scenario: soluciones moviles - reposicion SIM
    Given El usuario abre la página
    When Inicio de sesion
    When reposicion SIM

  @EyN_PORT_17
  Scenario: soluciones moviles - servicio tecnico
    Given El usuario abre la página
    When Inicio de sesion
    When servicio tecnico

  @EyN_PORT_18
  Scenario: soluciones moviles - paquetes de datos
    Given El usuario abre la página
    When Inicio de sesion
    When paquetes de datos

  @EyN_PORT_19
  Scenario: soluciones moviles - consultar consumos
    Given El usuario abre la página
    When Inicio de sesion
    When consultar consumos

  @EyN_PORT_20
  Scenario: soluciones moviles - detalle del plan
    Given El usuario abre la página
    When Inicio de sesion
    When detalle del plan

  @EyN_PORT_21
  Scenario: soluciones moviles - detalle cuenta maestra
    Given El usuario abre la página
    When Inicio de sesion
    When Detalle cuenta maestra

  @EyN_PORT_22
  Scenario: soluciones moviles - solicitudes a domicilio
    Given El usuario abre la página
    When Inicio de sesion
    When solicitudes a domicilio



