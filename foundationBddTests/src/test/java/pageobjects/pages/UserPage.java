package pageobjects.pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pageobjects.utility.SelenideHelper;
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

    private final ElementsCollection usersList =
            $$(By.xpath("//label[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-checked\"]"));

    private final SelenideElement XPATH_NOTIFICATION_TEXT =
            $(By.xpath("//*[@class='alarm_info_msg alert alert-info fade show']"));

    private final SelenideElement alertNotificationText = $(By.xpath("//*[@role='alert']"));

    private final SelenideElement XPATH_ERROR_NOTIFICATION_TEXT =
            $(By.xpath("//*[@class='alarm_alert_msg alert alert-danger fade show']"));

    private final SelenideElement selectOption = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement activeIcon = $(By.xpath("//div[@class='icontitle active']"));
    private final SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private final SelenideElement upArrowIcon = $(By.xpath("//div[@class='arrowupuserfilter']"));
    private final SelenideElement UsersLinkText = $(By.xpath("//*[@class='subMenu'][contains(text(),'Users')]"));
    private final SelenideElement idManagementPageLinkText = $(By.id("UserManagement"));
    private final SelenideElement filterTagText = $(By.xpath("//div[@class='filter-criteria-tag']"));
    private final SelenideElement userSearchTextBox = $(By.xpath("//input[contains(@placeholder, 'Search...')]"));
    private final SelenideElement employeeIDTextBox = $(By.id("employeeID"));
    private final SelenideElement firstNameTextBox = $(By.id("firstName"));
    private final SelenideElement lastNameTextBox = $(By.id("lastName"));
    private final SelenideElement userNameTextBox = $(By.id("userName"));
    private final SelenideElement emailIdTextBox = $(By.id("email"));
    private final SelenideElement mobileNumTextBox = $(By.id("phoneNumber"));
    private final SelenideElement departmentTextBox = $(By.id("department"));
    private final SelenideElement saveButton = $(By.id("save_Btn"));
    private final SelenideElement resetPwdButton = $(By.id("resetPass_Btn"));
    private final SelenideElement unlockAccountButton = $(By.xpath("//button[@class='user_btn btn_primary lck_btn']"));
    private final SelenideElement confirmationButton = $(By.id("remove_backup")); // TODO id to be changed from dev team
    private final SelenideElement disableUserButton = $(By.id("btn_disabl"));
    private final SelenideElement enableUserButton = $(By.id("btn_enabl"));
    private final SelenideElement createUserPlusButton = $(By.xpath("//div[contains(@class,'Adduserplus')]"));
    private final SelenideElement applyFilterButton = $(By.xpath("//button/b[contains(text(),'Apply Filter')]"));
    private final SelenideElement savePreferenceButton = $(By.className("btn-user-preferences"));
    private final SelenideElement roleNameTextBox = $(By.xpath("//span[@class='active-label']"));
    private final SelenideElement selectRoleFromDropdown = $(By.id("role"));
    private final String xpathEditUserIcon = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'edit-icon')]";
    private final SelenideElement cancelButton = $(By.xpath("//button/b[contains(text(),'Cancel')]"));
    private final String xpathUserName = "//tr[td[contains(.,'%s')]]";
    private final SelenideElement userNameField = $(By.xpath("(//td[@class='customusername'])[1]"));
    private final String defaultRolesNames = "//div[text()='%s']";
    private final String checkPermissions =
            "//label[@class='ant-checkbox-wrapper']/span/input/following-sibling::span[(contains(text(),'%s'))]";
    private final SelenideElement editIconOfOperator =
            $(By.xpath("//*[text()='Operator']/parent::div//div[5]/*[(@class='edit-icon')]"));
    private final String applicationModule = "//div[contains(@class,'icontitle') and contains(text(),'%s')]";
    private final SelenideElement userHeader = $(By.xpath("//div[text()='User Management']"));
    private final SelenideElement userProfile = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement changePwd = $(By.xpath("//span[text()='Change password']"));
    private final SelenideElement changePwdWindow = $(By.xpath("//h5[text()='Change Password']"));
    private final SelenideElement roleStatus = $(By.xpath("//table[@id='auditListTable']/tbody/tr/td[3]"));
    private final SelenideElement closeUserPropertiesChangeModal =
            $(By.xpath("//div[@id='userPropertiesChangeModal']/div/div[@class='crossicon']"));
    private final SelenideElement firstName = $(By.xpath("//div[@class='user-first-name-text']"));
    private final SelenideElement lastName = $(By.xpath("//div[@class='user-last-name-text']"));
    private final SelenideElement selectLanguageDropdown =
            $(By.xpath("//div[@id='language']//div[@class='role-dropdown-container']"));
    private final String XPATH_LANGUAGE_OPTION_DROPDOWN = "//li[contains(text(),'%s')]";
    private final SelenideElement roleAssigned =
            $(By.xpath("//table[@id='foundusertable']/tbody/tr/td[@class='rolecss']/div/div"));
    private final String moduleName = "//div[text()='%s']";

    private final String unlockMessage = "The account was successfully unlocked";

    public void setSearch(String search) {
        userSearchTextBox.clear();
        userSearchTextBox.setValue(search);
    }

    public void UserLocked(String user) {
        $(By.xpath(String.format(xpathUserName, user))).shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(230, 30, 80, 1)"));
        $(By.xpath(String.format(xpathUserName, user))).shouldHave(attribute("class", "rowLockedUser"));
    }

    public void UserUnLocked(String user) {
        cancelButton.click();
        $(By.xpath(String.format(xpathUserName, user))).shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(33, 37, 41, 1)"));
        $(By.xpath(String.format(xpathUserName, user) + "/td")).shouldHave(attribute("class", "customusername"));
    }

    public void edit(String user) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(xpathEditUserIcon, user))), visible)
                .click();
    }

    public void cannotEdit(String user) {
        $(By.xpath(String.format(xpathEditUserIcon, user))).should(not(appear));
    }

    public void usersNotEditable() {
        var users = $$(By.xpath(String.format(XPATH_USER_COLUMNS, 1))).texts();
        for (var user : users) {
            if (user.equals("Bio4CAdmin") || user.equals("Bio4cService")) {
                $(By.xpath(String.format(xpathEditUserIcon, user)))
                        .shouldHave(attribute("class", "edit-icon disabled"));
            } else {
                $(By.xpath(String.format(xpathEditUserIcon, user))).shouldBe(enabled);
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
        return disableUserButton.getAttribute("class")
                .equals("togg_btn toggle_act")
                && enableUserButton.getAttribute("class")
                        .equals("togg_btn");
    }

    public boolean isUserEnabled() {
        return enableUserButton.getAttribute("class")
                .equals("togg_btn toggle_act")
                && disableUserButton.getAttribute("class")
                        .equals("togg_btn");
    }

    public void createNewUser(String userName) {
        SelenideHelper.commonWaiter(createUserPlusButton, visible)
                .click();
        userNameTextBox.setValue(userName);
    }

    public void selectRole(String roleName) {
        selectRoleFromDropdown.click();
        List<WebElement> options = selectRoleFromDropdown.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText()
                    .equals(roleName)) {
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
        commonWaiter(roleNameTextBox, visible);
        return roleNameTextBox.getText();
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

    public void unlockAccount() {
        unlockAccountButton.click();
    }

    public void isGeneratedNotificationWhenUserModified(String user) {
        commonWaiter(XPATH_NOTIFICATION_TEXT, visible);
        XPATH_NOTIFICATION_TEXT.shouldHave(text("User account: " + user + " modified in server"));
    }

    public void isGeneratedNotificationWhenCreateExistingUsername(String message) {
        commonWaiter(XPATH_ERROR_NOTIFICATION_TEXT, visible);
        XPATH_ERROR_NOTIFICATION_TEXT.shouldHave(text(message));

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

    public void isGeneratedNotificationWhenPasswordReset(String name) {
        commonWaiter(XPATH_NOTIFICATION_TEXT, visible);
        Assert.assertTrue(XPATH_NOTIFICATION_TEXT.getText()
                .equalsIgnoreCase("User " + name
                        + " password reset successfully! New password has been sent to the user's registered email address."));
        commonWaiter(cancelButton, visible).click();
    }

    public void isGeneratedNotificationWhenAccountUnlock() {
        commonWaiter(XPATH_NOTIFICATION_TEXT, visible);
        Assert.assertTrue(XPATH_NOTIFICATION_TEXT.getText()
                .equalsIgnoreCase(unlockMessage));
    }

    public List<String> getAllUserColumnHeaders() {
        return $$(By.xpath(XPATH_USER_TABLE + "//th")).texts();
    }


    public void userProfile() {
        SelenideHelper.commonWaiter(userProfileIcon, visible)
                .click();
    }

    public void userPreferences() {
        userPreferencesLink.click();
    }

    public void chooseAndSaveDefaultPage(String defaultOptionName) {
        SelenideHelper.commonWaiter(selectOption, visible)
                .click();
        commonWaiter($(By.xpath(String.format(XPATH_LANGUAGE_OPTION_DROPDOWN, defaultOptionName))), visible).click();
        SelenideHelper.commonWaiter(savePreferenceButton, visible)
                .click();
    }

    public String getActiveIconTitle() {
        commonWaiter(activeIcon, visible);
        return activeIcon.getText();
    }

    public String getFilterTagText() {
        String value = filterTagText.getText();
        value = value.substring(0, value.length() - 1);
        return value;
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

    public void seeContent(String expectedText) {
        commonWaiter($(By.id("UserAccountContainer")), text(expectedText));
    }

    public void waitForUserCreationNotification(String userName) {
        SelenideHelper.commonWaiter(alertNotificationText, ownText(userName));
    }

    public void userProfileIcon() {
        SelenideHelper.commonWaiter(userProfile, visible)
                .click();
    }

    public void changePassword() {
        SelenideHelper.commonWaiter(changePwd, visible)
                .click();
    }

    public void windowPopup() {
        changePwdWindow.shouldBe(visible);
    }

    public void errorNotification(String message) {
        commonWaiter(XPATH_ERROR_NOTIFICATION_TEXT, visible);
        XPATH_ERROR_NOTIFICATION_TEXT.shouldHave(text(message));
    }

    public void updateNotification(String message) {
        commonWaiter(XPATH_NOTIFICATION_TEXT, visible);
        XPATH_NOTIFICATION_TEXT.shouldHave(text(message));
    }

    public void closeChangeUserPropertiesChangeModal() {
        SelenideHelper.commonWaiter(closeUserPropertiesChangeModal, visible)
                .click();
    }

    public void verifyUserProfileIcon(String firstname, String lastname) {
        commonWaiter(firstName, visible);
        firstName.shouldHave(text(firstname));
        commonWaiter(lastName, visible);
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

    public void clickOnAppModule(String name) {
        commonWaiter($(By.xpath(String.format(moduleName, name))), visible).click();
    }

    public void checkAlarmHeader() {
        userHeader.shouldBe(visible);
    }

    public void userAccountRole(String user1, String User2) {
        $(By.xpath(String.format(defaultRolesNames, user1))).shouldBe(visible);
        $(By.xpath(String.format(defaultRolesNames, User2))).shouldBe(visible);
    }

    public void saveMyUserChanges() {
        saveButton.click();
    }

    public void chooseUserLanguage(String language) {
        SelenideHelper.fluentWaiter()
                .until((webDriver) -> {
                    SelenideHelper.commonWaiter(selectLanguageDropdown, visible)
                            .click();
                    return $(By.xpath(String.format(XPATH_LANGUAGE_OPTION_DROPDOWN, language))).isDisplayed();
                });
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_LANGUAGE_OPTION_DROPDOWN, language))), visible)
                .click();
    }

    public void createUserIconPresent(String value) {
        if (value.equalsIgnoreCase("not exists")) {
            createUserPlusButton.should(not(appear));
        } else if (value.equalsIgnoreCase("exists")) {
            createUserPlusButton.should(appear);
        }
    }

    public void roleAssignedToUser(String role) {
        roleAssigned.shouldHave(text(role));
    }
}
