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







