Feature: Recipe Touch Enabler

  Background:
    Given I am logged in as "Bio4CAdmin" user
    And I go to recipe page
    And I trigger edit mode

  @IVI
  Scenario: Verify Add step, Add criteria, save, Delete functionality
    When I add 3 action steps
    And I set a step wait time to "04 seconds"
    And I add a criteria to step "2"
    And I Save the recipe
    And I delete step "3"
    Then I should be notified that "Step deleted successfully"
    When I Save the recipe
    Then I should see step is deleted

  @IVI
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
    And I should see step count is unchanged