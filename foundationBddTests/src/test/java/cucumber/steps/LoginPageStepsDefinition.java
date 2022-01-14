package cucumber.steps;

import dataobjects.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.HomePage;
import pageobjects.pages.LoginPage;
import org.junit.Assert;

public class LoginPageStepsDefinition {

    private final LoginPage loginPage;
    private final HomePage homepage;
    private final User user;

    public LoginPageStepsDefinition(LoginPage loginPage, HomePage homepage, User user) {
        this.loginPage = loginPage;
        this.homepage = homepage;
        this.user = user;
    }

    @Given("I open login page")
    public void iOpenLogin() {
        loginPage.openLogin();
    }

    @When("I enter {string} as username and {string} as password")
    public void iEnterUsernameAndPassword(String username,String password)
    {
        loginPage.setUser(username);
        loginPage.setPassword(password);
    }

    @When("I push the login button")
    public void iPushTheLoginButton()
    {
        loginPage.pushLogin();
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        loginPage.checkLoggedIn(true);
    }

    @Then("I am not logged in")
    public void iAmNotLoggedIn() {
        loginPage.checkLoggedIn(false);
    }

    @Then("I should see the message {string}")
    public void iShouldSeeThisMessage(String message) {
        loginPage.checkMessage(message);
    }

    @When("I am logged in as {string} user")
    public void iLoginAsGivenUser(String username) {
        homepage.open();
        loginPage.waitPnidLoading();
        loginPage.openLogin();
        user.setUserName(username);
        user.setPassword("MerckApp1@");
        loginPage.setUser(user.getUserName());
        loginPage.setPassword(user.getPassword());
        loginPage.pushLogin();
        loginPage.waitControlOnPnid();
    }

    @Given("I change password {string}")
    public void iChangePassword(String password) {
        loginPage.setNewpassword(password);
        loginPage.setConfirmpassword(password);
    }
    
    
    @Given("I click on user profile icon")
    public void iClickOnUserProfileIcon() {
    	loginPage.clickOnUserProfile();
    }
    
    @When("I click on user preferences link")
    public void iClickOnUserPreferencesLink() {
    	loginPage.clickOnUserPreferences();
    }
    
    @When("I select {string} option from drop down")
    public void iSelectFromDropDown(String defaultPageName) {
    	loginPage.selectDropDown(defaultPageName);
    }
    
    @When("I relogin")
    public void ilogout_and_relogin() {
    	iEnterUsernameAndPassword("Bio4CAdmin","MerckApp1@");
    }
    
    @Then("I should see home page displayed with option")
    public void iSeeHomepageWithDefaultOption() {
    	Assert.assertEquals(loginPage.getDefaultPageOption(), user.getDefaultPage());
    }
}

