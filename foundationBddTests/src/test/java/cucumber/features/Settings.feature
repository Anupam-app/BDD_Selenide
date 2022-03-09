Feature: Settings Modification

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "bio4cservice" user

  @CRS
  Scenario Outline: Change custom label
    Given I goto settings page 
    And I goto system components
    When I change custom label "<customLabelName>"
    Then New "<customLabelName>" is applied
    
    Examples:
         | customLabelName |
         | Pump 21         |