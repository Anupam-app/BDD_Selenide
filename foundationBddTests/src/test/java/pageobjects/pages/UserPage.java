package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;

public class UserPage {

    private SelenideElement idManagementPageLinkText = $(By.id("UserManagement"));
    private SelenideElement userSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));

    private SelenideElement saveButton = $(By.id("save_Btn"));
    private SelenideElement confirmationButton = $(By.id("remove_backup")); //TODO id to be changed from dev team
    private SelenideElement employeeIDTextBox = $(By.id("employeeID"));

    private SelenideElement disableUserButton = $(By.id("btn_disabl"));
    private SelenideElement enableUserButton = $(By.id("btn_enabl"));

    private String xpathEditUserIcon = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'edit-icon')]";
    private SelenideElement userNameExist = $(By.xpath("(//*[contains(@class,'customusername')])[1])"));
    private SelenideElement createUserPlusButton = $(By.xpath("//div[@class='Adduserplus']"));
    private SelenideElement userNameTextBox = $(By.id("userName"));
    private SelenideElement selectRoleFromDropdown = $(By.id("role"));
    private SelenideElement firstNameTextBox = $(By.id("firstName"));
    private SelenideElement lastNameTextBox = $(By.id("lastName"));
    private SelenideElement emailIdTextBox = $(By.id("email"));
    
    public void setSearch(String search) {
        userSearchTextBox.setValue(search);
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
    
    public void userList() {
    	userNameExist.should(be(visible));
    	userNameExist.getText();
    }
    
    public void createUser(String emailId) {
    	createUserPlusButton.click();
        userNameTextBox.setValue(RandomStringUtils.randomAlphabetic(10));
        
        selectRoleFromDropdown.click();
        List<WebElement> options = selectRoleFromDropdown.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            if (option.getText().equals("Bio4CService"))
            {
                option.click();
                break;
            }
        }
        
        firstNameTextBox.setValue(RandomStringUtils.randomAlphabetic(10));
        lastNameTextBox.setValue(RandomStringUtils.randomAlphabetic(10));
        employeeIDTextBox.setValue(RandomStringUtils.randomAlphabetic(10));
        emailIdTextBox.setValue(emailId);
        saveButton.click();
        confirmationButton.click();
    }
    
    public void editUser(String userName) {
    	userSearchTextBox.setValue(userName);
    	$(By.xpath(String.format(xpathEditUserIcon, userName))).waitUntil(visible,5000l).click();
    	employeeIDTextBox.setValue(RandomStringUtils.randomAlphabetic(10));
    	saveButton.click();
        confirmationButton.click();
    }
    
    public String getUserNameFromForm() {
        return userNameTextBox.getValue();
    }
}
