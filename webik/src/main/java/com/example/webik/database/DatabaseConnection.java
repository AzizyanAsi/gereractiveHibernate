package com.example.webik.database;

import com.example.webik.util.DatabaseConfigurationUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection initializeConnection() throws SQLException {

        Properties props = DatabaseConfigurationUtil.getConnectionProperties();
        String dbDialect = props.getProperty("hibernate.dialect");
        String dbDriverClass = props.getProperty("hibernate.connection.driver_class");
        String dbConnUrl = props.getProperty("hibernate.connection.url");
        String dbUsername = props.getProperty("hibernate.connection.username");
        String dbPassword = props.getProperty("hibernate.connection.password");
        String current_session_context_class = props.getProperty("hibernate.current_session_context_class");
        String show_sql = props.getProperty(" hibernate.show_sql");


        try {
            Class.forName(dbDriverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return DriverManager.getConnection(dbConnUrl, dbUsername, dbPassword);
    }
}
