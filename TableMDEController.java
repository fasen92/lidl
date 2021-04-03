import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableMDEController {

    @FXML
    private TableView<MDE> tabulka;

    @FXML
    private TableColumn<String, MDE> ColumTyp;

    @FXML
    private TableColumn<String, MDE> ColumNazov;

    @FXML
    private TableColumn<String, MDE> ColumPocet;

    @FXML
    private TableColumn<String, MDE> ColumSC;

    @FXML
    private TableColumn<String, MDE> ColumMac;

    @FXML
    private TableColumn<String, MDE> ColumIP;

    @FXML
    private TableColumn<String, MDE> ColumWifi;

    @FXML
    private TableColumn<String, MDE> ColumCF;

    @FXML
    private TableColumn<String, MDE> ColumDatumodoslania;

    @FXML
    private TableColumn<String, MDE> ColumZaruka;

    @FXML
    private TableColumn<String, MDE> ColumPoznamka;

    static ObservableList<MDE> OLtable = FXCollections.observableArrayList();

    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        OLtable = JDBMySQLConnection.getMDE(choiceZariadenie,choiceSklad);  

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumIP.setCellValueFactory(new PropertyValueFactory<>("IP"));
        ColumMac.setCellValueFactory(new PropertyValueFactory<>("MAC"));
        ColumWifi.setCellValueFactory(new PropertyValueFactory<>("WIFI"));
        ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

           
        tabulka.setItems(OLtable);

    }

}
