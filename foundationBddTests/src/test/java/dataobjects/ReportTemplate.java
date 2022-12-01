package dataobjects;

import lombok.Getter;
import lombok.Setter;

public class ReportTemplate {
    @Setter
    @Getter
    String name = "testDraftTemplate";

    @Setter
    @Getter
    String status;

    @Setter
    @Getter
    String SaveAsName;
}
