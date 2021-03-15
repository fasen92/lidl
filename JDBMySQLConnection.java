import java.sql.*;
public class JDBMySQLConnection {
public static final String URL="jdbc:mysql://localhost/lidl";
//User name pre potrebu zmejdbc
public static final String USER_NAME="dusan";
//Password pre potrebu zmen
public static final String PASSWORD="dusan";
//Driver : it comes with the jar file
public static final String DRIVER_CLASS="com.mysql.jdbc.Driver";


public static  Connection getConnection() throws SQLException {
    Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

    return connection;
    }
   
    
}



