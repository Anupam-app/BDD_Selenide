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
      
  Scenario Outline: Verify Permission check for Trends
    Given I am logged in as "Bio4CAdmin" user
    And I trigger roles mode
    And I update the role "testRoleForPermissions" with Permission "View Trend"
    And I logout and login as "UserForPermissions" and password as "MerckApp1@"
    When I navigate to trends page
    And I choose "<Param1>","<Param2>" parameters as default collection
    And I save as trends collections
    And I choose collection
    Then I see "<Param1>","<Param2>" parameters displayed

    @CRS
    Examples:
      | Param1   | Param2   |
      | PI101 PV | PI102 PV |

    @IVI
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |   
