@CRS @IVI @ORCHESTRATOR
Feature: Trends Common

  Scenario: BIOCRS-5482 - Verify the Trends layout | Verify the Trends Page
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    Then I see availability of Trends panel and chart area message "You currently have no selections to display."
    And footer section is not displayed.

  Scenario Outline: BIOCRS-5482 - Verify the Trends layout | Verify the Trends Panel- 1
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I collapse "<TrendPanelType>"
    Then I see the "<TrendPanelType>" collapsed
    When I expand "<TrendPanelType>"
    Then I see the "<TrendPanelType>" expanded

    Examples:
      | TrendPanelType     |
      | Trends_Area_Panel  |
      | Starred_Collection |
      | Default_Collection |
      | List of Collection |

  Scenario: BIOCRS-5482 | Verify the Trends layout | Verify the Trends Panel-2
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    Then I see the availability of below
      | Area graph                    |
      | Line graph                    |
      | Default Parameters Collection |
      | Starred Parameters Collection |
      | List of collection            |