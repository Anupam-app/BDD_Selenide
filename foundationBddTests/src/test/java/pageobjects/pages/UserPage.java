package pageobjects.pages;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import pageobjects.utility.SortHelper;

public class UserPage {

    private static final String XPATH_HEADER = "//div[@class='headerTitle']";
    private final String XPATH_USER_COLUMN_HEADER = "//th[contains(text(),'%s')]";
    private final String XPATH_USER_TABLE = "//table[@id='foundusertable']";
    private final String XPATH_USER_COLUMNS = "//table[@id='foundusertable']//td[%s]";
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement userPreferencesLink = $(By.xpath("//span[contains(text(),'User Preferences')]"));
	
    Function<Integer, List<String>> getUserColumns = (index) -> {
        var users = $$(By.xpath(String.format(XPATH_USER_COLUMNS, index))).texts();
        users.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return users;
    };

    private ElementsCollection userslist = $$(By.xpath("//label[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-checked\"]"));

    private SelenideElement XPATH_NOTIFICATION_TEXT = $(By.xpath("//*[@class='alarm_info_msg alert alert-info fade show']"));

    private SelenideElement alertNotificationText = $(By.xpath("//*[@role='alert']"));

    private SelenideElement XPATH_ERRORNOTIFICATION_TEXT = $(By.xpath("//*[@class='alarm_alert_msg alert alert-danger fade show']"));

