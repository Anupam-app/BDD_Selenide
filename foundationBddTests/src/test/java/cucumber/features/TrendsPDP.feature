Feature: Trends PDP

  @IVI-7185
  Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the Trends Panel-3
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I choose "<Param1>","<Param2>" parameters as default collection
    Then I see the Live graph is display
    And  I see the availability of below footer
      | options            |
      | Save as Collection |
      | Staked             |
      | Overlay            |
      | Live               |
      | Entire run         |
      | Current run        |
      | start date         |
      | end date           |
      | download           |
      | selectInterval     |

    @CRS
    Examples:
      | Param1   | Param2   |
      | PI101 PV | PI102 PV |

    @IVI @IVI-7185
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |

  @IVI-7185
  Scenario Outline: BIOCRS-5482 BIOCRS-1405 | Verify the Trends layout | Verify the chart area -list of collection
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I choose "<Param1>","<Param2>" parameters as default collection
    And I save as trends collections
    And I choose collection
    Then I see "<Param1>","<Param2>" parameters displayed
    And I see the graph is plotted for selected parameters in chart area "<Param1>","<Param2>"
    And I delete the collection name

    @CRS
    Examples:
      | Param1   | Param2   |
      | PI101 PV | PI102 PV |

    @IVI
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |

  @BIOCRS-9267
  Scenario Outline: BIOCRS-1405 | Save the collection from -list of collection
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I choose "<Param1>","<Param2>" parameters as default collection
    And I save as trends collections
    And I choose collection
    And I see "<Param1>","<Param2>" parameters displayed
    And I uncheck "<Param1>"
    Then I see the graph is plotted for selected parameters in chart area "<Param2>"
    And I delete the collection name

    @CRS
    Examples:
      | Param1   | Param2   |
      | PI101 PV | PI102 PV |

    @IVI
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |

  Scenario Outline: BIOCRS-1405 | remove 1 parameter while saving collection
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I choose "<Param1>","<Param2>" parameters as default collection
    And I remove "<Param1>" and save collections
    And I choose collection
    Then I see "<Param2>" parameters displayed
    And I delete the collection name

    @CRS
    Examples:
      | Param1   | Param2   |
      | PI101 PV | PI102 PV |

    @IVI @IVI-7185
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |

  Scenario Outline: BIOCRS-5482 BIOCRS-1405 | Duplicate Trends collection
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I choose "<Param1>","<Param2>" parameters as default collection
    And I save as trends collections
    And I choose "<Param3>","<Param4>" parameters as default collection
    And I save as trends collections as in step 4
    Then I see the error message "Collection Name already exist" on collection name window
    And I delete the collection name

    @CRS
    Examples:
      | Param1   | Param2   | Param3   | Param4  |
      | PI101 PV | PI102 PV | PI103 PV | TMP1 PV |

    @IVI
    Examples:
      | Param1          | Param2          | Param3      | Param4           |
      | P001 - Speed PV | P002 - Speed PV | P001 - OUT% | P001 - Manual SP |

  Scenario Outline: BIOCRS-1405 | To check the list of parameter groupings (collections)  are user specific.
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I choose "<Param1>","<Param2>" parameters as default collection
    And I save as trends collections
    Then I see "<Param1>","<Param2>" parameters displayed
    And I logout
    And I am logged in as "Bio4cService" user
    And I navigate to trends page
    When I choose "<Param1>","<Param2>" parameters as default collection
    And I save as trends collections as in step 4
    And I go to list of collection
    And I delete the collection name
    And I logout
    And I am logged in as "Bio4CAdmin" user
    And I navigate to trends page
    And I go to list of collection
    Then I delete the collection name

#    @CRS
#    Examples:
#      | Param1   | Param2   |
#      | PI101 PV | PI102 PV |

    @IVI
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |

  Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the chart area -Default collections
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I select the "Default" collection
    And I choose "<Param1>","<Param2>" parameters
    Then I see the graph is plotted for selected parameters in chart area "<Param1>","<Param2>"

    @CRS
    Examples:
      | Param1   | Param2   |
      | PI101 PV | PI102 PV |

    @IVI @IVI-7185
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |


  Scenario Outline: BIOCRS-5483 | Verify Default Collection in Trends | Default Trends Parameters List
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I select the "Default" collection
    Then I verify default list of "<parameters>"

    @CRS
    Examples:
      | parameters                  |
      | parameters/crs/trendsParams |

    @IVI
    Examples:
      | parameters                  |
      | parameters/ivi/trendsParams |


  Scenario Outline: BIOCRS-5483 | Verify Default Collection in Trends | Starred Trends Parameters List
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page
    When I select the "Default" collection
    And I select star icons for "<Param1>","<Param2>" parameters
    And I save as trends collections
    And I select the "Starred" collection
    And I see the graph is plotted for selected parameters in chart area "<Param1>","<Param2>"
    And  I unselect the star icons for "<Param1>","<Param2>" parameters
    Then I validate no parameters are present in starred collection

    @CRS
    Examples:
      | Param1   | Param2   |
      | PI101 PV | PI102 PV |

    @IVI @IVI-7185
    Examples:
      | Param1          | Param2          |
      | P001 - Speed PV | P002 - Speed PV |