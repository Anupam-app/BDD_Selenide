package pageobjects.utility;

import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Assert;

public class SortHelper {

    public static void sortList(SelenideElement sortAction, SelenideElement ascendingIcon, SelenideElement descendingIcon, boolean descending) {
        Selenide.executeJavaScript("arguments[0].click()", sortAction);
        if (!descendingIcon.isDisplayed() && descending) {
            ascendingIcon.click();
            descendingIcon.shouldBe(visible);
        }

        if (!ascendingIcon.isDisplayed() && !descending) {
            descendingIcon.click();
            ascendingIcon.shouldBe(visible);
        }
    }

    public static void checkSortedElement(List<String> headers, String columnName, boolean descending,
                                          Function<Integer, List<String>> getColumns, boolean dateType, String dateFormat) {
        var index = headers.indexOf(columnName);
        List<String> displayedList = getColumns.apply(index + 1);

        if (dateType) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat, Locale.US);
            List<LocalDate> displayedDateList = displayedList.stream()
                    .map(date -> LocalDate.parse(date, formatter)).collect(Collectors.toList());
            List<LocalDate> expectedDateList = new ArrayList<>(displayedDateList);

            if (descending) {
                expectedDateList.sort(Collections.reverseOrder());
            } else {
                Collections.sort(expectedDateList);
            }
            Assert.assertEquals(expectedDateList, displayedDateList);
        } else {
            checkOrderedStringList(displayedList, descending);
        }
    }

    private static void checkOrderedStringList(List<String> displayedList, boolean descending) {
        var expectedList = new ArrayList<>(displayedList);
        if (descending) {
            expectedList.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        } else {
            expectedList.sort(String.CASE_INSENSITIVE_ORDER);
        }
        Assert.assertEquals(expectedList, displayedList);
    }

    public static void checkSortedRolesElement(String columnName, boolean descending,
                                               List<String> getColumns) {
        List<String> displayedList = getColumns;
        checkOrderedStringList(displayedList, descending);
    }
}
