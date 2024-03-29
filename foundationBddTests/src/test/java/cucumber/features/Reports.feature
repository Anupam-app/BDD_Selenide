@CRS @IVI @ORCHESTRATOR @REPORT @SM
Feature: Report Management

  Scenario: BIOCRS-5238/5239 | Report Management Dashboard -  Runs Tab
    Given I am logged in as "Bio4CAdmin" user
    When I goto report management page
    Then I see Runs, Templates, Reports tabs are displayed
    And  I see list of "runs" are displayed
    And below "runs" columns are displayed
      | columns      |
      | Run          |
      | Start Date   |
      | Process Type |
      | Status       |

  Scenario: BIOCRS-5238/5239 | Report Management Dashboard -  Templates Tab
    Given I am logged in as "Bio4CAdmin" user
    When I goto report management page
    And I trigger report template mode
    Then  I see list of "templates" are displayed
    And below "templates" columns are displayed
      | columns          |
      | Template Name    |
      | Status           |
      | Last Modified By |
      | Last Modified On |

  Scenario: BIOCRS-5238/5239/5241 | Report Management Dashboard -  Reports Tab
    Given I am logged in as "Bio4CAdmin" user
    When I goto report management page
    And I trigger report mode
    Then  I see list of "reports" are displayed
    And below "reports" columns are displayed
      | columns        |
      | Report Name    |
      | Date Generated |
      | Created By     |
      | Report Type    |
      | E-Sign.Status  |
      | Signed By      |

  @SMOKE
  Scenario: BIOCRS-5106/592 Generate and sign Audittrail report
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "testadmin"
    And I select date range as "Last 7 Days"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence
    And I check audit trial report content

  @SMOKE
  Scenario: Generate and sign a recipe run history report
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Run History"
    And I choose recipe run "recipe4sec220211129030358"
    And I choose template "testReportTemplate"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  @SMOKE @IVI-7601
  Scenario: Generate and sign a consolidated report
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Consolidated"
    And I choose recipe run "recipe4sec220211129030358" for consolidation
    And I choose recipe run "recipe4sec220211129035111" for consolidation
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  @SMOKE
  Scenario: Generate and sign a custom report
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I select device "BEDTFF13"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  @IVI-7601
  Scenario: Generate run history report and check report content
    Given I am logged in as "testadmin" user
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
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
    Given I am logged in as "testadmin" user
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    When I goto report management page
    And I select report from dropdown "Audit Trail"
    And I select user in dropdown "testadmin"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify that user information are consistent

  @IVI-7869
  Scenario: Report Approval E-Sign Failure On Entering Wrong Password
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I eSign the report with wrong password "abcde#23"
    Then I verify the password error message "Incorrect Password"

  @IVI-7601
  Scenario: BIOCRS-5818 |Generate a consolidated report with same batch Id
    Given I am logged in as "Bio4CAdmin" user
    And I load recipe "testRecipeToExecute" and run it during 10 seconds with batch id "testBatchId" and product id "testProductId"
    And I load recipe "testRecipeToExecute" and run it during 10 seconds with batch id "testBatchId" and product id "testProductId"
    When I goto report management page
    And I wait for recipes in runs
    And I select report from dropdown "Consolidated"
    And I choose recipes from consolidation run
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify consolidate summary report

  @IVI-7601
  Scenario: BIOCRS-5818 |Generate a consolidated report with different batch Id
    Given I am logged in as "Bio4CAdmin" user
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    When I goto report management page
    And I wait for recipes in runs
    And I select report from dropdown "Consolidated"
    And I choose recipes from consolidation run
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify consolidate summary report

  @IVI-7601
  Scenario: Verify Create Custom Template
    Given I am logged in as "Bio4CAdmin" user
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    When I goto report management page
    And I select report from dropdown "Run History"
    And I choose corresponding recipe run
    And I choose template "testReportTemplate"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify run summary report report

  Scenario: Report Management | Recipe Steps summary section should not be available in consolidated report for Manual run
    Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
    When I select report from dropdown "Consolidated"
    And I choose recipe run "testManualRun1" for consolidation
    And I choose recipe run "testManualRun2" for consolidation
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify consolidate manual run summary report

  Scenario Outline: Generate custom report for Audit Trail Section based on dates
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    And I select date range as "<DateRange>"
    And I check the row count in DB for "AuditTrail" "<DateRange>"
    And I select report include "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify custom summary report for "Audit Trail"

    Examples:
      | DateRange    |
      | Today        |
      | Yesterday    |
      | Last 7 Days  |
      | Last 30 Days |
      | This Month   |
      | Last Month   |
      | Custom range |

  Scenario: Validate custom report page contents
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    Then I verify below options availability
      | Report Include  |
      | Generate Button |
      | Date Range      |
      | Alarm Eye Icon  |
      | Trends Eye Icon |

  Scenario Outline: Generate custom report for Event Summary Section based on dates
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    And I select date range as "<DateRange>"
    And I check the row count in DB for "EventSummary" "<DateRange>"
    And I select report include "Event Summary"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify custom summary report for "Event Summary"

    Examples:
      | DateRange    |
      | Today        |
      | Yesterday    |
      | Last 7 Days  |
      | Last 30 Days |
      | This Month   |
      | Last Month   |
      | Custom range |

  Scenario Outline: Generate custom report for Run Summary Section based on dates
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    And I select date range as "<DateRange>"
    And I check the row count in DB for "RunSummary" "<DateRange>"
    And I select report include "Run Summary"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify custom summary report for "Recipe Steps Summary"

    Examples:
      | DateRange    |
      | Today        |
      | Yesterday    |
      | Last 7 Days  |
      | Last 30 Days |
      | This Month   |
      | Last Month   |
      | Custom range |

  Scenario Outline: Generate custom report for ProcessAlarm Summary Section based on dates
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    And I select date range as "<DateRange>"
    And I check the row count in DB for "ProcessAlarms" "<DateRange>"
    And I select report include "ProcessAlarms"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify custom summary report for "Process Alarms"

    Examples:
      | DateRange    |
      | Today        |
      | Yesterday    |
      | Last 7 Days  |
      | Last 30 Days |
      | This Month   |
      | Last Month   |
      | Custom range |

  Scenario Outline: Generate custom report for System Alarm Summary Section based on dates
    Given I am logged in as "testadmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    And I select date range as "<DateRange>"
    And I check the row count in DB for "SystemAlarms" "<DateRange>"
    And I select report include "SystemAlarms"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify custom summary report for "System Alarms"

    Examples:
      | DateRange    |
      | Today        |
      | Yesterday    |
      | Last 7 Days  |
      | Last 30 Days |
      | This Month   |
      | Last Month   |
      | Custom range |
