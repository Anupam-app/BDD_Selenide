package cucumber.steps;

import cucumber.util.I18nUtils;
import dataobjects.Login;
import dataobjects.Recipe;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.RecipePage;
import pageobjects.utility.SelenideHelper;

import static pageobjects.utility.SelenideHelper.goToIFrame;

public class RecipePageStepsDefinition {

    private RecipePage recipePage;
    private Recipe recipe;
    private Login login;

    public RecipePageStepsDefinition(RecipePage recipePage, Recipe recipe,Login login) {
        this.recipePage = recipePage;
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

    @Then("I search the recipe {string}")
    public void iSearchRecipe(String recipeName) {
        this.recipe.setRecipeName(recipeName);
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

    @Then("Recipe should be approved")
    public void recipeShouldBeApproved() {
        Assert.assertEquals("Approved-Active",this.recipePage.getStatus());
    }

    @When("I export the recipe")
    public void iExport() {
    	recipePage.exportRecipe(recipe.getRecipeName());
    }

    @Then("I should see the recipe exported in user notifications")
    public void iShouldSeeExportMessage() {
    	recipePage.notificationMessageExport(recipe.getRecipeName());
    }

    @When("I import the recipe")
    public void iClickOnImport() {
    	recipePage.importRecipe(recipe.getRecipeName());
    	recipe.setRecipeImportedName(recipePage.getGeneratedName());
    }

    @Then("I should see the recipe imported in user notifications")
    public void iShouldSeeImportMessage() {
    	recipePage.notificationMessageImport(recipe.getRecipeImportedName());
    }

    @Then("I look at the user notification")
    public void iLookAtTheUserNotification() {
        recipePage.lookAtTheUserNotification();
    }

    @Then("I see expected texts from recipe module")
    public void iSeeExpectedTextsFromRecipeModule() {
        var expectedText= I18nUtils.getValueFromKey("recipe.app.header.name");
        recipePage.seeContent(expectedText);

        SelenideHelper.goParentFrame();
    }

    @Then("I see expected texts from recipe module criterias")
    public void iSeeExpectedTextsFromRecipeModuleParameters() {
        recipePage.goToEditMode();

        var deviceShapeElementNotTranslated = recipePage.getDeviceShapeElementNotLoaded();
        Assert.assertTrue("deviceShapeElementNotTranslated:" + deviceShapeElementNotTranslated.toString(),
                deviceShapeElementNotTranslated.isEmpty());

        SelenideHelper.goParentFrame();
    }
}
