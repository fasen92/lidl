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

public class ReklamaciaController implements Initializable{

    @FXML
    private AnchorPane ap;

    @FXML
    private TableView<Zariadenie> tabulka;

    @FXML
    private TableColumn<Zariadenie, String> ColumnTyp;

    @FXML
    private TableColumn<Zariadenie, String> ColumnNazov;

    @FXML
    private TableColumn<Zariadenie, String> ColumnOpravacez;

    @FXML
    private TableColumn<Zariadenie, String> ColumnPoznamka;

    @FXML
    private TableColumn<Zariadenie, String> ColumnSklad;

    @FXML
    private Button BtnMenu;

    @FXML
    private Button BtnAktualizuj;

    ObservableList<Zariadenie> ZariadenieTable = FXCollections.observableArrayList();

    @FXML
    void OnClickAktualizuj(ActionEvent event) throws SQLException {
        update_Table();
    }

    @FXML
    void OnClickMenu(ActionEvent event) throws IOException {
        Singleton x = Singleton.getInstance();
        String ucet = x.ucet.getRola();

        if (ucet.equals("Admin")) {
            Stage primaryStage = (Stage) ap.getScene().getWindow();
            primaryStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = (Parent) loader.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("/images/LidlLogo.png"));

            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.setTitle("Asset Management");
            newStage.show();
        }
        
        if (ucet.equals("Operating") || ucet.equals("Skladník")) {
            Stage primaryStage = (Stage) ap.getScene().getWindow();
            primaryStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuZam.fxml"));
            Parent root = (Parent) loader.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("/images/LidlLogo.png"));

            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.setTitle("Asset Management");
            newStage.show();
        }
    }
    public void update_Table() throws SQLException {

        ColumnTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumnNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumnOpravacez.setCellValueFactory(new PropertyValueFactory<>("Opravacez"));
        ColumnPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));
        ColumnSklad.setCellValueFactory(new PropertyValueFactory<>("Sklad"));
       
       

        ZariadenieTable = JDBMySQLConnection.getOprava();
        
        tabulka.setItems(ZariadenieTable);

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
