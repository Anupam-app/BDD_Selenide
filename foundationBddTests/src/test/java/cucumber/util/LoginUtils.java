package cucumber.util;

import java.util.Locale;

public class LoginUtils {

    public static String getPassword(String login) {
        switch (login.toUpperCase(Locale.ROOT)) {
            case "BIO4CADMIN":
                return "Merck@dmin";
            case "BIO4CSERVICE":
                return "Merck$ervice";
            default:
                return "MerckApp1@";
        }
    }
}
