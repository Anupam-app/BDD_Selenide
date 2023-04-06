package pageobjects.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.util.I18nUtils;
import dataobjects.Report;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import pageobjects.utility.SortHelper;

public class RecipePage {

	Actions stepAction = new Actions(WebDriverRunner.getWebDriver());

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
	private final SelenideElement editorLinkText = $(By.xpath("//a[contains(text(),'Editor') or contains(text(),'editor')]"));
	private final SelenideElement browserLinkText = $(By.xpath("//a[contains(text(),'Browser') or contains(text(),'browser')]"));
	private final SelenideElement phaseLibIcon = $(By.xpath("//span[(text()='Phase Library')]"));
	private final SelenideElement noPhaseAvailableMsg = $(By.xpath("//span[(text()='There is No Phase in Phase Library')]"));
	private final SelenideElement searchTextBox = $(By.className("search-txt-box"));

	private final SelenideElement recipeElementText = $(By.xpath("//div[@class='recipeTabs']"));
	private final SelenideElement notificationText = $(By.className("notification-summary"));
	private final SelenideElement headerText = $(By.xpath("//div[@class='navWrapper']//h2"));
	private final ElementsCollection deviceShapeElements = $$(By.xpath("//div[@class='search-node']//span"));

	private final SelenideElement recipeCriteriaSearchTextBox = $(By.className("search-txt-box"));
	private final SelenideElement recipeSearchTextBox = $(By.id("search"));
	private final SelenideElement phaseElementTextBox = $(By.className("phase-Name"));

