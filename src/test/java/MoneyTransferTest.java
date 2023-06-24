
import com.codeborne.selenide.Configuration;
import data.DataHelper;

import org.junit.jupiter.api.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferPage;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.*;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @AfterEach
    void memoryClear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Valid transfer from 2nd card to 1st")
    void shouldTransferValidAmountFrom2To1() {
        var firstCard = DataHelper.getFirstCardInfo();
        var initialBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        var amount = generateValidAmount(initialBalance2);
        var transferPage = dashboardPage.deposit1();
        dashboardPage = transferPage.validTransfer(String.valueOf(amount), secondCard.getNumber());
        var checkBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var checkBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        assertEquals(initialBalance1 + amount, checkBalance1);
        assertEquals(initialBalance2 - amount, checkBalance2);
    }

    @Test
    @DisplayName("Valid transfer from 1st card to 2nd")
    void shouldTransferValidAmountFrom1To2() {

        var firstCard = DataHelper.getFirstCardInfo();
        var initialBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        var amount = generateValidAmount(initialBalance1);
        var transferPage = dashboardPage.deposit2();
        dashboardPage = transferPage.validTransfer(String.valueOf(amount), firstCard.getNumber());
        var checkBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var checkBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        assertEquals(initialBalance1 - amount, checkBalance1);
        assertEquals(initialBalance2 + amount, checkBalance2);
    }

    @Test
    @DisplayName("Invalid transfer from 2nd card to 1st")
    void balanceShouldNotBeNegativeAfterTransferFrom2To1() {
        var firstCard = DataHelper.getFirstCardInfo();
        var initialBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        var amount = generateValidAmount(initialBalance2);
        var transferPage = dashboardPage.deposit1();
        var invalidTransfer = transferPage.invalidTransfer(String.valueOf(amount), secondCard.getNumber());
    }

    @Test
    @DisplayName("Invalid transfer from 1st card to 2nd")
    void balanceShouldNotBeNegativeAfterTransferFrom1To2() {
        var firstCard = DataHelper.getFirstCardInfo();
        var initialBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        var amount = generateValidAmount(initialBalance1);
        var transferPage = dashboardPage.deposit2();
        var invalidTransfer = transferPage.invalidTransfer(String.valueOf(amount), firstCard.getNumber());
        var checkBalance1 = dashboardPage.getCardBalance(firstCard);
        var checkBalance2 = dashboardPage.getCardBalance(secondCard);
        assertEquals(initialBalance2, checkBalance2);
        assertEquals(initialBalance1, checkBalance1);
    }

    @Test
    @DisplayName("Transfer from the same card(1st)")
    void shouldNotTransferFromSameCard1() {
        var firstCard = DataHelper.getFirstCardInfo();
        var initialBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        var amount = generateValidAmount(initialBalance1);
        var transferPage = dashboardPage.deposit1();
        var sameCardTransfer = transferPage.sameCardTransfer(String.valueOf(amount), firstCard.getNumber());
    }

    @Test
    @DisplayName("Transfer from the same card(2nd)")
    void shouldNotTransferFromSameCard2() {
        var firstCard = DataHelper.getFirstCardInfo();
        var initialBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        var amount = generateValidAmount(initialBalance2);
        var transferPage = dashboardPage.deposit2();
        var sameCardTransfer = transferPage.sameCardTransfer(String.valueOf(amount), secondCard.getNumber());
    }

    @Test
    @DisplayName("No amount transfer from 1st card")
    void shouldNotTransferNullAmount1() {
        var firstCard = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.deposit2();
        var nullAmountTransfer = transferPage.nullAmountTransfer(firstCard.getNumber());
    }

    @Test
    @DisplayName("No amount transfer from 2nd card")
    void shouldNotTransferNullAmount2() {
        var secondCard = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.deposit1();
        var nullAmountTransfer = transferPage.nullAmountTransfer(secondCard.getNumber());
    }

    @Test
    @DisplayName("No card number transfer from 1st card")
    void shouldNotTransferWithoutCardNumberFrom1() {
        var firstCard = DataHelper.getFirstCardInfo();
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance1 = dashboardPage.getCardBalance(getFirstCardInfo());
        var amount = generateValidAmount(initialBalance1);
        var transferPage = dashboardPage.deposit2();
        var nullCardTransfer = transferPage.nullCardTransfer(String.valueOf(amount));
    }

    @Test
    @DisplayName("No card number transfer from 2nd card")
    void shouldNotTransferWithoutCardNumberFrom2() {
        var firstCard = DataHelper.getFirstCardInfo();
        var secondCard = DataHelper.getSecondCardInfo();
        var initialBalance2 = dashboardPage.getCardBalance(getSecondCardInfo());
        var amount = generateValidAmount(initialBalance2);
        var transferPage = dashboardPage.deposit1();
        var nullCardTransfer = transferPage.nullCardTransfer(String.valueOf(amount));
    }
}
