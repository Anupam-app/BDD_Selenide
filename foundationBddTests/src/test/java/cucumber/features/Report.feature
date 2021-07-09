Feature: Report generation

  Background:
    Given The browser "Chrome_1024x768" is open
    And I open portal
    And I login with good credentials
    Then I am logged in

  Scenario: Report generated
    Given I open portal
    When I generate Audit trail report
    Then report should be generated with success message confirmation
    And should be present in reports tab


