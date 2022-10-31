package dataobjects;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import technology.tabula.Table;
import utils.pdf.PdfTableExtractUtils;

public class Report {

    private final String USER_COLUMN_FORMAT = "[aA1-zZ9]+\\([aA1-zZ9\\-]+(\\s[aA1-zZ9\\-]+)*\\)";
    private final String USER_COLUMN_NAME = "User";
    private final String INTERNAL_USER = "OMIUser";
    private final String EVENT_TABLE_HEADER = "Event Time|Event Description|Old Value|New Value";
    private final String REPORT_SUMMARY_TITLE = "Report Summary";
    private final String RUN_ID_FIELD = "RunId";
    private final String UNIT_OPERATION_NAME = "Unit Operation name";
    private final String BATCH_ID = "Batch ID";
    private final String PRODUCT_ID = "ProductID";
    private final String RECIPE_NAME = "Recipe Name";
    private final String START_DATE = "Start Date";
    private final String END_DATE = "End Date";
    private final String PRE_RUN_COMMENT = "Pre-run Comment";
    private final String POST_RUN_COMMENT = "Post-run Comment";
    private final String RUN_STATUS = "Run Status";
    private final String TEMPLATE_NAME = "Template Name";
    private final String PRE_RUN_DATE_TITLE = "Pre Run Data";
    private final String RECIPE_STEPS_SUMMARY = "Recipe Steps Summary";
    private final String EVENT_TIME = "Event Time";
    private final String EVENT_DESCRIPTION = "Event Description";
    private final String SETPOINT = "Setpoint";
    private final String EGU = "EGU";
    private final String STEP_START_TIME = "Step Start Time";
    private final String STEP_NUMBER = "Step Number";
    private final String PHASE_NUMBER = "Phase Number";
    private final String ACTION_DESCRIPTION = "Action Description";
    private final String EVENT_COLUMN_NAME = "Event Time";
    private final String AUDIT_TABLE_HEADER = "Event Time|Application Name|Record|User|Comment|Attribute|Current Value|Previous Value";


    @Setter
    @Getter
    String name;
    @Setter
    @Getter
    String reportName;

