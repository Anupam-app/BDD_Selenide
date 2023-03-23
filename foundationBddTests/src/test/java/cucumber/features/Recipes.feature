@CRS @IVI
Feature: Recipe management

  Background:
    Given I am logged in as "Bio4CAdmin" user


  Scenario: BIOCRS-5478 | Recipe modification
    Given I go to recipe page
    When I edit recipe "testDraftRecipeToAddPhase"
    And I delete phase to recipe
    And I add phase to recipe
    And I save the modified recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe

  @SMOKE
  Scenario: BIOCRS-5059 | Recipe approval
    Given I go to recipe page
    And I edit recipe "testDraftRecipeToChangeStatus"
    When I approve recipe
    Then Recipe should be approved
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
    And I see the "testDraftRecipeToChangeStatus" is changed to "APPROVED-ACTIVE" in report

  Scenario: BIOCRS-5478 | Recipe Management Dashboard -  Browser Tab
    When I go to recipe page
    Then  I see list of recipes are displayed
    And below "recipe" column is displayed
      | columns          |
      | Recipe Name      |
      | System Family    |
      | Imported         |
      | Import Status    |
      | Created By       |
      | Last Modified On |
      | UOP Status       |
 
  Scenario: BIOCRS-5060|BIOFOUND-12567| Recipe Obselete and Message Validation
    Given I go to recipe page
    And I edit recipe "testRecipeDraftToInactive"
    When I make recipe inactive
    Then Recipe should be inactive
    And I try change recipe status and see warning pop up dialog box "No Status Change allowed."
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
    And I see the "testRecipeDraftToInactive" is changed to "APPROVED-INACTIVE" in report

  Scenario: BIOCRS-5060| Recipe Tech Review Rejected
    Given I go to recipe page
    And I edit recipe "testRecipeDraftToReject"
    When I make recipe Draft-Rejected
    Then Recipe should be Draft-Rejected
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
    And I see the "testRecipeDraftToReject" is changed to "DRAFT" in report

  @SMOKE
  Scenario: Recipe creation
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe

  Scenario Outline: BIOCRS-5477 | Unsaved Recipe Error Scenarios-1
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I choose "<option>" from file menu
    Then I see warning message is displayed "<message>"

    Examples:
      | option | message                              |
      | New    | Please save the recipe.              |
      | Import | Please save the recipe.              |
      | Print  | Only approved recipe can be printed. |

  Scenario: BIOCRS-5477 | User tries to select another recipe from Browser while there is unsaved recipe
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I go to browser mode
    And I edit recipe "testRecipeDraftToReject"
    Then I see warning message is displayed "Please save the recipe."

  Scenario: BIOCRS-5477 | user navigates away from 'Recipes' screen without saving recipe then recipe draft progress shall be discarded
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I go to other module without saving recipe
    And I come back to Recipe page
    Then I can create a recipe

  Scenario: Create new recipe with existing Recipe name
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe with name "testRecipeToExecute"
    Then I see warning message is displayed "Recipe is locked. Please save it as new copy."

  Scenario: BIOCRS-1594 BIOCRS-5478 | Recipe export and import
    Given I go to recipe page
    When I click on export recipe "testDraftRecipeToAddPhase"
    And I trigger edit mode
    And I click on import "testDraftRecipeToAddPhase"
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications

  Scenario: BIOCRS-1594 | Recipe print
    Given I go to recipe page
    When I edit recipe "testRecipeToExecute"
    Then I print recipe "testRecipeToExecute"

  Scenario: BIOFOUND-3768| Create step using Keyboard event
    Given I go to recipe page
    When I trigger edit mode
    When I add new action step using Keyboard event
    Then I should see "blank" step added
    And I add "Setpoint" action to the step

  Scenario: BIOFOUND-3768| Create step using Action browser
    Given I go to recipe page
    When I trigger edit mode
    And I select action from action browser
    And I should see "action" step added
    When I add new step with message prompt
    Then I should see message input text field displayed

  Scenario: BIOFOUND-3768| Create new phase
    Given I go to recipe page
    When I trigger edit mode
    And I create a new step in recipe
    And I add "Setpoint" action to the step
   #And I add recipe action from phase library ("Already a recipe should be there in phase library")
    And I add criteria to phase using keyboard
    And I save the recipe with name "testRecipe"
    And I close and reopen the recipe
    And I should see recipe opened in editor

  Scenario:BIOFOUND-19474|FT_CF_Recipe Management_Validate error message displayed when invalid/out of range float value is provided in Recipe steps
    Given I go to recipe page
    When I trigger edit mode
    When I add new action step using Keyboard event
    And I add "Threshold" action to the step
    And I try to change the setpoint value to out of range
    Then I verify error message displayed
    And I add new action step using Keyboard event
    And I add "Setpoint" action to the step
    And I Validate the error message for below input values
        |5   |
        |3.  |
        |.2  |
        |-1  |
    And I save the recipe with name "errorRecipe"
    And I try to change status and verify error message displayed "Recipe has errors. Cannot change status."

  Scenario:BIOFOUND-27906 |Maximum Phases
    Given I go to recipe page
    And I edit recipe "maxPhaseRecipe"
    #the maximum number of phases allowed in the recipe is 19
    When I create phase with shortcut key
    Then I get a warning notifying that "Cannot add phase, number of phases in the recipe is exceeding the maximum number allowed."
    When I add Phases from phase library to recipe
    Then I get a warning notifying that "Cannot add phase, number of phases in the recipe is exceeding the maximum number allowed."
    When I try to copy and paste the phase
    Then I get a warning notifying that "Cannot add phase, number of phases in the recipe is exceeding the maximum number allowed."

  Scenario:BIOFOUND-27810|Recipe status after import
    Given I go to recipe page
    And I have exported recipes in different status
       |testRecipeDraftToInactive    |
       |testRecipeDraftToReject      |
       |testDraftRecipeToChangeStatus|
       |recipeTechReview             |
       |recipeInReview               |
    And I import recipes in different status
       |testRecipeDraftToInactive    |
       |testRecipeDraftToReject      |
       |testDraftRecipeToChangeStatus|
       |recipeTechReview             |
       |recipeInReview               |
    Then the UoP status of imported recipe changes to Draft
       |testRecipeDraftToInactive1    |
       |testRecipeDraftToReject1      |
       |testDraftRecipeToChangeStatus1|
       |recipeTechReview1             |
       |recipeInReview1               |
    #And Import status shows the status before import
    And I edit recipe "testRecipeDraftToInactive1"
    Then I make recipe inactive