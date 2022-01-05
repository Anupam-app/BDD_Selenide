package dataobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AnalyticsParameter {
    @Setter
    @Getter
    String name;

    @Setter
    @Getter
    String unit;
}
