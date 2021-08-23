package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class UserPage {

    private SelenideElement idManagementPageLinkText = $(By.id("UserManagement"));
    private SelenideElement userSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));

    private SelenideElement saveButton = $(By.id("save_Btn"));
    private SelenideElement confirmationButton = $(By.id("remove_backup")); //TODO id to be changed from dev team
    private SelenideElement employeeIDTextBox = $(By.id("employeeID"));

    private SelenideElement disableUserButton = $(By.id("btn_disabl"));
    private SelenideElement enableUserButton = $(By.id("btn_enabl"));

    private String xpathEditUserIcon = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'edit-icon')]";

    public void setSearch(String search) {
        userSearchTextBox.setValue(search);
    }

    public void edit(String user) {
        $(By.xpath(String.format(xpathEditUserIcon, user))).click();
    }

    public void userExists(String user) {
        $(By.xpath(String.format(xpathEditUserIcon, user))).should(be(visible));
    }

    public void setEmployeeId(String employeeId) {
        employeeIDTextBox.setValue(employeeId);
    }

    public void saveMyChanges() {
        saveButton.click();
        confirmationButton.click();
    }

    public String getEmployeeIdFromForm() {
        return employeeIDTextBox.getValue();
    }

    public void disableUser() {
        disableUserButton.click();
    }

    public void enableUser() {
        enableUserButton.click();
    }

    public void goTo() {
        idManagementPageLinkText.click();
    }

    public boolean isUserDisabled() {
        return disableUserButton.getAttribute("class").equals("togg_btn toggle_act")
                &&
                enableUserButton.getAttribute("class").equals("togg_btn");
    }

    public boolean isUserEnabled() {
        return enableUserButton.getAttribute("class").equals("togg_btn toggle_act")
                &&
                disableUserButton.getAttribute("class").equals("togg_btn");
    }
}
