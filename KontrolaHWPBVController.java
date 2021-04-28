import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//Classa ktorá riadi zariadenia PBV
public class KontrolaHWPBVController implements Initializable {

    // Objekty ktoré sa viažu k scene .fxml

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane BP;

    @FXML
    private Button BtnVycisti;

    @FXML
    private Button BtnRefresh;

    @FXML
    private Button BtnMenu;

    @FXML
    private Button BtnUlož;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnZmeny;

    @FXML
    private Button BtnOprava;

    @FXML
    private ChoiceBox<String> ChoiceBoxTypzariadenia;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad;

    @FXML
    private ChoiceBox<String> ChoiceBoxSklad1;

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

    @FXML
    private TableView<Pbv> tabulka;

    @FXML
    private TableColumn<Pbv, String> ColumTyp;

    @FXML
    private TableColumn<String, Pbv> ColumNazov;

    @FXML
    private TableColumn<String, Pbv> ColumPocet;

    @FXML
    private TableColumn<String, Pbv> ColumSC;

    @FXML
    private TableColumn<String, Pbv> ColumDatumodoslania;

    @FXML
    private TableColumn<Pbv, String> ColumZaruka;

    @FXML
    private TableColumn<String, Pbv> ColumPoznamka;

    // Localne premenne

    Pbv pbv;
    String zaruka;
    String datumodoslania;
    String index = "";

    Alert alert = new Alert(AlertType.INFORMATION);

    static ObservableList<Pbv> OLtable = FXCollections.observableArrayList();
    static ObservableList<Pbv> OLgettable = FXCollections.observableArrayList();

    // funkcia aktualizuje cele okno pri zmenach vyberu a novych údajoch v DB

