Feature: Analytics creation

  Scenario Outline: Aggregate creation
    Given I am logged in as "bio4cadmin" user
    And I load recipe "testRecipeToExecute" and run it during 15 seconds
    When I create an analytics aggregate
    And I use the recipe for this analytics aggregate with interval "Second"
    And I choose "<Param1>" analytics parameter with unit "<Unit1>" as "x" axis
    And I choose "<Param2>" analytics parameter with unit "<Unit2>" as "y" axis
    And I choose "<Param3>" analytics parameter with unit "<Unit3>" as "y" axis
    And I validate the analytics creation
    Then I see my changes in analytics aggregate

    @CRS
    Examples:
      | Param1          | Unit1 | Param2          | Unit2 | Param3         | Unit3 |
      | PI101 PV        | psi   | PI102 PV        | psi   | PI103 PV       | psi   |

    @IVI @SMOKE
    Examples:
      | Param1          | Unit1 | Param2          | Unit2 | Param3         | Unit3 |
      | P001 - Speed PV | psi   | P002 - Speed PV | psi   | P003 - Speed PV| rpm   |