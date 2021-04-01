import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuController implements Initializable{

    @FXML
    private Button BtnKontrolaHW;

    @FXML
    private Button BtnReklamácia;

    @FXML
    private Button BtnZaznamakci;

    @FXML
    private Button BtnZamestnanci;

    Ucet ucet;
    
    

    @FXML
    public void OnClickKontrolaHW(ActionEvent event) throws IOException {
        Parent KontrolaHWParent = FXMLLoader.load(getClass().getResource("KontrolaHW.fxml"));
        Scene KontrolaHWScene = new Scene(KontrolaHWParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(KontrolaHWScene);
        window.show();

    }

    @FXML
    void OnClickReklamacie(ActionEvent event) {

    }

    @FXML
    void OnClickZaznamAkci(ActionEvent event) {

    }

    @FXML
    void OnClickZamestnanci(ActionEvent event) throws IOException {
        Parent ZoznamZamParent = FXMLLoader.load(getClass().getResource("ZoznamZam.fxml"));
        Scene ZoznamZamScene = new Scene(ZoznamZamParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ZoznamZamScene);
        window.show();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Singleton x = Singleton.getInstance();
        System.out.println(x.ucet.getRola());
        if(String.valueOf(x.ucet.getRola()).equals("Admin")){
            BtnZamestnanci.setVisible(true);
        }else{
            BtnZamestnanci.setVisible(false);
        }

        
    }

}