
import com.codeborne.selenide.Configuration;
import data.DataHelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferPage;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

     DashboardPage dashboardPage;

     @BeforeEach
     void setUp() {
         open("http://localhost:9999");
         var loginPage = new LoginPage();
//    var loginPage = open("http://localhost:9999", LoginPage.class);
         var authInfo = DataHelper.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
         verificationPage.validVerify(verificationCode);
     }

     @Test
     @DisplayName("Valid transfer from 2nd card to 1st")
     void shouldTransferValidAmountFrom2To1 () {
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
//     @Test
//     @DisplayName("Valid transfer from 1st card to 2nd")
//     void shouldTransferValidAmountFrom1To2 () {
//
//         var deposit2 = DashboardPage.deposit2();
//         var transferPage = new TransferPage();
//         var validTransfer = TransferPage.validTransfer(DataHelper.getFirstCardInfo().getBalance(), DataHelper.getFirstCardInfo().getNumber());
//         var checkBalance1 = DashboardPage.getFirstCardBalance();
//         var checkBalance2 = DashboardPage.getSecondCardBalance();
//         var initialBalance1 = DataHelper.getFirstCardInfo().getBalance();
//         var initialBalance2 = getSecondCardInfo().getBalance();
//         var transferAmount = generateValidAmount(DataHelper.getFirstCardInfo().getBalance());
//         assertEquals(initialBalance1 - transferAmount, checkBalance1);
//         assertEquals(initialBalance2 + transferAmount, checkBalance2);
//     }
//     @Test
//     @DisplayName("Invalid transfer from 2nd card to 1st")
//     void balanceShouldNotBeNegativeAfterTransferFrom2To1 () {
//
//         var deposit1 = DashboardPage.deposit1();
//         var transferPage = new TransferPage();
//         var invalidTransfer = transferPage.invalidTransfer(getSecondCardInfo().getBalance(), getSecondCardInfo().getNumber());
//     }
//     @Test
//     @DisplayName("Invalid transfer from 1st card to 2nd")
//     void balanceShouldNotBeNegativeAfterTransferFrom1To2 () {
//
//         var deposit2 = DashboardPage.deposit2();
//         var transferPage = new TransferPage();
//         var invalidTransfer = transferPage.invalidTransfer(DataHelper.getFirstCardInfo().getBalance(), DataHelper.getFirstCardInfo().getNumber());
//     }
//     @Test
//     @DisplayName("Transfer from the same card(1st)")
//     void shouldNotTransferFromSameCard1 () {
//
//         var deposit1 = DashboardPage.deposit1();
//         var transferPage = new TransferPage();
//         var sameCardTransfer = TransferPage.sameCardTransfer(DataHelper.getFirstCardInfo().getBalance(), DataHelper.getFirstCardInfo().getNumber());
//
//     }
//     @Test
//     @DisplayName("Transfer from the same card(2nd)")
//     void shouldNotTransferFromSameCard2 () {
//
//         var deposit2 = DashboardPage.deposit2();
//         var transferPage = new TransferPage();
//         var sameCardTransfer = TransferPage.sameCardTransfer(getSecondCardInfo().getBalance(), getSecondCardInfo().getNumber());
//
//     }
//     @Test
//     @DisplayName("No amount transfer 1st")
//     void shouldNotTransferNullAmount1 () {
//
//         var deposit1 = DashboardPage.deposit1();
//         var transferPage = new TransferPage();
//         var nullAmountTransfer = TransferPage.nullAmountTransfer(DataHelper.getFirstCardInfo().getNumber());
//     }
//     @Test
//     @DisplayName("No amount transfer 2nd")
//     void shouldNotTransferNullAmount2 () {
//
//         var deposit2 = DashboardPage.deposit2();
//         var transferPage = new TransferPage();
//         var nullAmountTransfer = TransferPage.nullAmountTransfer(getSecondCardInfo().getNumber());
//     }

 }
