package pageobjects.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserProfilePage {

	private final String XPATH_LANGUAGE_TEXT = "//*[@class='restore-custom-drop-down-container']//li[text()='%s']";
	private final String XPATH_LANGUAGE_ACTIVE_TEXT = "//*[@class='restore-custom-drop-down-container']//*[@class='active-label' and text()='%s']";

	private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));

	private final SelenideElement userPreferencesButton = $(By.className("btn-user-preferences"));

	private final SelenideElement userPreferenceLinkText = $(By.xpath("//div[@class='portal-user-preferences-menu']//span"));

	private SelenideElement languageDropDown = $$(By.className("restore-custom-drop-down-container")).get(1);

	public void checkUserProfilePresence(boolean loggedInd) {
		if (loggedInd) {
			userProfileIcon.should(be(visible));
		} else {
			userProfileIcon.shouldNot(be(visible));
		}
	}

	public void goToUserProfile() {
		userProfileIcon.click();
	}

	public void closeUserProfile() {
		userProfileIcon.click(ClickOptions.usingJavaScript());
	}

	public void goToUserPreferences() {
		userPreferenceLinkText.click();
	}

	public void changeDefaultLanguage(String languageName) {
		languageDropDown.click();
		$(By.xpath(String.format(XPATH_LANGUAGE_TEXT, languageName))).click();
	}

	public void saveUserPreferences() {
		userPreferencesButton.click();
	}

	public void seeSelectedLanguage() {
		SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, "Select Language"))), not(visible));
	}

	public void seeExpectedTextsOnUserProfile(String userPrefMenu) {
		var userPrefTextMenu = $(By.className(String.format("portal-user-preferences-menu")));
		userPrefTextMenu.should(Condition.text(userPrefMenu));
	}
}
