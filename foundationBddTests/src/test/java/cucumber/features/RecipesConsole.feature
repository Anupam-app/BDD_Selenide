@COMMON
Feature: Recipe console

  Background:
    Given I am logged in as "Bio4CAdmin" user

  @SMOKE
  Scenario: Recipe system Hold/Restart
    When I expand recipe console in pnid
    And I hold and restart the system
    Then I see the system on hold

  @SMOKE
  Scenario: Recipe execution
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start and wait recipe execution during 10 seconds
    Then Recipe should be executed

  Scenario: Recipe operational control workflow
    When I expand recipe console in pnid
    And I load recipe "testRecipeFlows"
    And I start recipe execution
    And I click on pause button
    And I click on resume button
    And I click on jump step "2"
    And I click on abort button
    Then I should see the recipe run aborted
	
 #ToDo  
  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Invalid Step 
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    And I start recipe execution
    And I click on jump step "10"
    Then I should see Error message
  
  Scenario: BIOCRS-2687 Verify Jump to Step Functionality | Forward-Reverse step
    When I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1minTest"
    And I start recipe execution
    Then I jump to Step no and verify step execution
         |Step no|
         |5      |
         |3      |
    And Recipe should be executed
    
  
  Scenario: BIOCRS-4047 Verify state of Manual Operation tab when Recipe execution is in progress
    Given I expand recipe console in pnid
    And I load recipe "testRecipeToExecute1min"
    #Then I verify loading label and recipe download in progress# the loading message is goes off in 2sec,could not get the xpath
    And I verify Manual Operation tab is "enabled"
    And I verify Recipe Run tab is "enabled"
    And I start recipe execution
    Then I verify Manual Operation tab is "disabled"
    And I click on pause button
    Then I verify recipe execution is paused 
    And I verify Manual Operation tab is "disabled"
    When I click on resume button
    Then recipe execution is resumed
    And I verify Manual Operation tab is "disabled"
    And I should see the recipe run "Completed"
    And I verify Manual Operation tab is "enabled"
    When I re-run the recipe
    Then I verify Manual Operation tab is "disabled"
    When I click on abort button 
    Then I verify Manual Operation tab is "enabled"
    
  Scenario: BIOCRS-4047 Verify state of Manual Operation tab when Recipe execution is in progress
    Given I expand recipe console in pnid
    And I load recipe "SinglePhaseRecipe"
    When I Process hold the system
    Then I verify Manual Operation tab is "disabled"
    Then I verify Recipe Run tab is "disabled"
    And I restart the Process hold
    Then I verify Manual Operation tab is "enabled"
    Then I verify Recipe Run tab is "enabled"
    
  #Wip   
  Scenario: BIOCRS-4049: Verify Run start behavioral transitions during Manual Operation run & post-Run modal timeout verification
    Given I expand recipe console in pnid
    When I select "Manual operation" tab
    And I start Manual run 
    Then I validate the timer and stop button and run details 
    When I process hold
    Then I should see Process restart button 
    And I validate the timer is incrementing
    When I Stop the RUN 
    Then I validate the date formats and enter comments
    And I wait for 1 min for the post run window to auto closed
    And I validate the Start button is displayed and disabled
    And I Process restart 
    And I validate the Start button is displayed and enabled 
    
    
    