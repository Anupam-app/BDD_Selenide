package cucumber.util;

import com.xceptance.neodymium.util.Neodymium;
import io.cucumber.java.en.Given;

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
}
