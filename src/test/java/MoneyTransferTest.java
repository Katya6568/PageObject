import data.DataHelper;

import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferPage;

import static com.codeborne.selenide.Selenide.open;

 class MoneyTransferTest {
     @Test
     void shouldTransferMoneyBetweenOwnCardsV1() {
         open("http://localhost:9999");
         var loginPage = new LoginPage();
//    var loginPage = open("http://localhost:9999", LoginPage.class);
         var authInfo = DataHelper.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
         verificationPage.validVerify(verificationCode);
         var deposit1 = DashboardPage.deposit1();
         var validTransfer = TransferPage.validTransfer();





     }

 }
