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
    Then I see the system on hold
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
    Then I see the system on hold
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
	
	Scenario: IVI Bug IVI-4469| Special chars are not allowed in comments
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    And I provide special chars in pre run comments
    Then I see the error message as "Special characters are not allowed for Comments"

  @SMOKE
  Scenario: Recipe execution
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 12 seconds
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

  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Invalid Step
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
    And I start recipe execution
    And I click on jump step "10"
    Then I should see error message about recipe step
 
  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Forward-Reverse step
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
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
    #Then I verify loading label and recipe download in progress# the loading message is goes off in 2sec,could not get the xpath
    Then I verify Manual Operation tab is "enabled"
    And I verify Recipe Run tab is "enabled"
    When I start recipe execution
    Then I verify Manual Operation tab is "disabled"
    And I pause recipe and verify recipe paused and jump icon is disabled
    And I verify Manual Operation tab is "disabled"
    When I resume and verify recipe execution is resumed
    Then I verify Manual Operation tab is "disabled"
    #TO-DO: needs enhancement on scripting techniques
    And I wait the end of the execution of the recipe during 12 seconds
    And I should see the recipe run "Completed"
    And I verify Manual Operation tab is "enabled"
    And I verify Recipe Run tab is "enabled"
    And I re-run the recipe
    Then I verify Manual Operation tab is "disabled"
    And I click on abort button 
    Then I should see the recipe run aborted
    And I verify Manual Operation tab is "enabled"
    
  Scenario: BIOCRS-4047 Verify state of Manual Operation tab when Recipe execution is in progress
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I wait until Run button is displayed and "enabled"
    When I Process hold the system
    Then I see the system on hold
    Then I verify Manual Operation tab is "disabled"
    Then I verify Recipe Run tab is "disabled"
    And I restart the Process hold
    Then I verify Manual Operation tab is "enabled"
    Then I verify Recipe Run tab is "enabled"
    
  #TO-DO: needs fix in IVI
  Scenario: BIOCRS-4049|5479: Verify Run start behavioral transitions during Manual Operation run & post-Run modal timeout verification
    Given I expand recipe console in pnid
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

