Feature: User login

  Background:
    Given The browser "Chrome" is open
    And I open portal

  Scenario: User login
    Given I open login
    When I login as "bio4cservice" user
    Then I am logged in

  Scenario Outline: Login errors
    Given I open login
    When I enter "<login>" as username and "<password>" as password
    Then I am not logged in
    And I should see the message "The user name or password is incorrect."

    Examples:
      | login         | password   |
      | bio4cservice1 | MerckApp1@ |
      | bio4cservice  | MerckApp2@ |