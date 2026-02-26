package utils;

import exceptions.Excepciones;
import interactions.WaitFor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static java.time.Duration.ofMillis;

public class AndroidObject extends Excepciones {

    public void HideKeyboard(Actor actor) {
        androidDriver(actor).hideKeyboard();
    }

    public void ScrollElement(Actor actor, String instance, String label) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator(
                            "new UiScrollable(new UiSelector().resourceIdMatches(\".*com.clarocolombia.miclaro:id/rvCategories.*\")"
                                    + ".childSelector(new UiSelector().instance("
                                    + instance
                                    + ").scrollable(true)))"
                                    + ".setAsHorizontalList().scrollIntoView(new UiSelector().text(\""
                                    + label
                                    + "\"));");
            androidDriver(actor)
                    .findElementByAndroidUIAutomator("new UiSelector().text(\"" + label + "\")")
                    .isDisplayed();
        } catch (Exception e) {

            ExScrollElement(actor, instance, label);
        }
    }

    public void swipeHorizontal(
            Actor actor, double inicioX, double finX, double posY, double duracion) {
        AndroidDriver<MobileElement> driver = androidDriver(actor);

        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();

        int startX = (int) (screenWidth * inicioX);
        int endX = (int) (screenWidth * finX);
        int y = (int) (screenHeight * posY);

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(startX, y))
                .waitAction(WaitOptions.waitOptions(ofMillis((long) (duracion * 1000))))
                .moveTo(PointOption.point(endX, y))
                .release()
                .perform();
    }

    public void UnScrollAbajo(Actor actor) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
        } catch (Exception e) {
        }
    }

    public void UnScrollAbajoInicio(Actor actor) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator(
                            "new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\").scrollable(true)).scrollForward()");
        } catch (Exception e) {
        }
    }

    public void SwipeToElement(Actor actor, String label) {
        androidDriver(actor).findElementByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().text(\""
                                + label + "\"));")
                .click();
    }

    public void UnScrollArribaInicio(Actor actor) {
        try {
            androidDriver(actor).findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().resourceIdMatches(\"android:id/list\").scrollable(true)).scrollBackward()");
        } catch (Exception e) {
            e.printStackTrace(); // Agregar para ver si hay algún error
        }
    }

    public void ElTextoContiene(Actor actor, String text) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")")
                    .isDisplayed();
        } catch (Exception e) {
            ExElTextoContiene(actor, text);
        }
    }

    public void ClickElTextoContiene(Actor actor, String text) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")")
                    .click();
        } catch (Exception e) {
            ExClickElTextoContiene(actor, text);
        }
    }

    public void ClickByText(Actor actor, String text) {
        try {
            actor.attemptsTo(WaitFor.aTime(1000));
            androidDriver(actor)
                    .findElementByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")")
                    .click();
        } catch (Exception e) {
            ExClickByText(actor, text);
        }
    }


    public void ScrollVertical(Actor actor, String label) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                    + "new UiSelector().text(\""
                                    + label
                                    + "\"));");
        } catch (Exception e) {
            ExScrollVertical(actor, label);
        }
    }

    //


    public void scrollPorCoordenadas(AppiumDriver driver, int startX, int startY, int endX, int endY) {
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(ofMillis(800)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }


    public void swipeVertical(Actor actor, double inicioRatio, double finRatio, double duracionSegs) {
        Dimension dimension = androidDriver(actor).manage().window().getSize();
        int ancho = dimension.width / 2; // El ancho siempre será el centro de la pantalla
        int inicioY = (int) (dimension.height * inicioRatio);
        int finY = (int) (dimension.height * finRatio);
        new TouchAction<>(androidDriver(actor))
                .press(PointOption.point(ancho, inicioY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds((long) duracionSegs)))
                .moveTo(PointOption.point(ancho, finY))
                .release()
                .perform();
    }

    protected boolean estaCercaDelCentro(AndroidDriver<MobileElement> driver, WebElement elemento) {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int elementoY = elemento.getLocation().getY();

        int margenSuperior = (int) (screenHeight * 0.3);
        int margenInferior = (int) (screenHeight * 0.7);

        return elementoY > margenSuperior && elementoY < margenInferior;
    }

    public void centrarElementoSuavemente(Actor actor, WebElement elemento) {
        int screenHeight = androidDriver(actor).manage().window().getSize().getHeight();
        int elementoY = elemento.getLocation().getY();

        int movimiento = (screenHeight / 2) - elementoY;

        System.out.println("📌 Ajustando texto suavemente: " + movimiento + " píxeles");

        if (Math.abs(movimiento) > 20) { // Evitamos movimientos muy pequeños
            swipeVertical(actor, 0.5, 0.5 + (movimiento / (double) screenHeight), 0.3);
        }
    }

    public void scrollCorto2(Actor actor, String textoOpcional) {
        int intentosMaximos = 10;
        boolean textoEncontrado = false;

        try {
            AndroidDriver<MobileElement> driver = androidDriver(actor);

            //  **PRIMERA VERIFICACIÓN SIN SCROLL**
            if (textoOpcional != null && !textoOpcional.isEmpty()) {
                List<MobileElement> elementos =
                        driver.findElementsByAndroidUIAutomator(
                                "new UiSelector().textContains(\"" + textoOpcional + "\")");

                if (!elementos.isEmpty()) {
                    for (WebElement elemento : elementos) {
                        if (elemento.isDisplayed()) {
                            System.out.println(" Texto visible de entrada: " + textoOpcional);
                            return; //  NO HACEMOS SCROLL
                        }
                    }
                }
            }

            // **BÚSQUEDA CON SCROLL**
            for (int intento = 1; intento <= intentosMaximos; intento++) {
                try {
                    List<MobileElement> elementos =
                            driver.findElementsByAndroidUIAutomator(
                                    "new UiSelector().textContains(\"" + textoOpcional + "\")");

                    if (!elementos.isEmpty()) {
                        for (WebElement elemento : elementos) {
                            if (elemento.isDisplayed()) {
                                System.out.println(
                                        "Verificando elemento en intento #"
                                                + intento
                                                + " -> isDisplayed(): "
                                                + elemento.isDisplayed());

                                // 🚨 NUEVO: Comprobamos si ya está en una posición segura antes de centrar
                                if (estaCercaDelCentro(driver, elemento)) {
                                    System.out.println("✅ Texto ya está bien posicionado: " + textoOpcional);
                                    return;
                                }

                                // Si el texto no está centrado, lo movemos solo un poco
                                centrarElementoSuavemente(actor, elemento);
                                System.out.println(
                                        "✅ Texto encontrado y ajustado: " + textoOpcional + " en intento #" + intento);
                                return;
                            }
                        }
                    }

                    //  **Realizamos un scroll controlado**
                    System.out.println(" Realizando scroll, intento #" + intento);
                    swipeVertical(actor, 0.7, 0.5, 0.3);
                    Thread.sleep(800);

                } catch (Exception e) {
                    System.out.println(" Error en el intento de scroll #" + intento);
                    e.printStackTrace();
                }
            }

            System.out.println(" No se encontró el texto después de " + intentosMaximos + " intentos.");

        } catch (Exception e) {
            System.out.println(" Ocurrió un error en la operación de scroll.");
            e.printStackTrace();
        }
    }

    public void scrollCorto3(Actor actor, String textoOpcional) {
        int intentosMaximos = 1; // Número máximo de intentos de scroll
        boolean textoEncontrado = false;

        try {
            for (int intento = 1; intento <= intentosMaximos; intento++) {
                try {
                    if (textoOpcional != null && !textoOpcional.isEmpty()) {
                        // Intentar encontrar el texto en la vista actual
                        List<WebElement> elementos =
                                androidDriver(actor)
                                        .findElementsByAndroidUIAutomator(
                                                "new UiSelector().className(\"android.view.View\").textContains(\""
                                                        + textoOpcional
                                                        + "\")");

                        if (!elementos.isEmpty()) {
                            textoEncontrado = true;

                            // Obtener el elemento que contiene el texto
                            WebElement elemento = elementos.get(0);

                            // Ajustar la posición para centrar el texto
                            centrarElementoSuavemente(actor, elemento);

                            System.out.println(
                                    "Texto encontrado: " + textoOpcional + " en el intento #" + intento);
                            break; // Salir del bucle si el texto es encontrado
                        }
                    }

                    // Si no se encuentra el texto, o no se está buscando texto, hacer scroll hacia abajo
                    System.out.println(
                            "Realizando scroll, intento #"
                                    + intento
                                    + (textoOpcional != null ? " buscando: " + textoOpcional : ""));
                    swipeVertical(actor, 0.8, 0.3, 0.3); // Realizar scroll corto y lento
                } catch (NoSuchElementException e) {
                    System.out.println("No se encontró el texto en este intento. Continuando scroll...");
                } catch (Exception e) {
                    System.out.println("Error durante el intento de scroll #" + intento);
                    e.printStackTrace();
                }
            }

            if (!textoEncontrado && textoOpcional != null) {
                System.out.println(
                        "No se encontró el texto especificado después de " + intentosMaximos + " intentos.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error durante la operación de scroll.");
            e.printStackTrace();
        }
    }

    public void ScrollVerticalClass(Actor actor, String label) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator(
                            "new UiScrollable(new UiSelector().className(\"android.view.View\").scrollable(true)).scrollIntoView("
                                    + "new UiSelector().text(\""
                                    + label
                                    + "\"));");
        } catch (Exception e) {
            ExScrollVerticalClass(actor, label);
        }
    }


    public void ValidarTexto(Actor actor, String text) {
        try {
            androidDriver(actor)
                    .findElementByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")")
                    .isDisplayed();
        } catch (Exception e) {
            ExValidarTexto(actor, text);
        }
    }

    public void Atras(Actor actor) {
        androidDriver(actor).navigate().back();
    }


 /*   String texto = "";

    public ArrayList<Character> LeerMensaje(Actor actor) {
        androidDriver(actor).openNotifications();
        actor.attemptsTo(WaitFor.aTime(3000));
        try {
            texto =
                    androidDriver(actor)
                            .findElementByAndroidUIAutomator(
                                    "new UiSelector().textContains(\"Mensajes\")")
                            .getText();
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<Character> lista = new ArrayList<>();
        for (int i = 0; i < texto.length(); i++) {
            if (Character.isDigit(texto.charAt(i))) {
                lista.add(texto.charAt(i));
            }
        }

        return lista;
    } */

    // prueba

    String texto = "";

    public ArrayList<Character> LeerMensaje(Actor actor) {
        // Abrir notificaciones
        androidDriver(actor).openNotifications();
        actor.attemptsTo(WaitFor.aTime(3000));  // Esperar más tiempo por si el mensaje demora

        // Posibles textos que pueden venir en el SMS
        String[] posiblesTextos = {
                "Codigo", "Código", "verificacion"
        };

        // Buscar el texto en las notificaciones
        for (String clave : posiblesTextos) {
            try {
                texto = androidDriver(actor)
                        .findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + clave + "\")")
                        .getText();
                if (texto != null && !texto.isEmpty()) {
                    System.out.println("📩 Mensaje encontrado: " + texto);
                    break;
                }
            } catch (Exception e) {
                // Continuar con el siguiente término si no encuentra este
            }
        }

        // Extraer solo los dígitos del mensaje
        ArrayList<Character> lista = new ArrayList<>();
        if (texto != null && !texto.isEmpty()) {
            for (int i = 0; i < texto.length(); i++) {
                if (Character.isDigit(texto.charAt(i))) {
                    lista.add(texto.charAt(i));
                }
            }
            System.out.println("🔐 Código detectado: " + lista.toString());
        } else {
            System.out.println("⚠️ No se encontró ningún mensaje con código.");
        }

        return lista;
    }


    public void DigitarNumeros(Actor actor, String digito) {
        switch (digito) {
            case "0": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_0)); break;
            case "1": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_1)); break;
            case "2": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_2)); break;
            case "3": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_3)); break;
            case "4": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_4)); break;
            case "5": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_5)); break;
            case "6": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_6)); break;
            case "7": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_7)); break;
            case "8": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_8)); break;
            case "9": androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_9)); break;
        }
    }


    public AndroidDriver getAndroidDriver(Actor actor) {
        return androidDriver(actor);
    }

    private static WebDriverFacade getDriver(Actor actor) {
        return ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver());
    }

    public TouchAction withAction(Actor actor) {
        return new TouchAction(androidDriver(actor));
    }

    public void SwitchtoFrame(Actor actor, int id) {
        androidDriver(actor).switchTo().frame(id);
    }
}