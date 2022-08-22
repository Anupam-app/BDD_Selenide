@COMMON
Feature: Apply Filter Reports

  Background:
    Given I am logged in as "Bio4CAdmin" user

  Scenario: Verify search functionality in the templates page
    Given I goto report management page
    And I trigger report template mode
    When I search the report template "testReportTemplate"
    Then I should see template "testReportTemplate"

  Scenario: Verify filter functionality in the templates page
    Given I goto report management page
    And I trigger report template mode
    When I click on filter icon and select template status "Approved"
    Then I should see template "testReportTemplate"

  Scenario: Verify search reports functionality in Report Management
    Given I goto report management page
    And I trigger report mode
    When I search the report name "AuditTrail_1"
    Then I should see report "AuditTrail_1"

  Scenario: Verify filter reports functionality in Report Management
    Given I goto report management page
    And I trigger report mode
    When I click on filter icon and select report type "Run Summary"
    Then I should see report "RunSummary_18_Bio4CAdmin"
@acc	
  Scenario: Verify created by reports functionality in Report Management
    Given I goto report management page
    And I trigger report mode
    When I select report user from dropdown created by "Bio4CAdmin"
    Then I should see report "AuditTrail_1_Bio4CAdmin"

  Scenario: Verify signed by reports functionality in Report Management
    Given I goto report management page
    And I trigger report mode
    When I select report user from dropdown signed by "Bio4CAdmin"
    Then I should see report "RunSummary_18_Bio4CAdmin"

  Scenario: Verify search runs reports functionality in Report Management
    Given I goto report management page
    When I search the recipe run "recipe4sec220211129035111"
    Then I should see recipe run "recipe4sec220211129035111"

  Scenario: Verify filter runs reports functionality in Report Management
    Given I goto report management page
    When I click on filter icon and select runs status "Operation"
    Then I should see recipe run "recipe4sec220211129035111"
   
  Scenario: Verify the system allows user to filter runs based on report name
    Given I goto report management page
    When I select report from dropdown "Consolidated"
    Then I should see recipe run "recipe4sec220211129035111" from consolidated report
