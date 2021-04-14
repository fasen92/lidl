import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ZmenaHeslaDialogController {

    @FXML
    TextField txtHeslo;

    @FXML
    TextField txtHesloZnova;

    @FXML
    Button BtnSave;

    @FXML
    Button BtnZrusit;

    @FXML
    Label LabelChyba;
    
    @FXML
    void OnClickSave(ActionEvent event) throws SQLException{
        if(txtHeslo.getText() == "" || txtHesloZnova.getText() == ""){
            LabelChyba.setVisible(true);
            LabelChyba.setText("Treba vyplniť obidve polia");
        }
        else if(String.valueOf(txtHeslo.getText().trim()).equals(String.valueOf(txtHesloZnova.getText().trim()))){
            Connection conn = JDBMySQLConnection.getConnection();
            PreparedStatement ps = null;
            Singleton x = Singleton.getInstance();

            int value1 = x.ucet.getId();
            int value3 = 0;
            String value2 = txtHeslo.getText().trim();

            String sql = "UPDATE `ucet` SET `heslo`='" + value2 + "',`prvyLogin`='" + value3 + "' WHERE ID='" + value1 + "'";

            ps = conn.prepareStatement(sql);
            ps.execute();
            closeStage(event);
        }else{
            LabelChyba.setVisible(true);
            LabelChyba.setText("Heslá sa nezhodujú");
        }

    }

    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnClickZrusit(ActionEvent event){
        closeStage(event);
    }
    
}
