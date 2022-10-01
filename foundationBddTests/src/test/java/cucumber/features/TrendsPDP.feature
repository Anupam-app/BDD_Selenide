Feature: Trends Management

  Background:
    Given I am logged in as "bio4cAdmin" user
    And I navigate to trends page

  Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the Trends Panel-3
    When I choose "<Param1>","<Param2>" parameters as default collection
    Then I see the Live graph is display
    And  I see the availability of below footer
      |options			   |
      |Save as Collection |
      |Staked             |
      |Overlay            |
      |Live               |
      |Entire run         |
      |Current run        |
      |start date		   |
      |end date		   |
      |download		   |
      |selectInterval	   |

    @CRS
    Examples:
      |Param1   |Param2   |
      |PI101 PV |PI102 PV |
                                                   
    @IVI
    Examples:
      |Param1          |Param2          |
      |P001 - Speed PV |P002 - Speed PV |
							  
    
  Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the chart area -list of collection
    When I choose "<Param1>","<Param2>" parameters as default collection
    And I save as trends collections called "test"
    And I choose collection name as "test"
    Then I see the graph is plotted for selected parameters in chart area"<Param1>","<Param2>"
    And I delete the collection name
                              
    @CRS
    Examples:
      |Param1   |Param2   |
      |PI101 PV |PI102 PV |
							  
    @IVI
    Examples:
      |Param1          |Param2          |
      |P001 - Speed PV |P002 - Speed PV |
	
  Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the chart area -Default collections
    When I select the "Default" collection
    And I choose "<Param1>","<Param2>" parameters
    Then I see the graph is plotted for selected parameters in chart area"<Param1>","<Param2>"
			                  
    @CRS
    Examples:
      |Param1   |Param2   |
      |PI101 PV |PI102 PV |
							  
    @IVI
    Examples:
      |Param1          |Param2          |
      |P001 - Speed PV |P002 - Speed PV |
							  
                            
  Scenario Outline: BIOCRS-5483 | Verify Default Collection in Trends | Default Trends Parameters List
    When I select the "Default" collection
    Then I verify default list of "<parameters>"
           
    @CRS
    Examples:
      |parameters                    |
      |parameters/crs/trendsParams   |
            
    @IVI
    Examples:
      |parameters                    |
      |parameters/ivi/trendsParams   |

         
  Scenario Outline: BIOCRS-5483 | Verify Default Collection in Trends | Starred Trends Parameters List
    When I select the "Default" collection
    And I select star icons for "<Param1>","<Param2>" parameters
    And I select the "Starred" collection
    And I see the graph is plotted for selected parameters in chart area"<Param1>","<Param2>"
    And  I unselect the star icons for "<Param1>","<Param2>" parameters
    Then I validate no parameters are present in starred collection
				
    @CRS
    Examples:
      |Param1   |Param2   |
      |PI101 PV |PI102 PV |
							  
    @IVI
    Examples:
      |Param1          |Param2          |
      |P001 - Speed PV |P002 - Speed PV |
