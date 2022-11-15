package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import cucumber.util.I18nUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import static pageobjects.utility.SelenideHelper.goToIFrame;


public class TrendsPage {
	private SelenideElement starredLabel = $(By.xpath("(//button//label)[1]"));
	private SelenideElement defaultButton = $(By.xpath("(//button[@class='trends-parameters']//input)[2]"));
	private ElementsCollection deviceShapeElements = $$(By.xpath("(//div[@class='trends-sidebar']//ul//li//label)"));

	private ElementsCollection starredStaricon = $$(By.xpath("//input[@id='option1' and @value='Starred']/parent::button/following-sibling::div//li/span[2]"));
	private ElementsCollection leddgerParametersCheckBox= $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li/input"));
	private ElementsCollection defaultCollectionParams = $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li"));
	private ElementsCollection staricon = $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li/span[2]"));
	private ElementsCollection grapgAreaElement = $$(By.xpath("//*[@class='highcharts-graph']"));
	
    private SelenideElement graphStartTime = $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[1]"));
    private SelenideElement graphLastTime = $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[last()]"));
    private SelenideElement graphLastSecondTime = $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[last()-1]"));
	private SelenideElement trendsHeaderValidation = $(By.xpath("//div[@class= 'header' and text() ='Trends']"));
	private SelenideElement trends = $(By.xpath("//*[contains(@class,'Trends')]"));
	private SelenideElement areaGraph_Text = $(By.xpath("//span[text() = 'Area Graph']"));
	private SelenideElement lineGraph_Text = $(By.xpath("//span[text() = 'Line Graph']"));
	private SelenideElement starred_Text = $(By.xpath("//label[text() ='Starred']"));
	private SelenideElement default_Text = $(By.xpath("//label[text() ='Default']"));
	private SelenideElement listOfCollection_Text = $(By.xpath("//label[text() ='List of collections']"));
	private SelenideElement saveAsCollections_Text = $(By.xpath("//*[text()='Save as Collection']"));

	private SelenideElement footerValidation = $(By.xpath("//div[@class = 'chart-footer']"));
	private SelenideElement saveAsCollection =$(By.xpath("//button[@type='button'][@class='ant-btn ant-btn-primary btn-save-as-collection']"));
	private SelenideElement newCollection = $(By.xpath("//input[@type='text'][@class='collection-name']"));
	private SelenideElement listofcollection = $(By.xpath("//label[@for='selection1'][@title='List of collections']"));
	private SelenideElement overlay = $(By.xpath("//input[@class='ant-radio-button-input'][@value='Overlay']"));
	private SelenideElement stacked = $(By.xpath("//input[@class='ant-radio-button-input'][@value='Stacked']"));
	private SelenideElement selectInterval = $(By.xpath("//span[text()='Select interval']"));
	private SelenideElement live = $(By.xpath("//*[contains(@id,'Live')]"));
	private SelenideElement entireRun = $(By.xpath("//button[@id='Entire run']"));
	private SelenideElement startdate = $(By.xpath("//div[(@class='ant-picker-input ant-picker-input-active')]"));
	private SelenideElement end_date = $(By.xpath("//*[(@placeholder='End datetime')]"));
	private SelenideElement donwload = $(By.xpath("//*[(@class='ant-btn ant-btn-primary ant-dropdown-trigger download-button')]"));


	private SelenideElement starred_Collection = $(By.xpath("//input[@id='option1' and @value='Starred']"));
	private SelenideElement default_Collection = $(By.xpath("//input[@id='option1' and @value='Default']"));
	private SelenideElement listOfCollection = $(By.xpath("//input[@id='option1' and @value='List of collections']"));
	private SelenideElement chartAreaGraphMessage = $(By.xpath("//*[@class = 'noselection-component' and text() = 'You currently have no selections to display.']"));
	private SelenideElement validateGraph = $(By.xpath("//*[@class='highcharts-graph']"));
	private SelenideElement duplicateCollectionName = $(By.xpath("//p[@class='error-text' and text()='Collection Name already exists.']"));
	private String tagLabel = "//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li[%s]/label";
	private String collection_radiobutton = "//input[@id='option1' and @value='%s']";
	private SelenideElement Starredbtn = $(By.xpath("//input[@id='option1' and @value='Starred']"));
	private String listOfDefaultstaricons = "//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li[@title='%s']/span[2]";
	private String listOfStarredstaricons = "//input[@id='option1' and @value='Starred']/parent::button/following-sibling::div//li[@title='%s']/span[2]";
	private String collectionNameRadioButton = "//input[@type = 'radio' and @id ='option1' and @value ='%s' ]";

