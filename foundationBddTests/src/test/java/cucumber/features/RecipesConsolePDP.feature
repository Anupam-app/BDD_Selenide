Feature: Recipe Management console PDP

  JIRAs tested:
  https://stljirap.sial.com/browse/BIOFOUND-29286

  @IVI-4926
  Scenario Outline: Verify Authorized user can run UnApproved Recipe
    Given I am logged in as "Bio4CAdmin" user
    And  I expand recipe console in pnid
    When I load recipe "<recipeName>"
    And I start and wait recipe execution during 12 seconds
    Then Recipe should be executed

    @SM
    Examples:
      | recipeName       |
      | testDraftRecipe  |
      | recipeTechReview |
      | recipeInReview   |

  @IVI-4926
  Scenario: Verify Recipe Loader Page
    Given I am logged in as "reportUnauthUser" user
    And I expand recipe console in pnid
    When I load recipe
    Then I verify all the recipes are displayed
