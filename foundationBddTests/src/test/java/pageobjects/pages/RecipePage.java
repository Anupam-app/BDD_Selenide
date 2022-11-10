package pageobjects.pages;

import com.codeborne.selenide.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.SortHelper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class RecipePage {
    private final String XPATH_IMPORT_RECIPE = "//tr[td[contains(.,'%s')]]";
    private final String XPATH_EDIT_EXPORT_ICON = "//tr[td[text()='%s']]/td/div[contains(@class, 'export-icon')]";
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";

    private final String XPATH_RECIPE_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_RECIPE_TABLE = "//table[@id='recipeListTable']";

    private final String XPATH_RECIPE_COLUMNS = "//table[@id='recipeListTable']//td";
    private final String XPATH_RECIPE_COLUMNS_BY_INDEX = XPATH_RECIPE_COLUMNS + "[%s]";
    private final String XPATH_RECIPE_COLUMNS_BY_TEXT = XPATH_RECIPE_COLUMNS + "[text()='%s']";

    private final String XPATH_RECIPE_OPTIONS_TEXT = "//option[@value='%s']/ancestor::li";

    private final String NOTIFICATION_TEXT_IMPORT = "The recipe %s was successfully imported!";
    private final String NOTIFICATION_TEXT_EXPORT = "The recipe %s was successfully exported!";
    
    private final String XPATH_RecipeColumnName = "//*[@id='recipeListTable']/thead/tr/th[%d]";
    private final String XPATH_RecipeColumnName_Value = "//*[@id='recipeListTable']/tbody/tr[%d]/td[%d]";

    private final SelenideElement recipePageLinkText = $(By.id("RecipeManagement"));
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement editorLinkText = $(By.xpath("//a[text()='Editor']"));
    private final SelenideElement browserLinkText = $(By.xpath("//a[text()='Browser']"));

    private final SelenideElement recipeElementText = $(By.xpath("//div[@class='recipeTabs']"));
    private final SelenideElement notificationText = $(By.className("notification-summary"));

    private final SelenideElement recipeSearchTextBox = $(By.id("search"));
    private final SelenideElement phaseElementTextBox = $(By.className("phase-Name"));
    private final SelenideElement filterError = $(By.xpath("//h4"));
    private final SelenideElement openButton = $(By.className("open-recipe-btn"));
    private final SelenideElement plusButton = $(By.className("icon-plus"));
    private final By deletePhaseButton = By.className("deleteButton");
    private final SelenideElement primaryButton = $(By.className("btn-primary"));
    private final SelenideElement saveButton = $(By.xpath("//button[contains(text(),'Save')]"));
    private final SelenideElement XPATH_WARNNOTIFICATION_TEXT = $(By.xpath("//*[@class='editor-dialog']/div/div[1]"));
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
    private final SelenideElement importButton = $(By.xpath("//button[contains(text(),'Import')]"));
    private final ElementsCollection recipeListTable = $$(By.xpath("//*[@id='recipeListTable']/tbody/tr"));
    private final SelenideElement dateColumn=$(By.xpath("//input[@name='dateRange']"));
	private final SelenideElement datePopup=$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright') and contains(@style,'block')]"));
	private ElementsCollection dateOptionsRprt = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opens')]/div/ul/li"));
	private ElementsCollection dateOptions = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright')]/div/ul/li"));
	private final SelenideElement noDatamsg = $(By.xpath("//h4[text()='No runs matching with the applied filter.']"));
	private final SelenideElement startDateDesendingArrow=$(By.xpath("//th[text()='Start Date']/span[@class='order']"));
	private final SelenideElement startDateAsendingArrow=$(By.xpath("//th[text()='Start Date']/span[@class='react-bootstrap-table-sort-order dropup']"));
	private final SelenideElement startDateRep=$(By.xpath("//table[@id='recipeListTable']/tbody/tr[1]/td[6]"));
	private final SelenideElement previousMonth = $(By.xpath("//div[@class='drp-calendar left']//th[@class='prev available']"));
	private ElementsCollection availableDates=$$(By.xpath("//div[@class='drp-calendar left']/div/table/tbody/tr/td[@class='available']"));
	Function<Integer, List<String>> getRecipeColumns = (index) -> $$(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_INDEX, index))).texts();

    public void goTo() {
        recipePageLinkText.click();
    }

    public void setSearch(String recipeName) {
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
    		for(int i=1; i<=recipeListTable.size();i++) {
    			Assert.assertFalse($(By.xpath(String.format(XPATH_RecipeColumnName_Value, i ,columnIndex))).getText().isBlank()); 
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
        plusButton.waitUntil(Condition.visible, 5000l);
        plusButton.click();
        SelenideElement searchTextBox = $(By.className("search-txt-box"));
        searchTextBox.sendKeys("start");
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
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Save']")).click();
        SelenideElement recipeInputSave = $(By.className("selected-recipe-input"));
        $(By.className("selected-recipe-input")).clear();
        recipeInputSave.setValue(recipeName);
        $(By.className("btn_primary")).click();
    }
    
    public void isGeneratedNotificationWhenCreateExistingRecipe(String message) {   	
        commonWaiter(XPATH_WARNNOTIFICATION_TEXT,visible);
        XPATH_WARNNOTIFICATION_TEXT.shouldHave(text(message));
        $(By.xpath("//*[@class='btn-primary']")).click();         
    }

    public void saveModifiedRecipe() {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Save']")).click();
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
        SelenideElement searchTextBox = $(By.className("search-txt-box"));
        searchTextBox.sendKeys(recipenode);
        searchTextBox.sendKeys(Keys.ENTER);
        searchTextBox.sendKeys(Keys.LEFT_CONTROL + "g");
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
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.waitUntil(Condition.visible, 5000l);
        openButton.click();
    }

    public void deletePhaseToRecipe() {
        phasesListClick.waitUntil(Condition.visible, 5000l);
        phasesListClick1.click();
        phasesListClick2.click();
        $(By.xpath("//button[text()='Ok']")).click();
    }

    public void approveRecipe(String password) {
        statusDraft.click();
        selectInReview.click();
        primaryButton.click();
        $(By.xpath("//button[text()='Ok']")).click();
        statusInReview.click();
        selectApprove.click();
        $(By.xpath("//button[text()='Change']")).click();
        inputPassword.sendKeys(password);
        $(By.xpath("//button[text()='SIGN']")).click();
        statusApproved.waitUntil(Condition.visible, 5000l);
        browserLinkText.click();
        editorLinkText.click();
    }
    
    public void inactiveRecipe(String password) {
        statusDraft.click();
        selectInReview.click();
        primaryButton.click();
        $(By.xpath("//button[text()='Ok']")).click();
        statusInReview.click();
        selectInactive.click();
        $(By.xpath("//button[text()='Change']")).click();
        inputPassword.sendKeys(password);
        $(By.xpath("//button[text()='SIGN']")).click();
        statusApproved.waitUntil(Condition.visible, 5000l);
        browserLinkText.click();
        editorLinkText.click();
    }
    
    public void rejectTechReviewRecipe() {
        statusDraft.click();
        selectTechReview.click();
        $(By.xpath("//button[text()='Change']")).click();
        statusInReview.click();
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
        commonWaiter(openButton, Condition.visible).click();
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
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Import']")).click();
        $(By.xpath(String.format(XPATH_IMPORT_RECIPE, recipeName))).click();
        importButton.click();
        SelenideElement recipeInputSave = $(By.className("rename-recipe-import-input"));
        recipeInputSave.click();
        SelenideHelper.commonWaiter(recipeInputSave,visible).clear();
        var value=RandomStringUtils.randomAlphabetic(10);
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
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Print']")).click();
        //Alert printDialog = switchTo().alert();
        //printDialog.dismiss();
       // WebDriverRunner.getWebDriver().switchTo().window(WebDriverRunner.getWebDriver().getWindowHandles().toArray()[0].toString());
        //$(By.tagName("body")).sendKeys(Keys.TAB);
       // $(By.tagName("body")).sendKeys(Keys.ENTER);
        Selenide.sleep(2000);
        Alert printDialog = switchTo().alert();
        printDialog.dismiss();
        switchTo().frame(0);
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ESCAPE);
        r.keyRelease(KeyEvent.VK_ESCAPE);
        
        //Set<String> windowHandles = WebDriverRunner.getWebDriver().getWindowHandles();
        //System.out.println(windowHandles);
        //if (!windowHandles.isEmpty()) {
        	//WebDriverRunner.getWebDriver().switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
        //}
        //Now work with the dialog as with an ordinary page:  
        //$(By.className("cancel-button")).click();
        Selenide.sleep(5000);
        /*
		 * $(By.xpath(String.format(XPATH_IMPORT_RECIPE, recipeName))).click();
		 * importButton.click(); SelenideElement recipeInputSave =
		 * $(By.className("rename-recipe-import-input")); recipeInputSave.click();
		 * SelenideHelper.commonWaiter(recipeInputSave,visible).clear(); var
		 * value=RandomStringUtils.randomAlphabetic(10);
		 * recipeInputSave.setValue(value); saveButton.click();
		 * browserLinkText.waitUntil(Condition.visible, 5000l).click();
		 */
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
        $(By.xpath(String.format("//span[text()='%s']", recipeStatus))).click();
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
    
    public void selectRecipeStatusImported(String status,String imported) {
		commonWaiter(filterIcon, visible);
		filterIcon.click();
		commonWaiter($(By.xpath(String.format(upIcon, 1))), visible);
        $(By.xpath(String.format("//span[text()='%s']", status))).click();
        arrowIcon.click();
        commonWaiter($(By.xpath(String.format(upIcon, 2))), visible);
        $(By.xpath(String.format("//span[text()='%s']", imported))).click();
        if(imported.equalsIgnoreCase("Yes")) {
        	arrowIcon.click();
        	$(By.xpath(String.format("//div[text()='Import Status']/following::span[text()='%s']", status))).click();
        }
		applyFilterButton.click();
	}
    
    public void verifyRecipeStatusImported(String status,String imported) {
    	for(int i=1; i<=recipeListTable.size();i++) {
    		Assert.assertTrue($(By.xpath(String.format(XPATH_RecipeColumnName_Value,i, 3))).getText().equals(imported) || filterError.getText().equals("No recipes matching with the applied filter.") );
        	Assert.assertTrue($(By.xpath(String.format(XPATH_RecipeColumnName_Value,i, 7))).getText().equals(status)|| filterError.getText().equals("No recipes matching with the applied filter.") );
        	if(imported=="Yes") {
        		Assert.assertTrue($(By.xpath(String.format(XPATH_RecipeColumnName_Value,i, 4))).getText().equals(status)|| filterError.getText().equals("No recipes matching with the applied filter.") );
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
}