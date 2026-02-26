package userinterfaces;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

public class SegmentoPage extends PageObject {

  public static final Target BTN_USUARIO =
      Target.the("Boton Usuario y Password").located(MobileBy.id("btnLoginUserPass"));
  public static final Target BTN_CORREO =
      Target.the("Boton Con Correo").located(MobileBy.id("lbEmail"));
    public static final Target LOADING_SPLASH =
      Target.the("Logo de espera al iniciar la App Mi Claro")
          .located(MobileBy.id("com.clarocolombia.miclaro:id/imgLoadingSplash"));
   public static final Target BTN_ACEPTAR_PERMISO =
      Target.the("Boton Aceptar").located(MobileBy.xpath("//*[@text='Aceptar']"));

  public static final Target LBL_SESION_CERRADA_POR_SEGURIDAD =
          Target.the("Texto Tu sesión se ha cerrado por seguridad")
                  .located(MobileBy.xpath("//*[@text='Tu sesión se ha cerrado por seguridad']"));

  public static final Target BTN_ACEPTAR =
          Target.the("Boton Aceptar")
                  .located(MobileBy.xpath("//*[@text='Aceptar']"));

  public static final Target LBL_NOS_ALEGRA_TENERTE_DE_VUELTA =
          Target.the("Texto ¡Nos alegra tenerte de vuelta!")
                  .located(MobileBy.xpath("//*[@text='¡Nos alegra tenerte de vuelta!']"));

  public static final Target LBL_INICIAR_SESION =
          Target.the("Texto Iniciar sesión")
                  .located(MobileBy.xpath("//*[@text='Iniciar sesión']"));
  public static final Target BTN_PERMISO_UBICACION =
      Target.the("Boton Mientras la app está en uso")
          .located(MobileBy.xpath("//*[@text='Mientras la app está en uso']"));

  public static final Target SMS_PERMISO_LLAMADAS =
      Target.the("Mensaje ¿Permitir que Mi Claro haga y administre las llamadas telefónicas?")
          .located(MobileBy.id("permission_message"));

  public static final Target SMS_PERMISO_NOTIFICACIONES =
      Target.the("Mensaje ¿Permitir que Mi Claro te envíe notificaciones? ")
          .located(MobileBy.id("permission_message"));

  public static final Target CHECK_TERMINOS_Y_CONDICIONES =
          Target.the("Texto Acepto los términos y condiciones, el tratamiento de mis datos")
                  .located(MobileBy.id("iv_protocol"));
  public static final Target BTN_OMITIR =
      Target.the("Boton de Omitir").located(MobileBy.xpath("//*[@text='Omitir']"));

  public static final Target LBL_TERMINOS_Y_CONDICIONES =
          Target.the("Texto términos y condiciones").located(MobileBy.xpath("//*[@text='¡Te damos la bienvenida!']"));

  public static final Target LBL_SESION_ABIERTA =
      Target.the("Texto Tienes una sesión abierta en otro dispositivo")
          .located(MobileBy.xpath("//*[@text='Tienes una sesión abierta en otro dispositivo']"));

  public static final Target LBL_INGRESO_BIOMETRICO =
      Target.the("Boton Activar ingreso biometrico")
          .located(MobileBy.xpath("//*[@text='Activa el ingreso biométrico desde tu celular']"));

  public static final Target TXT_USERNAME =
      Target.the("Ingresar Usuario").located(MobileBy.id("input"));

  public static final Target TXT_PASSWORD =
      Target.the("Ingresar Password").located(MobileBy.id("input"));

  public static final Target TXT_AUTORIZACION_VELOCIDAD =
      Target.the("Texto de Autorización de medición de velocidad")
          .located(MobileBy.xpath("//*[@text='Autorización de medición de velocidad']"));

  public static final Target IMG_LOADING =
      Target.the("Imagen de espera de CLaro")
          .located(MobileBy.id("com.clarocolombia.miclaro:id/imgLoading"));

   public static final Target CBX_TIPO_PAQUETE =
      Target.the("Combo seleccion tipo de paquetes")
          .located(
              MobileBy.xpath(
                  "//android.widget.Button[@text=\"Paquetes todo incluido con redes\"]"));

   public static final Target LBL_ENCABEZADO_USUARIO =
      Target.the("Label Encabezado de usuario").located(MobileBy.id("home_user_name_tv"));
  public static final Target LOGO_ESPERA_CLARO =
      Target.the("Logo de espera Claro en la seccion de categorias")
          .located(MobileBy.id("com.clarocolombia.miclaro:id/imgLoadingInfoTitle"));

  public static final Target LBL_COMPRA_PAQUETES_RECARGAS2 =
      Target.the("Label Compra de paquetes y recargas")
          .located(
              MobileBy.xpath(
                  "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.TextView[2]"));

  public static final Target BTN_NEXT =
      Target.the("Boton de siguiente")
          .located(
              MobileBy.xpath(
                  "//android.view.View[@resource-id=\"__react-content\"]/android.view.View/android.widget.TextView[19]"));

  public static final Target BTN_NEXT_2 =
      Target.the("Boton de siguiente")
          .located(
              MobileBy.xpath(
                  "//android.view.View[@resource-id=\"__react-content\"]/android.view.View/android.widget.TextView[15]"));

  public static final Target BTN_NEXT_3 =
      Target.the("Boton de siguiente para paquetes Apps")
          .located(
              MobileBy.xpath(
                  "//android.view.View[@resource-id=\"__react-content\"]/android.view.View/android.widget.TextView[18]"));

