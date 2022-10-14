package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;
import cucumber.util.I18nUtils;
import dataobjects.Recipe;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import static pageobjects.utility.SelenideHelper.goToIFrame;

public class AnalyticsPage {

    private final SelenideElement analyticsPageLinkText = $(By.id("Analytics"));

    private final SelenideElement regressionChart = $(By.xpath("//span[text()='Regressions']"));
    private final SelenideElement switchLoess = $(By.xpath("//img[@class='ols-loess-switch']"));
    private final SelenideElement relationalChart = $(By.xpath("//span[text()='Relational Charts']"));

    private final SelenideElement createAggregateButton = $(By.id("create-aggregate"));
    private final SelenideElement validateAggregateButton = $(By.id("create-aggregate-button"));
    private final SelenideElement deleteButton = $(By.id("delete-aggregate-button"));
    private final SelenideElement cancelButton = $(By.xpath("//span[@class='cancel-aggregate']"));
    private final SelenideElement applyRelationalSettingsButton = $(By.xpath("//*[@id = 'relational-apply'][@class != 'ant-btn relational-apply-button-disabled']"));
    private final SelenideElement applyRegressionSettingsButton = $(By.xpath("//*[@id = 'regression-apply'][@class != 'ant-btn regression-apply-button-disabled']"));

    private final SelenideElement clickOnData = $(By.xpath("//span[text()='Data']"));
    private final SelenideElement clickOnScatter = $(By.xpath("//span[text()='Scatter']"));
    private final SelenideElement switchToXaxis = $(By.xpath("//img[@class='x-axis-switch']"));

    private final SelenideElement aggregateHeaderText = $(By.xpath("//span[@class='aggregate-header']"));
    private final String aggregateNameText = "//*[contains(text(),'%s')]//parent::div//input";
    private final String xparameterNameText = "//*[contains(text(),'%s')]//parent::label//span";
    private final String yparameterNameText = "(//*[contains(text(),'%s')]//parent::label//input)[2]";

    private final SelenideElement viewGraph = $(By.xpath("//*[@class='highcharts-root']"));

    private final ElementsCollection deviceShapeElements = $$(By.xpath("//div[@class='parameters-list']//span"));
    private final SelenideElement aggregateNameTextBox = $(By.xpath("//input[contains(@placeholder,'New aggregate name here')]"));


    private final By dropdownSelection = By.xpath("//*[@class='ant-select-item ant-select-item-option']");

    private final SelenideElement deleteIcon = $(By.xpath("//img[@class='aggregate-delete-icon']"));

    private final String XPATH_PARAMETER_DISPLAY = "//span[contains(text(),'%s')]/ancestor::div/span[contains(text(),'%s')]";
    private final String XPATH_DROPDOWN_SELECTION = "(//*[@class='ant-select-selection-item'])[%d]";
    private final String XPATH_OPTION_SELECTION = "//*[@class='ant-select-item ant-select-item-option']";
    private final String XPATH_PARAMETER_CHECKBOX = "//*[contains(@title,'%s')]";
    
    private final SelenideElement dataTableRow = $(By.xpath("//table[@id='aggregated-data-tab-table']/tbody/tr[1]"));

    private final int INDEX_BATCH_ID = 1;
    private final int INDEX_PRODUCT_ID = 2;
    private final int INDEX_INTERVAL_ID = 3;

    public void goToAnalytics() {
        analyticsPageLinkText.click();
    }

    public void switchToFrame() {
        goToIFrame();
    }

    public void selectAggregate(String aggregateName) {
        $(By.xpath(String.format(aggregateNameText, aggregateName))).click();
        commonWaiter(dataTableRow, visible);
    }

    public void lineGraph(String xparameterName) {
        relationalChart.click();
        $(By.xpath(String.format(xparameterNameText, xparameterName))).click();
    }

    public void selectYAxisParameter(String parameter) {
        $(By.xpath(String.format(yparameterNameText, parameter))).click();
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
        $(By.xpath(String.format(XPATH_DROPDOWN_SELECTION, INDEX_BATCH_ID))).click();

        var options = $$(By.xpath(XPATH_OPTION_SELECTION));
        for (WebElement option : options) {
            if (option.getText().equals(recipe.getBatchId())) {
                option.click();
                break;
            }
        }

        $(By.xpath(String.format(XPATH_DROPDOWN_SELECTION, INDEX_PRODUCT_ID))).click();
        List<WebElement> optionsRun = $(By.xpath("//*"))
                .findElements(dropdownSelection);

        for (WebElement option : optionsRun) {
            if (option.getText().contains(recipe.getRecipeName())) {
                option.click();
                break;
            }
        }

        var intervalElement = $(By.xpath(String.format(XPATH_DROPDOWN_SELECTION, INDEX_INTERVAL_ID)));
        intervalElement.waitUntil(Condition.visible, 5000);
        intervalElement.click();

        List<WebElement> optionsInterval = $(By.xpath("//*"))
                .findElements(dropdownSelection);

        for (WebElement option2 : optionsInterval) {
            if (option2.getText().equals(analyticsInterval)) {
                option2.click();
                break;
            }
        }
    }

    public void chooseParameter(String param) {
        $(By.xpath(String.format(XPATH_PARAMETER_CHECKBOX, param))).scrollTo().click();
    }

    public void validateCreation() {
        validateAggregateButton.click();
    }

    public void startAggregateCreation(String aggregateName) {
        SelenideHelper.commonWaiter(createAggregateButton, enabled).click();
        SelenideHelper.commonWaiter(aggregateNameTextBox, visible).setValue(aggregateName);
    }

    public void checkParameter(String parameter, String unit) {
        $(By.xpath(String.format(XPATH_PARAMETER_DISPLAY, parameter, unit))).should(visible);
    }

    public void deleteIfExists(String aggregateName) {
        if ($(By.xpath(String.format("//*[contains(text(),'%s')]", aggregateName))).isDisplayed()) {
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

    public void seeContent(String expectedText) {
        aggregateHeaderText.shouldHave(text(expectedText));
    }

    public List<String> getDeviceShapeElementNotLoaded() {
        commonWaiter(createAggregateButton,visible).click();
        var cancelButtonVisible=commonWaiter(cancelButton,visible);
        var elementNotTranslated = I18nUtils.getElementsNotI18N(deviceShapeElements);
        cancelButtonVisible.click();
        return elementNotTranslated;
    }
}
