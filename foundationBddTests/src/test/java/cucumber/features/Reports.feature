@COMMON
Feature: Report administration


	Scenario: BIOCRS-5238/5239 | Report Management Dashboard -  Runs Tab
	Given I am logged in as "Bio4CAdmin" user
    When I goto report management page
    Then I see Runs, Templates, Reports tabs are displayed
    And  I see list of "runs" are displayed
    And below "runs" columns are displayed
    |columns		|
    |Run    		|
    |Start Date     |
    |Process Type 	|
    |Status 		|
	
	Scenario: BIOCRS-5238/5239 | Report Management Dashboard -  Templates Tab
	Given I am logged in as "Bio4CAdmin" user
    When I goto report management page
    And I trigger report template mode
    Then  I see list of "templates" are displayed
    And below "templates" columns are displayed
    |columns           |
    |Template Name	   |
    |Status            |
    |Last Modified By  |
    |Last Modified On  |	


	Scenario: BIOCRS-5238/5239 | Report Management Dashboard -  Reports Tab
	Given I am logged in as "Bio4CAdmin" user
    When I goto report management page
    And I trigger report mode
    Then  I see list of "reports" are displayed
    And below "reports" columns are displayed
    |columns        |
    |Report Name	|
    |Date Generated |
    |Created By  	|
    |Report Type	|	
    |E-Sign.Status	|
    |Signed By		|
     
  Scenario: Generate and sign Audittrail report
  	Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "Bio4CAdmin"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence
    

  Scenario: BIOCRS-5106 | Unauthorized user cant generate the audit trail report
    Given I am logged in as "reportUnauthUser" user
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    Then I dont see the presence of generate button 

  Scenario: Generate and sign a recipe run history report
  	Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
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
  	Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
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
  	Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
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
  	Given I am logged in as "Bio4CAdmin" user
    And I expand recipe console in pnid
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
    Given I am logged in as "Bio4CAdmin" user
    And I expand recipe console in pnid
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
    Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report with wrong password "abcde#23"
    Then I verify the password error message "Incorrect Password"

  @WIP
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

  @WIP
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
    