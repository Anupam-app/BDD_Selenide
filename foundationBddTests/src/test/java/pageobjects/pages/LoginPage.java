package pageobjects.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class LoginPage {

    private final SelenideElement userIdTextBox = $(By.id("userId"));
    private final SelenideElement loginPageTitle = $(By.xpath("//span[@class='headermain__header--main']"));
    private final SelenideElement userPasswordTextBox = $(By.id("userPassword"));
    private final SelenideElement newPasswordTextbox = $(By.id("newPassword"));
    private final SelenideElement confirmPasswordTextbox = $(By.id("confirmPassword"));

    private final SelenideElement loginButton = $(By.xpath("//div[@class='loginButton']//button"));
    private final SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));

    private final SelenideElement SUBMIT_LOGIN = $(SelenideHelper.byTestAttribute("submit_login"));
    private final SelenideElement PNID_LOGIN_INFO = $(SelenideHelper.byTestAttribute("pnid_login_info"));

	private final SelenideElement userLoginAlertText = $(By.className("alertDanger"));
	private final SelenideElement loadingIcon = $(By.xpath("//div[@class=\"loading-overlay\"]"));
	private SelenideElement logOutButton = $(By.xpath("//button[text()='Log out']"));
    private final String pnidLoginTestId = "pnid_login_info";
    private SelenideElement licenseText = $(By.xpath("//h5[text()='License about to Expire']"));
    private final SelenideElement currentPasswordTestbox = $(By.xpath("//input[(@id='oldPassword')]"));
    private final SelenideElement savePassword = $(By.xpath("//button[@type='submit']/b[text()='Save Password']"));
    private final SelenideElement tempPwd_submitButton = $(By.xpath("//button[@type='submit' and @class='user_btn btn_primary']"));
    private final SelenideElement tempPwd_ErrorNotification = $(By.xpath("//div[@class='temporary-notification-bar error-bar'"));

    public void setUser(String user) {
        userIdTextBox.setValue(user);
    }

    public void pushLogin() {
        commonWaiter(SUBMIT_LOGIN, visible);
        SUBMIT_LOGIN.click();
    }

    public void verifyLoginPageTitle() {
        commonWaiter(loginPageTitle, visible).shouldHave(text("Bio4C ACE™ Software for Inline Virus Inactivation System"));
    }

    public void setPassword(String password) {
        userPasswordTextBox.setValue(password);
    }

    public void openLogin() {
        commonWaiter(loginButton, visible).click();
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
        commonWaiter(userLoginAlertText, visible);
        userLoginAlertText.shouldHave(text(message));
    }

    public void waitPnidMessage(String message) {
        commonWaiter(PNID_LOGIN_INFO.$(byText(message)), visible);
        commonWaiter(loadingIcon, not(visible));
    }

    public void waitControlOnPnid() {
        var message = Neodymium.localizedText("portal.pnid.info.screen_controlling");
        waitPnidMessage(message);
    }

    public void waitPnidLoading() {
        waitPnidMessage("Main screen is view only");
    }

    public void setNewPassword(String newPassword) {
        commonWaiter(newPasswordTextbox, visible);
        newPasswordTextbox.clear();
        newPasswordTextbox.setValue(newPassword);
    }

    public void setConfirmPassword(String newPassword) {
        confirmPasswordTextbox.clear();
        confirmPasswordTextbox.setValue(newPassword);
        tempPwd_submitButton.click();
    }

    public void savePassword(String newPassword) {
        confirmPasswordTextbox.setValue(newPassword);
        savePassword.click();
    }

    public void iLogout() {
        SelenideHelper.commonWaiter(userProfileIcon, visible).click();
        SelenideHelper.commonWaiter(logOutButton, visible).click();
    }

    public void setCurrentPassword(String newPassword) {
        currentPasswordTestbox.clear();
        currentPasswordTestbox.setValue(newPassword);
    }

    public void verifyNotification(String value){
        commonWaiter(tempPwd_ErrorNotification,visible);
        Assert.assertEquals("Error text message is not as expected","value",tempPwd_ErrorNotification.getText());
    }
}
