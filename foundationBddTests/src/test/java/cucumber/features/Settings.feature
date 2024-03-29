@CRS @IVI
Feature: Settings Modification

#  NOT able to stabilize this test and cause other test failure
#  @COMMON
#  Scenario Outline: Change language setting
#    Given I am logged in as "bio4cadmin" user
#    And I goto settings page
#    And I goto general components
#    When I change language to "<language>"
#    And I apply settings
#    And I goto backup page
#    And I goto settings page
#    And I goto general components
#    Then I see the expected language activated
#
#    Examples:
#      | language |
#      | fr-FR    |
#      | en-US    |

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

  Scenario: Verify UI behavior of System Information & Settings
    Given I am logged in as "bio4cadmin" user
    And I goto settings page
    When I goto system components
    Then below information is displayed
      | System Name           |
      | Custom System Name    |
      | System Family Name    |
      | System Serial No.     |
      | System Size           |
      | Location              |
      | Display Label Setting |
    When I provide random name to custom system name
    And I apply settings
    Then I see system name is updated in portal

  Scenario: Verify About Section in Settings
    Given I am logged in as "bio4cadmin" user
    When I goto settings page
    And I go to hamburger symbol
    And below information is displayed
      |Restore Factory Default|
    And I goto about components
    Then I verify softwareInformation details
    And I verify Third-party Licence Information
    And I verify End User Licence Agreement Information

  Scenario: Verify Maintenance details Section
    Given I am logged in as "bio4cadmin" user
    And I goto settings page
    When I goto service card
    Then below information is displayed
      | Report Issue |
      | Maintenance  |
    When I goto maintenance tab
    Then below information is displayed
      | Last Maintenance Date |
      | Next Maintenance Date |
      | Scheduled Maintenance |
    And I reset the last maintenance date
    Then I verify the Maintenance details updated
