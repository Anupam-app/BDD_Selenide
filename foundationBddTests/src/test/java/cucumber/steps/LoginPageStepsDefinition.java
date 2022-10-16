package cucumber.steps;

import java.util.List;

import dataobjects.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Objects;
import pageobjects.pages.HomePage;
import pageobjects.pages.LoginPage;
import pageobjects.pages.UserProfilePage;
import pageobjects.pages.RecipeConsolePage;
import pageobjects.pages.UserPage;
import pageobjects.utility.ContextHelper;

public class LoginPageStepsDefinition {

    private final LoginPage loginPage;
    private final HomePage homepage;
    private final RecipeConsolePage recipeConsolePage;
    private final User user;
    private final UserProfilePage userProfilePage;
    private final UserPage userPage;

    public LoginPageStepsDefinition(LoginPage loginPage, HomePage homepage, RecipeConsolePage recipeConsolePage, User user, UserProfilePage userProfilePage, UserPage userPage) {
        this.loginPage = loginPage;
        this.homepage = homepage;
        this.recipeConsolePage = recipeConsolePage;
        this.user = user;
		this.userProfilePage = userProfilePage;
        this.userPage = userPage;
    }

    @Given("I open login page")
    public void iOpenLogin() {
        if (!ContextHelper.isOrchestrator()) {
            loginPage.openLogin();
        }
    }

    @When("I enter {string} as username and {string} as password")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginPage.setUser(username);
        loginPage.setPassword(password);
    }

    @When("I push the login button")
    public void iPushTheLoginButton() {
        loginPage.pushLogin();
        
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        userProfilePage.checkUserProfilePresence(true);
    }

    @Then("I am not logged in")
    public void iAmNotLoggedIn() {
        userProfilePage.checkUserProfilePresence(false);
    }

    @Then("I should see the message {string}")
    public void iShouldSeeThisMessage(String message) {
        loginPage.checkMessage(message);
    }


    @When("^I login to application with wrong password$")
    public void iShouldSeeLoginMessage(DataTable table) {
        List<List<String>> list = table.asLists(String.class);

        for (int i = 1; i < list.size(); i++) {
            loginPage.setUser(list.get(i).get(0));
            loginPage.setPassword(list.get(i).get(1));
        	loginPage.pushLogin();
        	loginPage.checkLoggedIn(false);
        	loginPage.checkMessage(list.get(i).get(2));
        }
    }


    @When("I am logged in as {string} user")
    public void iLoginAsGivenUser(String username) {
        //TODO to be refactored?
        if (ContextHelper.isOrchestrator()) {
            homepage.open();
            user.setUserName(username);
            user.setPassword("MerckApp1@");
            loginPage.setUser(user.getUserName());
            loginPage.setPassword(user.getPassword());
            loginPage.pushLogin();
        } else {
            homepage.open();
            loginPage.waitPnidLoading();
            loginPage.openLogin();
            user.setUserName(username);
            user.setPassword("MerckApp1@");
            loginPage.setUser(user.getUserName());
            loginPage.setPassword(user.getPassword());
            loginPage.pushLogin();
            loginPage.waitControlOnPnid();
            recipeConsolePage.cleanLastRecipeDisplay();
        }
    }

    @Given("I change password {string}")
    public void iChangePassword(String password) {
        loginPage.setNewpassword(password);
        loginPage.setConfirmpassword(password);
    }

    @Then("I relogin")
    public void iReLogin() {
        iOpenLogin();
        iEnterUsernameAndPassword(user.getUserName(), user.getPassword());
        iPushTheLoginButton();
    }

    @Then("I logout")
    public void iLogout() {
        loginPage.iLogout();
    }

    @Then("I see the error message {string}")
    public void iSeetheErrorMessage(String message) {
        loginPage.checkMessage(message);
    }
}
