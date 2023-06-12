package dataobjects;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import utils.PdfTableExtractUtils;
import utils.TimezoneUtils;
import utils.pdf.PdfTable;

public class Report {

    public static final String REPORT_DATE_FORMAT = "dd/MMM/yyyy HH:mm:ss";
    public static final String RECIPE_DATE_FORMAT = "MMM d, yyyy, h:mm:ss a";
    public static final String RECIPE_DATE_POST_RUN_FORMAT = "MMM d, yyyy h:mm:ss a";

    private final String USER_COLUMN_FORMAT = "[aA1-zZ9]+\\([aA1-zZ9\\-]+(\\s[aA1-zZ9\\-]+)*\\)";
    private final String USER_COLUMN_FORMAT_OT = "^[a-z0-9]*$";
    private final String USER_COLUMN_FORMAT_NON_ADMIN_USER = "[aA-zZ]+\\s+\\([aA-zZ\\-]+([aA-zZ\\-]+)*\\)";
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
    private final String AUDIT_TABLE_HEADER =
            "Event Time|Application Name|Record|User|Comment|Attribute|Current Value|Previous Value";


    @Setter
    @Getter
    String name;

    @Setter
    @Getter
    String reportName;

    @Setter
    @Getter
    int rowCount;

    @Setter
    @Getter
    String startDate;

    @Setter
    @Getter
    String endDate;

    @Setter
    @Getter
    List<Recipe> recipes;

