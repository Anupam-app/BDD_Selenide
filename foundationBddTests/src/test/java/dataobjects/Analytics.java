package dataobjects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    @Getter
    List<Recipe> recipes;
}
