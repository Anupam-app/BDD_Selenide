package pageobjects.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import cucumber.util.I18nUtils;
import java.awt.AWTException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import pageobjects.utility.SortHelper;

public class RecipePage {
    private final String XPATH_STEP = "//div[@class='search-box-wrapper' and contains(@data-contextmenu,'step%s')]";
    private final String XPATH_DELETEBUTTON = "//*[@data-contextmenu='phase%s']//input[@class='deleteButton']";
    private final String XPATH_IMPORT_RECIPE = "//tr[td[contains(.,'%s')]]";
    private final String XPATH_EDIT_EXPORT_ICON = "//tr[td[contains(text(),'%s')]]/td/div[contains(@class, 'export-icon')]";
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";

    private final String XPATH_RECIPE_COLUMN_HEADER = "//th[contains(text(),'%s')]";
    private final String XPATH_RECIPE_TABLE = "//table[@id='recipeListTable']";

    private final String XPATH_RECIPE_COLUMNS = "//table[@id='recipeListTable']//td";
    private final String XPATH_RECIPE_COLUMNS_BY_INDEX = XPATH_RECIPE_COLUMNS + "[%s]";
    private final String XPATH_RECIPE_COLUMNS_BY_TEXT = XPATH_RECIPE_COLUMNS + "[contains(text(),'%s')]";

    private final String XPATH_RECIPE_OPTIONS_TEXT = "//option[@value='%s']/ancestor::li";

    private final String NOTIFICATION_TEXT_IMPORT = "The recipe %s was successfully imported!";
    private final String NOTIFICATION_TEXT_EXPORT = "The recipe %s was successfully exported!";

    private final String XPATH_RecipeColumnName = "//*[@id='recipeListTable']/thead/tr/th[%d]";
    private final String XPATH_RecipeColumnName_Value = "//*[@id='recipeListTable']/tbody/tr[%d]/td[%d]";

    private final SelenideElement recipePageLinkText = $(By.id("RecipeManagement"));
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement editorLinkText = $(By.xpath("//a[contains(text(),'Editor')]"));
    private final SelenideElement browserLinkText = $(By.xpath("//a[contains(text(),'Browser')]"));

    private final SelenideElement recipeElementText = $(By.xpath("//div[@class='recipeTabs']"));
    private final SelenideElement notificationText = $(By.className("notification-summary"));
    private final SelenideElement headerText = $(By.xpath("//div[@class='navWrapper']//h2"));
    private final ElementsCollection deviceShapeElements = $$(By.xpath("//div[@class='search-node']//span"));

    private final SelenideElement recipeCriteriaSearchTextBox = $(By.className("search-txt-box"));
    private final SelenideElement recipeSearchTextBox = $(By.id("search"));
    private final SelenideElement phaseElementTextBox = $(By.className("phase-Name"));

