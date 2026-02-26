package userinterfaces;

import interactions.scroll.Scroll;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CmaxPage {

  public static final Target TXT_COPIAR_SALDO = Target.the("Saldo de la línea")
          .locatedBy("//span[contains(@class,'DAReadOnlyTxt')]");

  public static final Target TXT_COPIAR_SI_HAY_PAQUETE = Target.the("Hay paquetes comprados??")
          .locatedBy("//a[contains(@class,'DATblTDALinkTxt')]");

  public static final Target TXT_USUARIO = Target.the("campo correo")
          .located(By.id("_cenLoginPortlet_userName"));

  public static final Target TXT_CONTRASENA =
      Target.the("Caja de texto para ingresar la contraseña del usuario")
              .located(By.id("_cenLoginPortlet_password"));

  public static final Target BTN_ACEPTAR = Target.the("Botón aceptar")
          .located(By.cssSelector("button.acept-btn"));

  public static final Target BTN_CERRAR_MENU = Target.the("Botón cerrar menú")
          .located(By.cssSelector("span.icon-Eliminar-02.icon-ham"));

  public static final Target MENU_DESPLEGABLE =
          Target.the("Caja de texto para ingresar la contraseña del usuario")
                  .located(By.id("icon-menu"));

  public static final Target PAGOS_EN_LINEA = Target.the("botón Pagos en línea")
          .located(By.xpath("//a[@href='/group/guest/pagos-linea']"));

  public static final Target CONSULTA_TUS_FACTURAS =
          Target.the("Consulta tus facturas")
                  .located(By.cssSelector("a[href='/group/guest/consulta-facturas']"));

  public static final Target SOLUCIONES_MOVILES =
          Target.the("Soluciones móviles")
                  .locatedBy("//a[@href='/group/movil/movil']");

  public static final Target DESCARGA_TU_FACTURA =
          Target.the("Descarga tu factura")
                  .located(By.cssSelector("a[href='/group/guest/consultar-factura']"));

  public static final Target RESUMEN_GRAFICO_FACTURAS =
          Target.the("Resumen Gráfico de Facturas")
                  .locatedBy("//a[@href='/group/guest/analisis-grafico']");

  public static final Target ACCOUNT_ITEM =
          Target.the("Número de cuenta")
                  .locatedBy("//div[@class='account-item' and normalize-space()='8.22448979']");

  public static final Target CUENTA_MAESTRA =
          Target.the("Cuenta maestra")
                  .located(By.cssSelector("div.account-card"));

  public static final Target HEADER_TIPO_SOLICITUD =
          Target.the("Header Tipo de solicitud")
                  .locatedBy("//div[contains(@class,'header-row')]//div[normalize-space()='Tipo de solicitud']");


  public static final Target CUENTA_CARD_CONTENEDOR =
          Target.the("Card de cuenta")
                  .located(By.cssSelector("div.card-body"));

  public static final Target TARJETA_LINEA_SELECCIONABLE =
          Target.the("Tarjeta de línea seleccionable")
                  .located(By.xpath("//div[contains(@class,'card-body') and .//h5/strong[text()='3226806277']]"));

  public static final Target LINE_CHECKBOX =
          Target.the("Checkbox de selección de línea")
                  .located(By.cssSelector("div.line-item input.custom-radio"));

  public static final Target CUENTA_MAESTRA_CONSUMOS =
          Target.the("Cuenta maestra en consumos")
                  .locatedBy(
                          "//div[contains(@class,'consumptions-source') and normalize-space()='Cuenta maestra: 8.22448979']");

  public static final Target CONSUMO_DATOS =
          Target.the("Botón Consumo de datos")
                  .locatedBy("//a[.//span[normalize-space()='Consumo de datos']]");

  public static final Target CHECKBOX_LINEA_POR_NUMERO =
          Target.the("Checkbox de la línea móvil")
                  .locatedBy("//div[@class='line-item']//div[@class='line' and normalize-space()='3226806277']" +
                          "/ancestor::div[@class='line-item']//input[@class='custom-radio']");

  public static final Target BOTON_CONTINUAR1 =
          Target.the("Botón Continuar")
                  .located(By.xpath("//button[contains(@class,'button-select-enable') and contains(@class,'button-size')]"));

  public static final Target BTN_INGRESAR = Target.the("botón ingresar")
          .located(By.id("buttonSign"));


  public static final Target HEADER_TABLA_LINEAS =
          Target.the("Encabezado de tabla de líneas móviles")
                  .located(By.xpath("//div[contains(@class,'header-row')]"));

  public static final Target HEADER_LINEA_MOVIL =
          Target.the("Header Línea Móvil")
                  .located(By.xpath("//div[contains(@class,'header-table')]//div[normalize-space()='Línea Móvil']"));

  public static final Target BOTON_ACEPTAR =
          Target.the("Botón Aceptar")
                  .located(By.cssSelector("button.boton-aceptar-desac"));

  public static final Target TEXTO_DESCRIPCION_ROAMING =
          Target.the("Texto descriptivo de Roaming Internacional")
                  .located(By.cssSelector("div.text-description"));

  public static final Target CAMBIO_SIM_CARD =
          Target.the("Cambio de SIM Card")
                  .locatedBy("//a[@href='/group/movil/cambio-de-sim']");

  public static final Target CAMBIO_DE_NUMERO =
          Target.the("Opción Cambio de Número")
                  .located(By.cssSelector("a[href='/group/movil/cambio-de-numero']"));

  public static final Target ACTUALIZACION_DATOS_MOVILES =
          Target.the("Opción Actualización de datos móviles")
                  .located(By.cssSelector("a[href='/group/movil/actualizacion-datos']"));

  public static final Target ROAMING_INTERNACIONAL =
          Target.the("Roaming Internacional")
                  .located(By.cssSelector("a[href='/group/movil/paquetes-roaming']"));

  public static final Target OPCION_REPOSICION_SIM_CARD =
          Target.the("Opción Reposición de SIM Card")
                  .located(By.xpath("//a[contains(@href,'/reposicion-domicilio')]"));

  public static final Target SERVICIO_TECNICO_DOMICILIO =
          Target.the("Servicio técnico a domicilio")
                  .located(By.xpath("//a[contains(@href,'/group/movil/servicio-tecnico')]"));

  public static final Target PAQUETES_DE_DATOS =
          Target.the("Paquetes de datos")
                  .located(By.xpath("//div[contains(@class,'item-type-menu')]//div[contains(@class,'container-text') and normalize-space()='Paquetes de datos']"));

  public static final Target CONSULTAR_CONSUMOS =
          Target.the("Opción Consultar consumos")
                  .locatedBy("//div[contains(@class,'item-type-menu')]//div[text()='Consultar consumos']");

  public static final Target DETALLE_DE_TU_PLAN =
          Target.the("Detalle de tu plan")
                  .locatedBy("//div[@class='item-type-menu']//div[@class='container-text' and normalize-space()='Detalle de tu plan']/ancestor::a");

  public static final Target DETALLE_CUENTAS_MAESTRAS =
          Target.the("Detalle de tus cuentas maestras")
                  .locatedBy("//div[contains(@class,'item-type-menu')]//div[@class='container-text' and normalize-space()='Detalle de tus cuentas maestras']/ancestor::a");

  public static final Target CONSULTAR_SOLICITUDES_DOMICILIO =
          Target.the("Consultar solicitudes a domicilio")
                  .locatedBy("//div[contains(@class,'item-type-menu')]//div[@class='container-text' and normalize-space()='Consultar solicitudes a domicilio']/ancestor::a");

  public static final Target CHECKBOX_CUSTOM_RADIO =
          Target.the("Checkbox custom radio")
                  .locatedBy("//input[@type='checkbox' and contains(@class,'custom-radio')]");

  public static final Target BTN_CONTINUAR =
          Target.the("Botón Continuar")
                  .located(By.cssSelector("button.continue-button.active"));

  public static final Target INFO_SIM =
          Target.the("Información SIM Card")
                  .located(By.cssSelector("div.container-info-sim"));

  public static final Target INFO_CAMBIO_NUMERO =
          Target.the("Mensaje informativo de cambio de número")
                  .located(By.cssSelector("div.margins.info-field"));

  public static final Target BOTON_VER_FACTURA =
          Target.the("Botón ver factura")
                  .located(By.cssSelector("button.btnBillRegister.btnResumeViewer"));

  public static final Target FILA_FACTURA =
          Target.the("Fila de factura")
                  .located(By.cssSelector("td[role='gridcell'] button.btnResumeViewer"));

  public static final Target TARJETAS_REGISTRADAS = Target.the("Tarjetas registradas")
          .located(By.xpath("//a[contains(@href,'/recordatorio-tarjeta')]"));

  public static final Target REGISTRAR_NUEVA_TARJETA = Target.the("Registrar una nueva tarjeta")
          .located(By.xpath("//a[contains(@href,'asociarTarjetaClaroPay')]"));

  public static final Target BOTON_SUMAR_TARJETA =
          Target.the("Botón registrar nueva tarjeta")
                  .located(By.xpath("//a[.//img[contains(@src,'icono-sumar.svg')]]"));


  public static final Target PAGO_SOLUCIONES_MOVILES = Target.the("botón Pago de soluciones móviles")
          .located(By.cssSelector("a[href='/group/guest/pago-en-linea-movil']"));

  public static final Target PAGO_SOLUCIONES_FIJAS_HFC = Target.the("Pago de soluciones fijas HFC")
          .located(By.xpath("//a[contains(@href,'/pago-en-linea-hfc')]"));

  public static final Target PAGAR_OTRAS_CUENTAS =
          Target.the("Pagar otras cuentas")
                  .located(By.cssSelector("a[href='/group/guest/pagar-otras-cuentas']"));

  public static final Target ADMINISTRAR_NITS =
          Target.the("Botón Administrar NITS del Grupo")
                  .located(By.xpath("//a[contains(.,'Administrar NITS del Grupo')]"));

  public static final Target CONTENEDOR_OPINION = Target.the("contenedor mensaje de opinión")
          .located(By.xpath("//div[@style='text-align: center;' and .//strong[contains(text(),'Tu opinión es importante')]]"));

  public static final Target BOTON_CERRAR_ENCUESTA = Target.the("botón cerrar encuesta Qualtrics")
          .located(By.xpath("//img[contains(@src,'svg-close-btn-black')]"));

  public static final Target DEPARTMENT_INPUT =
          Target.the("Campo departamento")
                  .located(By.id("department"));

  public static final Target ICONO_CHEVRON_LEFT =
          Target.the("Ícono Chevron Left Principal")
                  .located(By.xpath("//*[name()='svg' and @id='icono-chevron-left-ppal']"));

  public static final Target CHECKBOX_CUSTOM = Target.the("checkbox custom")
          .located(By.xpath("//input[@class='custom-radio']"));

  public static final Target CHECKBOX_FILA = Target.the("checkbox de fila de factura")
          .located(By.xpath("//div[contains(@class,'table-data')]//input[@type='checkbox' and contains(@class,'custom-radio') and not(@name='selectAll')]"));

  public static final Target BOTON_PAGAR = Target.the("botón Pagar")
          .located(By.xpath("//button[contains(@class,'pagar-btn-red') and text()='Pagar']"));

  public static final Target LABEL_NRO_DOCUMENTO_TITULAR = Target.the("Label número de documento del titular")
          .located(By.xpath("//label[normalize-space()='Nro de Documento del titular*:']"));

  public static final Target METODO_PSE = Target.the("método de pago PSE")
          .located(By.cssSelector("div.method.pse"));

  public static final Target BOTON_BANCOLOMBIA = Target.the("método de pago Botón Bancolombia")
          .located(By.xpath("//div[contains(@class,'method')]//div[text()='Botón Bancolombia']"));

  public static final Target BOTON_CONTINUAR = Target.the("botón Continuar")
          .located(By.cssSelector("button.continue-btn"));

  public static final Target METODO_TARJETA = Target.the("método de pago Tarjeta Débito o Crédito")
          .located(By.xpath("//div[contains(@class,'method')][.//div[text()='Tarjeta de Débito/Crédito']]"));

  public static final Target CAMPANA =
          Target.the("Caja de texto para ingresar la contraseña del usuario")
                  .located(By.id("campana-notifi"));

  public static final Target CAMPANA_X =
          Target.the("Caja de texto para ingresar la contraseña del usuario")
                  .located(By.id("closeButto"));

  public static final Target TERMINOS_Y_CONDICIONES = Target.the("botón Términos y condiciones")
          .located(By.xpath("//div[contains(@class,'text-submenu2')][contains(text(),'Términos y condiciones')]"));

  public static final Target MI_CUENTA = Target.the("botón Mi cuenta")
          .locatedBy("//span[contains(text(),'Mi cuenta')]");

  public static final Target INFORMACION_USUARIO = Target.the("opción Información del usuario")
          .located(By.id("mi-cuenta-informacion"));

  public static final Target GESTION_CUENTA = Target.the("título Gestión de la cuenta")
          .located(By.cssSelector("h1.title-arrow-left-gestion"));


  public static final Target GESTION_DE_CUENTA = Target.the("título Gestión de la cuenta")
          .located(By.cssSelector("h1.title-arrow-left"));

  public static final Target GESTION_USUARIOS = Target.the("link Gestión de usuarios")
          .locatedBy("//span[contains(text(),'Gestión de usuarios')]");

  public static final Target CREAR_USUARIO = Target.the("botón Crear usuario")
          .located(By.cssSelector("button.button-create-user"));


  public static final Target FLECHA_VOLVER_GU = Target.the("flecha volver")
          .located(By.cssSelector("div.back-arrow"));

  public static final Target BOTON_DESCARGAR = Target.the("botón Descargar")
          .locatedBy("//button[contains(text(),'Descargar')]");

  public static final Target BTN_BUSCAR =
      Target.the("Boton buscar").located(By.name("SearchButton"));

  public static final Target POLITICAS_PRIVACIDAD = Target.the("Botón Políticas de privacidad")
          .locatedBy("//div[contains(@class,'text-submenu2')][contains(text(),'Políticas de privacidad')]");


  public static final Target BTN_CLIENTES =
      Target.the("Boton de clientes").located(By.xpath("//*[@id=\"CustomersNode\"]/td[2]/a"));

  public static final Target BTN_BUSCAR_CLIENTE =
      Target.the("Boton de buscar a clientes")
          .located(By.xpath("//*[@id=\"CustomersNode_sl\"]/td[2]/table/tbody/tr/td[2]/a"));

  public static final Target TXT_MSISDN =
      Target.the("Caja de texto para ingresar numero").located(By.xpath("//*[@id=\"DIRNUM\"]"));

  public static final Target TXT_MSISDN_BUSCADO =
      Target.the("Texto de la linea consultada")
          .located(By.xpath("//*[@id=\"SearchResults\"]/tbody[2]/tr/td[2]/a"));

}