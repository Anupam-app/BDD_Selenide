@CRS @IVI @ORCHESTRATOR
Feature: Role administration

  Background:
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    And the user "testUserToAssignRole" exists
    And I trigger Roles mode
    And the role "testRoleToRemovePermission" exists
    And the role "testRoleToAssign" exists
    And I trigger Users mode

  Scenario: IVI Bug | IVI-4491 | Adding role and permissions
    Given I trigger Roles mode
    When I create random role
    And I assign permission "Basic navigation"
    And I assign permission "View User"
    And I click on save button
    And I see notification
    And I search the role
    And I edit the role
    Then I verify role details
    And I generate audit trail report
    And I verify audit logs for role update
    And I check the audit trail report
    And I see the role added in report

  Scenario: Modifying role and permissions
    Given I trigger Roles mode
    When I edit role "testRoleToRemovePermission"
    And I remove permission "Basic navigation"
    And I click on save button
    And I see notification
    And I search the role
    And I edit the role
    Then I verify role details

  @IVI-5671
  Scenario: IVI Bug IVI-5671| Assigning role to user
    Given I search "testUserToAssignRole" user
    When I edit the user
    And I assign "testRoleToAssign" to user
    And I save my user changes
    And I go to user page
    And I search the user
    And I edit the user
    Then I verify user details

  Scenario: Same role name cannot be created
    Given I trigger Roles mode
    When I create role "testRoleToRemovePermission"
    And I assign permission "Basic navigation"
    And I assign permission "View User"
    And I click on save button
    Then I see the error message of role "testRoleToRemovePermission"

  Scenario: IVI Bug | IVI-6138 | Obsolete Role
    Given I trigger Roles mode
    When I create random role
    And I assign permission "Basic navigation"
    And I assign permission "View User"
    And I click on save button
    And I see notification
    And I search the role
    And I delete the role
    And I generate audit trail report
    Then I verify audit logs for role update
    And I check the audit trail report
    And I see the role deleted in report
