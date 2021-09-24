Feature: User login

  Background:
    Given the browser "Chrome" is open
    And I open portal

  Scenario: User login
    Given I open login page
    When I am logged in as "bio4cservice" user
    Then I am logged in

  Scenario Outline: Login errors
    Given I open login page
    When I enter "<login>" as username and "<password>" as password
    And I push the login button
    Then I am not logged in
    And I should see the message "The user name or password is incorrect."

    Examples:
      | login         | password   |
      | bio4cservice1 | MerckApp1@ |
      | bio4cservice  | MerckApp2@ |