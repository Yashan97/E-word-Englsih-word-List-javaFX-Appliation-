package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private  Connection connection;
    private  DBConnection() throws SQLException {
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/english_words1", "root", "1234");


    }
    public Connection getConnection(){
        return connection;
    }
    public static DBConnection getInstance() throws SQLException {
        if (instance == null){
            return  new DBConnection();
        }else {
            return instance;
        }
    }

}
