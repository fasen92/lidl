import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class DetailZamController {
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
    
}
