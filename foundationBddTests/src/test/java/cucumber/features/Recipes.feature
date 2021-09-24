Feature: Recipe management

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "bio4cservice" user

  Scenario: Recipe creation
    Given I go to recipe page
    When I trigger edit mode
    And I create a random phase
    And I save the recipe
    Then I go to browser mode
    And I search the recipe
    And I edit the recipe
    And I see my changes in recipe