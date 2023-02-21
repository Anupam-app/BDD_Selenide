package pageobjects.pages;

import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.goToIFrame;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import dataobjects.Recipe;
import pageobjects.utility.SelenideHelper;

public class AnalyticsPage {

    private final SelenideElement analyticsPageLinkText = $(By.id("Analytics"));
    private final SelenideElement regressionChart = $(By.xpath("//span[text()='Regressions']"));
    private final SelenideElement switchLoess = $(By.xpath("//img[@class='ols-loess-switch']"));
    private final SelenideElement relationalChart = $(By.xpath("//span[text()='Relational Charts']"));
    private final SelenideElement plusButton = $(By.xpath(
            "//div[@class='ant-tabs-tab ant-tabs-tab-with-remove ant-tabs-tab-active']/following-sibling::button[@class='ant-tabs-nav-add']"));
    private final SelenideElement createAggregateButton = $(By.id("create-aggregate"));
    private final SelenideElement validateAggregateButton = $(By.id("create-aggregate-button"));
    private final SelenideElement deleteButton = $(By.id("delete-aggregate-button"));
    private final SelenideElement applyRelationalSettingsButton =
            $(By.xpath("//*[@id = 'relational-apply'][@class = 'ant-btn relational-apply-button']"));
    private final SelenideElement applyRegressionSettingsButton =
            $(By.xpath("//*[@id = 'regression-apply'][@class = 'ant-btn regression-apply-button']"));

    private final SelenideElement clickOnData = $(By.xpath("//span[text()='Data']"));
    private final SelenideElement clickOnScatter = $(By.xpath("//span[text()='Scatter']"));
    private final SelenideElement switchToXaxis = $(By.xpath("//img[@class='x-axis-switch']"));
    private final String aggregateNameText = "//*[text()='%s']//parent::div//input";
    private final String xparameterNameText = "//*[text()='%s']//parent::label//span";
    private final SelenideElement viewGraph = $(By.xpath("//*[@class='highcharts-root']"));
    private final SelenideElement aggregateNameTextBox =
            $(By.xpath("//input[@placeholder='New aggregate name here.']"));

    private final By dropdownSelection = By.xpath("//*[@class='ant-select-item ant-select-item-option']");
    private final SelenideElement relationalDropdownSelection =
            $(By.xpath("//*[@class='aggregate-relational-expand-arrow']"));
    private final SelenideElement timestampColumn = $(By.xpath("//span[contains(text(),'Timestamp')]"));
    private final SelenideElement regressionDropdownSelection =
            $(By.xpath("//*[@class='aggregate-regression-expand-arrow']"));
    private final SelenideElement deleteIcon = $(By.xpath("//img[@class='aggregate-delete-icon']"));

    private final String XPATH_RIGHT_PANEL = "//span[contains(text(),'%s')]";

    private final SelenideElement analyticsHeader = $(By.xpath("//div[text()='Analytics']"));
    private final ElementsCollection defaultParams = $$(By.xpath("//div[@class='custom-checkbox-wrapper']"));
    private final SelenideElement expandButton = $(By.xpath("//button[@id='expand']"));
    private final SelenideElement myAggregateHeader = $(By.xpath("//span[@class='my-aggregate-header']"));

    public void goToAnalytics() {
        analyticsPageLinkText.click();
    }

    public void switchToFrame() {
        goToIFrame();
    }

    public void selectAggregate(String aggregateName) {
        $(By.xpath(String.format(aggregateNameText, aggregateName))).click();
    }

    public void lineGraph(String xparameterName) {
        relationalChart.click();
        $(By.xpath(String.format(xparameterNameText, xparameterName))).click();
    }

    public void selectYAxisParameter(String parameter) {
        String yparameterNameText = "(//*[text()='%s']//parent::label//input)[2]";
        $(By.xpath(String.format(yparameterNameText, parameter))).click();
        SelenideHelper.fluentWaiter()
                .until((webDriver) -> webDriver.findElement(By.xpath(String.format(xparameterNameText, parameter)))
                        .getAttribute("class")
                        .contains("disabled"));
    }

    public void verifyParameters() {
        clickOnData.click();
    }

