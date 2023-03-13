@CRS @IVI @ORCHESTRATOR
Feature: Backup creation

  @SMOKE
  Scenario: BIOCRS-5113 BIOCRS-5473| Create Backup
    Given I am logged in as "bio4cadmin" user
    And I goto backup page
    When I trigger a immediate backup
    And I go to backup history
    And I see backup is triggered
    And I go to backup mode
    And I wait the end of backup
    And I go to backup history
    Then I verify backup history details
    And I verify backup in restore tab

  @BIOCRS-9281
  Scenario Outline: BIOCRS-5113 BIOCRS-5473 | Schedule backup
    Given I am logged in as "bio4cadmin" user
    And I goto backup page
    When I schedule backup "<occurrence>"
    And I see the notification message "Backup job scheduled"
    And I verify scheduled backup details for "<occurrence>"
    And I go to backup history
    Then I see backup scheduled is triggered
    And I wait the end of scheduled backup
    And I verify backup history details
    And I verify backup in restore tab
    And I go to backup mode
    When I schedule backup with existing name "<occurrence>"
    Then I see the notification message "Unable to schedule backup job"

    Examples:
      | occurrence |
      | Daily      |
      | Weekly     |
      | Monthly    |

  Scenario: BIOCRS-5113| Unauthorized user cant create backup
    Given I am logged in as "reportUnauthUser" user
    When I goto backup page
    Then I am not able to trigger a backup

  Scenario: BIOCRS-604| UATC_CRS_Verify the backup Operations when Recipe execution is in Progress
    Given I am logged in as "bio4cadmin" user
    And I expand recipe console in pnid
    When I load recipe "testRecipeForBackUp"
    And I start recipe execution
    And I goto backup page
    And I trigger a immediate backup
    And I go to backup history
    And I see backup is triggered
    And I go to backup mode
    And I wait the end of backup
    And I go to backup history
    And I verify backup history details
    And I go to Main screen
    And I expand recipe console in pnid
    And I click on abort button
    Then I should see the recipe run aborted
    And control should be on rerun button
    And I goto report management page
    And I select report from dropdown "Run History"
    And I choose corresponding recipe run

  Scenario: BIOCRS-5473| Backup Dashboard - History tab
    Given I am logged in as "bio4cadmin" user
    When I goto backup page
    And I go to backup history
    And I verify backup history tab

  Scenario: BIOCRS-5473| Backup Dashboard - Restore tab
    Given I am logged in as "bio4cadmin" user
    When I goto backup page
    And I go to backup restore
    And I verify backup restore tab
