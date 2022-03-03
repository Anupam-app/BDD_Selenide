package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class RecipeFilterPage {
    private final String XPATH_RECIPE_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_RECIPE_TABLE = "//table[@id='recipeListTable']";
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";

    private final String XPATH_RECIPE_COLUMNS = "//table[@id='recipeListTable']//td";
    private final String XPATH_RECIPE_COLUMNS_BY_INDEX = XPATH_RECIPE_COLUMNS + "[%s]";
    private final String XPATH_RECIPE_COLUMNS_BY_TEXT = XPATH_RECIPE_COLUMNS + "[text()='%s']";

    private final String XPATH_RECIPE_OPTIONS_TEXT = "//option[@value='%s']/ancestor::li";

    private final SelenideElement recipeSearch = $(By.xpath("//input[@placeholder='Search...']"));
    private final SelenideElement clickOnDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private SelenideElement upIcon = $(By.xpath("(//div[@class='up-icon'])[1]"));
    private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));

    public SelenideElement getRecipeColumnHeader(String columnName) {
        return $(By.xpath(String.format(XPATH_RECIPE_COLUMN_HEADER, columnName)));
    }

    public List<String> getAllRecipeColumnHeaders() {
        return $$(By.xpath(XPATH_RECIPE_TABLE + "//th")).texts();
    }

    public List<String> getRecipeColumns(int index) {
        return $$(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_INDEX, index))).texts();
    }

    public void searchRecipe(String recipeName) {
        SelenideHelper.commonWaiter(recipeSearch, visible).setValue(recipeName);
        filterIcon.click();
    }

    public void selectRecipeStatus(String recipeStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        commonWaiter(upIcon, visible);
        $(By.xpath(String.format("//span[text()='%s']", recipeStatus))).click();
        applyFilterButton.click();
    }

    public void selectCreatedBy(String user) {
        SelenideHelper.commonWaiter(clickOnDropdown, visible).click();
        commonWaiter($(By.xpath(String.format(XPATH_RECIPE_OPTIONS_TEXT, user))), visible).click();
    }

    public void sortList(String columnName, boolean descending) {
        getRecipeColumnHeader(columnName).click();

        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order dropup")));

        if (!descendingIcon.isDisplayed() && descending) {
            getRecipeColumnHeader(columnName).click();
            descendingIcon.shouldBe(visible);
        }

        if (!ascendingIcon.isDisplayed() && !descending) {
            getRecipeColumnHeader(columnName).click();
            ascendingIcon.shouldBe(visible);
        }
    }

    public void checkSortedElement(String columnName, boolean descending) {

        var recipeColumnHeaders = getAllRecipeColumnHeaders();
        var index = recipeColumnHeaders.indexOf(columnName);

        List<String> displayedList = getRecipeColumns(index + 1);

        var expectedList = new ArrayList<>(displayedList);
        if (descending) {
            expectedList.sort(Collections.reverseOrder());
        } else {
            Collections.sort(expectedList);
        }

        Assert.assertEquals(expectedList, displayedList);
    }

    public void checkTableContainsRecipe(String recipeName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_TEXT, recipeName))), visible);
    }
}