    public void scatterGraph(String xparameterName) {
        relationalChart.click();
        clickOnScatter.click();
        $(By.xpath(String.format(xparameterNameText, xparameterName))).click();
    }

    public void logarithmicGraph(String xparameterName) {
        relationalChart.click();
        switchToXaxis.click();
        $(By.xpath(String.format(xparameterNameText, xparameterName))).click();
    }

    public void olsGraph(String xparameterName) {
        regressionChart.click();
        $(By.xpath(String.format(xparameterNameText, xparameterName))).click();
    }

    public void loessGraph(String xparameterName) {
        regressionChart.click();
        switchLoess.click();
        $(By.xpath(String.format(xparameterNameText, xparameterName))).click();
    }

    public void verifyGraph() {
        viewGraph.should(be(visible));
    }

    public void createAggregate(Recipe recipe, String analyticsInterval) {
        String XPATH_DROPDOWN_SELECTION = "(//*[@class='ant-select-selection-item'])[%d]";
        int INDEX_BATCH_ID = 1;
        $(By.xpath(String.format(XPATH_DROPDOWN_SELECTION, INDEX_BATCH_ID))).click();

        String XPATH_OPTION_SELECTION = "//*[@class='ant-select-item ant-select-item-option']";
        var options = $$(By.xpath(XPATH_OPTION_SELECTION));
        for (WebElement option : options) {
            if (option.getText()
                    .equals(recipe.getBatchId())) {
                option.click();
                break;
            }
        }

        int INDEX_PRODUCT_ID = 2;
        $(By.xpath(String.format(XPATH_DROPDOWN_SELECTION, INDEX_PRODUCT_ID))).click();
        List<WebElement> optionsRun = $(By.xpath("//*")).findElements(dropdownSelection);

        for (WebElement option : optionsRun) {
            if (option.getText()
                    .contains(recipe.getRecipeName())) {
                option.click();
                break;
            }
        }

        int INDEX_INTERVAL_ID = 3;
        var intervalElement = $(By.xpath(String.format(XPATH_DROPDOWN_SELECTION, INDEX_INTERVAL_ID)));
        intervalElement.waitUntil(Condition.visible, 5000);
        intervalElement.click();

        List<WebElement> optionsInterval = $(By.xpath("//*")).findElements(dropdownSelection);

        for (WebElement option2 : optionsInterval) {
            if (option2.getText()
                    .equals(analyticsInterval)) {
                option2.click();
                break;
            }
        }
    }

    public void chooseParameter(String param) {
        String XPATH_PARAMETER_CHECKBOX = "//label//span[text()='%s']/ancestor::label//span[@class='ant-checkbox']";
        $(By.xpath(String.format(XPATH_PARAMETER_CHECKBOX, param))).scrollTo()
                .click();
    }

    public void validateCreation() {
        validateAggregateButton.click();
    }

    public void startAggregateCreation(String aggregateName) {
        SelenideHelper.commonWaiter(createAggregateButton, enabled)
                .click();
        SelenideHelper.commonWaiter(aggregateNameTextBox, visible)
                .setValue(aggregateName);
    }

    public void checkParameter(String parameter, String unit) {
        Selenide.sleep(2000);
        String XPATH_PARAMETER_DISPLAY = "//span[contains(text(),'%s')]/ancestor::div/span[contains(text(),'%s')]";
        $(By.xpath(String.format(XPATH_PARAMETER_DISPLAY, parameter, unit))).shouldBe(visible);
    }

    public void verifyTimestampColumn() {
        timestampColumn.shouldBe(visible);
    }

    public void verifyAggregateTab(String aggregateName) {
        ($(By.xpath("//*[@class='ant-tabs-tab-btn']"))).shouldHave(text(aggregateName));
    }

    public void verifyTimestamp() {
        ($(By.xpath("//img[@class='aggregate-refresh']"))).click();
        String timestamp =
                (($(By.xpath("//img[@class='aggregate-refresh']/preceding-sibling::span"))).getText()).substring(5, 15);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Assert.assertTrue(dateFormat.format(currentTimestamp)
                .contains(timestamp));
    }

