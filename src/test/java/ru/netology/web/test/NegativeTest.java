package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLunits;
import ru.netology.web.page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.SQLunits.*;

public class NegativeTest {

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
        //Configuration.holdBrowserOpen = true;
    }

    @AfterAll
    static void cleanDataBases() {
        SQLunits.cleanDb();
    }


    @DisplayName("1.Card - Declined card.")
    @Test
    public void PayDeclinedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCardInformation);
        payCard.notSuccessfulCardPayment();

        val paymentId = getPaymentId();
        val expectedStatus = "DECLINED";
        val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("1.Credit - Declined card.")
    @Test
    public void CreditDeclinedCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        buyCredit.enterCreditCardData(declinedCardInformation);
        buyCredit.notSuccessfulCreditCardPayment();

        val paymentId = getPaymentId();
        val expectedStatus = "DECLINED";
        val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("2.Card - All blank data.")
    @Test
    public void PayEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("2.Credit - All blank data.")
    @Test
    public void CreditEmptyForm() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        buyCredit.enterCreditCardData(emptyCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("3.Card - Expired year.")
    @Test
    public void PayExpiredYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredYear();
        payCard.enterCardData(invalidCard);
        payCard.expiredCardYear();
    }

    @DisplayName("3.Credit - Expired year.")
    @Test
    public void CreditExpiredYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCardYear();
    }

    @DisplayName("4.Card - Expired month.")
    @Test
    public void PayExpiredMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("4.Credit - Expired month.")
    @Test
    public void CreditExpiredMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("5.Card - Invalid card number.")
    @Test
    public void PayInvalidNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("5.Credit - Invalid card number.")
    @Test
    public void CreditInvalidNumber() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidNumber();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("6.Card - Wrong year.")
    @Test
    public void PayWrongYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongYear();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("6.Credit - Wrong year.")
    @Test
    public void CreditWrongYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getWrongYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("7.Card - Wrong month.")
    @Test
    public void PayWrongMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("7.Credit - Wrong month.")
    @Test
    public void CreditWrongMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getWrongMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("8.Сard - Numeric holder's name.")
    @Test
    public void PayNumericHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getNumericName();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("8.Сredit - Numeric holder's name.")
    @Test
    public void CreditNumericHolder() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getNumericName();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("9.Card - Invalid CVV.")
    @Test
    public void PayInvalidCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("9.Credit - Invalid CVV.")
    @Test
    public void CreditInvalidCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("10.Card - Zero card number.")
    @Test
    public void PayZeroNumberCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCard();
        payCard.enterCardData(invalidCard);
        payCard.notSuccessfulCardPayment();
    }

    @DisplayName("10.Credit - Zero card number.")
    @Test
    public void CreditZeroNumberCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCard();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.notSuccessfulCreditCardPayment();
    }

    @DisplayName("11.Card - Zero month.")
    @Test
    public void PayZeroMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("11.Credit- Zero month.")
    @Test
    public void CreditZeroMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("12.Card - Zero CVV")
    @Test
    public void PayZeroCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("12.Credit - Zero CVV")
    @Test
    public void CreditZeroCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

}

