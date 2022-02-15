package cucumber.steps;

import dataobjects.Recipe;
import dataobjects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.RecipeFilter;
import static pageobjects.utility.SelenideHelper.goToIFrame;


public class RecipeFilterStepsDefinition {

    private RecipeFilter recipeFilter;
    private Recipe recipe;
    private User user;

    public RecipeFilterStepsDefinition(RecipeFilter recipeFilter, Recipe recipe,User user) {
        this.recipeFilter = recipeFilter;
        this.recipe = recipe;
        this.user = user;
    }
    
    @When("I search recipe {string}")
    public void iSearchRecipe(String recipeName) {
    	recipeFilter.searchRecipe(recipeName);
    }

    @Then("I should see recipe {string}")
    public void iVerifyRecipe(String recipeName) {
    	Assert.assertEquals(recipeName, recipeFilter.getRecipeName());
    }

    @When("I click on filter icon and select recipe status {string}")
    public void iSelectRecipeStatus(String recipeStatus) {
    	recipeFilter.selectRecipeStatus(recipeStatus);
    }

    @When("I select from dropdown list {string}")
    public void iSelectFromDropdowmList(String recipeStatus) {
    	recipeFilter.selectRecipeStatus(recipeStatus);
    }
}
