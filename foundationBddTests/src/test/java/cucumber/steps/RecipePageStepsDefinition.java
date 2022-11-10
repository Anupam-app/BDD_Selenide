package cucumber.steps;

import dataobjects.Login;
import dataobjects.Recipe;
import dataobjects.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.RecipePage;
import pageobjects.pages.UserPage;

import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.goToIFrame;

import java.util.List;

public class RecipePageStepsDefinition {

    private RecipePage recipePage;
    private final UserPage userPage;
    private Recipe recipe;
    private Login login;

    public RecipePageStepsDefinition(RecipePage recipePage, UserPage userPage, Recipe recipe,Login login) {
        this.recipePage = recipePage;
        this.userPage = userPage;
        this.recipe = recipe;
        this.login = login;
    }

    @Given("I go to recipe page")
    public void iGoToRecipePage() {
        recipePage.goTo();
        goToIFrame();
    }

    @Then("I search the recipe")
    public void iSearchRecipe() {
        recipePage.setSearch(this.recipe.getRecipeName());
    }

    @And("I edit the recipe")
    public void iEditTheRecipe() {
        recipePage.edit(this.recipe.getRecipeName());
    }

    @And("I delete all phases")
    public void iDeleteAllPhases() {
        recipePage.deleteAllPhases();
    }

    @When("I trigger edit mode")
    public void iGoToEditMode() {
        recipePage.goToEditMode();
    }

    @Then("I go to browser mode")
    public void iGoToBrowserMode() {
        recipePage.goToBrowserMode();
    }

    @When("I create a random phase")
    public void iCreateARandomPhase() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhase(this.recipe.getPhaseName());
    }
    
    @When("I go to other module without saving recipe")
    public void igoToOtherModule() {
    	switchTo().parentFrame();
    	userPage.goTo();
    }
    
    @When("I come back to Recipe page")
    public void iGoToRecipeAndEdit() {
    	recipePage.goTo();
        goToIFrame();
        recipePage.goToEditMode();
    }
    
    @When("I can create a recipe")
    public void iCreateRecipe() {
    	this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhase(this.recipe.getPhaseName());
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(10));
        recipePage.saveRecipe(this.recipe.getRecipeName());
        
    }
    
    @When("I choose {string} from file menu")
    public void iChooseOption(String option) {
    	recipePage.chooseOption(option);
    }

    @And("I save the recipe")
    public void iSaveTheRecipe() {
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(10));
        recipePage.saveRecipe(this.recipe.getRecipeName());
    }
    
    @And("I save the recipe with name {string}")
    public void iSaveTheRecipeExistingName(String recipeName) {
        this.recipe.setRecipeName(recipeName);
        recipePage.saveRecipe(this.recipe.getRecipeName());
    }
    
    @Then("I see warning message is displayed {string}")
    public void iSeeWarningMessagedisplayed(String message) {
    	recipePage.isGeneratedNotificationWhenCreateExistingRecipe(message);
    }

    @And("I see my changes in recipe")
    public void iSeeMyChangesInRecipe() {
        Assert.assertEquals(this.recipePage.getPhaseName(), this.recipe.getPhaseName());
    }

    @Given("I goto to editor tab in recipe page")
    public void iNevigateToEditorTab() {
        recipePage.naviagateToEditorTab();
    }

    @When("I create a recipe {string} with phase")
    public void iCreateARecipe(String recipe) {
        this.recipe.setRecipeNode(recipe);
        recipePage.createRecipe(recipe);
    }

    @And("I save the recipe as {string}")
    public void iSaveRecipeAs(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.saveRecipe(recipe);
    }

    @Then("I save the modified recipe")
    public void iSaveModifiedRecipeAs() {
        recipePage.saveModifiedRecipe();
    }


    @Then("I see recipe {string} is created with draft status in browser tab")
    public void iVerifyRecipeIsCreated(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.verifyRecipe(recipe);
        Assert.assertEquals(this.recipe.getRecipeName(),this.recipePage.getRecipeName());
    }


    @Given("I edit recipe {string}")
    public void iEditRecipe(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.editRecipe(recipe);
    }
    
    @And("I see list of recipes are displayed")
    public void iSeeListOfRecipes() throws InterruptedException {
        recipePage.verifyList();
    }
    
    @And("below {string} column is displayed")
    public void verifyColumn(String tab, DataTable table) {
        List<List<String>> list = table.asLists(String.class);
        for (int i = 1; i < list.size(); i++) {
            recipePage.verifyColoumn(list.get(i).get(0), tab, i);
        }
    }

    @When("I delete phase to recipe")
    public void iDeletePhaseToRecipe() {
        recipePage.deletePhaseToRecipe();
    }

    @When("I add phase to recipe")
    public void iAddPhaseToRecipe() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhase(this.recipe.getPhaseName());
    }

    @When("I approve recipe")
    public void iApproveRecipe() {
        recipePage.approveRecipe(login.getPassword());
    }
    
    @When("I make recipe inactive")
    public void iInactiveRecipe() {
        recipePage.inactiveRecipe(login.getPassword());
    }
    
    @When("I make recipe Draft-Rejected")
    public void iRejectRecipe() {
        recipePage.rejectTechReviewRecipe();
    }

    @Then("Recipe should be approved")
    public void recipeShouldBeApproved() {
        Assert.assertEquals("Approved-Active",this.recipePage.getStatus());
        switchTo().parentFrame();
    }
    
    @Then("Recipe should be inactive")
    public void recipeShouldBeInactive() {
        Assert.assertEquals("Approved-InActive",this.recipePage.getStatus());
        switchTo().parentFrame();
    }
    
    @Then("Recipe should be Draft-Rejected")
    public void recipeShouldBeDraft() {
        Assert.assertEquals("Draft",this.recipePage.getStatus());
        switchTo().parentFrame();
    }

    @When("I click on export recipe {string}")
    public void iExport(String recipeName) {
        recipe.setRecipeName(recipeName);
    	recipePage.exportRecipe(recipe.getRecipeName());
    }

    @Then("I should see the recipe exported in user notifications")
    public void iShouldSeeExportMessage() {
    	recipePage.notificationMessageExport(recipe.getRecipeName());
    }

    @When("I click on import {string}")
    public void iClickOnImport(String recipeName) {
    	recipePage.importRecipe(recipeName);
    	recipe.setRecipeImportedName(recipePage.getGeneratedName());
    }
    
    @When("I click on print {string}")
    public void iClickOnPrint(String recipeName) throws Exception {
    	recipePage.printRecipe(recipeName);
    }

    @Then("I should see the recipe imported in user notifications")
    public void iShouldSeeImportMessage() {
    	recipePage.notificationMessageImport(recipe.getRecipeImportedName());
    }

    @Then("I look at the user notification")
    public void iLookAtTheUserNotification() {
        recipePage.lookAtTheUserNotification();
    }
}
