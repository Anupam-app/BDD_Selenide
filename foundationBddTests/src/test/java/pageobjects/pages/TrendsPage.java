package pageobjects.pages;


import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import static pageobjects.utility.SelenideHelper.goToIFrame;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.util.I18nUtils;
import pageobjects.components.SpinnerComponent;
import pageobjects.utility.SelenideHelper;


public class TrendsPage {

    private final String XPATH_STARRED_ICON = "//label[contains(text(),'%s')]/parent::*//span[@class='starred-icon']";
    private final String XPATH_LISTOFCOLLECTION_PARAMS =
            "(//input[@id='option1' and @value='%s']/parent::button/following-sibling::div//li/label)[%d]";
    private final ElementsCollection leddgerParametersCheckBox =
            $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li/input"));
    private final ElementsCollection defaultCollectionParams =
            $$(By.xpath("//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li"));

    private final SelenideElement graphStartTime =
            $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[1]"));
    private String XPATH_GENERIC_ICON = "//label[contains(text(),'%s')]/parent::*//span[contains(@class,'icon')]";
    private final SelenideElement graphLastTime =
            $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[last()]"));
    private final SelenideElement trends = $(By.xpath("//*[contains(@class,'Trends')]"));
    private SelenideElement StarredIcon = $(By.xpath(String.format(XPATH_GENERIC_ICON, "Starred")));
    private String ledgerParam = "//*[local-name ()='g']//*[contains(text(),'%s')]";
    private final SelenideElement areaGraph_Text = $(By.xpath("//span[text() = 'Area Graph']"));
    private final SelenideElement lineGraph_Text = $(By.xpath("//span[text() = 'Line Graph']"));
    private final SelenideElement starred_Text = $(By.xpath("//label[text() ='Starred']"));
    private final SelenideElement default_Text = $(By.xpath("//label[text() ='Default']"));
    private final SelenideElement listOfCollection_Text = $(By.xpath("//label[text() ='List of collections']"));
    private final SelenideElement saveAsCollections_Text = $(By.xpath("//button[text()='Save as Collection']"));

    private final SelenideElement footerValidation = $(By.xpath("//div[@class = 'chart-footer']"));
    private final SelenideElement overlay = $(By.xpath("//input[@class='ant-radio-button-input'][@value='Overlay']"));
    private final SelenideElement stacked = $(By.xpath("//input[@class='ant-radio-button-input'][@value='Stacked']"));
    private final SelenideElement selectInterval = $(By.xpath("//span[text()='Select interval']"));
    private final SelenideElement live = $(By.xpath("//*[contains(@id,'Live')]"));
    private final SelenideElement entireRun =
            $(By.xpath("//button[@id='Entire run'][@class='ant-btn btn-entire-batch']"));
    private final SelenideElement startdate =
            $(By.xpath("//div[(@class=\"ant-picker-input ant-picker-input-active\")]"));
    private final SelenideElement end_date = $(By.xpath("//*[(@placeholder='End datetime')]"));
    private final SelenideElement downloadButton =
            $(By.xpath("//*[(@class='ant-btn ant-btn-primary ant-dropdown-trigger download-button')]"));

    private final SelenideElement chartAreaGraphMessage = $(By.xpath(
            "//*[@class = 'noselection-component' and text() = 'You currently have no selections to display.']"));
    private final SelenideElement validateGraph = $(By.xpath("//*[@class='highcharts-graph']"));
    private final String tagLabel =
            "//input[@id='option1' and @value='Default']/parent::button/following-sibling::div//li[%s]/label";
    private final String collection_radiobutton = "//input[@id='option1' and @value='%s']";
    private final String listOfStarredstaricons =
            "//input[@id='option1' and @value='Starred']/parent::button/following-sibling::div//li[@title='%s']/span[2]";
    private final String collectionNameRadioButton = "//input[@type = 'radio' and @id ='option1' and @value ='%s' ]";

    private final SelenideElement StarredArrow =
            $(By.xpath("//input[@value='Starred']/following-sibling::span[contains(@class,'icon')]"));
    private final SelenideElement DefaultArrow =
            $(By.xpath("//input[@value='Default']/following-sibling::span[contains(@class,'icon')]"));
    private final SelenideElement arrowOfListOfCollection =
            $(By.xpath("//input[@value='List of collections']/following-sibling::span[contains(@class,'icon')]"));
    private final SelenideElement deleteCollectionButton = $(By.xpath("//button[@type='button' and text()='Delete']"));
    private final String ledggerParam = "//*[local-name ()='g']//*[text()='%s (in psi)']";

