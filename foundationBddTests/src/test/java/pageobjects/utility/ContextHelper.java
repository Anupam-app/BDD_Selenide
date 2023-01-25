package pageobjects.utility;

import java.util.Objects;

public class ContextHelper {
    public static boolean isOrchestrator() {
        return Objects.equals(System.getenv("PDP"), "ORCHESTRATOR");
    }
}
