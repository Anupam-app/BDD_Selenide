package cucumber.steps;

import static com.codeborne.selenide.Selenide.switchTo;

import com.codeborne.selenide.Selenide;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import cucumber.util.I18nUtils;
import dataobjects.Login;
import dataobjects.Report;
import dataobjects.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.AlarmPage;
import pageobjects.pages.AnalyticsPage;
import pageobjects.pages.BackupPage;
import pageobjects.pages.ConfigurationPage;
import pageobjects.pages.LoginPage;
import pageobjects.pages.RecipePage;
import pageobjects.pages.ReportsPage;
import pageobjects.pages.SettingPage;
import pageobjects.pages.TrendsPage;
import pageobjects.pages.UserPage;
import pageobjects.utility.SelenideHelper;

public class UserPageStepsDefinition {

    private final UserPage userPage;
    private final LoginPage loginPage;
    private final TrendsPage trendsPage;
    private final RecipePage recipePage;
    private final AnalyticsPage analyticsPage;
    private final AlarmPage alarmPage;
    private final BackupPage backupPage;
    private final SettingPage settingPage;
    private final ConfigurationPage configurationPage;
    private final User user;
    private final Report report;
    private final ReportsPage reportPage;
    private final Login login;

    public UserPageStepsDefinition(ReportsPage reportPage, UserPage userPage, LoginPage loginPage,
            RecipePage recipePage, TrendsPage trendsPage, AnalyticsPage analyticsPage, AlarmPage alarmPage,
            BackupPage backupPage, SettingPage settingPage, ConfigurationPage configurationPage, Report report,
            User user, Login login) {
        this.userPage = userPage;
        this.user = user;
        this.report = report;
        this.reportPage = reportPage;
        this.login = login;
        this.loginPage = loginPage;
        this.analyticsPage = analyticsPage;
        this.recipePage = recipePage;
        this.trendsPage = trendsPage;
        this.alarmPage = alarmPage;
        this.backupPage = backupPage;
        this.configurationPage = configurationPage;
        this.settingPage = settingPage;
    }

    @Given("I search {string} user")
    public void iSearchUser(String user) {
        this.user.setUserName(user);
        userPage.setSearch(user);
    }

    @When("I see the user is locked")
    public void iSeeUserLocked() {
        userPage.UserLocked(this.user.getUserName());
    }

    @When("I see the user is unlocked")
    public void iSeeUserUnLocked() {
        userPage.UserUnLocked(this.user.getUserName());
    }

    @When("I search the user")
    public void iSearchTheUser() {
        userPage.setSearch(this.user.getUserName());
    }

    @Given("I go to user page")
    public void iGoToUserPage() {
        userPage.goTo();
    }

    @Given("I trigger Users mode")
    public void iTriggerUsersMode() {
        userPage.triggerUsersMode();
    }

    @When("I edit the user")
    public void iModifyUser() {
        userPage.edit(user.getUserName());
    }

    @When("I cant edit the user")
    public void iCannotModifyUser() {
        userPage.cannotEdit(user.getUserName());
    }

    @When("I change the employee id with a random string")
    public void iChangeTheEmployeeIdWithARandomString() {
        user.setEmployeeId(RandomStringUtils.randomAlphabetic(10));
        userPage.setEmployeeId(user.getEmployeeId());
    }

    @When("I save my user changes")
    public void iSaveMyChanges() {
        userPage.saveMyChanges();
    }

    @When("I check user notification is displayed")
    public void iCheckUserNotificationIsDisplayed() {
        userPage.waitForUserCreationNotification(user.getUserName());
        userPage.isGeneratedNotificationWhenUserModified(user.getUserName());
    }

    @When("I save the new user")
    public void iSaveNewUser() {
        userPage.saveMyChanges();
    }

    @When("I disable the user")
    public void iDisableTheUser() {
        userPage.disableUser();
    }

    @Then("the user is disabled")
    public void theUserIsDisabled() {
        Assert.assertTrue(userPage.isUserDisabled());
        userPage.cancel();
    }

    @Then("I enable the user")
    public void iEnableTheUser() {
        userPage.enableUser();
    }

    @Then("the user is enabled")
    public void theUserIsEnabled() {
        Assert.assertTrue(userPage.isUserEnabled());
        userPage.cancel();
    }

    @Then("the employee id is equal to the string input")
    public void theEmployeeIdIsEqualToTheStringInput() {
        Assert.assertEquals(userPage.getEmployeeIdFromForm(), user.getEmployeeId());
    }

    @And("the user {string} exists")
    public void theUserExists(String user) {
        userPage.goTo();
        userPage.setSearch(user);
        userPage.userExists(user);
        userPage.clearSearch();
    }

    @When("I create a random username")
    public void iCreateRandomUsername() {
        this.user.setUserName(RandomStringUtils.randomAlphabetic(10));
        userPage.createNewUser(this.user.getUserName());
    }

