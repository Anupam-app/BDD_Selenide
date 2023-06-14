package pageobjects.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import dataobjects.Report;

public class DatabaseHelper {

    private static Connection connection;
    private final Report report;
    public static int rowCount;

    public DatabaseHelper(Report report) {
        this.report = report;
    }


    public static void connectDB(String dbURL, String dbUserName, String dbPassword)
            throws SQLException, FileNotFoundException {
        try {
            InputStream input = new FileInputStream("src/test/resources/application.properties");
            Properties prop = new Properties();
            prop.load(input);
            Class.forName(prop.getProperty("database.driver-class-name"));

            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int fetchData(String query) throws SQLException {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query);) {
            resultSet.next();
            rowCount = (resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
