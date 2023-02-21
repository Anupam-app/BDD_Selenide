@CRS @IVI
Feature: Analytics creation

  @SMOKE
  Scenario: Aggregate creation
    Given I am logged in as "bio4cadmin" user
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    When I create an analytics aggregate
    And I use the recipe for this analytics aggregate with interval "Second"
    And I choose "PI101 PV" analytics parameter with unit "psi" as "x" axis
    And I choose "PI102 PV" analytics parameter with unit "psi" as "y" axis
    And I choose "PI103 PV" analytics parameter with unit "psi" as "y" axis
    And I validate the analytics creation
    Then I see my changes in analytics aggregate

  Scenario: BIOCRS-5969 Aggregate creation while recipe run is in progress
    Given I am logged in as "bio4cadmin" user
    And I load recipe "testRecipeToVerifyAnalytics" and pause it during 10 seconds
    When I create an analytics aggregate
    And I use the recipe for this analytics aggregate with interval "Second"
    And I choose "PI101 PV" analytics parameter with unit "psi" as "x" axis
    And I choose "PI102 PV" analytics parameter with unit "psi" as "y" axis
    And I validate the analytics creation
    Then I verify analytics status "In Progress",BatchID, RunID, Timestamp
    And I go to Main screen
    And I expand recipe console
    And I click on resume button
    And Recipe should be executed
    And I go to analytics
    Then I verify analytics status "Completed",BatchID, RunID, Timestamp

  Scenario: BIOCRS-5486 | PFSTC_CRS_Verify Layout in Analytics Module
    Given I am logged in as "bio4cadmin" user
    When I go to analytics
    Then I verify presence of below options in Left Panel
      | expandButton        |
      | Create an Aggregate |
      | My Aggregates       |
    And I verify the message in Right Panel "No selection has been made."

  Scenario: BIOCRS-5486 BIOCRS-5507 | PFSTC_CRS_Verify Layout in Analytics Module right panel
    Given I am logged in as "bio4cadmin" user
    When I go to analytics
    And I choose aggregate "testAggregate"
    Then I verify presence of below options in Left Panel
      | Plus Button                                   |
      | aggregate tab                                 |
      | Data                                          |
      | Graph                                         |
      | Aggregated interval                           |
      | Created date timestamp                        |
      | Batch ID                                      |
      | Run ID                                        |
      | Status                                        |
      | Relational Charts with expand/collapse option |
      | Regression Charts with expand/collapse option |
      | Delete                                        |
    

