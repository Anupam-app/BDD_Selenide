@CRS @IVI @ORCHESTRATOR
Feature: Roles Permissions Check

  Scenario: Verify user do not have permissions to roles
    Given I am logged in as "testRoleWithoutPerms" user
    When I go to user page
    Then I do not see Roles mode

  @IVI-4908
  Scenario Outline: BIOFOUND-27758 | Verify Default Users & roles
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I trigger Roles mode
    Then I verify default roles
      | Bio4C Service   |
      | Administrator   |
      | ProcessManager  |
      | Operator        |
    And I verify default roles are disabled or enabled
    And I verify "<UserRole>" list of "<roles>"
    #TO-DO : skipping the test step until IVI-4908 is fixed
    #Then I should see view icon of particular roles
      #| Administrator |
      #| Bio4CService  |
    @CRS
    Examples:
      | roles                                        | UserRole       |
      | parameters/crs/privilegeslist                | service        |
      | parameters/crs/privilegeslistofAdministrator | admin          |
      | parameters/crs/privilegeslistProcessManager  | proceessManager|
      | parameters/crs/privilegeslistOperator        | operator       |

    @IVI
    Examples:
      | roles                                        | UserRole        |
      | parameters/ivi/privilegeslist                | service         |
      | parameters/ivi/privilegeslistofAdministrator | admin           |
      | parameters/ivi/privilegeslistProcessManager  | proceessManager |
      | parameters/ivi/privilegeslistOperator        | operator        |

  Scenario: BIOFOUND-27763 | Disable Custom role and User has no privilege to login
    Given I am logged in as "Bio4CAdmin" user
    When I go to user page
    And I trigger Roles mode
    And I search "CustomRoleDisable" role
    Then I verify role is "disabled"
    And I goto report management page
    And I select report from dropdown "Audit Trail"
    And I verify custom role disabled details captured in audit trail for user "Bio4CAdmin"
    And I logout and login as "userRoleDisable" and password as "MerckApp1@"
    And I verify error message "Unauthorized access, Failed to authenticate"
    And I am logged in as "Bio4CAdmin" user
    When I go to user page
    And I trigger Roles mode
    And I search "CustomRoleDisable" role
    Then I verify role is "enabled"
    And I logout and login as "userRoleDisable" and password as "MerckApp1@"
    And I am logged in

  Scenario: BIOFOUND-27762 | Modify custom role-Privileges and Name
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