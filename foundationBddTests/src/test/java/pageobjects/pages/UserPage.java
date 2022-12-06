package pageobjects.pages;


import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selenide;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.SortHelper;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertEquals;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import pageobjects.utility.SortHelper;


public class UserPage {

	private final String XPATH_USER_COLUMN_HEADER = "//th[text()='%s']";
	private final String XPATH_USER_TABLE = "//table[@id='foundusertable']";
	private final String XPATH_USER_COLUMNS = "//table[@id='foundusertable']//td[%s]";
	private final String XPATH_ORDER_ICON = "//span[@class='%s']";
	private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
	private final SelenideElement userPreferencesLink = $(By.xpath("//Span[text()='User preferences']"));
	Function<Integer, List<String>> getUserColumns = (index) -> {
		var users = $$(By.xpath(String.format(XPATH_USER_COLUMNS, index))).texts();
		users.removeIf(e -> StringUtils.isEmpty(e.trim()));
		return users;
	};
	private ElementsCollection userslist = $$(By.xpath("//label[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-checked\"]"));
	private SelenideElement XPATH_NOTIFICATION_TEXT = $(By.xpath("//*[@class='alarm_info_msg alert alert-info fade show']"));
	private SelenideElement XPATH_ERRORNOTIFICATION_TEXT = $(By.xpath("//*[@class='alarm_alert_msg alert alert-danger fade show']"));
	private SelenideElement selectOption = $(By.xpath("//span[@class='icon-down-arrow']"));
	private SelenideElement activeIcon = $(By.xpath("//div[@class='icontitle active']"));
	private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
	private SelenideElement upArrowIcon = $(By.xpath("//div[@class='arrowupuserfilter']"));
	private SelenideElement UsersLinkText = $(By.xpath("//*[@class='subMenu'][text()='Users']"));
	private SelenideElement idManagementPageLinkText = $(By.id("UserManagement"));
	private SelenideElement filterTagText = $(By.xpath("//div[@class='userfiltertag']"));
	private SelenideElement userSearchTextBox = $(By.xpath("//input[@placeholder='Search...']"));
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
	private SelenideElement createUserPlusButton = $(By.xpath("//div[@class='Adduserplus']"));
	private SelenideElement applyFilterButton = $(By.xpath("//button/b[text()='Apply Filter']"));
	private SelenideElement savePreferenceButton = $(By.className("btn-user-preferences"));
	private SelenideElement roleNameTextbox = $(By.xpath("//span[@class='active-label']"));
	private SelenideElement selectRoleFromDropdown = $(By.id("role"));
	private String xpathEditUserIcon = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'edit-icon')]";
	private String xpathUserName = "//tr[td[contains(.,'%s')]]";
	private SelenideElement cancelButton = $(By.xpath("//button/b[text()='Cancel']"));
	private SelenideElement userNameField = $(By.xpath("(//td[@class='customusername'])[1]"));

	private ElementsCollection rolePermissionList = $$(By.xpath("//div[(@class='modal-body')]//div//div"));
	private final String tagLabel = "//div[@class='permission-col'][%s]";   
	private final String defaultRolesnames = "//div[text()='%s']";
	private final String viewIconOfAdministrator = "//*[text()='%s']/parent::div//div[5]/*[(@class='MuiSvgIcon-root roleViewIcon vieIconBottom')]";
	private final String uncheckpermissions ="//label[contains(@class,'ant-checkbox-wrapper ant-checkbox')]/span[(contains(text(),'%s'))]";
	private final String checkPermissions ="//label[@class='ant-checkbox-wrapper']/span/input/following-sibling::span[(contains(text(),'%s'))]";
	//vate SelenideElement viewIconOfBio4cservice = $(By.xpath("//*[text()='Bio4CService']/parent::div//div[5]/*[(@class='MuiSvgIcon-root roleViewIcon vieIconBottom')]"));
	private SelenideElement editIconOfProcessManager =$(By.xpath("//*[text()='ProcessManager']/parent::div//div[5]/*[(@class='edit-icon')]"));
	private SelenideElement editIconOfOperator = $(By.xpath("//*[text()='Operator']/parent::div//div[5]/*[(@class='edit-icon')]"));
	private SelenideElement bio4cservices = $(By.xpath("//div[text()='Bio4CService']"));
	private SelenideElement administrator = $(By.xpath("//div[text()='Administrator']"));
	private SelenideElement processManager = $(By.xpath("//div[text()='ProcessManager']"));
	private SelenideElement operator = $(By.xpath("//div[text()='Operator']"));
	private SelenideElement roleTab = $(By.xpath("//div[text()='Roles']"));
	private SelenideElement proc_viewAll =$(By.xpath("//div[contains(text(),'ProcessManager')]/following::div[2]/a"));
	private SelenideElement viewAll = $(By.xpath("//div[contains(text(),'Administrator')]/following::div[2]/a"));
	private SelenideElement closeIcon = $(By.xpath("//div[(@class='viewAll-crossicon')]"));
	private SelenideElement adminEditeIcon = $(By.xpath("//div[(@class='roleCard')]/parent::div/div[3]/div[5]/div"));
	private SelenideElement newRoleName = $(By.xpath("//input[(@class='roleNameInput')]"));
	private SelenideElement save_Btn = $(By.xpath("//*[text()='Save']"));
	private SelenideElement cancel_Btn = $(By.xpath("//*[text()='Cancel']"));
	private SelenideElement searchbox = $(By.xpath("//input[(@class='Usersearchbox')]"));
	private SelenideElement newUserRole =$(By.xpath("//td[text()='NewUserRole' and @class='customusername']"));
	private SelenideElement newUserRoleName = $(By.xpath("//td[text()='NewUserRole' and @class='customusername']/parent::tr/td/div/div[text()='TestRole']"));
	private final String applicationModule = "//div[contains(@class,'icontitle') and contains(text(),'%s')]";
	//private SelenideElement trendsHeader =$(By.xpath("//div[@class='trends-sidebar']/div/div[text()='Trends']"));
	private SelenideElement mainHeader =$(By.xpath("//span[text()='You are controlling main screen']"));
	private SelenideElement alaramHeader = $(By.xpath("//div[text()='Alarms']"));
	private SelenideElement trendsHeader = $(By.xpath("//div[@class='header']"));
	private SelenideElement analytics = $(By.xpath("//span[@class='aggregate-header']"));
	private SelenideElement recipeHeader = $(By.xpath("//h2[contains(text(),'Recipe Management')]"));
	private SelenideElement reportHeader = $(By.xpath("//h2[contains(text(),'Report Management')]"));
	private SelenideElement configurationHeader = $(By.xpath("//h4[contains(text(),'Process Control')]"));
	private SelenideElement userHeader = $(By.xpath("//div[text()='User Management']"));
	private SelenideElement backupHeader = $(By.xpath("//div[text()='Backup and Restore']"));
	private SelenideElement settingsHeader = $(By.xpath("//div[text()='Settings' and @class='setting-header-title']"));
	private final SelenideElement userProfile = $(By.xpath("//*[@id='userProfile']"));
	private SelenideElement chnagePwd = $(By.xpath("//span[text()='Change password']"));
	private SelenideElement chnagePwdWindow =$ (By.xpath("//h5[text()='Change Password']"));
	private SelenideElement currentPWD =$(By.xpath("//input[@placeholder='Current Password']"));
	private SelenideElement newPWD =$(By.xpath("//input[@placeholder='New Password']"));
	private SelenideElement confirmPWD=$(By.xpath("//input[@placeholder='Confirm Password']"));
	private SelenideElement SavePWD= $(By.xpath("//*[text()='Save Password']"));
	private SelenideElement roleStatus = $(By.xpath("//table[@id='auditListTable']/tbody/tr/td[3]"));
	private SelenideElement closeUserPropertiesChangeModal=$(By.xpath("//div[@id='userPropertiesChangeModal']/div/div[@class='crossicon']"));
	private SelenideElement firstName=$(By.xpath("//div[@class='user-first-name-text']"));
	private SelenideElement lastName=$(By.xpath("//div[@class='user-last-name-text']"));
	private SelenideElement mainModule=$(By.xpath("//div[text()='Main']"));
	private SelenideElement htmlPage=$(By.tagName("html"));
	private final String uncheckpermissionsClass ="//label[contains(@class,'ant-checkbox-wrapper ant-checkbox')]/span[(contains(text(),'%s'))]/parent::label";
	private SelenideElement roleName=$(By.xpath("//div[@class='roleTableColumnOne roleCardMarginOne']"));
	private TrendsPage trendsPage;
	private AnalyticsPage analyticsPage;
	private LoginPage loginPage;
	private RecipePage recipePage;
	private ReportsPage reportsPage;

	public UserPage(LoginPage loginPage,TrendsPage trendsPage,AnalyticsPage analyticsPage,RecipePage recipePage,
			ReportsPage reportsPage)
	{
		this.loginPage = loginPage;
		this.trendsPage = trendsPage;
		this.analyticsPage = analyticsPage;
		this.recipePage=recipePage;
		this.reportsPage=reportsPage;
	}

	public void setSearch(String search) {
		userSearchTextBox.clear();
		userSearchTextBox.setValue(search);
		userSearchTextBox.waitUntil(Condition.visible, 10000l);
	}

	public void UserLocked(String user) {
		Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user))).waitUntil(visible, 5000l).getCssValue("color")), "rgba(230, 30, 80, 1)");
		Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user))).getAttribute("class")), "rowLockedUser");
	}

	public void UserUnLocked(String user) {
		cancelButton.click();
		Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user))).waitUntil(visible, 5000l).getCssValue("color")), "rgba(33, 37, 41, 1)");
		Assert.assertEquals(($(By.xpath(String.format(xpathUserName, user) + "/td")).getAttribute("class")), "customusername");
	}

	public void edit(String user) {
		$(By.xpath(String.format(xpathEditUserIcon, user))).waitUntil(visible, 5000l).click();
	}

	public void cannotEdit(String user) {
		Assert.assertTrue($(By.xpath(String.format(xpathEditUserIcon, user))).isEnabled());
		$(By.xpath(String.format(xpathEditUserIcon, user))).shouldNotBe(selected);
	}

	public void usersNotEditable() {
		var users = $$(By.xpath(String.format(XPATH_USER_COLUMNS, 1))).texts();
		for (var user : users) {
			if (user.equals("Bio4CAdmin") || user.equals("Bio4cService")) {
				Assert.assertEquals("Edit icon is enabled for system users", ($(By.xpath(String.format(xpathEditUserIcon, user))).getAttribute("class")), "edit-icon disabled");
			} else {
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
		createUserPlusButton.click();
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
		commonWaiter(XPATH_NOTIFICATION_TEXT, visible);
		XPATH_NOTIFICATION_TEXT.shouldHave(text("User account: " + user + " modified in server"));
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
		$(By.xpath(String.format("//span[text()='%s']", status))).click();
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
		commonWaiter($(By.xpath(String.format("//li[text()='%s']", defaultOptionName))), visible).click();
		SelenideHelper.commonWaiter(savePreferenceButton, visible).click();
	}

	public String getActiveIconTitle() {
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
		SortHelper.checkSortedElement(getAllUserColumnHeaders(), columnName, descending, getUserColumns, false, null);
	}

	public void userAccountRole(String user1,String User2) {
		$(By.xpath(String.format(defaultRolesnames, user1))).shouldBe(visible);
		$(By.xpath(String.format(defaultRolesnames, User2))).shouldBe(visible);
	}
	public void roleIcon() {
		SelenideHelper.commonWaiter(roleTab, visible).click();
	}
	public void iVerifyDefaultRoles(String name) {
		switch(name) {
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
	public void privilegesOfroles(String roles ) {
		SelenideHelper.commonWaiter(viewAll, visible).click();
		int count=rolePermissionList.size();
		List <String> acceptedParams = new ArrayList <String>();
		List <String> expectedParams = new ArrayList <String>();
		for (int i=1;i<=count;i++)
		{
			acceptedParams.add($(By.xpath(String.format(tagLabel, i))).getText());
		}
		var config = ConfigFactory.parseResourcesAnySyntax(roles,ConfigParseOptions.defaults());
		var params = config.getConfigList("ServiceRole.Role");
		int paramsSize = params.size();
		for (var param : params) {
			expectedParams.add(param.getString("value"));
		}
		if((count==paramsSize) && (count!=0 && paramsSize!=0)) {
			Collections.sort(acceptedParams);
			Collections.sort(expectedParams);
			Assert.assertEquals(acceptedParams,expectedParams );
		}
		SelenideHelper.commonWaiter(closeIcon, visible).click();
	}
	public void processManager(String roles ) {
		SelenideHelper.commonWaiter(proc_viewAll, visible).click();
		int count=rolePermissionList.size();
		List <String> acceptedParams = new ArrayList <String>();
		List <String> expectedParams = new ArrayList <String>();
		for (int i=1;i<=count;i++)
		{
			acceptedParams.add($(By.xpath(String.format(tagLabel, i))).getText());
		}
		var config = ConfigFactory.parseResourcesAnySyntax(roles,ConfigParseOptions.defaults());
		var params = config.getConfigList("manager.Role");
		int paramsSize = params.size();
		for (var param : params) {
			expectedParams.add(param.getString("value"));
		}
		if((count==paramsSize) && (count!=0 && paramsSize!=0)) {
			Collections.sort(acceptedParams);
			Collections.sort(expectedParams);
			Assert.assertEquals(acceptedParams,expectedParams );
		}
		SelenideHelper.commonWaiter(closeIcon, visible).click();
	}
	public void validateViewIcon(String name) {
		$(By.xpath(String.format(viewIconOfAdministrator, name))).shouldBe(visible);                    
	}
	public void adminIcon() {
		SelenideHelper.commonWaiter(adminEditeIcon, visible).click();                  
	}
	public void unselectrolepremission(String name){
		if($(By.xpath(String.format(uncheckpermissions, name))).isDisplayed())
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
	public void ststusOfrole(String role) {
	}
	public void iCreateNewUser(String user) {
		newUserRole.shouldBe(visible);
	}
	public void rolename(String name) {
		newUserRoleName.shouldBe(visible);
	}
	public void userProfileIcon() {
		SelenideHelper.commonWaiter(userProfile, visible).click();                    
	}
	public void changePassword() {
		SelenideHelper.commonWaiter(chnagePwd, visible).click();
	}
	public void windowPopup() {
		chnagePwdWindow.shouldBe(visible);
	}
	public void actualroleValue() {
		SelenideHelper.commonWaiter(roleStatus, visible).getText();
	}
	public void newAssignRole(String newrole) {
		SelenideHelper.commonWaiter(editIconOfOperator, visible).click();
		$(By.xpath(String.format(checkPermissions, newrole))).click();
	}
	public void modules(String name) {
		$(By.xpath(String.format(applicationModule, name))).should(visible);
	}
	public void iVerifyApp_Modules(String name){
		commonWaiter($(By.xpath(String.format("//div[text()='%s']", name))), visible).click();
		switch(name) {
		case "Main":                        
			loginPage.waitControlOnPnid();
			mainHeader.shouldBe(visible);
			break;                            
		case "Trends":                        
			SelenideHelper.goToIFrame();
			trendsPage.trendsHeaderValidation();
			SelenideHelper.goToDefault();
			break;
		case "Analytics":                
			SelenideHelper.goToIFrame();
			analyticsPage.verifyAnalyticsHeader();
			SelenideHelper.goToDefault();
			break;
		case "Alarms":
			SelenideHelper.goToIFrame();
			alaramHeader.shouldBe(visible);
			SelenideHelper.goToDefault();
			break;
		case "Recipes":
			SelenideHelper.goToIFrame();
			recipePage.verifyRecipeHeader();
			SelenideHelper.goToDefault();
			break;
		case "Reports":
			SelenideHelper.goToIFrame();
			reportsPage.verifyReportsHeader();
			SelenideHelper.goToDefault();
			break;
		case "Configuration":
			SelenideHelper.goToIFrame();
			configurationHeader.shouldBe(visible);
			SelenideHelper.goToDefault();
			break;
		case "Users":
			userHeader.shouldBe(visible);
			break;
		case "Backup":
			backupHeader.shouldBe(visible);
			break;
		case "Settings":
			settingsHeader.shouldBe(visible);
			break;
		}
	}
	public void errorNotification(String message) {
		commonWaiter(XPATH_ERRORNOTIFICATION_TEXT,visible);
		XPATH_ERRORNOTIFICATION_TEXT.shouldHave(text(message));                  
	}
	public void updateNotification(String message) {
		commonWaiter(XPATH_NOTIFICATION_TEXT,visible);
		XPATH_NOTIFICATION_TEXT.shouldHave(text(message));                
	}
	public void closeChangeUserPropertiesChangeModal() {
		SelenideHelper.commonWaiter(closeUserPropertiesChangeModal, visible).click();
	}
	public void verifyUserProfileIcon(String firstname,String lastname,String role) {
		commonWaiter(firstName,visible);
		firstName.shouldHave(text(firstname));
		commonWaiter(lastName,visible);
		lastName.shouldHave(text(lastname));
	}
	public void zoomOut() throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < 5; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Selenide.sleep(2000);
		}
	}
	public void verifyRoleName(String name) {          
		roleName.shouldHave(text(name));
	}
	public String getOldUserName() {                    
		newRoleName.click();                
		return newRoleName.getAttribute("value");
	}  

}
