package cucumber.steps;

import java.text.ParseException;
import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.RecipePage;


public class RecipeFilterStepsDefinition {

    private RecipePage recipePage;

    public RecipeFilterStepsDefinition(RecipePage recipePage) {
        this.recipePage = recipePage;
    }
    
    @When("I search recipe {string}")
    public void iSearchRecipe(String recipeName) {
        recipePage.setSearch(recipeName);
    }

    @Then("I should see recipe {string}")
    public void iVerifyRecipe(String recipeName) {
        recipePage.checkTableContainsRecipe(recipeName);
    }

    @When("I click on filter icon and select recipe status {string}")
    public void iSelectRecipeStatus(String recipeStatus) {
        recipePage.selectRecipeStatus(recipeStatus);
    }

    @When("I select recipe created by {string}")
    public void iSelectRecipeCreatedBy(String user) {
        recipePage.selectCreatedBy(user);
    }

    @When("I select recipe sort by {string} in {string}")
    public void iSelectSortBy(String columnName, String sortMode) {
        recipePage.sortList(columnName,Boolean.parseBoolean(sortMode));
    }
    
    @When("I filter based on uop status as {string} and Imported as {string}")
	public void iFilterUOPAndImported(String status, String imported) {
		recipePage.selectRecipeStatusImported(status, imported);
	}
    
    @When("I see recipe based on uop status as {string} and Imported as {string}")
	public void iverifyFilteredRecipe(String status, String imported) {
		recipePage.verifyRecipeStatusImported(status, imported);
	}

    @Then("{string} from recipe should be displayed in sorted order {string}")
    public void recipeDetailsShouldBeDisplayedInSortedOrder(String columnName,String sortMode) {
        recipePage.checkSortedElement(columnName,Boolean.parseBoolean(sortMode));
    }
    
    @Then("^I should see recipe list displayed based on date range dropdown$")
	public void iviewrecipereportlistdaterange(DataTable datatable)
		throws ParseException, InterruptedException {
			List<String> options = datatable.asList();
			for (String datarange : options) {
				recipePage.selectDateRange(datarange);
				recipePage.verifyDateRanges(datarange);
			}
	}
}
