package utils.enums;

import static utils.Utilities.getEnvironments;

public enum AppPackage {
  APP_DEV("Dev", "com.google.android.dialer"),
  APP_PROD("Prod", "com.google.android.dialer");

  private final String environment;
  private final String urlPackage;

  AppPackage(String environment, String urlPackage) {
    this.environment = environment;
    this.urlPackage = urlPackage;
  }

  public String getEnvironment() {
    return environment;
  }

  public String getUrlPackage() {
    return urlPackage;
  }

  public static String getAppPackage() {
    String packages = null;
    for (AppPackage appPackage : AppPackage.values()) {
      if (getEnvironments().equals(appPackage.getEnvironment())) {
        packages = appPackage.getUrlPackage();
        break;
      }
    }
    return packages;
  }
}
