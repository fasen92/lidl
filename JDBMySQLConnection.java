import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

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
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    // Pbv
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

                    OLPbv.add(new Pbv(rs.getString("ID"), rs.getString("Typ"), rs.getString("Názov"),
                            rs.getString("Počet"), rs.getString("Sériové číslo"), rs.getString("Záznam činnosti"),
                            rs.getString("Dátum odoslania fili"), rs.getString("Záruka"), rs.getString("Poznámka"),
                            rs.getString("Oprava cez")));
                }
            } catch (Exception e) {

            }

            OLPbv.get(0).getNazov();
            return OLPbv;
        }
        return null;
    }

    // scanner
    public static ObservableList<Scanner> getScanner(String choiceZariadenie, String choiceSklad) throws SQLException {
        // nother one

        Connection conn = getConnection();
        ObservableList<Scanner> OLPScanner = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Lispettore-scanner` Where Sklad = ?");
            ps.setString(1, choiceSklad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OLPScanner.add(new Scanner(rs.getString("ID"), rs.getString("Typ"), rs.getString("Názov"),
                        rs.getString("Počet"), rs.getString("Sériové číslo"), rs.getString("Záznam činnosti"),
                        rs.getString("Dátum odoslania fili"), rs.getString("Záruka"), rs.getString("Poznámka"),
                        rs.getString("Oprava cez")));
            }
        } catch (Exception e) {

        }
        return OLPScanner;

    }

    // Quail

    public static ObservableList<Quail> getQuail(String choiceZariadenie, String choiceSklad) throws SQLException {
        // nother one
        if (choiceZariadenie.equals("Quail")) {

            Connection conn = getConnection();
            ObservableList<Quail> OLQuail = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `quail`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    rs.getString("Cislo fili");
                    OLQuail.add(new Quail(rs.getString("ID"), rs.getString("Typ"), rs.getString("Názov"),
                            rs.getString("Počet"), rs.getString("Sériové číslo"), rs.getString("Cislo fili"),
                            rs.getString("Záznam činnosti"), rs.getString("Dátum odoslania fili"),
                            rs.getString("Záruka"), rs.getString("Poznámka"), rs.getString("Oprava cez")));
                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLQuail;
        }
        return null;

    }

    // Ostatne
    public static ObservableList<Ostatne> getOstatne(String choiceZariadenie, String choiceSklad) throws SQLException {
        // nother one
        if (choiceZariadenie.equals("Ostatné")) {

            Connection conn = getConnection();
            ObservableList<Ostatne> OLOstatne = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `ostatne`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    OLOstatne.add(new Ostatne(rs.getString("ID"), rs.getString("Typ"), rs.getString("Názov"),
                            rs.getString("Počet"), rs.getString("Sériové číslo"), rs.getString("Cislo fili"),
                            rs.getString("Záznam činnosti"), rs.getString("Dátum odoslania fili"),
                            rs.getString("Záruka"), rs.getString("Poznámka"), rs.getString("Oprava cez")));

                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLOstatne;
        }
        return null;

    }

    // MDE
    public static ObservableList<MDE> getMDE(String choiceZariadenie, String choiceSklad) throws SQLException {
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
                    } else {
                        wifi = "Nie";
                    }

                    OLMDE.add(new MDE(rs.getString("ID"), rs.getString("Typ"), rs.getString("Názov"),
                            rs.getString("Počet"), rs.getString("Sériové číslo"), rs.getString("MAC adresa"),
                            rs.getString("IP adresa"), wifi, rs.getString("Cislo fili"),
                            rs.getString("Záznam činnosti"), rs.getString("Dátum odoslania fili"),
                            rs.getString("Záruka"), rs.getString("Poznámka"), rs.getString("Oprava cez")));
                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLMDE;
        }
        return null;

    }

    // Rabattdrucker
    public static ObservableList<Rabattdrucker> getRabattdrucker(String choiceZariadenie, String choiceSklad)
            throws SQLException {
        // nother one
        if (choiceZariadenie.equals("Rabattdrucker")) {

            Connection conn = getConnection();
            ObservableList<Rabattdrucker> OLRabattdrucker = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `rabattdrucker`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    OLRabattdrucker.add(new Rabattdrucker(rs.getString("ID"), rs.getString("Typ"),
                            rs.getString("Názov"), rs.getString("Počet"), rs.getString("Sériové číslo"),
                            rs.getString("Evidencne cislo"), rs.getString("Cislo fili"),
                            rs.getString("Záznam činnosti"), rs.getString("Dátum odoslania fili"),
                            rs.getString("Záruka"), rs.getString("Poznámka"), rs.getString("Oprava cez")));

                }
            } catch (Exception e) {
                System.out.println("nieco je zle");
            }
            return OLRabattdrucker;
        }
        return null;

    }

    // ucty
    public static ArrayList<Ucet> getUcty() throws SQLException {
        Connection conn = getConnection();
        ArrayList<Ucet> UcetList = new ArrayList<Ucet>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `ucet`;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UcetList.add(new Ucet(rs.getInt("id"), rs.getString("heslo"), rs.getString("meno"),
                        rs.getString("priezvisko"), rs.getString("rola"), rs.getString("sklad"),
                        rs.getInt("prvyLogin")));
            }
        } catch (Exception e) {

        }

        return UcetList;
    }

    // Telefon pre predajnu skratka MP
    public static ObservableList<MP> getMP(String choiceZariadenie, String choiceSklad) throws SQLException {
        // nother one
        if (choiceZariadenie.equals("Moblný telefon")) {

            Connection conn = getConnection();
            ObservableList<MP> OLMP = FXCollections.observableArrayList();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `mobil-prepredajnu`  Where Sklad = ?");
                ps.setString(1, choiceSklad);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    OLMP.add(new MP(rs.getString("ID"), rs.getString("Typ"), rs.getString("Názov"),
                            rs.getString("Počet"), rs.getString("Sériové číslo"), rs.getString("Typ telefonu"),
                            rs.getString("IMEI"), rs.getString("SIM"), rs.getString("Tel.cislo"), rs.getString("PUK"),
                            rs.getString("PIN"), rs.getString("Cislo fili"), rs.getString("Záznam činnosti"),
                            rs.getString("Dátum odoslania fili"), rs.getString("Záruka"), rs.getString("Poznámka"),
                            rs.getString("Oprava cez")));
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
                        rs.getString("priezvisko"), rs.getString("rola"), rs.getString("sklad"),
                        rs.getInt("prvyLogin")));
            }
        } catch (Exception e) {

        }

        return UcetList;
    }

    public static void addtoUcet(String meno, String priezvisko, String sklad, String rola) {
        int prvyLogin = 1;
        getQueryUcet();
        insertUcet(meno, priezvisko, rola, sklad, prvyLogin);
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

    public static void addtoPbv(String vyberskladu, String text, String text2, String text3, String text4,
            String datumodoslania, String zaruka, String text5) {
        
        getQueryPbv();
        insertPbv(vyberskladu, text, text2, text3, text4, datumodoslania, zaruka, text5);
    }

    private static void insertPbv(String vyberskladu, String text, String text2, String text3, String text4,
            String datumodoslania, String zaruka, String text5) {

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

    public static void addtoScanner(String vyberskladu, String text, String text2, String text3, String text4,
            String datumodoslania, String zaruka, String text5) {
        
        getQueryScanner();
        insertScanner(vyberskladu, text, text2, text3, text4, datumodoslania, zaruka, text5);
    }

    private static void insertScanner(String vyberskladu, String text, String text2, String text3, String text4,
            String datumodoslania, String zaruka, String text5) {

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

    private static void getQueryScanner() {
        query = "INSERT INTO `lispettore-scanner`( `Sklad`, `Typ`, `Názov`, `Počet`, `Sériové číslo`,`Dátum odoslania fili`, `Záruka`, `Poznámka`) VALUES (?,?,?,?,?,?,?,?)";
    }

    public static void addtoMDE(String vyberskladu, String text, String text2, String text3, String text4, String text5,
            String text6, String wificheck, String text7, String datumodoslania, String zaruka, String text8) {
        
        getQueryMDE();
        insertMDE(vyberskladu, text, text2, text3, text4, text5, text6, wificheck, text7, datumodoslania, zaruka,
                text8);

    }

    private static void insertMDE(String vyberskladu, String text, String text2, String text3, String text4,
            String text5, String text6, String wificheck, String text7, String datumodoslania, String zaruka,
            String text8) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, vyberskladu);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text2);
            preparedStatement.setString(4, text3);
            preparedStatement.setString(5, text4);

            preparedStatement.setString(6, text5);
            preparedStatement.setString(7, text6);
            preparedStatement.setString(8, wificheck);
            preparedStatement.setString(9, text7);

            preparedStatement.setString(10, datumodoslania);
            preparedStatement.setString(11, zaruka);
            preparedStatement.setString(12, text8);

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getQueryMDE() {
        query = "INSERT INTO `mde`(`Sklad`, `Typ`, `Názov`, `Počet`, `Sériové číslo`, `MAC adresa`, `IP adresa`, `HuaweiWifi`, `Cislo fili`, `Dátum odoslania fili`,  `Záruka`, `Poznámka`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public static void addtoQuail(String vyberskladu, String text, String text2, String text3, String text4,
            String text5, String datumodoslania, String zaruka, String text6) {
        getQueryQuail();
        insertQuail(vyberskladu, text, text2, text3, text4, datumodoslania, zaruka, text5, text6);
    }

    private static void insertQuail(String vyberskladu, String text, String text2, String text3, String text4,
            String datumodoslania, String zaruka, String text5, String text6) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, vyberskladu);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text2);
            preparedStatement.setString(4, text3);
            preparedStatement.setString(5, text4);
            preparedStatement.setString(6, text5);

            preparedStatement.setString(7, datumodoslania);
            preparedStatement.setString(8, zaruka);
            preparedStatement.setString(9, text6);

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getQueryQuail() {
        query = "INSERT INTO `quail`(`Sklad`, `Typ`, `Názov`, `Počet`, `Sériové číslo`, `Cislo fili`, `Dátum odoslania fili`, `Záruka`, `Poznámka`) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    public static void addtoRabattdrucker(String vyberskladu, String text, String text2, String text3, String text4,
            String text5, String text6, String datumodoslania, String zaruka, String text7) {
        getQueryRabattdrucker();
        insertRabattdrucker(vyberskladu, text, text2, text3, text4, datumodoslania, zaruka, text5, text6, text7);
    }

    private static void insertRabattdrucker(String vyberskladu, String text, String text2, String text3, String text4,
            String datumodoslania, String zaruka, String text5, String text6, String text7) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, vyberskladu);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text2);
            preparedStatement.setString(4, text3);
            preparedStatement.setString(5, text4);

            preparedStatement.setString(6, text5);

            preparedStatement.setString(7, text6);

            preparedStatement.setString(8, datumodoslania);
            preparedStatement.setString(9, zaruka);
            preparedStatement.setString(10, text7);

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getQueryRabattdrucker() {
        query = "INSERT INTO `rabattdrucker`( `Sklad`, `Typ`, `Názov`, `Počet`, `Sériové číslo`, `Evidencne cislo`, `Cislo fili`,  `Dátum odoslania fili`, `Záruka`, `Poznámka`) VALUES (?,?,?,?,?,?,?,?,?,?)";
    }

    public static void addtoOstatne(String vyberskladu, String text, String text2, String text3, String text4,
            String text5, String datumodoslania, String zaruka, String text6) {

        getQueryOstatne();
        insertOstatne(vyberskladu, text, text2, text3, text4, datumodoslania, zaruka, text5, text6);
    }

    private static void insertOstatne(String vyberskladu, String text, String text2, String text3, String text4,
            String datumodoslania, String zaruka, String text5, String text6) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, vyberskladu);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text2);
            preparedStatement.setString(4, text3);
            preparedStatement.setString(5, text4);
            preparedStatement.setString(6, text5);

            preparedStatement.setString(7, datumodoslania);
            preparedStatement.setString(8, zaruka);
            preparedStatement.setString(9, text6);

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getQueryOstatne() {
        query = "INSERT INTO `ostatne`(`Sklad`, `Typ`, `Názov`, `Počet`, `Sériové číslo`, `Cislo fili`, `Dátum odoslania fili`, `Záruka`, `Poznámka`) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    public static void addtoMP(String vyberskladu, String text, String text2, String text3, String text4, String text5,
            String text6, String tFTelcCslo, String tFIMEI, String tFSIM, String tFPUK, String tFPIN,
            String datumodoslania, String zaruka, String text7) {

        getQueryMP();
        insertMP(vyberskladu, text, text2, text3, text4, text5, text6, tFTelcCslo, tFIMEI, tFSIM, tFPUK, tFPIN,
                datumodoslania, zaruka, text7);
    }

    private static void insertMP(String vyberskladu, String text, String text2, String text3, String text4,
            String text5, String text6, String tFTelcCslo, String tFIMEI, String tFSIM, String tFPUK, String tFPIN,
            String datumodoslania, String zaruka, String text7) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, vyberskladu);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text2);
            preparedStatement.setString(4, text3);
            preparedStatement.setString(5, text4);
            preparedStatement.setString(6, text5);

            preparedStatement.setString(7, text6);
            preparedStatement.setString(8, tFTelcCslo);
            preparedStatement.setString(9, tFIMEI);
            preparedStatement.setString(10, tFSIM);
            preparedStatement.setString(11, tFPUK);
            preparedStatement.setString(12, tFPIN);

            preparedStatement.setString(13, datumodoslania);
            preparedStatement.setString(14, zaruka);
            preparedStatement.setString(15, text7);

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getQueryMP() {
        query = "INSERT INTO `mobil-prepredajnu`( `Sklad`, `Typ`, `Názov`, `Typ telefonu`, `Počet`, `Sériové číslo`, `Cislo fili`, `Tel.cislo`, `IMEI`, `SIM`, `PUK`, `PIN`, `Dátum odoslania fili`, `Záruka`, `Poznámka`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public static ObservableList<Osoba> getUctyZazAkci() throws SQLException {
        Connection conn = getConnection();
        ObservableList<Osoba> OsobaList = FXCollections.observableArrayList();
        
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `zaznamy-akci`");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                OsobaList.add(new Osoba(rs.getString("ID"), rs.getString("Meno"), rs.getString("Priezvisko"),
                        rs.getString("Cas"), rs.getString("Akcia")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return OsobaList;
    }

    public static void addtoZazAkci( String Meno, String Priezvisko,  String Akcia) {
        getQueryZazakci();
        String Cas = LocalDate.now().toString();
        insertZazakci(Meno,Priezvisko,Cas,Akcia);
    }

    private static void insertZazakci(String Meno, String Priezvisko, String Cas, String Akcia) {

        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            
            preparedStatement.setString(1, Meno);
            preparedStatement.setString(2, Priezvisko);
            preparedStatement.setString(3, Cas);
            preparedStatement.setString(4, Akcia);

            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getQueryZazakci() {
        query = "INSERT INTO `zaznamy-akci`(`Meno`, `Priezvisko`, `Cas`, `Akcia`) VALUES (?,?,?,?)";
        
    }

}
