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
    Set<AnalyticsParameter> yParameters = new HashSet<>();

    @Setter
    @Getter
    AnalyticsParameter xParameters;

    @Setter
    @Getter
    AnalyticsMode analyticsMode;
}