    /**
     * Check run id matches expected run id in the report
     *
     * @param reportUrl     Report url
     * @param expectedRunId Expected run id
     * @throws IOException
     */
    public void checkRunId(String reportUrl, String expectedRunId) throws IOException {

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
     *
     * @param reportUrl Report url
     * @throws IOException
     */
    public void checkEventTable(String reportUrl) throws IOException {

        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTableFromTableHeader(url.openStream(), EVENT_TABLE_HEADER);
        if (table != null) {
            Assert.assertTrue("Table must contains at least one row in the body", table.getRows().size() > 1);
        }
    }

    /**
     * Check audit table in the report
     *
     * @param reportUrl Report url
     * @throws IOException
     */
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
     *
     * @param reportUrl Report url
     * @throws IOException
     */

    public void checkUserInformation(String reportUrl, String user) throws IOException {


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


    /**
     * Check Event Time information
     * - No internal user like OMIUser
     *
     * @param reportUrl Report url
     * @throws IOException
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
                            String eventColumnValue = eventColumnValue1.substring(0, 11);
                            Date currentdate = new Date();
                            Date eventDateTime = formatter.parse(eventColumnValue);
                            int diffInDays = (int) ((currentdate.getTime() - eventDateTime.getTime())
                                    / (1000 * 60 * 60 * 24));

                            if (diffInDays <= 7) {
                                Assert.assertTrue(true);
                            } else {
                                Assert.assertTrue(false);
                            }
                        } catch (Exception ignore) {
                        }

                    }
                }
            }
        }
    }

    public void checkUserIsEnabledOrDisabled(String reportUrl, String userName, boolean targetEnable, String userNameLoggedIn) throws IOException {

        URL url = new URL(reportUrl);
        // get all tables of the report
        List<Table> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (Table reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                String appNameColumnValue = reportTable.getRows().get(1).get(1).getText(false);
                String recordColumnValue = reportTable.getRows().get(1).get(2).getText(false);
                String userColumnValue = reportTable.getRows().get(1).get(userColumnIndex).getText(false);
                String attributeColumnValue = reportTable.getRows().get(1).get(5).getText(false);
                String currValueColumnValue = reportTable.getRows().get(1).get(6).getText(false);
                String preValueColumnValue = reportTable.getRows().get(1).get(7).getText(false);
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

                    Assert.assertTrue(userColumnValue.contains(userNameLoggedIn));
                    Assert.assertTrue(appNameColumnValue.contains("IDManagement"));
                    Assert.assertTrue(currValueColumnValue.contains(Boolean.toString(!targetEnable)));
                    Assert.assertTrue(preValueColumnValue.contains(Boolean.toString(targetEnable)));
                    Assert.assertTrue(recordColumnValue.contains(userName));
                    Assert.assertTrue(attributeColumnValue.contains("enabled"));
                }
            }
            break;
        }
    }
    
    public void checkModifiedUser(String reportUrl, String userName, String userNameLoggedIn,String [][] list) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<Table> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (Table reportTable : reportTables) {
        	for (int x=0;x<4;x++) {
                for(int y=0; y<list.length;y++) {
                	if((list[y][0].equals(reportTable.getRows().get(x+2).get(0).getText(false)))) {
                    	Assert.assertTrue(list[y][1].equals(reportTable.getRows().get(x+2).get(1).getText(false)));
                    	}
                 }
      		}
            break;
        }
    }

    /**
     * Check filed ids are not null in the report
     *
     * @param reportUrl Report url     *
     * @throws IOException
     */
    public void checkFiledIds(String reportUrl, String machineName, String batchID, String productId, String recipeName, String beforeComments, String afterComments, String startDate, String endDate,
                              String template) throws IOException {


        URL url = new URL(reportUrl);

        // get table from table title and check is not null and contains rows
        Table table = PdfTableExtractUtils.getTableFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE);
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows().size() > 1);

        // get field value and check is not null
        Assert.assertNotNull("No field with id " + machineName, PdfTableExtractUtils.getTableFieldValue(table, UNIT_OPERATION_NAME));
        Assert.assertEquals("Unexpected batch id value", batchID, PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));
        Assert.assertEquals("Unexpected productId id value ", productId, PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID));
        Assert.assertEquals("recipename id value ", recipeName, PdfTableExtractUtils.getTableFieldValue(table, RECIPE_NAME));
        String sdate = PdfTableExtractUtils.getTableFieldValue(table, START_DATE);
        Assert.assertTrue("No field with start date", PdfTableExtractUtils.getTableFieldValue(table, START_DATE).contains(startDate));
        Assert.assertTrue("No field with end date", PdfTableExtractUtils.getTableFieldValue(table, END_DATE).contains(endDate));
        Assert.assertEquals("No field with pre run comment", beforeComments, PdfTableExtractUtils.getTableFieldValue(table, PRE_RUN_COMMENT));
        Assert.assertEquals("post run comment value", afterComments, PdfTableExtractUtils.getTableFieldValue(table, POST_RUN_COMMENT));
        Assert.assertEquals("run status value", "Completed", PdfTableExtractUtils.getTableFieldValue(table, RUN_STATUS));
        Assert.assertEquals("tempalte name value", template, PdfTableExtractUtils.getTableFieldValue(table, TEMPLATE_NAME));

    }

    /**
     * Check Pre run columns present in the report
     *
     * @param reportUrl Report url     *
     * @throws IOException
     */
    public void checkPreRunColumnsInReport(String reportUrl) throws IOException {
        URL url = new URL(reportUrl);
        Table table = PdfTableExtractUtils.getTableFromTableTitle(url.openStream(), PRE_RUN_DATE_TITLE);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_DESCRIPTION) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, SETPOINT) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EGU) == 3);
        Assert.assertTrue(table.getRowCount() > 0);
    }

    /**
     * Check Pre run columns present in the report
     *
     * @param reportUrl Report url     *
     * @throws IOException
     */
    public void checkRecipeColumnsInReport(String reportUrl) throws IOException {
        URL url = new URL(reportUrl);
        Table table = PdfTableExtractUtils.getTableFromTableTitle(url.openStream(), RECIPE_STEPS_SUMMARY);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_START_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_NUMBER) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, PHASE_NUMBER) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, ACTION_DESCRIPTION) == 3);
        System.out.println("table row count" + table.getRowCount());
        Assert.assertTrue(table.getRowCount() > 0);
    }
}
