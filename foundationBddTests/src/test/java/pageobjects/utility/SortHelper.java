package pageobjects.utility;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static com.codeborne.selenide.Condition.visible;

public class SortHelper {

    public static void sortList(SelenideElement sortAction, SelenideElement ascendingIcon, SelenideElement descendingIcon, boolean descending) {
        sortAction.click();
        if (!descendingIcon.isDisplayed() && descending) {
            sortAction.click();
            descendingIcon.shouldBe(visible);
        }

        if (!ascendingIcon.isDisplayed() && !descending) {
            sortAction.click();
            ascendingIcon.shouldBe(visible);
        }
    }

    public static void checkSortedElement(List<String> headers, String columnName, boolean descending,
                                          Function<Integer, List<String>> getColumns) {
        var index = headers.indexOf(columnName);

        List<String> displayedList = getColumns.apply(index + 1);

        var expectedList = new ArrayList<>(displayedList);
        if (descending) {
            expectedList.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        } else {
            expectedList.sort(String.CASE_INSENSITIVE_ORDER);
        }

        Assert.assertEquals(expectedList, displayedList);
    }
}
