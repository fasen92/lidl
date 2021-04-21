import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZoznamZamController implements Initializable {

    @FXML
    private TableView<Ucet> TabZamestnanci;

    @FXML
    private TableColumn<Ucet, String> ColumnId;

    @FXML
    private TableColumn<Ucet, String> ColumnMeno;

    @FXML
    private TableColumn<Ucet, String> ColumnPriezvisko;

    @FXML
    private TableColumn<Ucet, String> ColumnSklad;

    @FXML
    private TableColumn<Ucet, String> ColumnRola;

    @FXML
    private TableColumn<Ucet, Button> ColumnDetail;

    @FXML
    private Label LabelBack;

    @FXML
    private Button BtnRefresh;

    @FXML
    private Button BtnNovyZam;

    ObservableList<Ucet> UcetTable = FXCollections.observableArrayList();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void OnClickSpat(ActionEvent event) throws IOException {
        Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MenuScene = new Scene(MenuParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MenuScene);
        window.show();

    }

    @FXML
    void onClickNovyPouzivatel(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PridatZamDialog.fxml"));
        Parent parent = fxmlLoader.load();
        
        Scene scene = new Scene(parent, 350, 360);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        try {
            update_Table();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void OnClickRefresh(ActionEvent event) throws IOException, SQLException {

        update_Table();
    }

    public void update_Table() throws SQLException {

        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnMeno.setCellValueFactory(new PropertyValueFactory<>("meno"));
        ColumnPriezvisko.setCellValueFactory(new PropertyValueFactory<>("priezvisko"));
        ColumnSklad.setCellValueFactory(new PropertyValueFactory<>("sklad"));
        ColumnRola.setCellValueFactory(new PropertyValueFactory<>("rola"));
        ColumnDetail.setCellValueFactory(new PropertyValueFactory<>("button"));

        UcetTable = JDBMySQLConnection.getUctyTab();
        TabZamestnanci.setItems(UcetTable);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            update_Table();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
