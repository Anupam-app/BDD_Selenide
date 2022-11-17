@CRS @IVI @ORCHESTRATOR
Feature: Apply Filter Roles
  

  Scenario: BIOCRS-5493- Verify search functionality in Role Management
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
    And I trigger Roles mode
    Then the role "testRoleToAssign" exists

   Scenario Outline: BIOCRS-5493- Verify order sort functionality in Role Management
    Given I am logged in as "Bio4cAdmin" user
    When I go to user page
	And I trigger Roles mode
    And I select role sort by "<columnName>" in "<descending>"
    Then "<columnName>" from role should be displayed in sorted order "<descending>"

    Examples:
      |columnName  	| descending |
      |Role Name    | true       |
      |Role Name    | false      |
      |Type			| true		 |
      |Type			| false		 |