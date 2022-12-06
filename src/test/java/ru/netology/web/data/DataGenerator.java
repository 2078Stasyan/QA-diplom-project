package ru.netology.web.data;

import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    private final LocalDate actualData = LocalDate.now();
    private final DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("yy");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");

    public Year shiftYear(int numberOfYears) {
        LocalDate newDate = actualData.plusYears(numberOfYears);
        return new Year(formatterYears.format(newDate));
    }

    public Year wrongYear(int numberOfWrongY) {
        LocalDate wrongDate = actualData.plusYears(numberOfWrongY);
        return new Year(formatterYears.format(wrongDate));
    }

    public Month shiftMonth(int numberOfMonths) {
        LocalDate newDate = actualData.plusMonths(numberOfMonths);
        return new Month(formatterMonth.format(newDate));
    }

    public Month wrongMonth(int numberOfWrongM) {
        LocalDate wrongDate = actualData.plusYears(numberOfWrongM);
        return new Month(formatterYears.format(wrongDate));
    }

    @Value
    public static class Year {
        private String year;
    }

    @Value
    public static class Month {
        private String month;
    }
}