    @And("default users are not editable")
    public void verifyEditing() {
        userPage.usersNotEditable();
    }

    @When("I select role {string}")
    public void iSelectRole(String user) {
        this.user.setRoleName(user);
        userPage.selectRole(user);
    }

    @When("I enter random firstname")
    public void iEnterRandomFirstName() {
        this.user.setFirstName(RandomStringUtils.randomAlphabetic(10));
        userPage.enterFirstName(this.user.getFirstName());
    }

    @When("I enter random lastname")
    public void iEnterRandomLastName() {
        this.user.setLastName(RandomStringUtils.randomAlphabetic(10));
        userPage.enterLastName(this.user.getLastName());
    }

    @When("I enter random employeeID")
    public void iEnterRandomEmployeeID() {
        this.user.setEmployeeId(RandomStringUtils.randomAlphabetic(10));
        userPage.enterEmpId(user.getEmployeeId());
    }

    @When("I enter random department")
    public void iEnterRandomDepartment() {
        this.user.setDeptName(RandomStringUtils.randomAlphabetic(10));
        userPage.enterDepartmentName(user.getDeptName());
    }

    @When("I enter mobile number {string}")
    public void iEnterMobNum(String user) {
        this.user.setMobNum(user);
        userPage.enterMobNum(user);
    }

    @When("I choose user language {string}")
    public void iChooseUserLanguage(String language) {
        userPage.chooseUserLanguage(language);
    }

    @When("I enter email {string}")
    public void iEnterEmail(String user) {
        this.user.setEmailId(user);
        userPage.enterEmail(user);
    }

    @When("I enter username {string}")
    public void iEnterUsername(String user) {
        this.user.setUserName(user);
        userPage.createNewUser(this.user.getUserName());
    }

    @Then("The username is equal to the expected one")
    public void theUserNameIsEqualToTheExpectedOne() {
        Assert.assertEquals(userPage.getUserNameFromForm(), user.getUserName());
        userPage.cancelUser();
    }

    @Then("the employee id is the expected one")
    public void theEmployeeIdIsEqualToTheExpectedOne() {
        Assert.assertEquals(userPage.getEmployeeIdFromForm(), user.getEmployeeId());
    }

    @Then("the role is {string}")
    public void theRoleIsEqualToTheExpectedOne(String role) {
        Assert.assertEquals(userPage.getRoleNameFromForm(), role);
    }

    @Then("I see user details are changed")
    public void verifyUserDetails() {
        iSearchTheUser();
        iModifyUser();
        Assert.assertEquals(user.getEmployeeId(), userPage.getEmployeeIdFromForm());
        Assert.assertEquals(user.getEmailId(), userPage.getEmailIdFromForm());
        Assert.assertEquals(user.getRoleName(), userPage.getRoleNameFromForm());
        Assert.assertEquals(user.getMobNum(), userPage.getMobNumFromForm());
        Assert.assertEquals(user.getDeptName(), userPage.getDeptNameFromForm());
        userPage.cancelUser();
    }

    @Then("I see the {string} user modified in report")
    public void iVerifyThatUserIsModified(String userName) throws Exception {
        Map<String, String> list = new HashMap<>();
        list.put("department", user.getDeptName());
        list.put("phoneNumber", user.getMobNum());
        list.put("role", user.getRoleName());
        list.put("employeeID", user.getEmployeeId());
        list.put("email", user.getEmailId());
        this.report.checkModifiedUser(reportPage.getPdfUrl(), userName, this.login.getLogin(), list);
        switchTo().parentFrame();
    }

    @When("I click on reset password")
    public void iClickOnResetPassword() {
        userPage.resetPassword();
    }

    @When("I unlock the account")
    public void iClickOnUnlockAccount() {
        userPage.unlockAccount();
    }

    @Then("I see password reset message is displayed")
    public void iSeePasswordResetMessageDisplayed() {
        userPage.isGeneratedNotificationWhenPasswordReset(this.user.getUserName());
    }

    @Then("I see account unlock message is displayed")
    public void iSeeAccountUnlockMessageDisplayed() {
        userPage.isGeneratedNotificationWhenAccountUnlock();
    }

    @Then("I see error message is displayed for {string}")
    public void iSeeErrorMessageDisplayed(String username) {
        var message = String.format(
                "Failed to create user account. Username: %s already exists. Use a different username", username);
        userPage.isGeneratedNotificationWhenCreateExistingUsername(message);
    }

    @Then("I should see user details are displayed")
    public void iSeeUserDetails() {
        Assert.assertEquals(userPage.getUserDetails(), user.getUserName());
    }

    @When("I click on filter icon and select status {string}")
    public void iSelectStatus(String status) {
        userPage.selectStatus(status);
    }

    @When("I verify filter tag")
    public void iVerifyFilterTag(String status) {
        Assert.assertEquals(status, userPage.getFilterTagText());
    }