    private SelenideElement selectOption = $(By.xpath("//span[@class='icon-down-arrow']"));
    private SelenideElement activeIcon = $(By.xpath("//div[@class='icontitle active']"));
    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private SelenideElement upArrowIcon = $(By.xpath("//div[@class='arrowupuserfilter']"));
    private SelenideElement UsersLinkText = $(By.xpath("//*[@class='subMenu'][contains(text(),'Users')]"));
    private SelenideElement idManagementPageLinkText = $(By.id("UserManagement"));
    private SelenideElement filterTagText = $(By.xpath("//div[@class='userfiltertag']"));
    private SelenideElement userSearchTextBox = $(By.xpath("//input[contains(@placeholder, 'Search...')]"));
    private SelenideElement employeeIDTextBox = $(By.id("employeeID"));
    private SelenideElement firstNameTextBox = $(By.id("firstName"));
    private SelenideElement lastNameTextBox = $(By.id("lastName"));
    private SelenideElement userNameTextBox = $(By.id("userName"));
    private SelenideElement emailIdTextBox = $(By.id("email"));
    private SelenideElement mobileNumTextBox = $(By.id("phoneNumber"));
    private SelenideElement departmentTextBox = $(By.id("department"));
    private SelenideElement saveButton = $(By.id("save_Btn"));
    private SelenideElement resetPwdButton = $(By.id("resetPass_Btn"));
    private SelenideElement confirmationButton = $(By.id("remove_backup")); //TODO id to be changed from dev team
    private SelenideElement disableUserButton = $(By.id("btn_disabl"));
    private SelenideElement enableUserButton = $(By.id("btn_enabl"));
    private SelenideElement createUserPlusButton = $(By.xpath("//div[contains(@class,'Adduserplus')]"));
    private SelenideElement applyFilterButton = $(By.xpath("//button/b[contains(text(),'Apply Filter')]"));
    private SelenideElement savePreferenceButton = $(By.className("btn-user-preferences"));
    private SelenideElement roleNameTextbox = $(By.xpath("//span[@class='active-label']"));
    private SelenideElement selectRoleFromDropdown = $(By.id("role"));
    private String xpathEditUserIcon = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'edit-icon')]";
    private SelenideElement cancelButton = $(By.xpath("//button/b[contains(text(),'Cancel')]"));
    private String xpathUserName = "//tr[td[contains(.,'%s')]]";
    private SelenideElement userNameField = $(By.xpath("(//td[@class='customusername'])[1]"));

    public void setSearch(String search) {
        SelenideHelper.commonWaiter(userSearchTextBox, visible).clear();
        userSearchTextBox.setValue(search);
    }
    
    public void UserLocked(String user) {
    	Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user))).waitUntil(visible, 5000l).getCssValue("color")),"rgba(230, 30, 80, 1)");
    	Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user))).getAttribute("class")),"rowLockedUser");
    }
    public void UserUnLocked(String user) {
    	cancelButton.click();
    	Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user))).waitUntil(visible, 5000l).getCssValue("color")),"rgba(33, 37, 41, 1)");
    	Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user)+"/td")).getAttribute("class")),"customusername");
    }

    public void edit(String user) {
        $(By.xpath(String.format(xpathEditUserIcon, user))).waitUntil(visible, 5000l).click();
    }
    
    public void cannotEdit(String user) {
        Assert.assertTrue($(By.xpath(String.format(xpathEditUserIcon, user))).isEnabled());
    }
	
	public void usersNotEditable() {
    	var users= $$(By.xpath(String.format(XPATH_USER_COLUMNS, 1))).texts();
    	for(var user:users) {
    		if(user.equals("Bio4CAdmin") || user.equals("Bio4cService")) {
    			Assert.assertEquals("Edit icon is enabled for system users",($(By.xpath(String.format(xpathEditUserIcon, user))).getAttribute("class")),"edit-icon disabled");
    		}
    		else {
    			Assert.assertTrue($(By.xpath(String.format(xpathEditUserIcon, user))).isEnabled());
    		}
    		
    	}
    }

    public void userExists(String user) {
        $(By.xpath(String.format(xpathEditUserIcon, user))).should(be(visible));
    }

    public void setEmployeeId(String employeeId) {
        employeeIDTextBox.setValue(employeeId);
    }

    public void saveMyChanges() {
        saveButton.click();
        commonWaiter(confirmationButton, visible).click();
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
    
    public void cancel() {
        cancelButton.click();
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
        SelenideHelper.commonWaiter(createUserPlusButton, visible).click();
        userNameTextBox.setValue(userName);
    }

    public void selectRole(String roleName) {
        selectRoleFromDropdown.click();
        List<WebElement> options = selectRoleFromDropdown.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals(roleName)) {
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

    public void enterMobNum(String mobNum) {
        mobileNumTextBox.setValue(mobNum);
    }

    public void enterDepartmentName(String deptName) {
        departmentTextBox.setValue(deptName);
    }

    public String getUserNameFromForm() {
        return userNameTextBox.getValue();
    }

    public String getEmailIdFromForm() {
        return emailIdTextBox.getValue();
    }

    public String getRoleNameFromForm() {
      	commonWaiter(roleNameTextbox, visible);
        return roleNameTextbox.getText();
    }

    public String getMobNumFromForm() {
        return mobileNumTextBox.getValue();
    }

    public String getDeptNameFromForm() {
        return departmentTextBox.getValue();
    }

    public void clearSearch() {
        userSearchTextBox.clear();
    }

    public void triggerUsersMode() {
        UsersLinkText.click();
    }

    public void resetPassword() {
        resetPwdButton.click();
    }

    public void isGeneratedNotificationWhenPasswordReset() {
        commonWaiter(XPATH_NOTIFICATION_TEXT, visible);
    }
    
    public void isGeneratedNotificationWhenUserModified(String user) {
        commonWaiter(XPATH_NOTIFICATION_TEXT,visible);
        XPATH_NOTIFICATION_TEXT.shouldHave(text("User account: "+user+" modified in server"));
    }

    public void isGeneratedNotificationWhenCreateExistingUsername(String message) {
        commonWaiter(XPATH_ERRORNOTIFICATION_TEXT, visible);
        XPATH_ERRORNOTIFICATION_TEXT.shouldHave(text(message));

    }

    public void cancelUser() {
        userNameTextBox.click();
        cancelButton.click();
    }

    public String getUserDetails() {
        return userNameField.getText();
    }

    public void selectStatus(String status) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        commonWaiter(upArrowIcon, visible);
        upArrowIcon.click();
        $(By.xpath(String.format("//span[contains(text(),'%s')]", status))).click();
        applyFilterButton.click();
    }

    public SelenideElement getUserColumnHeader(String columnName) {
    	System.out.println($(By.xpath(String.format(XPATH_USER_COLUMN_HEADER, columnName))));
    	return $(By.xpath(String.format(XPATH_USER_COLUMN_HEADER, columnName)));
        
    }

    public List<String> getAllUserColumnHeaders() {
        return $$(By.xpath(XPATH_USER_TABLE + "//th")).texts();
    }


    public void userProfile() {
        SelenideHelper.commonWaiter(userProfileIcon, visible).click();
    }

    public void userPreferences() {
        userPreferencesLink.click();
    }

    public void chooseAndSaveDefaultPage(String defaultOptionName) {
        SelenideHelper.commonWaiter(selectOption, visible).click();
        commonWaiter($(By.xpath(String.format("//li[contains(text(),'%s')]", defaultOptionName))), visible).click();
        SelenideHelper.commonWaiter(savePreferenceButton, visible).click();
    }

    public String getActiveIconTitle() {
    	commonWaiter(activeIcon, visible);
        return activeIcon.getText();
    }

    public String getFilterTagText() {
        return filterTagText.getText();
    }

    public String getUserName() {
        return $(By.xpath(String.format(XPATH_USER_COLUMNS, 1))).getText();
    }

    public void sortList(String columnName, boolean descending) {
        SelenideElement sortAction = getUserColumnHeader(columnName);
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order")));
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void checkSortedElement(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllUserColumnHeaders(), columnName, descending, getUserColumns);
    }

    public void seeContent(String expectedText) {
        commonWaiter($(By.xpath(XPATH_HEADER)), text(expectedText));
	}

    public void waitForUserCreationNotification(String userName) {
        SelenideHelper.commonWaiter(alertNotificationText, ownText(userName));
    }
}
