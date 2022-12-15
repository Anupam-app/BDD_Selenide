package cucumber.steps;

import io.cucumber.java.en.Given;
import pageobjects.pages.HomePage;

public class HomePageStepsDefinition {

    private HomePage homePage;

    public HomePageStepsDefinition(HomePage homePage) {
        this.homePage = homePage;
    }

    @Given("I open portal")
    public void iOpenPortal() {
        homePage.open();
    }

    @Given("I go to main")
    public void iGoToMain() {
        homePage.goToMain();
    }
}
