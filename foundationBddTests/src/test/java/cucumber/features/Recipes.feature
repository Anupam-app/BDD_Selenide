@CRS
Feature: Recipe management

  @IVI
  Scenario: BIOCRS-5478 | Recipe modification
  	Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "testDraftRecipeToAddPhase"
    And I delete phase to recipe
    And I add phase to recipe
    And I save the modified recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe

  @SMOKE  @IVI
  Scenario: IVI Bug IVI-5969 IVI-4468 | BIOCRS-5059 | Recipe approval
  	Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I edit recipe "testDraftRecipeToChangeStatus"
    When I approve recipe
    Then Recipe should be approved
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
    And I see the "testDraftRecipeToChangeStatus" is changed to "APPROVED-ACTIVE" in report
    
  @IVI 
  Scenario: IVI Bug IVI-5777 | Recipe Editor | User is allowed to save and approve a blank recipe | IVI Bug IVI-4971 | Recipe Management | Unable to export a recipe which has special characters in name
  	Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I save the recipe with name "!@#testSpecialChars?/\><%+-"
    When I approve recipe
    And Recipe should be approved
    And I go to recipe page
    And I search the recipe "!@testSpecialChars+-"
    When I export the recipe
    And I trigger edit mode
    And I import the recipe
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications
 
  @IVI
  Scenario: BIOCRS-5478 | Recipe Management Dashboard -  Browser Tab
    Given I am logged in as "Bio4CAdmin" user
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

  @IVI
  Scenario: IVI Bug IVI-5969 | BIOCRS-5060|BIOFOUND-12567| Recipe Obselete and Message Validation
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
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

  @IVI
  Scenario: IVI Bug IVI-5969 | BIOCRS-5060| Recipe Tech Review Rejected
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I edit recipe "testRecipeDraftToReject"
    When I make recipe Draft-Rejected
    Then Recipe should be Draft-Rejected
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
    And I see the "testRecipeDraftToReject" is changed to "DRAFT" in report

  @SMOKE  @IVI
  Scenario: Recipe creation
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe
  
  #File menu removed from IVI
  #To-DO: to be converted to use touch buttons
  Scenario Outline: BIOCRS-5477 | Unsaved Recipe Error Scenarios-1
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I choose "<option>" from file menu
    Then I see warning message is displayed "<message>"

    Examples:
      | option | message                              |
      | New    | Please save the recipe.              |
      | Import | Please save the recipe.              |
      | Print  | Only approved recipe can be printed. |

  @IVI
  Scenario: BIOCRS-5477 | User tries to select another recipe from Browser while there is unsaved recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I go to browser mode
    And I edit recipe "testRecipeDraftToReject"
    Then I see warning message is displayed "Please save the recipe."
  
  # IVI - unapplied changed pop up appears unlike crs
  Scenario: BIOCRS-5477 | user navigates away from 'Recipes' screen without saving recipe then recipe draft progress shall be discarded
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I go to other module without saving recipe
    And I come back to Recipe page
    Then I can create a recipe

  @IVI
  Scenario: Create new recipe with existing Recipe name
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe with name "testRecipeToExecute"
    Then I see warning message is displayed "Recipe is locked. Please save it as new copy."

  @IVI
  Scenario: BIOCRS-1594 BIOCRS-5478 | Recipe export and import
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I search the recipe "testDraftRecipeToAddPhase"
    When I export the recipe
    And I trigger edit mode
    And I import the recipe
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications
  
  # file menu is removed from IVI
  #To-DO: to be converted to use touch buttons
  Scenario: BIOCRS-1594 | Recipe print
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "testRecipeToExecute"
    Then I print recipe "testRecipeToExecute"

  @IVI
  Scenario: BIOFOUND-3768| Create step using Keyboard event
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    When I add new action step using Keyboard event
    Then I should see "blank" step added
    And I add action to the step
 
  @IVI 
  Scenario: BIOFOUND-3768| Create step using Action browser
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I select action from action browser
    And I should see "action" step added
    When I add new step with message prompt
    Then I should see message input text field displayed

  @IVI
  Scenario: BIOFOUND-3768| Create new phase 
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a new phase in recipe
    And I add action to the step
   #And I add recipe action from phase library ("Already a recipe should be there in phase library")
    And I add criteria to phase using keyboard
    And I save the recipe with name "testRecipe"
    And I close and reopen the recipe
    And I should see recipe opened in editor
  
  @IVI  
  Scenario: IVI Bug IVI-5800 | Unauthorized user cant create/edit the recipe
    Given I am logged in as "reportUnauthUser" user
    And I go to recipe page
    When I edit recipe "testDraftRecipe"
    Then I cannot edit the recipe
    And I cannot change the recipe status  
    And touch buttons are disabled

  @IVI   
  Scenario: IVI Bug IVI-5768 | Delete the phase
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I delete phase to recipe with shortcut key
    Then the phase is deleted

  @IVI   
  Scenario: IVI Bug IVI-5764 | Touch Enabled buttons for copy and paste phases
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I copy phase in recipe
    Then I am able to paste the phase
 
  @IVI 
  Scenario: IVI Bug IVI-5762 IVI-5761 IVI-5763| Recipe Editor | Correct Warning messages should be displayed for Phase buttons for blank recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    Then I verify phase buttons and warning messages

  @IVI   
  Scenario: IVI Bug IVI-4443 IVI-4480 | Save As recipe with shortcut keys
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "testDraftRecipe"
    And I perform saveAs option to save recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    And I approve recipe
    Then Recipe should be approved
