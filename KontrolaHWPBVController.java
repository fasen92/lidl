import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KontrolaHWPBVController implements Initializable{

    @FXML
    private BorderPane BP;

    @FXML
    private Button BtnVycisti;

    @FXML
    private Button BtnRefresh;

    @FXML
    private Button BtnMenu;

    @FXML
    private Button BtnUlož;

    @FXML
    private ChoiceBox<String> ChoiceBoxTypzariadenia;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad1;

    @FXML
    private TextField TFTyp;

    @FXML
    private TextField TFNazov;

    @FXML
    private TextField TFPocet;

    @FXML
    private TextField TFSeriove;

    @FXML
    private DatePicker DFOdoslanienafili;

    @FXML
    private DatePicker DFZaruka;

    @FXML
    private TextField TAPoznamka;

    @FXML
    private TableView<Pbv> tabulka;

    @FXML
    private TableColumn<String, Pbv> ColumTyp;

    @FXML
    private TableColumn<String, Pbv> ColumNazov;

    @FXML
    private TableColumn<String, Pbv> ColumPocet;

    @FXML
    private TableColumn<String, Pbv> ColumSC;

    @FXML
    private TableColumn<String, Pbv> ColumDatumodoslania;

    @FXML
    private TableColumn<String, Pbv> ColumZaruka;

    @FXML
    private TableColumn<String, Pbv> ColumPoznamka;

    Pbv pbv;
    String zaruka;
    String datumodoslania;

    Alert alert = new Alert(AlertType.INFORMATION);
    
    static ObservableList <Pbv> OLtable = FXCollections.observableArrayList();


    @FXML
    void OnClickRefresh(ActionEvent event) throws SQLException, IOException {
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Pbv") {
            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="Lispettore-scanner") {
        Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWScanner.fxml"));
        Scene scannerScene = new Scene(scannerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scannerScene);
        window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia)=="MDE") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWMDE.fxml"));
            Scene scannerScene = new Scene(scannerParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
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
        if (getVyberskladu(ChoiceBoxSklad).isEmpty()||TFTyp.getText().isEmpty()||TFNazov.getText().isEmpty()) {
        alert.setTitle("Information");
        alert.setContentText("Povynné polia sklad, typ alebo nazov nie sú vyplnené");
        alert.showAndWait();

        }else{
        datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
        zaruka = String.valueOf(DFZaruka.getValue());
        pbv = new Pbv(getVyberskladu(ChoiceBoxSklad1),TFTyp.getText(),TFNazov.getText(),TFPocet.getText(), TFSeriove.getText(), datumodoslania,zaruka, TAPoznamka.getText());

        JDBMySQLConnection.addtoPbv(getVyberskladu(ChoiceBoxSklad1),TFTyp.getText(),TFNazov.getText(),TFPocet.getText(), TFSeriove.getText(), datumodoslania,zaruka, TAPoznamka.getText());

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

        ChoiceBoxTypzariadenia.setValue("Pbv");
        ChoiceBoxSklad.setValue("Sklad1");
        ChoiceBoxSklad1.setValue("Sklad1");

       
    }

    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Pbv") {
        OLtable = JDBMySQLConnection.getPbv(choiceZariadenie,choiceSklad);  

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

           
        tabulka.setItems(OLtable);
        
        
        }
        

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
