package dataobjects;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import technology.tabula.Table;
import utils.PdfTableExtractUtils;
import utils.TimezoneUtils;

public class Report {

    private static final String REPORT_DATE_FORMAT = "dd/MMM/yyyy HH:mm:ss";

    private final String USER_COLUMN_FORMAT = "[aA1-zZ9]+\\([aA1-zZ9\\-]+(\\s[aA1-zZ9\\-]+)*\\)";
    private final String USER_COLUMN_NAME = "User";
    private final String INTERNAL_USER = "OMIUser";
    private final String EVENT_TABLE_HEADER = "Event Time|Event Description|Old Value|New Value";
    private final String REPORT_SUMMARY_TITLE = "Report Summary";
    private final String RUN_ID_FIELD_CONSOLIDATED = "RunID";
    private final String RUN_ID_FIELD = "RunId";
    private final String BATCH_ID = "Batch ID";
    private final String PRODUCT_ID_SUMMARY = "Product ID";
    private final String PRODUCT_ID = "ProductID";
    private final String RUNS = "Runs";
    private final String INSTRUMENT_SUMMARY = "Instrument Summary";
    private final String UNIT_OPERATION_NAME = "Unit Operation Name";
    private final String RECIPE_FILE_NAME = "Recipe File Name";
    private final String START_TIME = "Start Time";
    private final String END_TIME = "End Time";
    private final String RUN_STATUS = "Run Status";
    private final String CONSOLIDATED_ALARMS = "Consolidated Alarms";
    private final String EVENT_TIME = "Event Time";
    private final String MACHINE_NAME = "Machine Name";
    private final String INTOUCH_TYPE = "InTouch Type";
    private final String COMMENT = "Comment";
    private final String ON_UNIT_OPERATION_USERS = "On Unit Operation Users";
    private final String USER_NAME = "User Name";
    private final String FIRST_INSTANCE = "First Instance";
    private final String LAST_INSTANCE = "Last Instance";
    private final String RECIPE_NAME = "Recipe Name";
    private final String PRE_RUN_COMMENT = "Pre-run Comment";
    private final String POST_RUN_COMMENT = "Post-run Comment";
    private final String TEMPLATE_NAME = "Template Name";
    private final String AUDIT_TRAIL = "Audit Trail";
    private final String APPLICATION_NAME = "Application Name";
    private final String RECORD = "Record";
    private final String ATTRIBUTE = "Attribute";
    private final String USER = "User";
    private final String CURRENT_VALUE = "Current Value";
    private final String PREVIOUS_VALUE = "Previous Value";
    private final String SYSTEM_ALARMS = "System Alarms";
    private final String NAME = "Name";
    private final String TYPE = "Type";
    private final String ALARM_TYPE = "Alarm Type";
    private final String STEP_START_TIME = "Step Start Time";
    private final String STEP_NUMBER = "Step Number";
    private final String PHASE_NUMBER = "Phase Number";
    private final String ACTION_DESCRIPTION = "Action Description";
    private final String PROCESS_ALARMS = "Process Alarms";
    private final String CATEGORY = "Category";
    private final String RECIPE_STEPS_SUMMARY = "Recipe Steps Summary";
    private final String START_DATE = "Start Date";
    private final String END_DATE = "End Date";
    private final String PRE_RUN_DATE_TITLE = "Pre Run Data";
    private final String EVENT_DESCRIPTION = "Event Description";
    private final String SETPOINT = "Setpoint";
    private final String EGU = "EGU";
	private final String EVENT_COLUMN_NAME = "Event Time";
	private final String AUDIT_TABLE_HEADER = "Event Time|Application Name|Record|User|Comment|Attribute|Current Value|Previous Value";


    @Setter
    @Getter
    String name;

    @Setter
    @Getter
    String reportName;

    @Setter
    @Getter
    List<Recipe> recipes;


