@CRS
Feature: Settings Modification

  @CONFIG_SETTING_SYSTEM_COMPONENTS
  Scenario Outline: Change custom label
    Given I am logged in as "bio4cadmin" user
    And I goto settings page
    And I goto system components
    When I change custom label "<customLabelName>"
    Then New "<customLabelName>" is applied

    Examples:
      | customLabelName |
      | Pump 21         |

  #NOT able to stabilize this test and cause other test failure
  #@COMMON
  #Scenario Outline: Change language setting
  #  Given I am logged in as "bio4cadmin" user
  #  And I goto settings page
  #  And I goto general components
#    When I change language to "<language>"
  #  And I apply settings
  #  And I goto backup page
  #  And I goto settings page
  #  And I goto general components
  #  Then I see the expected language activated
#
  #  Examples:
  #    | language |
  #    | fr-FR    |
  #    | en-US    |

  Scenario: Verify General Information and Settings Page
    Given I am logged in as "bio4cadmin" user
    And I goto settings page
    When I goto general components
    Then below information is displayed
      | Language        |
      | Date Format     |
      | Time Format     |
      | Number Format   |
      | Session Timeout |
