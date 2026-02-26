package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnviaromentProperties {

  private static Properties properties = new Properties();
  private static final String PROPERTIES_FILE = "serenity.properties";

  public static void initProperties() throws IOException {
    properties.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
  }

  public static String getProperty(String nameProperty) {
    try {
      initProperties();
      return properties.getProperty(nameProperty);
    } catch (IOException e) {
      e.printStackTrace();
      Logger.getAnonymousLogger()
          .log(Level.WARNING, "ocurrio un error obteniendo la propieda" + nameProperty);
      return null;
    }
  }
}
