import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class MenuControler{

    @FXML
    private Button BtnKontrolaHW;

    @FXML
    private Button BtnReklamácia;

    @FXML
    private Button BtnZaznamakci;

    

    @FXML
    public void OnClickKontrolaHW(ActionEvent event)  throws IOException {
        Parent KontrolaHWParent = FXMLLoader.load(getClass().getResource("KontrolaHW.fxml"));
        Scene  KontrolaHWScene = new Scene(KontrolaHWParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(KontrolaHWScene);
        window.show();

    }

    @FXML
    void OnClickReklamacie(ActionEvent event) {

    }

    @FXML
    void OnClickZaznamAkci(ActionEvent event) {

    }

}
