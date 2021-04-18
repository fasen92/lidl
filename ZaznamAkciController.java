import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ZaznamAkciController implements Initializable {
    
    @FXML
    private AnchorPane ap;

    @FXML
    private TableView<Osoba> tabulka;

    @FXML
    private TableColumn<Osoba, String> ColumnID;

    @FXML
    private TableColumn<Osoba, String> ColumnMeno;

    @FXML
    private TableColumn<Osoba, String> ColumnPriezvisko;

    @FXML
    private TableColumn<Osoba, String> ColumnDatum;

    @FXML
    private TableColumn<Osoba, String> ColumnAkcia;

    @FXML
    private Button BtnMenu;
    
    @FXML
    private Button BtnAktualizuj;

    ObservableList<Osoba> OsobaTable = FXCollections.observableArrayList();

    @FXML
    void OnClickMenu(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ap.getScene().getWindow();
        primaryStage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = (Parent) loader.load();
        Scene newScene = new Scene(root);    
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/images/LidlLogo.png"));
        
        newStage.setScene(newScene);
        newStage.setTitle("Asset Management");
        newStage.show();
    
    }
    @FXML
    void OnClickAktualizuj(ActionEvent event) throws SQLException {
        update_Table();
    }

    public void update_Table() throws SQLException {

        ColumnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ColumnMeno.setCellValueFactory(new PropertyValueFactory<>("Meno"));
        ColumnPriezvisko.setCellValueFactory(new PropertyValueFactory<>("Priezvisko"));
        ColumnDatum.setCellValueFactory(new PropertyValueFactory<>("Datum"));
        ColumnAkcia.setCellValueFactory(new PropertyValueFactory<>("Akcia"));
       
        System.out.println("1");

        OsobaTable = JDBMySQLConnection.getUctyZazAkci();
        System.out.println("5");
        tabulka.setItems(OsobaTable);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            update_Table();
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }


}
