Feature: User login

  Background:
    Given The browser "Chrome_1024x768" is open

  Scenario: User logged in
    Given I open portal
    When I login as "Bio4CAdmin" user
    And I push login
    Then I am logged in

  Scenario: User can't login because of wrong login or password
    Given I open portal
    And I open login
    When I input credentials:
      | bio4cservice1 |  MerckApp1@ |
      | bio4cservice  |  MerckApp2@ |
    And I push login
    Then I am not logged in
    And I should see this message "The user name or password is incorrect."

