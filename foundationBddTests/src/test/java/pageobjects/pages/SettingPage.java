package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Selenide.$;

public class SettingPage {
	private SelenideElement settingsPageLinkText = $(By.id("ConfigurationManagement"));
	private SelenideElement systemComponentText = $(By.xpath("//div[text()='System Components']"));
	private SelenideElement inputCustomLabel = $(By.id("customerShortDescription"));
	private SelenideElement applyButton = $(By.xpath("//button[contains(text(),'Apply')]"));
	private SelenideElement customLabelText = $(By.xpath("//input[@id='customerShortDescription']"));
	
	public void goToSettingsPage() {
		SelenideHelper.commonWaiter(settingsPageLinkText,Condition.visible).click();
	}

	public void goToSystemComponents() {
		systemComponentText.click();
	}
	
	public void changeSettings(String customLabelName) {
		$(By.id("customerShortDescription")).clear();
		inputCustomLabel.setValue(customLabelName);
		applyButton.click();
		customLabelText.waitUntil(Condition.visible,120001);
		String s = customLabelText.getValue();
		System.out.println(s);
		
	}
	
	public String getCustomLabelNameText() {
		return customLabelText.getValue();
	}
}
