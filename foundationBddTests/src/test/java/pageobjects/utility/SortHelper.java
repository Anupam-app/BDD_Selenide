package pageobjects.utility;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.*;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static com.codeborne.selenide.Condition.visible;

public class SortHelper {

    public static void sortList(SelenideElement sortAction, SelenideElement ascendingIcon, SelenideElement descendingIcon, boolean descending) {
        Selenide.executeJavaScript("arguments[0].click()", sortAction);
        if (!descendingIcon.isDisplayed() && descending) {
            Selenide.executeJavaScript("arguments[0].click()", ascendingIcon);
            descendingIcon.shouldBe(visible);
        }

        if (!ascendingIcon.isDisplayed() && !descending) {
            Selenide.executeJavaScript("arguments[0].click()", descendingIcon);
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
	
	public static void checkSortedRolesElement(String columnName, boolean descending,
            List<String> getColumns) {
				List<String> displayedList = getColumns;
				var expectedList = new ArrayList<>(displayedList);
				if (descending) {
					expectedList.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));} 
				else {
					expectedList.sort(String.CASE_INSENSITIVE_ORDER);}
				Assert.assertEquals(expectedList, displayedList);
	}
}
