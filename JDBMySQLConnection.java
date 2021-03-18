import java.sql.*;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class JDBMySQLConnection {
public static final String URL="jdbc:mysql://localhost/lidl";
//User name pre potrebu zmejdbc
public static final String USER_NAME="dusan";
//Password pre potrebu zmen
public static final String PASSWORD="dusan";
//Driver : it comes with the jar file
public static final String DRIVER_CLASS="com.mysql.jdbc.Driver";

Connection conn = null;


public static  Connection getConnection() throws SQLException {
  
    try {
        Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        JOptionPane.showMessageDialog(null, "Connected");
        return conn;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,e);
        return null;
    }

    }

    public static ObservableList<Pbv> getData(String choice) throws SQLException{
        
        if (choice.equals("Pbv")) {
            Connection conn = getConnection();
        ObservableList<Pbv> OLPbv = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `pbv-ibahw`");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OLPbv.add(new Pbv(rs.getString("Typ"),rs.getString("Názov"),rs.getString("Počet"),rs.getString("Sériové číslo"),rs.getString("Dátum odoslania fili"),rs.getString("Záruka"),rs.getString("Poznámka")));
            }
        } catch (Exception e) {
            
        }

        return OLPbv;

        }
        if (choice.equals("Quail")) {
            System.out.println("2. Tabulka");
        }

        return null;
        
    }
   
    
}



