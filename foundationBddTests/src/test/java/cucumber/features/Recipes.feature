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

  Scenario: Recipe Execution
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start recipe execution with project id "testRecipeProjectToExecute"
    Then Recipe should be executed

  Scenario: Recipe creation
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe
    And I go to browser mode
    And I search the recipe
    And I edit the recipe
    Then I see my changes in recipe

  Scenario: Recipe operational control workflow
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
    And I start recipe execution flow with "testRecipeProjectToExecute"
    And I click on pause button
    And I click on resume button
    And I click on jump step "3"
    And I click on abort button
    Then I should see the recipe run aborted

  Scenario: Recipe export
    Given I go to recipe page
    When I click on export recipe "testRecipeToExecutePbAUucdMeT"
    Then I should see "testRecipeToExecutePbAUucdMeT" exported
    
  Scenario: Recipe import
    Given I go to recipe page
    When I trigger edit mode
    And I click on import "testRecipeToExecute"
    Then I should see a notification with successful recipe imported