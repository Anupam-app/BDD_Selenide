Feature: Apply Filter Reports

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user
    
  Scenario: To Verify search functionality in the templates page
   Given I goto report management page
   And I trigger report template mode
   When I search the report template
   Then I verify the report template
   
  Scenario: To Verify filter functionality in the templates page
   Given I goto report management page
   And I trigger report template mode
   When I click on filter icon and select template status "Draft"
   Then I should see template "testtemplate"
   
  Scenario: Verify search reports functionality in Report Management
   Given I goto report management page
   And I trigger report mode
   When I search the report name "testReport"
   Then I should see report "testReport"
   
  Scenario: Verify filter reports functionality in Report Management
   Given I goto report management page
   And I trigger report mode
   When I click on filter icon and select report type "Run Summary"
   Then I should see report "testReport"
   
  Scenario: Verify created by reports functionality in Report Management
   Given I goto report management page
   And I trigger report mode
   When I select from dropdown "Created by"
   Then I should see report "testReport"

  Scenario: Verify signed by reports functionality in Report Management
   Given I goto report management page
   And I trigger report mode
   When I select from dropdown "Signed by"
   Then I should see report "testReport"
   
   Scenario Outline: Verify order sort functionality in Report Management
    Given I goto report management page
    And I trigger report mode
    When I select sort by "<columnName>" in "<sortMode>"
    Then Details should be displayed in sort order

    Examples:
      | columnName  | sortMode  |
      | Report Name | Ascending |
   
  Scenario: Verify search runs reports functionality in Report Management
   Given I goto report management page
   When I search the recipe run "reciperunname"
   Then I should see recipe run "reciperunname"
   
  Scenario: Verify filter runs reports functionality in Report Management
   Given I goto report management page
   When I click on filter icon and select runs status "Operation"
   Then I should see recipe run "reciperunname"
   
  Scenario: Verify the system allows user to filter runs based on report name
   Given I goto report management page
   When I select from dropdown "Audit Trail"
   Then I should see recipe run "reciperunname"
   
  Scenario Outline: Verify order sort functionality for runs in Report Management
    Given I goto report management page
    When I select sort by "<columnName>" in "<sortMode>"
    Then Details should be displayed in sort order

    Examples:
      | columnName | sortMode  |
      | Run        | Ascending |
