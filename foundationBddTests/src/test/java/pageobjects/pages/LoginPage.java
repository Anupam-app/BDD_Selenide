package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement userIdTextBox = $(By.id("userId"));
    private final SelenideElement userPasswordTextBox = $(By.id("userPassword"));
    private final SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));
    private final SelenideElement loginButton = $(By.xpath("//button[text()='LOGIN']"));
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement userLoginAlertText = $(By.className("alertDanger"));

    public void setUser(String user) {
        userIdTextBox.setValue(user);
    }

    public void setPassword(String password) {
        userPasswordTextBox.setValue(password);
    }

    public void pushLogin() {
        submitButton.click();
    }

    public void openLogin() {
        loginButton.click();
    }

    public void checkLoggedIn(boolean loggedInd) {
        SelenideElement element = userProfileIcon;

        if (loggedInd) {
            element.should(be(visible));
        } else {
            element.shouldNot(be(visible));
        }
    }

    public void checkMessage(String message) {
        userLoginAlertText.waitUntil(Condition.visible,5000l);
        userLoginAlertText.shouldHave(text(message));
    }
}
