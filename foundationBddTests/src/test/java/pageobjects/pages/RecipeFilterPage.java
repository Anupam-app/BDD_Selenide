package pageobjects.pages;

import com.codeborne.selenide.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class RecipeFilterPage {
	private final String XPATH_RECIPE_COLUMN_HEADER = "//th[text()='%s']";
	private final String XPATH_RECIPE_TABLE = "//table[@id='recipeListTable']";
	private final String XPATH_RECIPE_COLUMNS = "//table[@id='recipeListTable']//td[%s]";

	private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
	private SelenideElement upIcon = $(By.xpath("(//div[@class='up-icon'])[1]"));
	private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));
	private final SelenideElement recipeSearch = $(By.xpath("//input[@placeholder='Search...']"));
	private final SelenideElement clickOnDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
	private SelenideElement columnText = $(By.xpath("//td[1]"));//TODO to be reviewed

	public SelenideElement getRecipeColumnHeader(String columnName) {
		return $(By.xpath(String.format(XPATH_RECIPE_COLUMN_HEADER, columnName)));
	}

	public List<String> getAllRecipeColumnHeaders() {
		return $$(By.xpath(XPATH_RECIPE_TABLE+"//th")).texts();
	}

	public List<String> getRecipeColumns(int index) {
		return $$(By.xpath(String.format(XPATH_RECIPE_COLUMNS, index))).texts();
	}
	
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

    public void selectCreatedBy(String adminName) {
        SelenideHelper.commonWaiter(clickOnDropdown, visible).click();
        commonWaiter($(By.xpath(String.format("//option[text()='%s']", adminName))),visible).click();
    }
    
	public void sortList(String columnName, boolean ascending) {
		//descending by default
		if (ascending) {
			getRecipeColumnHeader(columnName).click();
		}
	}

	public void checkSortedElement(String columnName, boolean ascending) {

		var recipeColumnHeaders= getAllRecipeColumnHeaders();
		var index = recipeColumnHeaders.indexOf(columnName);

		List<String> displayedList = getRecipeColumns(index+1);

		var expectedList = new ArrayList<>(displayedList);
		if (ascending) {
			Collections.sort(expectedList);
		} else {
			expectedList.sort(Collections.reverseOrder());
		}

		Assert.assertEquals(expectedList, displayedList);
	}
}
