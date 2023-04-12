Feature: Alarm creation

  Scenario: Alarm acknowledgement
    Given I am logged in as "bio4cadmin" user
    When I navigate to alarms page
    Then I acknowledge the alarm

