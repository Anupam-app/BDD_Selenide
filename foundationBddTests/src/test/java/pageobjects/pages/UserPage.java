package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserPage extends AbstractPageObject {

    private SelenideElement idManagementPage = $(By.id("UserManagement"));
    private SelenideElement userSearch = $(By.xpath("//input[@placeholder='Search...']"));

    private SelenideElement saveButton = $(By.id("save_Btn"));
    private SelenideElement confirmationButton = $(By.id("remove_backup")); //TODO id to be changed from dev team
    private SelenideElement employeeID = $(By.id("employeeID"));

    private SelenideElement disableUser = $(By.id("btn_disabl"));
    private SelenideElement enableUser = $(By.id("btn_enabl"));

    private String xpathEditUser = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'edit-icon')]";

    public void setSearch(String search) {
        userSearch.setValue(search);
    }

    public void edit(String user) {
        $(By.xpath(String.format(xpathEditUser, user))).click();
    }

    public void setEmployeeId(String employeeId) {
        employeeID.setValue(employeeId);
    }

    public void saveMyChanges() {
        saveButton.click();
        confirmationButton.click();
    }

    public String getEmployeeIdFromForm() {
        return employeeID.getValue();
    }

    public void disableUser() {
        disableUser.click();
    }

    public void enableUser() {
        enableUser.click();
    }

    public void goTo() {
        idManagementPage.click();
    }

    public boolean isUserDisabled() {
        return disableUser.getAttribute("class").equals("togg_btn toggle_act")
                &&
                enableUser.getAttribute("class").equals("togg_btn");
    }

    public boolean isUserEnabled() {
        return enableUser.getAttribute("class").equals("togg_btn toggle_act")
                &&
                disableUser.getAttribute("class").equals("togg_btn");
    }
}
