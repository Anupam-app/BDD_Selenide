Feature: Analytics PDP

  Scenario Outline: BIOCRS-1610 | Verify parameters list in analytics
    Given I am logged in as "bio4cAdmin" user
    When I create an analytics aggregate
    Then I verify default list of "<parameters>" in analytics

    @CRS
    Examples:
      | parameters                     |
      | parameters/crs/analyticsParams |

    @IVI
    Examples:
      | parameters                     |
      | parameters/ivi/analyticsParams |


 