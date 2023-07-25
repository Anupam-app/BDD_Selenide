@CRS @IVI
Feature: Recipe Management console

  Background:
    Given I am logged in as "Bio4CAdmin" user

  @SMOKE
  @PLC
  Scenario: Recipe system Hold/Restart
    Given I expand recipe console in pnid
    When I hold and restart the system
    Then I see the system is restarted

  Scenario: BIOCRS-5498 | Recipe system Hold/Restart validation when recipe already loaded but not started
    Given I expand recipe console in pnid
    When I load recipe "testRecipeToExecute1min"
    And I hold the system
    Then I see the system on restart
    And clear panel and run button is disabled

  Scenario: BIOCRS-5479 | Verify Recipe Run Console Options
    Given I expand and collapse recipe console in pnid
    When I verify Recipe run options
    And I goto manual operation tab
    Then I verify manual operation options

  Scenario: BIOCRS-5498 BIOCRS-5480| Recipe system Hold/Restart validation when recipe already loaded and started
    Given I expand recipe console in pnid
    When I load recipe "testRecipeToExecute1min"
    And I start recipe execution
    And I hold the system
    Then I see the system on restart
    And Recipe execution is paused
    And I restart the system
    And I click on pause button
    Then control should be on resume button
    And I click on resume button
    Then control should be on pause button
    And I click on jump step "1"
    And I click on abort button
    Then I should see the recipe run aborted
    And control should be on rerun button
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "Bio4CAdmin"
    Then I check audit trial logs

  Scenario: Recipe execution time is cached when it is Rerun after being aborted
    Given I expand recipe console in pnid
    When I load recipe "testRecipeFlows"
    And I start recipe execution
    And I wait for 4 seconds
    And I click on abort button
    And I should see the recipe run aborted
    Then control should be on rerun button
    And I rerun recipe execution and timer starts from zero

  Scenario: Special chars are not allowed in comments
    Given I expand recipe console in pnid
    When I load recipe "testRecipeToExecute1min"
    And I provide special chars in pre run comments
    Then I see the error message as "Special characters are not allowed for Comments"
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "Bio4CAdmin"
    Then I check audit trial logs

  @SMOKE
  Scenario: Recipe execution
    Given I expand recipe console in pnid
    When I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 12 seconds
    Then Recipe should be executed

  Scenario: BIOCRS-660 | Recipe operational control workflow
    Given I expand recipe console in pnid
    When I load recipe "testRecipeFlows"
    And I start recipe execution
    And I click on pause button
    Then control should be on resume button
    And I click on resume button
    Then control should be on pause button
    And I click on jump step "3"
    And I click on abort button
    Then I should see the recipe run aborted
    And control should be on rerun button

  Scenario: Verify the Recipe Execution|BIOCRS-1593|
    Given I expand recipe console in pnid
    When I click on load recipe
    Then I should not see unapproved recipe
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    Then I should see recipe name and recipe steps details
    When I goto report management page
    And I select template sort by "Run" in "false"
    And I select date range as "Today"
    Then I verify recipe details captured in report run tab "testRecipeToExecute"

  @BIOCRS-9352 @IVI-7256 @IVI-7040
  Scenario: BIOCRS-5496|BIOFOUND-12592: Verify Pre-run modal for Manual run Recipe execution
    When I expand recipe console in pnid
    And I select "MANUAL OPERATION" tab
    When I click on start button
    When I start manual recipe execution
    And I click ok button
    And I click on start button
    And I click ok button
    Then I should see "Mandatory field should not be empty." message
    When I enter existing value in RUNID
    Then I should see message "Run ID is already in use."
    When I enter special characters "@!#$%^&*" in comments section
    Then I should see special characters not allowed
    And I Verify manual run status in recipe console

  @IVI-7599
  Scenario: BIOCRS-5494|BIOFOUND-8611|BIOFOUND-12071: Verify Pre-run modal during Recipe execution
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    And I verify all mandatory fields has asterisk mark "*"
    When I click ok button
    Then I should see "Mandatory field should not be empty." message
    When I enter existing value in RUNID
    Then I should see message "Run ID is already in use."
    And I verify the Batch ID suggestion with unique Value
    When I enter special characters "@!#$%^&*" in run comments section
    Then I should see special characters not allowed

  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Invalid Step
    Given I expand recipe console in pnid
    When I load recipe "testRecipeFlows"
    And I start recipe execution
    And I click on jump step "10"
    Then I should see error message about recipe step

  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Forward-Reverse step
    Given I expand recipe console in pnid
    When I load recipe "testRecipeFlows"
    And I start recipe execution
    Then I jump to Step no and verify step execution
      | Step no |
      | 3       |
      | 2       |
    And I wait the end of the execution of the recipe during 25 seconds
    And Recipe should be executed

  Scenario: BIOCRS-4047|4050|5480|BIOFOUND-9732: Verify state of Manual Operation tab when Recipe execution is in progress
    Given I expand recipe console in pnid
    When I load recipe "testRecipeToExecute"
    Then I verify Manual Operation tab is "enabled"
    And I verify Recipe Run tab is "enabled"
    When I start recipe execution
    Then I verify Manual Operation tab is "disabled"
    And I pause recipe and verify recipe paused and jump icon is disabled
    And I verify Manual Operation tab is "disabled"
    When I resume and verify recipe execution is resumed
    Then I verify Manual Operation tab is "disabled"
    And I wait the end of the execution of the recipe during 30 seconds
    And I should see the recipe run "Completed"
    And I verify Manual Operation tab is "enabled"
    And I verify Recipe Run tab is "enabled"
    And I re-run the recipe
    Then I verify Manual Operation tab is "disabled"
    And I click on abort button
    Then I should see the recipe run aborted
    And I verify Manual Operation tab is "enabled"

  Scenario: BIOCRS-4047|BIOFOUND-12586: Verify state of Manual Operation tab when Recipe execution is in progress
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I wait until Run button is displayed and "enabled"
    When I hold the system
    Then I see the system on hold
    Then I verify Manual Operation tab is "disabled"
    Then I verify Recipe Run tab is "disabled"
    And I restart the Process hold
    Then I verify Manual Operation tab is "enabled"
    Then I verify Recipe Run tab is "enabled"

  @BIOCRS-9352 @IVI-7256 @IVI-7040 @IVI-7982
  Scenario: BIOCRS-4049|5479: Verify Run start behavioral transitions during Manual Operation run & post-Run modal timeout verification
    Given I expand recipe console in pnid
    When I select "MANUAL OPERATION" tab
    And I start Manual run
    Then I validate the timer, stop button, run details
    When I Process hold the system
    Then I should see Process restart button
    And I restart the Process hold
    And I validate the timer is incrementing
    When I Stop the RUN
    Then I validate the date formats in Post run window and enter comments
    And I wait for 1 min for the post run window to auto closed
    And I validate the Start button is "enabled"
    And I goto report management page
    And I choose the run
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    Then I should see the report file presence
    And I check manual run report

  Scenario: FT_CF_Recipe Management_Verify recipe console extended view before recipe download when Process Hold or Process Restart actions are performed on system
    Given I expand recipe console in pnid
    When I Select Process Hold
    And I verify the Process hold Dialog box , buttons
    And I validate close,No button functionality
    And I Select Process Hold
    And I Select Yes button
    Then I should see change of Process holding to Process restart
    And I verify the recipe console Elements
    And I select Process restart
    And I verify the Process restart Dialog box , buttons
    And I validate close,cancel button functionality
    And I select Process restart
    And I Select confirm button
    Then I should see change of Process restating to Process hold
    And I verify the recipe console Elements

  Scenario: BIOFOUND-10802: FT_CF_Recipe Management_Verify recipe execution live data persistency when user switches the focus outside P&ID page
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    And I start recipe execution
    And I verify the recipe execution details in console View
    When I goto report management page
    And I go to main
    Then I verify the recipe execution details in console View
    And I logout and login as "Bio4CAdmin" and password as "Merck@dmin"
    And I expand recipe console in pnid
    And I verify the recipe execution details in console View

  @BIOCRS-9352 @IVI-7256 @IVI-7040
  Scenario: Verify Pre-run modal for Manual run Recipe execution|BIOCRS-5496|
    Given I expand recipe console in pnid
    When I select "MANUAL OPERATION" tab
    Then I should see start button is displayed
    When I click on start button
    When I start manual recipe execution
    And I click ok button
    And I click on start button
    Then I should see "Mandatory field should not be empty." message
    When I enter existing value in RUNID
    Then I should see message "Run ID is already in use."
    When I enter special characters "@!#$%^&*" in comments section
    Then I should not see special characters not allowed
    And I Verify manual run status in recipe console

  Scenario: BIOFOUND-13271: Verify recipe console extended view UI when a recipe having lengthy recipe title and description is downloaded
    Given I expand recipe console
    When I load recipe "testRecipeWithChar30NameLengt"
    Then I verify the recipe name displayed on load recipe list
    And I verify the recipe name is trimmed on recipe console UI
    And I verify the recipe lengthy step is trimmed
    And I verify mouse hover on step displays tool tip with full step details

  Scenario: BIOFOUND-13262: Verify recipe console extended view UI when lengthy data is provided in Pre-run modal
    Given I expand recipe console
    When I load recipe "testRecipeToExecute1min"
    Then I should see pre run window
    When I clear and try to enter lengthy RUN ID, BatchID
    And provide remaining mandatory data to select OK button
    Then I should see recipe execution started successfully
    And I validate the recipe console UI elements
    And I mouse hover RUNID and BatchID to validate full text displayed
    And I Abort the recipe execution
    And I validate the RUNID BATCHID text displayed on Post run window

  @BIOCRS-9352 @IVI-7256 @IVI-7040
  Scenario: BIOFOUND-13275: Verify manual run UI from recipe console extended view.
    Given I expand recipe console
    When I select "Manual operation" tab
    And I start Manual run
    And I enter manual operation name more than 30 char and Tab out
    Then Verify the warning message "Manual Operation Name should not exceed 30 characters."
    And I start the recipe run with lengthy text on RUNID,BATCHID,PRODUCTID
    And I validate all above text value trimmed on recipe console UI
    And I mouse hover to see full text displayed on tooltip
    When I stop the run execution
    Then I verify the text value trimmed on post run window
    And I mouse hover to see full text displayed  on tooltip

  Scenario: BIOFOUND-11336: Multiple Users_ Verify Audit Trail log for recipe start, end, pause, resume and abort operation during Recipe execution
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    And I start recipe execution
    And I logout and login as "Bio4Cservice" and password as "Merck$ervice"
    And I expand recipe console in pnid
    And I click on pause button
    And I click on resume button
    And I logout and login as "bio4cAdmin" and password as "Merck@dmin"
    And I expand recipe console in pnid
    And I click on jump step "3"
    And I click on abort button
    Then I should see the recipe run aborted
    And control should be on rerun button
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "Bio4CAdmin"
    And I check audit trial logs

  Scenario: BIOFOUND-11316: Recipe Management_ Verify Audit Trail log for recipe start, end, pause, resume and abort operation during Recipe execution
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    And I start recipe execution
    And I hold the system
    And Recipe execution is paused
    And I restart the system
    And I click on pause button
    And I click on resume button
    Then control should be on pause button
    And I click on jump step "1"
    And I click on abort button
    Then I should see the recipe run aborted
    And control should be on rerun button
    And I goto report management page
    When I select report from dropdown "Audit Trail"
    And I select user in dropdown "Bio4CAdmin"
    And I check audit trial logs

  Scenario: BIOFOUND-11955: FT_CF_ Recipe Management_ Verify Audit Trail log for System Hold and Restart
    When I expand recipe console in pnid
    And I hold the system
    And I restart the system
    And I load the recipe "testRecipeToExecute1min"
    And I start recipe execution
    And I hold the system
    And I restart the system
    And I see Recipe should be executed
    And I click on pause button
    And I hold the system
    And I restart the system
    And I generate audit trail report
    And I verify audit logs for system hold and restart
    And I check the audit trail report
    Then I see the system hold and restart entries in report

  Scenario: BIOFOUND-11294: Verify state persistency of Recipe Console when system is on Hold and user switches the focus outside P&ID page
    Given I expand recipe console in pnid
    When I load recipe "testRecipeFlows"
    And I start recipe execution
    And I hold the system
    Then Verify the recipe console extended view UI components
    And I goto report management page
    And I go to Main screen
    And I expand recipe console in pnid
    Then Verify the recipe console extended view UI components
    And I logout
    And login page is open
    And I enter "bio4cAdmin" as username and "Merck@dmin" as password
    And I push the login button
    Then Verify the recipe console extended view UI components

  Scenario: BIOFOUND-9215: Verify Recipe Console access privileges for Unauthorised User or User with no permission to Run Recipe
    Given  I expand recipe console in pnid
    When I logout
    And login page is open
    And I enter "reportUnauthUser" as username and "MerckApp1@" as password
    And I push the login button
    Then I verify recipe console expand is disabled
