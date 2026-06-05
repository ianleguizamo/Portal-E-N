# language: es

Característica: Portal Empresas Claro - EyN

  @EyN_PORT_InicioSesion
  Escenario: Inicio de sesión y redireccionamientos
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el sistema redirige correctamente al usuario

  @EyN_PORT_ValidarMiCuenta
  Escenario: Validar información de mi cuenta desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario valida la información de su cuenta

  @EyN_PORT_GestionUsuarios
  Escenario: Gestión de usuarios desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario valida la gestión de usuarios

  @EyN_PORT_AdministrarNITS
  Escenario: Administrar NITs del grupo desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario valida los NITs del grupo empresarial

  @EyN_PORT_ConsultorAsignado
  Escenario: Consultar el consultor asignado desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario visualiza la información del consultor asignado

  @EyN_PORT_DocClaroCol
  Escenario: Consultar documentación de Claro Colombia desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario accede a la documentación de Claro Colombia

  @EyN_PORT_CentroDeAyuda
  Escenario: Validar todas las opciones del centro de ayuda desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario navega por todas las opciones del centro de ayuda

  @EyN_PORT_PagosPSE
  Escenario: Realizar pago de soluciones móviles mediante PSE
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles por PSE

  @EyN_PORT_PagosBancolombia
  Escenario: Realizar pago de soluciones móviles mediante Bancolombia
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles por Bancolombia

  @EyN_PORT_05
  Escenario: Realizar pago de soluciones móviles con tarjeta de crédito
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles con tarjeta

  @EyN_PORT_06
  Escenario: Realizar pago de soluciones fijas mediante PSE
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas por PSE

  @EyN_PORT_07
  Escenario: Realizar pago de soluciones fijas mediante Bancolombia
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas por Bancolombia

  @EyN_PORT_08
  Escenario: Realizar pago de soluciones fijas con tarjeta de crédito
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas con tarjeta

  @EyN_PORT_09
  Escenario: Consultar tarjetas registradas en el portal
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza las tarjetas registradas en su cuenta

  @EyN_PORT_10
  Escenario: Pagar otras facturas desde el portal
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de pagar otras facturas

  @EyN_PORT_11
  Escenario: Descargar facturas desde el portal
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario descarga sus facturas correctamente

  @EyN_PORT_11B
  Escenario: Visualizar resumen gráfico de consumos
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza el resumen gráfico de sus consumos

  @EyN_PORT_12
  Escenario: Solicitar cambio de SIM en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario realiza la solicitud de cambio de SIM

  @EyN_PORT_13
  Escenario: Solicitar cambio de número en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario realiza la solicitud de cambio de número

  @EyN_PORT_14
  Escenario: Actualizar datos personales en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario actualiza su información de datos personales

  @EyN_PORT_15
  Escenario: Activar roaming internacional en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de roaming internacional

  @EyN_PORT_16
  Escenario: Solicitar reposición de SIM en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario realiza la solicitud de reposición de SIM

  @EyN_PORT_17
  Escenario: Solicitar servicio técnico desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de servicio técnico

  @EyN_PORT_18
  Escenario: Consultar y activar paquetes de datos en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza los paquetes de datos disponibles

  @EyN_PORT_19
  Escenario: Consultar consumos desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario consulta el detalle de sus consumos

  @EyN_PORT_20
  Escenario: Consultar detalle del plan desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza el detalle de su plan activo

  @EyN_PORT_21
  Escenario: Consultar detalle de la cuenta maestra desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza el detalle de su cuenta maestra

  @EyN_PORT_22
  Escenario: Solicitar servicios a domicilio desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de solicitudes a domicilio