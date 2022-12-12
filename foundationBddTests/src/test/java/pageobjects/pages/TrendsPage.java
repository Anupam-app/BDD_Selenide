package pageobjects.pages;


import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
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
import java.util.Locale;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import static pageobjects.utility.SelenideHelper.goToIFrame;


public class TrendsPage {
    private String XPATH_STARRED_ICON = "//label[contains(text(),'%s')]/parent::*//span[@class='starred-icon']";
    private String XPATH_GENERIC_ICON = "//label[contains(text(),'%s')]/parent::*//span[contains(@class,'icon')]";

    private SelenideElement StarredIcon = $(By.xpath(String.format(XPATH_GENERIC_ICON, "Starred")));
    private SelenideElement DefaultIcon = $(By.xpath(String.format(XPATH_GENERIC_ICON, "Default")));

    private ElementsCollection leddgerParametersCheckBox = $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li/input"));
    private ElementsCollection defaultCollectionParams = $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li"));

    private SelenideElement graphStartTime = $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[1]"));
    private SelenideElement graphLastTime = $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[last()]"));
    private SelenideElement trends = $(By.xpath("//*[contains(@class,'Trends')]"));
    private SelenideElement areaGraph_Text = $(By.xpath("//span[text() = 'Area Graph']"));
    private SelenideElement lineGraph_Text = $(By.xpath("//span[text() = 'Line Graph']"));
    private SelenideElement starred_Text = $(By.xpath("//label[text() ='Starred']"));
    private SelenideElement default_Text = $(By.xpath("//label[text() ='Default']"));
    private SelenideElement listOfCollection_Text = $(By.xpath("//label[text() ='List of collections']"));
    private SelenideElement saveAsCollections_Text = $(By.xpath("//*[text()='Save as Collection']"));

    private SelenideElement footerValidation = $(By.xpath("//div[@class = 'chart-footer']"));
    private SelenideElement overlay = $(By.xpath("//input[@class='ant-radio-button-input'][@value='Overlay']"));
    private SelenideElement stacked = $(By.xpath("//input[@class='ant-radio-button-input'][@value='Stacked']"));
    private SelenideElement selectInterval = $(By.xpath("//span[text()='Select interval']"));
    private SelenideElement live = $(By.xpath("//*[contains(@id,'Live')]"));
    private SelenideElement entireRun = $(By.id("Entire run"));
    private SelenideElement startdate = $(By.xpath("//div[(@class=\"ant-picker-input ant-picker-input-active\")]"));
    private SelenideElement end_date = $(By.xpath("//*[(@placeholder='End date')]"));
    private SelenideElement donwload = $(By.xpath("//*[(@class='ant-btn ant-btn-primary ant-dropdown-trigger download-button')]"));


    private SelenideElement starred_Collection = $(By.xpath("//input[@id='option1' and @value='Starred']"));
    private SelenideElement chartAreaGraphMessage = $(By.xpath("//*[@class = 'noselection-component' and text() = 'You currently have no selections to display.']"));
    private SelenideElement validateGraph = $(By.xpath("//*[@class='highcharts-graph']"));
    private String tagLabel = "//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li[%s]/label";
    private String collection_radiobutton = "//input[@id='option1' and @value='%s']";
    private SelenideElement Starredbtn = $(By.xpath("//input[@id='option1' and @value='Starred']"));
    private String listOfStarredstaricons = "//input[@id='option1' and @value='Starred']/parent::button/following-sibling::div//li[@title='%s']/span[2]";
    private String collectionNameRadioButton = "//input[@type = 'radio' and @id ='option1' and @value ='%s' ]";

    private SelenideElement ArrowOfListOfCollection = $(By.xpath("//input[@value='List of collections']/following-sibling::span[contains(@class,'icon')]"));
    private SelenideElement deleteCollection = $(By.xpath("//span[@class='delete-collection']"));
    private SelenideElement deleteCollectionButton = $(By.xpath("//button[@type='button' and contains(text(),'Delete')]"));
    private String ledgerParam = "//*[local-name ()='g']//*[contains(text(),'%s')]";

