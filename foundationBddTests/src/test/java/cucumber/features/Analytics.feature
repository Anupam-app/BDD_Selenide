Feature: Analytics management

  Background: 
    Given the browser "Chrome" is open
    And I am logged in as "bio4cadmin" user
    And I expand recipe console in pnid
    And I load recipe "testRecipeToExecute"
    And I start recipe execution

  Scenario: aggregate creation
    Given I go to analytics
    When I create an analytics aggregate using button
    And I use the recipe for this analytics aggregate with interval "Weekly"
    And I choose "PI101 PV" analytics parameter as "x" axis
    And I choose "PI102 PV" analytics parameter as "y" axis
    And I choose "PI103 PV" analytics parameter as "y" axis
    And I validate the analytics creation
    Then I see my changes in analytics aggregate