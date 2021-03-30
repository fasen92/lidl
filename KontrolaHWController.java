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

    @FXML
    void OnClickSpat(ActionEvent event) throws IOException {
        Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MenuScene = new Scene(MenuParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MenuScene);
        window.show();
    
    }

    @FXML
    void OnClickRefresh(ActionEvent event) throws IOException, SQLException {
       

        ChoiceBoxValues();
        update_Table_Main(getVyberZariadenia(ChoiceBoxTypzariadenia),getVyberskladu(ChoiceBoxSklad));
    }

    //choice box

   

    public String getSklad() {
        return Sklad;
    }
    public String getZariadenia() {
        return Zariadenia;
    }
    public  String getVyberskladu(ChoiceBox<String> ChoiceBoxSklad){
        return ChoiceBoxSklad.getValue();
    }
    public  String getVyberZariadenia(ChoiceBox<String> ChoiceBoxTypzariadenia){
        return ChoiceBoxTypzariadenia.getValue();
    }

    //nacitanie spravnej fxml tabulky

    public void update_Table_Main(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        if(choiceZariadenie.equals("Pbv")){
            loadPage("TablePbv");
            
            //tablePbvControler.update_Table(choiceZariadenie, choiceSklad);
            
            
        }
        if (choiceZariadenie.equals("Lispettore-scanner")) {
            System.out.println("som tam 2");
            loadPage("TableScanner");
            
        }
        

    }

    private void loadPage(String page)  {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       

        BP.setCenter(root);
        
    }
    public void ChoiceBoxValues(){
        Sklad = getVyberskladu(ChoiceBoxSklad);
        Zariadenia = getVyberZariadenia(ChoiceBoxTypzariadenia);
    }

    public void setup_Choiceboxs(){
        ObservableList <String> OLsklady = FXCollections.observableArrayList("Sklad1","Sklad2","Sklad3");
        ObservableList <String> OLzariadenia = FXCollections.observableArrayList("Pbv","Lispettore-scanner","MDE","Rabattdrucker","Quail");

        ChoiceBoxSklad.setItems(OLsklady);
        ChoiceBoxTypzariadenia.setItems(OLzariadenia);

        ChoiceBoxTypzariadenia.setValue("Pbv");
        ChoiceBoxSklad.setValue("Sklad1");

       
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("vytvorenie pozadia");
        setup_Choiceboxs();
        ChoiceBoxValues();

        
        TablePbvController tablePbvController = new TablePbvController();
        
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("TablePbv.fxml"));

        TablePbvController tablePbvController = new TablePbvController();
        tablePbvController = loader.getController();
        tablePbvController.initData(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));*/


       
        
        try {
            //tablePbvController.initData(ChoiceBoxTypzariadenia.getValue(),ChoiceBoxSklad.getValue());
            System.out.println(ChoiceBoxSklad.getValue()); 
            update_Table_Main(ChoiceBoxTypzariadenia.getValue(),ChoiceBoxSklad.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }

}
