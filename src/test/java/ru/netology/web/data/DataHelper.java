package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.util.Locale;
import ru.netology.web.data.DataGenerator;

public class DataHelper {
    static Faker enfaker = new Faker(new Locale("en"));
    static Faker rufaker = new Faker(new Locale("ru"));
    static CardNumber cardNumber = new CardNumber();

    public static CardInformation getValidCard() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                DataGenerator.getValidYear(),
                DataGenerator.getValidMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getDeclinedCard() {
        return new CardInformation(
                cardNumber.getDeclinedCardNumber(),
                DataGenerator.getValidYear(),
                DataGenerator.getValidMonth(),
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
                DataGenerator.getPastYear(),
                DataGenerator.getValidMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getExpiredMonth() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                DataGenerator.getValidYear(),
                DataGenerator.getPastMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getInvalidNumber() {
        return new CardInformation(
                cardNumber.getInvalidCardNumber(),
                DataGenerator.getValidYear(),
                DataGenerator.getValidMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getWrongYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                DataGenerator.getInvalidFormatYear(),
                DataGenerator.getNextMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getWrongMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                DataGenerator.getNextYear(),
                DataGenerator.getInvalidFormatMonth(),
                enfaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getNumericName() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                DataGenerator.getNextYear(),
                DataGenerator.getNextMonth(),
                Integer.toString(enfaker.number().numberBetween(1, 999)),
                enfaker.number().digits(3));
    }

    public static CardInformation getInvalidCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                DataGenerator.getNextYear(),
                DataGenerator.getNextMonth(),
                rufaker.name().fullName(),
                enfaker.number().digits(2));
    }

    public static CardInformation getZeroCard() {
        return new CardInformation(
                "0000000000000000",
                DataGenerator.getNextYear(),
                DataGenerator.getNextMonth(),
                rufaker.name().fullName(),
                enfaker.number().digits(3));
    }

    public static CardInformation getZeroMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                DataGenerator.getNextYear(),
                "00",
                rufaker.name().fullName(),
                enfaker.number().digits(3));

    }

    public static CardInformation getZeroCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                DataGenerator.getNextYear(),
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
