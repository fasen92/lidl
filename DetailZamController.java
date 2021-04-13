import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DetailZamController implements Initializable {
    @FXML
    TextField txtMeno;

    @FXML
    TextField txtPriezvisko;

    @FXML
    ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    ChoiceBox<String> ChoiceBoxRola;

    @FXML
    Button BtnResetPass;

    @FXML
    Button BtnDeleteZam;

    @FXML
    Button BtnSave;

    @FXML
    Button BtnSpat;

    @FXML
    AnchorPane Apane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        SingletonDetailZam x = SingletonDetailZam.getInstance();
        txtMeno.setText(x.ucet.getMeno());
        txtPriezvisko.setText(x.ucet.getPriezvisko());
        setup_Choiceboxs(x.ucet.getRola(), x.ucet.getSklad());

    }

    public void setup_Choiceboxs(String rola, String sklad) {
        ObservableList<String> SkladList = FXCollections.observableArrayList("Sklad 1", "Sklad 2", "Sklad 3","Všetky");
        ObservableList<String> RolaList = FXCollections.observableArrayList("Admin", "Operating");

        ChoiceBoxSklad.setItems(SkladList);
        ChoiceBoxRola.setItems(RolaList);

        ChoiceBoxRola.setValue(rola);
        ChoiceBoxSklad.setValue(sklad);

    }

    @FXML
    void OnClickSave(ActionEvent event){
    SingletonDetailZam x = SingletonDetailZam.getInstance();
           
        
    }

    @FXML
    void OnClickDelete(ActionEvent event) throws SQLException, IOException{
        SingletonDetailZam x = SingletonDetailZam.getInstance();
        Stage stage = (Stage) Apane.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setHeaderText("Naozaj si prajete odstrániť zamestnanca "+x.ucet.getMeno()+" "+x.ucet.getPriezvisko()+"?");

        //prerobit
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Connection conn = JDBMySQLConnection.getConnection();
            String sql = "DELETE FROM `ucet` WHERE ID = ?";
            PreparedStatement pst;
            System.out.println(conn.prepareStatement(sql));
            pst = conn.prepareStatement(sql);
            pst.setInt(1, x.ucet.getId());
            pst.execute();
            spat(event);
        }else if(result.get() == ButtonType.CANCEL){

        }

    }

    @FXML
    void OnClickSpat(ActionEvent event) throws IOException {
       spat(event);
    }

    private void spat(ActionEvent event) throws IOException{
        Parent ZoznamZamParent = FXMLLoader.load(getClass().getResource("ZoznamZam.fxml"));
        Scene ZoznamZamScene = new Scene(ZoznamZamParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ZoznamZamScene);
        window.show();
    }
}