    private SelenideElement trendsCollapseArrow = $(By.xpath("//span[@class ='icon-arrow']"));
    private SelenideElement trendsExpandArrow = $(By.xpath("//span[@class ='icon-arrow-expand']"));
    private SelenideElement collectionName = $(By.xpath("//input[(@class='collection-name')]"));
    private SelenideElement collectionCreate = $(By.xpath("//*[@class='ant-btn ant-btn-primary btn-saveas-collection']"));
    private SelenideElement starredNullParameters = $(By.xpath("//input[@id='option1' and @value='Starred']/parent::button/following-sibling::div//li"));

    private SelenideElement starredCollapseArrow = $(By.xpath("//label[contains(text(),'Starred')]/parent::*//span[@class ='collpase-arrow-icon']"));
    private SelenideElement starredExpandArrow = $(By.xpath("//label[contains(text(),'Starred')]/parent::*//span[@class ='collpase-expand-icon']"));

    private SelenideElement defaultCollapseArrow = $(By.xpath("//label[contains(text(),'Default')]/parent::*//span[@class ='collpase-arrow-icon']"));
    private SelenideElement defaultExpandArrow = $(By.xpath("//label[contains(text(),'Default')]/parent::*//span[@class ='collpase-expand-icon']"));

    private String nameOfListCollection = "//input[@name= 'selection1' and @class='trends-option' and @value ='%s']";

    private SelenideElement starredLabel = $(By.xpath("(//button//label)[1]"));
    private String checkboxDefaultCollection = "//li[@title='%s']//input";
    private SelenideElement graphLastSecondTime = $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[last()-1]"));

    private SelenideElement defaultButton = $(By.xpath("(//button[@class='trends-parameters']//input)[2]"));
    private ElementsCollection deviceShapeElements = $$(By.xpath("(//div[@class='trends-sidebar']//ul//li//label)"));

    public void goToTrends() {
        trends.click();
        commonWaiter(trends, visible);
    }

    public void switchToFrame() {
        goToIFrame();
    }

    public void chartAreaGraph(String message) {
        commonWaiter(chartAreaGraphMessage, visible);
        chartAreaGraphMessage.shouldHave(text(message));
    }

    public void noFooterAvailable() {
        footerValidation.shouldNotBe(visible);
    }

    public void arrowCollapse(String name) {
        switch (name) {
            case "Trends_Area_Panel":
                trendsCollapseArrow.click();
                break;
            case "Starred_Collection":
                //go from expand to collapse
                SelenideHelper.commonWaiter(StarredIcon, visible);
                if (starredExpandArrow.isDisplayed()) {
                    starredExpandArrow.click();
                }
                break;
            case "Default_Collection":
                //go from expand to collapse
                SelenideHelper.commonWaiter(StarredIcon, visible);
                if (defaultExpandArrow.isDisplayed()) {
                    defaultExpandArrow.click();
                }
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
                trendsCollapseArrow.shouldNotBe(visible);
                break;
            case "Starred_Collection":
                starredCollapseArrow.should(visible);
                break;
            case "Default_Collection":
                defaultCollapseArrow.should(visible);
                break;
            case "List of Collection ":
                ArrowOfListOfCollection.should(visible);
                break;
            default:
        }

    }

    public void arrowExpand(String name) {
        switch (name) {
            case "Trends_Area_Panel":
                trendsExpandArrow.click();
                break;
            case "Starred_Collection":
                if (SelenideHelper.commonWaiter(starredCollapseArrow, visible).isDisplayed()) {
                    starredCollapseArrow.click();
                }
                break;
            case "Default_Collection":
                if (SelenideHelper.commonWaiter(defaultCollapseArrow, visible).isDisplayed()) {
                    defaultCollapseArrow.click();
                }
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
                trendsExpandArrow.shouldNotBe(visible);
                break;
            case "Starred_Collection":
                starredExpandArrow.shouldBe(visible);
                break;
            case "Default_Collection":
                defaultExpandArrow.shouldBe(visible);
                break;
            case "List of Collection ":
                ArrowOfListOfCollection.shouldBe(visible);
                break;
            default:
        }
    }

    public void selectRadioButton(String btn) {
        $(By.xpath(String.format(collection_radiobutton, btn))).click();
    }

    public void selectMultipleCheckbox(String tag1, String tag2) throws Exception {
        $(By.xpath(String.format(collection_radiobutton, "Default"))).click();
        $(By.xpath(String.format(checkboxDefaultCollection, tag1))).click();
        $(By.xpath(String.format(checkboxDefaultCollection, tag2))).click();
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
        commonWaiter(validateGraph, visible);
    }

