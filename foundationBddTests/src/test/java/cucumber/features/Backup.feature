Feature: Backup creation

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "bio4cservice" user
    
  Scenario: Create Backup
    Given I goto backup page
    When I trigger a immediate backup
    And I go to backup history
    And I see backup is triggered
    And I go to backup mode
    And I wait the end of backup
    And I go to backup history
    Then I verify backup history details
    
  Scenario: Schedule backup
    Given I goto backup page
    When I schedule backup
    And I go to backup history
    Then I see backup is triggered
    And I go to backup mode
    And I wait the end of backup
    And I go to backup history
    Then I verify backup history details