@CRS @IVI
Feature: Recipe console

  Background:
    Given I am logged in as "Bio4CAdmin" user

  @SMOKE
  @PLC
  Scenario: Recipe system Hold/Restart
    When I expand recipe console in pnid
    And I hold and restart the system
    Then I see the system on hold

  Scenario: BIOCRS-5498 | Recipe system Hold/Restart validation when recipe already loaded but not started
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    And I hold the system
    Then I see the system on restart
    And clear panel and run button is disabled

  Scenario: BIOCRS-5479 | Verify Recipe Run Console Options
    When I expand and collapse recipe console in pnid
    And I verify Recipe run options
    And I goto manual operation tab
    And I verify manual operation options

  Scenario: BIOCRS-5498 BIOCRS-5480| Recipe system Hold/Restart validation when recipe already loaded and started
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
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
    And I check audit trial logs

  @SMOKE
  Scenario: Recipe execution
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    Then Recipe should be executed

  Scenario: BIOCRS-660 | Recipe operational control workflow
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
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
    When I expand recipe console in pnid
    And I click on load recipe
    Then I should not see unapproved recipe
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    Then I should see recipe name and recipe steps details
    When I goto report management page
    And I select template sort by "Run" in "false"
    And I select date range as "Today"
    Then I verify recipe details captured in report run tab "testRecipeToExecute"

  Scenario: Verify Pre-run modal for Manual run Recipe execution|BIOCRS-5496|
    When I expand recipe console in pnid
    And I clear the recipe
    And I select "MANUAL OPERATION" tab
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
    And I Verify manual run status in recipe consol

  Scenario: Verify Pre-run modal during Recipe execution|BIOCRS-5494|BIOFOUND-8611|BIOFOUND-12071|
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute" and run it during 10 seconds
    And I verify all mandatory fields has asterick mark "*"
    When I click ok button
    Then I should see "Mandatory field should not be empty." message
    When I enter existing value in RUNID
    Then I should see message "Run ID is already in use."
    And I verify the Batch ID suggestion with unique Value
    When I enter special characters "@!#$%^&*" in run comments section

  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Invalid Step
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
    And I start recipe execution
    And I click on jump step "10"
    Then I should see error message about recipe step

  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Forward-Reverse step
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
    Then I jump to Step no and verify step execution
      | Step no |
      | 3       |
      | 2       |
    And I wait the end of the execution of the recipe during 25 seconds
    And Recipe should be executed

    # Maunual Run UI validation,Partial completed -Manual operation PDF validation is pending
  Scenario: BIOCRS-4047|4050|5480|BIOFOUND-9732|BIOFOUND-12586: Verify state of Manual Operation tab when Recipe execution is in progress
    Given I expand recipe console in pnid
    When I load recipe "testRecipeFlows"
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
    When I Process hold the system
    Then I verify Manual Operation tab is "disabled"
    Then I verify Recipe Run tab is "disabled"
    And I restart the Process hold
    Then I verify Manual Operation tab is "enabled"
    Then I verify Recipe Run tab is "enabled"

    # Manual Run UI validation,Partial completed -Manual operation PDF validation is pending
  Scenario: BIOCRS-4049|5479: Verify Run start behavioral transitions during Manual Operation run & post-Run modal timeout verification
    Given I expand recipe console in pnid
    And I clear the recipe
    When I select "Manual operation" tab
    And I start Manual run
    Then I validate the timer and stop button and run details
    When I Process hold the system
    Then I should see Process restart button
    And I validate the timer is incrementing
    When I Stop the RUN
    Then I validate the date formats in Post run window and enter comments
    And I wait for 1 min for the post run window to auto closed
    And I validate the Start button is "disabled"
    And I restart the Process hold
    And I validate the Start button is "enabled"

  Scenario: FT_CF_Recipe Management_Verify recipe console extended view before recipe download when Process Hold or Process Restart actions are performed on system
    Given I expand recipe console
    When I Select Process Hold
    And I verify the Process hold Dialog box , buttons
    And I validate close,No button funtionality
    And I Select Process Hold
    And I Select Yes button
    Then I should see change of Process holding to Process restart
    And I verify the recipe console Elements
    And I select Process restart
    And I verify the Process restart Dialog box , buttons
    And I validate close,No button funtionality
    And I select Process restart
    And I Select Yes button
    Then I should see change of Process restating to Process hold
    And I verify the recipe console Elements

  Scenario: FT_CF_Recipe Management_Verify recipe execution live data persistency when user switches the focus outside P&ID page
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    And I start recipe execution
    And I verify the recipe execution details in console View
    When I goto report management page
    And I go to Main screen
    Then I verify the recipe execution details in console View
    And I logout
    And I open login page
    And I login with "Bio4CAdmin" same user as above "Merck@dmin"
    And I verify the recipe execution details in console View.
    And I logout
    And I open login page
    And I login with "Bio4CAdmin" same user as above "Merck@dmin"
    And I expand recipe console in pnid
    And I verify the recipe execution details in console View


