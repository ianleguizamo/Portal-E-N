package userinterfaces;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;

public class WhatsAppPage {

    public static final Target LBL_WHATSAPP =
            Target.the("Texto de WhatsApp")
                    .located(MobileBy.id("toolbar_logo"));

    public static final Target BTN_MENU_ITEM =
            Target.the("Boton Más opciones WhatsApp")
                    .located(MobileBy.id("menuitem_overflow"));
    public static final Target BTN_ENVIAR_MENSAJE =
            Target.the("Boton enviar mensaje")
                    .located(MobileBy.id("fab"));

    public static final Target BTN_LUPA =
            Target.the("Boton lupa")
                    .located(MobileBy.id("menuitem_search"));

    public static final Target TXT_BUSCAR_TEXTO =
            Target.the("Caja de texto buscar")
                    .located(MobileBy.id("search_src_text"));

    public static final Target TXT_ENVIAR_MENSAJE =
            Target.the("Caja de texto enviar mensaje")
                    .located(MobileBy.id("entry"));

    public static final Target BTN_ENVIAR =
            Target.the("Boton enviar mensaje")
                    .located(MobileBy.id("send"));

    public static final Target LBL_TRATAMIENTO_DATOS =
            Target.the("¿Autorizas el tratamiento de tus datos personales y aceptas los T&C de nuestro canal de atención digital?")
                    .located(MobileBy.xpath("//*[@text='¿Autorizas el tratamiento de tus datos personales y aceptas los T&C de nuestro canal de atención digital?']"));

    public static final Target LBL_VISTA_PREVIA =
            Target.the("Texto vista previ del destino ")
                    .located(MobileBy.xpath("//*[@text='claro.com.co']"));
    public static final Target BTN_SI =
            Target.the("Boton Si")
                    .located(MobileBy.id("quick_reply_btn_1"));
    public static final Target BTN_SI2 =
            Target.the("Boton Si")
                    .located(MobileBy.xpath("//*[@text='Si']"));
    public static final Target BTN_VER_MENU_PREPAGO =
            Target.the("Boton Ver menú prepago ")
                    .located(MobileBy.xpath("//*[@text='Ver menú prepago']"));

    public static final Target LBL_SIN_SALDO =
            Target.the("Texto saldo disponible no alcanza")
                    .located(MobileBy.xpath("//*[contains(@text, 'no alcanza')]"));

    public static final Target LBL_PAQUETE_ACTIVO =
            Target.the("Texto paquete activo")
                    .located(MobileBy.xpath("//*[contains(@text, 'Actualmente tienes activo')]"));
    public static final Target BTN_ENVIAR2 =
            Target.the("Boton Enviar")
                    .located(MobileBy.xpath("//*[@text='Enviar']"));

    public static final Target BTN_SELECCIONA =
            Target.the("Boton Selecciona")
                    .located(MobileBy.xpath("(//android.widget.FrameLayout[@resource-id=\"com.whatsapp:id/button\"])[2]"));

    public static final Target OPCIONES_PAQUETES =
            Target.the("Opciones de paquetes")
                    .locatedBy("//*[contains(@text, 'Día') or contains(@text, 'Días') or contains(@text, 'días') or contains(@text, 'Horas') or contains(@text, 'Minutos')]");

    public static final Target BTN_VER_MAS_PAQUETES =
            Target.the("Botón Ver más paquetes")
                    .located(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Ver más paquetes\")"));

    public static final Target BTN_SELECCIONA2 =
            Target.the("Boton Selecciona")
                    .located(MobileBy.xpath("//*[@text='Selecciona' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='¡Elige y compra el paquete ideal para ti!']]]]"));

    public static final Target LBL_87456=
            Target.the("Texto 87456")
                    .located(MobileBy.xpath("//*[@text='87456']"));

    public static final Target BTN_COMPRAR_PAQ_PREPAGO=
            Target.the("Boton Comprar paq. prepago")
                    .located(MobileBy.xpath("//*[@text='Comprar paq. prepago']"));

    public static final Target BTN_SELECCIONA_PQ_VOZ=
            Target.the("Boton Selecciona paquete de voz")
                    .located(MobileBy.xpath("//*[@text='Selecciona' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='\uD83D\uDCDE Habla con tus seres queridos sin límites.\n" +
                            "Elige tu paquete ideal. ¡Más minutos, más cercanía!']]]]"));
    public static final Target LBL_PAQUETE_INSTALADO =
            Target.the("Texto paquete activo")
                    .located(MobileBy.xpath("//*[contains(@text, 'fue instalado')]"));

    public static final Target BTN_SELECCIONA_PQ_DATOS=
            Target.the("Boton Selecciona paquete de datos")
                    .located(MobileBy.xpath("//*[@text='Selecciona' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='\uD83D\uDCF1 Disfruta tus aplicaciones móviles favoritas y navega todo el contenido en línea que más te gusta']]]]"));
    public static final Target BTN_SELECCIONA_PQ_APPS=
            Target.the("Boton Selecciona paquete de apps")
                    .located(MobileBy.xpath("//*[@text='Selecciona' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='\uD83D\uDE4C Libérate de los límites y diviértete con tus aplicaciones móviles favoritas sin consumir tus datos']]]]"));
    public static final Target BTN_SELECCIONA_PQ_RELEVO=
            Target.the("Boton Selecciona paquete de comunidad sorda")
                    .located(MobileBy.xpath("//*[@text='Selecciona' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='\uD83E\uDEF6 En Claro ofrecemos un mundo sin barreras, incluyente y con total acceso para todos los colombianos. \n" +
                            "\n" +
                            "Recuerda tener saldo disponible para la compra de este paquete']]]]"));

    public static final Target BTN_SELECCIONA_PQ=
            Target.the("Boton Selecciona paquete")
                    .located(MobileBy.xpath("//*[@text='Selecciona' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='¡Elige y compra el paquete ideal para ti!']]]]"));

    public static final Target BTN_COMPRAR_ROAMING=
            Target.the("Boton Comprar Roaming ")
                    .located(MobileBy.xpath("//*[@text='Comprar Roaming']"));

    public static final Target BTN_SI_COMPRAR_PAQUETE_ROAMING=
            Target.the("Boton Si, comprar paquete roaming")
                    .located(MobileBy.xpath("//*[@text='Si, comprar paquete']"));

    public static final Target BTN_USAR_TU_SALDO=
            Target.the("Boton Usar tu saldo")
                    .located(MobileBy.xpath("//*[@text='Usar tu saldo']"));

    public static final Target BTN_SELECCIONA_PQ_INTERN =
            Target.the("Boton seleccionar paquete internacional")
                    .located(MobileBy.xpath("//*[@text='Selecciona' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='Puedes llamar a Alemania, Australia, Bermudas, Brasil, Chile, China, Chipre, Corea del Sur, Costa Rica, Dinamarca, Eslovaquia, España, Finlandia, Francia, Grecia, Guadalupe, Guyana Francesa, Hong Kong, Hungría, India, Indonesia, Irlanda, Islandia, Islas Vírgenes Americanas, Israel, Japón, Kuwait, Luxemburgo, Malta, Noruega, Nueva Zelanda, Panamá, Perú, Reino Unido, Rumania, Singapur, Suecia, Tailandia, Venezuela y Vietnam\n" +
                            "\n" +
                            "¡Elige y compra el paquete ideal para ti!']]]]"));

    public static final Target BTN_SELECCIONA_PQ_INTERN_2 =
            Target.the("Boton selecciona para ingresar al menu paquetes internacional")
                    .located(MobileBy.xpath("(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[2]"));
}
