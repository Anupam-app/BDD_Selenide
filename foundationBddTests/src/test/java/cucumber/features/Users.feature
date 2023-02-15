
@CRS @IVI @ORCHESTRATOR
Feature: User management

  @SMOKE
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
    And I choose user language "English"
    And I save the new user
    #TODO until IVI-5671 is resolved
    #Then I check user notification is displayed
    And I search the user
    And I edit the user
    Then The username is equal to the expected one

  Scenario Outline: IVI Bug  IVI-5793| Create new user with existing username
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
      | Username        |
      | testUsrFirstLog |

  Scenario: IVI Bug IVI-5671| BIOCRS-5370 | User modification
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUser" user
    And I edit the user
    And I change the employee id with a random string
    And I select role "Operator"
    And I save my user changes
    #TODO until IVI-5671 is resolved
    #Then I check user notification is displayed
    And I edit the user
    Then the employee id is the expected one
    And the role is "Operator"

  @IVI-5849
  Scenario: IVI Bug IVI-5671|  BIOCRS-586 | User disable
    Given I am logged in as "Bio4cService" user
    And I go to user page
    When I search "testUserEnabled" user
    And I edit the user
    And I disable the user
    And I save my user changes
    #TODO until IVI-5671 is resolved
    #Then I check user notification is displayed
    When I edit the user
    And the user is disabled
    And I generate the "Audit Trail" Report for "Bio4cService" user
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I see the "testUserEnabled" user disabled in report
    When I logout
    And I open login page
    And I enter "testUserEnabled" as username and "MerckApp1@" as password
    And I push the login button
    Then I see the error message "Unauthorized access, Failed to authenticate"

  @IVI-5849
  Scenario: IVI Bug IVI-5671 IVI-5849| BIOCRS-586 | User enable
    Given I am logged in as "Bio4cService" user
    And I go to user page
    When I search "testUserDisabled" user
    And I edit the user
	And I select role "Operator"
    And I save my user changes
    Then I see error message is displayed as "User details cannot be modified for disabled user" 
    And I enable the user
    And I save my user changes
    And I edit the user
    And the user is enabled
    And I generate the "Audit Trail" Report for "Bio4cService" user
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

  @IVI-5685
  Scenario: BIOCRS-4364 | Bug IVI-5685 Verify editable fields in user
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUserToEditFields" user
    And I edit the user
    And I select role "Operator"
    And I enter random employeeID
    And I enter email "alexiss.thiebaut@merckgroup.com"
    And I enter random department
    And I enter mobile number "339879567"
    And I save my user changes
    Then I see user details are changed
    And I generate audit trail report
    And I verify audit logs for user update
    And I check the audit trail report
    And I see the "testUserToEditFields" user modified in report

  Scenario: Reset the password
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUserToResetPwd" user
    And I edit the user
    And I click on reset password
    Then I see password reset message is displayed

  @IVI-4908
  Scenario Outline: IVI BUG IVI-4908 | Verify Default Users & roles
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    And I verify default user account "Bio4CService" and "Administrator"
    And I trigger Roles mode
    And I verify default roles
      | Bio4C Service |
      | Administrator |
      | Operator      |
    And I verify "<UserRole>" list of "<roles>"
    #TO-DO : skipping the test step until IVI-4908 is fixed
    #Then I should see view icon of particular roles
      #| Administrator |
      #| Bio4CService  |

    Examples:
      | roles                         | UserRole  |
      | parameters/crs/privilegeslist | privilege |


  Scenario: Assign custom role to new user |BIOCRS-2585|
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "NewUserRole" user
    And I edit the user
    And I select role "TestRole"
    And I save my user changes
    And I logout and login as "NewUserRole" and password as "MerckApp1@"
    When I goto report management page
    And I select report from dropdown "Audit Trail"
    Then I should see newly created user "NewUserRole" present in report

  Scenario Outline: Verify the system allows user to change the password negative flow1
    Given I am logged in as "<user>" user
    When I try to change password "<currentPassword>" "<newPassword>" "<confirmPassword>"
    Then I see error message is displayed as "<error message>"
    And I logout
    Examples:
      | user          | currentPassword | newPassword | confirmPassword | error message                             |
      | testPwdChange | MerckApp1@      | MerckApp1@  | MerckApp1@      | Cannot reuse old password.                |
      | testPwdChange | MerckApp1@      | 1234567890  | 1234567890      | Password doesn't met the policy criteria. |

  @IVI-6602
  Scenario Outline: IVI BUG IVI-6602 | Verify the system allows user to change the password positive flow2
    Given I am logged in as "<user>" user
    When I try to change password "<currentPassword>" "<newPassword>" "<confirmPassword>"
    Then I see password updated message is displayed for "<user>"
    And I logout
    Examples:
      | user          | currentPassword | newPassword | confirmPassword |
      | testPwdChange | MerckApp1@      | MerckApp2@  | MerckApp2@      |

    #TO-DO : test has dependency on above test case need to wait until IVI-6602 is fixed
  @IVI-6602
  Scenario Outline: Verify the system allows user to change the password 3
    Given I open portal
    And I open login page
    When I login to application with wrong password
      | username | password       | message                                                   |
      | <user>   | <old password> | Invalid username or password. You have 4 attempt(s) left. |
    And I enter "<user>" as username and "<newPassword>" as password
    And I push the login button
    Given I go to userprofile
    Then I should see "<first name>" "<last name>" under user profile icon
    When I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "<user>"
    And I select date range as "Today"
    When I select template sort by "Event Time" in "false"
    #audit report validation is failing Need to fix
    Then I should see change password entries in audit trail report for "<user>"
    When I try to change password "<newPassword>" "<confirmPassword>" "<confirmPassword>"
    Then I see password updated message is displayed for "<user>"
    And I logout
    Examples:
      | user          | old password | newPassword | confirmPassword | first name | last name | user role |
      | testPwdChange | MerckApp1@   | MerckApp2@  | MerckApp1@      | testPwd    | testing   |           |

  Scenario: BIOCRS-5377: Verify the Application Icons
    Given I am logged in as "Bio4CAdmin" user
    Then I verify the below Icons on left rail of Portal
      | Main          |
      | Trends        |
      | Analytics     |
      | Alarms        |
      | Recipes       |
      | Reports       |
      | Users         |
      | Backup        |
      | Settings      |

  @IVI-5823
  Scenario: IVI Bug IVI-5823| User Management | Irrelevant message is displayed when custom user tries to modify his own role
  	Given I am logged in as "testUserToEditFields" user
    And I go to user page
    When I search "testUserToEditFields" user
    And I edit the user
    And I select role "Operator"
    And I save my user changes
    Then I see error message is displayed "Please update user details" 

  @IVI-6198
  Scenario: BIOCRS-4364 | Verify editable fields in user
    Given I am logged in as "Bio4CAdmin" user
    And I go to user page
    When I search "testUserToEditFields" user
    And I edit the user
    And I disable the user
    And I save my user changes
    And I edit the user
    And I enter random employeeID
    And I save my user modification changes
    Then I see error message is displayed "user details cannot be modified for disabled user"

  @IVI-4912
  Scenario: IVI Bug - IVI-4912 | User Preference -Default Page
    Given I am using language "en-US"
    And I am logged in as "testUserForI18N" user
    And I go to user profile
    And I go to user preferences
    When I change default page to "Reports"
    And I save user preferences
    And I logout
    And I open login page
    And I enter "testUserForI18N" as username and "MerckApp1@" as password
    And I push the login button
    Then I am logged in
    And I am landed on "Report Management" page
