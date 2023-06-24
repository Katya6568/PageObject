package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class TransferPage {
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id= 'amount'] input");
    private SelenideElement from = $("[data-test-id= 'from'] input");
    private SelenideElement to = $("[data-test-id= 'to'] input");
    private SelenideElement transferButton = $("[data-test-id= 'action-transfer']");
    private SelenideElement errorNotification = $("[data-test-id= 'error-notification']");
    private SelenideElement cancelButton = $("[data-test-id= 'action-cancel']");

    public TransferPage() {
        transferHead.shouldBe(visible);
        amountField.shouldBe(visible);
        from.shouldBe(visible);
        to.shouldBe(visible);
        transferButton.shouldBe(visible);
        cancelButton.shouldBe(visible);
    }

    public void moneyTransfer(String amount, String otherCardNumber) {
        amountField.setValue(amount);
        from.setValue(otherCardNumber);
        transferButton.click();
    }

    public DashboardPage validTransfer(String amount, String otherCardNumber) {
        moneyTransfer(amount, otherCardNumber);
        return new DashboardPage();
    }

    public TransferPage invalidTransfer(String amount, String otherCardNumber) {
        moneyTransfer(amount, otherCardNumber);
        findErrorMessage("Указанная сумма перевода больше доступной суммы на балансе второй карты.");
        return this;
    }

    public TransferPage sameCardTransfer(String cardBalance, String thisCardNumber) {
        amountField.setValue(cardBalance);
        from.setValue(thisCardNumber);
        transferButton.click();
        findErrorMessage("Перевод не может быть осуществлен с одной и той же карты");
        return new TransferPage();
    }

    public TransferPage nullCardTransfer(String cardBalance) {
        amountField.setValue(cardBalance);
        transferButton.click();
        findErrorMessage("Не указан номер карты, с которой необходимо осуществить перевод");
        return new TransferPage();
    }

    public TransferPage nullAmountTransfer(String otherCardNumber) {
        from.setValue(otherCardNumber);
        transferButton.click();
        findErrorMessage("Не указана сумма перевода");
        return new TransferPage();
    }

    public void findErrorMessage(String errorText) {
        errorNotification.shouldBe(visible).shouldHave(exactText(errorText), Duration.ofSeconds(15));
    }
}
