package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.pages.UserPage;

public class UserFilterStepsDefinition {

    private final UserPage userPage;

    public UserFilterStepsDefinition(UserPage userPage) {
        this.userPage = userPage;
    }

    @When("I click on user profile icon")
    public void iClickOnUserProfileIcon() {
        userPage.userProfile();
    }

    @When("I click on user preferences link")
    public void iClickOnUserPreferencesLink() {
        userPage.userPreferences();
    }

    @When("I select default page {string}")
    public void iselectOption(String optionName) {
        userPage.chooseAndSaveDefaultPage(optionName);
    }

    @Then("I should see home page displayed with option {string}")
    public void iVerifytOption(String defaultOptionName) {
        Assert.assertEquals(defaultOptionName, userPage.getActiveIconTitle());
    }

    @Then("I should see the status {string} and user {string} displayed")
    public void iSeeUserDisplayed(String status, String userName) {
        Assert.assertEquals(status, userPage.getFilterTagText());
        Assert.assertEquals(userName, userPage.getUserName());
    }

    @When("I select user sort by {string} in {string}")
    public void iSelectSortBy(String columnName, String sortMode) {
        userPage.sortList(columnName, Boolean.parseBoolean(sortMode));
    }

    @Then("{string} from user should be displayed in sorted order {string}")
    public void userDetailsShouldBeDisplayedInSortedOrder(String columnName, String sortMode) {
        userPage.checkSortedElement(columnName, Boolean.parseBoolean(sortMode));
    }
}
