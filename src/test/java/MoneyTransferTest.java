import data.DataHelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferPage;

import static com.codeborne.selenide.Selenide.open;

 class MoneyTransferTest {
     @Test
     void balanceShouldNotBeNegativeAfterTransfer () {
         open("http://localhost:9999");
         var loginPage = new LoginPage();
//    var loginPage = open("http://localhost:9999", LoginPage.class);
         var authInfo = DataHelper.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
         verificationPage.validVerify(verificationCode);
         var deposit1 = DashboardPage.deposit1();
         var transferPage = new TransferPage();
         var invalidTransfer = transferPage.invalidTransfer();
     }
     @Test
     void shouldTransferValidAmount () {
         open("http://localhost:9999");
         var loginPage = new LoginPage();
//    var loginPage = open("http://localhost:9999", LoginPage.class);
         var authInfo = DataHelper.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
         verificationPage.validVerify(verificationCode);
         var deposit1 = DashboardPage.deposit1();
         var transferPage = new TransferPage();
         var validTransfer = TransferPage.validTransfer();
         var checkBalance1 = DashboardPage.getFirstCardBalance();
         var checkBalance2 = DashboardPage.getSecondCardBalance();
         var initialBalance1 = DataHelper.getFirstCardInfo().getBalance();
         var initialBalance2 = DataHelper.getSecondCardInfo().getBalance();
         var transferAmount = DataHelper.generateValidAmount(DataHelper.getFirstCardInfo().getBalance());
         Assertions.assertEquals(initialBalance1 + transferAmount, checkBalance1);
         Assertions.assertEquals(initialBalance2 - transferAmount, checkBalance2);
     }

 }
