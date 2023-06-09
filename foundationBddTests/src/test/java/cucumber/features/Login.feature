@CRS @IVI @ORCHESTRATOR
Feature: Login functionalities

  JIRAs tested:
  https://stljirap.sial.com/browse/BIOFOUND-27790

  Background:
    Given I open portal

  @SMOKE
  @LOGIN
  Scenario: User login
    Given login page is open
    When I enter "bio4cadmin" as username and "Merck@dmin" as password
    And I push the login button
    Then I am logged in

  Scenario: BIOCRS-5151 | Account Lock on 5 unsuccessful attempts and unlock the same account by admin user
    Given login page is open
    When I login to application with wrong password
      | username        | password   | message                                                                                                                                     |
      | AccountLockUser | MerckApp2@ | Invalid username or password. You have 4 attempt(s) left.                                                                                   |
      | AccountLockUser | MerckApp2@ | Invalid username or password. You have 3 attempt(s) left.                                                                                   |
      | AccountLockUser | MerckApp2@ | Invalid username or password. You have 2 attempt(s) left.                                                                                   |
      | AccountLockUser | MerckApp2@ | Invalid username or password. You have 1 attempt(s) left.                                                                                   |
      | AccountLockUser | MerckApp2@ | Your account has been temporarily locked due to multiple invalid login attempts. Please try again in 1439 minutes or contact Administrator. |
    Then I am not logged in
    And I should see the message "Your account has been temporarily locked due to multiple invalid login attempts. Please try again in 1439 minutes or contact Administrator."
    And I enter "Bio4CAdmin" as username and "Merck@dmin" as password
    And I push the login button
    And I go to user page
    And I search "AccountLockUser" user
    And I see the user is locked
    And I edit the user
    And I unlock the account
    Then I see account unlock message is displayed
    And I see the user is unlocked
    And I logout
    And login page is open
    And I enter "AccountLockUser" as username and "MerckApp1@" as password
    And I push the login button
    Then I am logged in

  Scenario Outline: Login errors
    Given login page is open
    When I enter "<login>" as username and "<password>" as password
    And I push the login button
    Then I am not logged in
    And I should see the message "<message>"

    Examples:
      | login         | password     | message                                                   |
      | bio4cservice1 | Merck$ervice | Bad credentials                                           |
      | Bio4cService  | MerckApp2@   | Invalid username or password. You have 4 attempt(s) left. |

  Scenario Outline: New user login Or Connect after reset the password
    Given login page is open
    When I enter "<login>" as username and "<tempPassword>" as password
    And I push the login button
    And I change password "<newPassword>"
    And login page is open
    And I enter "<login>" as username and "<tempPassword>" as password
    And I push the login button
    And I should see the message "Invalid username or password. You have 4 attempt(s) left."
    And I enter "<login>" as username and "<newPassword>" as password
    And I push the login button
    And I generate audit trail report
    And I verify audit logs for "temp" password
    And I check the audit trail report
    Then I see the "temp" password events in report

    Examples:
      | login                | tempPassword | newPassword |
      | testUsrFirstLog      | Ot8[o[Zo{0   | !2345Zxcv1  |
      | testUsrAfterResetPwd | IN0Ax^t;:6   | !2345Zxcv1  |


  Scenario: User login for user disabled
    Given login page is open
    When I enter "UserDisabled" as username and "MerckApp1@" as password
    And I push the login button
    Then I see the error message "Unauthorized access, Failed to authenticate"

  Scenario: BIOFOUND-27788 | Verify the errors when user doesn't set the password according to the password policy
    Given login page is open
    When I enter "NewUserTempPwd" as username and "Wrv0*]G0=p" as password
    And I push the login button
    Then I provide less complex passwords to verify the password policy
      | merckapp   | merckapp   | Password doesn't met the policy criteria.            |
      | MERCKAPP   | MERCKAPP   | Password doesn't met the policy criteria.            |
      | MerckApp   | MerckApp   | Password doesn't met the policy criteria.            |
      | 123456789  | 123456789  | Password doesn't met the policy criteria.            |
      | MerckApp1  | MerckApp1  | Password doesn't met the policy criteria.            |
      | Mar1@      | Mar1@      | Password doesn't met the policy criteria.            |
      | MerckApp1@ | MerckApp2@ | New password and confirmation password do not match. |