    private final SelenideElement trendsCollapseArrow = $(By.xpath("//span[@class ='icon-arrow']"));
    private final SelenideElement trendsExpandArrow = $(By.xpath("//span[@class ='icon-arrow-expand']"));
    private final SelenideElement saveTrendsCollection = $(By.xpath("//*[text()='Save as Collection']"));
    private final SelenideElement collectionName = $(By.xpath("//input[(@class='collection-name')]"));
    private final SelenideElement collectionCreate =
            $(By.xpath("//*[@class='ant-btn ant-btn-primary btn-saveas-collection']"));
    private final SelenideElement starredNullParameters =
            $(By.xpath("//input[@id='option1' and @value='Starred']/parent::button/following-sibling::div//li"));

    private final SelenideElement starredCollapseArrow =
            $(By.xpath("//label[contains(text(),'Starred')]/parent::*//span[@class ='collpase-arrow-icon']"));
    private final SelenideElement starredExpandArrow =
            $(By.xpath("//label[contains(text(),'Starred')]/parent::*//span[@class ='collpase-expand-icon']"));

    private final SelenideElement defaultCollapseArrow =
            $(By.xpath("//label[contains(text(),'Default')]/parent::*//span[@class ='collpase-arrow-icon']"));
    private final SelenideElement defaultExpandArrow =
            $(By.xpath("//label[contains(text(),'Default')]/parent::*//span[@class ='collpase-expand-icon']"));
    private final SelenideElement trendsHeaderValidation = $(By.xpath("//div[@class= 'header' and text() ='Trends']"));
    private final String nameOfListCollection =
            "//input[@name= 'selection1' and @class='trends-option' and @value ='%s']";
    private final SelenideElement errorText = $(By.xpath("//p[@class='error-text']"));
    private final SelenideElement closeDialogue = $(By.xpath("//span[@class='close-dialog-icon']"));
    private final String XPATH_DELETE_COLLECTION =
            "//div[@class='coll-panel']/button/label[text()='%s']/following::span[@class='delete-collection']";
    private final String XPATH_PARAMETER_UNCHECK =
            "//div[@class='coll-panel']/button/label[text()='%s']/following::input[@value='%s']";
    private final SelenideElement expandListOfCollection = $(
            By.xpath("//label[(@title='List of collections')]/following-sibling::span[@class='collpase-expand-icon']"));

    private final SpinnerComponent spinnerComponent = new SpinnerComponent();

    private SelenideElement starredLabel = $(By.xpath("//label[contains(@title,'Starred')]"));
    private String checkboxDefaultCollection = "//li[@title='%s']//input";
    private SelenideElement graphLastSecondTime =
            $(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels']/*[last()-1]"));
    private SelenideElement defaultButton = $(By.xpath("(//button[@class='trends-parameters']//input)[2]"));
    private ElementsCollection deviceShapeElements = $$(By.xpath("(//div[@class='trends-sidebar']//ul//li//label)"));
    private String parameters = "//li[text()='%s']/span";
    private String downloadData = "//span[text()='%s']";

    public void goToTrends() {
        commonWaiter(trends, visible).click();
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
                // go from expand to collapse
                SelenideHelper.commonWaiter(StarredIcon, visible);
                if (starredExpandArrow.isDisplayed()) {
                    starredExpandArrow.click();
                }
                break;
            case "Default_Collection":
                // go from expand to collapse
                SelenideHelper.commonWaiter(StarredIcon, visible);
                if (defaultExpandArrow.isDisplayed()) {
                    defaultExpandArrow.click();
                }
                break;
            case "List of Collection ":
                arrowOfListOfCollection.click();
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
                arrowOfListOfCollection.should(visible);
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
                if (SelenideHelper.commonWaiter(starredCollapseArrow, visible)
                        .isDisplayed()) {
                    starredCollapseArrow.click();
                }
                break;
            case "Default_Collection":
                if (SelenideHelper.commonWaiter(defaultCollapseArrow, visible)
                        .isDisplayed()) {
                    defaultCollapseArrow.click();
                }
                break;
            case "List of Collection ":
                arrowOfListOfCollection.click();
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
                arrowOfListOfCollection.shouldBe(visible);
                break;
            default:
        }
    }

