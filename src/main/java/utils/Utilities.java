package utils;

import static utils.ManagerProperties.ENVIRONMENTS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Utilities {

  private static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class);
  private static Map<Integer, Integer> sizes = new LinkedHashMap<>();

  public static String getDataBaseReport(ResultSet resultSet) throws SQLException {
    Map<Integer, List<String>> data = new LinkedHashMap<>();
    data.put(0, getColumnName(resultSet));
    int accountant = 1;
    while (resultSet.next()) {
      List<String> info = new ArrayList<>();
      for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
        getSize(i, resultSet.getString(i).length());
        info.add(resultSet.getString(i));
      }
      data.put(accountant, info);
      accountant++;
    }
    return getDataTable(data);
  }

  public static List<String> getColumnName(ResultSet resultSet) throws SQLException {
    List<String> columnName = new ArrayList<>();
    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
      columnName.add(resultSet.getMetaData().getColumnName(i));
      sizes.put(i, resultSet.getMetaData().getColumnName(i).length());
    }
    return columnName;
  }

  public static String getDataTable(Map<Integer, List<String>> data) {
    List<String> fields = new ArrayList<>();
    List<String> info = new ArrayList<>();
    data.forEach(
        (i, j) -> {
          IntStream.range(0, j.size())
              .forEach(p -> fields.add(addBlanks(sizes.get(p + 1), j.get(p))));
          info.add("| " + String.join(" | ", fields) + " | \n");
          fields.clear();
        });
    return String.join("", info);
  }

  public static void getSize(Integer position, Integer size) {
    if (sizes.get(position) < size) {
      sizes.put(position, size);
    }
  }

  public static String addBlanks(int size, String value) {
    return String.format("%-" + size + "s", value);
  }

  public static int getMonthDate(String date, String format) {
    Calendar c = new GregorianCalendar();
    try {
      c.setTime(new SimpleDateFormat(format).parse(date));
    } catch (ParseException e) {
      LOGGER.error(String.valueOf(e));
    }
    return c.get(Calendar.MONTH) + 1;
  }

  public static String getCurrentDate(String format) {
    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return new SimpleDateFormat(format).format(calendar.getTime());
  }

  public static String getFutureOrPastDate(String format, int days) {
    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_YEAR, days);
    return new SimpleDateFormat(format).format(calendar.getTime());
  }

  public static int getDayDate(String date, String format) {
    Calendar c = new GregorianCalendar();
    try {
      c.setTime(new SimpleDateFormat(format).parse(date));
    } catch (ParseException e) {
      LOGGER.error(String.valueOf(e));
    }
    return c.get(Calendar.DAY_OF_MONTH);
  }

  public static String getRandomDriver() {
    String[] drivers = {"chrome", "firefox", "edge"};
    int r = (int) Math.floor(Math.random() * drivers.length);
    return drivers[r];
  }

  public static String construirXPathBotonComprar(String paqueteText) {
    return "//android.widget.FrameLayout[//android.widget.TextView[contains(@text, '"
        + paqueteText
        + "')]]//android.widget.Button";
  }

  public static Double getValue(Target target, Actor actor) {
    return Double.valueOf(
        Text.of(target).viewedBy(actor).asString().split(" ")[0].replace("$", "").replace(",", ""));
  }

  public static String getEnvironments() {
    return ENVIRONMENTS.getString("environment");
  }

  public static Integer obtenerValor(Target target, Actor actor) {
    return Integer.valueOf(
        Text.of(target).viewedBy(actor).asString().split(" ")[0].replace("$", "").replace(",", ""));
  }

  private Utilities() {}
}
