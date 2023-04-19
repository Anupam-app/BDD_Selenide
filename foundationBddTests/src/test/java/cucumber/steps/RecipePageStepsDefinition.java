package cucumber.steps;

import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.goToIFrame;

import java.awt.*;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import cucumber.util.I18nUtils;
import dataobjects.Login;
import dataobjects.Recipe;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.RecipePage;
import pageobjects.pages.ReportsPage;
import pageobjects.pages.UserPage;
import pageobjects.utility.SelenideHelper;

public class RecipePageStepsDefinition {

    private final RecipePage recipePage;
    private final UserPage userPage;
    private final ReportsPage reportPage;
    private final Recipe recipe;
    private final Login login;

    public RecipePageStepsDefinition(RecipePage recipePage, UserPage userPage, Recipe recipe, Login login,
                                     ReportsPage reportPage) {
        this.recipePage = recipePage;
        this.userPage = userPage;
        this.recipe = recipe;
        this.login = login;
        this.reportPage = reportPage;
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

    @When("I select phase library")
    public void iSelectPhaseLibrary() {
        recipePage.goToPhaseLibrary();
    }

    @When("I verify the message {string}")
    public void iVerifyPhaseLibraryMessage(String message) {
        recipePage.verifyPhaseMessage(message);
    }

    @Then("I go to browser mode")
    public void iGoToBrowserMode() {
        switchTo().parentFrame();
        reportPage.goToReports();
        recipePage.unappliedChangesPopUp();
        iGoToRecipePage();
    }

    @When("I create a random phase")
    public void iCreateARandomPhase() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhase(this.recipe.getPhaseName());
    }

    @When("I copy phase in recipe")
    public void iCopyPhase() {
        recipePage.copyPhase();
    }

    @When("I am able to paste the phase")
    public void iPastePhase() {
        recipePage.pastePhase(this.recipe.getPhaseName());
    }

    @When("I verify phase buttons and warning messages")
    public void iVerifyPhaseButtons() {
        recipePage.verifyPhaseButtons();
    }

    @When("I go to other module without saving recipe")
    public void igoToOtherModule() {
        switchTo().parentFrame();
        userPage.goTo();
        recipePage.unappliedChangesPopUp();
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

    @Then("I save the modified recipe")
    public void iSaveModifiedRecipeAs() {
        recipePage.saveModifiedRecipe();
    }

    @Given("I edit recipe {string}")
    public void iEditRecipe(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.editRecipe(recipe);
    }

    @And("I see list of recipes are displayed")
    public void iSeeListOfRecipes() {
        recipePage.verifyList();
    }

    @And("below {string} column is displayed")
    public void verifyColumn(String tab, DataTable table) {
        List<List<String>> list = table.asLists(String.class);
        for (int i = 1; i < list.size(); i++) {
            recipePage.verifyColoumn(list.get(i)
                .get(0), tab, i);
        }
    }

    @When("I delete phase to recipe")
    public void iDeletePhaseToRecipe() {
        recipePage.deletePhaseToRecipe();
    }

    @When("I delete phase to recipe with shortcut key")
    public void iDeletePhaseToRecipeWithShortCutKeys() {
        recipePage.deletePhaseToRecipeWithShortCutKeys();
    }

    @When("I perform saveAs option to save recipe")
    public void iDoSaveAsRecipeWithShortCutKeys() {
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(4));
        recipePage.saveAsRecipeWithShortCutKeys(this.recipe.getRecipeName());
    }

