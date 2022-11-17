@CRS
Feature: Settings Modification

  Background:
    Given I am logged in as "bio4cadmin" user

  @CONFIG_SETTING_SYSTEM_COMPONENTS
  Scenario Outline: Change custom label
    Given I goto settings page 
    And I goto system components
    When I change custom label "<customLabelName>"
    Then New "<customLabelName>" is applied
    
    Examples:
         | customLabelName |
         | Pump 21         |