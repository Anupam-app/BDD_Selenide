@CRS @IVI
Feature: Recipe management

  JIRAs tested:
  https://stljirap.sial.com/browse/BIOFOUND-27859
  https://stljirap.sial.com/browse/BIOFOUND-10899
  https://stljirap.sial.com/browse/BIOFOUND-12786
  https://stljirap.sial.com/browse/BIOFOUND-10897
  https://stljirap.sial.com/browse/BIOFOUND-27821
  https://stljirap.sial.com/browse/BIOFOUND-27908
  https://stljirap.sial.com/browse/BIOFOUND-27818
  https://stljirap.sial.com/browse/BIOFOUND-19474
  https://stljirap.sial.com/browse/BIOFOUND-27865
  https://stljirap.sial.com/browse/BIOFOUND-27816
  https://stljirap.sial.com/browse/BIOFOUND-27905
  https://stljirap.sial.com/browse/BIOFOUND-27935
  https://stljirap.sial.com/browse/BIOFOUND-27904
  https://stljirap.sial.com/browse/BIOFOUND-27903
  https://stljirap.sial.com/browse/BIOFOUND-28042

  @IVI-6688
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
    And I generate audit trail report
    And I verify audit logs for recipe "modify"
    And I check the audit trail report
    Then I see the "modify" recipe events in report

  @SMOKE @IVI-4468 @IVI-6688
  Scenario: BIOCRS-5059 | Recipe approval
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
  Scenario: IVI Bug IVI-4468 | BIOCRS-5060|BIOFOUND-12567| Recipe Obsolete and Message Validation
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

  @IVI-4468
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

  @SMOKE @IVI-6688
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
    And I generate audit trail report
    And I verify audit logs for recipe "created"
    And I check the audit trail report
    Then I see the "created" recipe events in report

  Scenario: BIOCRS-5477 | User tries to select another recipe from Browser while there is unsaved recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    Then I verify notification messages "Phase created successfully"
    And I navigate to recipe browser, open a recipe "testRecipeDraftToReject"
    Then I see warning message is displayed "Please save the recipe."

  Scenario: BIOCRS-5477 | user navigates away from 'Recipes' screen without saving recipe then recipe draft progress shall be discarded
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I go to other module without saving recipe
    And I open Recipe editor
    Then I can create a recipe

  Scenario: Create new recipe with existing Recipe name
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create a random phase
    Then I verify notification messages "Phase created successfully"
    And I save the recipe with name "testRecipeToExecute"
    And I see warning message is displayed "Recipe is locked. Please save it as new copy."

  @IVI-6151 @IVI-6688
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
    And I add "Setpoint" action to the step

  Scenario: BIOFOUND-3768| Create step using Action browser
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
    And I create a new step in recipe
    And I add "Setpoint" action to the step
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
    Then I verify notification messages "Phase created successfully"
    And I delete phase to recipe with shortcut key
    And the phase is deleted

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

  Scenario: Save As recipe with shortcut keys
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
    Then I verify notification messages "Phase created successfully"
    And I rename phase in recipe
    And I am able to see the phase is renamed in Step

  Scenario: IVI Bug IVI-6071 | Recipe Management | Delete phase is not working as expected when there are multiple phases in recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "twoPhaseTestRecipe"
    And I delete phase to recipe with cross button
    Then the deleted phase is not shown in invocation step

  @IVI-6150
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
    Then I verify notification messages "Phase created successfully"

  Scenario: Validate error message displayed when invalid/out of range float value is provided in Recipe steps
    Given I am logged in as "Bio4CAdmin" user
    When I open Recipe editor
    When I add new action step using Keyboard event
    And I add "Threshold" action to the step
    And I verify error message "Out of Range" for out of range value entry
    And I should see error message for respective "Setpoint" values provided
      | 5  | Out of Range                        |
      | 3. | No value before/after decimal point |
      | .2 | No value before/after decimal point |
      | -1 | Out of Range                        |
      | 1  |                                     |
    And I save the recipe with name "errorRecipe"


  Scenario:BIOFOUND-27906 |Maximum Phases
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I edit recipe "maxPhaseRecipe"
    When I create phase with shortcut key
    Then I get a warning notifying that "Cannot add phase, number of phases in the recipe is exceeding the maximum number allowed."
    When I add Phases from phase library to recipe
    Then I get a warning notifying that "Cannot add phase, number of phases in the recipe is exceeding the maximum number allowed."
    When I try to copy and paste the phase
    Then I get a warning notifying that "Cannot add phase, number of phases in the recipe is exceeding the maximum number allowed."

  Scenario: Recipe management_Save As recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "testRecipeDraftToReject"
    And I add few actions steps to existing recipe
    And I save as recipe name "secondRecipe"
    Then I verify below recipes are displayed in recipe browser list
      | testRecipeDraftToReject |
      | secondRecipe            |
    When I edit recipe "testRecipeDraftToReject"
    And I verify recipe steps are not modified
    And I add few actions steps to existing recipe
    And I verify the Unsaved status below recipe name
    And I save as recipe name "ThirdRecipe"
    Then I verify below recipes are displayed in recipe browser list
      | testRecipeDraftToReject |
      | secondRecipe            |
      | ThirdRecipe             |

  Scenario:BIOFOUND-27810|Recipe status after import
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I have exported recipes in different status
      | testRecipeDraftToInactive     |
      | testRecipeDraftToReject       |
      | testDraftRecipeToChangeStatus |
      | recipeTechReview              |
      | recipeInReview                |
    And I import recipes in different status
      | testRecipeDraftToInactive     |
      | testRecipeDraftToReject       |
      | testDraftRecipeToChangeStatus |
      | recipeTechReview              |
      | recipeInReview                |
    Then the UoP status of imported recipe changes to "Draft"
      | testRecipeDraftToInactive1     |
      | testRecipeDraftToReject1       |
      | testDraftRecipeToChangeStatus1 |
      | recipeTechReview1              |
      | recipeInReview1                |
    And I edit recipe "testRecipeDraftToInactive1"
    Then I make recipe inactive

  Scenario: Create and save a Recipe with 30 characters
    Given I am logged in as "Bio4CAdmin" user
    When I open Recipe editor
    And I add few actions steps
    And I add criteria to phase using keyboard
    And I verify recipe status as "Unsaved"
    And I save the recipe with 30 character name
    And I verify the recipe name displayed on Recipe tab
    And I verify recipe status as "Saved"
    And I go to browser mode
    And I should see full recipe name on mouse hover

  Scenario: Verify new recipe and existing recipe
    Given I am logged in as "Bio4CAdmin" user
    When I open Recipe editor
    And I add few actions steps
    And I save the recipe
    Then I verify recipe status as "Saved"
    And I go to browser mode
    And I should see last modified recipe name
    And I change the recipe to in review
    And I open the recipe and add few more steps
    And I try to save the recipe
    And I should see warning popup alert with text message "Recipe is locked. Please save it as new copy."
    And I select OK and save as new recipe

  Scenario: Availability of GoTo step and Goto Phase buttons
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I add few actions steps
    And I create a random phase with multiple steps
    And I select GoTo Phase button
    And I select GoTo Step button a drop down opened
    Then drop down contain phase invocation step number

  Scenario: Recipe management_ Operation phase criteria
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I add action and create random phase with multiple steps
    And I expand the recipe action browser
    And phases option is displayed
    And I add few actions steps to existing recipe
    And I create a random phase with multiple steps
    And I expand the recipe action browser
    And phases option is displayed
    And I save the recipe
    Then Recipe is saved

  Scenario: Recipe management_Closing recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "testDraftRecipe"
    And I close the recipe
    Then blank recipe is displayed
    And I go to browser mode
    And I edit recipe "testDraftRecipe"
    And I add few actions steps to existing recipe
    And I close the recipe
    And I "Cancel" the recipe
    And I close the recipe
    And I "Discard" the recipe
    And I add one new step
    And I close the recipe
    And I "Save" the recipe
    And I perform saveAs option to save recipe

  Scenario: Addition of phases with errors to Phase Library
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I create phase with errors
    And I save phase to Phase library
    Then I get appropriate error
    And Phase is not added to phase library.
    When I clear errors in the phase
    And I save phase to Phase library
    Then I should be notified that "Phase successfully added to phase library"
    And I should see phase details under phase library

  Scenario: Validate the add Step count
    Given I am logged in as "Bio4CAdmin" user
    When I open Recipe editor
    When I add new action step using Keyboard event
    Then I add "Setpoint" to the "blank" step
    And I should see step count increased by 1
    And I select action from action browser
    And I should see step count increased by 1
    And I Copy step number "2"
    And I paste the copied step after step 2
    And I should see step count increased by 1
    When I add Phases from phase library to recipe
    And I should see step count increased by 1

  Scenario: Recipe status cannot change when errors are present
    Given I am logged in as "Bio4CAdmin" user
    When I edit the recipe "errorRecipe" from recipe browser
    Then I verify error message "Recipe has errors. Cannot change status." on changing recipe status
    When I update actual range of value
    Then I should be able to save & approve recipe

  @IVI-7794
  Scenario: Addition of recipe criteria
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I trigger edit mode
    And I add all the criteria with few actions steps
    Then I save as recipe name "RecipeWithAllCriteria"
    When I go to browser mode
    And I edit recipe "RecipeWithAllCriteria"
    And I add criteria to phase using keyboard
    Then I should see warning popup alert with text message "recipe.alert.msg.max_criteria_ms"

  Scenario: Cloning of Tech review recipe
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "recipeTechReview"
    And I add few actions steps to existing recipe
    And I save the recipe using keyboard event
    Then Recipe is saved
    And I go to browser mode
    When I edit recipe "recipeTechReview"
    And I perform saveAs option to save recipe
    Then I see new recipe is saved as "Draft"

  Scenario Outline:  Overwriting recipe with different status such as Tech review, In review, Approved active and Approved Inactive
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "<recipes>"
    And I saveAs the recipe
    Then I select "<existing>" recipe to verify the warning text message "Recipe is locked. Please save it as new copy."

    Examples:
      | recipes          | existing                      |
      | testDraftRecipe  | recipeInReview                |
      | recipeTechReview | testRecipeWithChar30NameLengt |

  Scenario: Delete step and criteria from recipe
    Given I am logged in as "Bio4CAdmin" user
    When I edit the recipe "criteriaRecipe" from recipe browser
    And I delete the step"3" using shortcut key
    Then I verify step"3" is deleted and message seen "Step cut successfully"
    And I delete the step"2" using cross button
    Then I verify step"2" is deleted and message seen "Step deleted successfully"
    And I delete the "WHEN" criteria using shortcut key
    Then I verify "WHEN" criteria is deleted and message seen "Step cut successfully"
    And I delete the "IF-ELSE" criteria using cross button
    Then I verify "IF-ELSE" criteria is deleted and message seen "criteria deleted successfully"

  Scenario Outline:  Overwriting recipe with different status
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    When I edit recipe "<recipes>"
    And I verify action "Acid pH Control Loop Setpoint" in the step
    And I saveAs the recipe
    And I select "<existing>" recipe to verify the warning text message "This recipe is already exist, Do you want to overwrite?"
    And I verify action "Acid pH Control Loop Setpoint" in the step
    Then I verify the recipe "<status>"
    Examples:
      | recipes                | status      | existing         |
      | recipeTechReview       | Tech-Review | testDraftRecipe  |
      | OverWritingDraftRecipe | Draft       | recipeTechReview |

  Scenario: Default step wait time validation in Recipe Editor
    Given I am logged in as "Bio4CAdmin" user
    When I edit the recipe "criteriaRecipe" from recipe browser
    And  I select "Step Wait Time" button
    Then I verify the default step wait time dialog box
    And I update default wait time
    And I select "Step Wait Time" button
    Then I verify the saved step wait time

