package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLunits;
import ru.netology.web.page.TourPage;
import lombok.val;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLunits.*;

public class PositiveTest {


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

    @DisplayName("1.Successful card purchase.")
    @Test
    void ConfirmPaymentApprovedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.successfulCardPayment();

        val paymentId = getPaymentId();
        val expectedStatus = "APPROVED";
        val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

    @DisplayName("2.Successful credit purchase")
    @Test
    public void ConfirmCreditApprovedCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        buyCredit.enterCreditCardData(approvedCardInformation);
        buyCredit.successfulCreditCardPayment();

        val paymentId = getPaymentId();
        val expectedStatus = "APPROVED";
        val actualStatus = getStatusForPaymentWithCreditCard(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }
}



