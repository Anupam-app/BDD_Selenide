package cucumber.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dataobjects.Report;
import io.cucumber.java.en.Given;

public class DBConnect {

    public static Connection con = null;
    // Statement object
    private static Statement stmt;
    // Constant for Database URL
    public static String DB_URL = "jdbc:sqlserver://molcipvm44:1433;databaseName=Runtime;encrypt=false";
    // Constant for Database Username
    public static String DB_USER = "runtimeuser";
    public static String DB_PASSWORD = "Service$App1@";

    private final Report report;
    public String executableQuery = null;
    public String startDate;
    public String endDate;

    public DBConnect(Report report) {
        this.report = report;
    }


    @Given("I check the row count in DB for {string}")
    public void iGetTheRowCount(String dateRange) {
        dbConnection(dateRange);
    }

    public void dbConnection(String dateRange) {



        if (dateRange.equals("Today")) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startDate = dateFormat.format(cal.getTime());
            cal.set(Calendar.HOUR, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            endDate = dateFormat.format(cal.getTime());
        } else if (dateRange.equals("Yesterday")) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startDate = dateFormat.format(cal.getTime());
            cal.set(Calendar.HOUR, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            endDate = dateFormat.format(cal.getTime());
        } else if (dateRange.equals("Last 7 Days")) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            endDate = dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, -6);
            startDate = dateFormat.format(cal.getTime());
        } else if (dateRange.equals("Last 30 Days")) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            endDate = dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, -29);
            startDate = dateFormat.format(cal.getTime());
        } else if (dateRange.equals("This Month")) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            endDate = dateFormat.format(cal.getTime());
            cal.set(Calendar.DAY_OF_MONTH, 1);
            startDate = dateFormat.format(cal.getTime());
        } else if (dateRange.equals("Last Month")) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DATE, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            endDate = dateFormat.format(cal.getTime());
            cal.set(Calendar.DATE, 1);
            startDate = dateFormat.format(cal.getTime());
        } else if (dateRange.equals("Custom range")) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            endDate = dateFormat.format(cal.getTime());
            cal.add(Calendar.MONTH, -2);
            startDate = dateFormat.format(cal.getTime());
        }
        this.report.setStartDate(startDate);
        this.report.setEndDate(endDate);
        System.out.println(startDate.substring(1, 11) + " " + endDate.substring(1, 11));

        try {
            String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(dbClass)
                    .newInstance();
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // var config = ConfigFactory.parseResourcesAnySyntax("parameters/ivi/Queries",
            // ConfigParseOptions.defaults());
            // var queries = config.getConfigList("Report.Queries");
            //
            // for (var query : queries) {
            // if (query.equals("AuditTrail")) {
            // executableQuery = query.getString("AuditTrail");
            // System.out.println(executableQuery);
            // }
            //
            // }
            System.out.println(startDate + "  " + endDate);
            String query = "select COUNT(*) from (select FORMAT(e1.EventTime,'dd/MMM/yyyy HH:mm:ss') as EventTime,\n"
                    + "e1.Provider_ApplicationName,\n" + "e1.Source_Object,\n" + "e1.User_Name,\n" + "e1.Comment,\n"
                    + "replace(e1.Source_ProcessVariable, 'null','') as Source_ProcessVariable,\n"
                    + "replace (e1.ValueString,'null','') as ValueString,\n"
                    + "replace (e1.PreviousValueString,'null','') as PreviousValueString\n"
                    + "FROM [Runtime].[dbo].[Events] e1 join [Runtime].[dbo].[Events] e2 on e1.User_Phone = e2.User_Phone\n"
                    + "and e1.Type=e2.Type\n" + "where e1.type='Manual'\n"
                    + "and e1.EventTime Between 'startDate' and 'endDate'\n"
                    + "and e2.EventTime Between 'startDate' and 'endDate'\n"
                    + "and lower(e2.Source_ProcessVariable) = 'parity_flag' and e2.ValueString = '1'\n"
                    + "and lower(e1.Source_ProcessVariable) != 'parity_flag'\n" + "union all\n"
                    + "Select FORMAT(EventTime,'dd/MMM/yyyy HH:mm:ss') as EventTime,\n" + "Provider_ApplicationName,\n"
                    + "Source_Object,\n" + "User_Name,\n" + "Comment,\n"
                    + "replace(Source_ProcessVariable, 'null','') as Source_ProcessVariable,\n"
                    + "replace (ValueString,'null','') as ValueString,\n"
                    + "replace (PreviousValueString,'null','') as PreviousValueString\n"
                    + "From  [Runtime].[dbo].[Events]\n" + "Where Events.EventTime Between 'startDate' and 'endDate'\n"
                    + "And Type = 'User.Write' and InTouchType = 'OPR' and Comment Is Not Null) as auditTrailRecords";

            ResultSet res = stmt.executeQuery(query.replace("startDate", startDate)
                    .replace("endDate", endDate));

            res.next();
            System.out.print(res.getInt(1));
            report.setRowCount(res.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
