package utils.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum MobileDevices {
  XIAOMI_REDMI_PRO_NOTE_10("e8bc2218", "11", "Android"),
  IPHONE_11_PRO("xxxxx", "xxxxx", "xxxxx"),
  SAMSUNG_GALAXY_FLIP3_5G("xxxxx", "xxxxx", "xxxxx"),
  XIAOMI_A3("Mi A3", "11", "Android"),
  MOTO_E20("moto e20", "11", "Android");

  private final String DeviceName;
  private final String Version;
  private final String OperatingSystem;

  MobileDevices(String deviceName, String version, String operatingSystem) {
    DeviceName = deviceName;
    Version = version;
    OperatingSystem = operatingSystem;
  }

  public String getDeviceName() {
    return DeviceName;
  }

  public String getVersion() {
    return Version;
  }

  public String getOperatingSystem() {
    return OperatingSystem;
  }

  public static Map<String, String> getMobileDevices() {
    Map<String, String> infoMobil = new LinkedHashMap<>();
    for (MobileDevices mobileDevices : MobileDevices.values()) {
      if ("Prueba".equals("Prueba")) {
        infoMobil.put("DeviceName", mobileDevices.getDeviceName());
        infoMobil.put("Version", mobileDevices.getDeviceName());
        infoMobil.put("OperatingSystem", mobileDevices.getDeviceName());
        break;
      }
    }
    return infoMobil;
  }
}
