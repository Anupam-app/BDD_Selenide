package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects.utility.SelenideHelper.selectButton;
import pageobjects.utility.SelenideHelper;

public class RecipeTouchEnablerPage {

    private final String NEW_RECIPE_BUTTON = "ant-btn new-recipe-button";
    private final String IMPORT_BUTTON = "ant-btn import-button";
    private final String SAVE_BUTTON = "ant-btn save-button";
    private final String SAVE_AS_BUTTON = "ant-btn save-as-button";
    private final String EXPORT_BUTTON = "ant-btn export-as-pdf-button";

    private final String ADD_STEP_BUTTON = "ant-btn step-add-button";
    private final String ADD_CRITERIA_BUTTON = "ant-btn criteria-add-button";
    private final String ADD_PHASE_BUTTON = "ant-btn phase-add-button";

    private final String INSERT_BEFORE_STEP = "ant-btn step-insert-before-button";
    private final String INSERT_AFTER_STEP = "ant-btn step-insert-after-button";
    private final String COPY_STEP_BUTTON = "ant-btn step-copy-button";
    private final String CUT_STEP_BUTTON = "ant-btn step-cut-button";
    private final String PASTE_STEP_AFTER_BUTTON = "ant-btn step-paste-after-button";
    private final String PASTE_STEP_BEFORE_BUTTON = "ant-btn step-paste-before-button";
    private final String DELETE_STEP_BUTTON = "ant-btn step-delete-button";

    private final String COPY_PHASE_BUTTON = "ant-btn phase-copy-button";
    private final String CUT_PHASE_BUTTON = "ant-btn phase-cut-button";
    private final String PASTE_PHASE_AFTER_BUTTON = "ant-btn phase-paste-after-button";
    private final String PASTE_PHASE_BEFORE_BUTTON = "ant-btn phase-paste-before-button";
    private final String PHASE_DELETE_BUTTON = "ant-btn phase-delete-button";
    private final String SAVE_PHASE_LIBRARY_BUTTON = "ant-btn phase-library-save-button";
    private final String STEP_WAIT_TIME_BUTTON = "ant-btn step-wait-time-default-button";
    private final String CLEAR_ALL_BUTTON = "ant-btn recipe-clear-all-button";
    private final String BUTTON_LOCATOR = "//button[contains(@class,'%s')]";
    private SelenideElement buttonClick = null;
    private final String phaseUnderLib = "//p[@data-path='Phase Library--->%s']";

    /**
     * Reusable method to verify button is enabled and select the button
     * @param value : button name
     */
    public void buttonClick(String value) {
        if(value.equalsIgnoreCase("Before")){
            value= "Paste Phase Before";
        }
        else if(value.equalsIgnoreCase("After")){
            value= "Paste Phase After";
        }
        switch(value) {
            case "New Recipe":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, NEW_RECIPE_BUTTON)));
                break;
            case "Import":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, IMPORT_BUTTON)));
                break;
            case "Save":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, SAVE_BUTTON)));
                break;
            case "Save As":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, SAVE_AS_BUTTON)));
                break;
            case "Export":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, EXPORT_BUTTON)));
                break;
            case "Add Step":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, ADD_STEP_BUTTON)));
                break;
            case "Add Criteria":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, ADD_CRITERIA_BUTTON)));
                break;
            case "Add Phase":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, ADD_PHASE_BUTTON)));
                break;
            case "Insert Before Step":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, INSERT_BEFORE_STEP)));
                break;
            case "Insert After Step":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, INSERT_AFTER_STEP)));
                break;
            case "Copy Step":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, COPY_STEP_BUTTON)));
                break;
            case "Cut Step":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, CUT_STEP_BUTTON)));
                break;
            case "Paste Step After":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, PASTE_STEP_AFTER_BUTTON)));
                break;
            case "Paste Step Before":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, PASTE_STEP_BEFORE_BUTTON)));
                break;
            case "Delete Step":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, DELETE_STEP_BUTTON)));
                break;
            case "Copy Phase":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, COPY_PHASE_BUTTON)));
                break;
            case "Cut Phase":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, CUT_PHASE_BUTTON)));
                break;
            case "Paste Phase After":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, PASTE_PHASE_AFTER_BUTTON)));
                break;
            case "Paste Phase Before":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, PASTE_PHASE_BEFORE_BUTTON)));
                break;
            case "Delete Phase":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, PHASE_DELETE_BUTTON)));
                break;
            case "Save Phase Library":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, SAVE_PHASE_LIBRARY_BUTTON)));
                break;
            case "Step Wait Time":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, STEP_WAIT_TIME_BUTTON)));
                break;
            case "Clear All":
                buttonClick = $(By.xpath(String.format(BUTTON_LOCATOR, CLEAR_ALL_BUTTON)));
                break;

            default:
        }
        SelenideHelper.takePicture();
        selectButton(buttonClick);
    }

    public void verifyPhaseInLibrary(String name) {
        $(By.xpath(String.format(phaseUnderLib,name))).shouldBe(visible);
    }

}
