import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;

public class MenuController implements Initializable{

    @FXML
    private Button BtnKontrolaHW;

    @FXML
    private Button BtnReklamácia;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button BtnZaznamakci;

    @FXML
    private Button BtnZamestnanci;

    @FXML
    private Button BtnSignOut;

    @FXML
    private ImageView img1;

    Ucet ucet;
    
    

    @FXML
    public void OnClickKontrolaHW(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ap.getScene().getWindow();
        primaryStage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("KontrolaHWPBV.fxml"));
        Parent root = (Parent) loader.load();
        Scene newScene = new Scene(root);    
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/images/LidlLogo.png"));
        
        newStage.setScene(newScene);
        newStage.setTitle("Asset Management HW");
        newStage.show();
        
    }

    @FXML
    void OnClickSignOut(ActionEvent event) throws IOException{
        Parent LoginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene LoginScene = new Scene(LoginParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(LoginScene);
        window.show();
    }

    @FXML
    void OnClickReklamacie(ActionEvent event) {

    }

    @FXML
    void OnClickZaznamAkci(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ap.getScene().getWindow();
        primaryStage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZaznamAkci.fxml"));
        Parent root = (Parent) loader.load();
        Scene newScene = new Scene(root);    
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/images/LidlLogo.png"));
        
        newStage.setScene(newScene);
        newStage.setTitle("Záznam akcí");
        newStage.show();
        
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
        if(String.valueOf(x.ucet.getRola()).equals("Admin")){
            BtnZamestnanci.setVisible(true);
        }else{
            BtnZamestnanci.setVisible(false);
            BtnZamestnanci.setStyle("display:none");
        }
        
    }

}