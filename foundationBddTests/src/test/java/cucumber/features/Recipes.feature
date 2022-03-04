Feature: Recipe management

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user


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

  Scenario: Recipe creation
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe

  Scenario: Recipe export and import
    Given I go to recipe page
    When I click on export recipe "testRecipeToExecute"
    And I trigger edit mode
    And I click on import "testRecipeToExecute"
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications