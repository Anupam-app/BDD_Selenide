Feature: Analytics creation

  @SMOKE @IVI-7594 @IVI-7601
  Scenario Outline: Aggregate creation
    Given I am logged in as "bio4cadmin" user
    And I load recipe "testRecipeToExecute" and run it during 15 seconds
    When I create an analytics aggregate
    And I use the recipe for this analytics aggregate with interval "Second"
    And I choose "<Param1>" analytics parameter with unit "<Unit1>" as "x" axis
    And I choose "<Param2>" analytics parameter with unit "<Unit2>" as "y" axis
    And I choose "<Param3>" analytics parameter with unit "<Unit3>" as "y" axis
    And I validate the analytics creation
    Then I see my changes in analytics aggregate

    @CRS
    Examples:
      | Param1   | Unit1 | Param2   | Unit2 | Param3   | Unit3 |
      | PI101 PV | psi   | PI102 PV | psi   | PI103 PV | psi   |

    @IVI @IVI-7601
    Examples:
      | Param1          | Unit1 | Param2          | Unit2 | Param3          | Unit3 |
      | P001 - Speed PV | rpm   | P002 - Speed PV | rpm   | P003 - Speed PV | rpm   |

  @IVI-7601
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

  @IVI
  Scenario: BIOCRS-5486 | PFSTC_CRS_Verify Layout in Analytics Module
    Given I am logged in as "bio4cadmin" user
    When I go to analytics
    Then I verify presence of below options in Left Panel
      | expandButton        |
      | Create an Aggregate |
      | My Aggregates       |
    And I verify the message in Right Panel "No selection has been made"

  @IVI
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

