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
    public void i_open_login() {
        loginPage.openLogin();
    }


    @When("I input username with {string}")
    public void i_input_username_with(String string) {
        loginPage.setUser(string);
    }

    @When("I input password with {string}")
    public void i_input_password_with(String string) {
        loginPage.setPassword(string);
    }

    @When("I input credentials:")
    public void i_input_password_with(Map<String,String> credentials) {
        for (Map.Entry<String, String> entry : credentials.entrySet()) {
            loginPage.setUser(entry.getKey());
            loginPage.setPassword(entry.getValue());
        }
    }

    @When("I push login")
    public void i_input_password_with() {
        loginPage.pushLogin();
    }

    @Then("I am logged in")
    public void i_am_logged_in() {
        loginPage.checkLoggedIn(true);
    }

    @Then("I am not logged in")
    public void i_am_not_logged_in() {
        loginPage.checkLoggedIn(false);
    }

    @Then("I should see this message {string}")
    public void i_should_see_this_message(String message) {
        loginPage.checkMessage(message);
    }

    @Given("I login with good credentials")
    public void i_login_with_good_credentials() {
        loginPage.openLogin();
        loginPage.setUser("bio4cservice");
        loginPage.setPassword("MerckApp1@");
        loginPage.pushLogin();
    }
}
