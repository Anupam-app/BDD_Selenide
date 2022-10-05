package dataobjects;

import java.io.IOException;
import java.net.URL;
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
    private final String RUN_ID_FIELD = "RunID";
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
    private final String PRE_RUN_DATE_TITLE="Pre Run Data";
    private final String EVENT_DESCRIPTION="Event Description";
    private final String SETPOINT="Setpoint";
    private final String EGU="EGU";

    @Setter
    @Getter
    String name;
	
    @Setter
    @Getter
    String reportName;

    @Setter
    @Getter
    List<Recipe> recipes;


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
        Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE).stream().findFirst().get();
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
     * Check user information
     * - Expected format : UserLogin(Firstname Lastname)
     * - No internal user like OMIUser
     *
     * @param reportUrl Report url
     * @throws IOException
     */
    public void checkUserInformation(String reportUrl) throws IOException {

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
                    }
                }
            }
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
        Assert.assertEquals("No field with id ", recipe.runId, PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD));
        Assert.assertEquals("recipename id value ", recipe.recipeName, PdfTableExtractUtils.getTableFieldValue(table, RECIPE_NAME));

        Assert.assertEquals("No field with start date", recipe.startDate, PdfTableExtractUtils.getTableFieldValue(table, START_DATE));
        Assert.assertEquals("No field with end date", recipe.endDate, PdfTableExtractUtils.getTableFieldValue(table, END_DATE));
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
        Assert.assertEquals(recipes.stream().findFirst().get().startDate, startDateFromReport);

        var endDateFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, END_DATE);
        Assert.assertEquals(recipes.get(recipes.size() - 1).endDate, endDateFromReport);

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

        for (int i = 0; i < runSummaryTables.size()-1; i++) {
            var recipe = recipes.get(recipes.size() - i - 1);
            var runSummaryTable = runSummaryTables.get(i + 1);

            validateRunSummary(runSummaryTable, recipe);

            //TODO validate audit trail
            //TODO validate steps

        }
	}
    
    /**
     * Check filed ids are not null in the report
     * @param reportUrl Report url     * 
     * @throws IOException
     */
    public void checkFiledIds(String reportUrl, String machineName,String batchID, String productId,String recipeName, String beforeComments,String afterComments,String startDate,String endDate,
    		String template) throws IOException {


    	URL url = new URL(reportUrl);

    	// get table from table title and check is not null and contains rows
    	Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE).stream().findFirst().get();
    	Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
    	Assert.assertTrue("Table contains no data", table.getRows().size() > 1);

    	// get field value and check is not null
    	Assert.assertNotNull("No field with id " + machineName, PdfTableExtractUtils.getTableFieldValue(table, UNIT_OPERATION_NAME));    	
    	Assert.assertEquals("Unexpected batch id value", batchID, PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));
    	Assert.assertEquals("Unexpected productId id value " , productId, PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID));
    	Assert.assertEquals("recipename id value " , recipeName, PdfTableExtractUtils.getTableFieldValue(table, RECIPE_NAME));
    	String sdate =PdfTableExtractUtils.getTableFieldValue(table, START_DATE);
    	Assert.assertTrue("No field with start date" , PdfTableExtractUtils.getTableFieldValue(table, START_DATE).contains(startDate));        
    	Assert.assertTrue("No field with end date" , PdfTableExtractUtils.getTableFieldValue(table, END_DATE).contains(endDate));
    	Assert.assertEquals("No field with pre run comment", beforeComments, PdfTableExtractUtils.getTableFieldValue(table, PRE_RUN_COMMENT));
    	Assert.assertEquals("post run comment value" , afterComments, PdfTableExtractUtils.getTableFieldValue(table, POST_RUN_COMMENT));
    	Assert.assertEquals("run status value" , "Completed", PdfTableExtractUtils.getTableFieldValue(table, RUN_STATUS));
    	Assert.assertEquals("tempalte name value" , template, PdfTableExtractUtils.getTableFieldValue(table, TEMPLATE_NAME));

    }
    
    /**
     * Check Pre run columns present in the report
     * @param reportUrl Report url     * 
     * @throws IOException
     */
    public void checkPreRunColumnsInReport(String reportUrl) throws IOException {
    	 URL url = new URL(reportUrl);
         Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), PRE_RUN_DATE_TITLE).stream().findFirst().get();
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME)==0);
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EVENT_DESCRIPTION)==1);
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, SETPOINT)==2);
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, EGU)==3);
         Assert.assertTrue(table.getRowCount()>0);
    }
    
    /**
     * Check Pre run columns present in the report
     * @param reportUrl Report url     * 
     * @throws IOException
     */
    public void checkRecipeColumnsInReport(String reportUrl) throws IOException {
    	 URL url = new URL(reportUrl);
         Table table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), RECIPE_STEPS_SUMMARY).stream().findFirst().get();
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_START_TIME)==0);
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, STEP_NUMBER)==1);
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, PHASE_NUMBER)==2);
         Assert.assertTrue(PdfTableExtractUtils.getColumnIndex(table, ACTION_DESCRIPTION)==3);
         System.out.println("table row count"+table.getRowCount());
         Assert.assertTrue(table.getRowCount()>0);
    }
}
