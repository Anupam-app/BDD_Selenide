Feature: Apply Filter

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user
    

  Scenario: Verify the user preferred homepage is displayed based on the User Preferences
   Given I click on user profile icon
   When I click on user preferences link
   And I select "Analytics" option from drop down
   And I relogin
   Then I should see home page displayed with option
    
  Scenario: Verify search functionality in User Management
   Given I go to user page
   When I search "testUser" user
   Then I should see user details are displayed
   
  Scenario: Verify filter functionality in User Management
   Given I go to user page
   When I click on filter icon and select status "<enabled>"
   Then I should see user details are displayed based on filter
   
   Scenario Outline: Verify order sort functionality in User Management
    Given I go to user page
    When I select sort by "<columnName>" in "<sortMode>"
    Then User details should be displayed by "<column name>" in "<sort mode>" order

    Examples:
      | columnName | sortMode  |
      | username   | ascending |
   

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
   
  Scenario: Verify search functionality in Recipe Browser
   Given I go to recipe page
   When I search "<recipe>"
   Then I should see recipe details are displayed
   
  Scenario: Verify filter functionality in Recipe Browser
   Given I go to recipe page
   When I click on filter icon and select status
   Then I should see recipe details are displayed
   
  Scenario: Verify created by functionality in Recipe Browser
   Given I go to recipe page
   When I select "<created by>" from drop down
   Then I should see recipe details are displayed
   
  Scenario: Verify date range functionality in Recipe Browser
   Given I go to recipe page
   When I select "<date range>" from drop down
   Then I should see recipe details are displayed 

  Scenario Outline: Verify order sort functionality in Recipe Browser
    Given I go to recipe page
    When I select sort by "<columnName>" in "<sortMode>"
    Then Recipe details should be displayed by "<column name>" in "<sort mode>" order

    Examples:
      | columnName   | sortMode  |
      | recipeName   | ascending |