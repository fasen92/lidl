import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.print.DocFlavor.STRING;
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

public class KontrolaHWRabattdruckerController implements Initializable {

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
    private TextField TFECO;

    @FXML
    private TextField TFCF;

    @FXML
    private DatePicker DFOdoslanienafili;

    @FXML
    private DatePicker DFZaruka;

    @FXML
    private TextField TAPoznamka;

    @FXML
    private TableView<Rabattdrucker> tabulka;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumTyp;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumNazov;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumPocet;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumSC;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumECO;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumCF;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumDatumodoslania;

    @FXML
    private TableColumn<String, Rabattdrucker> ColumPoznamka;

    @FXML
    private TableColumn<Rabattdrucker, String> ColumZaruka;

    String zaruka;
    String datumodoslania;

    String index = "";

    Alert alert = new Alert(AlertType.INFORMATION);

    static ObservableList<Rabattdrucker> OLtable = FXCollections.observableArrayList();

    @FXML
    void OnClickRefresh(ActionEvent event) throws IOException, SQLException {
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Pbv") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWPBV.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Lispettore-scanner") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWScanner.fxml"));
            Scene scannerScene = new Scene(scannerParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "MDE") {
            Parent scannerParent = FXMLLoader.load(getClass().getResource("KontrolaHWMDE.fxml"));
            Scene scannerScene = new Scene(scannerParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scannerScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Quail") {
            Parent quailParent = FXMLLoader.load(getClass().getResource("KontrolaHWQuail.fxml"));
            Scene quailScene = new Scene(quailParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(quailScene);
            window.setMaximized(true);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Rabattdrucker") {

            update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Ostatné") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWOstatne.fxml"));
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

        if (ucet.equals("Operating") || ucet.equals("Skladnik")) {
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
        if (getVyberskladu(ChoiceBoxSklad).isEmpty() || TFTyp.getText().isEmpty() || TFNazov.getText().isEmpty()) {
            alert.setTitle("Information");
            alert.setContentText("Povynné polia sklad, typ alebo nazov nie sú vyplnené");
            alert.showAndWait();

        } else {
            datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
            zaruka = String.valueOf(DFZaruka.getValue());

            JDBMySQLConnection.addtoRabattdrucker(getVyberskladu(ChoiceBoxSklad1), TFTyp.getText(), TFNazov.getText(),
                    TFPocet.getText(), TFSeriove.getText(), TFECO.getText(), TFCF.getText(), datumodoslania, zaruka,
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
        ObservableList<Rabattdrucker> rbt = tabulka.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Dbajte na rozhodnutí");
        alert.setContentText("Ste si istý že chcete vymazať?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                System.out.println(rbt.get(0).getID());

                Connection conn = JDBMySQLConnection.getConnection();
                String sql = "DELETE FROM `rabattdrucker` WHERE ID = ?";
                PreparedStatement pst;
                pst = conn.prepareStatement(sql);
                pst.setString(1, rbt.get(0).getID());
                pst.execute();
                tabulka.getItems().removeAll(tabulka.getSelectionModel().getSelectedItems());
                update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());
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

                String value7 = TFECO.getText();
                String value8 = TFCF.getText();

                String value10 = DFOdoslanienafili.getValue().toString();
                String value11 = DFZaruka.getValue().toString();
                String value12 = TAPoznamka.getText();

                String sql = "UPDATE `rabattdrucker` SET `Sklad`='" + value2 + "',`Typ`='" + value3 + "',`Názov`='"
                        + value4 + "',`Počet`='" + value5 + "',`Sériové číslo`='" + value6 + "',`Evidencne cislo`='"
                        + value7 + "',`Cislo fili`='" + value8 + "',`Dátum odoslania fili`='" + value10 + "',`Záruka`='"
                        + value11 + "',`Poznámka`='" + value12 + "' WHERE ID='" + index + "'";

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
            Rabattdrucker rbt = tabulka.getSelectionModel().getSelectedItem();
            System.out.println(rbt.getDatum_odoslania());
            LocalDate localDate1 = LocalDate.parse(rbt.getDatum_odoslania());
            LocalDate localDate2 = LocalDate.parse(rbt.getZaruka());
            ChoiceBoxSklad1.setValue(getVyberZariadenia(ChoiceBoxTypzariadenia));
            TFNazov.setText(rbt.getNazov());
            TFTyp.setText(rbt.getTyp());
            TFPocet.setText(rbt.getPocet());
            TAPoznamka.setText(rbt.getPoznamka());
            TFCF.setText(rbt.getCF());
            TFECO.setText(rbt.getECO());
            TFSeriove.setText(rbt.getSC());
            DFOdoslanienafili.setValue(localDate1);
            DFZaruka.setValue(localDate2);
            index = rbt.getID();
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
        TFECO.clear();
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
        ChoiceBoxTypzariadenia.setValue("Rabattdrucker");
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

        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Rabattdrucker") {
            OLtable = JDBMySQLConnection.getRabattdrucker(choiceZariadenie, choiceSklad);

            ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
            ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
            ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
            ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
            ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
            ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
            ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
            ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));
            ColumECO.setCellValueFactory(new PropertyValueFactory<>("ECO"));

            tabulka.setItems(OLtable);

            customiseFactory(ColumZaruka);
        }

    }

    private void customiseFactory(TableColumn<Rabattdrucker, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<Rabattdrucker, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);
                    TableRow<Rabattdrucker> currentRow = getTableRow();
                    if (!isEmpty()) {

                        LocalDate date = LocalDate.parse(item);
                        if (date.compareTo(LocalDate.now()) <= 0)
                            currentRow.setStyle("-fx-background-color: #CD1C24");

                    }

                }
            };
        });
    }
    @FXML
    void OnClickOprava(ActionEvent event) throws IOException, SQLException {
        try {

            ObservableList<Rabattdrucker> rabat = tabulka.getSelectionModel().getSelectedItems();
            String TAtxt = "", Opravacez = "", ID = "";

            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpravaScene.fxml"));
            Parent root = (Parent) loader.load();
            OpravaSceneController opravaScene = loader.getController();

            TAtxt = rabat.get(0).getZazcinnosti();
            ID = rabat.get(0).getID();
            Opravacez = rabat.get(0).getOpravacez();

            opravaScene.initData(ID, TAtxt, Opravacez,ChoiceBoxTypzariadenia.getValue());
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.showAndWait();

            update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());
        } catch (Exception e) {

        }

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
