package pageobjects.pages;

import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import org.openqa.selenium.By;

import dataobjects.User;
import pageobjects.utility.SelenideHelper;

public class LoginPage {

    private final SelenideElement userIdTextBox = $(By.id("userId"));
    private final SelenideElement loginPageTitle = $(By.xpath("//span[@class='headermain__header--main']"));
    private final SelenideElement userPasswordTextBox = $(By.id("userPassword"));
    private final SelenideElement newPasswordTextBox = $(By.id("newPassword"));
    private final SelenideElement confirmPasswordTextBox = $(By.id("confirmPassword"));

    private final SelenideElement loginButton = $(By.xpath("//button[text()='LOGIN']"));
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));

    private final SelenideElement SUBMIT_LOGIN = $(SelenideHelper.byTestAttribute("submit_login"));
    private final SelenideElement pnidLoginInfo = $(SelenideHelper.byTestAttribute("pnid_login_info"));

    private final SelenideElement userLoginAlertText = $(By.xpath("//div[contains(@class,'alertDanger')]"));
    private final SelenideElement loadingIcon = $(By.xpath("//div[@class=\"loading-overlay\"]"));
    private final SelenideElement logOutButton = $(By.xpath("//button[text()='Log out']"));
    private final SelenideElement currentPasswordTextBox = $(By.xpath("//input[(@id='oldPassword')]"));
    private final SelenideElement savePassword = $(By.xpath("//button[@type='submit']/b[text()='Save Password']"));
    private final SelenideElement tempPwd_submitButton =
            $(By.xpath("//button[@type='submit' and contains(@class,'user_btn')]"));
    private final SelenideElement tempPwd_ErrorNotification =
            $(By.xpath("//div[@class='temporary-notification-bar error-bar']"));
    private final SelenideElement loginErrorNotificationText =
            $(By.xpath("//div[contains(@class,'alert-danger fade show')]"));
    private final SelenideElement unAppliedChanges = $(By.xpath("//h5[text()='Unapplied Changes']"));
    private final SelenideElement exitWithoutSaveButton = $(By.xpath("//span[text()='Exit without saving']"));
    private final SelenideElement userNotificationSection = $(By.xpath("//div[@id='userProfile']/parent::a"));
    private final SelenideElement prePostRunWindow = $(By.xpath("//p[contains(text(),'Run Record')]"));
    private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'Ok')]"));
    private final SelenideElement cancelButton = $(By.xpath("//button/b[contains(text(),'Cancel')]"));
    private final SelenideElement addEditUserDialog = $(By.xpath("//h5[@class='modal-title']"));
    private final SelenideElement cancelRoleButton = $(By.className("roleBtnCancel"));
    private final User user;

    public LoginPage(User user) {
        this.user = user;
    }

    public void setUser(String user) {
        userIdTextBox.setValue(user);
        this.user.setUserName(user);
    }

    public void pushLogin() {
        commonWaiter(SUBMIT_LOGIN, visible);
        SUBMIT_LOGIN.click();
    }

    public void verifyLoginPageTitle() {
        commonWaiter(loginPageTitle, visible)
                .shouldHave(text("Bio4C ACE™ Software for Inline Virus Inactivation System"));
    }

    public void setPassword(String password) {
        userPasswordTextBox.setValue(password);
    }

    public void openLogin() {
        waitPnidLoading();
        loginButton.waitUntil(enabled, 10000)
                .waitUntil(visible, 10000)
                .click();
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
        commonWaiter(pnidLoginInfo.$(byText(message)), visible);
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
        commonWaiter(newPasswordTextBox, visible);
        newPasswordTextBox.clear();
        newPasswordTextBox.setValue(newPassword);
    }

    public void setConfirmPassword(String newPassword) {
        confirmPasswordTextBox.clear();
        confirmPasswordTextBox.setValue(newPassword);
        tempPwd_submitButton.waitUntil(enabled, 10000)
                .click();
    }

    public void savePassword(String newPassword) {
        confirmPasswordTextBox.setValue(newPassword);
        savePassword.click();
    }

    public void iLogout() {
        if (addEditUserDialog.isDisplayed() && (addEditUserDialog.getText()
                .equalsIgnoreCase("Add User")
                || addEditUserDialog.getText()
                        .equalsIgnoreCase("Edit User"))) {
            cancelButton.click();
        } else if (cancelRoleButton.isDisplayed()) {
            cancelRoleButton.click();
        } else if (prePostRunWindow.isDisplayed()) {
            okButton.waitUntil(visible, 3000)
                    .click();
        }
        if (!newPasswordTextBox.isDisplayed()) {
            switchTo().parentFrame();
            if (userProfileIcon.isDisplayed()) {
                if (!userNotificationSection.getAttribute("aria-expanded")
                        .equalsIgnoreCase("true")) {
                    commonWaiter(userProfileIcon, visible).click();
                }
                commonWaiter(logOutButton, visible).click();
                if (unAppliedChanges.exists()) {
                    commonWaiter(exitWithoutSaveButton, visible).click();
                }
                waitPnidLoading();
            }
        }
    }

    public void setCurrentPassword(String newPassword) {
        currentPasswordTextBox.clear();
        currentPasswordTextBox.setValue(newPassword);
    }

    public void verifyNotification(String value) {
        commonWaiter(tempPwd_ErrorNotification, visible);
        tempPwd_ErrorNotification.shouldHave(text(value));
    }

    public void errorNotification(String message) {
        commonWaiter(loginErrorNotificationText, visible);
        loginErrorNotificationText.shouldHave(text(message));
    }

}