    private final SelenideElement filterError = $(By.xpath("//h4"));
    private final SelenideElement openButton = $(By.className("open-recipe-btn"));
    private final SelenideElement plusButton = $(By.className("icon-plus"));
    private final SelenideElement addStepButton = $(By.xpath("//*[contains(@class, 'home-screen-icon-block icon-plus')]"));
    private final By deletePhaseButton = By.className("deleteButton");
    private final SelenideElement primaryButton = $(By.className("btn-primary"));
    private final SelenideElement saveButton = $(By.xpath("//button[contains(text(),'Save')]"));
    private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'Ok')]"));
    private final SelenideElement deleteButton = $(By.xpath("//div[@class='phaseRow selected']//input[@class='deleteButton']"));

    private final SelenideElement XPATH_WARNNOTIFICATION_TEXT = $(By.xpath("//*[@class='editor-dialog']/div/div[1]/span"));

    private final String xpathEditPage = "//*[@id=\"recipeListTable\"]/tbody/tr/td[contains(.,'%s')]";
    private final String chooseOption = "//*[@class=\"submenu-value-left\"]/label[text()='%s']";
    private final By phasesList = By.className("phaseHead");

    private final SelenideElement phasesListClick = $(By.className("phaseHead"));
    private final SelenideElement phasesListClick1 = $(By.xpath("(//label[@class='phaseHead'])[1]"));
    private final SelenideElement phasesListClick2 = $(By.xpath("((//label[@class='phaseHead'])[1]//following::span[@class='deleteBtn egu-right-block']/input)[1]"));
    private SelenideElement arrowIcon = $(By.xpath("//div[(@class='down-icon')]"));
    private final SelenideElement statusDraft = $(By.xpath("//div[@class='status-tooltip']"));
    private final SelenideElement selectInReview = $(By.xpath("//span[text()='In-Review']"));
    private final SelenideElement selectTechReview = $(By.xpath("//span[text()='Tech-Review']"));
    private final SelenideElement statusInReview = $(By.xpath("//div[@class='status-tooltip']"));
    private final SelenideElement selectApprove = $(By.xpath("//span[text()='Approved-Active']"));
    private final SelenideElement selectDraft = $(By.xpath("//span[text()='Draft']"));
    private final SelenideElement selectInactive = $(By.xpath("//span[text()='Approved-InActive']"));
    private final SelenideElement inputPassword = $(By.xpath("//input[@type='password']"));
    private final SelenideElement statusApproved = $(By.xpath("//div[@class='status-tooltip']"));

    private final SelenideElement clickOnDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final ElementsCollection notificationTexts = $$(By.xpath("//div[@class='description-text-blue orch-notification-description']"));

    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private final String upIcon = "(//div[@class='up-icon'])[%d]";

    private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));

    private final ElementsCollection deleteButtons = $$(By.xpath("//*[@class='deleteButton']"));
    private final SelenideElement saveEditorButton = $(By.xpath("//button[contains(@class,'save-button')]"));
    private final SelenideElement importMenuButton = $(By.xpath("//button[contains(@class,'import-button')]"));
    private final SelenideElement importButton = $(By.xpath("//button[contains(@class,'import-button-text')]"));

    private final SelenideElement phase1_Label = $(By.xpath("//label[contains(text(),'Phase 1')]"));

    private final ElementsCollection recipeListTable = $$(By.xpath("//*[@id='recipeListTable']/tbody/tr"));
    private final SelenideElement dateColumn = $(By.xpath("//input[@name='dateRange']"));
    private final SelenideElement datePopup = $(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright') and contains(@style,'block')]"));
    private ElementsCollection dateOptionsRprt = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opens')]/div/ul/li"));
    private ElementsCollection dateOptions = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright')]/div/ul/li"));
    private final SelenideElement noDatamsg = $(By.xpath("//h4[text()='No runs matching with the applied filter.']"));
    private final SelenideElement startDateDesendingArrow = $(By.xpath("//th[text()='Start Date']/span[@class='order']"));
    private final SelenideElement startDateAsendingArrow = $(By.xpath("//th[text()='Start Date']/span[@class='react-bootstrap-table-sort-order dropup']"));
    private final SelenideElement startDateRep = $(By.xpath("//table[@id='recipeListTable']/tbody/tr[1]/td[6]"));
    private final SelenideElement previousMonth = $(By.xpath("//div[@class='drp-calendar left']//th[@class='prev available']"));
    private ElementsCollection availableDates = $$(By.xpath("//div[@class='drp-calendar left']/div/table/tbody/tr/td[@class='available']"));
    Function<Integer, List<String>> getRecipeColumns = (index) -> $$(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_INDEX, index))).texts();

    public void goTo() {
        recipePageLinkText.click();
    }

    public void setSearch(String recipeName) {
        commonWaiter(recipeSearchTextBox, visible);
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
    }

    public void goToEditMode() {
        editorLinkText.waitUntil(Condition.visible, 5000l);
        editorLinkText.click();
    }

    public void goToBrowserMode() {
        browserLinkText.click();
    }

    public void verifyList() throws InterruptedException {
        $$(recipeListTable).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
    }

    public void verifyColoumn(String columnName, String tab, int columnIndex) {
        $(By.xpath(String.format(XPATH_RecipeColumnName, columnIndex))).shouldHave(text(columnName));
        for (int i = 1; i <= recipeListTable.size(); i++) {
            Assert.assertFalse($(By.xpath(String.format(XPATH_RecipeColumnName_Value, i, columnIndex))).getText().isBlank());
        }

    }

    public void edit(String recipeName) {
        commonWaiter($(By.xpath(String.format(xpathEditPage, recipeName))), Condition.visible).click();
        commonWaiter(openButton, Condition.visible).click();
    }

    public void deleteAllPhases() {
        $$(phasesList).forEach(e -> {
            e.click();
            e.find(deletePhaseButton).click();
        });
    }

    public void addPhase(String phase) {
        addStepButton.click();
        SelenideElement searchTextBox = $(By.className("search-txt-box"));
        searchTextBox.sendKeys("setpoint");
        searchTextBox.sendKeys(Keys.ENTER);
        searchTextBox.sendKeys(Keys.LEFT_CONTROL + "g");
        phaseElementTextBox.sendKeys(phase);
        phaseElementTextBox.sendKeys(Keys.ENTER);
    }

    public void addStep() {
        SelenideElement searchTextBox = $(By.className("search-txt-box"));
        searchTextBox.sendKeys("setpoint");
        searchTextBox.sendKeys(Keys.ENTER);
        switchTo().parentFrame();

    }

    public void saveRecipe(String recipeName) {
        saveEditorButton.click();
        SelenideElement recipeInputSave = $(By.className("selected-recipe-input"));
        recipeInputSave.click();
        SelenideHelper.commonWaiter(recipeInputSave, visible).clear();
        recipeInputSave.setValue(recipeName);
        saveButton.click();
    }

    public void isGeneratedNotificationWhenCreateExistingRecipe(String message) {
        commonWaiter(XPATH_WARNNOTIFICATION_TEXT, visible);
        XPATH_WARNNOTIFICATION_TEXT.shouldHave(ownText(message));
        $(By.xpath("//*[@class='btn-primary']")).click();
    }

    public void saveModifiedRecipe() {
        saveEditorButton.click();
    }

    public String getPhaseName() {
        return phaseElementTextBox.getValue();
    }

    public String getRecipeName() {
        return recipeElementText.getText();
    }

    public void naviagateToEditorTab() {
        recipePageLinkText.click();
        switchTo().frame("CrossDomainiframeId");
        editorLinkText.waitUntil(Condition.visible, 5000l);
        editorLinkText.click();
    }

    public void createRecipe(String recipenode) {
        plusButton.waitUntil(Condition.visible, 5000l);
        plusButton.click();
        recipeCriteriaSearchTextBox.sendKeys(recipenode);
        recipeCriteriaSearchTextBox.sendKeys(Keys.ENTER);
        recipeCriteriaSearchTextBox.sendKeys(Keys.LEFT_CONTROL + "g");
        phaseElementTextBox.sendKeys(RandomStringUtils.randomAlphabetic(10));
        phaseElementTextBox.sendKeys(Keys.ENTER);
    }

    public void verifyRecipe(String recipeName) {
        browserLinkText.click();
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.waitUntil(Condition.visible, 5000l);
        openButton.click();
    }

    public void editRecipe(String recipeName) {
        SelenideHelper.commonWaiter(recipeSearchTextBox,visible).setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
        commonWaiter($(By.xpath(String.format(xpathEditPage, recipeName))),visible).click();
        openButton.waitUntil(Condition.visible, 5000l);
        openButton.click();
    }

    public void deletePhaseToRecipe() {
        commonWaiter(phase1_Label, visible).click();
        commonWaiter(deleteButton, visible).click();
        commonWaiter(okButton, visible).click();
    }

    public void approveRecipe(String password) {
        commonWaiter(statusDraft,visible).click();
        selectInReview.click();
        primaryButton.click();
        okButton.click();
        commonWaiter(statusInReview,visible).click();
        selectApprove.click();
        $(By.xpath("//button[contains(text(),'Change')]")).click();
        inputPassword.sendKeys(password);
        $(By.xpath("//button[contains(text(),'SIGN')]")).click();
        statusApproved.waitUntil(Condition.visible, 5000l);
        browserLinkText.click();
        editorLinkText.click();
    }

    public void inactiveRecipe(String password) {
        commonWaiter(statusDraft,visible).click();
        selectInReview.click();
        primaryButton.click();
        $(By.xpath("//button[text()='Ok']")).click();
        commonWaiter(statusInReview,visible).click();
        selectInactive.click();
        $(By.xpath("//button[text()='Change']")).click();
        inputPassword.sendKeys(password);
        $(By.xpath("//button[text()='SIGN']")).click();
        statusApproved.waitUntil(Condition.visible, 5000l);
        browserLinkText.click();
        editorLinkText.click();
    }

    public void rejectTechReviewRecipe() {
        commonWaiter(statusDraft,visible).click();
        selectTechReview.click();
        $(By.xpath("//button[text()='Change']")).click();
        commonWaiter(statusInReview,visible).click();
        selectDraft.click();
        $(By.xpath("//button[text()='Change']")).click();
        statusApproved.waitUntil(Condition.visible, 5000l);
        browserLinkText.click();
        editorLinkText.click();
    }

    public String getStatus() {
        return statusApproved.getText();
    }


    public void exportRecipe(String recipeName) {
        $(By.xpath(String.format(XPATH_EDIT_EXPORT_ICON, recipeName))).waitUntil(visible, 5000l).click();
    }

    public void notificationMessageImport(String recipeName) {
        String expectedNotificationText = String.format(NOTIFICATION_TEXT_IMPORT, recipeName);
        checkNotification(expectedNotificationText);
    }

    public void checkNotification(String notification) {
        notificationTexts.shouldHave(
                CollectionCondition.anyMatch("User notification should contain this notification"
                        , n -> n.getText().equals(notification)));
    }

    public void notificationMessageExport(String recipeName) {
        String expectedNotificationText = String.format(NOTIFICATION_TEXT_EXPORT, recipeName);
        checkNotification(expectedNotificationText);
    }

    public void importRecipe(String recipeName) {
        importMenuButton.click();
        var importRecipe = $(By.xpath(String.format("//td[contains(@title,'%s')]", recipeName)));
        importRecipe.click();
        importButton.click();
        SelenideElement recipeInputSave = $(By.className("rename-recipe-import-input"));
        recipeInputSave.click();
        SelenideHelper.commonWaiter(recipeInputSave, visible).clear();
        var value = RandomStringUtils.randomAlphabetic(10);
        recipeInputSave.setValue(value);
        saveButton.click();


        browserLinkText.waitUntil(Condition.visible, 5000l).click();
    }

    public void chooseOption(String option) {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath(String.format(chooseOption, option))).click();
        Selenide.sleep(5000);

    }

    public void printRecipe(String recipeName) throws AWTException {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Print']")).shouldBe(visible);
    }

    public void lookAtTheUserNotification() {
        switchTo().defaultContent();
        userProfileIcon.waitUntil(Condition.visible, 5000l).click();
    }

    public String getGeneratedName() {
        switchTo().parentFrame();
        notificationText.waitUntil(visible, 30000l, 500l);
        var notif = notificationText.text();
        var recipeName = notif.split("The recipe ")[1].split(" ")[0];
        return recipeName;
    }

    public void checkTableContainsRecipe(String recipeName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_TEXT, recipeName))), visible);
    }

    public void selectRecipeStatus(String recipeStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        commonWaiter($(By.xpath(String.format(upIcon, 1))), visible);
        $(By.xpath(String.format("//span[contains(text(),'%s')]", recipeStatus))).click();
        applyFilterButton.click();
    }

    public void selectCreatedBy(String user) {
        SelenideHelper.commonWaiter(clickOnDropdown, visible).click();
        commonWaiter($(By.xpath(String.format(XPATH_RECIPE_OPTIONS_TEXT, user))), visible).click();
    }

    public SelenideElement getRecipeColumnHeader(String columnName) {
        return $(By.xpath(String.format(XPATH_RECIPE_COLUMN_HEADER, columnName)));
    }

    public List<String> getAllRecipeColumnHeaders() {
        return $$(By.xpath(XPATH_RECIPE_TABLE + "//th")).texts();
    }

    public void sortList(String columnName, boolean descending) {
        SelenideElement sortAction = getRecipeColumnHeader(columnName);
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void selectRecipeStatusImported(String status, String imported) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        commonWaiter($(By.xpath(String.format(upIcon, 1))), visible);
        $(By.xpath(String.format("//span[text()='%s']", status))).click();
        arrowIcon.click();
        commonWaiter($(By.xpath(String.format(upIcon, 2))), visible);
        $(By.xpath(String.format("//span[text()='%s']", imported))).click();
        if (imported.equalsIgnoreCase("Yes")) {
            arrowIcon.click();
            $(By.xpath(String.format("//div[text()='Import Status']/following::span[text()='%s']", status))).click();
        }
        applyFilterButton.click();
    }

    public void verifyRecipeStatusImported(String status, String imported) {
        for (int i = 1; i <= recipeListTable.size(); i++) {
            Assert.assertTrue($(By.xpath(String.format(XPATH_RecipeColumnName_Value, i, 3))).getText().equals(imported)
                    || filterError.getText().equals("No recipes matching with the applied filter."));
            Assert.assertTrue($(By.xpath(String.format(XPATH_RecipeColumnName_Value, i, 7))).getText().equals(status)
                    || filterError.getText().equals("No recipes matching with the applied filter."));
            if (imported == "Yes") {
                Assert.assertTrue($(By.xpath(String.format(XPATH_RecipeColumnName_Value, i, 4))).getText().equals(status)
                        || filterError.getText().equals("No recipes matching with the applied filter."));
            }
        }
    }

    public void selectDateRange(String option) throws InterruptedException {
        commonWaiter(dateColumn, visible);
        dateColumn.click();
        ElementsCollection options = dateOptionsRprt;
        for (SelenideElement element : options) {
            if (element.getText().equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }

        if (option.equalsIgnoreCase("Custom Range")) {
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            commonWaiter(previousMonth, visible);
            int index = getRandomNumber(0, availableDates.size() / 2);
            availableDates.get(index).click();
            index = getRandomNumber(availableDates.size() / 2, availableDates.size());
            availableDates.get(index).click();

        }

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public boolean verifyDateRanges(String dateRange) throws ParseException, InterruptedException {
        boolean isTrue = false;
        switch (dateRange) {
            case "Today":
            case "Yesterday":
                String dateValue = dateColumn.getAttribute("value").split("to")[0].trim();
                Date selectedDate = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue);
                if (startDateRep.isDisplayed()) {
                    sortList("Last Modified On", false);
                    String startDateRow1 = startDateRep.getText().split(" ")[0].trim();
                    Date selectedAsendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow1);
                    sortList("Last Modified On", true);
                    startDateRow1 = startDateRep.getText().split(" ")[0].trim();
                    Date selectedDesendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow1);
                    if (selectedAsendingDate.equals(selectedDate) && selectedDesendingDate.equals(selectedDate)) {
                        isTrue = true;
                    }
                } else if (noDatamsg.isDisplayed()) {
                    isTrue = true;
                }
                break;
            case "Last 7 Days":
            case "Last 30 Days":
            case "This Month":
            case "Last Month":
            case "Custom Range":
                commonWaiter(dateColumn, visible);
                String dateValue1 = dateColumn.getAttribute("value").split("to")[0].trim();
                Date selectedDate1 = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue1);
                String dateValue2 = dateColumn.getAttribute("value").split("to")[1].trim();
                Date selectedDate2 = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue2);
                if (startDateRep.isDisplayed()) {
                    sortList("Last Modified On", false);
                    String startDateRow = startDateRep.getText().split(" ")[0].trim();
                    Date selectedAsendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow);
                    sortList("Last Modified On", true);
                    String endDateRow = startDateRep.getText().split(" ")[0].trim();
                    Date selectedDesendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(endDateRow);
                    if ((selectedAsendingDate.equals(selectedDate1) || selectedAsendingDate.after(selectedDate1))
                            && (selectedDesendingDate.equals(selectedDate2)
                            || selectedDesendingDate.before(selectedDate2))) {
                        isTrue = true;
                    }
                } else if (noDatamsg.isDisplayed()) {
                    isTrue = true;
                }
                break;
        }
        return isTrue;
    }


    public void checkSortedElement(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllRecipeColumnHeaders(), columnName, descending, getRecipeColumns);
        switchTo().parentFrame();
    }

    public void seeContent(String expectedText) {
        headerText.shouldHave(text(expectedText));
    }

    public List<String> getDeviceShapeElementNotLoaded() {
        plusButton.waitUntil(Condition.visible, 5000l);
        plusButton.click();
        recipeCriteriaSearchTextBox.click();

        var elementNotTranslated = I18nUtils.getElementsNotI18N(deviceShapeElements);

        deleteButtons.forEach(deleteButton -> deleteButton.click());

        return elementNotTranslated;
    }
}