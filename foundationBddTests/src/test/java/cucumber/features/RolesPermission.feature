@CRS @IVI @ORCHESTRATOR
Feature: Roles Permissions Check

  Scenario: Verify user do not have permissions to roles
    Given I am logged in as "testRoleWithoutPerms" user
    When I go to user page
    Then I do not see Roles mode

  Scenario: BIOFOUND-27763 | Disable Custom role and User has no privilege to login
    Given I am logged in as "Bio4CAdmin" user
    When I trigger roles mode
    And I verify "CustomRoleDisable" role is "disabled"
    And I goto report management page
    And I select report from dropdown "Audit Trail"
    And I verify custom role disabled details captured in audit trail for user "Bio4CAdmin"
    And I logout and login as "userRoleDisable" and password as "MerckApp1@"
    And I verify error message "Unauthorized access, Failed to authenticate"
    And I am logged in as "Bio4CAdmin" user
    When I trigger roles mode
    And I verify "CustomRoleDisable" role is "enabled"
    And I logout and login as "userRoleDisable" and password as "MerckApp1@"
    And I am logged in

  Scenario: BIOCRS-5145 | BIOFOUND-27762 | Modify custom role-Privileges and Name
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "customRoleEdit" to validate role "editCustomRole" assigned
    And I trigger Roles mode
    And I search and edit role "editCustomRole"
    And I update roleName as "modifiedCustomRole"
    And I modify permission
      | removePermission | AddPermission       |
      | Create User      | Create Role         |
      | Basic Process    | View Present Alarm  |
    And I save role successfully
    Then I see modified role name is displayed on Role list data
    And I verify Role permission are updated
    And I trigger Users mode
    And I search "customRoleEdit" to validate role "modifiedCustomRole" assigned
    And I generate audit trail report
    And I verify custom role permissions details captured in audit trail for user