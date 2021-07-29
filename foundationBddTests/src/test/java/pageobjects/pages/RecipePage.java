package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class RecipePage extends AbstractPageObject {

    private SelenideElement recipePage = $(By.id("RecipeManagement"));
    private SelenideElement recipeSearch = $(By.id("search"));
    private SelenideElement openButton=$(By.xpath("//*[@class=\"open-recipe-btn\"]/div/button[contains(.,'Open')]"));
    private SelenideElement editorLink=$(By.xpath("//a[text()='Editor']"));
    private SelenideElement browserLink=$(By.xpath("//a[text()='Browser']"));
    private SelenideElement plusButton=$(By.className("icon-plus"));
    private SelenideElement phaseElement=$(By.className("phase-Name"));

    private By phases = By.className("phaseHead");
    private By deletePhaseButton = By.className("deleteButton");

    private String xpathEditPage = "//*[@id=\"recipeListTable\"]/tbody/tr/td[contains(.,'%s')]";

    public void goTo() {
        recipePage.click();
    }

    public void switchToFrame() {
        switchTo().frame("CrossDomainiframeId");
    }

    public void setSearch(String recipeName) {
        recipeSearch.setValue(recipeName);
        recipeSearch.sendKeys(Keys.ENTER);
    }

    public void goToEditMode() {
        editorLink.waitUntil(Condition.visible,5000l);
        editorLink.click();
    }

    public void goToBrowserMode() {
        browserLink.click();
    }

    public void edit(String recipeName) {
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.click();
    }

    public void deleteAllPhases() {
        Selenide.$$(phases).forEach(e->{
            e.click();
            e.find(deletePhaseButton).click();
        });
    }

    public void addPhase(String phase) {
        plusButton.click();
        SelenideElement searchTextBox=$(By.className("search-txt-box"));

        searchTextBox.sendKeys("start");
        searchTextBox.sendKeys(Keys.ENTER);
        searchTextBox.sendKeys(Keys.LEFT_CONTROL+"g");

        phaseElement.sendKeys(phase);
        phaseElement.sendKeys(Keys.ENTER);
    }

    public void saveRecipe(String recipeName) {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Save']")).click();
        SelenideElement recipeInputSave=$(By.className("selected-recipe-input"));
        recipeInputSave.setValue(recipeName);
        $(By.className("btn_primary")).click();
    }

    public String getPhaseName() {
        return phaseElement.getValue();
    }
}
