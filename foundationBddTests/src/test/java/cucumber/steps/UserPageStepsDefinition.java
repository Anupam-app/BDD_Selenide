package cucumber.steps;

import dataobjects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.UserPage;

public class UserPageStepsDefinition {

    private final UserPage userPage;
    private final User user;

    public UserPageStepsDefinition(UserPage userPage, User user) {
        this.userPage = userPage;
        this.user = user;
    }

    @Given("I search {string} user")
    public void iSearchUser(String user) {
        this.user.setUserName(user);
        userPage.setSearch(user);
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

    @When("I change the employee id with a random string")
    public void iChangeTheEmployeeIdWithARandomString() {
        user.setEmployeeId(RandomStringUtils.randomAlphabetic(10));
        userPage.setEmployeeId(user.getEmployeeId());
    }

    @When("I save my user changes")
    public void iSaveMyChanges() {
        userPage.saveMyChanges();
    }

    @When("I disable the user")
    public void iDissableTheUser() {
        userPage.disableUser();
    }

    @Then("the user is disabled")
    public void theUserIsDisabled() {
        Assert.assertTrue(userPage.isUserDisabled());
    }

    @Then("I enable the user")
    public void iEnableTheUser() {
        userPage.enableUser();
    }

    @Then("the user is enabled")
    public void theUserIsEnabled() {
        Assert.assertTrue(userPage.isUserEnabled());
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

    @Then("The username is equal to the expected one")
    public void theUserNameIsEqualToTheExpectedOne() {
        Assert.assertEquals(userPage.getUserNameFromForm(), user.getUserName());
    }

    @Then("the employee id is the expected one")
    public void theEmployeeIdIsEqualToTheExpectedOne() {
        Assert.assertEquals(userPage.getEmployeeIdFromForm(), user.getEmployeeId());
    }
    
    @Then("I see user details are changed")
    public void verifyUserDetails() {
    	Assert.assertEquals(userPage.getEmployeeIdFromForm(), user.getEmployeeId());
    	Assert.assertEquals(userPage.getEmailIdFromForm(), user.getEmailId());
    	Assert.assertEquals(userPage.getRoleNameFromForm(), user.getRoleName());
    	Assert.assertEquals(userPage.getMobNumFromForm(), user.getMobNum());
    	Assert.assertEquals(userPage.getDeptNameFromForm(), user.getDeptName());
    	
    }
    
    @When("I click on reset password")
    public void iClickOnResetPassword() {
    	userPage.resetPassword();
    }

    @Then("I see password reset message is displayed")
    public void iSeePasswordResetMessagedisplayed() {
    	user.setName(userPage.getGeneratedNotificationWhenPasswordReset());
    }


}
