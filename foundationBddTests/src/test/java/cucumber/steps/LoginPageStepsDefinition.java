package cucumber.steps;

import dataobjects.Login;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import pageobjects.pages.HomePage;
import pageobjects.pages.LoginPage;
import pageobjects.pages.RecipeConsolePage;
import pageobjects.pages.UserPage;
import pageobjects.pages.UserProfilePage;
import pageobjects.utility.ContextHelper;

public class LoginPageStepsDefinition {

    private final LoginPage loginPage;
    private final HomePage homepage;
    private final RecipeConsolePage recipeConsolePage;
    private final UserProfilePage userProfilePage;
    private final Login login;
    private final UserPage userPage;


    public LoginPageStepsDefinition(LoginPage loginPage, HomePage homepage, RecipeConsolePage recipeConsolePage, Login login,
                                    UserProfilePage userProfilePage, UserPage userPage) {
        this.loginPage = loginPage;
        this.homepage = homepage;
        this.recipeConsolePage = recipeConsolePage;
        this.login = login;
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
        if (ContextHelper.isOrchestrator()) {
            homepage.open();
            login.setLogin(username);
            login.setPassword("MerckApp1@");
            loginPage.setUser(login.getLogin());
            loginPage.setPassword(login.getPassword());
            loginPage.pushLogin();
        } else {
            homepage.open();
            loginPage.waitPnidLoading();
            loginPage.openLogin();
            login.setLogin(username);
            login.setPassword("MerckApp1@");
            loginPage.setUser(login.getLogin());
            loginPage.setPassword(login.getPassword());
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
        iEnterUsernameAndPassword(login.getLogin(), login.getPassword());
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

    @When("I try to change password {string} {string} {string}")
    public void iTryToChangePassword(String currentPassword, String newPassword, String confirmPassword) {
        userPage.userProfileIcon();
        userPage.changePassword();
        loginPage.setCurrentpassword(currentPassword);
        loginPage.setNewpassword(newPassword);
        loginPage.setConfirmpassword(confirmPassword);
    }

    @And("I logout and login as {string} and password as {string}")
    public void logourAndLogin(String username, String password)
    {
        loginPage.iLogout();
        loginPage.openLogin();
        loginPage.setUser(username);
        loginPage.setPassword(password);
        loginPage.pushLogin();
    }
}
