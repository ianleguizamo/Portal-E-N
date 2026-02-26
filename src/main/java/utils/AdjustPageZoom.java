package utils;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class AdjustPageZoom implements Task {

  private int zoomLevel;

  public AdjustPageZoom(int zoomLevel) {
    this.zoomLevel = zoomLevel;
  }

  public static AdjustPageZoom to(int zoomLevel) {
    return new AdjustPageZoom(zoomLevel);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
    js.executeScript("document.body.style.zoom = arguments[0] + '%'", zoomLevel);
  }
}
