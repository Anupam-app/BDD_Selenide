Feature: Settings Modification

  Background:
    Given the browser "Chrome" is open
    And I am using language "en-US"
    And I am logged in as "bio4cservice" user

  Scenario Outline: Test i18n
    Given I go to user profile
    And I go to user preferences
    When I change default language to "<language>"
    And I save user preferences
    Then I see expected texts in OnScreenKeyBoard
    And I go to alarm
    And I see expected texts from alarm module
    
    Examples:
         | language |
         | fr-FR    |
         | en-US    |