    @When("the phase is deleted")
    public void iVerifyPhaseIsDeleted() {
        recipePage.iSeePhaseDeleted();
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

    @Then("I make recipe inactive")
    public void iInactiveRecipe() {
        recipePage.inactiveRecipe(login.getPassword());
    }

    @When("I make recipe Draft-Rejected")
    public void iRejectRecipe() {
        recipePage.rejectTechReviewRecipe();
    }

    @Then("Recipe should be approved")
    public void recipeShouldBeApproved() {
        Assert.assertEquals("Approved-Active", this.recipePage.getStatus());
        switchTo().parentFrame();
    }

    @Then("Recipe should be inactive")
    public void recipeShouldBeInactive() {
        Assert.assertEquals("Approved-InActive", this.recipePage.getStatus());
        switchTo().parentFrame();
    }

    @Then("Recipe should be Draft-Rejected")
    public void recipeShouldBeDraft() {
        Assert.assertEquals("Draft", this.recipePage.getStatus());
        switchTo().parentFrame();
    }

    @When("I export the recipe")
    public void iExport() {
        recipePage.exportRecipe(recipe.getRecipeName());
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

    @When("I import the recipe")
    public void iClickOnImport() {
        recipePage.importRecipe(recipe.getRecipeName());
        recipe.setRecipeImportedName(recipePage.getGeneratedName());
        switchTo().parentFrame();
        reportPage.goToReports();
        iGoToRecipePage();
    }

    @When("I click on import {string}")
    public void iClickOnImport(String recipeName) {
        recipePage.importRecipe(recipeName);
        recipe.setRecipeImportedName(recipePage.getGeneratedName());
    }

    @When("I print recipe {string}")
    public void iPrintRecipe(String recipeName) {
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

    @Then("I see expected texts from recipe module")
    public void iSeeExpectedTextsFromRecipeModule() {
        var expectedText = I18nUtils.getValueFromKey("recipe.app.header.name");
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

    @When("I add new action step using Keyboard event")
    public void addStepKeyboard() {
        recipePage.keyboardActionRecipe();
    }

    @Then("I should see {string} step added")
    public void verifyStep(String status) {

        if (status.equalsIgnoreCase("blank")) {
            recipePage.placeholder(status);
        } else if (status.equalsIgnoreCase("action")) {
            recipePage.placeholder(status);
        }
    }

    @And("I add {string} action to the step")
    public void actionAddedInStep(String action) {
        recipePage.addActionStep(action);
    }

    @And("I select action from action browser")
    public void actionBrowser() {
        recipePage.addStepActionBrowser();
    }

    @When("I add new step with message prompt")
    public void addStepMessage() {
        recipePage.addMessageInStep();
    }


    @Then("I should see message input text field displayed")
    public void messageDisplayed() {
        recipePage.messageInputStepValidate();
    }

    @And("I create a new step in recipe")
    public void newPhase() {
        recipePage.addingStepByClickPlusIcon();
    }

    @And("I add criteria to phase using keyboard")
    public void addCriteriaInPhase() {
        recipePage.addCriteria();
    }

    @And("I close and reopen the recipe")
    public void openRecipe() {
        iGoToBrowserMode();
        recipePage.openRecipe(this.recipe.getRecipeName());
    }

    @And("I should see recipe opened in editor")
    public void verifyRecipeInEditor() {
        recipePage.verifyRecipeEditor(this.recipe.getRecipeName());
    }

    @And("I try change recipe status and see warning pop up dialog box {string}")
    public void recipeWarningMessage(String message) {
        recipePage.warningMessage(message);
        switchTo().parentFrame();
    }

    @When("I cannot edit the recipe")
    public void iCannotModifyRecipe() {
        recipePage.cannotEditRecipe();
    }

    @When("I cannot change the recipe status")
    public void iCannotModifyRecipeStatus() {
        recipePage.cannotEditRecipeStatus();
    }

    @When("touch buttons are disabled")
    public void touchButtonsAreDisabled() {
        recipePage.cannotClickTouchButtons();
    }

    @And("I verify touch buttons are not displayed")
    public void touchButtonsAreNotDisplayed() {
        recipePage.touchButtonNotDisplayed();
    }

    @When("I rename phase in recipe")
    public void iRenamePhase() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.renamePhase(this.recipe.getPhaseName());
    }

    @When("I am able to see the phase is renamed in Step")
    public void iverifyPhaseNameInStep() {
        recipePage.verifyPhaseIsRenamed(this.recipe.getPhaseName());
    }

    @When("the deleted phase is not shown in invocation step")
    public void iVerifyDeletedPhaseIsNotInvokedInStep() {
        recipePage.iSeeDeletedPhaseIsNotSeenInStep();
    }

    @When("I delete phase to recipe with cross button")
    public void iDeletePhaseToRecipeWithCrossButton() {
        recipePage.deletePhaseToRecipeWithCrossButton();
    }

    @And("I add step after step {string}")
    public void insertStepAfterStep(String stepNo) {
        recipePage.addActionStepAfterStep(stepNo);
    }

    @And("I see blank step is added")
    public void iSeeBlankStep() {
        recipePage.iSeeBlankStep();
    }

    @And("I verify recipe tab title")
    public void iVerifyRecipeTab() {
        recipePage.verifyRecipeTab();
    }

    @When("I create a phase")
    public void iCreatePhaseToRecipe() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.createPhase(this.recipe.getPhaseName());
    }

    @When("I verify notification messages {string}")
    public void iVerifyNotification(String message) {
        recipePage.verifyNotification(message);
    }

    @And("I verify error message {string} for out of range value entry")
    public void outRangeValuePassing(String message) {
        recipePage.outAndInOfRangeValue("out");
        recipePage.outOfRangeErrorMessage(message);
    }

    @And("I validate below error message for respective {string} values provided")
    public void inValidInputValue(String message,DataTable table) {
        recipePage.keyboardActionRecipe();
        recipePage.addActionStep(message);
        List <List<String>> list = table.asLists(String.class);
        for (int i = 1; i < list.size(); i++) {
            recipePage.inValidValueAndErrorMessageOfThreshold(list.get(i).get(0),list.get(i).get(1));
        }
    }

    @And("I verify error message {string} on changing recipe status")
    public void verifyErrorMessageOfChangeStatus(String message) {
        recipePage.verifyErrorMessageOfChangeStatus(message);
    }

    @When("I create phase with shortcut key")
    public void iCreatePhaseWithShortcutKey() {
        recipePage.createPhaseWithShortcutKey();
    }

    @Then("I get a warning notifying that {string}")
    public void iGetAWarningNotifyingThat(String message) {
        recipePage.maxPhaseWarningMessage(message);
    }

    @When("I add Phases from phase library to recipe")
    public void iAddPhaseFromPhaseLibraryToRecipe() {
        recipePage.addPhaseFromLibrary();
    }

    @When("I try to copy and paste the phase")
    public void iTryToCopyAndPasteThePhase() {
        recipePage.copyAndPastePhase();
    }

    @And("I open Recipe editor")
    public void theRecipeEditorPageIsOpen() {
        recipePage.goTo();
        goToIFrame();
        recipePage.goToEditMode();
    }

    @Then("I should see unsaved warning dialog box")
    public void iShouldSeeUnsavedWarningDialogBox() {
        recipePage.saveRecipeWarningMsg();
    }

    @And("I select OK and navigate to recipe editor")
    public void iSelectOkAndNavigateToRecipeEditer() {
        recipePage.okBtn();
    }

    @And("I add few more action steps")
    public void iAddFewMoreActionSteps() {
        recipePage.addFewSteps();
    }

    @And("I create a phase and add phase to library")
    public void iCreatePhaseAndAddPhaseToLibibrary() throws AWTException {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.zoomOut();
        recipePage.addPhaseAndLibrary(this.recipe.getPhaseName());
    }

    @And("I save the recipe with 30 character name")
    public void iSaveTheRecipeWithcharacterName() {
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(30));
        recipePage.iSaveRecipeWithkeyBoardActions(this.recipe.getRecipeName());
    }

    @And("I verify the recipe name displayed on Recipe tab")
    public void iVerifyTheRecipeNameDisplayedOnRecipeTab() {
        recipePage.iVerifyRecipeNameInRecipeTab(this.recipe.getRecipeName());
    }

    @Then("I verify recipe status as {string}")
    public void iVerifyRecipeStatus(String status) {
        recipePage.verifyRecipeStatus(status);
    }

    @And("I add few more steps and save the recipe")
    public void iAddStepsAndSaveTheRecipe() {
        recipePage.addFewSteps();
        recipePage.openRecipiList();
    }

    @Then("I should see full recipe name on mouse hover")
    public void iCheckRecipeNameWithMouseHover() {
        recipePage.setSearch(this.recipe.getRecipeName());
        recipePage.iCheckRecipeNameWithMouseOver();
    }

    @And("I save the recipes")
    public void iSaveRecipes() {
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(15));
        recipePage.saveRecipe(this.recipe.getRecipeName());
        SelenideHelper.goToDefault();
    }

