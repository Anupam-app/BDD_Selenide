@COMMON
Feature: Trends Management

Background:
      Given I am logged in as "bio4cadmin" user
      And I navigate to trends page
           
Scenario: BIOCRS-5482 - Verify the Trends layout | Verify the Trends Page
          When I am on Trends Panel
          Then I see availability of Trends panel and chart area
          And footer section is not displayed.

Scenario Outline: BIOCRS-5482 - Verify the Trends layout | Verify the Trends Panel- 1
          When I collapse “<TrendPanelType>”
          Then I see the “<TrendPanelType>” collapsed
          When I expand “<TrendPanelType>”
          Then I see the “<TrendPanelType>” expanded
                              
        Examples:
                  |TrendPanelType      |
							    |Trends_Area_Panel   |
                  |Starred_Collection  |
                  |Default_Collection  |
                  |List of Collection  |

Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the Trends Panel-2  
           When I am on Trends Panel
           Then I see the availability of below options
           |Area graph                    |
           |Line graph                     |
           |Default Parameters Collection  |
           |Starred Parameters Collection  |
           |List of collection             |
 
			  
Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the Trends Panel-3               
          When I choose "<Param1>","<Param2>" parameters as default collection
          And  I see the availability of below options
               |Save as Collection |
               |Staked             |
               |Overlay            |
               |Live               |
               |Entire run         |
               |Current run        |
           And I see the Live option displays last 60 minutes data

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
          Then I see the graph is plotted for selected parameters in chart area
                              
				  @CRS
					Examples:
                |Param1   |Param2   |
                |PI101 PV |PI102 PV |
							  
					@IVI
					Examples:
                |Param1          |Param2         |
                |P001 - Speed PV |P002 - Speed PV |
		
Scenario Outline: BIOCRS-5482 | Verify the Trends layout | Verify the chart area -Default collections                         
           When I select the default collection
           And I choose "<Param1>","<Param2>" parameters
           Then I see the graph is plotted for selected parameters in chart area				
			                  
					 @CRS
					 Examples:
                 |Param1   |Param2   |
                 |PI101 PV |PI102 PV |
							  
					 @IVI
					 Examples:
                 |Param1          |Param2          |
                 |P001 - Speed PV |P002 - Speed PV |
							  
                              
Scenario: BIOCRS-5483 | Verify Default Collection in Trends | Default Trends Parameters List                
           When I select the default collection
           Then I verify default list of parameters

           
Scenario Outline: BIOCRS-5483 | Verify Default Collection in Trends | Starred Trends Parameters List                
           When I select the default collection
           And I select star icons for "<Param1>","<Param2>" parameters 
           And I select the starred collection
           And I see the graph is plotted for selected parameters in chart area
           And  I uselect the star icons for "<Param1>","<Param2>" parameters
           Then I validate no parameters are present in starred collection
				
				   @CRS
					 Examples:
						      |Param1   |Param2   |
							    |PI101 PV |PI102 PV |
							  
					 @IVI
					 Examples:
							  |Param1          |Param2          |
							  |P001 - Speed PV |P002 - Speed PV |
							  
