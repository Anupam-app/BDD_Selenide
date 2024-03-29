package pageobjects.pages;


import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import dataobjects.RoleAction;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.SortHelper;

public class RolePage {

    private final SelenideElement rolesLinkText =
            $(By.xpath("//*[contains(@class,'subMenu')][contains(text(),'Roles')]"));
    private final SelenideElement XPATH_NOTIFICATION_TEXT =
            $(By.xpath("//*[@class='roleModalNotificationBar roleMgmtNotificationBar information-bar']"));
    private final ElementsCollection permissionsText =
            $$(By.xpath("//label[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-checked\"]"));
    private final SelenideElement saveText = $(By.xpath("//div[contains(@class,'roleModalNotificationBar')]"));
    private final SelenideElement inputRoleName = $(By.className("roleNameInput"));

    private final SelenideElement saveRoleButton = $(By.id("save_Btn"));
    private final SelenideElement cancelRoleButton = $(By.className("roleBtnCancel"));
    private final SelenideElement closeButton = $(By.className("role-crossicon"));
    private final SelenideElement createRoleButton = $(By.xpath("//*[@id=\"AddBtn\"]/div[2]"));

    private final SelenideElement roleSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));

    private final String xpathEditRoleIcon = "//div[div[contains(.,'%s')]]/*//div[contains(@class, 'edit-icon')]";
    private final String roleNameCheck = "//div[contains(text(),'%s')]";
    private final SelenideElement selectRoleDropdown = $(By.id("role"));

    private final SelenideElement roleNotificationBar = $(By.xpath("//*[contains(@class,'roleModalNotificationBar')]"));
    private final String XPATH_SORT_ICON = "//div[text()='%s']/child::div[@class='sortNone']";
    private String roleTableColumnClassValue = null;
    private final String XPATH_ROLE_COLUMN_HEADER = "//div[text()='%s']";
    private final String XPATH_ORDER_ICON = "//div[@class='%s']";
    private final String XPATH_ROLE_TABLE = "//div[@class='roleTableHeader']";
    private final String XPATH_ROLE_COLUMNS = "//div[contains(@class,'%s')]";
    private final String NOTIFICATION_TEXT = "Successfully %s role.";

    private final String PERMISSION_TEXT = "//span[contains(text(),'%s')]";

    private final SelenideElement bio4cservices = $(By.xpath("//div[text()='Bio4CService']"));
    private final SelenideElement administrator = $(By.xpath("//div[text()='Administrator']"));
    private final SelenideElement processManager = $(By.xpath("//div[text()='ProcessManager']"));
    private final SelenideElement operator = $(By.xpath("//div[text()='Operator']"));
    private final String viewAll = "//div[contains(text(),'%s')]/following::div/a";
    private final SelenideElement closeIcon = $(By.xpath("//div[(@class='viewAll-crossicon')]"));
    private final SelenideElement adminEditeIcon =
            $(By.xpath("//div[(@class='roleCard')]/parent::div/div[3]/div[5]/div"));
    private final ElementsCollection rolePermissionList = $$(By.xpath("//div[(@class='modal-body')]//div//div"));
    private final String tagLabel = "//div[@class='permission-col'][%s]";
    private final String viewIconOfAdministrator =
            "//*[text()='%s']/parent::div//div[5]/*[(@class='MuiSvgIcon-root roleViewIcon vieIconBottom')]";
    private final String uncheckPermissions =
            "//label[contains(@class,'ant-checkbox-wrapper ant-checkbox')]/span[(contains(text(),'%s'))]";
    private final SelenideElement newRoleName = $(By.xpath("//input[(@class='roleNameInput')]"));
    private final SelenideElement save_Btn = $(By.xpath("//*[text()='Save']"));
    private final SelenideElement cancelButton = $(By.xpath("//*[text()='Cancel']"));
    private final SelenideElement searchBox = $(By.xpath("//input[(@class='Usersearchbox')]"));
    private final SelenideElement newUserRole = $(By.xpath("//td[text()='NewUserRole' and @class='customusername']"));
    private final SelenideElement newUserRoleName = $(By
            .xpath("//td[text()='NewUserRole' and @class='customusername']/parent::tr/td/div/div[text()='TestRole']"));
    private final SelenideElement roleNameText = $(By.xpath("//div[@class='roleTableColumnOne roleCardMarginOne']"));
    private final String disableEditButton = "//div[contains(text(),'%s')]/following-sibling::div[5]//button";
    private final String NOTIFICATION_ERROR = "Role name: %s already exists.";
    private final String DISABLE_ENABLE_ROLE_NOTIFICATION = "The role successfully %s.";
    private final SelenideElement notification_Text = $(By.xpath("//*[contains(@class,'roleModalNotificationBar')]"));
    private final SelenideElement deleteRoleButton = $(By.xpath("//span[text()='Delete role']"));
    private final SelenideElement yesDeleteButton = $(By.xpath("//button[text()='Yes, delete']"));
    private final SelenideElement noRoleMessageStatus = $(By.xpath("//div[text()='No result found for deleteRole']"));
    private final String CHECKBOX_ATTRIBUTE = "//span[contains(text(),'%s')]/parent::label";
    private final SelenideElement roleDependencyDialog = $(By.xpath("//div[@class='confirmation-modal-header']"));
    private final SelenideElement yesProceedButton =
            $(By.xpath("//b[text()='Yes, proceed']/parent::button[@id='save_Btn']"));
    private final SelenideElement roleNameToolTip = $(By.xpath("//div[text()='User Created']/preceding-sibling::div"));

    public void clickOnPermission(String permission) {
        if (permission.equals("Approve Report Template")) {
            $(By.xpath("//span[contains(text(),'Approve') and contains(text(),'Template')]")).click();
        } else {
            $x(String.format(PERMISSION_TEXT, permission)).click();
        }
    }

    public void gotoRolesTab() {
        rolesLinkText.click();
    }

    public void createNewRole(String roleName) {
        createRoleButton.click();
        SelenideHelper.commonWaiter(inputRoleName, visible)
                .setValue(roleName);
    }

    public void sortList(String columnName, boolean descending) {
        SelenideElement sortAction = getRoleColumnHeader(columnName);
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "sortDesc")));
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "sortAsc")));
        $(By.xpath(String.format(XPATH_SORT_ICON, columnName))).click();
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void checkSortedElement(String columnName, boolean descending) {
        var index = getAllRoleColumnHeaders().indexOf(columnName);
        if (index == 0) {
            roleTableColumnClassValue = "roleTableColumnOne";
        } else if (index == 1) {
            roleTableColumnClassValue = "roleTableColumnTwo";
        }
        List<String> getRoles = $$(By.xpath(String.format(XPATH_ROLE_COLUMNS, roleTableColumnClassValue))).texts();
        getRoles.removeIf(e -> StringUtils.isEmpty(e.trim()));
        SortHelper.checkSortedRolesElement(columnName, descending, getRoles);
    }

    public List<String> getAllRoleColumnHeaders() {
        return $$(By.xpath(XPATH_ROLE_TABLE + "/div")).texts();
    }

    public SelenideElement getRoleColumnHeader(String columnName) {
        return $(By.xpath(String.format(XPATH_ROLE_COLUMN_HEADER, columnName)));
    }

    public void notification(RoleAction roleAction) {
        // saveRoleButton.click();

        String expectedNotificationText = "";
        switch (roleAction) {
            case ADDED:
                expectedNotificationText = String.format(NOTIFICATION_TEXT, "added");
                break;
            case UPDATED:
                expectedNotificationText = String.format(NOTIFICATION_TEXT, "updated");
                break;
            case ERROR:
                expectedNotificationText = String.format(NOTIFICATION_ERROR, "testRoleToRemovePermission");
                break;

        }

        commonWaiter(saveText, visible).shouldHave(text(expectedNotificationText));

        closeButton.click();
    }

    public void modifyRole(String roleName) {
        $(By.xpath(String.format(xpathEditRoleIcon, roleName))).click();
    }

    public void assignRole(String roleName) {
        selectRoleDropdown.click();

        List<WebElement> options = selectRoleDropdown.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText()
                    .equals(roleName)) {
                option.click();
                break;
            }
        }
    }

    public String getRoleNameFromTextBox() {
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
        return permissionsText.shouldHave(CollectionCondition.allMatch("textNotEmpty", e -> !e.getText()
                .isEmpty()))
                .texts();
    }

    public List<String> getOldPermissionList() {
        return permissionsText.shouldHave(CollectionCondition.allMatch("textNotEmpty", e -> !e.getText()
                .isEmpty()))
                .texts();
    }

    public void roleExists(String role) {
        $(By.xpath(String.format(xpathEditRoleIcon, role))).shouldBe(visible);
    }

    public void verifyRoleNameToolTip(String role) {
        Assert.assertEquals(roleNameToolTip.getText(), role);
    }

    public void noRolesTab() {
        rolesLinkText.should(Condition.not(visible));
    }

    public void checkMessage(String message) {
        commonWaiter(roleNotificationBar, visible);
        roleNotificationBar.shouldHave(text(message));
    }

    public void saveButton() {
        saveRoleButton.click();
    }

    public void cancelButton() {
        cancelRoleButton.click();
        switchTo().parentFrame();
    }

    public void iVerifyDefaultRoles(String name) {
        SelenideElement enableDisableButton = $(By.xpath(String.format(disableEditButton, name)));
        switch (name) {
            case "Bio4CService":
                bio4cservices.shouldBe(visible);
                enableDisableButton.shouldNotBe(enabled);
                break;
            case "Administrator":
                administrator.shouldBe(visible);
                enableDisableButton.shouldNotBe(enabled);
                break;
            case "ProcessManager":
                processManager.shouldBe(visible);
                enableDisableButton.shouldBe(enabled);
                break;
            case "Operator":
                operator.shouldBe(visible);
                enableDisableButton.shouldBe(enabled);
                break;
            default:
        }
    }

    public void rolePermissionAssigned(SelenideElement element, String roles, String rolePath) {
        SelenideHelper.commonWaiter(element, visible)
                .click();
        int count = rolePermissionList.size();
        List<String> acceptedParams = new ArrayList<>();
        List<String> expectedParams = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            acceptedParams.add($(By.xpath(String.format(tagLabel, i))).getText());
        }
        var config = ConfigFactory.parseResourcesAnySyntax(roles, ConfigParseOptions.defaults());
        var params = config.getConfigList(rolePath);
        int paramsSize = params.size();
        for (var param : params) {
            expectedParams.add(param.getString("value"));
        }
        if (count == paramsSize && count != 0) {
            Collections.sort(acceptedParams);
            Collections.sort(expectedParams);
            Assert.assertEquals(acceptedParams, expectedParams);
        }
        SelenideHelper.commonWaiter(closeIcon, visible)
                .click();
    }

    public void adminRolePermission(String role) {
        rolePermissionAssigned($(By.xpath(String.format(viewAll, "Administrator"))), role, "AdminRole.role");
    }

    public void serviceRolePermission(String role) {
        rolePermissionAssigned($(By.xpath(String.format(viewAll, "Bio4CService"))), role, "ServiceRole.role");
    }

    public void processMgr_RolePermission(String role) {
        rolePermissionAssigned($(By.xpath(String.format(viewAll, "ProcessManager"))), role, "ProcessManagerRole.role");
    }

    public void operatorRolePermission(String role) {
        rolePermissionAssigned($(By.xpath(String.format(viewAll, "Operator"))), role, "OperatorRole.role");
    }

    public void validateViewIcon(String name) {
        $(By.xpath(String.format(viewIconOfAdministrator, name))).shouldBe(visible);
    }

    public void adminIcon() {
        SelenideHelper.commonWaiter(adminEditeIcon, visible)
                .click();
    }

    public void unselectRolePermission(String name) {
        if ($(By.xpath(String.format(uncheckPermissions, name))).isDisplayed())
            $(By.xpath(String.format(uncheckPermissions, name))).click();
    }

    public void iCreateRandomName(String userName) {
        newRoleName.click();
        newRoleName.setValue(userName);
        SelenideHelper.commonWaiter(save_Btn, visible)
                .click();
        SelenideHelper.commonWaiter(cancelButton, visible)
                .click();
    }

    public void iSearchName(String name) {
        SelenideHelper.commonWaiter(searchBox, visible)
                .click();
        SelenideHelper.commonWaiter(searchBox, visible)
                .setValue(name);
    }

    public void verifyRoleName(String name) {
        roleNameText.shouldHave(text(name));
    }

    public String getOldRoleName() {
        newRoleName.click();
        return newRoleName.getAttribute("value");
    }

    public void deleteRole() {
        commonWaiter(deleteRoleButton, visible).click();
        commonWaiter(yesDeleteButton, visible).click();
        commonWaiter(XPATH_NOTIFICATION_TEXT, visible);
        XPATH_NOTIFICATION_TEXT.shouldHave(text("The role successfully obsolete."));
    }

    public void roleNameExists(String role) {
        $(By.xpath(String.format(roleNameCheck, role))).shouldBe(visible);
    }

    public void verifyAssignedPermission(String roleName, Set<String> set) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(viewAll, roleName))), visible)
                .click();
        int count = rolePermissionList.size();
        List<String> acceptedParams = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            acceptedParams.add($(By.xpath(String.format(tagLabel, i))).getText());
        }
        List<String> list = new ArrayList<>(set);
        if ((count == set.size()) && (count != 0)) {
            Collections.sort(acceptedParams);
            Collections.sort(list);
            Assert.assertEquals(list, acceptedParams);
        }
        SelenideHelper.commonWaiter(closeIcon, visible)
                .click();
    }

    public void updateRoleName(String roleName) {
        commonWaiter(inputRoleName, visible).clear();
        inputRoleName.setValue(roleName);
        Assert.assertEquals("Role Name is not entered as expected", roleName, inputRoleName.getValue());
    }

    public void iEnableDisableRole(String value, String name) {
        SelenideElement enableDisableButton = $(By.xpath(String.format(disableEditButton, name)));
        if (value.equals("enabled") && Objects.equals(enableDisableButton.getAttribute("aria-checked"), "false")) {
            enableDisableButton.click();
            updatedNotification(String.format(DISABLE_ENABLE_ROLE_NOTIFICATION, value));
            enableDisableButton.shouldHave(attribute("aria-checked", "true"));
        } else if (value.equals("disabled")
                && Objects.equals(enableDisableButton.getAttribute("aria-checked"), "true")) {
            enableDisableButton.click();
            updatedNotification(String.format(DISABLE_ENABLE_ROLE_NOTIFICATION, value));
            enableDisableButton.shouldHave(attribute("aria-checked", "false"));
        }
    }

    public void updatedNotification(String message) {
        commonWaiter(notification_Text, visible);
        notification_Text.shouldHave(text(message));
    }

    public void roleSearchAndEdit(String roleName) {
        searchRole(roleName);
        $(By.xpath(String.format(xpathEditRoleIcon, roleName))).waitUntil(visible, 5000L, 1000L)
                .click();
        deleteRoleButton.shouldBe(visible);
        if (cancelButton.isDisplayed()) {
            cancelButton.waitUntil(visible, 2000L, 1000L)
                    .click();
        }
    }

    public void verifyRoleAfterDelete() {
        noRoleMessageStatus.waitUntil(visible, 2000, 1000)
                .shouldHave(text("No result found for deleteRole"));
    }

    public void unSelectPermission(String permission) {
        if (permission.equalsIgnoreCase("Approve Report Template")) {
            String checkbox = "//span[contains(text(),'Approve') and contains(text(),'Template')]/parent::label";
            if ($(By.xpath(String.format(checkbox))).getAttribute("class")
                    .contains("checked")) {
                $(By.xpath("//span[contains(text(),'Approve') and contains(text(),'Template')]")).click();
            }
        } else {
            if ($(By.xpath(String.format(CHECKBOX_ATTRIBUTE, permission))).getAttribute("class")
                    .contains("checked")) {
                $x(String.format(PERMISSION_TEXT, permission)).click();
            }
        }
        roleDependencyPopUp();
    }

    public void roleDependencyPopUp() {
        Selenide.sleep(2000);
        if (roleDependencyDialog.isDisplayed()) {
            yesProceedButton.click();
        }
    }

    public void userCannotViewRole() {
        rolesLinkText.shouldNotBe(visible);
    }

    public void addRoleIsNotDisplayed(){
        createRoleButton.shouldNotBe(visible);
    }

    public void viewRoleAccess(String roleName){
        addRoleIsNotDisplayed();
        searchRole(roleName);
        $(By.xpath(String.format(xpathEditRoleIcon, roleName))).shouldNotBe(visible);
        $(By.xpath(String.format(disableEditButton, roleName))).shouldNotBe(visible);
    }

    public void closeRoleModal(){
        commonWaiter(closeButton,visible).click();
    }

}
