package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.pages.RecipeFilterPage;


public class RecipeFilterStepsDefinition {

    private RecipeFilterPage recipeFilterPage;

    public RecipeFilterStepsDefinition(RecipeFilterPage recipeFilterPage) {
        this.recipeFilterPage = recipeFilterPage;
    }
    
    @When("I search recipe {string}")
    public void iSearchRecipe(String recipeName) {
    	recipeFilterPage.searchRecipe(recipeName);
    }

    @Then("I should see recipe {string}")
    public void iVerifyRecipe(String recipeName) {
        recipeFilterPage.checkTableContainsRecipe(recipeName);
    }

    @When("I click on filter icon and select recipe status {string}")
    public void iSelectRecipeStatus(String recipeStatus) {
    	recipeFilterPage.selectRecipeStatus(recipeStatus);
    }

    @When("I select recipe created by {string}")
    public void iSelectRecipeCreatedBy(String user) {
    	recipeFilterPage.selectCreatedBy(user);
    }

    @When("I select recipe sort by {string} in {string}")
    public void iSelectSortBy(String columnName, String sortMode) {
        recipeFilterPage.sortList(columnName,Boolean.parseBoolean(sortMode));
    }

    @Then("{string} from recipe should be displayed in sorted order {string}")
    public void recipeDetailsShouldBeDisplayedInSortedOrder(String columnName,String sortMode) {
        recipeFilterPage.checkSortedElement(columnName,Boolean.parseBoolean(sortMode));
    }
}
