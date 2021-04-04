import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class TableMPController {

    @FXML
    private TableView<MP> tabulka;

    @FXML
    private TableColumn<MP, String> ColumTyp;

    @FXML
    private TableColumn<MP, String>ColumNazov;

    @FXML
    private TableColumn<MP, String> ColumPocet;

    @FXML
    private TableColumn<MP, String> ColumSC;

    @FXML
    private TableColumn<MP, String> ColumTyptelefónu;

    @FXML
    private TableColumn<MP, String> ColumIMEI;

    @FXML
    private TableColumn<MP, String> ColumSIM;

    @FXML
    private TableColumn<MP, String> ColumTelčíslo;

    @FXML
    private TableColumn<MP, String> ColumPUK;

    @FXML
    private TableColumn<MP, String> ColumPIN;

    @FXML
    private TableColumn<MP, String> ColumCF;

    @FXML
    private TableColumn<MP, String> ColumDatumodoslania;

    @FXML
    private TableColumn<MP, String> ColumZaruka;

    @FXML
    private TableColumn<MP, String> ColumPoznamka;

    static ObservableList <MP> OLtable = FXCollections.observableArrayList();

    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        OLtable = JDBMySQLConnection.getMP(choiceZariadenie,choiceSklad);  

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumIMEI.setCellValueFactory(new PropertyValueFactory<>("IMEI"));
        ColumPIN.setCellValueFactory(new PropertyValueFactory<>("PIN"));
        ColumPUK.setCellValueFactory(new PropertyValueFactory<>("PUK"));
        ColumSIM.setCellValueFactory(new PropertyValueFactory<>("SIM"));
        ColumTyptelefónu.setCellValueFactory(new PropertyValueFactory<>("Typtel"));
        ColumTelčíslo.setCellValueFactory(new PropertyValueFactory<>("Telcislo"));
        ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

           
        tabulka.setItems(OLtable);

    }

}
