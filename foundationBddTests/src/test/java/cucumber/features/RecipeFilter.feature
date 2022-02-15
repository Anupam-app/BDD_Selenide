Feature: Apply Filter Recipes

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user

  Scenario: Verify search functionality in Recipe Browser
    Given I go to recipe page
    When I search recipe "testRecipe"
    Then I should see recipe "testRecipe"
   
  Scenario: Verify filter functionality in Recipe Browser
    Given I go to recipe page
    When I click on filter icon and select recipe status "Run Summary"
    Then I should see recipe "testRecipe"
   
  Scenario: Verify created by functionality in Recipe Browser
    Given I go to recipe page
    When I select from dropdown list "Created by"
    Then I should see recipe "testRecipe"
    
  Scenario Outline: Verify order sort functionality in Recipe Browser
    Given I go to recipe page
    When I select sort by "<columnName>" in "<sortMode>"
    Then Details should be displayed in sort order

    Examples:
      | columnName  | sortMode  |
      | Recipe Name | Ascending |
      