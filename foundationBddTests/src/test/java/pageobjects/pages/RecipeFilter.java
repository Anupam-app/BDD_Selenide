package pageobjects.pages;

import com.codeborne.selenide.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class RecipeFilter {

	private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
	private SelenideElement upIcon = $(By.xpath("(//div[@class='up-icon']))[1])"));
	private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));
	private final SelenideElement recipeSearch = $(By.xpath("//input[@placeholder='Search...']"));
	private SelenideElement columnText = $(By.xpath("//td[1]"));
	
	public void searchRecipe(String recipeName) {
		SelenideHelper.commonWaiter(recipeSearch, visible).setValue(recipeName);
	}
	
	public String getRecipeName() {
		return columnText.getText();
	}
	
	public void selectRecipeStatus(String recipeStatus) {
		commonWaiter(filterIcon,visible);
    	filterIcon.click();
    	commonWaiter(upIcon,visible);
    	$(By.xpath(String.format("//span[text()='%s']", recipeStatus))).click();
    	applyFilterButton.click(); 
	}
}
