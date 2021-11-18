package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import java.util.List;

public class UserPage {

    private SelenideElement UsersLinkText = $(By.xpath("//*[@class='subMenu'][text()='Users']"));
    private SelenideElement idManagementPageLinkText = $(By.id("UserManagement"));

    private SelenideElement userSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));
    private SelenideElement employeeIDTextBox = $(By.id("employeeID"));
    private SelenideElement firstNameTextBox = $(By.id("firstName"));
    private SelenideElement lastNameTextBox = $(By.id("lastName"));
    private SelenideElement userNameTextBox = $(By.id("userName"));
    private SelenideElement emailIdTextBox = $(By.id("email"));

    private SelenideElement saveButton = $(By.id("save_Btn"));
    private SelenideElement confirmationButton = $(By.id("remove_backup")); //TODO id to be changed from dev team
    private SelenideElement disableUserButton = $(By.id("btn_disabl"));
    private SelenideElement enableUserButton = $(By.id("btn_enabl"));
    private SelenideElement createUserPlusButton = $(By.xpath("//div[@class='Adduserplus']"));

    private SelenideElement selectRoleFromDropdown = $(By.id("role"));
    private String xpathEditUserIcon = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'edit-icon')]";

    public void setSearch(String search) {
        userSearchTextBox.clear();
        userSearchTextBox.setValue(search);
        userSearchTextBox.waitUntil(Condition.visible,10000l);
    }

    public void edit(String user) {
        $(By.xpath(String.format(xpathEditUserIcon, user))).waitUntil(visible,5000l).click();
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

    public void createNewUser(String userName) {
        createUserPlusButton.click();
        userNameTextBox.setValue(userName);
    }

    public void selectRole(String roleName) {
        selectRoleFromDropdown.click();
        List<WebElement> options = selectRoleFromDropdown.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            if (option.getText().equals(roleName))
            {
                option.click();
                break;
            }
        }

    }

    public void enterFirstName(String firstName) {
        firstNameTextBox.setValue(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameTextBox.setValue(lastName);
    }

    public void enterEmpId(String employeeID) {
        employeeIDTextBox.setValue(employeeID);
    }

    public void enterEmail(String emailId) {
        emailIdTextBox.setValue(emailId);
    }

    public String getUserNameFromForm() {
        return userNameTextBox.getValue();
    }

    public void clearSearch() {
        userSearchTextBox.clear();
    }
    public void triggerUsersMode() {
        UsersLinkText.click();
    }
}