    @When("I create a {string}")
    public void iCreateNewUsername() {
        userPage.createNewUser(this.user.getUserName());
    }

    @Then("I see expected texts from user module")
    public void iSeeExpectedTextsFromUserModule() {
        var expectedText = I18nUtils.getValueFromKey("user.header.title");
        userPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @Given("I go to userprofile")
    public void iGoToUserProfile() {
        userPage.userProfileIcon();
    }

    @And("I change the password")
    public void iChangeThePassword() {
        userPage.changePassword();
    }

    @And("I should see change password window popup")
    public void iSeeChangePasswordWindowPopup() {
        userPage.windowPopup();
    }

    @Given("^I verify the below Icons on left rail of Portal$")
    public void iVerifyTheApplicationModule(DataTable datatable) throws AWTException {
        List<String> icon = datatable.asList();
        userPage.zoomOut();
        for (String name : icon) {
            userPage.clickOnAppModule(name);
            switch (name) {
                case "Main":
                    loginPage.waitControlOnPnid();
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
                    alarmPage.checkAlarmHeader();
                    SelenideHelper.goToDefault();
                    break;
                case "Recipes":
                    SelenideHelper.goToIFrame();
                    recipePage.verifyRecipeHeader();
                    SelenideHelper.goToDefault();
                    break;
                case "Reports":
                    SelenideHelper.goToIFrame();
                    reportPage.verifyReportsHeader();
                    SelenideHelper.goToDefault();
                    break;
                case "Configuration":
                    SelenideHelper.goToIFrame();
                    configurationPage.verifyConfigurationHeader();
                    SelenideHelper.goToDefault();
                    break;
                case "Users":
                    userPage.checkAlarmHeader();
                    break;
                case "Backup":
                    backupPage.verifyBackupHeader();
                    break;
                case "Settings":
                    settingPage.verifySettingHeader();
                    break;
            }
        }
    }

    @Then("I see error message is displayed as {string}")
    public void iVerifyErrorNotification(String message) {
        userPage.errorNotification(message);
        userPage.closeChangeUserPropertiesChangeModal();
    }

    @Then("I see password updated message is displayed for {string}")
    public void iSeePasswordUpdatedMessageDisplayed(String username) {
        var message = String.format("User password for %s is updated", username);
        userPage.updateNotification(message);
        userPage.closeChangeUserPropertiesChangeModal();
        this.user.setUserName(username);
    }

    @Then("I should see {string} {string} under user profile icon")
    public void iVerifyUserProfile(String firstName, String lastName) {
        userPage.verifyUserProfileIcon(firstName, lastName);
        Selenide.sleep(2000);
        userPage.changePassword();
        userPage.closeChangeUserPropertiesChangeModal();
    }

    @When("I click on change password")
    public void iClickOnChangePassword() {
        userPage.changePassword();
    }

    @Then("I see error message is displayed {string}")
    public void iSeeErrorMessageIsDisplayed(String message) {
        userPage.isGeneratedNotificationWhenCreateExistingUsername(message);
    }

    @When("I verify default user account {string} and {string}")
    public void iVerifyDefaultUserAccount(String user1, String user2) {
        userPage.userAccountRole(user1, user2);
    }

    @When("I save my user modification changes")
    public void iSaveMyUserModificationChanges() {
        userPage.saveMyUserChanges();
    }

    @And("I verify create User icon {string}")
    public void createUserIconPresence(String value) {
        userPage.createUserIconPresent(value);
    }

    @And("I see {string} role assigned to user")
    public void verifyRoleAssigned(String role) {
        userPage.roleAssignedToUser(role);
    }

    @Given("I search {string} to validate role {string} assigned")
    public void roleAssignedToUser(String user, String role) {
        this.user.setUserName(user);
        userPage.setSearch(user);
        userPage.roleAssignedToUser(role);
    }

    @Then("I see the user added in report")
    public void iVerifyThatUserIsAdded() throws Exception {
        Map<String, String> list = new HashMap<>();
        list.put("department", user.getDeptName());
        list.put("phoneNumber", user.getMobNum());
        list.put("role", user.getRoleName());
        list.put("employeeID", user.getEmployeeId());
        list.put("email", user.getEmailId());
        list.put("lastName", user.getLastName());
        list.put("firstName", user.getFirstName());
        list.put("userName", user.getUserName());
        this.report.checkAddedUser(reportPage.getPdfUrl(), user.getUserName(), this.login.getLogin(), list);
        switchTo().parentFrame();
    }

    @Then("I see the {string} password events in report")
    public void iVerifyResetChangePasswordEvent(String passwordAction) throws Exception {
        this.report.verifyAuditReportForPasswordReset(reportPage.getPdfUrl(), user.getUserName(), this.login.getLogin(),
                passwordAction);
        switchTo().parentFrame();
    }

    @And("I verify {string} role is not present")
    public void verifyRoleIsNotPresent(String roleName){
        userPage.verifyRoleIsNotPresent(roleName);
    }

}
