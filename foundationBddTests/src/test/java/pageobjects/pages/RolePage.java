package pageobjects.pages;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dataobjects.RoleAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class RolePage {

    private SelenideElement rolesLinkText = $(By.xpath("//*[@class='subMenu'][text()='Roles']"));

    private ElementsCollection permissionsText = $$(By.xpath("//label[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-checked\"]"));
    private SelenideElement saveText = $(By.className("roleModalNotificationBar"));

    private SelenideElement inputRoleName = $(By.className("roleNameInput"));

    private SelenideElement saveRoleButton = $(By.id("save_Btn"));
    private SelenideElement closeButton = $(By.className("role-crossicon"));
    private SelenideElement createRoleButton = $(By.xpath("//*[@id=\"AddBtn\"]/div[2]"));

    private SelenideElement roleSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));

    private String xpathEditRoleIcon = "//div[div[contains(.,'%s')]]/div/div[contains(@class, 'edit-icon')]";

    private SelenideElement selectRoleDropdown = $(By.id("role"));

    private final String NOTIFICATION_TEXT="Successfully %s role.";
    private final String PERMISSION_TEXT = "//span[text()='%s']";

    public void clickOnPermission(String permission) {
        $x(String.format(PERMISSION_TEXT, permission)).click();
    }

    public void gotoRolesTab() {
        rolesLinkText.click();
    }

    public void createNewrole(String roleName) {
        createRoleButton.click();
        SelenideHelper.commonWaiter(inputRoleName, Condition.visible).setValue(roleName);
    }

    public void saveRole(RoleAction roleAction) {
        saveRoleButton.click();

        String expectedNotificationText="";
        switch (roleAction){
            case ADDED:
                expectedNotificationText=String.format(NOTIFICATION_TEXT, "added");
                break;
            case UPDATED:
                expectedNotificationText=String.format(NOTIFICATION_TEXT, "updated");
                break;
        }

        saveText.shouldHave(Condition.text(expectedNotificationText));

        closeButton.click();
    }

    public void modifyRole(String roleName) {
        $(By.xpath(String.format(xpathEditRoleIcon, roleName))).click();
    }

    public void assignRole(String roleName) {
        selectRoleDropdown.click();

        List<WebElement> options = selectRoleDropdown.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals(roleName)) {
                option.click();
                break;
            }
        }
    }

    public String getRoleNameFromTextbox() {
        return inputRoleName.getValue();
    }

    public String getRoleName() {
        return selectRoleDropdown.getValue();
    }

    public void searchRole(String roleName) {
        roleSearchTextBox.clear();
        roleSearchTextBox.sendKeys(roleName);
    }

    public List<String> getPermissionList() {
        return permissionsText
                .shouldHave(CollectionCondition.allMatch("textNotEmpty",e-> !e.getText().isEmpty()))
                .texts();
    }

    public void roleExists(String role) {
        $(By.xpath(String.format(xpathEditRoleIcon, role))).shouldBe(Condition.visible);
    }
}
