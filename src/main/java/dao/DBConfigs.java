package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfigs {
    public static final String DB_HOST = "185.231.154.146";
    public static final String DB_PORT = "3306";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "flush";
    public static final String DB_NAME = "Messenger";

    static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME+
                "?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(connectionString, DB_USERNAME, DB_PASSWORD);
    }
}
