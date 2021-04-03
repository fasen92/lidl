import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableRabattdruckerController {

    @FXML
    private TableView<Rabattdrucker> tabulka;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumTyp;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumNazov;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumPocet;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumSC;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumECO;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumCF;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumDatumodoslania;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumPoznamka;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumZaruka;

    static ObservableList <Rabattdrucker> OLtable = FXCollections.observableArrayList();

    
    


    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        OLtable = JDBMySQLConnection.getRabattdrucker(choiceZariadenie,choiceSklad);  

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumECO.setCellValueFactory(new PropertyValueFactory<>("ECO"));
        ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

           
        tabulka.setItems(OLtable);

    }
}
