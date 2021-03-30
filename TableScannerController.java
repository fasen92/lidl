import java.net.URL;

import java.sql.SQLException;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableScannerController  implements Initializable  {

    @FXML
    private TableView<Scanner> tabulka;

    @FXML
    private TableColumn<Scanner, String> ColumTyp;

    @FXML
    private TableColumn<Scanner, String> ColumNazov;

    @FXML
    private TableColumn<Scanner, String> ColumPocet;

    @FXML
    private TableColumn<Scanner, String> ColumSC;

    @FXML
    private TableColumn<Scanner, String> ColumDatumodoslania;

    @FXML
    private TableColumn<Scanner, String> ColumZaruka;

    @FXML
    private TableColumn<Scanner, String> ColumPoznamka;

    ObservableList<Scanner> OLtable = FXCollections.observableArrayList();

    public static void start(){
        System.out.println("pre init");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


