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

public class KontrolaHWOstatneController implements Initializable {

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
    private TextField TFCF;

    @FXML
    private DatePicker DFOdoslanienafili;

    @FXML
    private DatePicker DFZaruka;

    @FXML
    private TextField TAPoznamka;

    @FXML
    private TableView<Ostatne> tabulka;

    @FXML
    private TableColumn<String, Ostatne> ColumTyp;

    @FXML
    private TableColumn<String, Ostatne> ColumNazov;

    @FXML
    private TableColumn<String, Ostatne> ColumPocet;

    @FXML
    private TableColumn<String, Ostatne> ColumSC;

    @FXML
    private TableColumn<String, Ostatne> ColumCF;

    @FXML
    private TableColumn<String, Ostatne> ColumDatumodoslania;

    @FXML
    private TableColumn<Ostatne, String> ColumZaruka;

    @FXML
    private TableColumn<String, Ostatne> ColumPoznamka;

    String zaruka;
    String datumodoslania;
    String index = "";

    Alert alert = new Alert(AlertType.INFORMATION);
    ObservableList<Ostatne> OLtable = FXCollections.observableArrayList();

    @FXML
    void OnClickRefresh(ActionEvent event) throws IOException, SQLException {
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Ostatné") {
            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Lispettore-scanner") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWScanner.fxml"));
            Scene scannerScene = new Scene(scannerParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Pbv") {
            Parent pbvParent = FXMLLoader.load(getClass().getResource("KontrolaHWPBV.fxml"));
            Scene pbvScene = new Scene(pbvParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(pbvScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "MDE") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWMDE.fxml"));
            Scene scannerScene = new Scene(scannerParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Rabattdrucker") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWRabattdrucker.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Quail") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWQuail.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Moblný telefon") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWMP.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
        }
    }

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

    @FXML
    void OnClickUloz(ActionEvent event) throws SQLException {
        if (getVyberskladu(ChoiceBoxSklad1).isEmpty() || TFTyp.getText().isEmpty() || TFNazov.getText().isEmpty()) {
            alert.setTitle("Information");
            alert.setContentText("Povynné polia sklad, typ alebo nazov nie sú vyplnené");
            alert.showAndWait();

        } else {
            datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
            zaruka = String.valueOf(DFZaruka.getValue());

            JDBMySQLConnection.addtoOstatne(getVyberskladu(ChoiceBoxSklad1), TFTyp.getText(), TFNazov.getText(),
                    TFPocet.getText(), TFSeriove.getText(), TFCF.getText(), datumodoslania, zaruka,
                    TAPoznamka.getText());

            alert.setTitle("Information");
            alert.setContentText("Uspešne pridané");
            alert.showAndWait();

            vycisti();

            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
        }
    }

    @FXML
    void OnClickVycisti(ActionEvent event) {
        vycisti();
    }

    @FXML
    void OnClickDelete(ActionEvent event) throws SQLException {
        ObservableList<Ostatne> ostatne = tabulka.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Dbajte na rozhodnutí");
        alert.setContentText("Ste si istý že chcete vymazať?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                System.out.println(ostatne.get(0).getID());

                Connection conn = JDBMySQLConnection.getConnection();
                String sql = "DELETE FROM `ostatne` WHERE ID = ?";
                PreparedStatement pst;
                pst = conn.prepareStatement(sql);
                pst.setString(1, ostatne.get(0).getID());
                String Nazov = ostatne.get(0).getNazov();
                pst.execute();
                tabulka.getItems().removeAll(tabulka.getSelectionModel().getSelectedItems());
                update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());

                Singleton x = Singleton.getInstance();

                String Meno = x.ucet.getMeno();
                System.out.println("Meno" + Meno);
                String Priezvisko = x.ucet.getPriezvisko();
                System.out.println("Priez" + Priezvisko);
                String Akcia = "Odstranene Ostatne -" + Nazov;
                System.out.println(Akcia);

                JDBMySQLConnection.addtoZazAkci(Meno, Priezvisko, Akcia);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nič nie je vybraté");
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    // aj toto
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

                String value7 = TFCF.getText();

                String value8 = DFOdoslanienafili.getValue().toString();
                String value10 = DFZaruka.getValue().toString();
                String value11 = TAPoznamka.getText();

                String sql = "UPDATE `ostatne` SET `Sklad`='" + value2 + "',`Typ`='" + value3 + "',`Názov`='" + value4
                        + "',`Počet`='" + value5 + "',`Sériové číslo`='" + value6 + "',`Cislo fili`='" + value7
                        + "',`Dátum odoslania fili`='" + value8 + "',`Záruka`='" + value10 + "',`Poznámka`='" + value11
                        + "' WHERE ID='" + index + "'";

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

    // zatial napic
    @FXML
    void getSelected(MouseEvent event) {
        try {
            Ostatne ostatne = tabulka.getSelectionModel().getSelectedItem();
            System.out.println(ostatne.getDatum_odoslania());
            LocalDate localDate1 = LocalDate.parse(ostatne.getDatum_odoslania());
            LocalDate localDate2 = LocalDate.parse(ostatne.getZaruka());
            ChoiceBoxSklad1.setValue(getVyberskladu(ChoiceBoxSklad));
            TFNazov.setText(ostatne.getNazov());
            TFTyp.setText(ostatne.getTyp());
            TFPocet.setText(ostatne.getPocet());
            TAPoznamka.setText(ostatne.getPoznamka());
            TFCF.setText(ostatne.getCF());
            TFSeriove.setText(ostatne.getSC());
            DFOdoslanienafili.setValue(localDate1);
            DFZaruka.setValue(localDate2);
            index = ostatne.getID();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private void vycisti() {
        TFNazov.clear();
        TFTyp.clear();
        TFPocet.clear();
        TFSeriove.clear();
        TAPoznamka.clear();
        DFOdoslanienafili.setValue(null);
        DFZaruka.setValue(null);
        TAPoznamka.clear();
        TFCF.clear();
    }
    @FXML
    void OnClickOprava(ActionEvent event) throws IOException, SQLException {
        try {

            ObservableList<Ostatne> ostatne = tabulka.getSelectionModel().getSelectedItems();
            String TAtxt = "", Opravacez = "", ID = "";

            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpravaScene.fxml"));
            Parent root = (Parent) loader.load();
            OpravaSceneController opravaScene = loader.getController();

            TAtxt = ostatne.get(0).getZazcinnosti();
            ID = ostatne.get(0).getID();
            Opravacez = ostatne.get(0).getOpravacez();

            opravaScene.initData(ID, TAtxt, Opravacez,ChoiceBoxTypzariadenia.getValue());
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.showAndWait();

            update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());
        } catch (Exception e) {

        }

    }

    public String getVyberskladu(ChoiceBox<String> ChoiceBoxSklad) {
        return ChoiceBoxSklad.getValue();
    }

    public String getVyberZariadenia(ChoiceBox<String> ChoiceBoxTypzariadenia) {
        return ChoiceBoxTypzariadenia.getValue();
    }

    public void setup_Choiceboxs() {
        ObservableList<String> OLsklady = FXCollections.observableArrayList("Sklad1", "Sklad2", "Sklad3");
        ObservableList<String> OLsklady3 = FXCollections.observableArrayList("Sklad3");
        ObservableList<String> OLsklady2 = FXCollections.observableArrayList("Sklad2");
        ObservableList<String> OLsklady1 = FXCollections.observableArrayList("Sklad1");
        ObservableList<String> OLzariadenia = FXCollections.observableArrayList("Pbv", "Lispettore-scanner", "MDE",
                "Rabattdrucker", "Quail", "Moblný telefon", "Ostatné");

        Singleton x = Singleton.getInstance();
        ChoiceBoxTypzariadenia.setValue("Ostatné");
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

    public void update_Table(String choiceZariadenie, String choiceSklad) throws SQLException {

        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Ostatné") {
            OLtable = JDBMySQLConnection.getOstatne(choiceZariadenie, choiceSklad);

            ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
            ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
            ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
            ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
            ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
            ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
            ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
            ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

            tabulka.setItems(OLtable);

            customiseFactory(ColumZaruka);
        }

    }

    private void customiseFactory(TableColumn<Ostatne, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<Ostatne, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);
                    TableRow<Ostatne> currentRow = getTableRow();
                    if (!isEmpty()) {

                        LocalDate date = LocalDate.parse(item);
                        if (date.compareTo(LocalDate.now()) <= 0)
                            currentRow.setStyle("-fx-background-color: #CD1C24");

                    }

                }
            };
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("vytvorenie pozadia");
        setup_Choiceboxs();

        try {
            System.out.println(ChoiceBoxSklad.getValue());
            update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
