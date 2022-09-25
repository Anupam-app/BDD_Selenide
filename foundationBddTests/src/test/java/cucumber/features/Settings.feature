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

  @COMMON
  Scenario Outline: Change language setting
    Given I goto settings page
    And I goto general components
    When I change language to "<language>"
    And I apply settings
    And I goto backup page
    And I goto settings page
    And I goto general components
    Then I see the expected language activated

    Examples:
      | language |
      | fr-FR    |
      | en-US    |