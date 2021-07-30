package cucumber.steps;

import dataobjects.User;
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
        this.user.setUsername(user);
        userPage.setSearch(user);
    }

    @Given("I go to user page")
    public void iGoToUserPage() {
        userPage.goTo();
    }

    @When("I edit the user")
    public void iModifyUser() {
        userPage.edit(user.getUsername());
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
}
