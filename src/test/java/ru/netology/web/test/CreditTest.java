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


public class CreditTest {

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

    @DisplayName("1.Successful credit purchase")
    @Test
    public void confirmCreditApprovedCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        buyCredit.enterCreditCardData(approvedCardInformation);
        buyCredit.successfulCreditCardPayment();

        var expectedStatus = "APPROVED";
        var actualStatus = SQLunits.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("2.Credit - Declined card.")
    @Test
    public void creditDeclinedCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        buyCredit.enterCreditCardData(declinedCardInformation);
        buyCredit.notSuccessfulCreditCardPayment();

        var expectedStatus = "DECLINED";
        var actualStatus = SQLunits.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("3.Credit - All blank data.")
    @Test
    public void creditEmptyForm() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        buyCredit.enterCreditCardData(emptyCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("4.Credit - Expired year.")
    @Test
    public void creditExpiredYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCardYear();
    }

    @DisplayName("5.Credit - Expired month.")
    @Test
    public void creditExpiredMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("6.Credit - Invalid card number.")
    @Test
    public void creditInvalidNumber() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidNumber();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("7.Credit - Wrong year.")
    @Test
    public void creditWrongYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getWrongYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("8.Credit - Wrong month.")
    @Test
    public void creditWrongMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getWrongMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("9.Сredit - Numeric holder's name.")
    @Test
    public void CreditNumericHolder() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getNumericName();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("10.Credit - Invalid CVV.")
    @Test
    public void creditInvalidCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("11.Credit - Zero card number.")
    @Test
    public void creditZeroNumberCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCard();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.notSuccessfulCreditCardPayment();
    }

    @DisplayName("12.Credit- Zero month.")
    @Test
    public void creditZeroMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("13.Credit - Zero CVV")
    @Test
    public void creditZeroCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }
}
