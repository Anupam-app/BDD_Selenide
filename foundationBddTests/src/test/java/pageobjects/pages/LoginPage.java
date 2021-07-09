package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends AbstractPageObject {

    private SelenideElement userId = $(By.id("userId"));
    private SelenideElement userPassword = $(By.id("userPassword"));
    private SelenideElement submit = $(By.xpath("//button[@type='submit']"));
    private SelenideElement loginText = $(By.xpath("//button[text()='LOGIN']"));
    private SelenideElement userProfile = $(By.xpath("//*[@id='userProfile']"));
    private SelenideElement userLoginModal = $(By.xpath("//*[@id='userLoginModal']"));

    public void setUser(String user) {
        userId.setValue(user);
    }

    public void setPassword(String password) {
        userPassword.setValue(password);
    }

    public void pushLogin() {
        submit.click();
    }

    public void openLogin() {
        loginText.click();
    }

    public void checkLoggedIn(boolean loggedInd) {
        SelenideElement element = userProfile;

        if (loggedInd) {
            element.should(be(visible));
        } else {
            element.shouldNot(be(visible));
        }
    }

    public void checkMessage(String message) {
        userLoginModal.shouldHave(text(message));
    }
}
