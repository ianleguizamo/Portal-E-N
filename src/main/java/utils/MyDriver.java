package utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static utils.enums.AppPackage.getAppPackage;
import static utils.enums.MobileDevices.MOTO_E20;

public class MyDriver implements DriverSource {
 //   private static AndroidDriver driver;

    private static AndroidDriver<MobileElement> driver;


    public static AndroidDriver<MobileElement> getDriver() {
        return driver;
    }


    @Override
    public AndroidDriver newDriver() {
        try {

            // Obtener el tipo de app a usar desde una variable de entorno
            EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
            String appType = System.getProperty("appType", " "); // Valor por defecto


            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("app", variables.getProperty("appium.app." + appType));
            capabilities.setCapability("appPackage", variables.getProperty("appium.appPackage." + appType));
            capabilities.setCapability("appActivity", variables.getProperty("appium.appActivity." + appType));



            capabilities.setCapability("noReset", true);  // No borra datos, mantiene la sesión
            capabilities.setCapability("fullReset", false);  // Evita reiniciar la app


         /*   capabilities.setCapability("appActivity", "com.claro.superapp.SplashActivity");
            capabilities.setCapability("appPackage", getAppPackage()); */

            capabilities.setCapability(
                    "chromedriverExecutable", "src/test/resources/webdriver/chromedriver.exe");
            capabilities.setCapability("deviceName", MOTO_E20.getDeviceName());
            capabilities.setCapability("platformVersion", MOTO_E20.getVersion());
            capabilities.setCapability("platformName", MOTO_E20.getOperatingSystem());
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("autoGrantPermissions", "true");
            capabilities.setCapability("reset", "false");
            capabilities.setCapability("noReset", "false");
            capabilities.setCapability("autoDismissAlerts", "true");
            // return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            System.out.println("Ejecutando con: " + appType);
            System.out.println("Ruta APK: " + variables.getProperty("appium.app." + appType));



            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        } catch (IOException e) {
            throw new RuntimeException("Error iniciando el driver de Appium", e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