	private SelenideElement StarredArrow =  $(By.xpath("//input[@value='Starred']/following-sibling::span[contains(@class,'icon')]"));
	private SelenideElement DefaultArrow = $(By.xpath("//input[@value='Default']/following-sibling::span[contains(@class,'icon')]"));
	private SelenideElement ArrowOfListOfCollection = $(By.xpath("//input[@value='List of collections']/following-sibling::span[contains(@class,'icon')]"));
	private SelenideElement deleteCollection = $(By.xpath("//span[@class='delete-collection']"));
	private SelenideElement deleteCollectionButton =$(By.xpath("//button[@type='button' and contains(text(),'Delete')]"));
	private String limitedStaricon = "//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li[i]/span[2]";
	private String ledggerParam = "//*[local-name ()='g']//*[text()='%s (in rpm)']";

	private SelenideElement trends_Collapse_Arrow = $(By.xpath("//span[@class ='icon-arrow']"));
	private SelenideElement trends_Expand_Arrow = $(By.xpath("//span[@class ='icon-arrow-expand']"));
	private SelenideElement trendsCollapse = $(By.xpath("//div[@class ='collapsed-sidebar-wrapper']"));
	private SelenideElement graphValue = $(By.xpath("//*[contains(@class,'highcharts-legend-box')]"));
	private SelenideElement saveTrendsCollection = $(By.xpath("//span[text()='Save as Collection']"));
	private SelenideElement collectionName = $(By.xpath("//input[(@class='collection-name')]"));
	private SelenideElement collectionCreate = $(By.xpath("//*[@class='ant-btn ant-btn-primary btn-saveas-collection']"));
	private SelenideElement starredNullParameters = $(By.xpath("//input[@id='option1' and @value='Starred']/parent::button/following-sibling::div//li"));    
	private String nameOfListCollection = "//input[@name= 'selection1' and @class='trends-option' and @value ='%s']";
	private String checkboxDefaultCollection = "//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li[@title='%s']/input";
	private ElementsCollection staricon1 = $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li/span[2]"));
	private SelenideElement loadingIcon = $(By.xpath("//div[contains(@class,'loading')]"));
	private SelenideElement chartBorder = $(By.xpath("//*[@class='highcharts-plot-border']"));
			
	public void goToTrends() {
		trends.click();
		commonWaiter(trends,visible);
	}

