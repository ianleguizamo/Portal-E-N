# language: es

Característica: Portal Empresas Claro - EyN

  Antecedentes:
    Dado que el usuario abre el portal de Claro Empresas
    Cuando el usuario inicia sesión con sus credenciales

  @EyN_PORT_Consultar_Consumos @usuario_secundario
  Escenario: Consultar consumos desde soluciones móviles
    Entonces el usuario consulta el detalle de sus consumos

  @EyN_PORT_Cambio_SIM @usuario_secundario
  Escenario: Solicitar cambio de SIM en soluciones móviles
    Entonces el usuario realiza la solicitud de cambio de SIM

  @EyN_PORT_Cambio_Numero @usuario_secundario
  Escenario: Solicitar cambio de número en soluciones móviles
    Entonces el usuario realiza la solicitud de cambio de número

  @EyN_PORT_Actualizacion_Datos
  Escenario: Actualizar datos personales en soluciones móviles
    Entonces el usuario actualiza su información de datos personales

  @EyN_PORT_Roaming_Internacional @usuario_secundario
  Escenario: Activar roaming internacional en soluciones móviles
    Entonces el usuario accede a la opción de roaming internacional

  @EyN_PORT_Reposicion_SIM @usuario_secundario
  Escenario: Solicitar reposición de SIM en soluciones móviles
    Entonces el usuario realiza la solicitud de reposición de SIM

  @EyN_PORT_Servicio_Tecnico @usuario_secundario
  Escenario: Solicitar servicio técnico desde soluciones móviles
    Entonces el usuario accede a la opción de servicio técnico

  @EyN_PORT_Paquetes_Datos @usuario_secundario
  Escenario: Consultar y activar paquetes de datos en soluciones móviles
    Entonces el usuario visualiza los paquetes de datos disponibles

  @EyN_PORT_Detalle_Plan @usuario_secundario
  Escenario: Consultar detalle del plan desde soluciones móviles
    Entonces el usuario visualiza el detalle de su plan activo

  @EyN_PORT_Detalle_Cuenta_Maestra @usuario_secundario
  Escenario: Consultar detalle de la cuenta maestra desde soluciones móviles
    Entonces el usuario visualiza el detalle de su cuenta maestra

  @EyN_PORT_Solicitudes_Domicilio
  Escenario: Solicitar servicios a domicilio desde soluciones móviles
    Entonces el usuario accede a la opción de solicitudes a domicilio