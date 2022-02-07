Feature: Apply Filter

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user
    

  Scenario: To Verify search functionality in the templates page
   Given I goto report management page
   And I trigger report template mode	
   When I search "<template name>"
   Then I should see template details are displayed
   
  Scenario: To Verify filter functionality in the templates page
   Given I goto report management page
   And I trigger report template mode	
   When I click on filter icon and select status "<draft>"
   Then I should see template details are displayed
   
  Scenario: Verify search reports functionality in Report Management
   Given I load report management page
   And I navigate to report tab
   When I search the report name
   Then report details should be displayed
   
  Scenario: Verify filter reports functionality in Report Management
   Given I load report management page
   And I navigate to report tab
   When I click on filter icon and select status
   Then report details should be displayed based on filter
   
  Scenario: Verify created by reports functionality in Report Management
   Given I load report management page
   And I navigate to report tab
   When I select "<created by>" from drop down
   Then report details should be displayed based on "<created by>"

  Scenario: Verify signed by reports functionality in Report Management
   Given I load report management page
   And I navigate to report tab
   When I select "<signed by>" from drop down
   Then report details should be displayed based on "<signed by>"
   
   Scenario Outline: Verify order sort functionality in Report Management
    Given I load report management page
    And I navigate to report tab
    When I select sort by "<columnName>" in "<sortMode>"
    Then Report details should be displayed by "<column name>" in "<sort mode>" order

    Examples:
      | columnName   | sortMode  |
      | reportName   | ascending |
   
  Scenario: Verify search runs reports functionality in Report Management
   Given I load report management page
   When I search the "<recipe run name>"
   Then Run details should be displayed
   
  Scenario: Verify filter runs reports functionality in Report Management
   Given I load report management page
   When I click on filter icon and select status
   Then Run report details should be displayed based on filter
   
  Scenario: Verify the system allows user to filter runs based on report name
   Given I load report management page
   When I select "<report name>" from drop down
   Then Run report details should be displayed based on filter
   
  Scenario: Verify the system allows user to filter runs based on date range
   Given I load report management page
   When I select "<date range>" from drop down
   Then Run report details should be displayed based on filter
   
  Scenario Outline: Verify order sort functionality for runs in Report Management
    Given I load report management page
    When I select sort by "<columnName>" in "<sortMode>"
    Then Run report details should be displayed by "<column name>" in "<sort mode>" order

    Examples:
      | columnName | sortMode  |
      | run        | ascending |
