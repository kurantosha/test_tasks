package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateUtils {

    public static String getActualYesterdayDate() {
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ENGLISH));
        return yesterday + " - " + yesterday;
    }

    public static String getActualTodayDate() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.US));
        return today + " - " + today;
    }

    public static String getActualTomorrowDate() {
        String tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.US));
        return tomorrow + " - " + tomorrow;
    }

    public static String getActualThisWeekDate() {
        String sundayOfThisWeek = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1).minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.US));
        String saturdayOfThisWeek = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 6).format(DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.US));
        return sundayOfThisWeek + " - " + saturdayOfThisWeek;
    }
}
