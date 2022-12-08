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
                dataGenerator.getValidYear(),
                dataGenerator.getValidMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getDeclinedCard() {
        return new CardInformation(
                cardNumber.getDeclinedCardNumber(),
                dataGenerator.getValidYear(),
                dataGenerator.getValidMonth(),
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
                dataGenerator.getPastYear(),
                dataGenerator.getValidMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getExpiredMonth() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                dataGenerator.getValidYear(),
                dataGenerator.getPastMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getInvalidNumber() {
        return new CardInformation(
                cardNumber.getInvalidCardNumber(),
                dataGenerator.getValidYear(),
                dataGenerator.getValidMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getWrongYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.getInvalidFormatYear(),
                dataGenerator.getNextMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getWrongMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.getNextYear(),
                dataGenerator.getInvalidFormatMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getNumericName() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.getNextYear(),
                dataGenerator.getNextMonth(),
                Integer.toString(enfaker.number().numberBetween(1, 999)),
                enfaker.number().digits(3));
    }

    public static CardInformation getInvalidCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.getNextYear(),
                dataGenerator.getNextMonth(),
                rufaker.name().fullName(),
                enfaker.number().digits(2));
    }

    public static CardInformation getZeroCard() {
        return new CardInformation(
                "0000000000000000",
                dataGenerator.getNextYear(),
                dataGenerator.getNextMonth(),
                rufaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getZeroMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.getNextYear(),
                "00",
                rufaker.name().fullName(),
                enfaker.number().digits(3));

    }

    public static CardInformation getZeroCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.getNextYear(),
                DataGenerator.getNextMonth(),
                rufaker.name().fullName(),
                "000");
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
