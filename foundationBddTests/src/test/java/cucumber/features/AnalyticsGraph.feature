Feature: Analytics management

  Background: 
    Given the browser "Chrome" is open
    And I am logged in as "bio4cadmin" user
    And I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start recipe execution
    And I create analytics aggregate "tstAggregGraph"

  Scenario Outline: Plot Graph
    When I select the aggregate
    And I select "<graphName>" graph in x axis
    And I select the y axis
    And I apply the analytics settings
    Then Graph should be plotted
    And Values for parameter should be displayed in Data section of aggregate
    
    Examples:
      | graphName   |
      | line        |
      | scatter     |
      | logarithmic |
      | OLS         |
      | LOESS       |