@CRS @IVI
Feature: Recipe management

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

  @SMOKE @IVI-4468
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

  @IVI-5777 @IVI-4971
  Scenario: IVI Bug IVI-5777 | Recipe Editor | User is allowed to save and approve a blank recipe | IVI Bug IVI-4971 | Recipe Management | Unable to export a recipe which has special characters in name
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I save the recipe with name "!@#testSpecialChars?/\><%+-"
    When I approve recipe
    And Recipe should be approved
    And I go to browser mode
    And I search the recipe "!@testSpecialChars+-"
    When I export the recipe
    And I trigger edit mode
    And I import the recipe
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications

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

    @IVI-4468
  Scenario: IVI Bug IVI-4468 | BIOCRS-5060|BIOFOUND-12567| Recipe Obselete and Message Validation
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I edit recipe "testRecipeDraftToInactive"
    When I make recipe inactive
    Then Recipe should be inactive
      #To-Do: below step will work once IVI-4468 is fixed
      #And I try change recipe status and see warning pop up dialog box "No Status Change allowed."
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
    And I see the "testRecipeDraftToInactive" is changed to "APPROVED-INACTIVE" in report

  Scenario: IVI Bug IVI-4468 | BIOCRS-5060| Recipe Tech Review Rejected
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

  @SMOKE
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

  Scenario: BIOCRS-5477 | User tries to select another recipe from Browser while there is unsaved recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I go to browser mode
    And I edit recipe "testRecipeDraftToReject"
    Then I see warning message is displayed "Please save the recipe."

  Scenario: Create new recipe with existing Recipe name
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe with name "testRecipeToExecute"
    Then I see warning message is displayed "Recipe is locked. Please save it as new copy."

  @IVI-6151
  Scenario: BUG IVI-6151 | BIOCRS-1594 BIOCRS-5478 | Recipe export and import
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I search the recipe "testDraftRecipeToAddPhase"
    When I export the recipe
    And I trigger edit mode
    And I import the recipe
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications

  Scenario: BIOFOUND-3768| Create step using Keyboard event
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    When I add new action step using Keyboard event
    Then I should see "blank" step added
    And I add action to the step

  @IVI-5149
  Scenario: IVI BUG IVI-5149 | BIOFOUND-3768| Create step using Action browser
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I select action from action browser
    And I should see "action" step added
    When I add new step with message prompt
    Then I should see message input text field displayed

  @IVI-5149
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

  Scenario: IVI Bug IVI-5800 | Unauthorized user cant create/edit the recipe
    Given I am logged in as "reportUnauthUser" user
    And I go to recipe page
    When I edit recipe "testDraftRecipe"
    Then I cannot edit the recipe
    And I cannot change the recipe status
    And I verify touch buttons are not displayed

  @IVI-5768
  Scenario: IVI Bug IVI-5768 | Delete the phase
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I delete phase to recipe with shortcut key
    Then the phase is deleted

  Scenario: Touch Enabled buttons for copy and paste phases
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I copy phase in recipe
    Then I am able to paste the phase

  @IVI-5761 @IVI-5762 @IVI-5763
  Scenario: IVI Bug IVI-5762 IVI-5761 IVI-5763| Recipe Editor | Correct Warning messages should be displayed for Phase buttons for blank recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    Then I verify phase buttons and warning messages

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

  Scenario: IVI Bug IVI-6064 | Recipe Management | Phase name renamed in phases section is not reflecting in step details
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I rename phase in recipe
    Then I am able to see the phase is renamed in Step

  Scenario: IVI Bug IVI-6071 | Recipe Management | Delete phase is not working as expected when there are multiple phases in recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "twoPhaseTestRecipe"
    And I delete phase to recipe with cross button
    Then the deleted phase is not shown in invocation step

  Scenario: IVI Bug IVI-6150 | Recipe Management | 'ALT+ENTER' is not working as expected when there is an existing phase
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I add step after step "1"
    Then I see blank step is added
    
  @IVI-6167
  Scenario: IVI Bug IVI-6167 | Recipe Management | Unable to modify a recipe which is in approved or InReview state
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "testRecipeFlows"
    And I add step after step "1"
    Then I see blank step is added
    
  @IVI-6153
  Scenario: IVI Bug IVI-6153 | Recipe Editor | Text/confirmation message not displayed on recipe editor
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    Then I verify recipe tab title
    When I create a phase
    Then I verify notification messages "Phase Successfully Created"
    