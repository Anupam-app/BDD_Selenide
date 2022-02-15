package pageobjects.pages;

import org.openqa.selenium.WebElement;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import org.junit.Assert;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class UserFilter {
	
	private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
	private final SelenideElement userPreferences = $(By.xpath("//Span[text()='User preferences']"));
	private SelenideElement selectOption = $(By.xpath("//span[@class='icon-down-arrow']"));
	private SelenideElement saveButton = $(By.xpath("//span[text()='Save']"));
	private SelenideElement activeIconName = $(By.xpath("//div[@class='icontitle active']"));
	private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
	private SelenideElement upArrow = $(By.xpath("//div[@class='arrowupuserfilter']"));
    private SelenideElement applyFilterButton = $(By.xpath("//button/b[text()='Apply Filter']"));
    private SelenideElement filterTagText = $(By.xpath("//div[@class='userfiltertag']"));
    private SelenideElement userNameText = $(By.xpath("(//td[@class='customusername'])[1]"));
    private SelenideElement nameList = $(By.xpath("//td[1]"));
    
	public void userProfile() {
		userProfileIcon.click();
	}
	
	public void userPreferences() {
		userPreferences.click();
	}
	
    public void defaultOption(String defaultOptionName) {
        SelenideHelper.commonWaiter(selectOption, visible).click();
        $(By.xpath(String.format("//li[text()='%s']", defaultOptionName))).click();
        saveButton.click();
    }

    public String getActiveIconTitle() {
    return activeIconName.getText();
    }
    
    public void selectUserStatus(String status) {
    	commonWaiter(filterIcon,visible);
    	filterIcon.click();
    	commonWaiter(upArrow,visible);
    	upArrow.click();
    	$(By.xpath(String.format("//span[text()='%s']", status))).click();
    	applyFilterButton.click();
    }
    
    public String getFilterTagText() {
    	return filterTagText.getText();
    }
    
    public String getUserName() {
    	return userNameText.getText();
    }
    
    public void sortList(String columnName,String sortMode) {
    	if(sortMode=="Ascending") {
    	List actualList = new ArrayList();
    	List<WebElement> users=nameList.findElements(By.tagName("td"));
    	for(WebElement ele:users) {
    		String data=ele.getText();
    		actualList.add(data);
    	}
    	List tempList = new ArrayList();
    	tempList.addAll(actualList);
    	Collections.sort(tempList);
    	Assert.assertTrue(actualList.equals(tempList));
    	}
    	
    	else if (sortMode=="Descending") {
    		$(By.xpath(String.format("//th[text()='%s']", columnName))).click();
    		List actualList = new ArrayList();
        	List<WebElement> users=nameList.findElements(By.tagName("td"));
        	for(WebElement ele:users) {
        		String data=ele.getText();
        		actualList.add(data);
        	}
        	List tempList = new ArrayList();
        	tempList.addAll(actualList);
        	Collections.sort(tempList);
        	Assert.assertTrue(actualList.equals(tempList));
    	}
    }
}