    public void checkRunId(String reportUrl, List<Recipe> recipes) throws IOException {

        URL url = new URL(reportUrl);

        // get table from table title and check is not null and contains rows
        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE).stream().findFirst().get();
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows().size() > 1);

        // get field value and check is not null
        String fieldValue = PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD);
        Assert.assertNotNull("No field with id " + RUN_ID_FIELD, fieldValue);
        recipes.forEach(recipe -> Assert.assertEquals("Unexpected run id value", recipe.getRunId(), fieldValue));
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
	
	public void checkModifiedUser(String reportUrl, String userName, String userNameLoggedIn,Map<String,String> list) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<Table> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (Table reportTable : reportTables) {
        	for(Map.Entry m:list.entrySet()){  
        		//check first row
        		if((reportTable.getRows().get(1).get(5).getText(false)).equals(m.getKey())) {
                	Assert.assertTrue(m.getValue().equals(reportTable.getRows().get(1).get(6).getText(false)));
            	}
        		//check from 2nd row till 5th row in PDF table
        		for (int rowno=2;rowno<6;rowno++) {
        			if((reportTable.getRows().get(rowno).get(0).getText(false)).equals(m.getKey())) {
                    	Assert.assertTrue(m.getValue().equals(reportTable.getRows().get(rowno).get(1).getText(false)));
                    }
                 }
        	}
            break;
        }
    }
    

    public void checkRecipeStatus(String reportUrl, String recipeName, String status, String userNameLoggedIn) throws IOException {

        URL url = new URL(reportUrl);
        // get all tables of the report
        List<Table> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (Table reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
            	for (int i=1;i<3;i++) {
            		String appNameColumnValue = reportTable.getRows().get(i).get(1).getText(false);
                    String recordColumnValue = reportTable.getRows().get(i).get(2).getText(false);
                    String userColumnValue = reportTable.getRows().get(i).get(userColumnIndex).getText(false);
                    String attributeColumnValue = reportTable.getRows().get(i).get(5).getText(false);
                    String currValueColumnValue = reportTable.getRows().get(i).get(6).getText(false);
                    String preValueColumnValue = reportTable.getRows().get(i).get(7).getText(false);
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
                        Assert.assertTrue(appNameColumnValue.contains("RecipeManagement"));
                        Assert.assertTrue(recordColumnValue.contains(recipeName));
                        Assert.assertTrue(attributeColumnValue.contains("status"));
                        if (i==1) {
                        	Assert.assertTrue(currValueColumnValue.contains(status));
                            Assert.assertTrue(List.of("In-Review", "Tech-Review").contains(preValueColumnValue));
                        }
                        else {
                        	Assert.assertTrue(preValueColumnValue.contains("Draft"));
                            Assert.assertTrue(List.of("IN-REVIEW", "TECH-REVIEW").contains(currValueColumnValue));
                        }
                    }
            	}
            }

            break;
        }
    }

    public void consolidatedValidateReportSummary(Table table, String startDate, String endDate, String batchId, String productId, String runID) throws Exception {
        boolean flag = false;

        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows().size() > 1);
        Assert.assertTrue("No field with start date", PdfTableExtractUtils.getTableFieldValue(table, START_DATE).contains(startDate));
        Assert.assertTrue("No field with end date", PdfTableExtractUtils.getTableFieldValue(table, END_DATE).contains(endDate));

        String actual = PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID);
        if (actual.contains(batchId)) {
            flag = true;
            Assert.assertTrue(flag);
        } else {
            Assert.assertTrue(flag);

        }

        String actual1 = PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID);
        if (actual1.contains(productId)) {

            flag = true;
            Assert.assertTrue(flag);
        } else {
            Assert.assertTrue(flag);

        }
        String actual2 = PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD);
        if (actual2.contains(runID)) {

            flag = true;
            Assert.assertTrue(flag);
        } else {
            Assert.assertTrue(flag);

        }
    }

    public void InstrumentSummary(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), INSTRUMENT_SUMMARY)
                .stream().findFirst().get();
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, UNIT_OPERATION_NAME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, RECIPE_FILE_NAME) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, START_TIME) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, END_TIME) == 3);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, RUN_STATUS) == 4);

        Assert.assertNotNull(table.getRowCount() > 1);

    }

    public void ConsolidatedAlarm(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), CONSOLIDATED_ALARMS)
                .stream().findFirst().get();
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, MACHINE_NAME) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, INTOUCH_TYPE) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, COMMENT) == 3);
        Assert.assertTrue(table.getRowCount() > 1);

    }

    public void OnUnitOperation(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), ON_UNIT_OPERATION_USERS)
                .stream().findFirst().get();
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, USER_NAME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, MACHINE_NAME) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, FIRST_INSTANCE) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, LAST_INSTANCE) == 3);
        Assert.assertNotNull(table.getRowCount() > 1);
    }

    public void AuditTrail(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), AUDIT_TRAIL)
                .stream().findFirst().get();
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, APPLICATION_NAME) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, RECORD) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, COMMENT) == 3);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, ATTRIBUTE) == 4);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, CURRENT_VALUE) == 5);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, PREVIOUS_VALUE) == 5);
        Assert.assertNotNull(table.getRowCount() > 1);
    }

    public void processAlarm(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), PROCESS_ALARMS)
                .stream().findFirst().get();
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, TYPE) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, NAME) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, CATEGORY) == 3);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, USER) == 4);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, ALARM_TYPE) == 5);
    }

    public void systemAlarm(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), SYSTEM_ALARMS)
                .stream().findFirst().get();
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, NAME) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, TYPE) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, COMMENT) == 3);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, USER) == 4);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, ALARM_TYPE) == 5);
    }

    public void recipeStepsSummary(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), RECIPE_STEPS_SUMMARY)
                .stream().findFirst().get();
        //table
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_START_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_NUMBER) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, PHASE_NUMBER) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, ACTION_DESCRIPTION) == 3);
    }

    public void validateRunSummary(Table table, Recipe recipe) {
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows().size() > 1);

        //  get field value and check is not null
        Assert.assertNotNull("No field with id " + recipe.getMachineName(), PdfTableExtractUtils.getTableFieldValue(table, UNIT_OPERATION_NAME));
        Assert.assertNotNull("No field with id " + BATCH_ID, PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));
        Assert.assertEquals("Unexpected batch id value", recipe.batchId, PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));

        Assert.assertEquals("Unexpected productId id value ", recipe.productId, PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID));
        Assert.assertEquals("No field with id ", recipe.runId, PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD_CONSOLIDATED));
        Assert.assertEquals("recipename id value ", recipe.recipeName, PdfTableExtractUtils.getTableFieldValue(table, RECIPE_NAME));

        TimezoneUtils.compareDateFromLocalToDistantServer("Start date not expected", recipe.startDate,
                PdfTableExtractUtils.getTableFieldValue(table, START_DATE), REPORT_DATE_FORMAT);

        TimezoneUtils.compareDateFromLocalToDistantServer("End date not expected", recipe.endDate,
                PdfTableExtractUtils.getTableFieldValue(table, END_DATE), REPORT_DATE_FORMAT);

        Assert.assertEquals("No field with pre run comment", recipe.beforeComments, PdfTableExtractUtils.getTableFieldValue(table, PRE_RUN_COMMENT));

        Assert.assertEquals("post run comment value", recipe.afterComments, PdfTableExtractUtils.getTableFieldValue(table, POST_RUN_COMMENT));
        Assert.assertEquals("run status value", recipe.status, PdfTableExtractUtils.getTableFieldValue(table, RUN_STATUS));
    }


    public void validateConsolidateRunSummary(String reportPdfUrl, List<Recipe> recipes) throws IOException {
        URL url = new URL(reportPdfUrl);
        List<Table> runSummaryTables = PdfTableExtractUtils.getTablesFromTableTitle((url.openStream()), REPORT_SUMMARY_TITLE);

        Assert.assertEquals(recipes.size() + 1, runSummaryTables.size());

        var consolidatedReportSummary = runSummaryTables.get(0);

        var startDateFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, START_DATE);
        TimezoneUtils.compareDateFromLocalToDistantServer("Start date not expected",
                recipes.stream().findFirst().get().startDate, startDateFromReport, REPORT_DATE_FORMAT);

        var endDateFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, END_DATE);
        TimezoneUtils.compareDateFromLocalToDistantServer("End date not expected",
                recipes.get(recipes.size()-1).endDate, endDateFromReport, REPORT_DATE_FORMAT);

        var productIdsFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, PRODUCT_ID_SUMMARY);
        recipes.forEach(recipe -> {
            Assert.assertTrue(productIdsFromReport.contains(recipe.productId));
        });

        var batchIdsFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, BATCH_ID);
        recipes.forEach(recipe -> {
            Assert.assertTrue(batchIdsFromReport.contains(recipe.batchId));
        });

        //TODO tabula not able to detect RUNS row
        //var runsFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, RUNS);
        //recipes.forEach(recipe -> {
        //    Assert.assertTrue(runsFromReport.contains(recipe.runId));
        //});

        for (int i = 0; i < runSummaryTables.size() - 1; i++) {
            var recipe = recipes.get(recipes.size() - i - 1);
            var runSummaryTable = runSummaryTables.get(i + 1);

            validateRunSummary(runSummaryTable, recipe);

            //TODO validate audit trail
            //TODO validate steps

        }
    }

    /**
     * Check filed ids are not null in the report
     *
     * @param reportUrl Report url     *
     * @throws IOException
     */
    public void checkFiledIds(String reportUrl, List<Recipe> recipes, String template) throws IOException {

        URL url = new URL(reportUrl);

        // get table from table title and check is not null and contains rows
        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE).stream().findFirst().get();
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows().size() > 1);

        for (var recipe : recipes) {
            // get field value and check is not null
            Assert.assertNotNull("No field with id " + recipe.getMachineName(), PdfTableExtractUtils.getTableFieldValue(table, UNIT_OPERATION_NAME));
            Assert.assertEquals("Unexpected batch id value", recipe.getBatchId(), PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));
            Assert.assertEquals("Unexpected productId id value ", recipe.getProductId(), PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID));
            Assert.assertEquals("recipename id value ", recipe.getRecipeName(), PdfTableExtractUtils.getTableFieldValue(table, RECIPE_NAME));
            String sdate = PdfTableExtractUtils.getTableFieldValue(table, START_DATE);
            Assert.assertTrue("No field with start date", PdfTableExtractUtils.getTableFieldValue(table, START_DATE).contains(recipe.getStartDate()));
            Assert.assertTrue("No field with end date", PdfTableExtractUtils.getTableFieldValue(table, END_DATE).contains(recipe.getEndDate()));
            Assert.assertEquals("No field with pre run comment", recipe.getBeforeComments(), PdfTableExtractUtils.getTableFieldValue(table, PRE_RUN_COMMENT));
            Assert.assertEquals("post run comment value", recipe.getAfterComments(), PdfTableExtractUtils.getTableFieldValue(table, POST_RUN_COMMENT));
            Assert.assertEquals("run status value", recipe.getStatus(), PdfTableExtractUtils.getTableFieldValue(table, RUN_STATUS));
            Assert.assertEquals("template name value", template, PdfTableExtractUtils.getTableFieldValue(table, TEMPLATE_NAME));
        }
    }

    /**
     * Check Pre run columns present in the report
     *
     * @param reportUrl Report url     *
     * @throws IOException
     */
    public void checkPreRunColumnsInReport(String reportUrl) throws IOException {
        URL url = new URL(reportUrl);
        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), PRE_RUN_DATE_TITLE).stream().findFirst().get();
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
        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), RECIPE_STEPS_SUMMARY).stream().findFirst().get();
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_START_TIME) == 0);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_NUMBER) == 1);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, PHASE_NUMBER) == 2);
        Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, ACTION_DESCRIPTION) == 3);
        System.out.println("table row count" + table.getRowCount());
        Assert.assertTrue(table.getRowCount() > 0);
    }
}
