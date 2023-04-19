package cucumber.util;

import com.codeborne.selenide.ElementsCollection;
import com.xceptance.neodymium.util.Neodymium;
import io.cucumber.java.en.Given;
import java.util.ArrayList;
import java.util.List;

public class I18nUtils {

    @Given("I am using language {string}")
    public static void iAmUsingLanguage(String language) {
        changeLanguage(language);
    }

    public static void changeLanguage(String language) {
        Neodymium.configuration().setProperty("neodymium.locale", language);
    }

    public static String getLanguageName() {
        return Neodymium.localizedText("name");
    }

    public static String getValueFromKey(String key) {
        return Neodymium.localizedText(key);
    }

    public static List<String> getElementsNotI18N(ElementsCollection elementsCollection) {
        var elementNotTranslated = new ArrayList<String>();
        elementsCollection.forEach(element -> {
            if (!element.text().endsWith("...") && element.text().matches(".*\\..*\\..*")) {
                elementNotTranslated.add(element.text());
            }
        });
        return elementNotTranslated;
    }
}
