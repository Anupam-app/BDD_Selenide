package pageobjects.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class HomePage extends AbstractPageObject {

    private SelenideElement defaultSession = $(By.xpath("//ul[@class='list-group']/button[1]"));

    public void open() {
        Selenide.open(Neodymium.configuration().url());
        try {
            defaultSession.click();
        } catch (Exception exception) {
        }
    }
}
