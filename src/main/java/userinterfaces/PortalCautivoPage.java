package userinterfaces;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PortalCautivoPage {

    public static final Target BARRA_NAVEGACION = Target.the("Barra de busqueda del navegador").
            located(By.id("url_bar"));
    public static final Target BARRA_BUSQUEDA = Target.the("Barra de busqueda del navegador Chrome").
            located(By.id("search_box_text"));
    public static final Target TXT_NUMERO_CLARO2 = Target.the("Caja de texto Número Claro: 2").
            located(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.widget.EditText"));
    public static final Target TXT_NUMERO_CLARO3 = Target.the("Caja de texto Número Claro: 3").
            located(By.id("msisdn"));
    public static final Target TXT_NUMERO_CLARO4 = Target.the("Caja de texto Número Claro: 4").
            located(By.className("android.widget.EditText"));
    public static final Target BTN_CONTINUAR2 = Target.the("Boton Continuar")
            .located(MobileBy.xpath("//*[@text='Continuar']"));
    public static final Target BTN_ACEPTAR2 = Target.the("Boton Aceptar")
            .located(MobileBy.xpath("//*[@text='Aceptar']"));
    public static final Target TXT_RECHAZAR = Target.the("Texto Rechazar")
            .located(MobileBy.xpath("//*[@text='Rechazar']"));
    public static final Target BTN_PAQUETES2 = Target.the("Boton Paquetes")
            .located(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[3]/android.widget.Button"));
    public static final Target LBL_PAQUETES = Target.the("Texto Paquetes")
            .located(MobileBy.xpath("//*[@text='Paquetes']"));
    public static final Target PQT_30_DIAS_MINUTOS_ILIMITADOS_18GB = Target.the("Boton Comprar Paquete 30 dias con Minutos Ilimitados y 18 GB")
            .located(MobileBy.xpath("//android.view.View[.//*[contains(@text, '18 GB') or contains(@text, '$42.000')]]//*[contains(@text, 'Comprar')]"));
    public static final Target PQT_30_DIAS_MINUTOS_ILIMITADOS_12GB = Target.the("Boton Comprar Paquete 30 dias con Minutos Ilimitados y 18 GB -  Celular pequeño")
            .located(MobileBy.xpath("//android.view.View[.//*[contains(@text, '12 GB') or contains(@text, '$32.000')]]//*[contains(@text, 'Comprar')]"));

}
