Feature: User management

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user
    And the user "testUser" exists
    And the user "testUserEnabled" exists
    And the user "testUserDisabled" exists

  Scenario: Create new user
    Given I go to user page
    When I create a random username
    And I select role "Bio4CService"
    And I enter random firstname
    And I enter random lastname
    And I enter random employeeID
    And I enter email "alexis.thiebaut@merckgroup.com"
    And I save my user changes
    And I search the user
    And I edit the user
    Then The username is equal to the expected one

  Scenario: User modification
    Given I go to user page
    When I search "testUser" user
    And I edit the user
    And I change the employee id with a random string
    And I save my user changes
    And I edit the user
    Then the employee id is the expected one

  Scenario: User disable
    Given I go to user page
    When I search "testUserEnabled" user
    And I edit the user
    And I disable the user
    And I save my user changes
    And I edit the user
    Then the user is disabled

  Scenario: User enable
    Given I go to user page
    When I search "testUserDisabled" user
    And I edit the user
    And I enable the user
    And I save my user changes
    And I edit the user
    And the user is enabled
