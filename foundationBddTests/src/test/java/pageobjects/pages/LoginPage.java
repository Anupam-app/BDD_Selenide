package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects.utility.SelenideHelper.byTestAttribute;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import static com.codeborne.selenide.Selenide.switchTo;

public class LoginPage {

    private final SelenideElement userIdTextBox = $(By.id("userId"));
    private final SelenideElement userPasswordTextBox = $(By.id("userPassword"));
    private final SelenideElement newPasswordTextbox = $(By.id("newPassword"));
    private final SelenideElement confirmPasswordTextbox = $(By.id("confirmPassword"));

    private final SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));
    private final SelenideElement loginButton = $(By.xpath("//div[@class='loginButton']//button"));
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement userLoginAlertText = $(By.className("alertDanger"));
    private final SelenideElement loadingIcon = $(By.xpath("//div[@class=\"loading-overlay\"]"));
    private final String pnidLoginTestId = "pnid_login_info";
    private SelenideElement logOutButton = $(By.xpath("//button[text()='Log out']"));
    
    
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
        commonWaiter(loginButton,visible).click();
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
        commonWaiter(userLoginAlertText,visible);
        userLoginAlertText.shouldHave(text(message));
    }

    public void waitControlOnPnid() {
        waitPnidMessage("You are controlling main screen");
    }

    public void waitPnidLoading() {
        waitPnidMessage("Main screen is view only");
    }

    public void waitPnidMessage(String message) {
        commonWaiter($(byTestAttribute(pnidLoginTestId)).$(byText(message)), visible);
        commonWaiter(loadingIcon, not(visible));
    }

    public void setNewpassword(String newpassword) {
        commonWaiter(newPasswordTextbox,visible);
        newPasswordTextbox.setValue(newpassword);
    }

    public void setConfirmpassword(String newpassword) {
        confirmPasswordTextbox.setValue(newpassword);
        submitButton.click();
    }

    public void iLogout() {
        SelenideHelper.commonWaiter(logOutButton, visible).click();
    }
}
