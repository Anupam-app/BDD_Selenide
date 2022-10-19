@COMMON
Feature: User management
   

  @SMOKE @wip
  Scenario: Create new user
  	Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I create a random username
    And I select role "Operator"
    And I enter random firstname
    And I enter random lastname
    And I enter random employeeID
    And I enter email "alexis.thiebaut@merckgroup.com"
    And I enter mobile number "0123456789"
    And I save the new user
    And I search the user
    And I edit the user
    Then The username is equal to the expected one
  @wip  
  Scenario Outline: Create new user with existing username
  	Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I enter username "<Username>"
    And I select role "Operator"
    And I enter random firstname
    And I enter random lastname
    And I enter random employeeID
    And I enter email "alexis.thiebaut@merckgroup.com"
    And I enter mobile number "0123456789"
    And I save the new user
    Then I see error message is displayed for "<Username>"

    Examples:
      | Username         |              
      | testUsrFirstLog  |

  Scenario: User modification
  	Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUser" user
    And I edit the user
    And I change the employee id with a random string
    And I save my user changes
    And I edit the user
    Then the employee id is the expected one

  Scenario: BIOCRS-586 | User disable
  	Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUserEnabled" user
    And I edit the user
    And I disable the user
    And I save my user changes
    And I edit the user
    And the user is disabled
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
	And I see the "testUserEnabled" user disabled in report
    And I logout
    And I open login page
    And I enter "testUserEnabled" as username and "MerckApp1@" as password
    And I push the login button
    Then I see the error message "Unauthorized access, Failed to authenticate"

  Scenario: BIOCRS-586 | User enable
  	Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUserDisabled" user
    And I edit the user
    And I enable the user
    And I save my user changes
    And I edit the user
    And the user is enabled
    And I generate the "Audit Trail" Report for "Bio4CAdmin" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I should see the report file presence
	And I see the "testUserDisabled" user enabled in report
    And I logout
    And I open login page
    And I enter "testUserDisabled" as username and "MerckApp1@" as password
    And I push the login button
    Then I am logged in
   
  Scenario: BIOCRS-586 | Unauthorized user cant edit the user
    Given I am logged in as "reportUnauthUser" user
    And I go to user page
    When I search "testUserToEditFields" user
    Then I cant edit the user  

  Scenario: Verify editable fields in user
  	Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUserToEditFields" user
    And I edit the user
    And I select role "Operator"
    And I enter random employeeID
    And I enter email "alexis.thiebaut@merckgroup.com"
    And I enter random department
    And I enter mobile number "0123456789"
    And I save my user changes
    Then I see user details are changed
    And I generate audit trail report
    And I check the audit trail report  
    
  Scenario: Reset the password
  	Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUserToResetPwd" user
    And I edit the user
    And I click on reset password
    Then I see password reset message is displayed