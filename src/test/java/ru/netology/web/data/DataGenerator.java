package ru.netology.web.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.github.javafaker.Faker;
import java.util.Locale;

public class DataGenerator {

    private static final Faker fakerEn = new Faker(new Locale("en"));

    public static String getValidYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getPastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNextMonth() {
        return LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInvalidFormatMonth() {
        return fakerEn.number().digit();
    }

    public static String getInvalidFormatYear() {
        return fakerEn.number().digit();
    }

}

