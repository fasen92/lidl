import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KontrolaHWController implements Initializable {

    //premenné
    

    private String Sklad;
    private String Zariadenia;

    @FXML
    private BorderPane BP;
    
    @FXML
    public ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    private ChoiceBox<String> ChoiceBoxTypzariadenia;
    
    @FXML
    private Button Btnspat;

    @FXML
    private Button BtnRefresh;

    //ObservableList <Pbv> OLtable = FXCollections.observableArrayList();

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    //pripravene na tlacitko spat navrat do menu 
    @FXML
    void OnClickSpat(ActionEvent event) throws IOException {
        Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MenuScene = new Scene(MenuParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MenuScene);
        window.show();
    
    }

    //refresh button
    @FXML
    void OnClickRefresh(ActionEvent event) throws IOException, SQLException {
 
        update_Table_Main(getVyberZariadenia(ChoiceBoxTypzariadenia),getVyberskladu(ChoiceBoxSklad));
    }

    //choice box
    public String getSklad() {
        return Sklad;
    }
    public String getZariadenia() {
        return Zariadenia;
    }
    public String getVyberskladu(ChoiceBox<String> ChoiceBoxSklad){
        return ChoiceBoxSklad.getValue();
    }
    public String getVyberZariadenia(ChoiceBox<String> ChoiceBoxTypzariadenia){
        return ChoiceBoxTypzariadenia.getValue();
    }

    //nacitanie spravnej fxml tabulky

    public void update_Table_Main(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        if(choiceZariadenie.equals("Pbv")){
            loadPage("TablePbv");

        }
        if (choiceZariadenie.equals("Lispettore-scanner")) {
            loadPage("TableScanner");
            
        }
        if (choiceZariadenie.equals("Quail")) {
            loadPage("TableQuail");
        }
        if (choiceZariadenie.equals("Ostatné")) {
         loadPage("TableOstatne");   
        }
        if (choiceZariadenie.equals("Rabattdrucker")) {
            loadPage("TableRabattdrucker");
        }
        

    }
    //load Page funkcia pre zvolenie custom fxml sceny
    private void loadPage(String page)  {
        Parent root = null;
        

        
        if (page == "TablePbv") {
            try {
                TablePbvController tablePbvController;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page+".fxml"));
                root = (Parent) fxmlLoader.load();
                tablePbvController = fxmlLoader.getController();
    

                tablePbvController.update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));

                
            } catch (IOException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (page.equals("TableScanner")) {
            try {
                TableScannerController tableScannerController;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page+".fxml"));
                root = (Parent) fxmlLoader.load();
                tableScannerController = fxmlLoader.getController();
    
                System.out.println(getVyberZariadenia(ChoiceBoxTypzariadenia));
                System.out.println(getVyberskladu(ChoiceBoxSklad));
    
                tableScannerController.update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));

                
            } catch (IOException | SQLException e) {
                
                e.printStackTrace();
            }
        }

        if (page.equals("TableQuail")) {
            try {
                TableQuailController tableQuailController;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page+".fxml"));
                root = (Parent) fxmlLoader.load();
                tableQuailController = fxmlLoader.getController();
    
                System.out.println(getVyberZariadenia(ChoiceBoxTypzariadenia));
                System.out.println(getVyberskladu(ChoiceBoxSklad));
    
                tableQuailController.update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));

                
            } catch (IOException | SQLException e) {
                
                e.printStackTrace();
            }
        }
        if (page.equals("TableOstatne")) {
            try {
                TableOstatneController tableOstatneController;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page+".fxml"));
                root = (Parent) fxmlLoader.load();
                tableOstatneController = fxmlLoader.getController();
    
                System.out.println(getVyberZariadenia(ChoiceBoxTypzariadenia));
                System.out.println(getVyberskladu(ChoiceBoxSklad));
    
                tableOstatneController.update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));

                
            } catch (IOException | SQLException e) {
                
                e.printStackTrace();
            }
        }
        if (page.equals("TableRabattdrucker")) {
            try {
                TableRabattdruckerController tableRabattdruckerController;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page+".fxml"));
                root = (Parent) fxmlLoader.load();
                tableRabattdruckerController = fxmlLoader.getController();
    
                System.out.println(getVyberZariadenia(ChoiceBoxTypzariadenia));
                System.out.println(getVyberskladu(ChoiceBoxSklad));
    
                tableRabattdruckerController.update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));

                
            } catch (IOException | SQLException e) {
                
                e.printStackTrace();
            }
        }
        BP.setCenter(root);
        
    }
    

    public void setup_Choiceboxs(){
        ObservableList <String> OLsklady = FXCollections.observableArrayList("Sklad1","Sklad2","Sklad3");
        ObservableList <String> OLzariadenia = FXCollections.observableArrayList("Pbv","Lispettore-scanner","MDE","Rabattdrucker","Quail","Moblný telefon","Ostatné");

        ChoiceBoxSklad.setItems(OLsklady);
        ChoiceBoxTypzariadenia.setItems(OLzariadenia);

        ChoiceBoxTypzariadenia.setValue("Pbv");
        ChoiceBoxSklad.setValue("Sklad1");

       
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("vytvorenie pozadia");
        setup_Choiceboxs();
        
        try {
            System.out.println(ChoiceBoxSklad.getValue()); 
            update_Table_Main(ChoiceBoxTypzariadenia.getValue(),ChoiceBoxSklad.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }

}
