import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableQuailController {

    @FXML
    private TableView<Quail> tabulka;

    @FXML
    private TableColumn<String, Quail> ColumTyp;

    @FXML
    private TableColumn<String, Quail> ColumNazov;

    @FXML
    private TableColumn<String, Quail> ColumPocet;

    @FXML
    private TableColumn<String, Quail> ColumSC;

    @FXML
    private TableColumn<String, Quail> ColumCF;

    @FXML
    private TableColumn<String, Quail> ColumDatumodoslania;

    @FXML
    private TableColumn<String, Quail> ColumZaruka;

    @FXML
    private TableColumn<String, Quail> ColumPoznamka;

    static ObservableList <Quail> OLtable = FXCollections.observableArrayList();

    
    


    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        OLtable = JDBMySQLConnection.getQuail(choiceZariadenie,choiceSklad);  

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
