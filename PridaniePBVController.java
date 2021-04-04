import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PridaniePBVController implements Initializable  {

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    private TextField TFTyp;

    @FXML
    private TextField TFNazov;

    @FXML
    private TextField TFPocet;

    @FXML
    private TextField TFSeriove;

    @FXML
    private DatePicker DFOdoslanienafili;

    @FXML
    private DatePicker DFZaruka;

    @FXML
    private TextField TAPoznamka;

    Pbv pbv;
    String zaruka;
    String datumodoslania;

    public String getVyberskladu(ChoiceBox<String> ChoiceBoxSklad){
        return ChoiceBoxSklad.getValue();
    }

    public void vytvoreniePBV(){
        datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
        zaruka = String.valueOf(DFZaruka.getValue());
        pbv = new Pbv(getVyberskladu(ChoiceBoxSklad),TFTyp.getText(),TFNazov.getText(),TFPocet.getText(), TFSeriove.getText(), datumodoslania,zaruka, TAPoznamka.getText());

        System.out.println(pbv.getDatum_odoslania()+" "+pbv.getSklad());
        
        System.out.println(getVyberskladu(ChoiceBoxSklad));
        System.out.println(TFTyp.getText());
        System.err.println(datumodoslania);
    }

    

    private void setup_Choiceboxs() {
        ObservableList <String> OLsklady = FXCollections.observableArrayList("Sklad1","Sklad2","Sklad3");
        ChoiceBoxSklad.setItems(OLsklady);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setup_Choiceboxs();
        
    }
    

}
