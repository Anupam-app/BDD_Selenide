package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class UserFilterPage {

    private final String XPATH_USER_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_USER_TABLE = "//table[@id='foundusertable']";
    private final String XPATH_USER_COLUMNS = "//table[@id='foundusertable']//td[%s]";
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";

    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement userPreferences = $(By.xpath("//Span[text()='User preferences']"));

    private SelenideElement selectOption = $(By.xpath("//span[@class='icon-down-arrow']"));
    private SelenideElement saveButton = $(By.xpath("//span[text()='Save']"));
    private SelenideElement activeIconName = $(By.xpath("//div[@class='icontitle active']"));
    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private SelenideElement upArrow = $(By.xpath("//div[@class='arrowupuserfilter']"));
    private SelenideElement applyFilterButton = $(By.xpath("//button/b[text()='Apply Filter']"));
    private SelenideElement filterTagText = $(By.xpath("//div[@class='userfiltertag']"));
    private SelenideElement userNameText = $(By.xpath("//td[1]"));
    private SelenideElement logOutButton = $(By.xpath("//button[text()='Log out']"));

    public SelenideElement getUserColumnHeader(String columnName) {
        return $(By.xpath(String.format(XPATH_USER_COLUMN_HEADER, columnName)));
    }

    public List<String> getAllUserColumnHeaders() {
        return $$(By.xpath(XPATH_USER_TABLE + "//th")).texts();
    }

    public List<String> getUserColumns(int index) {
        var users=$$(By.xpath(String.format(XPATH_USER_COLUMNS, index))).texts();
        users.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return users;
    }

    public void userProfile() {
        SelenideHelper.commonWaiter(userProfileIcon, visible).click();
    }

    public void userPreferences() {
        userPreferences.click();
    }

    public void defaultOption(String defaultOptionName) {
        SelenideHelper.commonWaiter(selectOption, visible).click();
        commonWaiter($(By.xpath(String.format("//li[text()='%s']", defaultOptionName))), visible).click();
        SelenideHelper.commonWaiter(saveButton, visible).click();
        SelenideHelper.commonWaiter(userProfileIcon, visible).click();
        SelenideHelper.commonWaiter(logOutButton, visible).click();
    }

    public String getActiveIconTitle() {
        return activeIconName.getText();
    }

    public void setToDefault() {
        switchTo().defaultContent();
        SelenideHelper.commonWaiter(userProfileIcon, visible).click();
        userPreferences.click();
        SelenideHelper.commonWaiter(selectOption, visible).click();
        commonWaiter($(By.xpath(String.format("//li[text()='Main']"))), visible).click();
        SelenideHelper.commonWaiter(saveButton, visible).click();
        SelenideHelper.commonWaiter(userProfileIcon, visible).click();
        SelenideHelper.commonWaiter(logOutButton, visible).click();
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


    public void sortList(String columnName, boolean descending) {
        getUserColumnHeader(columnName).click();

        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order")));
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order dropup")));

        if (!descendingIcon.isDisplayed() && descending) {
            getUserColumnHeader(columnName).click();
            descendingIcon.shouldBe(visible);
        }

        if (!ascendingIcon.isDisplayed() && !descending) {
            getUserColumnHeader(columnName).click();
            ascendingIcon.shouldBe(visible);
        }
    }

    public void checkSortedElement(String columnName, boolean descending) {

        var userColumnHeaders = getAllUserColumnHeaders();
        var index = userColumnHeaders.indexOf(columnName);

        List<String> displayedList = getUserColumns(index + 1);

        var expectedList = new ArrayList<>(displayedList);
        if (descending) {
            expectedList.sort(Collections.reverseOrder());
        } else {
            Collections.sort(expectedList);
        }

        Assert.assertEquals(expectedList, displayedList);
    }
}


