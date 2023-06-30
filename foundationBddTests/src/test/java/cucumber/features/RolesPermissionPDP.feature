Feature: Roles Permissions PDP specific

  JIRAs tested:
  https://stljirap.sial.com/browse/BIOFOUND-27758

  Scenario Outline: Verify Default Roles & their privileges
    Given I am logged in as "Bio4CAdmin" user
    When I trigger roles mode
    Then I verify default roles are disabled or enabled
      | Bio4CService   |
      | Administrator  |
      | ProcessManager |
      | Operator       |
    And I verify "<UserRole>" list of "<roles>"

    @CRS
    Examples:
      | roles                                        | UserRole       |
      | parameters/crs/privilegeslist                | service        |
      | parameters/crs/privilegeslistofAdministrator | admin          |
      | parameters/crs/privilegeslistProcessManager  | processManager |
      | parameters/crs/privilegeslistOperator        | operator       |

    @IVI
    Examples:
      | roles                                        | UserRole       |
      | parameters/ivi/privilegeslist                | service        |
      | parameters/ivi/privilegeslistofAdministrator | admin          |
      | parameters/ivi/privilegeslistProcessManager  | processManager |
      | parameters/ivi/privilegeslistOperator        | operator       |

    @SM
    Examples:
      | roles                                       | UserRole       |
      | parameters/sm/privilegeslist                | service        |
      | parameters/sm/privilegeslistofAdministrator | admin          |
      | parameters/sm/privilegeslistProcessManager  | processManager |
      | parameters/sm/privilegeslistOperator        | operator       |