@IVI @CRS
@SMOKE
Feature: I18N on all modules

  Background:
    Given I am using language "en-US"
    And I am logged in as "testUserForI18N" user

  Scenario Outline: IVI Bug - IVI-4913 | Test i18n all modules
    Given I go to user profile
    And I go to user preferences
    When I change default language to "<language>"
    And I save user preferences
    Then I see expected texts in user profile
    And I go to main
    And I expand recipe console in pnid
    And I see expected texts from recipe console
    And I navigate to trends page
    And I see expected texts from trend module
    And I go to analytics
    And I see expected texts from analytics module
    And I go to alarm
    And I see expected texts from alarm module
    And I go to recipe page
    And I see expected texts from recipe module
    And I goto report management page
    And I see expected texts from report module
    And I go to user page
    And I see expected texts from user module
    And I goto backup page
    And I see expected texts from backup module
    And I goto settings page
    And I see expected texts from setting module
    And I reset my language to "en-US"

    Examples:
      | language |
      | fr-FR    |
      | en-US    |

  Scenario: Test i18n device shape
    Given I go to user profile
    And I go to user preferences
    When I change default language to "en-US"
    And I navigate to trends page
    And I see expected texts from trend module parameters
    And I go to recipe page
    And I see expected texts from recipe module criterias
    And I go to analytics
    And I see expected texts from analytics module parameters

  Scenario: IVI Bug - IVI-4912 | User Preference -Default Page
    Given I go to user profile
    And I go to user preferences
    When I change default page to "Reports"
    And I save user preferences
    And I logout
    And I open login page
    And I enter "testUserForI18N" as username and "MerckApp1@" as password
    And I push the login button
    Then I am logged in
    And I am landed on "Report Management" page
    And I reset to "Main" page
    