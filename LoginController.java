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
    Ucet prihlasenyU;

    public void Login(ActionEvent event) throws IOException, SQLException {
        UcetList = JDBMySQLConnection.getUcty();
        boolean podmnienka = true;

        for (int i = 0; i < UcetList.size(); i++) {
            if (txtId.getText().equals(String.valueOf(UcetList.get(i).getId())) && txtPassword.getText().equals(UcetList.get(i).getHeslo())) {
                // tu sa bude overovat uzivatel s DB
                Parent MainMenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                Scene MainMenuScene = new Scene(MainMenuParent);
                podmnienka = false;
                prihlasenyU = UcetList.get(i);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(MainMenuScene);
                window.show();
            }
        }

        if(podmnienka){
            // zobrazi sa ak das zle udaje
            txtLabel.setVisible(true);
        }
    }

    public Ucet getCurrentUcet(){
        return prihlasenyU;
    }
}