    @FXML
    void OnClickRefresh(ActionEvent event) throws SQLException, IOException {
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Pbv") {
            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));

        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Lispettore-scanner") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWScanner.fxml"));
            Scene scannerScene = new Scene(scannerParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
            // window.setMaximized(true);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "MDE") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWMDE.fxml"));
            Scene scannerScene = new Scene(scannerParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
            //window.setMaximized(true);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Quail") {
            Parent quailParent = FXMLLoader.load(getClass().getResource("KontrolaHWQuail.fxml"));
            Scene quailScene = new Scene(quailParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(quailScene);
            //window.setMaximized(true);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Rabattdrucker") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWRabattdrucker.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            //window.setMaximized(true);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Ostatné") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWOstatne.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            //window.setMaximized(true);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Moblný telefon") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWMP.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
           // window.setMaximized(true);
            window.show();
        }

    }

    // je funkcia aktivovana pri clicku na Menu
    // Zmeni okno na hl. menu

    @FXML
    void OnClickSpat(ActionEvent event) throws IOException {
        Singleton x = Singleton.getInstance();
        String ucet = x.ucet.getRola();

        if (ucet.equals("Admin")) {
            Stage primaryStage = (Stage) ap.getScene().getWindow();
            primaryStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = (Parent) loader.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("/images/LidlLogo.png"));

            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.setTitle("Asset Management");
            newStage.show();
        }
        
        if (ucet.equals("Operating") || ucet.equals("Skladník")) {
            Stage primaryStage = (Stage) ap.getScene().getWindow();
            primaryStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuZam.fxml"));
            Parent root = (Parent) loader.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("/images/LidlLogo.png"));

            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.setTitle("Asset Management");
            newStage.show();
        }

    }

    // Pokial som vyplnil atributy tato funkcia prida zariadenie do DB

    @FXML
    void OnClickUloz(ActionEvent event) throws SQLException {
        if (getVyberskladu(ChoiceBoxSklad).isEmpty() || TFTyp.getText().isEmpty() || TFNazov.getText().isEmpty()) {
            alert.setTitle("Information");
            alert.setContentText("Povynné polia sklad, typ alebo nazov nie sú vyplnené");
            alert.showAndWait();

        } else {
            datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
            zaruka = String.valueOf(DFZaruka.getValue());

            JDBMySQLConnection.addtoPbv(getVyberskladu(ChoiceBoxSklad1), TFTyp.getText(), TFNazov.getText(),
                    TFPocet.getText(), TFSeriove.getText(), datumodoslania, zaruka, TAPoznamka.getText());

            alert.setTitle("Information");
            alert.setContentText("Uspešne pridané");
            alert.showAndWait();

            vycisti();

            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
        }
    }

    // je funkcia ktora sa aktivuje pri clicknuti na vycisti a spusti funkciu
    // vycisti()

    @FXML
    void OnClickVycisti(ActionEvent event) {
        vycisti();

    }

    @FXML
    void OnClickDelete(ActionEvent event) throws SQLException {
        ObservableList<Pbv> pbv = tabulka.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Dbajte na rozhodnutí");
        alert.setContentText("Ste si istý že chcete vymazať?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                Connection conn = JDBMySQLConnection.getConnection();
                String sql = "DELETE FROM `pbv` WHERE ID = ?";
                PreparedStatement pst;
                pst = conn.prepareStatement(sql);
                pst.setString(1, pbv.get(0).getID());
                String Nazov = pbv.get(0).getNazov();
                pst.execute();
                tabulka.getItems().removeAll(tabulka.getSelectionModel().getSelectedItems());
                update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());

                Singleton x = Singleton.getInstance();

                String Meno = x.ucet.getMeno();
                System.out.println("Meno" + Meno);
                String Priezvisko = x.ucet.getPriezvisko();
                System.out.println("Priez" + Priezvisko);
                String Akcia = "Odstranene PBV -" + Nazov;
                System.out.println(Akcia);

                JDBMySQLConnection.addtoZazAkci(Meno, Priezvisko, Akcia);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nič nie je vybraté");
            }
        } else {

        }

    }

    // Pokial som z tabulky zvolil zariadenie a upravil jeho atribúty tato btn click
    // funkcia uozi zmeny
    @FXML
    void OnClickUlozZmeny(ActionEvent event) throws SQLException {

        if (getVyberskladu(ChoiceBoxSklad).isEmpty() || TFTyp.getText().isEmpty() || TFNazov.getText().isEmpty()) {
            alert.setTitle("Information");
            alert.setContentText("Povynné polia sklad, typ alebo nazov nie sú vyplnené");
            alert.showAndWait();

        } else {

            try {
                datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
                zaruka = String.valueOf(DFZaruka.getValue());

                Connection conn = JDBMySQLConnection.getConnection();
                PreparedStatement ps = null;
                String value2 = ChoiceBoxSklad1.getValue();
                String value3 = TFTyp.getText();
                String value4 = TFNazov.getText();
                String value5 = TFPocet.getText();
                String value6 = TFSeriove.getText();
                String value8 = DFOdoslanienafili.getValue().toString();
                String value9 = DFZaruka.getValue().toString();
                String value10 = TAPoznamka.getText();

                String sql = "UPDATE `pbv` SET `Sklad`='" + value2 + "',`Typ`='" + value3 + "',`Názov`='" + value4
                        + "',`Počet`='" + value5 + "',`Sériové číslo`='" + value6 + "',`Dátum odoslania fili`='"
                        + value8 + "',`Záruka`='" + value9 + "',`Poznámka`='" + value10 + "' WHERE ID='" + index + "'";

                ps = conn.prepareStatement(sql);
                ps.execute();

                JOptionPane.showMessageDialog(null, "Uspese upravene");
                vycisti();

                update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "nikde nastala chyba");
            }

        }

    }

    // Funkcia sa spusta ked kliknem na miesto v tabulke a pokial sa kliklo na
    // zariadenie toto zariadenie
    // a jeho atributy sa zobrazia na lavo v textových poliach
    @FXML
    void getSelected(MouseEvent event) {

        try {
            Pbv pbv = tabulka.getSelectionModel().getSelectedItem();
            LocalDate localDate1 = LocalDate.parse(pbv.getDatum_odoslania());
            LocalDate localDate2 = LocalDate.parse(pbv.getZaruka());
            ChoiceBoxSklad1.setValue(getVyberskladu(ChoiceBoxSklad));
            TFNazov.setText(pbv.getNazov());
            TFTyp.setText(pbv.getTyp());
            TFPocet.setText(pbv.getPocet());
            TAPoznamka.setText(pbv.getPoznamka());
            TFSeriove.setText(pbv.getSC());
            DFOdoslanienafili.setValue(localDate1);
            DFZaruka.setValue(localDate2);
            index = pbv.getID();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // je samotna funkcia ktora vycisti vsetky textove polia

    private void vycisti() {
        TFNazov.clear();
        TFTyp.clear();
        TFPocet.clear();
        TFSeriove.clear();
        TAPoznamka.clear();
        DFOdoslanienafili.setValue(null);
        DFZaruka.setValue(null);
        TAPoznamka.clear();
    }

    // funkcia mi vrati aktualne zvoleny sklad

    public String getVyberskladu(ChoiceBox<String> ChoiceBoxSklad) {
        return ChoiceBoxSklad.getValue();
    }

    // funkcia mi vrati aktualne zvolene zariadenie

    public String getVyberZariadenia(ChoiceBox<String> ChoiceBoxTypzariadenia) {
        return ChoiceBoxTypzariadenia.getValue();
    }

    // pri nacitanie okna so zariadenim s nacitaju sklady a zariadenia do vyberovych
    // boxov na vrchu tabulky

    public void setup_Choiceboxs() {
        ObservableList<String> OLsklady = FXCollections.observableArrayList("Sklad1", "Sklad2", "Sklad3");
        ObservableList<String> OLsklady3 = FXCollections.observableArrayList("Sklad3");
        ObservableList<String> OLsklady2 = FXCollections.observableArrayList("Sklad2");
        ObservableList<String> OLsklady1 = FXCollections.observableArrayList("Sklad1");
        ObservableList<String> OLzariadenia = FXCollections.observableArrayList("Pbv", "Lispettore-scanner", "MDE",
                "Rabattdrucker", "Quail", "Moblný telefon", "Ostatné");

        Singleton x = Singleton.getInstance();
        ChoiceBoxTypzariadenia.setValue("Pbv");
        if (String.valueOf(x.ucet.getRola()).equals("Admin")) {
            ChoiceBoxSklad.setItems(OLsklady);
            ChoiceBoxTypzariadenia.setItems(OLzariadenia);
            ChoiceBoxSklad1.setItems(OLsklady);

            ChoiceBoxSklad.setValue("Sklad1");
            ChoiceBoxSklad1.setValue("Sklad1");
        }
        if (String.valueOf(x.ucet.getSklad()).equals("Sklad 1")) {
            ChoiceBoxSklad.setItems(OLsklady1);
            ChoiceBoxTypzariadenia.setItems(OLzariadenia);
            ChoiceBoxSklad1.setItems(OLsklady1);

            ChoiceBoxSklad.setValue("Sklad1");
            ChoiceBoxSklad1.setValue("Sklad1");
        }
        if (String.valueOf(x.ucet.getSklad()).equals("Sklad 2")) {
            ChoiceBoxSklad.setItems(OLsklady2);
            ChoiceBoxTypzariadenia.setItems(OLzariadenia);
            ChoiceBoxSklad1.setItems(OLsklady2);

            ChoiceBoxSklad.setValue("Sklad2");
            ChoiceBoxSklad1.setValue("Sklad2");
        }
        if (String.valueOf(x.ucet.getSklad()).equals("Sklad 3")) {
            ChoiceBoxSklad.setItems(OLsklady3);
            ChoiceBoxTypzariadenia.setItems(OLzariadenia);
            ChoiceBoxSklad1.setItems(OLsklady3);

            ChoiceBoxSklad.setValue("Sklad3");
            ChoiceBoxSklad1.setValue("Sklad3");
        }

    }

    // funkcia priamo vklada udaje z DB do samotneho objektu tabuky a konkretnych
    // stlpcov

    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException {

        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Pbv") {
            OLtable = JDBMySQLConnection.getPbv(choiceZariadenie, choiceSklad);

            ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
            ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
            ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
            ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
            ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
            ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
            ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

            tabulka.setItems(OLtable);

            customiseFactory(ColumZaruka);

        }

    }

    // tato funkcia zisti ci su zariadenia po zaruke a ak su tak ich podfarbí
    // červenou

    private void customiseFactory(TableColumn<Pbv, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<Pbv, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);
                    TableRow<Pbv> currentRow = getTableRow();
                    if (!isEmpty()) {

                        LocalDate date = LocalDate.parse(item);
                        if (date.compareTo(LocalDate.now()) <= 0)
                            currentRow.setStyle("-fx-background-color: #CD1C24");

                    }

                }
            };
        });
    }

    // tato funkcia otvori dialog s opravou

    @FXML
    void OnClickOprava(ActionEvent event) throws IOException, SQLException {
        try {

            ObservableList<Pbv> pbv = tabulka.getSelectionModel().getSelectedItems();
            String TAtxt = "", Opravacez = "", ID = "";

            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpravaScene.fxml"));
            Parent root = (Parent) loader.load();
            OpravaSceneController opravaScene = loader.getController();

            TAtxt = pbv.get(0).getZazcinnosti();
            ID = pbv.get(0).getID();
            Opravacez = pbv.get(0).getOpravacez();

            opravaScene.initData(ID, TAtxt, Opravacez, ChoiceBoxTypzariadenia.getValue());
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.showAndWait();

            update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());
        } catch (Exception e) {

        }

    }

    // je funkcia ktora sa vzdy spusta ked sa otvara dany stage

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setup_Choiceboxs();

        try {
            update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
