package cucumber.steps;

import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.goToIFrame;

import java.awt.AWTException;
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
import pageobjects.pages.RecipeTouchEnablerPage;
import pageobjects.pages.ReportsPage;
import pageobjects.pages.UserPage;
import pageobjects.utility.SelenideHelper;

public class RecipePageStepsDefinition {

    private final RecipePage recipePage;
    private final UserPage userPage;
    private final ReportsPage reportPage;
    private final Recipe recipe;
    private final Login login;
    private final RecipeTouchEnablerPage recipeTouchEnablerPage;
    private final RecipeConsoleStepsDefinition recipeConsoleStepsDefinition;
    public RecipePageStepsDefinition(RecipePage recipePage, UserPage userPage, Recipe recipe, Login login,
                                     ReportsPage reportPage, RecipeTouchEnablerPage recipeTouchEnablerPage, RecipeConsoleStepsDefinition recipeConsoleStepsDefinition) {
        this.recipePage = recipePage;
        this.userPage = userPage;
        this.recipe = recipe;
        this.login = login;
        this.reportPage = reportPage;
        this.recipeTouchEnablerPage = recipeTouchEnablerPage;
        this.recipeConsoleStepsDefinition = recipeConsoleStepsDefinition;
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
        recipePage.unAppliedChangesPopUp();
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
        recipePage.unAppliedChangesPopUp();
    }

