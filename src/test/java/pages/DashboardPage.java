package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    SelenideElement heading = $("[data-test-id= 'dashboard']");
    SelenideElement card1Info = $("[data-test-id= '92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    SelenideElement card2Info = $("[data-test-id= '0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    public DashboardPage() {
        heading.shouldBe(visible);
        card1Info.shouldBe(visible);
        card2Info.shouldBe(visible);
    }
    public static TransferPage deposit1() {
        $("[data-test-id= '92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
        $("[data-test-id= 'to'] disabled value= '**** **** **** 0001'").shouldBe(visible);
        return new TransferPage();

    }
    public static TransferPage deposit2() {
        $("[data-test-id= '0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
        $("[data-test-id= 'to'] disabled value= '**** **** **** 0002'").shouldBe(visible);
        return new TransferPage();
    }
}

