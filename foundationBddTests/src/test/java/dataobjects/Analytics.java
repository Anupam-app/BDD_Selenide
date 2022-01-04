package dataobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Analytics {
    @Setter
    @Getter
    String name;

    @Setter
    @Getter
    Set<String> yParameters = new HashSet<>();

    @Setter
    @Getter
    String xParameters;

    @Setter
    @Getter
    AnalyticsMode analyticsMode;
}
