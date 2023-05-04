@IVI
Feature: Recipe Touch Enabler

  Background:
    Given I am logged in as "Bio4CAdmin" user
    And I open Recipe editor

  Scenario: Verify Add step, Add criteria, save, Delete functionality
    When I add 3 action steps
    And I set a step wait time to "04 seconds"
    And I add a criteria to step "2"
    And I Save the recipe
    And I delete step "3"
    Then I should be notified that "Step deleted successfully"
    When I Save the recipe
    Then I should see step is deleted

  Scenario: Verify Copy step, cut step, paste step functionality
    When I add 3 action steps
    And I add a criteria to step "2"
    And I Copy step number "2"
    And I paste the copied step after step 3
    Then step "2" should be identical to the step "4"
    And I Save the recipe
    When I Cut Step number "3"
    Then I should be notified that "Step cut successfully"
    And I paste the cut step before the step 2
    And I Save the recipe
    And I should see cut step is pasted

  Scenario Outline: Verify Add phase, copy, paste phase functionality
    When I add 4 action steps
    And I initiate Phase creation using action step "2,3"
    Then I should see phase creation warning message
    When I add phase successfully
    Then I should see phase has action steps "2,3"
    When I "<action>" to "<pasteAction>" Phase
    Then I verify phase is pasted "<value>"
    And I Save the recipe

    Examples:
      |action     |pasteAction        |value  |
      |Copy Phase |Paste Phase Before |Before |
      |Copy Phase |Paste Phase After  |After  |