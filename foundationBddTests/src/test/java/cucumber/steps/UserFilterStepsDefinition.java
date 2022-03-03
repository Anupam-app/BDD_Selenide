package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.pages.UserFilterPage;

public class UserFilterStepsDefinition {

    private final UserFilterPage userFilter;

    public UserFilterStepsDefinition(UserFilterPage userFilter) {
        this.userFilter = userFilter;
    }
    
	@When("I click on user profile icon")
	public void iClickOnUserProfileIcon() {
		userFilter.userProfile();
	}
	
	@When("I click on user preferences link")
	public void iClickOnUserPreferencesLink() {
		userFilter.userPreferences();
	}
	
	@When("I select {string} option from drop down")
	public void iselectOption(String defaultOptionName) {
		userFilter.defaultOption(defaultOptionName);
	}

	@Then("I should see home page displayed with option {string}")
	public void iVerifytOption(String defaultOptionName) {
		Assert.assertEquals(defaultOptionName, userFilter.getActiveIconTitle());
		userFilter.setToDefault();
	}
	
	@Then("I should see the status {string} and user {string} displayed")
	public void iSeeUserDisplayed(String status, String userName) {
		Assert.assertEquals(status, userFilter.getFilterTagText());
		Assert.assertEquals(userName, userFilter.getUserName());
	}
	
    @When("I select user sort by {string} in {string}")
    public void iSelectSortBy(String columnName, String sortMode) {
        userFilter.sortList(columnName,Boolean.parseBoolean(sortMode));
    }

    @Then("{string} from user should be displayed in sorted order {string}")
    public void userDetailsShouldBeDisplayedInSortedOrder(String columnName,String sortMode) {
        userFilter.checkSortedElement(columnName,Boolean.parseBoolean(sortMode));
    }

}
