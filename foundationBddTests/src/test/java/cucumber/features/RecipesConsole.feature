@COMMON
Feature: Recipe console

  Background:
    Given I am logged in as "Bio4CAdmin" user

  @SMOKE
  Scenario: Recipe system Hold/Restart
    When I expand recipe console in pnid
    And I hold and restart the system
    Then I see the system on hold

  @SMOKE
  Scenario: Recipe execution
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 12 seconds
    Then Recipe should be executed

  Scenario: Recipe operational control workflow
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
    And I start recipe execution
    And I click on pause button
    And I click on resume button
    And I click on jump step "2"
    And I click on abort button
    Then I should see the recipe run aborted