Feature: User management

  Background:
    Given The browser "Chrome_1024x768" is open
    And I open portal
    When I login as "Bio4CAdmin" user
    Then I am logged in

  Scenario: User modification
    Given I go to user page
    When I search "testUser" user
    And I edit the user
    And I change the employee id with a random string
    And I save my changes
    And I edit the user
    Then the employee id is equal to the string input

  Scenario: User disable and enable
    Given I go to user page
    When I search "testUser" user
    And I edit the user
    And I disable the user
    And I save my changes
    Then I edit the user
    And the user is disabled
    And I enable the user
    And I save my changes
    And I edit the user
    And the user is enabled
