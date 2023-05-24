@IVI
Feature: Recipe Touch Enabler

  Background:
    Given I am logged in as "Bio4CAdmin" user

  Scenario: Verify Add step, Add criteria, save, Delete functionality
    When I open Recipe editor
    And I add 3 action steps
    And I set a step wait time to "04 seconds"
    And I add a criteria to step "2"
    And I Save the recipe
    And I delete step "3"
    Then I should be notified that "Step deleted successfully"
    When I Save the recipe
    Then I should see step is deleted

  Scenario: Verify Copy step, cut step, paste step functionality
    When  I open Recipe editor
    And I add 3 action steps
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
    When  I open Recipe editor
    And I add 4 action steps
    And I add Phase using action step "2,3"
    Then I verify steps are added in phase
    When I Copy Phase
    And I paste phase "<value>"
    Then I verify copied phase is pasted

    Examples:
      |value  |
      |Before |
      |After  |

  Scenario Outline: Verify Add phase, cut, paste phase functionality
    When  I open Recipe editor
    And I add 6 action steps
    And I add 2 phases with Steps "4,5" & "2,3"
    And I Cut Phase <cutPhase>
    And I paste phase "<action>" phase <pastePhaseNo>
    Then I verify phase is pasted "<action>" phase

    Examples:
      |cutPhase |action  |pastePhaseNo |
      |2        |Before  |1            |
      |2        |After   |1            |

  Scenario: Verify Save to Phase library functionality
    When  I open Recipe editor
    And I add 4 action steps
    And I add Phase using action step "2,3"
    Then I verify steps are added in phase
    And I save phase to Phase library
    Then I should be notified that "Phase successfully added to phase library"
    And I should see phase details under phase library

  @IVI-7828
  Scenario: Verify Delete phase functionality
    When  I open Recipe editor
    And I add 4 action steps
    And I add Phase using action step "2,3"
    Then I verify steps are added in phase
    And I Delete phase
    And I should see confirmation message "Phase Deleted Successfully"

  Scenario: Save as new recipe
    When I go to recipe page
    And I edit recipe "recipeTechReview"
    And I select "Save As" button
    And I SaveAs recipe "RecipeSaveAsSecond"
    Then I verify below recipes are displayed in recipe browser list
      | recipeTechReview   |
      | RecipeSaveAsSecond |

  Scenario: Recipe export and import using Touch button
    When I go to recipe page
    And I search the recipe "twoPhaseTestRecipe"
    And I export the recipe
    And I trigger edit mode
    And I Import Recipe
    And I look at the user notification
    Then I should see the recipe exported in user notifications
    And I should see the recipe imported in user notifications

  Scenario Outline: Verify Insert Step before & after, recipe clear button functionality
    When I open Recipe editor
    And I trigger edit mode
    Then I add 3 action steps
    And I "<action>" number 2
    And I add action to new blank step
    And I Save the recipe
    When I select "Clear All" button
    And I Save the recipe
    Then blank recipe is displayed

    Examples:
    | action             |
    | Insert Before Step |
    | Insert After Step  |