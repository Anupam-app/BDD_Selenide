Feature: User login

  Background:
    Given the browser "Chrome" is open
    And I open portal

  Scenario: User login
    Given I open login page
    When I enter "bio4cservice" as username and "MerckApp1@" as password
    And I push the login button
    Then I am logged in

  Scenario Outline: Login errors
    Given I open login page
    When I enter "<login>" as username and "<password>" as password
    And I push the login button
    Then I am not logged in
    And I should see the message "<message>"

    Examples:
      | login         | password   | message                                                   |
      | bio4cservice1 | MerckApp1@ | Bad credentials                                           |
      | bio4cservice  | MerckApp2@ | Invalid username or password. You have 4 attempt(s) left. |

  Scenario Outline: New user login Or Connect after reset the password
    Given I open login page
    When I enter "<login>" as username and "<tempPassword>" as password
    And I push the login button
    And I change password "<newPassword>"
    And I open portal
    And I open login page
    And I enter "<login>" as username and "<newPassword>" as password
    And I push the login button
    Then I am logged in

    Examples:
      | login                  | tempPassword     | newPassword |
      | testUsrFirstLog1       | 05wUa^(ucQ       | !2345Zxcv1  |
      | testUsrAfterResetPwd   | juZZ63~2#x       | !2345Zxcv1  |
