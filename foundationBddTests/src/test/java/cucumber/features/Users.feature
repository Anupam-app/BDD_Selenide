Feature: User management

  Background:
    Given the browser "Chrome" is open
    And I am logged in as "Bio4CAdmin" user
    And the user "testUser" exists
    And the user "testUserEnabled" exists
    And the user "testUserDisabled" exists
    
  Scenario: Verify editable fileds in user
		Given I goto user management page
    When I search "testUser" user
    And I edit the user
		And I change the role
		And I change the employee id
		And I change the email
		And I change the mobile number
		And I change the department
		And I generate audit trail report
		Then I see user details are changed
		And I check the audit trail report
		
		
  Scenario: Reset the password
		Given I goto user management page
		When I click on edit user
		And I click on reset password
		Then I see password reset message is displayed


  Scenario: Connect after reset the password
		Given I goto user management page
		When I login with new password
		Then I am logged in

  Scenario: User modification
    Given I go to user page
    When I search "testUser" user
    And I edit the user
    And I change the employee id with a random string
    And I save my user changes
    And I edit the user
    Then the employee id is the one expected

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
    Then the user is enabled
    
    
  @Wip
  Scenario: Verify permissions related to Users Management
    Given I am logged in as "testUserPermsn" user
    When I go to user page 
    And I see user management page with list of users
    And I create user "mallikarjun.onkar@external.merckgroup.com"
    And I edit user
    Then I verify new user permissions to user management
    
  Scenario: Verify user do not have permissions to Users Management
    Given I am logged in as "testUserWithoutUserPerm" user
    When I go to user page
    And I dont see user management page with list of users
    Then I verify user dont have permission to create user and edit user