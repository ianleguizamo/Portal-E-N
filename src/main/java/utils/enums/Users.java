package utils.enums;

import static utils.Utilities.getEnvironments;

import java.util.HashMap;
import java.util.Map;

public enum Users {
  USER_ALIADO_DEV("Dev", "HWfkj3m7Xdi5j0uiKsVzjA==", "dBPGZBDL7OYoJX1l7BtU9w=="),
  USER_ALIADO_PROD(
      "Prod", "NFb2F7CsV/gICIw5+0bpQGuEt+TYTrWbilOYWSSPHew=", "dBPGZBDL7OYoJX1l7BtU9w==");

  private final String enviroment;
  private final String user;
  private final String password;

  Users(String enviroment, String user, String password) {
    this.enviroment = enviroment;
    this.user = user;
    this.password = password;
  }

  public String getEnviroment() {
    return enviroment;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public static Map<String, String> getUsers() {
    Map<String, String> listUsers = new HashMap<>();
    for (Users user : Users.values()) {
      if (user.getEnviroment().equals(getEnvironments())) {
        listUsers.put("user", user.getUser());
        listUsers.put("password", user.getPassword());
        break;
      }
    }
    return listUsers;
  }
}