    public void checkRunId(String reportUrl, List<Recipe> recipes) throws IOException {

        URL url = new URL(reportUrl);

        // get table from table title and check is not null and contains rows
        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE)
                .stream()
                .findFirst()
                .get();
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows()
                .size() > 1);

        // get field value and check is not null
        String fieldValue = PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD);
        Assert.assertNotNull("No field with id " + RUN_ID_FIELD, fieldValue);
        recipes.forEach(recipe -> Assert.assertEquals("Unexpected run id value", recipe.getRunId(), fieldValue));
    }

    /**
     * Check event table in the report
     *
     * @param reportUrl Report url
     */
    public void checkEventTable(String reportUrl) throws IOException {

        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTableFromTableHeader(url.openStream(), EVENT_TABLE_HEADER);
        if (table != null) {
            Assert.assertTrue("Table must contains at least one row in the body", table.getRows()
                    .size() > 1);
        }
    }

    /**
     * Check audit table in the report
     *
     * @param reportUrl Report url
     */
    public void checkAuditTable(String reportUrl) throws Exception {

        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTableFromTableHeader(url.openStream(), AUDIT_TABLE_HEADER);

        if (table != null) {
            Assert.assertTrue("Table must contains at least one row in the body", table.getRows()
                    .size() > 1);
        }
    }

    /**
     * Check user information - Expected format : UserLogin(Firstname Lastname) - No internal user like OMIUser
     *
     * @param reportUrl Report url
     */

    public void checkUserInformation(String reportUrl, String user) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // extract only user column data
                PdfTable columnDataTable = PdfTableExtractUtils.extractColumnData(reportTable, userColumnIndex);

                if (columnDataTable != null) {

                    // start from 1 to skip the header row
                    for (int i = 1; i < columnDataTable.getRowCount(); i++) {

                        String userColumnValue = columnDataTable.getRows()
                                .get(i)
                                .get(0)
                                .getText(false);

                        if (!StringUtils.isEmpty(userColumnValue)) {

                            // check user is not an internal user
                            if (StringUtils.containsIgnoreCase(StringUtils.trim(userColumnValue), INTERNAL_USER)) {
                                Assert.fail(String.format("Internal user in the report : %s", userColumnValue));
                            }

                            // check user format
                            Assert.assertTrue(String.format(
                                    "User format error. Value : %s. Expected pattern : UserLogin(Firstname Lastname)",
                                    userColumnValue), userNameValidation(userColumnValue));
                        }
                    }
                }
            }
        }
    }

    // temporary solution until BIOFOUND-29184 is implemented
    public boolean userNameValidation(String userValue) {
        return userValue.matches(USER_COLUMN_FORMAT) || userValue.matches(USER_COLUMN_FORMAT_OT)
                || userValue.matches(USER_COLUMN_FORMAT_NON_ADMIN_USER);
    }

    /**
     * Check Event Time information - No internal user like OMIUser
     *
     * @param reportUrl Report url
     */

    public void checkEventTimeInformation(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int eventColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, EVENT_COLUMN_NAME);
            if (eventColumnIndex == 0) {
                // start from 1 to skip the header row

                for (int i = 1; i < reportTable.getRowCount(); i++) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
                    String eventColumnValue1 = reportTable.getRows()
                            .get(i)
                            .get(eventColumnIndex)
                            .getText(false);
                    if (!StringUtils.isEmpty(eventColumnValue1)) {
                        // check date range within last 7 days
                        try {
                            String eventColumnValue = eventColumnValue1.substring(0, 11);
                            Date currentdate = new Date();
                            Date eventDateTime = formatter.parse(eventColumnValue);
                            int diffInDays =
                                    (int) ((currentdate.getTime() - eventDateTime.getTime()) / (1000 * 60 * 60 * 24));

                            Assert.assertTrue(diffInDays <= 7);
                        } catch (Exception ignore) {
                        }

                    }
                }
            }
        }
    }

    public void checkUserIsEnabledOrDisabled(String reportUrl, String userName, boolean targetEnable,
            String userNameLoggedIn) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                String appNameColumnValue = reportTable.getRows()
                        .get(1)
                        .get(1)
                        .getText(false);
                String recordColumnValue = reportTable.getRows()
                        .get(1)
                        .get(2)
                        .getText(false);
                String userColumnValue = reportTable.getRows()
                        .get(1)
                        .get(userColumnIndex)
                        .getText(false);
                String attributeColumnValue = reportTable.getRows()
                        .get(1)
                        .get(5)
                        .getText(false);
                String currValueColumnValue = reportTable.getRows()
                        .get(1)
                        .get(6)
                        .getText(false);
                String preValueColumnValue = reportTable.getRows()
                        .get(1)
                        .get(7)
                        .getText(false);
                if (!StringUtils.isEmpty(userColumnValue)) {
                    // check user is not an internal user
                    if (StringUtils.containsIgnoreCase(StringUtils.trim(userColumnValue), INTERNAL_USER)) {
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

    public void checkModifiedUser(String reportUrl, String userName, String userNameLoggedIn, Map<String, String> list)
            throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            for (Map.Entry m : list.entrySet()) {
                // check first row
                if ((reportTable.getRows()
                        .get(1)
                        .get(5)
                        .getText(false)).equals(m.getKey())) {
                    Assert.assertEquals(m.getValue(), reportTable.getRows()
                            .get(1)
                            .get(6)
                            .getText(false));
                }
                // check from 2nd row till 5th row in PDF table
                for (int rowNo = 2; rowNo < 6; rowNo++) {
                    if ((reportTable.getRows()
                            .get(rowNo)
                            .get(0)
                            .getText(false)).equals(m.getKey())) {
                        Assert.assertEquals(m.getValue(), reportTable.getRows()
                                .get(rowNo)
                                .get(1)
                                .getText(false));
                    }
                }
            }
            break;
        }
    }

    public void checkAddedRole(String reportUrl, String roleName, String userNameLoggedIn, Set<String> permissionList)
            throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                for (int i = 1; i < 3; i++) {
                    String appNameColumnValue = reportTable.getRows()
                            .get(i)
                            .get(1)
                            .getText(false);
                    String recordColumnValue = reportTable.getRows()
                            .get(i)
                            .get(2)
                            .getText(false);
                    String userColumnValue = reportTable.getRows()
                            .get(i)
                            .get(userColumnIndex)
                            .getText(false);
                    String attributeColumnValue = reportTable.getRows()
                            .get(1)
                            .get(5)
                            .getText(false);
                    String currValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(6)
                            .getText(false);
                    if (i == 1) {
                        if (attributeColumnValue.equalsIgnoreCase("permissions")) {
                            String[] permissions = permissionList.toArray(new String[0]);
                            Assert.assertTrue(userColumnValue.contains(userNameLoggedIn));
                            Assert.assertTrue(appNameColumnValue.contains("IDManagement"));
                            Assert.assertTrue(recordColumnValue.contains(roleName));
                            for (var permission : permissions) {
                                Assert.assertTrue((currValueColumnValue.replaceAll("\\s", ""))
                                        .contains(permission.replaceAll("\\s", "")));
                            }
                        } else if (attributeColumnValue.equalsIgnoreCase("roleName")) {
                            Assert.assertTrue(currValueColumnValue.contains(roleName));
                        }
                    }
                    if (i == 2) {
                        if (reportTable.getRows()
                                .get(2)
                                .get(5)
                                .getText(false)
                                .equalsIgnoreCase("permissions")) {
                            String[] permissions = permissionList.toArray(new String[0]);
                            for (var permission : permissions) {
                                Assert.assertTrue((reportTable.getRows()
                                        .get(2)
                                        .get(6)
                                        .getText(false)
                                        .replaceAll("\\s", "")).contains(permission.replaceAll("\\s", "")));
                            }
                        } else if (reportTable.getRows()
                                .get(2)
                                .get(5)
                                .getText(false)
                                .equalsIgnoreCase("roleName")) {
                            Assert.assertTrue(reportTable.getRows()
                                    .get(2)
                                    .get(6)
                                    .getText(false)
                                    .contains(roleName));
                        }
                    }
                }
            }
            break;
        }
    }

    public void checkRecipeStatus(String reportUrl, String recipeName, String status, String userNameLoggedIn)
            throws IOException {

        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                for (int i = 1; i < 3; i++) {
                    String appNameColumnValue = reportTable.getRows()
                            .get(i)
                            .get(1)
                            .getText(false);
                    String recordColumnValue = reportTable.getRows()
                            .get(i)
                            .get(2)
                            .getText(false);
                    String userColumnValue = reportTable.getRows()
                            .get(i)
                            .get(userColumnIndex)
                            .getText(false);
                    String attributeColumnValue = reportTable.getRows()
                            .get(i)
                            .get(5)
                            .getText(false);
                    String currValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(6)
                            .getText(false);
                    String preValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(7)
                            .getText(false);
                    if (!StringUtils.isEmpty(userColumnValue)) {
                        // check user is not an internal user
                        if (StringUtils.containsIgnoreCase(StringUtils.trim(userColumnValue), INTERNAL_USER)) {
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
                        if (i == 1) {
                            Assert.assertTrue(currValueColumnValue.contains(status));
                            Assert.assertTrue(List.of("IN-REVIEW", "TECH-REVIEW")
                                    .contains(preValueColumnValue));
                        } else {
                            Assert.assertTrue(preValueColumnValue.contains("Draft"));
                            Assert.assertTrue(List.of("IN-REVIEW", "TECH-REVIEW")
                                    .contains(currValueColumnValue));
                        }
                    }
                }
            }

            break;
        }
    }

    public void consolidatedValidateReportSummary(PdfTable table, String startDate, String endDate, String batchId,
            String productId, String runID) {
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows()
                .size() > 1);
        Assert.assertTrue("No field with start date", PdfTableExtractUtils.getTableFieldValue(table, START_DATE)
                .contains(startDate));
        Assert.assertTrue("No field with end date", PdfTableExtractUtils.getTableFieldValue(table, END_DATE)
                .contains(endDate));

        String actual = PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID);
        Assert.assertTrue(actual.contains(batchId));

        String actual1 = PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID);
        Assert.assertTrue(actual1.contains(productId));
        String actual2 = PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD);
        Assert.assertTrue(actual2.contains(runID));
    }

    public void InstrumentSummary(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);
        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), INSTRUMENT_SUMMARY)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, UNIT_OPERATION_NAME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, RECIPE_FILE_NAME));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, START_TIME));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, END_TIME));
        Assert.assertEquals(4, PdfTableExtractUtils.getColumnIndex(table, RUN_STATUS));
        Assert.assertTrue(table.getRowCount() > 1);
    }

    public void checkSignaturesTable(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), "Signatures")
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, "User name"));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, "Signature"));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, "Date"));

        Assert.assertTrue(table.getRowCount() > 1);
        Assert.assertEquals("testadmin", table.getRows()
                .get(1)
                .get(PdfTableExtractUtils.getColumnIndex(table, "User name"))
                .getText(false));
        Assert.assertEquals("Test Admin", table.getRows()
                .get(1)
                .get(PdfTableExtractUtils.getColumnIndex(table, "Signature"))
                .getText(false));
        Assert.assertTrue(table.getRows()
                .get(1)
                .get(PdfTableExtractUtils.getColumnIndex(table, "Date"))
                .getText(false)
                .matches(("([0-9]{2})/([aA-zZ]{3})/([0-9]{4}) ([0-9]{2}):([0-9]{2}):([0-9]{2})")));

    }

    public void ConsolidatedAlarm(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), CONSOLIDATED_ALARMS)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, MACHINE_NAME));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, INTOUCH_TYPE));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, COMMENT));
        Assert.assertTrue(table.getRowCount() > 1);

    }

    public void OnUnitOperation(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), ON_UNIT_OPERATION_USERS)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, USER_NAME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, MACHINE_NAME));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, FIRST_INSTANCE));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, LAST_INSTANCE));
        Assert.assertTrue(table.getRowCount() > 1);
    }

    public void AuditTrail(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), AUDIT_TRAIL)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, APPLICATION_NAME));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, RECORD));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, COMMENT));
        Assert.assertEquals(4, PdfTableExtractUtils.getColumnIndex(table, ATTRIBUTE));
        Assert.assertEquals(5, PdfTableExtractUtils.getColumnIndex(table, CURRENT_VALUE));
        Assert.assertEquals(5, PdfTableExtractUtils.getColumnIndex(table, PREVIOUS_VALUE));
        Assert.assertTrue(table.getRowCount() > 1);
    }

    public void processAlarm(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), PROCESS_ALARMS)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, TYPE));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, NAME));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, CATEGORY));
        Assert.assertEquals(4, PdfTableExtractUtils.getColumnIndex(table, USER));
        Assert.assertEquals(5, PdfTableExtractUtils.getColumnIndex(table, ALARM_TYPE));
    }

    public void systemAlarm(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), SYSTEM_ALARMS)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, NAME));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, TYPE));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, COMMENT));
        Assert.assertEquals(4, PdfTableExtractUtils.getColumnIndex(table, USER));
        Assert.assertEquals(5, PdfTableExtractUtils.getColumnIndex(table, ALARM_TYPE));
    }

    public void recipeStepsSummary(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);

        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), RECIPE_STEPS_SUMMARY)
                .stream()
                .findFirst()
                .get();
        // table
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, STEP_START_TIME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, STEP_NUMBER));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, PHASE_NUMBER));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, ACTION_DESCRIPTION));
    }

    public void validateRunSummary(PdfTable table, Recipe recipe) {
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows()
                .size() > 1);

        // get field value and check is not null
        Assert.assertNotNull("No field with id " + recipe.getMachineName(),
                PdfTableExtractUtils.getTableFieldValue(table, UNIT_OPERATION_NAME));
        Assert.assertNotNull("No field with id " + BATCH_ID, PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));
        Assert.assertEquals("Unexpected batch id value", recipe.batchId,
                PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));

        Assert.assertEquals("Unexpected productId id value ", recipe.productId,
                PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID));
        Assert.assertEquals("No field with id ", recipe.runId,
                PdfTableExtractUtils.getTableFieldValue(table, RUN_ID_FIELD_CONSOLIDATED));
        Assert.assertEquals("recipe name id value ", recipe.recipeName,
                PdfTableExtractUtils.getTableFieldValue(table, RECIPE_NAME));

        TimezoneUtils.compareDateFromLocalToDistantServer("Start date not expected", recipe.startDate,
                PdfTableExtractUtils.getTableFieldValue(table, START_DATE), REPORT_DATE_FORMAT);

        TimezoneUtils.compareDateFromLocalToDistantServer("End date not expected", recipe.endDate,
                PdfTableExtractUtils.getTableFieldValue(table, END_DATE), REPORT_DATE_FORMAT);

        Assert.assertEquals("No field with pre run comment", recipe.beforeComments,
                PdfTableExtractUtils.getTableFieldValue(table, PRE_RUN_COMMENT));

        Assert.assertEquals("post run comment value", recipe.afterComments,
                PdfTableExtractUtils.getTableFieldValue(table, POST_RUN_COMMENT));
        Assert.assertEquals("run status value", recipe.status,
                PdfTableExtractUtils.getTableFieldValue(table, RUN_STATUS));
    }

    public void validateConsolidateRunSummary(String reportPdfUrl, List<Recipe> recipes) throws IOException {
        URL url = new URL(reportPdfUrl);
        List<PdfTable> runSummaryTables =
                PdfTableExtractUtils.getTablesFromTableTitle((url.openStream()), REPORT_SUMMARY_TITLE);
        Assert.assertEquals(recipes.size() + 1, runSummaryTables.size());

        var consolidatedReportSummary = runSummaryTables.get(0);
        validateRecipeDate("Recipe start date", consolidatedReportSummary, START_DATE, recipes.stream()
                .findFirst()
                .get().startDate);
        validateRecipeDate("Recipe end date", consolidatedReportSummary, END_DATE,
                recipes.get(recipes.size() - 1).endDate);

        var productIdsFromReport =
                PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, PRODUCT_ID_SUMMARY);
        recipes.forEach(recipe -> {
            Assert.assertTrue(productIdsFromReport.contains(recipe.productId));
        });

        var batchIdsFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, BATCH_ID);
        recipes.forEach(recipe -> {
            Assert.assertTrue(batchIdsFromReport.contains(recipe.batchId));
        });

        // TODO tabula not able to detect RUNS row
        // var runsFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, RUNS);
        // recipes.forEach(recipe -> {
        // Assert.assertTrue(runsFromReport.contains(recipe.runId));
        // });

        for (int i = 0; i < runSummaryTables.size() - 1; i++) {
            var recipe = recipes.get(recipes.size() - i - 1);
            var runSummaryTable = runSummaryTables.get(i + 1);

            validateRunSummary(runSummaryTable, recipe);

            // TODO validate audit trail
            // TODO validate steps

        }
    }

    /**
     * Check filed ids are not null in the report
     *
     * @param reportUrl Report url *
     * @throws IOException
     */
    public void checkFiledIds(String reportUrl, List<Recipe> recipes, String template) throws IOException {

        URL url = new URL(reportUrl);

        // get table from table title and check is not null and contains rows
        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE)
                .stream()
                .findFirst()
                .get();
        Assert.assertNotNull("No table found for title " + REPORT_SUMMARY_TITLE, table);
        Assert.assertTrue("Table contains no data", table.getRows()
                .size() > 1);

        for (var recipe : recipes) {
            validateRecipeInReport(template, table, recipe);
        }
    }

    private void validateRecipeInReport(String template, PdfTable table, Recipe recipe) {
        // get field value and check is not null
        Assert.assertNotNull("No field with id " + recipe.getMachineName(),
                PdfTableExtractUtils.getTableFieldValue(table, UNIT_OPERATION_NAME));
        Assert.assertEquals("Unexpected batch id value", recipe.getBatchId(),
                PdfTableExtractUtils.getTableFieldValue(table, BATCH_ID));
        Assert.assertEquals("Unexpected productId id value ", recipe.getProductId(),
                PdfTableExtractUtils.getTableFieldValue(table, PRODUCT_ID));
        Assert.assertEquals("Recipe name id value ", recipe.getRecipeName(),
                PdfTableExtractUtils.getTableFieldValue(table, RECIPE_NAME));

        validateRecipeDate("Recipe start date", table, START_DATE, recipe.getStartDate());
        validateRecipeDate("Recipe end date", table, END_DATE, recipe.getEndDate());

        Assert.assertEquals("No field with pre run comment", recipe.getBeforeComments(),
                PdfTableExtractUtils.getTableFieldValue(table, PRE_RUN_COMMENT));
        Assert.assertEquals("Post run comment value", recipe.getAfterComments(),
                PdfTableExtractUtils.getTableFieldValue(table, POST_RUN_COMMENT));
        Assert.assertEquals("Run status value", recipe.getStatus(),
                PdfTableExtractUtils.getTableFieldValue(table, RUN_STATUS));
        Assert.assertEquals("Template name value", template,
                PdfTableExtractUtils.getTableFieldValue(table, TEMPLATE_NAME));
    }

    private void validateRecipeDate(String message, PdfTable table, String dateColumnFromReport, String recipeDate) {
        var recipeDateFromReport = PdfTableExtractUtils.getTableFieldValue(table, dateColumnFromReport);
        Assert.assertNotNull(message + " is null", recipeDateFromReport);

        DateTimeFormatter formatterRecipe = DateTimeFormatter.ofPattern(RECIPE_DATE_POST_RUN_FORMAT)
                .localizedBy(Locale.ENGLISH);
        LocalDateTime localRecipeDate = LocalDateTime.parse(recipeDate, formatterRecipe);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(REPORT_DATE_FORMAT)
                .localizedBy(Locale.ENGLISH);
        LocalDateTime serverRecipeDateFromReport = LocalDateTime.parse(recipeDateFromReport, formatter);

        TimezoneUtils.compareDateFromLocalToDistantServer(message + " and recipe date from report are not the same",
                localRecipeDate, serverRecipeDateFromReport);
    }

    /**
     * Check Pre run columns present in the report
     *
     * @param reportUrl Report url *
     * @throws IOException
     */
    public void checkPreRunColumnsInReport(String reportUrl) throws IOException {
        URL url = new URL(reportUrl);
        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), PRE_RUN_DATE_TITLE)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, EVENT_TIME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, EVENT_DESCRIPTION));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, SETPOINT));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, EGU));
        Assert.assertTrue(table.getRowCount() > 0);
    }

    /**
     * Check Pre run columns present in the report
     *
     * @param reportUrl Report url *
     * @throws IOException
     */
    public void checkRecipeColumnsInReport(String reportUrl) throws IOException {
        URL url = new URL(reportUrl);
        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), RECIPE_STEPS_SUMMARY)
                .stream()
                .findFirst()
                .get();
        Assert.assertEquals(0, PdfTableExtractUtils.getColumnIndex(table, STEP_START_TIME));
        Assert.assertEquals(1, PdfTableExtractUtils.getColumnIndex(table, STEP_NUMBER));
        Assert.assertEquals(2, PdfTableExtractUtils.getColumnIndex(table, PHASE_NUMBER));
        Assert.assertEquals(3, PdfTableExtractUtils.getColumnIndex(table, ACTION_DESCRIPTION));
        Assert.assertTrue(table.getRowCount() > 0);
    }

    public void checkDeletedRole(String reportUrl, String roleName, String userNameLoggedIn) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(userColumnIndex)
                        .getText(false)
                        .contains(userNameLoggedIn));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(1)
                        .getText(false)
                        .contains("IDManagement"));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(2)
                        .getText(false)
                        .contains(roleName));
                Assert.assertEquals((reportTable.getRows()
                        .get(1)
                        .get(4)
                        .getText(false)).replaceAll("\\s", ""),
                        (userNameLoggedIn + " has made the Role, " + roleName + " obsolete").replaceAll("\\s", ""));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(5)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(6)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(7)
                        .getText(false)
                        .isEmpty());
            }
            break;
        }
    }

    public void verifyConsolidateManualSummaryReport(String reportUrl) throws Exception {
        URL url = new URL(reportUrl);
        boolean table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), RECIPE_STEPS_SUMMARY)
                .stream()
                .findFirst()
                .isPresent();
        Assert.assertFalse("Recipe Step Summary table is found for Manual runs " + RECIPE_STEPS_SUMMARY, table);
        List<PdfTable> reportTables =
                PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), REPORT_SUMMARY_TITLE);
        for (PdfTable reportTable : reportTables) {
            Assert.assertNull("Template name value",
                    PdfTableExtractUtils.getTableFieldValue(reportTable, TEMPLATE_NAME));
        }

    }

    public void checkModifiedRolePermission(String reportUrl, String roleName, String userNameLoggedIn,
            Set<String> permissionList, Set<String> oldPermissionList) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                for (int i = 1; i < reportTable.getRows()
                        .size(); i++) {
                    String appNameColumnValue = reportTable.getRows()
                            .get(i)
                            .get(1)
                            .getText(false);
                    String recordColumnValue = reportTable.getRows()
                            .get(i)
                            .get(2)
                            .getText(false);
                    String userColumnValue = reportTable.getRows()
                            .get(i)
                            .get(userColumnIndex)
                            .getText(false);
                    String commentColumnValue = reportTable.getRows()
                            .get(i)
                            .get(4)
                            .getText(false);
                    String attributeColumnValue = reportTable.getRows()
                            .get(i)
                            .get(5)
                            .getText(false);
                    String currValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(6)
                            .getText(false);
                    String prevValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(7)
                            .getText(false);
                    String[] permissions = permissionList.toArray(new String[0]);
                    String[] oldPermissions = oldPermissionList.toArray(new String[0]);
                    if (attributeColumnValue.equalsIgnoreCase("permissions")) {
                        Assert.assertTrue(userColumnValue.contains(userNameLoggedIn));
                        Assert.assertTrue(appNameColumnValue.contains("IDManagement"));
                        Assert.assertEquals("Role-" + roleName, recordColumnValue);
                        Assert.assertEquals(userNameLoggedIn + " updated Role" + roleName, commentColumnValue);
                        for (var permission : permissions) {
                            Assert.assertTrue((currValueColumnValue.replaceAll("\\s", ""))
                                    .contains(permission.replaceAll("\\s", "")));
                        }
                        for (var oldPermission : oldPermissions) {
                            Assert.assertTrue((prevValueColumnValue.replaceAll("\\s", ""))
                                    .contains(oldPermission.replaceAll("\\s", "")));
                        }
                    }
                }
            }
            break;
        }
    }

    public void checkModifiedRoleName(String reportUrl, String roleName, String oldRoleName, String userNameLoggedIn)
            throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                for (int i = 1; i < reportTable.getRows()
                        .size(); i++) {
                    String appNameColumnValue = reportTable.getRows()
                            .get(i)
                            .get(1)
                            .getText(false);
                    String recordColumnValue = reportTable.getRows()
                            .get(i)
                            .get(2)
                            .getText(false);
                    String userColumnValue = reportTable.getRows()
                            .get(i)
                            .get(userColumnIndex)
                            .getText(false);
                    String commentColumnValue = reportTable.getRows()
                            .get(i)
                            .get(4)
                            .getText(false);
                    String attributeColumnValue = reportTable.getRows()
                            .get(i)
                            .get(5)
                            .getText(false);
                    String currValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(6)
                            .getText(false);
                    String prevValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(7)
                            .getText(false);
                    if (attributeColumnValue.equalsIgnoreCase("roleName")) {
                        Assert.assertTrue(userColumnValue.contains(userNameLoggedIn));
                        Assert.assertTrue(appNameColumnValue.contains("IDManagement"));
                        Assert.assertEquals("Role-" + roleName, recordColumnValue);
                        Assert.assertEquals(userNameLoggedIn + " updated Role" + oldRoleName, commentColumnValue);
                        Assert.assertTrue(currValueColumnValue.contains(roleName));
                        Assert.assertTrue(prevValueColumnValue.contains(oldRoleName));
                    }
                }
            }
            break;
        }
    }

    public void checkTemplateStatus(String reportUrl, String templateName, String status, String userNameLoggedIn)
            throws IOException {

        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                // start from 1 to skip the header row
                for (int i = 1; i < 3; i++) {
                    String appNameColumnValue = reportTable.getRows()
                            .get(i)
                            .get(1)
                            .getText(false);
                    String recordColumnValue = reportTable.getRows()
                            .get(i)
                            .get(2)
                            .getText(false);
                    String userColumnValue = reportTable.getRows()
                            .get(i)
                            .get(userColumnIndex)
                            .getText(false);
                    String commentColumnValue = reportTable.getRows()
                            .get(i)
                            .get(4)
                            .getText(false);
                    String attributeColumnValue = reportTable.getRows()
                            .get(i)
                            .get(5)
                            .getText(false);
                    String currValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(6)
                            .getText(false);
                    String preValueColumnValue = reportTable.getRows()
                            .get(i)
                            .get(7)
                            .getText(false);

                    if (!StringUtils.isEmpty(userColumnValue)) {
                        // check user is not an internal user
                        if (StringUtils.containsIgnoreCase(StringUtils.trim(userColumnValue), INTERNAL_USER)) {
                            Assert.fail(String.format("Internal user in the report : %s", userColumnValue));
                        }
                        // check user format
                        Assert.assertTrue(String.format(
                                "User format error. Value : %s. Expected pattern : UserLogin(Firstname Lastname)",
                                userColumnValue), userColumnValue.matches(USER_COLUMN_FORMAT));
                        Assert.assertTrue(userColumnValue.contains(userNameLoggedIn));
                        Assert.assertTrue(appNameColumnValue.contains("ReportManagement"));
                        Assert.assertTrue(recordColumnValue.contains(templateName));
                        Assert.assertTrue(attributeColumnValue.contains("status"));
                        if (i == 1) {
                            Assert.assertTrue(commentColumnValue
                                    .contains(userNameLoggedIn + " approved andsigned Report Template"));
                            Assert.assertTrue(currValueColumnValue.contains(status));
                            Assert.assertTrue(preValueColumnValue.contains("In Review"));
                        } else {
                            Assert.assertTrue(
                                    commentColumnValue.contains(userNameLoggedIn + " updated ReportTemplate"));
                            Assert.assertTrue(preValueColumnValue.contains("Draft"));
                            Assert.assertTrue(currValueColumnValue.contains("In Review"));
                        }
                    }
                }
            }
            break;
        }
    }

    public void checkCreatedTemplate(String reportUrl, String templateName, String status, String userNameLoggedIn)
            throws IOException {
        URL url = new URL(reportUrl);
        String attributeColumnValue = null;
        String currValueColumnValue = null;
        int flag = 0;
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            flag = flag + 1;
            if (flag == 1) {
                for (int i = 3; i < 9; i++) {
                    if (!(i == 8)) {
                        attributeColumnValue = reportTable.getRows()
                                .get(i)
                                .get(0)
                                .getText(false);
                        currValueColumnValue = reportTable.getRows()
                                .get(i)
                                .get(1)
                                .getText(false);
                    }
                }
            } else {
                for (int i = 1; i < 5; i++) {
                    if (!(i == 1)) {
                        attributeColumnValue = reportTable.getRows()
                                .get(i)
                                .get(4)
                                .getText(false);
                        currValueColumnValue = reportTable.getRows()
                                .get(i)
                                .get(5)
                                .getText(false);
                    } else {
                        String appNameColumnValue = reportTable.getRows()
                                .get(i)
                                .get(1)
                                .getText(false);
                        String recordColumnValue = reportTable.getRows()
                                .get(i)
                                .get(2)
                                .getText(false);
                        String userColumnValue = reportTable.getRows()
                                .get(i)
                                .get(3)
                                .getText(false);
                        String commentColumnValue = reportTable.getRows()
                                .get(i)
                                .get(4)
                                .getText(false);
                        attributeColumnValue = reportTable.getRows()
                                .get(i)
                                .get(5)
                                .getText(false);
                        currValueColumnValue = reportTable.getRows()
                                .get(i)
                                .get(6)
                                .getText(false);
                        Assert.assertTrue(userColumnValue.contains(userNameLoggedIn));
                        Assert.assertTrue(appNameColumnValue.contains("ReportManagement"));
                        Assert.assertTrue(recordColumnValue.contains(templateName));
                        Assert.assertTrue(commentColumnValue.contains("Bio4CAdmin created ReportTemplate"));
                    }
                }
                switch (attributeColumnValue) {
                    case "status":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase("Draft"));
                        break;
                    case "isDefault":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase("n"));
                        break;
                    case "templateType":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase("RunSummary"));
                        break;
                    case "name":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase(templateName));
                        break;
                    case "createdBy":
                    case "lastModifiedBy":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase(userNameLoggedIn));
                        break;
                    case "signatureC ount":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase("1"));
                        break;
                    case "deviceId":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase("IVI"));
                        break;
                    case "state":
                        Assert.assertTrue(currValueColumnValue.equalsIgnoreCase("true"));
                        break;
                }
            }
            if (flag > 1) {
                break;
            }
        }
    }

    public void verifyAuditReportForRoleDisableEnable(String reportUrl, String roleName, String userNameLoggedIn)
            throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                for (int i = 1; i < 3; i++) {
                    Assert.assertTrue(reportTable.getRows()
                            .get(i)
                            .get(userColumnIndex)
                            .getText(false)
                            .contains(userNameLoggedIn));
                    Assert.assertTrue(reportTable.getRows()
                            .get(i)
                            .get(1)
                            .getText(false)
                            .contains("IDManagement"));
                    Assert.assertTrue(reportTable.getRows()
                            .get(i)
                            .get(2)
                            .getText(false)
                            .contains(roleName));
                    Assert.assertTrue(reportTable.getRows()
                            .get(i)
                            .get(5)
                            .getText(false)
                            .isEmpty());
                    Assert.assertTrue(reportTable.getRows()
                            .get(i)
                            .get(6)
                            .getText(false)
                            .isEmpty());
                    Assert.assertTrue(reportTable.getRows()
                            .get(i)
                            .get(7)
                            .getText(false)
                            .isEmpty());
                    if (i == 1) {
                        Assert.assertEquals((reportTable.getRows()
                                .get(i)
                                .get(4)
                                .getText(false)).replaceAll("\\s", ""),
                                (userNameLoggedIn + " enabled role" + roleName).replaceAll("\\s", ""));
                    } else {
                        Assert.assertEquals((reportTable.getRows()
                                .get(i)
                                .get(4)
                                .getText(false)).replaceAll("\\s", ""),
                                (userNameLoggedIn + " disabled role" + roleName).replaceAll("\\s", ""));
                    }
                }
            }
            break;
        }
    }

    public void checkAddedUser(String reportUrl, String userName, String userNameLoggedIn, Map<String, String> list)
            throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {

            for (Map.Entry m : list.entrySet()) {
                for (int rowNo = 1; rowNo < 8; rowNo++) {
                    if ((reportTable.getRows()
                            .get(rowNo)
                            .get(0)
                            .getText(false)).equals(m.getKey())) {
                        Assert.assertEquals(m.getValue(), reportTable.getRows()
                                .get(rowNo)
                                .get(1)
                                .getText(false));
                    }
                }
            }
            break;
        }
    }

    public void verifyAuditReportForPasswordReset(String reportUrl, String userName, String userNameLoggedIn,
            String passwordAction) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(userColumnIndex)
                        .getText(false)
                        .contains(userNameLoggedIn));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(1)
                        .getText(false)
                        .contains("IDManagement"));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(2)
                        .getText(false)
                        .contains("User -" + userName));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(5)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(6)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(7)
                        .getText(false)
                        .isEmpty());
                if (passwordAction.equals("reset")) {
                    Assert.assertEquals((reportTable.getRows()
                            .get(1)
                            .get(4)
                            .getText(false)).replaceAll("\\s", ""),
                            (userNameLoggedIn + " reset password for User Account" + userName).replaceAll("\\s", ""));
                } else if (passwordAction.equals("temp")) {
                    Assert.assertEquals((reportTable.getRows()
                            .get(1)
                            .get(4)
                            .getText(false)).replaceAll("\\s", ""),
                            (userNameLoggedIn + " changed the account temporary password on first login")
                                    .replaceAll("\\s", ""));
                } else {
                    Assert.assertEquals((reportTable.getRows()
                            .get(1)
                            .get(4)
                            .getText(false)).replaceAll("\\s", ""),
                            (userNameLoggedIn + " changed the account password").replaceAll("\\s", ""));
                }
            }
            break;
        }
    }

    public void verifyAuditReportForRecipe(String reportUrl, String recipeName, String userNameLoggedIn,
            String recipeAction) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(userColumnIndex)
                        .getText(false)
                        .contains(userNameLoggedIn));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(1)
                        .getText(false)
                        .contains("RecipeManagement"));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(2)
                        .getText(false)
                        .contains("Recipe Name -" + recipeName));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(5)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(6)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(7)
                        .getText(false)
                        .isEmpty());
                if (recipeAction.equals("created")) {
                    Assert.assertEquals((reportTable.getRows()
                            .get(1)
                            .get(4)
                            .getText(false)).replaceAll("\\s", ""),
                            (userNameLoggedIn + " created a new recipe" + recipeName).replaceAll("\\s", ""));
                } else {
                    Assert.assertEquals((reportTable.getRows()
                            .get(1)
                            .get(4)
                            .getText(false)).replaceAll("\\s", ""),
                            (userNameLoggedIn + " edited recipe" + recipeName).replaceAll("\\s", ""));
                }
            }
            break;
        }
    }

    public void verifyAuditReportForBackUp(String reportUrl, String backUpName, String userNameLoggedIn,
            String backUpAction) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(userColumnIndex)
                        .getText(false)
                        .contains(userNameLoggedIn));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(1)
                        .getText(false)
                        .contains("BackupManagement"));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(5)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(6)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(7)
                        .getText(false)
                        .isEmpty());
                Assert.assertEquals((reportTable.getRows()
                        .get(1)
                        .get(4)
                        .getText(false)).replaceAll("\\s", ""),
                        (userNameLoggedIn + " triggered data backup" + "").replaceAll("\\s", ""));
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(2)
                        .getText(false)
                        .contains("Backup Job Id"));
            }
            break;
        }
    }

    public void verifyAuditReportForScheduleBackUp(String reportUrl, String backUpName, String userNameLoggedIn,
            String occurrence) throws IOException {
        URL url = new URL(reportUrl);
        // get all tables of the report
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, USER_COLUMN_NAME);
            if (userColumnIndex > 0) {
                for (int i = 1; i < 3; i++) {
                    Assert.assertTrue(reportTable.getRows()
                            .get(1)
                            .get(userColumnIndex)
                            .getText(false)
                            .contains(userNameLoggedIn));
                    Assert.assertTrue(reportTable.getRows()
                            .get(1)
                            .get(1)
                            .getText(false)
                            .contains("BackupManagement"));
                    Assert.assertTrue(reportTable.getRows()
                            .get(1)
                            .get(7)
                            .getText(false)
                            .isEmpty());
                    Assert.assertEquals((reportTable.getRows()
                            .get(1)
                            .get(2)
                            .getText(false)).replaceAll("\\s", ""),
                            (" Backup Schedule Name - " + backUpName).replaceAll("\\s", ""));
                }
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(5)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(1)
                        .get(6)
                        .getText(false)
                        .isEmpty());
                Assert.assertTrue(reportTable.getRows()
                        .get(2)
                        .get(5)
                        .getText(false)
                        .contains("Job Name"));
                Assert.assertTrue(reportTable.getRows()
                        .get(2)
                        .get(6)
                        .getText(false)
                        .contains(backUpName));
                Assert.assertEquals(reportTable.getRows()
                        .get(1)
                        .get(4)
                        .getText(false)
                        .replaceAll("\\s", ""),
                        (userNameLoggedIn + "deactivated backup schedule named" + backUpName).replaceAll("\\s", ""));
                Assert.assertEquals(reportTable.getRows()
                        .get(2)
                        .get(4)
                        .getText(false)
                        .replaceAll("\\s", ""),
                        (userNameLoggedIn + "scheduled" + occurrence + "backup named" + backUpName).replaceAll("\\s",
                                ""));

            }


            break;
        }
    }

    public void verifyCustomReport(String reportUrl, int dbRowCount, String endDate, String startDate)
            throws IOException {
        URL url = new URL(reportUrl);
        int rowCount = 0;
        // get all tables of the report
        PdfTable table = PdfTableExtractUtils.getTablesFromTableTitle(url.openStream(), AUDIT_TRAIL)
                .stream()
                .findFirst()
                .get();
        List<PdfTable> reportTables = PdfTableExtractUtils.getTables(url.openStream());
        for (PdfTable reportTable : reportTables) {
            rowCount = rowCount + reportTable.getRowCount();
        }
        Assert.assertTrue((dbRowCount - (rowCount - 11)) < 30);
        System.out.println(dbRowCount + " " + rowCount);
        for (PdfTable reportTable : reportTables) {
            System.out.println(reportTable.getRows()
                    .get(0)
                    .get(1)
                    .getText(false)
                    .contains(startDate.substring(1, 11)));
            System.out.println(reportTable.getRows()
                    .get(1)
                    .get(1)
                    .getText(false)
                    .contains(endDate.substring(1, 11)));
            Assert.assertTrue(reportTable.getRows()
                    .get(0)
                    .get(1)
                    .getText(false)
                    .contains(startDate.substring(1, 11)));
            Assert.assertTrue(reportTable.getRows()
                    .get(1)
                    .get(1)
                    .getText(false)
                    .contains(endDate.substring(1, 11)));
            break;
        }
    }

}

