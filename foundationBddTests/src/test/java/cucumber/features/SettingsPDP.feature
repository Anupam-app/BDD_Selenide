Feature: Settings Modification specific to PDP

  Scenario Outline: Change custom label
    Given I am logged in as "bio4cadmin" user
    And I goto settings page
    And I goto system components
    When I change custom label "<customLabelName>"
    Then New "<customLabelName>" is applied

    @CRS
    Examples:
      | customLabelName |
      | Pump 21         |
