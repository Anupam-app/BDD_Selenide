@CRS @IVI @ORCHESTRATOR
Feature: Report Template module validations

  JIRAs test:
  https://stljirap.sial.com/browse/BIOFOUND-28944

  Background:
    Given I am logged in as "Bio4CAdmin" user

 #TODO fix the application or the scenario to make it work on headless mode
#  Scenario: BIOCRS-5240| More than 5 trends chart not allowed in report template
#    When I create report template
#    And I select below report include sections
#      | Trends |
#    And I create five trends chart
#    Then I verify that sixth chart is not allowed

  @IVI-6148
  Scenario: IVI bug IVI-6148| Report Management | Incorrect message is seen when Trend name is blank while creating a new template
    When I create report template
    And I select below report include sections
      | Trends |
    And I save Trends without name
    Then I see error message displayed "Trend Name cannot be empty"

  Scenario: Validate create template page
    When I create report template
    Then I verify below options availability
      | Template Name    |
      | Status           |
      | Signature Needed |
      | Report Include   |
      | Cancel           |
      | Save             |
      | Save As          |

  Scenario: Create and approve Report Template
    When I create report template
    And I select below report include sections
      | Audit Trail |
      | Run Summary |
      | Alarms      |
    And I save the report template
    And I search and approve the report template
    And I search the report template
    Then I verify the report template
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I verify audit logs for template update
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
    And I verify the audit trail for template

  Scenario: Create Report Templates With existing Name
    When I create report template
    And I provide the existing template name
    And I select below report include sections
      | Audit Trail |
      | Run Summary |
    And I save the report template
    Then I verify the template name error message

  Scenario: Template Approval E-Sign Failure On Entering Wrong Password
    And I open the report template dashboard
    And I search the report template "testInReviewTemplate"
    When I try to approve the report template with wrong password "abde@39"
    Then I verify the password error message "Incorrect Password"

  Scenario: Approved Report Template Is Not Modifiable
    And I open the report template dashboard
    When I search the report template "testReportTemplate"
    Then I verify template is not editable

  Scenario: Verify template status approved to Inactive
    When I create report template
    And I select below report include sections
      | Audit Trail |
      | Run Summary |
      | Alarms      |
    And I save the report template
    And I search and approve the report template
    And I search the report template
    And I change the template status Approved to Inactive
    And I search the report template
    Then I verify the report template