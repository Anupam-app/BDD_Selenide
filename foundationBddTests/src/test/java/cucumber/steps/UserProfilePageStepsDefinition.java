package cucumber.steps;


import cucumber.util.I18nUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.UserProfilePage;

public class UserProfilePageStepsDefinition {

    private UserProfilePage userProfilePage;

    public UserProfilePageStepsDefinition(UserProfilePage userProfilePage) {
        this.userProfilePage = userProfilePage;
    }

    @Given("I go to user profile")
    public void iGoToUserProfile() {
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
}
