import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KontrolaHWMPController implements Initializable{
    @FXML
    private BorderPane BP;

    @FXML
    private ChoiceBox<String> ChoiceBoxTypzariadenia;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    private TableView<MP> tabulka;

    @FXML
    private TableColumn<String, MP> ColumTyp;

    @FXML
    private TableColumn<String, MP> ColumNazov;

    @FXML
    private TableColumn<String, MP> ColumPocet;

    @FXML
    private TableColumn<String, MP> ColumSC;

    @FXML
    private TableColumn<String, MP> ColumTyptelefónu;

    @FXML
    private TableColumn<String, MP> ColumIMEI;

    @FXML
    private TableColumn<String, MP> ColumSIM;

    @FXML
    private TableColumn<String, MP> ColumTelčíslo;

    @FXML
    private TableColumn<String, MP> ColumPUK;

    @FXML
    private TableColumn<String, MP> ColumPIN;

    @FXML
    private TableColumn<String, MP> ColumCF;

    @FXML
    private TableColumn<String, MP> ColumDatumodoslania;

    @FXML
    private TableColumn<String, MP> ColumZaruka;

    @FXML
    private TableColumn<String, MP> ColumPoznamka;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad1;

    @FXML
    private TextField TFTyp;

    @FXML
    private TextField TFNazov;

    @FXML
    private TextField TFTypTel;

    @FXML
    private TextField TFPocet;

    @FXML
    private TextField TFSeriove;

    @FXML
    private TextField TFCF;

    @FXML
    private TextField TFTelcCslo;

    @FXML
    private TextField TFIMEI;

    @FXML
    private TextField TFSIM;

    @FXML
    private TextField TFPUK;

    @FXML
    private TextField TFPIN;

    @FXML
    private DatePicker DFOdoslanienafili;

    @FXML
    private DatePicker DFZaruka;

    @FXML
    private TextField TAPoznamka;

    @FXML
    private Button BtnVycisti;

    @FXML
    private Button BtnRefresh;

    @FXML
    private Button BtnMenu;

    @FXML
    private Button BtnUlož;

    
    String zaruka;
    String datumodoslania;

    Alert alert = new Alert(AlertType.INFORMATION);
    ObservableList<MP> OLtable = FXCollections.observableArrayList();
    

    @FXML
    void OnClickRefresh(ActionEvent event) throws IOException, SQLException {
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="MDE") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWMDE.fxml"));
            Scene rabatScene = new Scene(rabatParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Lispettore-scanner") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWScanner.fxml"));
            Scene scannerScene = new Scene(scannerParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Pbv") {
            Parent pbvParent = FXMLLoader.load(getClass().getResource("KontrolaHWPBV.fxml"));
            Scene pbvScene = new Scene(pbvParent);
        
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(pbvScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Quail") {
            Parent quailParent = FXMLLoader.load(getClass().getResource("KontrolaHWQuail.fxml"));
            Scene quailScene = new Scene(quailParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(quailScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Rabattdrucker") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWRabattdrucker.fxml"));
            Scene rabatScene = new Scene(rabatParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Ostatné") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWOstatne.fxml"));
            Scene rabatScene = new Scene(rabatParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Moblný telefon") {
            

            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
        }

    }

    @FXML
    void OnClickSpat(ActionEvent event) throws IOException {
        Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MenuScene = new Scene(MenuParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MenuScene);
        window.show();
    }

    @FXML
    void OnClickUloz(ActionEvent event) throws SQLException {
        if (getVyberskladu(ChoiceBoxSklad1).isEmpty()||TFTyp.getText().isEmpty()||TFNazov.getText().isEmpty()) {
            alert.setTitle("Information");
            alert.setContentText("Povynné polia sklad, typ alebo nazov nie sú vyplnené");
            alert.showAndWait();
    
            }else{
            datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
            zaruka = String.valueOf(DFZaruka.getValue());
           
    
            JDBMySQLConnection.addtoMP(getVyberskladu(ChoiceBoxSklad1),TFTyp.getText(),TFNazov.getText(),TFTypTel.getText(),TFPocet.getText(), TFSeriove.getText(), TFCF.getText(),TFTelcCslo.getText(),TFIMEI.getText(),TFSIM.getText(),TFPUK.getText(),TFPIN.getText(),datumodoslania,zaruka,TAPoznamka.getText());
    
            alert.setTitle("Information");
            alert.setContentText("Uspešne pridané");
            alert.showAndWait();
    
            vycisti();
    
            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
        }
    }

    @FXML
    void OnClickVycisti(ActionEvent event) {
        vycisti();
    }
    private void vycisti() {
        TFNazov.clear();
        TFTyp.clear();
        TFPocet.clear();
        TFSeriove.clear();
        TAPoznamka.clear();
        DFOdoslanienafili.setValue(null);
        DFZaruka.setValue(null);
        TAPoznamka.clear();
        TFTypTel.clear();
        TFPIN.clear();
        TFPUK.clear();
        TFIMEI.clear();
        TFTelcCslo.clear();
        TFSIM.clear();
        TFCF.clear();
        
        
    }

    public String getVyberskladu(ChoiceBox<String> ChoiceBoxSklad){
        return ChoiceBoxSklad.getValue();
    }
    public String getVyberZariadenia(ChoiceBox<String> ChoiceBoxTypzariadenia){
        return ChoiceBoxTypzariadenia.getValue();
    }

    public void setup_Choiceboxs(){
        ObservableList <String> OLsklady = FXCollections.observableArrayList("Sklad1","Sklad2","Sklad3");
        ObservableList <String> OLzariadenia = FXCollections.observableArrayList("Pbv","Lispettore-scanner","MDE","Rabattdrucker","Quail","Moblný telefon","Ostatné");

        ChoiceBoxSklad.setItems(OLsklady);
        ChoiceBoxTypzariadenia.setItems(OLzariadenia);
        ChoiceBoxSklad1.setItems(OLsklady);

        ChoiceBoxTypzariadenia.setValue("Moblný telefon");
        ChoiceBoxSklad.setValue("Sklad1");
        ChoiceBoxSklad1.setValue("Sklad1");
        

       
    }

    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        OLtable = JDBMySQLConnection.getMP(choiceZariadenie,choiceSklad);  

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumTyptelefónu.setCellValueFactory(new PropertyValueFactory<>("Typtel"));
        ColumIMEI.setCellValueFactory(new PropertyValueFactory<>("IMEI"));
        ColumPIN.setCellValueFactory(new PropertyValueFactory<>("PIN"));
        ColumPUK.setCellValueFactory(new PropertyValueFactory<>("PUK"));
        ColumTelčíslo.setCellValueFactory(new PropertyValueFactory<>("Telcislo"));
        ColumSIM.setCellValueFactory(new PropertyValueFactory<>("SIM"));
        ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

           
        tabulka.setItems(OLtable);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        System.out.println("vytvorenie pozadia");
        setup_Choiceboxs();
        
        try {
            System.out.println(ChoiceBoxSklad.getValue()); 
            update_Table(ChoiceBoxTypzariadenia.getValue(),ChoiceBoxSklad.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
