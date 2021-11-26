Feature: Report administration

Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user

  Scenario: Generate and sign Audittrail report
    Given I goto report management page
    When I select from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I see the report signed
    And I view the report
    And I check the report presence

  Scenario: Create Report Template and approve it
    Given I goto report management page
    And I trigger report template mode
    When I create random report template
    And I select report include "Pre Run Data"
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
