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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KontrolaHWControler implements Initializable {

    @FXML
    private TableView<Pbv> tabulka;

    @FXML
    private TableColumn<Pbv, String> ColumTyp;

    @FXML
    private TableColumn<Pbv, String> ColumNazov;

    @FXML
    private TableColumn<Pbv, String> ColumPocet;

    @FXML
    private TableColumn<Pbv, String> ColumSC;

    @FXML
    private TableColumn<Pbv, String> ColumDatumodoslania;

    @FXML
    private TableColumn<Pbv, String> ColumZaruka;

    @FXML
    private TableColumn<Pbv, String> ColumPoznamka;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    private ChoiceBox<String> ChoiceBoxTypzariadenia;
    
    @FXML
    private Button Btnspat;

    @FXML
    private Button BtnRefresh;

    ObservableList <Pbv> OLtable = FXCollections.observableArrayList();

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
        update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia),getVyberskladu(ChoiceBoxSklad));
    }
    public String getVyberskladu(ChoiceBox<String> ChoiceBoxSklad){
        return ChoiceBoxSklad.getValue();
    }
    public String getVyberZariadenia(ChoiceBox<String> ChoiceBoxTypzariadenia){
        return ChoiceBoxTypzariadenia.getValue();
    }

    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

        OLtable = JDBMySQLConnection.getData(choiceZariadenie,choiceSklad);
        tabulka.setItems(OLtable);

    }

    public void setup_Choiceboxs(){
        ObservableList <String> OLsklady = FXCollections.observableArrayList("Sklad1","Sklad2","Sklad3");
        ObservableList <String> OLzariadenia = FXCollections.observableArrayList("Pbv","Lispettore-scanner","MDE","Rabattdrucker","Quail");

        ChoiceBoxSklad.setItems(OLsklady);
        ChoiceBoxTypzariadenia.setItems(OLzariadenia);

        ChoiceBoxTypzariadenia.setValue("Pbv");
        //to do nacitat podla profilu predvoleny sklad
        ChoiceBoxSklad.setValue("Sklad1");
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setup_Choiceboxs();

        try {
            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia),getVyberskladu(ChoiceBoxSklad));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
