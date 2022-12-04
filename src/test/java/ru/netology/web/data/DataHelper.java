package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    static Faker enfaker = new Faker(new Locale("en"));
    static Faker rufaker = new Faker(new Locale("ru"));
    static DataGenerator dataGenerator = new DataGenerator();
    static CardNumber cardNumber = new CardNumber();

    public static CardInformation getValidCard() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(1).getYear(),
                dataGenerator.shiftMonth(1).getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getCurrentMonthAndYear() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0).getYear(),
                dataGenerator.shiftMonth(0).getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getDeclinedCard() {
        return new CardInformation(
                cardNumber.getDeclinedCardNumber(),
                dataGenerator.shiftYear(1).getYear(),
                dataGenerator.shiftMonth(1).getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getAllFieldsEmpty() {
        return new CardInformation(
                " ",
                " ",
                " ",
                " ",
                " ");
    }

    public static CardInformation getExpiredYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(-1).getYear(),
                dataGenerator.shiftMonth(0).getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getExpiredMonth() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0).getYear(),
                dataGenerator.shiftMonth(-1).getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getInvalidNumber() {
        return new CardInformation(
                cardNumber.getInvalidCardNumber(),
                dataGenerator.shiftYear(1).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getWrongYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.wrongYear().getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getWrongMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(3).getYear(),
                dataGenerator.wrongMonth().getMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getNumericName() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                Integer.toString(enfaker.number().numberBetween(1, 999)),
                enfaker.number().digits(3));
    }

    public static CardInformation getInvalidCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                rufaker.name().fullName(),
                enfaker.number().digits(2));
    }

    public static CardInformation getZeroCard() {
        return new CardInformation(
                "0000000000000000",
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                rufaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getZeroMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0).getYear(),
                "00",
                rufaker.name().fullName(),
                enfaker.number().digits(3));

    }

    public static CardInformation getZeroCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                rufaker.name().fullName(),
                "000");
    }

    public static CardInformation getMonthEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(2).getYear(),
                " ",
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    @Value
    public static class CardInformation {
        private String cardNumber;
        private String year;
        private String month;
        private String holder;
        private String CVV;
    }
}
