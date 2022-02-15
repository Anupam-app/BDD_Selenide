package cucumber.steps;

import dataobjects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.UserFilter;

public class UserFilterStepsDefinition {

    private final UserFilter userFilter;
    private final User user;

    public UserFilterStepsDefinition(UserFilter userFilter, User user) {
        this.userFilter = userFilter;
        this.user = user;
    }
    
	@Given("I click on user profile icon")
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
	}
	
	@When("I click on filter icon and select status {string}")
	public void iSelectUserStatus(String status) {
		userFilter.selectUserStatus(status);
	}
	
	@Then("I should see the status {string} and user {string} displayed")
	public void iSeeUserDisplayed(String status, String userName) {
		Assert.assertEquals(status, userFilter.getFilterTagText());
		Assert.assertEquals(userName, userFilter.getUserName());
	}
	
	@When("I select sort by {string} in {string}")
	public void iSelectSortBy(String columnName, String sortMode) {
		userFilter.sortList(columnName,sortMode);
	}
	
	@Then("Details should be displayed in sort order")
	public void user_details_should_be_displayed_by_in_order(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
