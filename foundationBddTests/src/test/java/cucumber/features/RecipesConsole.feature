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
    And I click on jump step "2"
    And I click on abort button
    Then I should see the recipe run aborted
    And control should be on rerun button
