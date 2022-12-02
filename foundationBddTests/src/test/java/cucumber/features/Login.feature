@CRS @IVI @ORCHESTRATOR
Feature: User login

  Background:
    Given I open portal

  @SMOKE
  @LOGIN
  Scenario: User login
    Given I open login page
    When I enter "bio4cadmin" as username and "MerckApp1@" as password
    And I push the login button
    Then I am logged in

  Scenario: BIOCRS-5151 | Account Lock on 5 unsuccessful attempts
    Given I open login page
    When I login to application with wrong password
      | username | password   | message                                                                                                                                     |
      | Acclock  | MerckApp2@ | Invalid username or password. You have 4 attempt(s) left.                                                                                   |
      | Acclock  | MerckApp2@ | Invalid username or password. You have 3 attempt(s) left.                                                                                   |
      | Acclock  | MerckApp2@ | Invalid username or password. You have 2 attempt(s) left.                                                                                   |
      | Acclock  | MerckApp2@ | Invalid username or password. You have 1 attempt(s) left.                                                                                   |
      | Acclock  | MerckApp2@ | Your account has been temporarily locked due to multiple invalid login attempts. Please try again in 1439 minutes or contact Administrator. |
    Then I am not logged in
    And I should see the message "Your account has been temporarily locked due to multiple invalid login attempts. Please try again in 1439 minutes or contact Administrator."

  Scenario: BIOCRS-5151 | IVI Bug-IVI-4488 Account unlock for locked account
    Given I open login page
    And I am logged in as "Bio4CAdmin" user
    And I go to user page
    And I search "Acclock" user
    And I see the user is locked
    And I edit the user
    And I click on reset password
    Then I see password reset message is displayed
    And I see the user is unlocked

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
      | login                | tempPassword | newPassword |
      | testUsrFirstLog      | Ot8[o[Zo{0   | !2345Zxcv1  |
      | testUsrAfterResetPwd | IN0Ax^t;:6   | !2345Zxcv1  |

  Scenario: User login Unauthorized
    Given I open login page
    When I enter "UserDisabled" as username and "MerckApp1@" as password
    And I push the login button
    Then I see the error message "Unauthorized access, Failed to authenticate" 
    
   
   
