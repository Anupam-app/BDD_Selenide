Feature: Analytics graph

  Background: 
    Given the browser "Chrome" is open
    And I am logged in as "bio4cadmin" user
    And I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min" and run it during 65 seconds if not done before
    And I create analytics aggregate "tstAggregGraph" if not done before

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