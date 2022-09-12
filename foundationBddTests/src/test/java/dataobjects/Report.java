package dataobjects;

import static com.codeborne.selenide.Selenide.$;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;

import technology.tabula.Table;
import utils.pdf.PdfTableExtractUtils;

public class Report {

    private final String USER_COLUMN_FORMAT = "[aA1-zZ9]+\\([aA1-zZ9\\-]+(\\s[aA1-zZ9\\-]+)*\\)";
    private final String USER_COLUMN_NAME = "User";
    private final String EVENT_COLUMN_NAME = "Event Time";
    private final String INTERNAL_USER = "OMIUser";
    private final String EVENT_TABLE_HEADER = "Event Time|Event Description|Old Value|New Value";
    private final String AUDIT_TABLE_HEADER = "Event Time|Application Name|Record|User|Comment|Attribute|Current Value|Previous Value";
    private final String REPORT_SUMMARY_TITLE = "Report Summary";
    private final String RUN_ID_FIELD = "RunId";

    @Setter
    @Getter
    String name;

    /**
     * Check run id matches expected run id in the report
     * @param reportUrl Report url
     * @param expectedRunId Expected run id
     * @throws Exception
     */
    public void checkRunId(String reportUrl, String expectedRunId) throws Exception {

        URL url = new URL(reportUrl);

        // get table from table title and check is not null and contains rows
        Table table = PdfTableExtractUtils.getTableFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE);
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows().size() > 1);

        // get field value and check is not null
        String fieldValue = PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD);
        Assert.assertNotNull("No field with id " + RUN_ID_FIELD, fieldValue);
        Assert.assertEquals("Unexpected run id value", expectedRunId, fieldValue);
    }

    /**
     * Check event table in the report
     * @param reportUrl Report url
     * @throws Exception
     */
    public void checkEventTable(String reportUrl) throws Exception {

        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTableFromTableHeader(url.openStream(), EVENT_TABLE_HEADER);

        if (table != null) {
            Assert.assertTrue("Table must contains at least one row in the body", table.getRows().size() > 1);
        }
    }
    
    public void checkAuditTable(String reportUrl) throws Exception {

        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTableFromTableHeader(url.openStream(), AUDIT_TABLE_HEADER);

        if (table != null) {
            Assert.assertTrue("Table must contains at least one row in the body", table.getRows().size() > 1);
        }
    }

    /**
     * Check user information
     * - Expected format : UserLogin(Firstname Lastname)
     * - No internal user like OMIUser
     * @param reportUrl Report url
     * @throws Exception
     */
    public void checkEventTimeInformation(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<Table> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (Table reportTable : reportTables) {
            int eventColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, EVENT_COLUMN_NAME);
            if (eventColumnIndex == 0) {
                // start from 1 to skip the header row
            	
                for (int i = 1; i < reportTable.getRowCount(); i++) {
                	SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
                    String eventColumnValue1 = reportTable.getRows().get(i).get(eventColumnIndex).getText(false);
                    if (!StringUtils.isEmpty(eventColumnValue1)) {
                        // check date range within last 7 days
                    	try {
                    		String eventColumnValue=eventColumnValue1.substring(0, 11);
                    		Date currentdate = new Date();
                        	Date eventDateTime = formatter.parse(eventColumnValue);
                        	int diffInDays = (int)( (currentdate.getTime() - eventDateTime.getTime())
                        	        / (1000 * 60 * 60 * 24) );
                        	
                        	if (diffInDays <= 7) {                    		
                        		Assert.assertTrue(true);
                        	}
                        	else {
                        		Assert.assertTrue(false);
                        	}
                    	}
                    	catch(Exception ignore) {}
                    	
                        }
                }
            }
        }
    }
    
    public void checkUserInformation(String reportUrl, String user) throws Exception {

        URL url = new URL(reportUrl);

        // get all tables of the report
        List<Table> reportTables = PdfTableExtractUtils.getTables(url.openStream());

        for (Table reportTable : reportTables) {

            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                for (int i = 1; i < reportTable.getRowCount(); i++) {

                    String userColumnValue = reportTable.getRows().get(i).get(userColumnIndex).getText(false);
                    if (!StringUtils.isEmpty(userColumnValue)) {

                        // check user is not an internal user
                        if (StringUtils.containsIgnoreCase(StringUtils.trim(userColumnValue),
                            INTERNAL_USER)) {
                            Assert.fail(String.format("Internal user in the report : %s", userColumnValue));
                        }

                        // check user format
                        Assert.assertTrue(String.format(
                            "User format error. Value : %s. Expected pattern : UserLogin(Firstname Lastname)",
                            userColumnValue), userColumnValue.matches(USER_COLUMN_FORMAT));
                        Assert.assertTrue(userColumnValue.contains(user));
                    }
                }
            }
        }
    }
}
