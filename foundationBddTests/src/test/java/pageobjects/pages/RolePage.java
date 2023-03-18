package pageobjects.pages;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import dataobjects.RoleAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import pageobjects.utility.SortHelper;

public class RolePage {

    private final SelenideElement rolesLinkText = $(By.xpath("//*[@class='subMenu'][contains(text(),'Roles')]"));
	private final SelenideElement XPATH_NOTIFICATION_TEXT = $(By.xpath("//*[@class='roleModalNotificationBar roleMgmtNotificationBar information-bar']"));
    private final ElementsCollection permissionsText = $$(By.xpath("//label[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-checked\"]"));
    private final SelenideElement saveText = $(By.className("roleModalNotificationBar"));
    private final SelenideElement inputRoleName = $(By.className("roleNameInput"));

    private final SelenideElement saveRoleButton = $(By.id("save_Btn"));
    private final SelenideElement cancelRoleButton = $(By.className("roleBtnCancel"));
    private final SelenideElement closeButton = $(By.className("role-crossicon"));
    private final SelenideElement createRoleButton = $(By.xpath("//*[@id=\"AddBtn\"]/div[2]"));

    private final SelenideElement roleSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));

    private final String xpathEditRoleIcon = "//div[div[contains(.,'%s')]]/div/div[contains(@class, 'edit-icon')]";
    private final String roleNameCheck = "//div[contains(text(),'%s')]";
    private final SelenideElement selectRoleDropdown = $(By.id("role"));

    private final SelenideElement roleNotificationBar = $(By.xpath("//*[@class = 'roleModalNotificationBar error-bar']"));
    private final String XPATH_SORTICON = "//div[text()='%s']/child::div[@class='sortNone']";
    private String roleTableColumnclassValue = null;
    private final String XPATH_ROLE_COLUMN_HEADER = "//div[@class='roleTableHeader']/div[text()='%s']";
    private final String XPATH_ORDER_ICON = "//div[@class='%s']";
    private final String XPATH_ROLE_TABLE = "//div[@class='roleTableHeader']";
    private final String XPATH_ROLE_COLUMNS = "//div[contains(@class,'%s')]";
    private final String NOTIFICATION_TEXT = "Successfully %s role.";

    private final String PERMISSION_TEXT = "//span[contains(text(),'%s')]";

    private final SelenideElement bio4cservices = $(By.xpath("//div[text()='Bio4CService']"));
    private final SelenideElement administrator = $(By.xpath("//div[text()='Administrator']"));
    private final SelenideElement processManager = $(By.xpath("//div[text()='ProcessManager']"));
    private final SelenideElement operator = $(By.xpath("//div[text()='Operator']"));
    private final SelenideElement roleTab = $(By.xpath("//div[text()='Roles']"));
    private final String viewAll = "//div[contains(text(),'%s')]/following::div[2]/a";
    private final SelenideElement closeIcon = $(By.xpath("//div[(@class='viewAll-crossicon')]"));
    private final SelenideElement adminEditeIcon = $(By.xpath("//div[(@class='roleCard')]/parent::div/div[3]/div[5]/div"));
    private final ElementsCollection rolePermissionList = $$(By.xpath("//div[(@class='modal-body')]//div//div"));
    private final String tagLabel = "//div[@class='permission-col'][%s]";
    private final String viewIconOfAdministrator = "//*[text()='%s']/parent::div//div[5]/*[(@class='MuiSvgIcon-root roleViewIcon vieIconBottom')]";
    private final String uncheckpermissions = "//label[contains(@class,'ant-checkbox-wrapper ant-checkbox')]/span[(contains(text(),'%s'))]";
    private final SelenideElement newRoleName = $(By.xpath("//input[(@class='roleNameInput')]"));
    private final SelenideElement save_Btn = $(By.xpath("//*[text()='Save']"));
    private final SelenideElement cancel_Btn = $(By.xpath("//*[text()='Cancel']"));
    private final SelenideElement searchbox = $(By.xpath("//input[(@class='Usersearchbox')]"));
    private final SelenideElement newUserRole = $(By.xpath("//td[text()='NewUserRole' and @class='customusername']"));
    private final SelenideElement newUserRoleName = $(By.xpath("//td[text()='NewUserRole' and @class='customusername']/parent::tr/td/div/div[text()='TestRole']"));
    private final SelenideElement roleName = $(By.xpath("//div[@class='roleTableColumnOne roleCardMarginOne']"));
    private final String disable_Edit_Button = "//div[contains(text(),'%s')]/following-sibling::div[5]/button";
    private final String NOTIFICATION_ERROR = "Role name: %s already exists.";

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

    public void sortList(String columnName, boolean descending) {
        SelenideElement sortAction = getRoleColumnHeader(columnName);
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "sortDesc")));
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "sortAsc")));
        $(By.xpath(String.format(XPATH_SORTICON, columnName))).click();
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void checkSortedElement(String columnName, boolean descending) {
        var index = getAllRoleColumnHeaders().indexOf(columnName);
        if (index == 0) {
            roleTableColumnclassValue = "roleTableColumnOne";
        } else if (index == 1) {
            roleTableColumnclassValue = "roleTableColumnTwo";
        }
        List<String> getRoles = $$(By.xpath(String.format(XPATH_ROLE_COLUMNS, roleTableColumnclassValue))).texts();
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
        //saveRoleButton.click();

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
                .shouldHave(CollectionCondition.allMatch("textNotEmpty", e -> !e.getText().isEmpty()))
                .texts();
    }

    public void roleExists(String role) {
        $(By.xpath(String.format(xpathEditRoleIcon, role))).shouldBe(Condition.visible);
    }

    public void verifyRoleNameToolTip(String role) {
        Assert.assertEquals($(By.xpath("//div[@class='roleTableColumnOne']")).getAttribute("title"), role);
    }

    public void NoRolesTab() {
        rolesLinkText.should(Condition.not(Condition.visible));
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

    public void notificationError(String name) {
        commonWaiter(roleNotificationBar, visible);
        String expectedNotificationText = String.format(NOTIFICATION_ERROR, name);
        roleNotificationBar.shouldHave(text(expectedNotificationText));
    }

    public void iVerifyDefaultRoles(String name) {
        switch (name) {
            case "Bio4C Service":
                bio4cservices.shouldBe(visible);
                break;
            case "Administrator":
                administrator.shouldBe(visible);
                break;
            case "ProcessManager":
                processManager.shouldBe(visible);
                break;
            case "Operator":
                operator.shouldBe(visible);
                break;
            default:
        }
    }

    public void rolePermissionAssigned(SelenideElement element, String roles,String rolePath) {
        SelenideHelper.commonWaiter(element, visible).click();
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
        if ((count == paramsSize) && (count != 0 && paramsSize != 0)) {
            Collections.sort(acceptedParams);
            Collections.sort(expectedParams);
            Assert.assertEquals(acceptedParams, expectedParams);
        }
        SelenideHelper.commonWaiter(closeIcon, visible).click();
    }

    public void adminRolePermission(String role){
        rolePermissionAssigned($(By.xpath(String.format(viewAll,"Administrator"))),role,"AdminRole.role");
    }

    public void serviceRolePermission(String role){
        rolePermissionAssigned($(By.xpath(String.format(viewAll,"Bio4CService"))),role,"ServiceRole.role");
    }

    public void processMgr_RolePermission(String role){
        rolePermissionAssigned($(By.xpath(String.format(viewAll,"ProcessManager"))),role,"ProcessManagerRole.role");
    }

    public void operatorRolePermission(String role){
        rolePermissionAssigned($(By.xpath(String.format(viewAll,"Operator"))),role,"OperatorRole.role");
    }

    public void validateViewIcon(String name) {
        $(By.xpath(String.format(viewIconOfAdministrator, name))).shouldBe(visible);
    }

    public void adminIcon() {
        SelenideHelper.commonWaiter(adminEditeIcon, visible).click();
    }

    public void unselectrolepremission(String name) {
        if ($(By.xpath(String.format(uncheckpermissions, name))).isDisplayed())
            $(By.xpath(String.format(uncheckpermissions, name))).click();
    }

    public void iCreateRondomName(String userName) {
        newRoleName.click();
        newRoleName.setValue(userName);
        SelenideHelper.commonWaiter(save_Btn, visible).click();
        SelenideHelper.commonWaiter(cancel_Btn, visible).click();
    }

    public void isearchName(String name) {
        SelenideHelper.commonWaiter(searchbox, visible).click();
        SelenideHelper.commonWaiter(searchbox, visible).setValue(name);
    }

    public void iCreateNewUser(String user) {
        newUserRole.shouldBe(visible);
    }

    public void rolename(String name) {
        newUserRoleName.shouldBe(visible);
    }

    public void verifyRoleName(String name) {
        roleName.shouldHave(text(name));
    }

    public String getOldRoleName() {
        newRoleName.click();
        return newRoleName.getAttribute("value");
    }
	
	public void deleteRole(String roleName) {
		commonWaiter($(By.xpath("//span[text()='Delete role']")),visible).click();
		commonWaiter($(By.xpath("//button[text()='Yes, delete']")),visible).click();
		commonWaiter(XPATH_NOTIFICATION_TEXT,visible);
		XPATH_NOTIFICATION_TEXT.shouldHave(text("The role successfully obsolete."));
		
	}

    public boolean defaultRoleDisabled(String value){
        return $(By.xpath(String.format(disable_Edit_Button,value))).isEnabled();
    }

    public void roleNameExists(String role) {
        $(By.xpath(String.format(roleNameCheck, role))).shouldBe(Condition.visible);
    }

    public void verifyAssignedPermission(String roleName, Set<String> set){
        SelenideHelper.commonWaiter($(By.xpath(String.format(viewAll,roleName))), visible).click();
        int count = rolePermissionList.size();
        List<String> acceptedParams = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            acceptedParams.add($(By.xpath(String.format(tagLabel, i))).getText());
        }
        List<String> list = new ArrayList<>(set);
        if ((count == set.size()) && (count != 0)) {
            Collections.sort(acceptedParams);
            Collections.sort(list);
            Assert.assertEquals(acceptedParams, list);
        }
        SelenideHelper.commonWaiter(closeIcon, visible).click();
    }

    public void updateRoleName(String roleName){
        commonWaiter(inputRoleName,visible).clear();
        inputRoleName.setValue(roleName);
        Assert.assertEquals("Role Name is not entered as expected", roleName, inputRoleName.getValue());
    }

}
