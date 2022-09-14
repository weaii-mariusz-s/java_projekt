package projectjavaserver;

//glowne podpiecie pod baze danych

import java.sql.*;


public class DBConnect {   
   static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
   static final String USER = "JAVAPROJ";
   static final String PASS = "Nkocsq1q";

   public static Connection getConnection() {
    Connection conn = null;
      
    try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
        }catch (SQLException e) {
            e.printStackTrace();
        }
       return conn;
    }
}