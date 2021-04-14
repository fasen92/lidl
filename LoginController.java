import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label txtLabel;

    @FXML
    private void zavriOkno(ActionEvent event) {
        System.exit(0);
    }

    ArrayList<Ucet> UcetList = new ArrayList<Ucet>();

    public void Login(ActionEvent event) throws IOException, SQLException {
        UcetList = JDBMySQLConnection.getUcty();
        boolean podmnienka = true;

        for (int i = 0; i < UcetList.size(); i++) {
            if (txtId.getText().equals(String.valueOf(UcetList.get(i).getId()))
                    && txtPassword.getText().equals(UcetList.get(i).getHeslo())) {
                podmnienka = false;
                if (UcetList.get(i).getPrvyLogin() == 1) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ZmenaHeslaDialog.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent, 350, 360);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                }
                Singleton x = Singleton.getInstance();
                x.setUcet(UcetList.get(i));
                Parent MainMenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                Scene MainMenuScene = new Scene(MainMenuParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(MainMenuScene);
                window.show();
            }
        }

        if (podmnienka) {
            // zobrazi sa ak das zle udaje
            txtLabel.setVisible(true);
        }
    }
}