    public void footerValidation(String options) {

        switch (options) {
            case "Save as Collection":
                saveAsCollections_Text.shouldBe(visible);
                break;
            case "Overlay":
                overlay.shouldBe(not(selected));
                break;
            case "Staked":
                stacked.shouldBe(selected);
                break;
            case "Entire run":
                entireRun.shouldBe(visible);
                break;
            case "selectInterval":
                selectInterval.shouldBe(visible);
                break;
            case "Live":
                live.shouldBe(visible);
                break;
            case "start date":
                startdate.shouldBe(visible);
                break;
            case "end date":
                end_date.shouldBe(visible);
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
        ArrowOfListOfCollection.click();
        $(By.xpath(String.format(collectionNameRadioButton, name))).click();
    }

    public void chooseCollection(String name) {
        commonWaiter($(By.xpath(String.format(nameOfListCollection, name))), visible).click();
    }

    public void defaultCollectionTagsValidation(String parameters) {
        int count = defaultCollectionParams.size();
        List<String> acceptedParams = new ArrayList<String>();
        List<String> expectedParams = new ArrayList<String>();
        for (int i = 1; i <= count; i++) {
            acceptedParams.add($(By.xpath(String.format(tagLabel, i))).getText());
        }

        var config = ConfigFactory.parseResourcesAnySyntax(parameters, ConfigParseOptions.defaults());
        var params = config.getConfigList("Params.list");
        int paramsSize = params.size();
        for (var param : params) {
            expectedParams.add(param.getString("value"));
        }
        if ((count == paramsSize) && (count != 0 && paramsSize != 0)) {
            Collections.sort(acceptedParams);
            Collections.sort(expectedParams);
            Assert.assertEquals(acceptedParams, expectedParams);
        }
    }

    public void iSelectStarIcon(String trendParam) {
        var starOption = $(By.xpath(String.format(XPATH_STARRED_ICON, trendParam)));
        starOption.click();
    }

    public void unSelectStarParam(String param1, String param2) {
        uncheckParam(param1);
        uncheckParam(param2);
    }

    private void uncheckParam(String param) {
        var paramElement = $(By.xpath(String.format(listOfStarredstaricons, param)));
        paramElement.click();
        commonWaiter(paramElement, not(visible));
    }

    public void ledgerParameterOnChartArea(String param) {
        commonWaiter($(By.xpath(String.format(ledgerParam, param))), Condition.visible);
    }

    public void noParametersStarred() {
        starredNullParameters.waitWhile(not(visible), 20);
        starredNullParameters.shouldNotBe(visible);
    }

    public void deleteCollection() {
        deleteCollection.click();
        deleteCollectionButton.click();
    }

    public void graphTime() throws ParseException {
        String startTimeString = commonWaiter(graphStartTime, visible).getText().replaceAll("\\s", "");

        String lastTimeString = graphLastTime.getText();
        if (lastTimeString.length() != 20) {
            lastTimeString = graphLastSecondTime.getText();
        }
        lastTimeString = lastTimeString.replaceAll("\\s", "");

        SimpleDateFormat format = new SimpleDateFormat("MMMd,yyyy,HH:mm:ssa", Locale.US);
        Date startTime = format.parse(startTimeString);
        Date lastTime = format.parse(lastTimeString);

        if(lastTime.getHours()==0){
            lastTime.setHours(24);
        }

        long difference = Math.abs((lastTime.getTime() - startTime.getTime())) / (60 * 1000);

        Assert.assertTrue(String.format("difference greater than 45 minutes :%s for dates between %s and %s",
                difference, lastTimeString, startTimeString), difference >= 45);

        Assert.assertTrue(String.format("difference lower than 1 hour :%s for dates between %s and %s",
                difference, lastTimeString, startTimeString), difference <= 60);
    }

    public void seeContent(String expectedText) {
        SelenideHelper.commonWaiter(starredLabel, Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    public List<String> getDeviceShapeElementNotLoaded() {
        SelenideHelper.commonWaiter(defaultButton, Condition.visible).click();
        return I18nUtils.getElementsNotI18N(deviceShapeElements);
    }
}






