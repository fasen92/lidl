import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableOstatneController {

    @FXML
    private TableView<Ostatne> tabulka;

    @FXML
    private TableColumn<String, Ostatne> ColumTyp;

    @FXML
    private TableColumn<String, Ostatne> ColumNazov;

    @FXML
    private TableColumn<String, Ostatne> ColumPocet;

    @FXML
    private TableColumn<String, Ostatne> ColumSC;

    @FXML
    private TableColumn<String, Ostatne> ColumCF;

    @FXML
    private TableColumn<String, Ostatne> ColumDatumodoslania;

    @FXML
    private TableColumn<String, Ostatne> ColumZaruka;

    @FXML
    private TableColumn<String, Ostatne> ColumPoznamka;

    static ObservableList <Ostatne> OLtable = FXCollections.observableArrayList();

    
    


    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        OLtable = JDBMySQLConnection.getOstatne(choiceZariadenie,choiceSklad);  

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

           
        tabulka.setItems(OLtable);

    }

}
