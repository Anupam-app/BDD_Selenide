Feature: Apply Filter Recipes

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user

  Scenario: Verify search functionality in Recipe Browser
    Given I go to recipe page
    When I search recipe "testRecipeFlows"
    Then I should see recipe "testRecipeFlows"
   
  Scenario: Verify filter functionality in Recipe Browser
    Given I go to recipe page
    When I click on filter icon and select recipe status "Run Summary"
    Then I should see recipe "testRecipeFlows"
   
  Scenario: Verify created by functionality in Recipe Browser
    Given I go to recipe page
    When I select from dropdown list "Created by"
    Then I should see recipe "testRecipeFlows"
    
  Scenario Outline: Verify order sort functionality in Recipe Browser
    Given I go to recipe page
    When I select recipe sort by "<columnName>" in "<ascending>"
    Then "<columnName>" from recipe should be displayed in sorted order "<ascending>"

    Examples:
      | columnName  | ascending  |
      | Recipe Name | true       |
      | Recipe Name | false      |
