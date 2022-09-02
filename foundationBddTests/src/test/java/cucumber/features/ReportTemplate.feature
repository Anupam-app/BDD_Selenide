@COMMON
Feature: Report Template module validations

Background:
    Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
    And I trigger report template mode
    When I create random report template
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I select report include "Alarms"
    And I save the report template
    And I search the report template
    And I put the report template in review
    And I save the report template
  
	Scenario: BIOCRS-5240| Select more than 5 trends parameters
    Given I search the report template
    And I edit the report template
    When I select report include "Trends"
    And I select below parameters 
		|Parameters  |
		|PD1 PV      |
		|PI101 PV    |
		|PI102 PV    |
		|PI103 PV    |
		|TMP1 PV     |
		|TPH1 PV     |
   Then I verify the error message "Maximum of 5 sensors allowed"
 @acc
  Scenario: BIOCRS-5240| More than 5 trends chart not allowed in report template
    Given I search the report template
    And I edit the report template
    When I select report include "Trends"
    And I create five trends chart
   	Then I verify that sixth chart is not allowed
  
  Scenario: Create Report Template and approve it
    Given I search the report template
    When I approve the report template
    And I search the report template
    Then I verify the report template

  Scenario: BIOCRS-5240| Create Two Report Templates With Same Names
    Given I search the report template
    When I approve the report template
    And I search the report template
    And I verify the report template
    And I trigger report template mode
    And I create new report template with existing name
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I save the report template
    Then I verify the template name error message
    
  Scenario: Template Approval E-Sign Failure On Entering Wrong Password
    Given I search the report template
    When I try to approve the report template with wrong password "abde@39"
    Then I verify the password error message "Incorrect Password"
    
  Scenario: Approved Report Template Is Not Modifiable
    Given I search the report template
    When I approve the report template
    And I search the report template
    And I verify the report template
    Then I verify template is not editable  
   