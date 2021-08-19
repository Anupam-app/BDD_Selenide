Feature: Recipe management

  Background:
    Given The browser "Chrome" is open
    And I login as "bio4cservice" user

  Scenario: Recipe modification
    Given I go to recipe page
    When I go to edit mode
    And I create a random phase
    And I save the recipe
    Then I go to browser mode
    And I search the recipe
    And I edit the recipe
    And I see my changes in recipe