  public static final Target BTN_NEXT_4 =
      Target.the("Boton de siguiente para paquetes Apps")
          .located(
              MobileBy.xpath(
                  "//android.view.View[@resource-id=\"__react-content\"]/android.view.View/android.widget.TextView[17]"));

  public static final Target BTN_CONTRA_SALDO =
      Target.the("Boton Contra Saldo")
          .located(MobileBy.xpath("//android.widget.TextView[@text=\"Contra saldo\"]"));

  public static final Target BTN_CONTRA_SALDO2 =
      Target.the("Boton Contra Saldo").located(MobileBy.id("imageViewIcon"));

  public static final Target BTN_COMPRAR2 =
      Target.the("Boton de comprar2")
          .located(MobileBy.xpath("(//android.widget.Button[@text=\"Comprar\"])[2]"));

  public static final Target BTN_COMPRAR1 =
      Target.the("Boton de comprar1")
          .located(
              MobileBy.xpath(
                  "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.Button"));

  public static final Target TXT_TE_GUSTARIA_CALIFICAR_MI_CLARO_APP =
      Target.the("Texto de te gustaria calificar mi claro App")
          .located(
              MobileBy.xpath(
                  "//*[@text='¿Te gustaría calificar tu experiencia con el App Mi Claro?']"));

  public static final Target TXT_CUENTANOS_COMO_FUE_TU_EXPERIENCIA =
      Target.the("Texto de Cuéntanos cómo fue tu experiencia")
          .located(MobileBy.xpath("//*[@text='¡Cuéntanos cómo fue tu experiencia!']"));

  public static final Target LOADING_ESPERA_UN_MOMENTO =
      Target.the("Logo de espera al iniciar la App Mi Claro")
          .located(MobileBy.id("com.clarocolombia.miclaro:id/animation_splash_lottie"));

  public static final Target BTN_VER_MAS_PAGOS_CONSULTAS =
      Target.the("Boton Ver más Pagos y consultas")
          .located(
              MobileBy.xpath(
                  "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout[2]/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[4]/android.widget.FrameLayout/android.widget.ImageView"));

   public static final Target BTN_COMPRAR_1 =
      Target.the("Boton comprar 1")
          .located(MobileBy.xpath("(//android.widget.Button[@text=\"Comprar\"])[1]"));

  public static final Target BTN_COMPRAR_2 =
      Target.the("Boton comprar 2")
          .located(MobileBy.xpath("(//android.widget.Button[@text=\"Comprar\"])[2]"));

  public static final Target BTN_COMPRAR_3 =
      Target.the("Boton comprar 3")
          .located(MobileBy.xpath("(//android.widget.Button[@text=\"Comprar\"])[3]"));

  public static final Target LBL_VER_DETALLE_1 =
      Target.the("Texto Ver detalle del paquete 1")
          .located(
              MobileBy.xpath("(//android.widget.Button[@text=\"Ver detalle del paquete\"])[1]"));

  public static final Target LBL_VER_DETALLE_2 =
      Target.the("Texto Ver detalle del paquete 2")
          .located(
              MobileBy.xpath("(//android.widget.Button[@text=\"Ver detalle del paquete\"])[2]"));

  public static final Target LBL_VER_DETALLE_3 =
      Target.the("Texto Ver detalle del paquete 3")
          .located(
              MobileBy.xpath("(//android.widget.Button[@text=\"Ver detalle del paquete\"])[3]"));

  public static final Target CHECK_ACEPTAR_TERMINOS_CONDICIONES =
      Target.the("Texto Acepto términos y condiciones")
          .located(
              MobileBy.xpath(
                  "//android.view.View[@resource-id=\"__react-content\"]/android.view.View[2]/android.widget.CheckBox/android.widget.CheckBox"));

  public static final Target PRUEBA =
      Target.the("PRUEBA DE VALIDACION PRECIO")
          .located(MobileBy.xpath("//android.widget.Button[@text=\"términos y condiciones\"]"));

  public static final Target BTN_VER_MAS_PAQUETES =
      Target.the("Boton Ver más paquetes").located(MobileBy.xpath("//*[@text='Ver más paquetes']"));

  public static final Target BTN_TRES_PUNTOS_MAS =
          Target.the("Boton de los 3 punticos para validar la mini versión")
                  .located(MobileBy.xpath("//android.widget.TextView[@content-desc=\"Más\"]"));

  public static final Target BTN_PAGAR_CON_MI_SALDO =
          Target.the("Boton Pagar con mi saldo")
                  .located(MobileBy.xpath("//*[@text='Pagar con mi saldo' and @class='android.widget.Button']"));

  public static final Target CHECK_ACEPTAR_TERMINOS_CONDICIONES2 =
          Target.the("Texto Acepto términos y condiciones")
                  .located(
                          MobileBy.xpath(
                                  "//android.view.View[@resource-id=\"__react-content\"]/android.view.View[2]/android.widget.CheckBox/android.widget.CheckBox\n"));

  public static final Target LBL_COSTO_DEL_PAQUETE =
          Target.the("Texto El costo del paquete se descontará del saldo disponible de tu última recarga")
                  .located(MobileBy.xpath("//*[contains(@text, 'El costo del paquete se descontará del saldo disponible de tu última recarga')]"));

  public static final Target LBL_VER_DETALLE_4 =
          Target.the("Texto Ver detalle del paquete")
                  .located(
                          MobileBy.xpath("//*[@text='Ver detalle del paquete']"));

  public static final Target BTN_PAGAR =
          Target.the("Boton Pagar")
                  .located(
                          MobileBy.xpath("//*[@text='Pagar' and @class='android.widget.Button']"));
}
