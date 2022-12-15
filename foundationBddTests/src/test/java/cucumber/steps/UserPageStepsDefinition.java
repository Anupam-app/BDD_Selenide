package cucumber.steps;

import static com.codeborne.selenide.Selenide.switchTo;
import cucumber.util.I18nUtils;
import dataobjects.Login;
import dataobjects.Report;
import dataobjects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.ReportsPage;
import pageobjects.pages.UserPage;
import pageobjects.utility.SelenideHelper;

public class UserPageStepsDefinition {

    private final UserPage userPage;
    private final User user;
    private final Report report;
    private final ReportsPage reportPage;
    private final Login login;

    public UserPageStepsDefinition(ReportsPage reportPage, UserPage userPage, Report report, User user, Login login) {
        this.userPage = userPage;
        this.user = user;
        this.report = report;
        this.reportPage = reportPage;
        this.login = login;
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
        userPage.waitForUserCreationNotification(user.getUserName());
        userPage.isGeneratedNotificationWhenUserModified(user.getUserName());
    }

    @When("I save the new user")
    public void iSaveNewUser() {
        userPage.saveMyChanges();
    }

    @When("I disable the user")
    public void iDissableTheUser() {
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
        Map<String, String> list = new HashMap<String, String>();
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
    public void iSeePasswordResetMessagedisplayed() {
        userPage.isGeneratedNotificationWhenPasswordReset(this.user.getUserName());
    }

    @Then("I see account unlock message is displayed")
    public void iSeeAccountUnlockMessagedisplayed() {
        userPage.isGeneratedNotificationWhenAccountUnlock();
    }

    @Then("I see error message is displayed for {string}")
    public void iSeeErrorMessagedisplayed(String username) {
        var message = String.format("Failed to create user account. Username: %s already exists. Use a different username", username);
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

    @When("I verify filetr tag")
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
}
