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
   When I click on filter icon and select status "<Enabled>"
   Then I should see user details are displayed
   And I verify filetr tag "<Enabled>"
   
   Scenario Outline: Verify order sort functionality in User Management
    Given I go to user page
    When I select sort by "<columnName>" in "<sortMode>"
    Then User details should be displayed by "<column name>" in "<sort mode>" order

    Examples:
      | columnName | sortMode  |
      | username   | ascending |