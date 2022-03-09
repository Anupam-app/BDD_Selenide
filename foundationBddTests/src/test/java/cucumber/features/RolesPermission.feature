@Common
Feature: Roles Permissions Check

  Background:
    Given the browser "Chrome" is open

  Scenario: Verify user do not have permissions to roles
    Given I am logged in as "testRoleWithoutPerms" user
    When I go to user page
    Then I do not see Roles mode