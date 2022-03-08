Feature: Apply Filter Recipes

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user

  Scenario: Verify search functionality in Recipe Browser
    Given I go to recipe page
    When I search recipe "testRecipeToExecute"
    Then I should see recipe "testRecipeToExecute"
    
  Scenario: Verify filter functionality in Recipe Browser
    Given I go to recipe page
    When I click on filter icon and select recipe status "Draft"
    Then I should see recipe "testDraftRecipe"

  Scenario: Verify created by functionality in Recipe Browser
    Given I go to recipe page
    When I select recipe created by "Bio4CAdmin"
    Then I should see recipe "testDraftRecipe"

  Scenario Outline: Verify order sort functionality in Recipe Browser
    Given I go to recipe page
    When I select recipe sort by "<columnName>" in "<descending>"
    Then "<columnName>" from recipe should be displayed in sorted order "<descending>"

    Examples:
      | columnName  | descending  |
      | Recipe Name | true       |
      | Recipe Name | false      |
