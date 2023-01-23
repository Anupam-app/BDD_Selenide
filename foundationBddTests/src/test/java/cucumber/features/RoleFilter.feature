@CRS @IVI @ORCHESTRATOR
Feature: Apply Filter Roles

  Scenario: BIOCRS-5493- Verify search functionality in Role Management
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
    And I trigger Roles mode
    Then the role "testRoleToAssign" exists

  @IVI-5190
  Scenario: IVI Bug IVI-5190 -  User Management | Tool tip is not displayed to view full text when role name has lengthy text
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
    And I trigger Roles mode
    Then the role "testRoleToRemovePermission" exists
    And tooltip shows the role name as "testRoleToRemovePermission"

  @IVI-5871
  Scenario Outline: IVI Bug IVI-5871 | BIOCRS-5493- Verify order sort functionality in Role Management
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
    And I trigger Roles mode
    And I select role sort by "<columnName>" in "<descending>"
    Then "<columnName>" from role should be displayed in sorted order "<descending>"

    Examples:
      | columnName | descending |
      | Role Name  | true       |
      | Role Name  | false      |
      | Type       | true       |
      | Type       | false      |