    @When("I can create a recipe")
    public void iCreateRecipe() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhase(this.recipe.getPhaseName());
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(10));
        recipePage.saveRecipe(this.recipe.getRecipeName());

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
    public void iSeeWarningMessageDisplayed(String message) {
        recipePage.isGeneratedNotificationWhenCreateExistingRecipe(message);
    }

    @And("I see my changes in recipe")
    public void iSeeMyChangesInRecipe() {
        Assert.assertEquals(this.recipePage.getPhaseName(), this.recipe.getPhaseName());
        switchTo().parentFrame();
    }

    @Then("I save the modified recipe")
    public void iSaveModifiedRecipeAs() {
        recipePage.saveModifiedRecipe();
    }

    @Given("I edit recipe {string}")
    public void iEditRecipe(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.editRecipe(this.recipe.getRecipeName());
    }

    @And("I see list of recipes are displayed")
    public void iSeeListOfRecipes() {
        recipePage.verifyList();
    }

    @And("below {string} column is displayed")
    public void verifyColumn(String tab, DataTable table) {
        List<List<String>> list = table.asLists(String.class);
        for (int i = 1; i < list.size(); i++) {
            recipePage.verifyColumn(list.get(i)
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

    @Then("I see expected texts from recipe module criteria")
    public void iSeeExpectedTextsFromRecipeModuleParameters() {
        recipePage.goToEditMode();
        var deviceShapeElementNotTranslated = recipePage.getDeviceShapeElementNotLoaded();
        Assert.assertTrue("deviceShapeElementNotTranslated:" + deviceShapeElementNotTranslated.toString(),
                deviceShapeElementNotTranslated.isEmpty());
        SelenideHelper.goParentFrame();
    }

    @When("I add new action step using Keyboard event")
    public void addStepKeyboard() {
        this.recipe.setOrgStepCount(recipePage.actionsStepsCount());
        recipePage.keyboardActionRecipe();
    }

    @Then("I should see {string} step added")
    public void verifyStep(String status) {
        recipePage.placeholder(status);
    }

    @And("I add {string} action to the step")
    public void actionAddedInStep(String action) {
        recipePage.addActionStep(action);
    }

    @And("I select action from action browser")
    public void actionBrowser() throws AWTException {
        this.recipe.setOrgStepCount(recipePage.actionsStepsCount());
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
    public void iVerifyPhaseNameInStep() {
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
        recipePage.verifyRecipeTab("Untitled", "Unsaved");
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

    @And("I should see error message for respective {string} values provided")
    public void inValidInputValue(String action, DataTable table) {
        recipePage.keyboardActionRecipe();
        recipePage.addActionStep(action);
        List<List<String>> list = table.asLists(String.class);
        for (int i = 1; i < list.size(); i++) {
            recipePage.inValidValueAndErrorMessageOfThreshold(list.get(i)
                    .get(0),
                    list.get(i)
                            .get(1));
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
        this.recipe.setOrgStepCount(recipePage.actionsStepsCount());
        recipePage.addPhaseFromLibrary("Phase123");
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

    @And("I save the recipe with 30 character name")
    public void iSaveTheRecipeWithCharacterName() {
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(30));
        recipePage.iSaveRecipeWithKeyBoardActions(this.recipe.getRecipeName());
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
        recipePage.openRecipeList();
    }

    @Then("I should see full recipe name on mouse hover")
    public void iCheckRecipeNameWithMouseHover() {
        recipePage.setSearch(this.recipe.getRecipeName());
        recipePage.iCheckRecipeNameWithMouseOver();
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
    public void iSeeWarningPopUpAlertWithTextMessage(String message) {
        recipePage.waringPopUpForRecipe(message);
    }

    @And("I select OK and save as new recipe")
    public void iSelectOkAndSaveAsNewRecipe() {
        recipePage.okBtn();
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(5));
        recipePage.saveBtn(this.recipe.getRecipeName());
    }

    @And("I navigate to recipe browser, open a recipe {string}")
    public void iNavigateToRecipeBrowserAndOpenRecipe(String recipeName) {
        recipePage.goToBrowserMode();
        recipePage.chooseRecipe(recipeName);
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

    @Then("the UoP status of imported recipe changes to {string}")
    public void importedRecipeStatusIsDraft(String status, DataTable table) {
        iGoToBrowserMode();
        List<String> list = table.asList(String.class);
        for (int i = 1; i < list.size(); i++) {
            recipePage.importedRecipeStatusIsDraft(list.get(i), status);
        }
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
        recipePage.createPhaseWithMultipleSteps(this.recipe.getPhaseName());
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

    @And("I {string} the recipe")
    public void iSelectButton(String value) {
        recipePage.selectButtonDialogBox(value);
    }

    @And("I add one new step")
    public void iAddOneNewStep() {
        recipePage.singleStep();
    }

    @When("I create phase with errors")
    public void iCreatePhaseWithErrors() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(3));
        recipePage.creatingPhaseWithError(this.recipe.getPhaseName());
    }

    @And("I try to add phase to phase library")
    public void iTryToAddPhaseToPhaseLibrary() throws AWTException {
        recipePage.addPhaseLibraryWithErrorPhase();
    }

    @Then("I get appropriate error")
    public void iGetAppropriateError() {
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

    @And("I add action and create random phase with multiple steps")
    public void iAddActionAndCreateRandomPhaseWithMultipleSteps() {
        recipePage.addingPhaseByPlus();
        recipePage.addActionStep();
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(3));
        recipePage.createPhaseWithMultipleSteps(this.recipe.getPhaseName());
    }

    @Then("I add {string} to the {string} step")
    public void actionAddedInBlankStep(String action, String status) {
        recipePage.placeholder(status);
        recipePage.addActionStep(action);
    }

    @And("I should see step count increased by {int}")
    public void stepCountIncrease(int value) {
        recipePage.verifyActionStepCount(recipe.getOrgStepCount(), value);
    }

    @When("I edit the recipe {string} from recipe browser")
    public void editRecipeFromBrowser(String recipe) {
        recipePage.goTo();
        goToIFrame();
        this.recipe.setRecipeName(recipe);
        recipePage.editRecipe(recipe);
    }

    @When("I update actual range of value")
    public void updateThresholdValue() {
        recipePage.outAndInOfRangeValue("in");
    }

    @Then("I should be able to save & approve recipe")
    public void saveAndApproveRecipe() {
        recipePage.saveModifiedRecipe();
        recipePage.approveRecipe(login.getPassword());
    }

    @And("I add all the criteria with few actions steps")
    public void iAddAllTheCriteriaWithFewActionsSteps() throws AWTException {
        recipePage.multipleSteps();
    }

    @And("I should see confirmation message {string}")
    public void notification(String text) {
        recipePage.checkNotification(text);
    }

    @And("I save the recipe using keyboard event")
    public void iSaveTheRecipeUsingKeyboardEvent() {
        recipePage.iSaveRecipeWithKeyboardAction();
    }

    @Then("I see new recipe is saved as {string}")
    public void newRecipeIsSaved(String status) {
        iGoToBrowserMode();
        recipePage.importedRecipeStatusIsDraft(this.recipe.getRecipeName(), status);
    }

    @And("I saveAs the recipe")
    public void iSaveAsTheRecipe() {
        recipePage.saveAsRecipe();
    }

    @Then("I select {string} recipe to verify the warning text message {string}")
    public void iVerifyTheWarningPopupAlert(String existing, String message) {
        recipePage.iVerifyTheAlert(existing, message);
    }


    @And("I delete the step{string} using shortcut key")
    public void deleteStepUsingShortcut(String stepNo) {
        recipePage.deleteStepUsingShortcut(stepNo);
    }

    @And("I verify step{string} is deleted and message seen {string}")
    public void validateStepDelete(String stepNo, String message) {
        recipePage.validateStepDelete(stepNo);
    }

    @And("I delete the step{string} using cross button")
    public void deleteStepUsingCrossButton(String stepNo) {
        recipePage.deleteStepUsingCrossButton(stepNo);
    }

    @And("I delete the {string} criteria using shortcut key")
    public void deleteCriteriaUsingShortCut(String step) {
        recipePage.deleteCriteriaUsingShortcut(step);
    }

    @And("I delete the {string} criteria using cross button")
    public void deleteCriteriaUsingCrossButton(String step) {
        recipePage.deleteCriteriaUsingCrossButton(step);
    }

    @And("I verify {string} criteria is deleted and message seen {string}")
    public void validateCriteriaDelete(String step, String message) {
        recipePage.validateCriteriaDelete(step);
    }

    @Then("I verify the default step wait time dialog box")
    public void defaultStepWaitTimePopUp() {
        recipePage.defaultStepWaitTimePopUp();
    }

    @And("I update default wait time")
    public void verifyTimeField() {
        recipePage.setDefaultStepWaitTime("01", "hours");
    }

    @Then("I verify the saved step wait time")
    public void verifySaveTimeFieldValue() {
        recipePage.verifySaveTimeFieldValue();
    }

    @Then("I verify the recipe {string}")
    public void iVerifyTheRecipeStatus(String status) {
        iGoToBrowserMode();
        recipePage.importedRecipeStatusIsDraft(this.recipe.getRecipeName(), status);
    }

    @And("I verify action {string} in the step")
    public void iVerifyRecipeActionStep(String actionStep) {
        recipePage.verifyRecipeActionStep(actionStep);
    }

    @And("I verify recipe {string} permission")
    public void verifyPermissionAccess(String permission){
        if(!permission.equalsIgnoreCase("Control Run")){
            iGoToRecipePage();
        }
        switch(permission) {
            case "View Recipe":
                recipePage.goToEditMode();
                recipePage.viewOnlyRecipeAccess();
                recipeTouchEnablerPage.buttonDisabled("Import");
                recipeTouchEnablerPage.buttonDisabled("Save");
                recipeTouchEnablerPage.buttonDisabled("Save As");
                recipeTouchEnablerPage.buttonDisabled("Export");
                break;
            case "Create Recipe":
                createRecipe();
                break;
            case "Edit Recipe":
                editRecipe();
                break;
            case "Approve Recipe":
                recipeStatueChange("InReviewToApprovePermission", "MerckApp1@","Approved-Active");
                break;
            case "Deactivate Recipe":
                recipeStatueChange("DeactivateRecipe", "MerckApp1@", "Approved-Inactive");
                break;
            case "Review Recipe":
                recipeStatueChange("reviewRecipe", "MerckApp1@", "Tech-Review");
                break;
            case "Control Run":
                SelenideHelper.goToDefault();
                recipeConsoleStepsDefinition.runRecipe("testRecipeToExecute1min","3");
                break;
            default:
        }
    }

    public void createRecipe(){
        recipePage.goToEditMode();
        recipePage.addingPhaseByPlus();
        recipePage.addActionStep();
        recipePage.addFewSteps();
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(10));
        recipeTouchEnablerPage.buttonClick("Save");
        recipePage.saveRecipeNewAndExisting(this.recipe.getRecipeName());
    }

    public void editRecipe(){
        this.recipe.setRecipeName("testDraftRecipeToAddPhase");
        recipePage.editRecipe(this.recipe.getRecipeName());
        this.recipe.setOrgStepCount(recipePage.actionsStepsCount());
        recipePage.keyboardActionRecipe();
        recipePage.addActionStep("Setpoint");
        recipeTouchEnablerPage.buttonClick("Save");
        recipePage.saveRecipeNewAndExisting(this.recipe.getRecipeName());
    }

    public void recipeStatueChange(String recipeName, String password, String status){
        this.recipe.setRecipeName(recipeName);
        recipePage.editRecipe(this.recipe.getRecipeName());
        recipePage.recipeStatusChange(password,status);
    }

}
