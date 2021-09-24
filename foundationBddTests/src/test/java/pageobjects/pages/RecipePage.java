package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class RecipePage {

    private SelenideElement recipePageLinkText = $(By.id("RecipeManagement"));
    private SelenideElement recipeSearchTextBox = $(By.id("search"));
    private SelenideElement openButton=$(By.xpath("//*[@id=\"open_recipe_block\"]/div/button"));
    private SelenideElement editorLinkText=$(By.xpath("//a[text()='Editor']"));
    private SelenideElement browserLinkText=$(By.xpath("//a[text()='Browser']"));
    private SelenideElement plusButton=$(By.className("icon-plus"));
    private SelenideElement phaseElementTextBox =$(By.className("phase-Name"));

    private By phasesList = By.className("phaseHead");
    private By deletePhaseButton = By.className("deleteButton");

    private String xpathEditPage = "//*[@id=\"recipeListTable\"]/tbody/tr/td[contains(.,'%s')]";

    public void goTo() {
        recipePageLinkText.click();
    }

    public void switchToFrame() {
        switchTo().frame("CrossDomainiframeId");
    }

    public void setSearch(String recipeName) {
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
    }

    public void goToEditMode() {
        editorLinkText.waitUntil(Condition.visible,5000l);
        editorLinkText.click();
    }

    public void goToBrowserMode() {
        browserLinkText.click();
    }

    public void edit(String recipeName) {
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.click();
    }

    public void deleteAllPhases() {
        Selenide.$$(phasesList).forEach(e->{
            e.click();
            e.find(deletePhaseButton).click();
        });
    }

    public void addPhase(String phase) {
        plusButton.waitUntil(Condition.visible,5000l);
        plusButton.click();
        SelenideElement searchTextBox=$(By.className("search-txt-box"));

        searchTextBox.sendKeys("start");
        searchTextBox.sendKeys(Keys.ENTER);
        searchTextBox.sendKeys(Keys.LEFT_CONTROL+"g");

        phaseElementTextBox.sendKeys(phase);
        phaseElementTextBox.sendKeys(Keys.ENTER);
    }

    public void saveRecipe(String recipeName) {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Save']")).click();
        SelenideElement recipeInputSave=$(By.className("selected-recipe-input"));
        recipeInputSave.setValue(recipeName);
        $(By.className("btn_primary")).click();
    }

    public String getPhaseName() {
        return phaseElementTextBox.getValue();
    }
}
