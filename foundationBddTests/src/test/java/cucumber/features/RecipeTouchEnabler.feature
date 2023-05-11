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
    And I add Phase using action step "2,3"
    Then I verify steps are added in phase
    When I "<action>"
    And I paste phase "<value>"
    Then I verify phase is pasted "<value>"

    Examples:
      |action     |value  |
      |Copy Phase |Before |
      |Copy Phase |After  |

  Scenario: Verify Save to Phase library functionality
    When I add 4 action steps
    And I add Phase using action step "2,3"
    Then I verify steps are added in phase
    And I save phase to Phase library
    Then I should be notified that "Phase successfully added to phase library"
    And I should see phase details under phase library

  @IVI-7828
  Scenario: Verify Delete phase functionality
    When I add 4 action steps
    And I add Phase using action step "2,3"
    Then I verify steps are added in phase
    And I Delete phase
    And I should see confirmation message "Phase Deleted Successfully"

