package pageobjects.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;

public class HomePage {

    private SelenideElement defaultSessionButton = $(By.xpath("//ul[@class='list-group']/button[1]"));

    public void open() {
        Selenide.open(Neodymium.configuration().url());
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(1920,1080));
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
        if(defaultSessionButton.isDisplayed()){
            defaultSessionButton.click();
        }
    }
}
