package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

public class SettingPage {
	private SelenideElement settingsPageLinkText = $(By.id("ConfigurationManagement"));
	private SelenideElement systemComponentText = $(By.xpath("//div[text()='System Components']"));
	private SelenideElement inputCustomLabel = $(By.id("customerShortDescription"));
	private SelenideElement applyButton = $(By.xpath("//button[contains(text(),'Apply')]"));
	private SelenideElement customLabelText = $(By.xpath("//input[@id='customerShortDescription']"));
	private SelenideElement settingsHeader = $(By.xpath("//div[text()='Settings' and @class='setting-header-title']"));

	public void goToSettingsPage() {
		SelenideHelper.commonWaiter(settingsPageLinkText, Condition.visible).click();
	}

	public void goToSystemComponents() {
		Selenide.sleep(3000);
		systemComponentText.click();

	}

	public void changeSettings(String customLabelName) throws AWTException {
		zoomOut();
		$(By.id("customerShortDescription")).clear();
		inputCustomLabel.setValue(customLabelName);
		applyButton.click();
		customLabelText.waitUntil(Condition.visible, 120001);
		String s = customLabelText.getValue();
		System.out.println(s);

	}

	public String getCustomLabelNameText() {
		return customLabelText.getValue();
	}

	public void verifySettingHeader() {
		settingsHeader.shouldBe(visible);
	}

	private void zoomOut() throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < 3; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Selenide.sleep(2000);
		}
	}
}
