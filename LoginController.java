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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane ap;

    @FXML
    private void zavriOkno(ActionEvent event) {
        System.exit(0);
    }

    ArrayList<Ucet> UcetList = new ArrayList<Ucet>();

    public void Login(ActionEvent event) throws IOException, SQLException {
        UcetList = JDBMySQLConnection.getUcty();
        boolean podmnienka = true;

        for (int i = 0; i < UcetList.size(); i++) {
            if (txtId.getText().equals(String.valueOf(UcetList.get(i).getPrihlasenie()))
                    && txtPassword.getText().equals(UcetList.get(i).getHeslo())) {
                podmnienka = false;

                Singleton x = Singleton.getInstance();
                x.setUcet(UcetList.get(i));
                if (UcetList.get(i).getPrvyLogin() == 1) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ZmenaHeslaDialog.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent, 350, 360);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                }
                if (String.valueOf(UcetList.get(i).getRola()).equals("Admin")) {
                    Stage primaryStage = (Stage) ap.getScene().getWindow();
                    primaryStage.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                    Parent root = (Parent) loader.load();
                    Scene ZoznamZamScene = new Scene(root);
                    Stage newStage = new Stage();
                    newStage.setResizable(false);
                    newStage.getIcons().add(new Image("/images/LidlLogo.png"));

                    newStage.setScene(ZoznamZamScene);
                    newStage.setTitle("Asset Management Menu");
                    newStage.show();
                } else {
                    Stage primaryStage = (Stage) ap.getScene().getWindow();
                    primaryStage.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuZam.fxml"));
                    Parent root = (Parent) loader.load();
                    Scene ZoznamZamScene = new Scene(root);
                    Stage newStage = new Stage();
                    newStage.setResizable(false);
                    newStage.getIcons().add(new Image("/images/LidlLogo.png"));

                    newStage.setScene(ZoznamZamScene);
                    newStage.setTitle("Asset Management Menu");
                    newStage.show();
                }

            }
        }

        if (podmnienka) {
            // zobrazi sa ak das zle udaje
            txtLabel.setVisible(true);
        }
    }
}
