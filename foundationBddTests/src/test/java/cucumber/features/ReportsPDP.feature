Feature: Report administration Based on PDP

  Scenario Outline: BIOCRS-5240| Select more than 5 trends parameters
    Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
    And I trigger report template mode
    When I create random report template
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I select report include "Alarms"
    And I select report include "Trends"
    And I choose "6" trends "<parameters>"
    Then I verify the error message "Maximum of 5 sensors allowed"

    @CRS
    Examples:
      | parameters                  |
      | parameters/crs/trendsParams |

    @IVI
    Examples:
      | parameters                  |
      | parameters/ivi/trendsParams |

  @SMOKE
  Scenario Outline: Generate and sign a custom report
    Given I am logged in as "Bio4CAdmin" user
    And I goto report management page
    When I select report from dropdown "Custom"
    And I select report include "Audit Trail"
    And I select report include "Run Summary"
    And I select report include "Alarms"
    And I select report include "Trends"
    And I choose "5" trends "<parameters>"
    And I save trends
    And I click on generate button
    And I goto report management page
    And I trigger report mode
    And I esign the report
    Then I should see the report signed
    And I should see the report file presence

    @CRS
    Examples:
      | parameters                  |
      | parameters/crs/trendsParams |

    @IVI
    Examples:
      | parameters                  |
      | parameters/ivi/trendsParams |