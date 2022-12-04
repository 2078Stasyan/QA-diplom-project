package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TourPage {

    private final SelenideElement cardButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));

    public PaymentPage payCard() {
        cardButton.click();
        return new PaymentPage();
    }

    public CreditPage buyCredit() {
        creditButton.click();
        return new CreditPage();
    }
}
