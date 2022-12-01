@CRS @IVI
Feature: Apply Filter Recipes

  Background:
    Given I am logged in as "Bio4CAdmin" user

  Scenario: BIOCRS-2689 BIOCRS-5030 | Verify search functionality in Recipe Browser
    Given I go to recipe page
    When I search recipe "testRecipeToExecute"
    Then I should see recipe "testRecipeToExecute"
    
  Scenario: Bug- IVI-5144 | Verify message in phase library when there is no phase in phase library.
    Given I go to recipe page
    When I trigger edit mode
    And I select phase library
    Then I verify the message "There is No Phase in Phase Library"
    
  Scenario: BIOCRS-2689 BIOCRS-5030 | Verify filter functionality in Recipe Browser
    Given I go to recipe page
    When I click on filter icon and select recipe status "Draft"
    Then I should see recipe "testDraftRecipe"

  Scenario: BIOCRS-2689 BIOCRS-5030| Verify created by functionality in Recipe Browser
    Given I go to recipe page
    When I select recipe created by "Bio4CAdmin"
    Then I should see recipe "testDraftRecipe"

  Scenario Outline: BIOCRS-2689 BIOCRS-5030 | Verify order sort functionality in Recipe Browser
    Given I go to recipe page
    When I select recipe sort by "<columnName>" in "<descending>"
    Then "<columnName>" from recipe should be displayed in sorted order "<descending>"
    And I logout

    Examples:
      |columnName  		| descending |
      |Recipe Name		| true  |
      |Recipe Name		| false |
      |System Family	| true  |
      |System Family	| false |
      |Imported 		| true  |
      |Imported 		| false |
      |Import Status	| true  |
      |Import Status	| false |
      |Created By		| true  |
      |Created By		| false |
      |Last Modified On	| true  |
      |Last Modified On	| false |
      |UOP Status		| true  |
      |UOP Status		| false |
    
  Scenario: BIOCRS-2689 BIOCRS-5030 | Verify filter recipe functionality in Recipe Management Based on status
    Given I go to recipe page
    When I filter based on uop status as "Approved-Active" and Imported as "Yes"
    Then  I see recipe based on uop status as "Approved-Active" and Imported as "Yes"
 
  Scenario: BIOCRS-2689 BIOCRS-5030 |Verify recipefilter functionality based on the Date range.
    Given I go to recipe page 
    Then  I should see recipe list displayed based on date range dropdown
        |Today|
        |Yesterday|
        |Last 7 Days|
        |Last 30 Days|
        |This Month|
        |Last Month|
		|Custom Range|