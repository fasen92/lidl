import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePbvController  implements Initializable {
    
    
    String sklad = "Sklad1";
    String zariadenie = "Pbv";
    @FXML
    public TableView<Pbv> tabulka ;

    @FXML
    public TableColumn<Pbv, String> ColumTyp;

    @FXML
    public TableColumn<Pbv, String> ColumNazov;

    @FXML
    public TableColumn<Pbv, String> ColumPocet;

    @FXML
    public TableColumn<Pbv, String> ColumSC;

    @FXML
    public TableColumn<Pbv, String> ColumDatumodoslania;

    @FXML
    public TableColumn<Pbv, String> ColumZaruka;

    @FXML
    public TableColumn<Pbv, String> ColumPoznamka;

    

    static ObservableList <Pbv> OLtable = FXCollections.observableArrayList();

    
    


    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException{
        
        OLtable = JDBMySQLConnection.getPbv(choiceZariadenie,choiceSklad);  

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

           
        tabulka.setItems(OLtable);

    }
    /*public void initData(String zariadenie,String sklad) {
        this.sklad = sklad;
        System.out.println(this.sklad+"--------"+sklad);
        this.zariadenie = zariadenie;
        System.out.println(this.zariadenie+"--------"+zariadenie);

        try {
            update_Table(zariadenie, sklad);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //neni potreba ked vytvorime instance v HW controler a tak zavolame metody z tohto 
        //System.out.println("init TablePbvControler");
       
    
        
    }



   
}