	private final SelenideElement filterError = $(By.xpath("//h4"));
	private final SelenideElement openButton = $(By.className("open-recipe-btn"));
	private final SelenideElement insertStepBeforeButton = $(By.xpath("//button[@class=\"ant-btn step-insert-before-button\"]"));
	private final SelenideElement plusButton = $(By.className("icon-plus"));
	private final SelenideElement addStepButton = $(By.xpath("//*[contains(@class, 'home-screen-icon-block icon-plus')]"));
	private final By deletePhaseButton = By.className("deleteButton");
	private final SelenideElement primaryButton = $(By.className("btn-primary"));
	private final SelenideElement saveButton = $(By.xpath("//button[contains(text(),'Save') or contains(text(),'save')]"));
	private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'Ok')]"));
	private final SelenideElement deleteButton = $(By.xpath("//div[@class='phaseRow selected']//input[@class='deleteButton']"));

	private final SelenideElement XPATH_WARNNOTIFICATION_TEXT = $(By.xpath("//*[@class='editor-dialog']/div/div[1]/span"));

	private final String xpathEditPage = "//*[@id=\"recipeListTable\"]/tbody/tr/td[contains(.,'%s')]";
	private final String chooseOption = "//*[@class=\"submenu-value-left\"]/label[text()='%s']";
	private final String deletePhaseMessage = "//span[(text()='%s') and (text()='%s')]";
	private final String touchIdButtons = "//button[@class='%s']";
	private final String phasenumber_Label = "//label[contains(text(),'%s')]";
	private final String phaseNameLabel = "(//input[@class='phase-Name'])[%d]";
	private final String phaseMessage = "//span[(text()='%s')]";
	private final String label = "//label[contains(text(),'%s')]";
	private final By phasesList = By.className("phaseHead");

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


	private final ElementsCollection recipeListTable = $$(By.xpath("//*[@id='recipeListTable']/tbody/tr"));
	private final SelenideElement dateColumn = $(By.xpath("//input[@name='dateRange']"));
	private ElementsCollection dateOptionsRprt = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opens')]/div/ul/li"));
	private final SelenideElement noDatamsg = $(By.xpath("//h4[text()='No runs matching with the applied filter.']"));
	private final SelenideElement startDateRep = $(By.xpath("//table[@id='recipeListTable']/tbody/tr[1]/td[6]"));
	private final SelenideElement previousMonth = $(By.xpath("//div[@class='drp-calendar left']//th[@class='prev available']"));
	private ElementsCollection availableDates = $$(By.xpath("//div[@class='drp-calendar left']/div/table/tbody/tr/td[@class='available']"));
	Function<Integer, List<String>> getRecipeColumns = (index) -> $$(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_INDEX, index))).texts();
	private final SelenideElement recipeBlock = $(By.xpath("//div[@class='recipe-data-block']"));
	private final SelenideElement stepPlaceholder = $(By.xpath("//input[@placeholder='Search instruments and actions...']"));
	private final String stepCountPlaceholder = "(//input[@placeholder='Search instruments and actions...'])[%s]";
	private final String rootStep = "(//span[text()='%s']/parent::span/span)[1]";
	private final String stepNumber = "//div[@class='stepNumber' and @data-label='step%s']";
	private final SelenideElement criteriaPlaceholder = $(By.xpath("//input[@placeholder='Search criteria...']"));
	private final SelenideElement opertionAction = $(By.xpath("//span[contains(text(),'Operation Actions')]"));
	private final String expandAction = "//p[@title='%s']";
	private final SelenideElement messageStepVaidate = $(By.xpath("//input[@placeholder ='Enter text']"));
	private final String editorRecipeName = "//label[contains(text(),'%s')]";
	private final SelenideElement recipeInputSave = $(By.xpath("//input[@class='ant-input selected-recipe-input']"));
	private final SelenideElement notificationMessage = $(By.xpath("//div[@class='notification-bar warning-bar']"));
	private final String recipe_Step = "//label[@class='stepCount' and text()=%s]";
	private final String searchPlaceholder = "(//input[@placeholder='Search instruments and actions...'])[%s]";
	public static final String RECIPE_DATE_FILTER_IVI = "MMM d, yyyy";
	private final SelenideElement recipe_BlueNotification = $(By.xpath("//div[@class='notification-bar information-bar']"));
	private final SelenideElement recipeManagementHeader = $(By.xpath("//h2[text()='Recipe Management']"));
	private final SelenideElement goToPhaseButton = $(By.className("go-to-phase-btn"));
	private final SelenideElement goToStep = $(By.xpath("//div[@class='go-to-steps-btn']"));
	private final SelenideElement stepInvocation = $(By.xpath("(//td[@class='step-cell'])[2]"));
	private SelenideElement latestRecipeName = $(By.xpath("(//table[@class='table']/tbody/tr/td)[1]"));
	private final String addSteps = "(//span[@class='target'])[%s]";
	private final String OperationalActionsList = "//p[@class='action-item lavel-2'][@title='%s']";
	private final String warningOptions = "//button[text()='%s']";
	private final SelenideElement close_Btn = $(By.xpath("//button[text()='x']"));
	private final String popUpMessage = "//span[text()='%s']";
	private SelenideElement phaseOne = $(By.xpath("//label[text()='Phase 1']"));
	private SelenideElement phaseLibrary = $(By.xpath("//label[text()='Save to phase library']"));
	private final String errorMsg = "//h4[text()='%s']";
	private final String phaseAction = "(//div[@class='last-step'])[last()]//label[text()='%s']";
	private final SelenideElement changeSteps = $(By.xpath("(//div[@class='action'])[2]"));
	private final String blankRecipeMessage = "//div[text()='%s']";

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

	public void goToPhaseLibrary() {
		phaseLibIcon.waitUntil(Condition.visible, 5000l).click();
		noPhaseAvailableMsg.shouldBe(visible);
	}

	public void verifyPhaseMessage(String message) {
		Assert.assertTrue(noPhaseAvailableMsg.getText().equalsIgnoreCase(message));
	}

	public void goToBrowserMode() {
		browserLinkText.click();
	}

	public void verifyList() {
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
		searchTextBox.sendKeys("All Auto");
		searchTextBox.sendKeys(Keys.ENTER);
		searchTextBox.sendKeys(Keys.LEFT_CONTROL + "c");
		searchTextBox.sendKeys(Keys.LEFT_CONTROL + "v");
		searchTextBox.sendKeys(Keys.LEFT_CONTROL + "c");
		searchTextBox.sendKeys(Keys.LEFT_CONTROL + "v");
		searchTextBox.sendKeys(Keys.LEFT_CONTROL + "g");
		commonWaiter(notificationMessage, visible).shouldHave(text("Phase creation in progress. Press \"Enter\" once completed."));
		phaseElementTextBox.sendKeys(phase);
		phaseElementTextBox.sendKeys(Keys.ENTER);
	}

	public void saveRecipe(String recipeName) {
		saveEditorButton.click();
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
		SelenideHelper.commonWaiter(recipeSearchTextBox, visible).setValue(recipeName);
		recipeSearchTextBox.sendKeys(Keys.ENTER);
		commonWaiter($(By.xpath(String.format(xpathEditPage, recipeName))), visible).click();
		openButton.waitUntil(Condition.visible, 5000l);
		openButton.click();
	}

	public void deletePhaseToRecipe() {
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 1"))), visible).click();
		commonWaiter(deleteButton, visible).click();
		String phaseName = $(By.xpath(String.format(phaseNameLabel, 1))).getText();
		$(By.xpath(String.format(deletePhaseMessage, "Proceed with deleting the Phase -", phaseName))).shouldBe(visible);
		commonWaiter(okButton, visible).click();
	}

	public void deletePhaseToRecipeWithShortCutKeys() {
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 1"))), visible).click();
		stepAction.keyDown(commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 1"))), visible), Keys.LEFT_CONTROL).sendKeys(Keys.DELETE).perform();
		String phaseName = $(By.xpath(String.format(phaseNameLabel, 1))).getText();
		$(By.xpath(String.format(deletePhaseMessage, "Proceed with deleting the Phase -", phaseName))).shouldBe(visible);
		commonWaiter(okButton, visible).click();
	}

	public void saveAsRecipeWithShortCutKeys(String recipeName) {
		stepAction.keyDown(recipeBlock, Keys.SHIFT).keyDown(Keys.CONTROL).sendKeys("s").perform();
		recipeInputSave.click();

		SelenideHelper.commonWaiter(recipeInputSave, visible).clear();
		recipeInputSave.click();

		SelenideHelper.fluentWaiter().until(
				(webDriver) -> {
					recipeInputSave.setValue(recipeName);
					return recipeInputSave.getValue().equals(recipeName);
				}
				);

		saveButton.click();
	}

	public void copyPhase() {
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 1"))), visible).click();
		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-copy-button"))).click();
	}

	public void pastePhase(String phaseName) {
		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-paste-after-button"))).click();
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 2"))), visible).shouldBe(visible);
		$(By.xpath(String.format(phaseNameLabel, 2))).shouldHave(value(phaseName + "_2"));
		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-copy-button"))).click();
		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-paste-before-button"))).click();
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 3"))), visible).shouldBe(visible);
		$(By.xpath(String.format(phaseNameLabel, 3))).shouldHave(value(phaseName + "_2"));
		$(By.xpath(String.format(phaseNameLabel, 2))).shouldHave(value(phaseName + "_2_3"));
	}

	public void verifyPhaseButtons() {
		($(By.xpath("//div[(text()='Start creating your recipe by adding actions or phases from the right or pressing') ]/span[(text()='alt')]"))).shouldBe(visible);
		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-copy-button"))).click();
		$(By.xpath(String.format(phaseMessage, "Please add a phase."))).shouldBe(visible);
		commonWaiter(okButton, visible).click();

		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-cut-button"))).click();
		$(By.xpath(String.format(phaseMessage, "Please select a phase."))).shouldBe(visible);
		commonWaiter(okButton, visible).click();

		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-paste-after-button"))).click();
		$(By.xpath(String.format(phaseMessage, "No phase is copied."))).shouldBe(visible);
		commonWaiter(okButton, visible).click();

		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-paste-before-button"))).click();
		$(By.xpath(String.format(phaseMessage, "No phase is copied."))).shouldBe(visible);
		commonWaiter(okButton, visible).click();

		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-delete-button"))).click();
		$(By.xpath(String.format(phaseMessage, "Please select a phase."))).shouldBe(visible);
		commonWaiter(okButton, visible).click();

		$(By.xpath(String.format(touchIdButtons, "ant-btn phase-library-save-button"))).click();
		$(By.xpath(String.format(phaseMessage, "Please select a phase."))).shouldBe(visible);
		commonWaiter(okButton, visible).click();
	}

	public void iSeePhaseDeleted() {
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 1"))), visible).shouldNotBe(visible);
	}

	public void approveRecipe(String password) {
		commonWaiter(statusDraft, visible).click();
		selectInReview.click();
		primaryButton.click();
		okButton.click();
		commonWaiter(statusInReview, visible).click();
		selectApprove.click();
		$(By.xpath("//button[contains(text(),'Change')]")).click();
		inputPassword.sendKeys(password);
		$(By.xpath("//button[contains(text(),'SIGN')]")).click();
		statusApproved.waitUntil(Condition.visible, 5000l);
		browserLinkText.click();
		editorLinkText.click();
	}

	public void inactiveRecipe(String password) {
		commonWaiter(statusDraft, visible).click();
		selectInReview.click();
		primaryButton.click();
		$(By.xpath("//button[text()='Ok']")).click();
		commonWaiter(statusInReview, visible).click();
		selectInactive.click();
		$(By.xpath("//button[text()='Change']")).click();
		inputPassword.sendKeys(password);
		$(By.xpath("//button[text()='SIGN']")).click();
		statusApproved.waitUntil(Condition.visible, 5000l);
		browserLinkText.click();
		editorLinkText.click();
	}

	public void rejectTechReviewRecipe() {
		commonWaiter(statusDraft, visible).click();
		selectTechReview.click();
		$(By.xpath("//button[text()='Change']")).click();
		commonWaiter(statusInReview, visible).click();
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
		SelenideHelper.commonWaiter(importMenuButton, visible).click();
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

	public void printRecipe(String recipeName) {
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

	public void selectDateRange(String option) {
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

	public boolean verifyDateRanges(String dateRange) throws ParseException {

		boolean isTrue = false;
		switch (dateRange) {
		case "Today":
		case "Yesterday":
			String dateValue = dateColumn.getAttribute("value").split("To")[0].trim();
			LocalDate selectedDate = SelenideHelper.dateParser(dateValue, RECIPE_DATE_FILTER_IVI);
			if (startDateRep.isDisplayed()) {
				sortList("Last Modified On", false);
				Selenide.sleep(1000);
				String startDateRow1 = startDateRep.getText();
				LocalDate selectedAsendingDate = SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
				sortList("Last Modified On", true);
				Selenide.sleep(1000);
				startDateRow1 = startDateRep.getText();
				LocalDate selectedDesendingDate = SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
				if (selectedAsendingDate.getDayOfMonth() == selectedDate.getDayOfMonth() && selectedDesendingDate.getDayOfMonth() == selectedDate.getDayOfMonth()) {
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
			String dateValue1 = dateColumn.getAttribute("value").split("To")[0].trim();
			LocalDate selectedDate1 = SelenideHelper.dateParser(dateValue1, RECIPE_DATE_FILTER_IVI);
			String dateValue2 = dateColumn.getAttribute("value").split("To")[1].trim();
			LocalDate selectedDate2 = SelenideHelper.dateParser(dateValue2, RECIPE_DATE_FILTER_IVI);
			if (startDateRep.isDisplayed()) {
				sortList("Last Modified On", false);
				Selenide.sleep(1000);
				String startDateRow = startDateRep.getText();
				LocalDate selectedAsendingDate = SelenideHelper.dateParser(startDateRow, Report.RECIPE_DATE_FORMAT);
				sortList("Last Modified On", true);
				Selenide.sleep(1000);
				String endDateRow = startDateRep.getText();
				LocalDate selectedDesendingDate = SelenideHelper.dateParser(endDateRow, Report.RECIPE_DATE_FORMAT);
				if ((selectedAsendingDate.getDayOfMonth() == selectedDate1.getDayOfMonth() || selectedAsendingDate.isAfter(selectedDate1))
						&& (selectedDesendingDate.getDayOfMonth() == selectedDate2.getDayOfMonth()
						|| selectedDesendingDate.isBefore(selectedDate2))) {
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
		SortHelper.checkSortedElement(getAllRecipeColumnHeaders(), columnName,
				descending, getRecipeColumns, columnName.equals("Last Modified On"), Report.RECIPE_DATE_FORMAT);
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

	public void keyboardActionRecipe() {
		commonWaiter(editorLinkText, visible);
		stepAction.keyDown(recipeBlock, Keys.ALT).sendKeys(Keys.ENTER).perform();
	}

	public void placeholder(String status) {
		if (status.equalsIgnoreCase("blank")) {
			stepPlaceholder.waitUntil(Condition.visible, 50001);
		} else if (status.equalsIgnoreCase("action")) {
			stepPlaceholder.shouldNotHave(text("Search instruments and actions..."));
		}
	}

	public void addActionStep() {
		stepPlaceholder.click();
		stepPlaceholder.clear();
		stepPlaceholder.sendKeys("Setpoint");
		stepPlaceholder.sendKeys(Keys.ENTER);
	}

	public void addStepActionBrowser() {
		opertionAction.waitUntil(visible, 2000).click();
		$(By.xpath(String.format(expandAction, "Product Inlet"))).click();
		$(By.xpath(String.format(expandAction, "Flowpath"))).click();
		$(By.xpath(String.format(expandAction, "Control"))).waitUntil(visible, 1000).click();
		$(By.xpath(String.format(rootStep, "Inlet"))).doubleClick();
	}

	public void addMessageInStep() {
		stepAction.keyDown(recipeBlock, Keys.ALT).sendKeys(Keys.ENTER).perform();
		$(By.xpath(String.format(stepCountPlaceholder, "2"))).click();
		$(By.xpath(String.format(stepCountPlaceholder, "2"))).waitUntil(visible, 1000).sendKeys("Snooze");
		$(By.xpath(String.format(stepCountPlaceholder, "2"))).sendKeys(Keys.ENTER);
	}

	public void messageInputStepValidate() {
		messageStepVaidate.waitUntil(visible, 1000);
	}

	public void addingPhaseByPlus() {
		plusButton.waitUntil(Condition.visible, 5000l);
		plusButton.click();
	}

	public void addCriteria() {
		commonWaiter($(By.xpath(String.format(stepNumber, "1"))),visible).click();
		stepAction.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_UP).perform();
		criteriaPlaceholder.sendKeys("Ended");
		criteriaPlaceholder.sendKeys("Running");
		criteriaPlaceholder.sendKeys(Keys.ENTER);
	}

	public void openRecipe(String recipeName) {
		commonWaiter(browserLinkText,visible).click();
		recipeSearchTextBox.sendKeys(recipeName);
		recipeSearchTextBox.sendKeys(Keys.ENTER);
		$(By.xpath(String.format(XPATH_IMPORT_RECIPE, recipeName))).click();
		openButton.click();
	}

	public void verifyRecipeEditor(String recipeName) {
		commonWaiter(editorLinkText, visible);
		$(By.xpath(String.format(editorRecipeName, recipeName))).shouldHave(text(recipeName));
	}

	public void warningMessage(String message) {
		switchTo().frame("CrossDomainiframeId");
		commonWaiter($(By.xpath("//label[text()='Approved-InActive']")),visible).click();
		String actual = $(By.xpath("//div[text()='No Status Change allowed.']")).getText();
		Assert.assertEquals(actual, message);
		$(By.xpath("//button[@class='btn-secondary']")).click();
	}

	public void cannotEditRecipe() {
		searchTextBox.shouldNotBe(selected);
	}

	public void cannotEditRecipeStatus() {
		commonWaiter(statusDraft, visible).shouldNotBe(selected);
	}

	public void cannotClickTouchButtons() {
		commonWaiter(insertStepBeforeButton, visible).shouldBe(selected);
	}

	public void touchButtonNotDisplayed() {
		commonWaiter(insertStepBeforeButton,not(visible));
	}

	public void verifyRecipeHeader() {
		recipeManagementHeader.shouldBe(visible);
	}

	public void renamePhase(String phaseName) {
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 1"))), visible);
		stepAction.doubleClick($(By.xpath(String.format(phaseNameLabel, 1)))).perform();
		commonWaiter($(By.xpath(String.format(phaseNameLabel, 1))), visible).sendKeys(Keys.ENTER);
		commonWaiter($(By.xpath(String.format(phaseNameLabel, 1))), visible).sendKeys(phaseName);
		commonWaiter($(By.xpath(String.format(phaseNameLabel, 1))), visible).sendKeys(Keys.ENTER);
	}

	public void verifyPhaseIsRenamed(String phaseName) {
		commonWaiter($(By.xpath("(//div[@class='phase-invocation stepNumber']/label[@class='stepCount'])[1]")),visible);
		$(By.xpath(String.format("//label[text()='%s']", phaseName))).shouldBe(visible);
	}

	public void iSeeDeletedPhaseIsNotSeenInStep() {
		Selenide.sleep(1000);
		$(By.xpath(String.format(phasenumber_Label, "PSH1"))).shouldNotBe(visible);
		$(By.xpath(String.format(phasenumber_Label, "PSH2"))).shouldBe(visible);
	}

	public void deletePhaseToRecipeWithCrossButton() {
		commonWaiter($(By.xpath(String.format(phasenumber_Label, "Phase 1"))), visible).click();
		commonWaiter($(By.xpath("(//input[@class='deleteButton'])[3]")), visible).click();		
		String phaseName = $(By.xpath(String.format(phaseNameLabel, 1))).getText();
		$(By.xpath(String.format(deletePhaseMessage, "Proceed with deleting the Phase -", phaseName))).shouldBe(visible);
		commonWaiter(okButton, visible).click();
	}

	public void addActionStepAfterStep(String stepNo) {
		commonWaiter($(By.xpath(String.format(recipe_Step, stepNo))),visible).click();
		stepAction.keyDown(Keys.ALT).sendKeys(Keys.ENTER).perform();
	}

	public void iSeeBlankStep() {
		Assert.assertTrue(($(By.xpath(String.format(searchPlaceholder,"6")))).getAttribute("value").isBlank());
	}

	public void verifyRecipeTab() {
		$(By.xpath(String.format(label, "Untitled"))).shouldBe(visible);
		$(By.xpath(String.format(label, "Unsaved"))).shouldBe(visible);
	}

	public void createPhase(String phase) {
		addStepButton.click();
		searchTextBox.sendKeys("All Auto");
		searchTextBox.sendKeys(Keys.ENTER);
		searchTextBox.sendKeys(Keys.LEFT_CONTROL + "g");
		commonWaiter(notificationMessage, visible).shouldHave(text("Phase creation in progress. Press \"Enter\" once completed."));
		phaseElementTextBox.sendKeys(phase);
		phaseElementTextBox.sendKeys(Keys.ENTER);
	}

	public void verifyNotification(String message) {
		commonWaiter(recipe_BlueNotification, visible).shouldHave(text(message));
	}

	public void addFewSteps() {
		$(By.xpath(String.format(addSteps, "1"))).click();
		stepAction.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
		stepAction.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
	}

	public void createPhaseWithMutlipleSteps(String phaseName){
		$(By.xpath(String.format(addSteps, "1"))).click();
		stepAction.keyDown(Keys.CONTROL).sendKeys("g").keyUp(Keys.CONTROL).build().perform();
		phaseElementTextBox.sendKeys(phaseName);
		phaseElementTextBox.sendKeys(Keys.ENTER);
	}

	public void goToPhaseBtn(){
		commonWaiter(goToPhaseButton,visible).click();
	}

	public void goToStep(){
		commonWaiter(goToStep,visible).click();
	}

	public void stepInvocation(){
		stepInvocation.shouldBe(visible);
	}

	public void iCheckTwoRecipes(String recipes) {
		SelenideHelper.commonWaiter(recipeSearchTextBox, visible).click();
		recipeSearchTextBox.clear();
		recipeSearchTextBox.sendKeys(recipes);
		recipeSearchTextBox.sendKeys(Keys.ENTER);
		recipeSearchTextBox.shouldHave(attribute("value",latestRecipeName.getText()));
	}

	public void stepsNotModified() {
		$(By.xpath(String.format(addSteps, "1"))).shouldBe(visible);
		$(By.xpath(String.format(addSteps,"2"))).shouldNotBe(visible);
	}

	public void verifyUnsaved(){
		$(By.xpath(String.format(label, "Unsaved"))).shouldBe(visible);
	}

	public void operationActions(){
		commonWaiter(opertionAction,visible).click();
	}

	public void verifyPhaseOption(){
		$(By.xpath(String.format(OperationalActionsList,"Phases"))).shouldBe(visible);
	}

	public void verifySavedRecipe(){
		$(By.xpath(String.format(label,"Saved"))).shouldBe(visible);
	}

	public void closeBtn() {
		stepAction.moveToElement(recipeElementText).moveToElement(close_Btn).click().perform();
		System.out.println("Clear the recipe name");
	}

	public void blankRecipe() {
		$(By.xpath(String.format(blankRecipeMessage, "Start creating your recipe by adding actions or phases from"))).waitUntil(Condition.visible, 50001);
	}

	public void cancelRecipe() {
		$(By.xpath(String.format(warningOptions, "Cancel"))).waitUntil(Condition.visible, 50001).click();
	}

	public void iDiscard_Btn() {
		$(By.xpath(String.format(warningOptions, "Discard"))).waitUntil(Condition.visible, 50001).click();
	}

	public void saveFromWarningBox() {
		$(By.xpath(String.format(warningOptions, "Save"))).waitUntil(Condition.visible, 50001).click();
	}

	public void singleStep() {
		plusButton.waitUntil(Condition.visible, 5000l);
		plusButton.click();
		SelenideElement searchTextBox = $(By.className("search-txt-box"));
		searchTextBox.sendKeys("setpoint");
		searchTextBox.sendKeys(Keys.ENTER);
	}

	public void creatingPhaseWithError(){
		plusButton.waitUntil(Condition.visible, 5000l);
		plusButton.click();
		SelenideElement searchTextBox = $(By.className("search-txt-box"));
		searchTextBox.sendKeys("Unit");
		searchTextBox.sendKeys(Keys.ENTER);
		$(By.xpath(String.format(addSteps,"1"))).click();
		stepAction.keyDown(Keys.CONTROL).sendKeys("g").keyUp(Keys.CONTROL).build().perform();
		phaseElementTextBox.setValue(RandomStringUtils.randomAlphabetic(3));
		phaseElementTextBox.sendKeys(Keys.ENTER);
	}

	public void addPhaseLibraryWithErrorPhase() throws AWTException {
		SelenideHelper.commonWaiter(phaseOne, visible).click();
		zoomOut();
		stepAction.contextClick(phaseOne).perform();
		stepAction.moveToElement(phaseLibrary).click().perform();
	}

	public void zoomOut() throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < 5; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

	public void checkErrorMsg() {
		$(By.xpath(String.format(errorMsg, "Error"))).waitUntil(Condition.visible, 50001);
	}

	public void checkWindowPopupMsg() {
		$(By.xpath(String.format(popUpMessage, "Phase has errors. Cannot add to Phase Library."))).waitUntil(Condition.visible, 50001);
		commonWaiter(okButton, visible).click();
	}

	public void iClearPhaseErrorStep() {
		commonWaiter(changeSteps, visible).click();
		stepAction.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		stepAction.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	}
}