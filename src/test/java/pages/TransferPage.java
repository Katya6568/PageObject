package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import static data.DataHelper.getSecondCardInfo;

public class TransferPage {
    private static SelenideElement amount = $("[data-test-id= 'amount'] input");
    private static SelenideElement from = $("[data-test-id= 'from'] input");
    private static SelenideElement to = $("[data-test-id= 'to'] input");
    private static SelenideElement transferButton = $("[data-test-id= 'action-transfer']");
    private static SelenideElement errorNotification = $("[data-test-id= 'error-notification']");
    private static SelenideElement cancelButton = $("[data-test-id= 'action-cancel']");

    public static DashboardPage validTransfer() {
        amount.setValue(DataHelper.generateValidAmount(getSecondCardInfo().getBalance()));
        from.setValue(DataHelper.getFirstCardInfo().getNumber());
        transferButton.click();
        return new DashboardPage();
    }

}