    public void verifyBatchIDAndRunIDAndStatus(Recipe recipe, String status) {
        $(By.xpath(String.format(XPATH_RIGHT_PANEL, recipe.getBatchId()))).shouldBe(visible);
        Assert.assertTrue(($(By.xpath("//div[@class='aggregate-runid-label']/span")).getAttribute("title"))
                .contains(recipe.getRunId()));
        if ($(By.xpath(String.format(XPATH_RIGHT_PANEL, "In Progress"))).isDisplayed()) {
            ($(By.xpath("//img[@class='aggregate-refresh']"))).click();
            Selenide.sleep(1000);
        }
        $(By.xpath(String.format(XPATH_RIGHT_PANEL, status))).shouldBe(visible);

    }

    public void deleteIfExists(String aggregateName) {
        if ($(By.xpath(String.format("//*[text()='%s']", aggregateName))).isDisplayed()) {
            selectAggregate(aggregateName);
            deleteIcon.click();
            deleteButton.click();
        }
    }

    public void relationalApplySettings() {
        applyRelationalSettingsButton.scrollTo();
        applyRelationalSettingsButton.click();
    }

    public void regressionApplySettings() {
        applyRegressionSettingsButton.scrollTo();
        applyRegressionSettingsButton.click();
    }

    public void verifyAnalyticsHeader() {
        analyticsHeader.shouldBe(visible);
    }

    public void panelValidation(String options) {

        switch (options) {
            case "Create an Aggregate":
                createAggregateButton.shouldBe(visible);
                break;
            case "expandButton":
                expandButton.shouldBe(visible);
                break;
            case "My Aggregates":
                myAggregateHeader.shouldBe(visible);
                ($(By.xpath(String.format(XPATH_RIGHT_PANEL, "testAggregate")))).shouldBe(visible);
                break;
            case "Data":
                clickOnData.shouldBe(visible);
                break;
            case "Graph":
                ($(By.xpath(String.format(XPATH_RIGHT_PANEL, "Graph")))).shouldBe(visible);
                break;
            case "aggregate tab":
                ($(By.xpath("//*[@class='ant-tabs-tab-btn']"))).shouldBe(visible);
                break;
            case "Plus Button":
                plusButton.shouldBe(visible);
                break;
            case "Aggregated interval":
                $(By.xpath(String.format(XPATH_RIGHT_PANEL, "Aggregated Hourly"))).shouldBe(visible);
                break;
            case "Created date timestamp":
                $(By.xpath("//span[contains(text(),'On : 01/02/2023')]")).shouldBe(visible);
                break;
            case "Batch ID":
                $(By.xpath(String.format(XPATH_RIGHT_PANEL, "Batch ID b10"))).shouldBe(visible);
                break;
            case "RUN ID":
                $(By.xpath(String.format(XPATH_RIGHT_PANEL, "Run ID recipe4sec2202..."))).shouldBe(visible);
                break;
            case "Status":
                $(By.xpath(String.format(XPATH_RIGHT_PANEL, "Status Completed"))).shouldBe(visible);
                break;
            case "Relational Charts with expand/collapse option":
                relationalDropdownSelection.shouldBe(visible);
                break;
            case "Regression Charts with expand/collapse option":
                regressionDropdownSelection.shouldBe(visible);
                break;
            case "Delete":
                deleteIcon.shouldBe(visible);
                break;
            default:
        }
    }

    public void verifyMessageDisplayedOnRightPanel(String message) {
        ($(By.xpath(String.format(XPATH_RIGHT_PANEL, message)))).shouldBe(visible);
        ($(By.xpath(String.format(XPATH_RIGHT_PANEL, "Select an existing aggregate or ")))).shouldBe(visible);
        ($(By.xpath(String.format(XPATH_RIGHT_PANEL, "create an aggregate.")))).shouldBe(visible);
    }

    public void chooseAggregate(String aggregate) {
        ($(By.xpath(String.format(aggregateNameText, aggregate)))).click();
        Selenide.sleep(1000);
    }

    public void defaultCollectionTagsValidation(String parameters) {
        int count = defaultParams.size();
        List<String> acceptedParams = new ArrayList<String>();
        List<String> expectedParams = new ArrayList<String>();
        for (int i = 1; i <= count; i++) {
            String tagLabel = "//div[@class='custom-checkbox-wrapper'][%d]";
            acceptedParams.add(($(By.xpath(String.format(tagLabel, i)))).getAttribute("title"));
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
}
