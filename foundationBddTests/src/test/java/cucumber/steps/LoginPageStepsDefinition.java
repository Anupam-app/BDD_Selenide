package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.HomePage;
import pageobjects.pages.LoginPage;

public class LoginPageStepsDefinition {

    private final LoginPage loginPage;
    private final HomePage homepage;

    public LoginPageStepsDefinition(LoginPage loginPage, HomePage homepage) {
        this.loginPage = loginPage;
        this.homepage = homepage;
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
    public void iLoginAsGivenUser(String user) {
        homepage.open();
        loginPage.openLogin();
        loginPage.setUser(user);
        loginPage.setPassword("MerckApp1@");
        loginPage.pushLogin();
    }
}
