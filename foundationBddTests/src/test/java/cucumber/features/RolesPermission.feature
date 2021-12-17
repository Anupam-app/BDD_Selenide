Feature: Roles Permissions Check

  Background:
    Given the browser "Chrome" is open

  Scenario: Verify user do not have permissions to roles
    Given I am logged in as "testRoleWithoutPerms" user
    When I go to user page
    And I trigger Roles mode
    Then I should not see roles list
    And I verify user do not have permission to create and edit roles