Feature: Analytics graph

  Scenario Outline: Plot Graph
    Given I am logged in as "bio4cadmin" user
    And I load recipe "testRecipeToExecute1min" and run it during 65 seconds if not done before
    And I create analytics aggregate "tstAggregGraph" with "<analyticsParams>" if not done before
    When I select the aggregate
    And I select "<graphName>" graph in x axis
    And I select the y axis
    And I apply the analytics settings
    Then Graph should be plotted
    And Values for parameter should be displayed in Data section of aggregate

    @IVI
    Examples:
      | analyticsParams                | graphName   |
      | parameters/ivi/analyticsParams | line        |
      | parameters/ivi/analyticsParams | scatter     |
      | parameters/ivi/analyticsParams | logarithmic |
      | parameters/ivi/analyticsParams | OLS         |
      | parameters/ivi/analyticsParams | LOESS       |

    @CRS
    Examples:
      | analyticsParams                | graphName   |
      | parameters/crs/analyticsParams | line        |
      | parameters/crs/analyticsParams | scatter     |
      | parameters/crs/analyticsParams | logarithmic |
      | parameters/crs/analyticsParams | OLS         |
      | parameters/crs/analyticsParams | LOESS       |