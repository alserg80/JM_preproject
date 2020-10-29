package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
