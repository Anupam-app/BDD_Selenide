@CRS @IVI
Feature: Reports Filter

  Background:
    Given I am logged in as "Bio4CAdmin" user

  Scenario: Verify audit Trail search based on user and date range
    Given I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "Bio4CAdmin"
    And I select date range as "Last 7 Days"
    Then I see Audit logs are displayed for date range 7 and "bio4cadmin" for at least 10 results

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
    When I search the report name "AuditTrail_1_Bio4CAdmin"
    Then I should see report "AuditTrail_1_Bio4CAdmin"

  Scenario: Verify filter reports functionality in Report Management
    Given I goto report management page
    And I trigger report mode
    When I click on filter icon and select report type "Run Summary"
    Then I should see report "RunSummary_18_Bio4CAdmin"

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

  Scenario: Verify filter run reports functionality in Report Management Based on status
    Given I goto report management page
    When  I filter on icon and select run status as "Completed"
    Then  I should see run status as "Completed"
    When  I filter on icon and select run status as "Aborted"
    Then  I should see run status as "Aborted"

  Scenario: Verify run history functionality based on the Date range.
    Given I goto report management page
    Then  I should see recipe run list displayed based on date range dropdown
      | Today        |
      | Yesterday    |
      | Last 7 Days  |
      | Last 30 Days |
      | This Month   |
      | Last Month   |
      | Custom Range |

  Scenario: Verify sort run functionality all columns in ascending order
    Given I goto report management page
    Then  Report columns should be sorted in ascending order
      | Run          |
      | Start Date   |
      | Process Type |
      | Status       |
    Then  Report columns should be sorted in descending order
      | Run          |
      | Start Date   |
      | Process Type |
      | Status       |

  Scenario: Verify report history functionality based on the Date range.
    Given I goto report management page
    And   I trigger report mode
    Then  I should see recipe report list displayed based on date range dropdown
      | Today        |
      | Yesterday    |
      | Last 7 Days  |
      | Last 30 Days |
      | This Month   |
      | Last Month   |
      | Custom Range |

  Scenario: Verify sort reports functionality all columns in ascending order
    Given I goto report management page
    And   I trigger report mode
    Then  Reports columns should be sorted in ascending order
      | Report Name    |
      | Date Generated |
      | Created By     |
      | Report Type    |
      | E-Sign.Status  |
      | Signed By      |
    Then  Reports columns should be sorted in descending order
      | Report Name    |
      | Date Generated |
      | Created By     |
      | Report Type    |
      | E-Sign.Status  |
      | Signed By      |

  Scenario: Verify consolidated reports functionality  all columns in sorting order
    Given I goto report management page
    When I select report from dropdown "Consolidated"
    Then I verify consolidated columns and columns should be sorted
      | Run          |
      | Batch ID     |
      | Start Date   |
      | Process Type |
      | Status       |

  Scenario: Verify filter consolidated reports functionality in Report Management Based on status
    Given I goto report management page
    When I select report from dropdown "Consolidated"
    And  I filter on icon and select run status as "Operation"
    Then  I should see consolidated status as "Operation"

  Scenario Outline: Verify sort template functionality in ascending order
    Given I goto report management page
    And I trigger report template mode
    When  I select template sort by "<columns>" in "<descending>"
    Then "<columns>" list should be sorted in "<descending>" order
    Examples:
      | columns          | descending |
      | Last Modified By | false      |
      | Last Modified On | true       |
      | Last Modified By | true       |
      | Last Modified On | false      |

  Scenario: IVI Bug IVI-6034| Verify filter reports functionality in Report Management based on signed status
    Given I goto report management page
    And I trigger report mode
    When I click on filter icon and select eSignStatus "Not Signed"
    Then I should see reports with eSignStatus "Not Signed"
