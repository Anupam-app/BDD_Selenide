Feature: User management

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user
    And the user "testUser" exists
    And the user "testUserEnabled" exists
    And the user "testUserDisabled" exists
    And the user "testUserToEditFields" exists
    And the user "testUserToResetPwd" exists

  Scenario: Create new user
    Given I go to user page
    When I create a random username
    And I select role "Operator"
    And I enter random firstname
    And I enter random lastname
    And I enter random employeeID
    And I enter email "alexis.thiebaut@merckgroup.com"
    And I save my user changes
    And I search the user
    And I edit the user
    Then The username is equal to the expected one

  Scenario: User modification
    Given I go to user page
    When I search "testUser" user
    And I edit the user
    And I change the employee id with a random string
    And I save my user changes
    And I edit the user
    Then the employee id is the expected one

  Scenario: User disable
    Given I go to user page
    When I search "testUserEnabled" user
    And I edit the user
    And I disable the user
    And I save my user changes
    And I edit the user
    Then the user is disabled

  Scenario: User enable
    Given I go to user page
    When I search "testUserDisabled" user
    And I edit the user
    And I enable the user
    And I save my user changes
    And I edit the user
    And the user is enabled

  Scenario: Verify editable fields in user
    Given I go to user page
    When I search "testUserToEditFields" user
    And I edit the user
    And I select role "Bio4CService"
    And I enter random employeeID
    And I enter email "alexis.thiebaut@merckgroup.com"
    And I enter random department
    And I enter mobile number "+0019859859855"
    And I save my user changes
    Then I see user details are changed
    And I generate audit trail report
    And I check the audit trail report
    
  Scenario: Reset the password
    Given I go to user page
    When I search "testUserToResetPwd" user
    And I edit the user
    And I click on reset password
    Then I see password reset message is displayed