Feature: Recipe management

  Background:
    Given The browser "Chrome_1024x768" is open
    And I open portal
    When I login as "bio4cservice" user
    Then I am logged in

  Scenario: Recipe modification
    Given I go to recipe page
    When I go to edit mode
    And I create a random phase
    And I save the recipe
    Then I go to browser mode
    And I search the recipe
    And I edit the recipe
    And I see my changes in recipe