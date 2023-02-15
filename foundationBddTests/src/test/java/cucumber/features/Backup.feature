@CRS @IVI
Feature: Backup creation

  Background:
    Given I am logged in as "bio4cadmin" user

  @SMOKE @IVI-4722
  Scenario: Create Backup
    Given I goto backup page
    When I trigger a immediate backup
    And I go to backup history
    #And I see backup is triggered
    And I go to backup mode
    And I wait the end of backup
    #And I go to backup history
    #Then I verify backup history details

  @IVI-6202 @IVI-4722
  Scenario: Schedule backup
    Given I goto backup page
    When I schedule backup
    And I see the notification message "Backup job scheduled"
    And I go to backup history
    #Then I see backup scheduled is triggered
    And I wait the end of scheduled backup
    And I verify backup history details
    And I go to backup mode
    When I schedule backup with existing name
    Then I see the notification message