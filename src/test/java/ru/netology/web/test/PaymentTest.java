package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLunits;
import ru.netology.web.page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.SQLunits.getPaymentId;
import static ru.netology.web.data.SQLunits.getStatusForPaymentWithDebitCard;

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
        open("http://localhost:8080/");
        Configuration.holdBrowserOpen = true;
    }

    @AfterAll
    static void cleanDataBases() {
        SQLunits.cleanDb();
    }

    @DisplayName("1.Successful card purchase.")
    @Test
    void ConfirmPaymentApprovedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.successfulCardPayment();

        var paymentId = getPaymentId();
        var expectedStatus = "APPROVED";
        var actualStatus = getStatusForPaymentWithDebitCard(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("2.Card - Declined card.")
    @Test
    public void PayDeclinedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCardInformation);
        payCard.notSuccessfulCardPayment();

        var paymentId = getPaymentId();
        var expectedStatus = "DECLINED";
        var actualStatus = getStatusForPaymentWithDebitCard(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("3.Card - All blank data.")
    @Test
    public void PayEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("4.Card - Expired year.")
    @Test
    public void PayExpiredYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredYear();
        payCard.enterCardData(invalidCard);
        payCard.expiredCardYear();
    }

    @DisplayName("5.Card - Expired month.")
    @Test
    public void PayExpiredMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("6.Card - Invalid card number.")
    @Test
    public void PayInvalidNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("7.Card - Wrong year.")
    @Test
    public void PayWrongYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongYear();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("8.Card - Wrong month.")
    @Test
    public void PayWrongMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("9.Сard - Numeric holder's name.")
    @Test
    public void PayNumericHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getNumericName();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("10.Card - Invalid CVV.")
    @Test
    public void PayInvalidCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("11.Card - Zero card number.")
    @Test
    public void PayZeroNumberCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCard();
        payCard.enterCardData(invalidCard);
        payCard.notSuccessfulCardPayment();
    }

    @DisplayName("12.Card - Zero month.")
    @Test
    public void PayZeroMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("13.Card - Zero CVV")
    @Test
    public void PayZeroCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

}

