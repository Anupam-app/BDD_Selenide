@Common
Feature: Apply Filter Users

  Background:
    Given the browser "Chrome" is open

  Scenario Outline: Verify the user preferred homepage is displayed based on the User Preferences
    Given I am logged in as "testUserPref" user
    When I click on user profile icon
    And I click on user preferences link
    And I select default page "<userPref>"
    And I logout
    And I relogin
    Then I should see home page displayed with option "<userPref>"
    
    Examples:
      | userPref |
      | Recipes  |

  Scenario: Verify search functionality in User Management
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
    And I search "testUser" user
    Then the user "testUser" exists

  Scenario Outline: Verify filter enabled functionality in User Management
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
    And I click on filter icon and select status "<status>"
    And I search "<userName>" user
    Then I should see the status "<status>" and user "<userName>" displayed

    Examples:
      |userName         | status   |
      |testUser         | Enabled  |
      |testUserDisabledFilter  | Disabled |

   Scenario Outline: Verify order sort functionality in User Management
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
    And I select user sort by "<columnName>" in "<descending>"
    Then "<columnName>" from user should be displayed in sorted order "<descending>"

    Examples:
      | columnName  | descending |
      | Username    | true       |
      | Username    | false      |