import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Node;

public class OpravaSceneController implements Initializable {

    @FXML
    private ChoiceBox<String> ChoiceBox;

    @FXML
    private TextArea TAOprava;

    @FXML
    private Button BtnUlozit;

    @FXML
    private Button BtnZrusit;

    String ID = "";
    String Zariadenie = "";

    @FXML
    void OnClickUlozit(ActionEvent event) throws SQLException, IOException {
        String sql = "";
        Connection conn = JDBMySQLConnection.getConnection();
        PreparedStatement ps = null;
        String value1 = TAOprava.getText();
        String value2 = ChoiceBox.getValue();
        if (Zariadenie == "Pbv") {
             sql= "UPDATE `pbv` SET `Záznam činnosti`='" + value1 + "',`Oprava cez`='" + value2 + "'WHERE ID='" + ID
                + "'";
        }
        if (Zariadenie == "Lispettore-scanner") {
            sql= "UPDATE `lispettore-scanner` SET `Záznam činnosti`='" + value1 + "',`Oprava cez`='" + value2 + "'WHERE ID='" + ID
               + "'";
        }
        if (Zariadenie == "Quail") {
            sql= "UPDATE `quail` SET `Záznam činnosti`='" + value1 + "',`Oprava cez`='" + value2 + "'WHERE ID='" + ID
               + "'";
        }
        if (Zariadenie == "MDE") {
            sql= "UPDATE `mde` SET `Záznam činnosti`='" + value1 + "',`Oprava cez`='" + value2 + "'WHERE ID='" + ID
               + "'";
        }
        if (Zariadenie == "Rabattdrucker") {
            sql= "UPDATE `rabattdrucker` SET `Záznam činnosti`='" + value1 + "',`Oprava cez`='" + value2 + "'WHERE ID='" + ID
               + "'";
        }
        if (Zariadenie == "Moblný telefon") {
            sql= "UPDATE `mobil-prepredajnu` SET `Záznam činnosti`='" + value1 + "',`Oprava cez`='" + value2 + "'WHERE ID='" + ID
               + "'";
        }
        if (Zariadenie == "Ostatné") {
            sql= "UPDATE `ostatne` SET `Záznam činnosti`='" + value1 + "',`Oprava cez`='" + value2 + "'WHERE ID='" + ID
               + "'";
        }
        
        ps = conn.prepareStatement(sql);
        ps.execute();

        JOptionPane.showMessageDialog(null, "Uspese upravene");

        closeStage(event);
    }

    @FXML
    void OnClickZrusit(ActionEvent event) {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }

    public void initData(String ID, String TAtxt, String Opravacez,String Zariadenie) {
        if (Opravacez == null) {
            ChoiceBox.setValue("Vporiadku");
        }else{
            ChoiceBox.setValue(Opravacez);}
        TAOprava.setText(TAtxt);
        this.ID = ID;
        this.Zariadenie = Zariadenie;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setup_Choiceboxs();

    }

    private void setup_Choiceboxs() {
        ObservableList<String> OLsklady = FXCollections.observableArrayList("Vporiadku", "RMA portal",
                "dodávateľa poštou", "centrála");
        ChoiceBox.setItems(OLsklady);

    }
}