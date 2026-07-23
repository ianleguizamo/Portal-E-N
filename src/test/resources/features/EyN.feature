# language: es

Característica: Portal Empresas Claro - EyN

  @EyN_PORT_Inicio_Sesion
  Escenario: Inicio de sesión y redireccionamientos
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el sistema redirige correctamente al usuario

  @EyN_PORT_Validar_MiCuenta
  Escenario: Validar información de mi cuenta desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario valida la información de su cuenta

  @EyN_PORT_Gestion_Usuarios
  Escenario: Gestión de usuarios desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario valida la gestión de usuarios

  @EyN_PORT_Administrar_NITS
  Escenario: Administrar NITs del grupo desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario valida los NITs del grupo empresarial

  @EyN_PORT_Consultor_Asignado
  Escenario: Consultar el consultor asignado desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario visualiza la información del consultor asignado

  @EyN_PORT_Doc_Claro_Col
  Escenario: Consultar documentación de Claro Colombia desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario accede a la documentación de Claro Colombia

  @EyN_PORT_Centro_De_Ayuda
  Escenario: Validar todas las opciones del centro de ayuda desde el menú desplegable
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Y el usuario ingresa al menú desplegable
    Entonces el usuario navega por todas las opciones del centro de ayuda

  @EyN_PORT_Pagos_PSE
  Escenario: Realizar pago de soluciones móviles mediante PSE
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles por PSE

  @EyN_PORT_Pagos_Bancolombia
  Escenario: Realizar pago de soluciones móviles mediante Bancolombia
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles por Bancolombia

  @EyN_PORT_Pagos_Tarjeta
  Escenario: Realizar pago de soluciones móviles con tarjeta de crédito
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles con tarjeta

  @EyN_PORT_Pagos_Google_Play
  Escenario: Realizar pago de soluciones móviles con Google Play
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles con Google Play

  @EyN_PORT_Pagos_Tarjeta_Codensa
  Escenario: Realizar pago de soluciones móviles con tarjeta Codensa
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones móviles con Tarjeta Codensa

  @EyN_PORT_Pagos_Fija_PSE
  Escenario: Realizar pago de soluciones fijas mediante PSE
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas por PSE

  @EyN_PORT_Pagos_Fija_Bancolombia
  Escenario: Realizar pago de soluciones fijas mediante Bancolombia
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas por Bancolombia

  @EyN_PORT_Pagos_Fija_Tarjeta
  Escenario: Realizar pago de soluciones fijas con tarjeta de crédito
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas con tarjeta

  @EyN_PORT_Pagos_Fija_Google_Play
  Escenario: Realizar pago de soluciones fijas con Google Play
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas con google play

  @EyN_PORT_Pagos_Fija_Tarjeta_Codensa
  Escenario: Realizar pago de soluciones fijas con tarjeta Codensa
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a pagos de soluciones fijas con tarjeta codensa

  @EyN_PORT_Tarjetas_Registradas
  Escenario: Consultar tarjetas registradas en el portal
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza las tarjetas registradas en su cuenta

  @EyN_PORT_Pagar_Otras_Cuentas
  Escenario: Pagar otras facturas desde el portal
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de pagar otras facturas

  @EyN_PORT_Pago_Auto_Facturas_Soluciones_Moviles
  Escenario: Pagos automáticos de facturas en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción Pago automático de facturas

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

  @EyN_PORT_Cambio_SIM
  Escenario: Solicitar cambio de SIM en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario realiza la solicitud de cambio de SIM

  @EyN_PORT_Cambio_Numero
  Escenario: Solicitar cambio de número en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario realiza la solicitud de cambio de número

  @EyN_PORT_Actualizacion_Datos
  Escenario: Actualizar datos personales en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario actualiza su información de datos personales

  @EyN_PORT_Roaming_Internacional
  Escenario: Activar roaming internacional en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de roaming internacional

  @EyN_PORT_Reposicion_SIM
  Escenario: Solicitar reposición de SIM en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario realiza la solicitud de reposición de SIM

  @EyN_PORT_Servicio_Tecnico
  Escenario: Solicitar servicio técnico desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de servicio técnico

  @EyN_PORT_Paquetes_Datos
  Escenario: Consultar y activar paquetes de datos en soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza los paquetes de datos disponibles

  @EyN_PORT_Consultar_Consumos
  Escenario: Consultar consumos desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario consulta el detalle de sus consumos

  @EyN_PORT_Detalle_Plan
  Escenario: Consultar detalle del plan desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza el detalle de su plan activo

  @EyN_PORT_Detalle_Cuenta_Maestra
  Escenario: Consultar detalle de la cuenta maestra desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario visualiza el detalle de su cuenta maestra

  @EyN_PORT_Solicitudes_Domicilio
  Escenario: Solicitar servicios a domicilio desde soluciones móviles
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales
    Entonces el usuario accede a la opción de solicitudes a domicilio
