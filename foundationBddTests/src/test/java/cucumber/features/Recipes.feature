@COMMON
Feature: Recipe management

  Background:
    Given I am logged in as "Bio4CAdmin" user


  Scenario: Recipe modification
    Given I go to recipe page
    When I edit recipe "testDraftRecipeToAddPhase"
    And I delete phase to recipe
    And I add phase to recipe
    And I save the modified recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe

  Scenario: Recipe approval
    Given I go to recipe page
    And I edit recipe "testDraftRecipeToChangeStatus"
    When I approve recipe
    Then Recipe should be approved
    
  Scenario: BIOCRS-5060-UATC_CRS_Recipe Obsolete Workflow
    Given I go to recipe page
    And I edit recipe "testDraftRecipeToChangeStatus"
    When I approve-inactive recipe
    Then Recipe should be approved-inactive
    And I verify autit trial logs
    
  Scenario: BIOCRS-5060-UATC_CRS_Recipe Reject Workflow
    Given I go to recipe page
    And I edit recipe "testDraftRecipeToChangeStatus"
    When I tech-review recipe
    And Recipe should be tech review
    And I draft recipe
    And Recipe should be draft
    And I verify autit trial logs

  Scenario: Recipe creation
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe
     
  Scenario: Create new recipe with existing Recipe name
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe with name "testRecipeToExecute"
    Then I see warning message is displayed "Recipe is locked. Please save it as new copy."

  Scenario: Recipe export and import
    Given I go to recipe page
    When I click on export recipe "testRecipeToExecute"
    And I trigger edit mode
    And I click on import "testRecipeToExecute"
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications