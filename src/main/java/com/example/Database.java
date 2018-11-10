package com.example;

import com.example.utils.Constants;
import java.sql.*;

public class Database {
    private static final String URL = Constants.DATABASE_URL;
    private static final String USER = Constants.DATABASE_USER;
    private static final String PASSWORD = Constants.DATABASE_PASSWORD;
    private static final String jdbcDriver = Constants.JDBC_DRIVER;
    private static Connection connection = null;

    public static synchronized Connection getInstance() {
        if (connection == null) {
            try {
                Class.forName(jdbcDriver);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeSqlResources(PreparedStatement preparedStatement, ResultSet result) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ignore) {
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ignore) {
            }
        }
    }

    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
            }
        }
    }
}