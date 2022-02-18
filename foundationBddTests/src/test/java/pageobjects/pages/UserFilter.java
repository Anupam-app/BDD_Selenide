package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class UserFilter {

    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement userPreferences = $(By.xpath("//Span[text()='User preferences']"));

    private SelenideElement selectOption = $(By.xpath("//span[@class='icon-down-arrow']"));
    private SelenideElement saveButton = $(By.xpath("//span[text()='Save']"));
    private SelenideElement activeIconName = $(By.xpath("//div[@class='icontitle active']"));
    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private SelenideElement upArrow = $(By.xpath("//div[@class='arrowupuserfilter']"));
    private SelenideElement applyFilterButton = $(By.xpath("//button/b[text()='Apply Filter']"));
    private SelenideElement filterTagText = $(By.xpath("//div[@class='userfiltertag']"));
    private SelenideElement userNameText = $(By.xpath("(//td[@class='customusername'])[1]"));



    public void userProfile() {
        userProfileIcon.click();
    }

    public void userPreferences() {
        userPreferences.click();
    }

    public void defaultOption(String defaultOptionName) {
        SelenideHelper.commonWaiter(selectOption, visible).click();
        $(By.xpath(String.format("//li[text()='%s']", defaultOptionName))).click();
        saveButton.click();
    }

    public String getActiveIconTitle() {
        return activeIconName.getText();
    }

    public void selectUserStatus(String status) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        commonWaiter(upArrow, visible);
        upArrow.click();
        $(By.xpath(String.format("//span[text()='%s']", status))).click();
        applyFilterButton.click();
    }

    public String getFilterTagText() {
        return filterTagText.getText();
    }

    public String getUserName() {
        return userNameText.getText();
    }


}


