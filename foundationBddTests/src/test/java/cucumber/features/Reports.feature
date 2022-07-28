@COMMON
Feature: Report administration

Background:
    Given I am logged in as "Bio4CAdmin" user

  Scenario: Generate and sign Audittrail report
    Given I goto report management page
    When I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Generate and sign a recipe run history report
    Given I goto report management page
    When I select report from dropdown "Run History"
    And I choose recipe run "recipe4sec220211129030358"
    And I choose template "testReportTemplate"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Generate and sign a consolidated report
    Given I goto report management page
    When I select report from dropdown "Consolidated"
    And I choose recipe run "recipe4sec220211129030358" for consolidation
    And I choose recipe run "recipe4sec220211129035111" for consolidation
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Generate and sign a custom report
    Given I goto report management page
    When I select report from dropdown "Custom"
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Create Report Template and approve it
    Given I goto report management page
    And I trigger report template mode
    When I create random report template
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I save the report template
    And I search the report template
    And I put the report template in review
    And I save the report template
    And I search the report template
    And I approve the report template
    And I search the report template
    Then I verify the report template

  Scenario: Generate run history report and check report content
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    And I wait the end of the execution of the recipe
    When I goto report management page
    And I select report from dropdown "Run History"
    And I choose corresponding recipe run
    And I choose template "testReportTemplate"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I check report content

  Scenario: Generate Audittrail report and verify that user information are consistent
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    And I wait the end of the execution of the recipe
    When I goto report management page
    And I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify that user information are consistent

  @hooks  
  Scenario: Report Approval E-Sign Failure On Entering Wrong Password
    Given I goto report management page
    When I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report with wrong password "abcde#23"
    Then I verify the password error message "Incorrect Password"