package cucumber.steps;


import com.codeborne.selenide.Selenide;

import cucumber.util.I18nUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.UserProfilePage;
import pageobjects.utility.SelenideHelper;

public class UserProfilePageStepsDefinition {

	private UserProfilePage userProfilePage;

	public UserProfilePageStepsDefinition(UserProfilePage userProfilePage) {
		this.userProfilePage = userProfilePage;
	}

	@Given("I go to user profile")
	public void iGoToUserProfile() {
		SelenideHelper.goParentFrame();
		userProfilePage.goToUserProfile();
	}

	@Given("I go to user preferences")
	public void iGoToUserPreferences() {
		userProfilePage.goToUserPreferences();
	}

	@When("I change default language to {string}")
	public void iGoToUserPreferences(String language) {
		userProfilePage.seeSelectedLanguage();
		I18nUtils.changeLanguage(language);
		var languageName = I18nUtils.getLanguageName();
		userProfilePage.changeDefaultLanguage(languageName);
	}
	
	@When("I change default page to {string}")
	public void iChooseDefaultPage(String defaultPage) {
		userProfilePage.changeDefaultPage(defaultPage);
	}
	
	@When("I reset to {string} page")
	public void iResetToMainPage(String defaultPage) {
		SelenideHelper.goParentFrame();
		userProfilePage.goToUserProfile();
		userProfilePage.goToUserPreferences();
		userProfilePage.changeDefaultPage(defaultPage);
		userProfilePage.saveUserPreferences();
	}
	
	@Then("I am landed on {string} page")
	public void iAmLandedOnReportModule(String page) {
		SelenideHelper.goToIFrame();
		userProfilePage.seeContent(page);
		SelenideHelper.goParentFrame();
	}

	@When("I save user preferences")
	public void iSaveUserPreferences() {
		userProfilePage.saveUserPreferences();
	}

	@Then("I see expected texts in user profile")
	public void iSeeExpectedTextsOnScreenKeyBoard() {
		userProfilePage.goToUserProfile();
		var showKeyboardText = I18nUtils.getValueFromKey("portal.modal.list.userPreferences");
		userProfilePage.seeExpectedTextsOnUserProfile(showKeyboardText);
		userProfilePage.closeUserProfile();
	}

	@Then("I reset my language to {string}")
	public void iResetMyLanguageTo(String language) {
		iGoToUserProfile();
		iGoToUserPreferences();
		iGoToUserPreferences(language);
		iSaveUserPreferences();
	}
}
