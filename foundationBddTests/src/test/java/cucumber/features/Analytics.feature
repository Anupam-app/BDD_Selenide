@COMMON
Feature: Analytics creation

  Scenario: Aggregate creation
    Given I am logged in as "bio4cadmin" user
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    When I create an analytics aggregate
    And I use the recipe for this analytics aggregate with interval "Weekly"
    And I choose "PI101 PV" analytics parameter with unit "psi" as "x" axis
    And I choose "PI102 PV" analytics parameter with unit "psi" as "y" axis
    And I choose "PI103 PV" analytics parameter with unit "psi" as "y" axis
    And I validate the analytics creation
    Then I see my changes in analytics aggregate