package pageobjects.utility;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.switchTo;

public class SelenideHelper {
    public static By byTestAttribute(String attributeValue) {
        return byAttribute("data-tid",attributeValue);
    }

    public static SelenideElement commonWaiter(SelenideElement element,Condition condition){
        return element.waitUntil(condition,20000l,2000l);
    }

    public static void goToIFrame(){
        switchTo().frame("CrossDomainiframeId");
    }

    public static void goParentFrame(){
        switchTo().parentFrame();
    }
}
