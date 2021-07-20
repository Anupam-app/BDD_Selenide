package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.LoginPage;

import java.util.Map;

public class LoginPageStepsDefinition {

    private final LoginPage loginPage;

    public LoginPageStepsDefinition(LoginPage loginPage) {
        this.loginPage = loginPage;

    }

    @Given("I open login")
    public void iOpenLogin() {
        loginPage.openLogin();
    }

    @When("I input username with {string}")
    public void iInputUsername(String string) {
        loginPage.setUser(string);
    }

    @When("I input password with {string}")
    public void iInputPassword(String string) {
        loginPage.setPassword(string);
    }

    @When("I input credentials:")
    public void iInputCredentials(Map<String,String> credentials) {
        for (Map.Entry<String, String> entry : credentials.entrySet()) {
            loginPage.setUser(entry.getKey());
            loginPage.setPassword(entry.getValue());
        }
    }

    @When("I push login")
    public void iPushLogin() {
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

    @Then("I should see this message {string}")
    public void IshouldSeeThisMessage(String message) {
        loginPage.checkMessage(message);
    }

    @When("I login as {string} user")
    public void iLoginAsGivenUser(String user) {
        loginPage.openLogin();
        loginPage.setUser(user);
        loginPage.setPassword("MerckApp1@");
        loginPage.pushLogin();
    }
}
