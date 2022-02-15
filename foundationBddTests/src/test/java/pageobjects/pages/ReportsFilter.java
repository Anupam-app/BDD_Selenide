package pageobjects.pages;

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

public class ReportsFilter {

	private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
	private SelenideElement upIcon = $(By.xpath("(//div[@class='up-icon']))[1])"));
	private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));
	private SelenideElement columnText = $(By.xpath("//td[1]"));
	private final SelenideElement reportSearch = $(By.xpath("//input[@placeholder='Search...']"));
	
	public void selectTemplateStatus(String templateStatus) {
		commonWaiter(filterIcon,visible);
    	filterIcon.click();
    	commonWaiter(upIcon,visible);
    	$(By.xpath(String.format("//span[text()='%s']", templateStatus))).click();
    	applyFilterButton.click();
	}
	 public String getTemplateName() {
		 return columnText.getText();
	 }
	 
	 public void searchReport(String reportName) {
		 SelenideHelper.commonWaiter(reportSearch, visible).setValue(reportName);
	 }
	 
	 public String getReportName() {
		 return columnText.getText();
	 }
	 
	 public void selectReportType(String reportType) {
			commonWaiter(filterIcon,visible);
	    	filterIcon.click();
	    	commonWaiter(upIcon,visible);
	    	$(By.xpath(String.format("//span[text()='%s']", reportType))).click();
	    	applyFilterButton.click(); 
	 }
	 
	 public String getRecipeName() {
		 return columnText.getText();
	 }
	 
	 public void selectRunStatus(String runReportStatus) {
			commonWaiter(filterIcon,visible);
	    	filterIcon.click();
	    	commonWaiter(upIcon,visible);
	    	upIcon.click();
	    	$(By.xpath(String.format("//span[text()='%s']", runReportStatus))).click();
	    	applyFilterButton.click(); 
	 }
}
