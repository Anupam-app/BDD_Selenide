@CRS @IVI @ORCHESTRATOR @SM
Feature: Roles Permissions Check

  JIRAs test:
  https://stljirap.sial.com/browse/BIOFOUND-27763
  https://stljirap.sial.com/browse/BIOFOUND-27762
  https://stljirap.sial.com/browse/BIOCRS-5145
  https://stljirap.sial.com/browse/BIOFOUND-29957

  Scenario: Verify user do not have permissions to roles
    Given I am logged in as "testRoleWithoutPerms" user
    When I go to user page
    Then I do not see Roles mode

  Scenario: Disable/Enable Custom role
    Given I am logged in as "Bio4CAdmin" user
    When I trigger roles mode
    Then I verify "CustomRoleDisable" role is "disabled"
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    Then I verify custom role "disabled" details captured in audit trail for user "Bio4CAdmin"
    When I logout and login as "userRoleDisable" and password as "MerckApp1@"
    Then I verify error message "Unauthorized access, Failed to authenticate"
    And I enter "Bio4CAdmin" as username and "Merck@dmin" as password
    And I push the login button
    When I trigger roles mode
    Then I verify "CustomRoleDisable" role is "enabled"
    And I logout and login as "userRoleDisable" and password as "MerckApp1@"
    And I am logged in
    And I logout and login as "Bio4CAdmin" and password as "Merck@dmin"
    And I generate audit trail report
    Then I verify custom role "enabled" details captured in audit trail for user "Bio4CAdmin"
    And I check the audit trail report
    And I see the role disabling enabling events in report

  Scenario: Modify custom role-Privileges
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "customRoleEdit" user to validate role "editCustomRole" assigned
    And I trigger Roles mode
    And I search and edit role "editCustomRole"
    And I modify permission
      | removePermission | AddPermission      |
      | Create User      | Create Role        |
      | Basic Process    | View Present Alarm |
    And I save role successfully
    And I search the role
    Then I verify Role permission are updated
    And I generate audit trail report
    And I verify custom role modification-"permissions" details captured in audit trail for user

  Scenario: Modify custom role - Name
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "customRoleEdit" user to validate role "editCustomRole" assigned
    And I trigger Roles mode
    And I search and edit role "editCustomRole"
    And I update roleName as "modifiedCustomRole"
    And I save role successfully
    Then I see modified role name is displayed on Role list data
    And I trigger Users mode
    And I search "customRoleEdit" user to validate role "modifiedCustomRole" assigned
    And I generate audit trail report
    And I verify custom role modification-"roleName" details captured in audit trail for user

  Scenario Outline: Verify Permission check for Recipe
    Given I am logged in as "Bio4CAdmin" user
    And I trigger roles mode
    And I update the role "testRoleForPermissions" with Permission "<Permission>"
    And I logout and login as "UserForPermissions" and password as "MerckApp1@"
    When I go to recipe page
    Then I verify recipe "<Permission>" permission
    And I logout and login as "Bio4CAdmin" and password as "Merck@dmin"
    And I verify audit logs for recipe with permission "<Permission>" & "UserForPermissions"

    Examples:
      |Permission|
      |View Recipe|
      |Create Recipe|
      |Edit Recipe  |
      |Approve Recipe|

  Scenario: Verify unauthorized user is not able to view the roles list
    Given I am logged in as "noViewRoleUser" user
    And I go to user page
    And I verify unauthorized user cannot view role
