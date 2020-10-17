package com.epam.utility;

import java.sql.*;

public class DatabaseConnectionManager {
    static ConfigFileReader configFileReader;
    static Connection connection;

    public static Connection getConnection() {
        if (connection != null) return connection;
        configFileReader = new ConfigFileReader();
        return getConnection(configFileReader.getDbUrl(), configFileReader.getDbUsername(), configFileReader.getDbPassword());
    }

    private static Connection getConnection(String dbUrl, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            LoggerUtil.warn("Error connecting to Database");
        }
        return connection;
    }
}
