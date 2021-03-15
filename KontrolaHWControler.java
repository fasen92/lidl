import java.beans.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
    
    Connection connection = null;

    Statement statement = null;

    ResultSet rs = null;

    @FXML
    private TableView<Pbv> tabulka;

    @FXML
    private TableColumn<Pbv, String> ColumSeriovecislo;

    @FXML
    private TableColumn<Pbv, String> ColumNazov;

    @FXML
    private TableColumn<Pbv, String> ColumZaruka;

    @FXML
    private TableColumn<Pbv, String> ColumDatumprijatia;

    @FXML
    private TableColumn<Pbv, String> ColumDatumodoslania;

    @FXML
    private TableColumn<Pbv, String> ColumMiestopouzivania;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    private ChoiceBox<String> ChoiceBoxTypzariadenia;
    @FXML
    private Button Btnspat;

    ObservableList <String> OLsklady = FXCollections.observableArrayList("Sklad 1","Sklad 2","Sklad 3");
    ObservableList <String> OLzariadenia = FXCollections.observableArrayList("Pbv","Lispettore scanner","MDE","Rabattdrucker","Quail");

    ObservableList <Pbv> OLtable = FXCollections.observableArrayList();
    

    @FXML
    void OnClickSpat(ActionEvent event) throws IOException {
        Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MenuScene = new Scene(MenuParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MenuScene);
        window.show();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBoxSklad.setItems(OLsklady);
        ChoiceBoxTypzariadenia.setItems(OLzariadenia);

        try {
            Connection con = JDBMySQLConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `pbv-ibahw`");

            while (rs.next()) {
                OLtable.add(new Pbv(rs.getString("Sériové číslo"),rs.getString("Názov"),rs.getString("Záruka"),rs.getString("Dátum prijatia"),rs.getString("Dátum odoslania"),rs.getString("Miesto používania")));
            }

            ColumSeriovecislo.setCellValueFactory(new PropertyValueFactory<>("Sc"));
            ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
            ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
            ColumDatumprijatia.setCellValueFactory(new PropertyValueFactory<>("Datum_prijatia"));
            ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
            ColumMiestopouzivania.setCellValueFactory(new PropertyValueFactory<>("Miesto_pouzivania"));

            tabulka.setItems(OLtable);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       

        
        
        
    }


     

}
