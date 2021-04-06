import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JDBMySQLConnection {
    public static final String URL = "jdbc:mysql://localhost/lidl";
    // User name pre potrebu zmejdbc
    public static final String USER_NAME = "dusan";
    // Password pre potrebu zmen
    public static final String PASSWORD = "dusan";
    // Driver : it comes with the jar file
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    static String query = null;
    static PreparedStatement preparedStatement;

    static Connection conn = null;

    public static Connection getConnection() throws SQLException {

        try {
            Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            JOptionPane.showMessageDialog(null, "Connected");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    //Pbv
    public static ObservableList<Pbv> getPbv(String choiceZariadenie, String choiceSklad) throws SQLException {
        // first one
        if (choiceZariadenie.equals("Pbv")) {
            Connection conn = getConnection();
            ObservableList<Pbv> OLPbv = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `pbv` Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    
                    OLPbv.add(new Pbv(rs.getString("Typ"), rs.getString("Názov"), rs.getString("Počet"),
                            rs.getString("Sériové číslo"), rs.getString("Dátum odoslania fili"), rs.getString("Záruka"),
                            rs.getString("Poznámka")));
                }
            } catch (Exception e) {

            }

            OLPbv.get(0).getNazov();

            return OLPbv;

        }

        return null;

    }

    //scanner
    public static ObservableList<Scanner> getScanner(String choiceZariadenie, String choiceSklad) throws SQLException{
        // nother one
        if (choiceZariadenie.equals("Lispettore-scanner")) {
            Connection conn = getConnection();
            ObservableList<Scanner> OLPScanner = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Lispettore-scanner` Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                   System.out.println(ps);
                    OLPScanner.add(new Scanner(rs.getString("Typ"), rs.getString("Názov"), rs.getString("Počet"),
                            rs.getString("Sériové číslo"), rs.getString("Dátum odoslania fili"), rs.getString("Záruka"),
                            rs.getString("Poznámka")));
                }
            } catch (Exception e) {

            }
            return OLPScanner;
        }
        return null;

    }

    //Quail

    public static ObservableList<Quail> getQuail(String choiceZariadenie, String choiceSklad) throws SQLException{
        // nother one
        if (choiceZariadenie.equals("Quail")) {
           
            Connection conn = getConnection();
            ObservableList<Quail> OLQuail = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `quail`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    OLQuail.add(new Quail(rs.getString("Typ"), rs.getString("Názov"), rs.getString("Počet"),
                            rs.getString("Sériové číslo"),rs.getString("Cislo fili"), rs.getString("Dátum odoslania fili"), rs.getString("Záruka"),
                            rs.getString("Poznámka")));

                            
                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLQuail;
        }
        return null;

    }





    //Ostatne
    public static ObservableList<Ostatne> getOstatne(String choiceZariadenie, String choiceSklad) throws SQLException{
        // nother one
        if (choiceZariadenie.equals("Ostatné")) {
           
            Connection conn = getConnection();
            ObservableList<Ostatne> OLOstatne = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `ostatne`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    OLOstatne.add(new Ostatne(rs.getString("Typ"), rs.getString("Názov"), rs.getString("Počet"),
                            rs.getString("Sériové číslo"),rs.getString("Cislo fili"), rs.getString("Dátum odoslania fili"), rs.getString("Záruka"),
                            rs.getString("Poznámka")));

                            
                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLOstatne;
        }
        return null;

    }
    //MDE
    public static ObservableList<MDE> getMDE(String choiceZariadenie, String choiceSklad) throws SQLException{
        // nother one
        if (choiceZariadenie.equals("MDE")) {
           
            Connection conn = getConnection();
            ObservableList<MDE> OLMDE = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `mde`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();
                String wifi;
                while (rs.next()) {
                    if (rs.getBoolean("HuaweiWifi")) {
                        wifi = "Ano";
                    }else{
                        wifi = "Nie";
                    }
                    OLMDE.add(new MDE(rs.getString("Typ"), rs.getString("Názov"), rs.getString("Počet"),
                            rs.getString("Sériové číslo"),rs.getString("MAC adresa"),rs.getString("IP adresa"),wifi,rs.getString("Cislo fili"), rs.getString("Dátum odoslania fili"), rs.getString("Záruka"),
                            rs.getString("Poznámka")));          
                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLMDE;
        }
        return null;

    }
    //Rabattdrucker
    public static ObservableList<Rabattdrucker> getRabattdrucker(String choiceZariadenie, String choiceSklad) throws SQLException{
        // nother one
        if (choiceZariadenie.equals("Rabattdrucker")) {
           
            Connection conn = getConnection();
            ObservableList<Rabattdrucker> OLRabattdrucker = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `rabattdrucker`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    OLRabattdrucker.add(new Rabattdrucker(rs.getString("Typ"), rs.getString("Názov"), rs.getString("Počet"),
                            rs.getString("Sériové číslo"),rs.getString("Evidencne cislo"),rs.getString("Cislo fili"), rs.getString("Dátum odoslania fili"), rs.getString("Záruka"),
                            rs.getString("Poznámka")));

                            
                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLRabattdrucker;
        }
        return null;

    }
    //ucty
    public static ArrayList<Ucet> getUcty() throws SQLException {
        Connection conn = getConnection();
        ArrayList<Ucet> UcetList = new ArrayList<Ucet>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `ucet`;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UcetList.add(new Ucet(rs.getInt("id"), rs.getString("heslo"), rs.getString("meno"),
                        rs.getString("priezvisko"), rs.getString("rola"), rs.getString("sklad")));
            }
        } catch (Exception e) {

        }

        return UcetList;
    }
    //Telefon pre predajnu skratka MP
    public static ObservableList<MP> getMP(String choiceZariadenie, String choiceSklad) throws SQLException{
        // nother one
        if (choiceZariadenie.equals("Moblný telefon")) {
           
            Connection conn = getConnection();
            ObservableList<MP> OLMP = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `mobil-prepredajnu`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    OLMP.add(new MP(rs.getString("Typ"), rs.getString("Názov"), rs.getString("Počet"),
                            rs.getString("Sériové číslo"),rs.getString("Typ telefonu"),rs.getString("IMEI"),rs.getString("SIM"),
                            rs.getString("Tel.cislo"),rs.getString("PUK"),rs.getString("PIN"),
                            rs.getString("Cislo fili"), rs.getString("Dátum odoslania fili"), rs.getString("Záruka"),
                            rs.getString("Poznámka")));
                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLMP;
        }
        return null;
    }

    public static ObservableList<Ucet> getUctyTab() throws SQLException {
        Connection conn = getConnection();
        ObservableList<Ucet> UcetList = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `ucet`;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UcetList.add(new Ucet(rs.getInt("id"), rs.getString("heslo"), rs.getString("meno"),
                        rs.getString("priezvisko"), rs.getString("rola"), rs.getString("sklad")));
            }
        } catch (Exception e) {

        }

        return UcetList;
    }

    public static void addtoUcet(String meno, String priezvisko, String sklad, String rola) {
        System.out.println("add to ucet");
        int prvyLogin =1;
        getQueryUcet();
        insertUcet(meno,priezvisko,rola,sklad,prvyLogin);
    }

    private static void getQueryUcet() {
        query = "INSERT INTO `ucet`( `id`, `heslo`, `meno`, `priezvisko`, `rola`,`sklad`,`prvyLogin`) VALUES (?,?,?,?,?,?,?)";
    }

    private static void insertUcet(String meno, String priezvisko, String rola, String sklad, int prvyLogin) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, meno);
            preparedStatement.setString(3, meno);
            preparedStatement.setString(4, priezvisko);
            preparedStatement.setString(5, rola);
            preparedStatement.setString(6, sklad);
            preparedStatement.setInt(7, prvyLogin);
            

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void addtoPbv(String vyberskladu, String text, String text2, String text3, String text4,String datumodoslania, String zaruka, String text5) {
        System.out.println("add to pbv");
        getQueryPbv();
        insertPbv(vyberskladu,text,text2,text3,text4,datumodoslania,zaruka,text5);
    }
    private static void insertPbv(String vyberskladu, String text, String text2, String text3, String text4,String datumodoslania, String zaruka, String text5) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, vyberskladu);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text2);
            preparedStatement.setString(4, text3);
            preparedStatement.setString(5, text4);
            
            preparedStatement.setString(6, datumodoslania);
            preparedStatement.setString(7, zaruka);
            preparedStatement.setString(8, text5);

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void getQueryPbv() {
        query = "INSERT INTO `pbv`( `Sklad`, `Typ`, `Názov`, `Počet`, `Sériové číslo`,`Dátum odoslania fili`, `Záruka`, `Poznámka`) VALUES (?,?,?,?,?,?,?,?)";
    }

    
}
