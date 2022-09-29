@COMMON
Feature: Report administration

Background:
    Given I am logged in as "Bio4CAdmin" user

  Scenario: Generate and sign Audittrail report
    Given I goto report management page
    When I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Generate and sign a recipe run history report
    Given I goto report management page
    When I select report from dropdown "Run History"
    And I choose recipe run "recipe4sec220211129030358"
    And I choose template "testReportTemplate"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Generate and sign a consolidated report
    Given I goto report management page
    When I select report from dropdown "Consolidated"
    And I choose recipe run "recipe4sec220211129030358" for consolidation
    And I choose recipe run "recipe4sec220211129035111" for consolidation
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Generate and sign a custom report
    Given I goto report management page
    When I select report from dropdown "Custom"
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

  Scenario: Generate run history report and check report content
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    And I wait the end of the execution of the recipe
    When I goto report management page
    And I select report from dropdown "Run History"
    And I choose corresponding recipe run
    And I choose template "testReportTemplate"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I check report content

  Scenario: Generate Audittrail report and verify that user information are consistent
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    And I wait the end of the execution of the recipe
    When I goto report management page
    And I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I verify that user information are consistent
 
  Scenario: Report Approval E-Sign Failure On Entering Wrong Password
    Given I goto report management page
    When I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report with wrong password "abcde#23"
    Then I verify the password error message "Incorrect Password"

    
  Scenario: Verify Save As options in template page
    Given I goto report management page
    And I trigger report template mode
    When I create random report template
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    Then I save the report template
    And I search the report template
    And I select the report template
    Then I see "Save As" button enable and save As the report template
    And I see SaveTemplate popup window
    When I modify the Existing template
    Then I see "Report template created" successfully message
    And I search modified the template

 Scenario: Verify Create Custom Template  
  	Given I expand recipe console in pnid
    And 	I load recipe "testRecipeToExecute"
    And 	I start and wait recipe execution during 10 seconds
    And 	I wait the end of the execution of the recipe
    When  I goto report management page
    And 	I select report from dropdown "Run History"
    And 	I choose corresponding recipe run
    And 	I choose template "testReportTemplate"
    And   I click on generate button
    And 	I goto report management page
    And 	I trigger report mode
    Then  I should see the report file presence
    And 	I verify run summary report report   
   
 Scenario: verify generate by second user
    Given I am logged in
    And I logout
    Given I open login page
    When I enter "reportUnauthUser" as username and "MerckApp1@" password
    And I push the login button
    And  I goto report management page
    Then I validate generate button not displayed