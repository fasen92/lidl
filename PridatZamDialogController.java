import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PridatZamDialogController implements Initializable {

    @FXML
    TextField txtMeno;

    @FXML
    TextField txtPriezvisko;

    @FXML
    Button BtnPridat;

    @FXML
    Button BtnZrusit;

    @FXML
    ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    ChoiceBox<String> ChoiceBoxRola;

    @FXML
    void OnClickPridatZam(ActionEvent event) throws SQLException {
        String meno = String.valueOf(txtMeno.getText().trim());
        String priezvisko = String.valueOf(txtPriezvisko.getText().trim());
        String sklad = getVyberSkladu(ChoiceBoxSklad);
        String rola = getVyberRole(ChoiceBoxRola);
        ArrayList<Ucet> UcetList = JDBMySQLConnection.getUcty();
        String prihlasenie = priezvisko;

        for (int i = 0; i < UcetList.size(); i++) {
            if (prihlasenie.equals(UcetList.get(i).getPrihlasenie())) {

                for (int j = 0; j < UcetList.size(); j++) {
                    if (UcetList.get(j).getPrihlasenie().equals(priezvisko + String.valueOf(meno.charAt(0)).toUpperCase())) {
                        prihlasenie = priezvisko + String.valueOf(meno.charAt(0)).toUpperCase() + meno.substring(1, 3);
                        break;
                    } else {
                        prihlasenie = priezvisko + String.valueOf(meno.charAt(0)).toUpperCase();
                    }
                }

            }
        }

        if (meno == "") {
            JOptionPane.showMessageDialog(null, "Meno nie je vyplnené.");
        } else if (priezvisko == "") {
            JOptionPane.showMessageDialog(null, "Priezvisko nie je vyplnené.");
        } else if (sklad == "") {
            JOptionPane.showMessageDialog(null, "Nebol vybratý sklad.");
        } else if (rola == "") {
            JOptionPane.showMessageDialog(null, "Nebola Vybratá rola.");
        } else {
            JDBMySQLConnection.addtoUcet(meno, priezvisko, sklad, rola, prihlasenie);
        }

        closeStage(event);
    }

    @FXML
    void OnCLickZrusit(ActionEvent event) {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setup_Choiceboxs() {
        ObservableList<String> SkladList = FXCollections.observableArrayList("Sklad 1", "Sklad 2", "Sklad 3", "Všetky");
        ObservableList<String> RolaList = FXCollections.observableArrayList("Admin", "Operating", "Skladník");

        ChoiceBoxSklad.setItems(SkladList);
        ChoiceBoxRola.setItems(RolaList);

        ChoiceBoxRola.setValue("");
        ChoiceBoxSklad.setValue("");

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setup_Choiceboxs();

    }

    public String getVyberSkladu(ChoiceBox<String> ChoiceBoxSklad) {
        return ChoiceBoxSklad.getValue();
    }

    public String getVyberRole(ChoiceBox<String> ChoiceBoxRola) {
        return ChoiceBoxRola.getValue();
    }
}
