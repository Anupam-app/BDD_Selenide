package pageobjects.pages;

import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import pageobjects.utility.SelenideHelper;

public class UserProfilePage {

    private final String XPATH_LANGUAGE_TEXT = "//*[@class='restore-custom-drop-down-container']//li[text()='%s']";
    private final String XPATH_PAGE_TEXT = "//*[@class='restore-custom-drop-down-container']//li[text()='%s']";
    private final String XPATH_LANGUAGE_ACTIVE_TEXT =
            "//*[@class='restore-custom-drop-down-container']//*[@class='active-label' and text()='%s']";
    private final SelenideElement pageTitle = $(By.xpath("//div[@class='navWrapper']//h2"));
    private final SelenideElement notificationMessage = $(By.xpath("//div[@class='notification-bar information-bar']"));


    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));

    private final SelenideElement userPreferencesButton = $(By.className("btn-user-preferences"));

    private final SelenideElement userPreferenceLinkText =
            $(By.xpath("//div[@class='portal-user-preferences-menu']//span"));

    private SelenideElement languageDropDown = $$(By.className("restore-custom-drop-down-container")).get(1);
    private SelenideElement pageDropDown = $$(By.className("restore-custom-drop-down-container")).get(0);

    public void checkUserProfilePresence(boolean loggedInd) {
        if (loggedInd) {
            userProfileIcon.waitUntil(visible, 5000);
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

    public void changeDefaultPage(String defaultPage) {
        pageDropDown.click();
        $(By.xpath(String.format(XPATH_PAGE_TEXT, defaultPage))).click();
    }

    public void saveUserPreferences() {
        userPreferencesButton.click();
        if ($(By.xpath("(//span[@class='active-label'])[2]")).getText()
                .equals("English (USA)")) {
            commonWaiter(notificationMessage, visible).getText()
                    .equals("Preferences saved");
        } else {
            commonWaiter(notificationMessage, visible).getText()
                    .equals("user.preference.save");
        }
    }

    public void seeContent(String expectedText) {
        SelenideHelper.fluentWaiter()
                .until((webdriver) -> {
                    return pageTitle.getText()
                            .equals(expectedText);
                });
    }

    public void seeSelectedLanguage() {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, "Select Language"))),
                not(visible));
    }

    public void seeDefaultPage() {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, "Select Language"))),
                not(visible));
    }

    public void seeExpectedTextsOnUserProfile(String userPrefMenu) {
        var userPrefTextMenu = $(By.className(String.format("portal-user-preferences-menu")));
        userPrefTextMenu.should(Condition.text(userPrefMenu));
    }
}
