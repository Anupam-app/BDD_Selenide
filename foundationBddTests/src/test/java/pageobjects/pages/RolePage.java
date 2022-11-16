package pageobjects.pages;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dataobjects.RoleAction;
import org.openqa.selenium.By;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.SortHelper;
import java.util.List;
import java.util.function.Function;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class RolePage {

    private SelenideElement rolesLinkText = $(By.xpath("//*[@class='subMenu'][text()='Roles']"));

    private ElementsCollection permissionsText = $$(By.xpath("//label[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-checked\"]"));
    private SelenideElement saveText = $(By.className("roleModalNotificationBar"));
    private SelenideElement rolesText = $(By.xpath("//div[@class='noDataIndication']"));

    private SelenideElement inputRoleName = $(By.className("roleNameInput"));

    private SelenideElement saveRoleButton = $(By.id("save_Btn"));
    private SelenideElement closeButton = $(By.className("role-crossicon"));
    private SelenideElement createRoleButton = $(By.xpath("//*[@id=\"AddBtn\"]/div[2]"));

    private SelenideElement roleSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));

    private String xpathEditRoleIcon = "//div[div[contains(.,'%s')]]/div/div[contains(@class, 'edit-icon')]";

    private SelenideElement selectRoleDropdown = $(By.id("role"));
    
    private final SelenideElement roleNotificationBar = $(By.xpath("//*[@class = 'roleModalNotificationBar error-bar']"));
	private SelenideElement sortIcon = $(By.xpath("//div[@class='sortNone']"));
    private final String XPATH_SORTICON= "//div[text()='%s']/child::div[@class='sortNone']";
    private String roleTableColumnclassValue=null;
	private final String XPATH_ROLE_COLUMN_HEADER = "//div[@class='roleTableHeader']/div[text()='%s']";
    private final String XPATH_ORDER_ICON = "//div[@class='%s']";
	private final String XPATH_ROLE_TABLE = "//div[@class='roleTableHeader']";
    private final String XPATH_ROLE_COLUMNS = "//div[contains(@class,'%s')]";
    private final String NOTIFICATION_TEXT="Successfully %s role.";
    private final String PERMISSION_TEXT = "//span[text()='%s']";
    
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
    	if(index==0){roleTableColumnclassValue="roleTableColumnOne";}
    	else if (index==1) {roleTableColumnclassValue="roleTableColumnTwo";}
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

        String expectedNotificationText="";
        switch (roleAction){
            case ADDED:
                expectedNotificationText=String.format(NOTIFICATION_TEXT, "added");
                break;
            case UPDATED:
                expectedNotificationText=String.format(NOTIFICATION_TEXT, "updated");
                break;
            case ERROR:
            	expectedNotificationText=String.format(NOTIFICATION_ERROR, "testRoleToRemovePermission");
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
    
    public void NoRolesTab() {
        rolesLinkText.should(Condition.not(Condition.visible));
    }

	public void checkMessage(String message) {
		commonWaiter(roleNotificationBar,visible);
		roleNotificationBar.shouldHave(text(message));
		
	}
	public void saveButton() {
		saveRoleButton.click();
	}
	
	public void notificationError(String name) {
		commonWaiter(roleNotificationBar, visible);
	    String expectedNotificationText = String.format(NOTIFICATION_ERROR, name);
	    roleNotificationBar.shouldHave(text(expectedNotificationText));
	    }
}
