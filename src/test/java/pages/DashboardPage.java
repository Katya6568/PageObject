package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    SelenideElement heading = $("[data-test-id= 'dashboard']");
    SelenideElement card1Info = $("[data-test-id= '92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    SelenideElement card2Info = $("[data-test-id= '0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage() {
        heading.shouldBe(visible);
        card1Info.shouldBe(visible);
        card2Info.shouldBe(visible);
    }

    public TransferPage deposit1() {
        $("[data-test-id= '92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
        return new TransferPage();

    }

    public TransferPage deposit2() {
        $("[data-test-id= '0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
        return new TransferPage();
    }

    private final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

//    public int getCardBalance(int index) {
//        val text = cards.get(index).getText();
//        return extractBalance(text);
//    }
    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(text(cardInfo.getNumber().substring(15))).getText();
        return extractBalance(text);
    }

}