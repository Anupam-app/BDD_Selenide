Feature: Roles Permissions PDP specific

  Scenario Outline: BIOFOUND-27758 | Verify Default Users & roles
    Given I am logged in as "Bio4CAdmin" user
    When I trigger roles mode
    Then I verify default roles are disabled or enabled
      | Bio4CService    |
      | Administrator   |
      | ProcessManager  |
      | Operator        |
    And I verify "<UserRole>" list of "<roles>"
    #Then I should see view icon of particular roles
    #  | Administrator |
    #  | Bio4CService  |

    @CRS
    Examples:
      | roles                                        | UserRole       |
      | parameters/crs/privilegeslist                | service        |
      | parameters/crs/privilegeslistofAdministrator | admin          |
      | parameters/crs/privilegeslistProcessManager  | processManager |
      | parameters/crs/privilegeslistOperator        | operator       |

    @IVI @IVI-4908
    Examples:
      | roles                                        | UserRole        |
      | parameters/ivi/privilegeslist                | service         |
      | parameters/ivi/privilegeslistofAdministrator | admin           |
      | parameters/ivi/privilegeslistProcessManager  | processManager  |
      | parameters/ivi/privilegeslistOperator        | operator        |