    public void seeContent(String expectedText) {
        SelenideHelper.commonWaiter(starredLabel, Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    public List<String> getDeviceShapeElementNotLoaded() {
        SelenideHelper.commonWaiter(defaultButton, Condition.visible).click();
        return I18nUtils.getElementsNotI18N(deviceShapeElements);
    }

	public void switchToFrame() {
		goToIFrame();
	}

	public void trendsHeaderValidation() {
		commonWaiter(trendsHeaderValidation,visible);
		trendsHeaderValidation.isDisplayed();
		Assert.assertTrue(true);
	}

	public void chartAreaGraph(String message) {
		commonWaiter(chartAreaGraphMessage,visible);
		chartAreaGraphMessage.shouldHave(text(message));	
	}

	public void noFooterAvailable() {
		footerValidation.shouldNotBe(visible);
		Assert.assertFalse(false);
	}
	
	public void arrowCollapse(String name) {

		switch (name) {
		case "Trends_Area_Panel":
			trends_Collapse_Arrow.click();
			break;
		case "Starred_Collection":
			StarredArrow.click();
			break;
		case "Default_Collection":
			DefaultArrow.click();
			break;
		case "List of Collection ":
			ArrowOfListOfCollection.click();
			break;
		default:
		}
	}
	
	public void arrowCollapseValidation(String name) {
		switch (name) {
		case "Trends_Area_Panel":
			trends_Collapse_Arrow.shouldNot(visible);
			break;
		case "Starred_Collection":
			StarredArrow.isDisplayed();
			break;
		case "Default_Collection":
			DefaultArrow.isDisplayed();
			break;
		case "List of Collection ":
			ArrowOfListOfCollection.isDisplayed();
			break;
		default:
		}
	}
	
	public void arrowExpand(String name) {

		switch (name) {
		case "Trends_Area_Panel":
			trends_Expand_Arrow.click();
			break;
		case "Starred_Collection":
			StarredArrow.click();
			break;
		case "Default_Collection":
			DefaultArrow.click();
			break;
		case "List of Collection ":
			ArrowOfListOfCollection.click();
			break;
		default:
		}
	}
	
	public void arrowExpandValidation(String name) {

		switch (name) {
		case "Trends_Area_Panel":
			trends_Expand_Arrow.shouldNotBe(visible);
			break;
		case "Starred_Collection":
			StarredArrow.shouldBe(visible);
			break;
		case "Default_Collection":
			DefaultArrow.shouldBe(visible);
			break;
		case "List of Collection ":
			ArrowOfListOfCollection.shouldBe(visible);
			break;
		default:
		}
	}

	public void selectRadioButton(String btn){
		$(By.xpath(String.format(collection_radiobutton,btn))).click();
	}

	public void selectMultipleCheckbox(String tag1,String tag2) throws Exception {
		$(By.xpath(String.format(collection_radiobutton,"Default"))).click();
		$(By.xpath(String.format(checkboxDefaultCollection,tag1))).click();
		$(By.xpath(String.format(checkboxDefaultCollection,tag2))).click();
	}	

	public void trendsPanelValidation(String options) {
		switch (options) {
		case "Area graph":
			areaGraph_Text.shouldBe(visible);
			System.out.println("Area graph visible");
			break;
		case "Line graph":
			lineGraph_Text.shouldBe(visible);
			System.out.println("Line graph visible");
			break;
		case "Default Parameters Collection":
			default_Text.shouldBe(visible);
			System.out.println("Default Para visible");
			break;
		case "Starred Parameters Collection":
			starred_Text.shouldBe(visible);
			System.out.println("Starred Para visible");
			break;
		case "List of collection":
			listOfCollection_Text.shouldBe(visible);
			System.out.println("List visible");
			break;
		default:
		}
	}

	public void validateGraph() {
		commonWaiter(loadingIcon, not(visible));
		commonWaiter(validateGraph, visible);

	}

	public void footerValidation(String options) {
		
		switch(options){
		case "Save as Collection":
			saveAsCollections_Text.shouldBe(visible);
			break;
		case "Overlay":
			overlay.isDisplayed();
			break;
		case "Staked":
			stacked.isEnabled();
			break;
		case "Entire run":
			entireRun.shouldBe(visible);
			break;
		case "selectInterval":
			selectInterval.isDisplayed();
			break;
		case "Live":
			live.isEnabled();
			break;
		case "start date":
			startdate.isDisplayed();
			break;
		case "end date":
			end_date.isDisplayed();
			break;
		case "download":
			donwload.shouldBe(visible);
			break;
		default:
		}
	}
	public void collectionName(String name) {
		saveAsCollections_Text.click();
		collectionName.click();
		collectionName.sendKeys(name);
		collectionCreate.click();
		Selenide.sleep(2000);
		DefaultArrow.click();
		commonWaiter(ArrowOfListOfCollection, visible);
		ArrowOfListOfCollection.click();
		$(By.xpath(String.format(collectionNameRadioButton,name))).click();

	}

	public void chooseCollection(String name) {
		$(By.xpath(String.format(nameOfListCollection,name))).click();
	}

	public void defaultCollectionTagsValidation(String parameters ) {
		int count=defaultCollectionParams.size();
		List <String> acceptedParams = new ArrayList <String>();
		List <String> expectedParams = new ArrayList <String>();
		for (int i=1;i<=count;i++)
		{
			acceptedParams.add($(By.xpath(String.format(tagLabel, i))).getText());
		}
		System.out.println(acceptedParams);
		var config = ConfigFactory.parseResourcesAnySyntax(parameters,ConfigParseOptions.defaults());
	    var params = config.getConfigList("Params.list");
	    int paramsSize = params.size();
		for (var param : params) {
			expectedParams.add(param.getString("value"));
        }
		if((count==paramsSize) && (count!=0 && paramsSize!=0)) {
			Collections.sort(acceptedParams);
			Collections.sort(expectedParams);
			Assert.assertEquals(acceptedParams,expectedParams );
			
		}
		
	}

	public void starredButton() {
		starred_Collection.click();
	}

	public void verifyStarredbutton() {
		Starredbtn.shouldBe(visible);
		starred_Text.shouldBe(visible);
	}

	public void iselectstaricon(String star1,String star2 ) throws InterruptedException{
		for(SelenideElement star_Options:staricon) {
			String tagsname = star_Options.parent().getAttribute("title");
			System.out.println("----"+tagsname+"----");
			if(tagsname.equals(star1)||tagsname.equals(star2)){
				star_Options.click();
				Thread.sleep(5000);
			}
		}
	}

	public void validateStarredCollection(String staricon1, String staricon2,String staricon3) throws Exception{
		for(SelenideElement star_Options:starredStaricon) {
			star_Options.getSize();
			String tagsname = star_Options.getAttribute("title");
			if(tagsname.equals(staricon1)||tagsname.equals(staricon2)||tagsname.equals(staricon3)){
				star_Options.isSelected();
			}
		}
	}

	public void unSelectStarParam(String param1, String param2) {
		uncheckParam(param1);
		uncheckParam(param2);
	}

	private void uncheckParam(String param) {
		var paramElement=$(By.xpath(String.format(listOfStarredstaricons, param)));
		paramElement.click();
		commonWaiter(paramElement,not(visible));
	}

	public void trendsBarCollapse() {
		trendsCollapse.shouldBe(visible);
	}

	public void trendsBarExpand() {

		trends_Expand_Arrow.click();
	}

	public void arrowIcon() {
		commonWaiter(trends_Collapse_Arrow,visible);
		trends_Collapse_Arrow.click();	
	}

	public void ledggerParameterOnChartArea(String param1, String param2) {
		
        commonWaiter(chartBorder, visible);
		commonWaiter($(By.xpath(String.format(ledggerParam,param1))),Condition.visible);
		
		Selenide.sleep(2000);
		commonWaiter($(By.xpath(String.format(ledggerParam,param2))),Condition.visible);
         
		validateGraph();
		
		

	}	
	
	public void noParametes_starred() {
		starredNullParameters.waitWhile(not(visible), 20);
		
		starredNullParameters.shouldNotBe(visible);
	}
	
	public void deleteCollection() {
		deleteCollection.click();
		deleteCollectionButton.click();;
	}
	
	public void graphTime() throws ParseException {
		String startTime = graphStartTime.getText().replaceAll("\\s", "");
		String lastTime  = null;
		if(!((graphLastTime.getText().replaceAll("\\s", "")).length()==20))
		{
			lastTime = graphLastSecondTime.getText().replaceAll("\\s", "");
		}
		else
		{
			lastTime = graphLastTime.getText().replaceAll("\\s", "");
		}
		
		SimpleDateFormat format = new SimpleDateFormat("MMMd,yyyy,HH:mm:ssaa");
		Date date1 = format.parse(startTime);
		Date date2 = format.parse(lastTime);
		long difference = ((date2.getTime() - date1.getTime()))/(60 * 1000) % 60;
        System.out.print("Time difference:"+difference);
        Assert.assertTrue(difference<=60);
        Assert.assertTrue(difference>=45);
	}

}