    @And("I should see last modified recipe name")
    public void iSeeLastModifiedRecipeName() {
        recipePage.setSearch(this.recipe.getRecipeName());
        recipePage.iVerifyLatestModifiedRecipe();
    }

    @And("I change the recipe to in review")
    public void iChangeRecipeToInReview() {
        recipePage.DraftToInReview();
        recipePage.okBtn();
    }

    @And("I open the recipe and add few more steps")
    public void iOpenRecipeAndAddFewMoreSteps() {
        recipePage.addFewSteps();
    }

    @And("I try to save the recipe")
    public void iTryToSaveTheRecipe() {
        recipePage.tryToSaveRecipe();
    }

    @And("I should see warning popup alert with text message {string}")
    public void iSeeWarningpopupAlertWithTextMessage(String message) {
        recipePage.waringpopupForRecipe(message);
    }

    @And("I select OK and save as new recipe")
    public void iSelectOkAndSaveAsNewRecipe() {
        recipePage.okBtn();
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(5));
        recipePage.saveBtn(this.recipe.getRecipeName());
    }

    @And("I navigate to recipe browser, open a recipe")
    public void iNavigateToRecipeBrowserAndOpenRecipe() {
        // iGoToBrowserMode();
        recipePage.goToBrowserMode();
        recipePage.chooseRecipe();
    }

    @And("I have exported recipes in different status")
    public void exportRecipe(DataTable table) {
        List<String> list = table.asList(String.class);
        list.forEach(recipePage::listOfRecipeExport);
    }

    @And("I import recipes in different status")
    public void importRecipe(DataTable table) {
        List<String> list = table.asList(String.class);
        list.forEach(recipePage::listOfImportRecipe);
    }

    @Then("the UoP status of imported recipe changes to Draft")
    public void importedRecipeStatusIsDraft(DataTable table) {
        iGoToBrowserMode();
        List<String> list = table.asList(String.class);
        list.forEach(recipePage::importedRecipeStatusIsDraft);
    }

    @And("I add few actions steps")
    public void iAddFewActionsSteps() {
        recipePage.addingPhaseByPlus();
        recipePage.addActionStep();
        recipePage.addFewSteps();
    }

    @And("I create a random phase with multiple steps")
    public void iCreateRandomPhaseWithMultipleSteps() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(3));
        recipePage.createPhaseWithMutlipleSteps(this.recipe.getPhaseName());
    }

    @And("I select GoTo Phase button")
    public void iClickOnGoToPhaseButton() {
        recipePage.goToPhaseBtn();
    }

    @And("I select GoTo Step button a drop down opened")
    public void iClickOnGoToStepButtonDropDownOpened() {
        recipePage.goToStep();
    }

    @Then("drop down contain phase invocation step number")
    public void dropDownContainPhaseInvocationStepNumber() {
        recipePage.stepInvocation();
    }

    @And("I save as recipe name {string}")
    public void iSaveAsRecipeName(String name) {
        recipePage.saveAsRecipeWithShortCutKeys(name);
    }

    @And("^I verify below recipes are displayed in recipe browser list$")
    public void iVerifyTwoRecipes(DataTable table) {
        iGoToBrowserMode();
        List<String> list = table.asList(String.class);
        for (int i = 1; i < list.size(); i++) {
            list.forEach(recipePage::iCheckTwoRecipes);
        }
    }

    @And("I verify recipe steps are not modified")
    public void iVerifyRecipeStepsAreNotModified() {
        recipePage.stepsNotModified();
    }

    @And("I add few actions steps to existing recipe")
    public void iAddFewActionsStepsToExistingRecipe() {
        recipePage.addFewSteps();
    }

    @And("I verify the Unsaved status below recipe name")
    public void iVerifyUnsavedStatus() {
        recipePage.verifyUnsaved();
    }

    @And("I expand the recipe action browser")
    public void iExpandTheRecipeActionBrowser() {
        recipePage.operationActions();
    }

    @And("phases option is displayed")
    public void phaseOptionIsDisplayed() {
        recipePage.verifyPhaseOption();
    }

    @Then("Recipe is saved")
    public void recipeIsSaved() {
        recipePage.verifySavedRecipe();
    }

    @And("I close the recipe")
    public void iCloseTheRecipe() {
        recipePage.closeBtn();
    }

    @Then("blank recipe is displayed")
    public void iDisplayedBlankRecipe() {
        recipePage.blankRecipe();
    }

    @And("I cancel the recipe")
    public void iCancelTheRecipe() {
        recipePage.cancelRecipe();
    }

    @And("I discard the recipe")
    public void iDiscardTheRecipe() {
        recipePage.iDiscard_Btn();
    }

    @And("I save the recipe from warning box")
    public void iSaveTheRecipeFromWarningBox() {
        recipePage.saveFromWarningBox();
    }

    @And("I add one new step")
    public void iAddOneNewStep() {
        recipePage.singleStep();
    }

    @When("I create phase with errors")
    public void iCreatePhaseWithErrors() {
        recipePage.creatingPhaseWithError();
    }

    @And("I try to add phase to phase library")
    public void iTryToAddPhaseToPhaseLibrary() throws AWTException {
        recipePage.addPhaseLibraryWithErrorPhase();
    }

    @Then("I get appropriate error")
    public void iGetappropriateError() {
        recipePage.checkErrorMsg();
    }

    @And("Phase is not added to phase library.")
    public void phaseIsNotAddedToPhaseLibrary() {
        recipePage.checkWindowPopupMsg();
    }

    @And("I clear errors in the phase")
    public void iClearErrorsInThePhase() {
        recipePage.iClearPhaseErrorStep();
    }

    @Then("I can add phase to phase library.")
    public void iCanAddPhaseToPhaseLibrary() throws AWTException {
        recipePage.addPhaseLibraryWithErrorPhase();
    }

    @And("I add action and create random phase with multiple steps")
    public void iAddActionAndCreateRandomPhaseWithMultipleSteps() {
        recipePage.addingPhaseByPlus();
        recipePage.addActionStep();
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(3));
        recipePage.createPhaseWithMutlipleSteps(this.recipe.getPhaseName());
    }
    @When("I edit the recipe {string} from recipe browser")
    public void editRecipeFromBrowser(String recipe){
        recipePage.goTo();
        goToIFrame();
        this.recipe.setRecipeName(recipe);
        recipePage.editRecipe(recipe);
    }
    @When("I update actual range of value")
    public void updateThresholdValue(){
        recipePage.outAndInOfRangeValue("in");
    }
    @Then("I should be able to save & approve recipe")
    public void saveAndApproveRecipe(){
        recipePage.saveModifiedRecipe();
        recipePage.approveRecipe(login.getPassword());
    }
}
