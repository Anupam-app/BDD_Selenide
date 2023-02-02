Feature: Alarm creation

  @wip
  Scenario: Alarm acknowledgements
    Given I am logged in as "bio4cadmin" user
    When I navigate to alarms page
    Then I acknowledge the alarm