    public void selectRadioButton(String btn) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(collection_radiobutton, btn))), visible)
                .click();
    }

    public void selectMultipleCheckbox(String tag1, String tag2) {
        $(By.xpath(String.format(collection_radiobutton, "Default"))).click();
        for (SelenideElement allTags : leddgerParametersCheckBox) {
            String tagsName = allTags.parent()
                    .getAttribute("title");
            if (tagsName.equals(tag1) || tagsName.equals(tag2)) {
                allTags.click();
            }
        }
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

    public void downloadGraph(String option) {
        commonWaiter(downloadButton, visible).click();
        commonWaiter($(By.xpath(String.format(downloadData, option))), visible).click();
    }

    public void verifyDownloadGraph() {
        downloadButton.waitUntil(enabled, 5000);
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
                downloadButton.shouldBe(visible);
                break;
            default:
        }
    }

    public void collectionName(String name) {
        saveAsCollections_Text.click();
        collectionName.click();
        collectionName.sendKeys(name);
        Selenide.sleep(5000);
        collectionCreate.click();
        if (!expandListOfCollection.isDisplayed()) {
            arrowOfListOfCollection.click();
        }
        $(By.xpath(String.format(collectionNameRadioButton, name))).waitUntil(visible, 5000L, 1000L)
                .click();

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

    public void deleteCollection(String nameOfListCollection) {
        commonWaiter($(By.xpath(String.format(XPATH_DELETE_COLLECTION, nameOfListCollection))), visible).click();
        deleteCollectionButton.click();
    }

    public void graphTime() throws ParseException {
        String startTimeString = commonWaiter(graphStartTime, visible).getText()
                .replaceAll("\\s", "");

        String lastTimeString = graphLastTime.getText();
        if (lastTimeString.length() != 20) {
            lastTimeString = graphLastSecondTime.getText();
        }
        lastTimeString = lastTimeString.replaceAll("\\s", "");

        SimpleDateFormat format = new SimpleDateFormat("MMMd,yyyy,HH:mm:ssa", Locale.US);
        Date startTime = format.parse(startTimeString);
        Date lastTime = format.parse(lastTimeString);

        if (lastTime.getHours() == 0) {
            lastTime.setHours(24);
        }
        long difference = Math.abs((lastTime.getTime() - startTime.getTime())) / (60 * 1000);

        Assert.assertTrue(String.format("difference greater than 45 minutes :%s for dates between %s and %s",
                difference, lastTimeString, startTimeString), difference >= 45);

        Assert.assertTrue(String.format("difference lower than 1 hour :%s for dates between %s and %s", difference,
                lastTimeString, startTimeString), difference <= 60);
    }

    public void seeContent(String expectedText) {
        var starredElement = SelenideHelper.commonWaiter(starredLabel, Condition.visible);
        starredElement.shouldHave(Condition.text(expectedText));
    }

    public List<String> getDeviceShapeElementNotLoaded() {
        SelenideHelper.commonWaiter(defaultButton, Condition.visible)
                .click();
        return I18nUtils.getElementsNotI18N(deviceShapeElements);
    }

    public void trendsHeaderValidation() {
        commonWaiter(trendsHeaderValidation, visible);
        trendsHeaderValidation.isDisplayed();
        Assert.assertTrue(true);
    }

    public void iSeeParametersDisplayed(String collectionName, String tag1, String tag2) {
        $(By.xpath(String.format(XPATH_LISTOFCOLLECTION_PARAMS, collectionName, 1))).shouldHave(text(tag1));
        $(By.xpath(String.format(XPATH_LISTOFCOLLECTION_PARAMS, collectionName, 2))).shouldHave(text(tag2));
    }

    public void saveCollection(String name) {
        saveTrendsCollection.click();
        collectionName.click();
        collectionName.sendKeys(name);
        collectionCreate.click();
    }

    public void isGeneratedNotificationWhenCreateExistingCollection(String message, String name) {
        commonWaiter(errorText, visible);
        errorText.shouldHave(text(message));
        commonWaiter(closeDialogue, visible).click();
        arrowOfListOfCollection.click();
        $(By.xpath(String.format(collectionNameRadioButton, name))).click();
    }

    public void listOfCollection(String name) {
        arrowOfListOfCollection.waitUntil(visible,20000,1000).click();
        $(By.xpath(String.format(collectionNameRadioButton, name))).waitUntil(visible, 2000)
                .click();
    }

    public void removeParam1AndSaveCollection(String param1, String name) {
        saveTrendsCollection.click();
        collectionName.click();
        collectionName.sendKeys(name);
        $(By.xpath(String.format(parameters, param1))).click();
        collectionCreate.click();
        spinnerComponent.spinnerIcon.waitUntil(not(visible), 20000);
        commonWaiter(defaultExpandArrow, visible).click();
        arrowOfListOfCollection.click();
        $(By.xpath(String.format(collectionNameRadioButton, name))).click();
    }

    public void iSeeParameterDisplayed(String collectionName, String tag1) {
        $(By.xpath(String.format(XPATH_LISTOFCOLLECTION_PARAMS, collectionName, 1))).shouldHave(text(tag1));
    }

    public void unCheckParameter(String name, String param1) {
        $(By.xpath(String.format(XPATH_PARAMETER_UNCHECK, name, param1))).click();
    }
}


