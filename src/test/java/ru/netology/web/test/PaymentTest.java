package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLunits;
import ru.netology.web.page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openChrome() {
        String url = System.getProperty("sut.url");
        open(url);
        Configuration.holdBrowserOpen = true;
    }

    @AfterAll
    static void cleanDataBases() {
        SQLunits.cleanDb();
    }

    @DisplayName("1.Successful card purchase.")
    @Test
    void confirmPaymentApprovedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.successfulCardPayment();

        var expectedStatus = "APPROVED";
        var actualStatus = SQLunits.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("2.Card - Declined card.")
    @Test
    public void payDeclinedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCardInformation);
        payCard.notSuccessfulCardPayment();

        var expectedStatus = "DECLINED";
        var actualStatus = SQLunits.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("3.Card - All blank data.")
    @Test
    public void payEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("4.Card - Expired year.")
    @Test
    public void payExpiredYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredYear();
        payCard.enterCardData(invalidCard);
        payCard.expiredCardYear();
    }

    @DisplayName("5.Card - Expired month.")
    @Test
    public void payExpiredMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("6.Card - Invalid card number.")
    @Test
    public void payInvalidNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("7.Card - Wrong year.")
    @Test
    public void payWrongYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongYear();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("8.Card - Wrong month.")
    @Test
    public void payWrongMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("9.Сard - Numeric holder's name.")
    @Test
    public void payNumericHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getNumericName();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("10.Card - Invalid CVV.")
    @Test
    public void payInvalidCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("11.Card - Zero card number.")
    @Test
    public void payZeroNumberCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCard();
        payCard.enterCardData(invalidCard);
        payCard.notSuccessfulCardPayment();
    }

    @DisplayName("12.Card - Zero month.")
    @Test
    public void payZeroMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("13.Card - Zero CVV")
    @Test
    public void payZeroCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

}

