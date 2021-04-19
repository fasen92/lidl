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
import javafx.scene.control.CheckBox;
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

public class KontrolaHWMDEController implements Initializable {

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
    private TextField TFMAC;

    @FXML
    private TextField TFIP;

    @FXML
    private CheckBox CheckboxWifi;

    @FXML
    private TextField TFCF;

    @FXML
    private DatePicker DFOdoslanienafili;

    @FXML
    private DatePicker DFZaruka;

    @FXML
    private TextField TAPoznamka;

    @FXML
    private TableView<MDE> tabulka;

    @FXML
    private TableColumn<String, MDE> ColumTyp;

    @FXML
    private TableColumn<String, MDE> ColumNazov;

    @FXML
    private TableColumn<String, MDE> ColumPocet;

    @FXML
    private TableColumn<String, MDE> ColumSC;

    @FXML
    private TableColumn<String, MDE> ColumMac;

    @FXML
    private TableColumn<String, MDE> ColumIP;

    @FXML
    private TableColumn<String, MDE> ColumWifi;

    @FXML
    private TableColumn<String, MDE> ColumCF;

    @FXML
    private TableColumn<String, MDE> ColumDatumodoslania;

    @FXML
    private TableColumn<MDE, String> ColumZaruka;

    @FXML
    private TableColumn<String, MDE> ColumPoznamka;

    MDE mde;
    String zaruka;
    String datumodoslania;
    String wificheck;
    String index = "";

    Alert alert = new Alert(AlertType.INFORMATION);

    static ObservableList<MDE> OLtable = FXCollections.observableArrayList();

    @FXML
    void OnClickRefresh(ActionEvent event) throws IOException, SQLException {
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "MDE") {
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
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Quail") {
            Parent quailParent = FXMLLoader.load(getClass().getResource("KontrolaHWQuail.fxml"));
            Scene quailScene = new Scene(quailParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(quailScene);
            window.show();
        }
        if (getVyberZariadenia(ChoiceBoxTypzariadenia) == "Rabattdrucker") {
            Parent rabatParent = FXMLLoader.load(getClass().getResource("KontrolaHWRabattdrucker.fxml"));
            Scene rabatScene = new Scene(rabatParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rabatScene);
            window.show();
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
            // scanner = new
            // Scanner(getVyberskladu(ChoiceBoxSklad),TFTyp.getText(),TFNazov.getText(),TFPocet.getText(),
            // TFSeriove.getText(), datumodoslania,zaruka, TAPoznamka.getText());
            if (CheckboxWifi.isSelected()) {
                wificheck = "1";
            } else {
                wificheck = "0";
            }
            JDBMySQLConnection.addtoMDE(getVyberskladu(ChoiceBoxSklad1), TFTyp.getText(), TFNazov.getText(),
                    TFPocet.getText(), TFSeriove.getText(), TFMAC.getText(), TFIP.getText(), wificheck, TFCF.getText(),
                    datumodoslania, zaruka, TAPoznamka.getText());

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
        ObservableList<MDE> mde = tabulka.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Dbajte na rozhodnutí");
        alert.setContentText("Ste si istý že chcete vymazať?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                System.out.println(mde.get(0).getID());

                Connection conn = JDBMySQLConnection.getConnection();
                String sql = "DELETE FROM `mde` WHERE ID = ?";
                PreparedStatement pst;
                System.out.println(conn.prepareStatement(sql));
                pst = conn.prepareStatement(sql);
                pst.setString(1, mde.get(0).getID());
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
            alert.setContentText("Povinné polia sklad, typ alebo nazov nie sú vyplnené");
            alert.showAndWait();

        } else {

            try {
                datumodoslania = String.valueOf(DFOdoslanienafili.getValue());
                zaruka = String.valueOf(DFZaruka.getValue());

                Connection conn = JDBMySQLConnection.getConnection();
                PreparedStatement ps = null;

                if (CheckboxWifi.isSelected()) {
                    wificheck = "1";
                } else {
                    wificheck = "0";
                }

                String value2 = ChoiceBoxSklad1.getValue();
                String value3 = TFTyp.getText();
                String value4 = TFNazov.getText();
                String value5 = TFPocet.getText();
                String value6 = TFSeriove.getText();
                String value7 = TFMAC.getText();
                String value8 = TFIP.getText();
                String value9 = wificheck;
                String value10 = DFOdoslanienafili.getValue().toString();
                String value11 = DFZaruka.getValue().toString();
                String value12 = TAPoznamka.getText();
                String value13 = TFCF.getText();

                String sql = "UPDATE `mde` SET `Sklad`='" + value2 + "',`Typ`='" + value3 + "',`Názov`='" + value4
                        + "',`Počet`='" + value5 + "',`Sériové číslo`='" + value6 + "',`MAC adresa`='" + value7
                        + "',`IP adresa`='" + value8 + "',`HuaweiWifi`='" + value9 + "',`Cislo fili`='" + value13
                        + "',`Dátum odoslania fili`='" + value10 + "',`Záruka`='" + value11 + "',`Poznámka`='" + value12
                        + "'  WHERE ID='" + index + "'";

                ps = conn.prepareStatement(sql);
                ps.execute();

                JOptionPane.showMessageDialog(null, "Uspesne upravene");
                vycisti();

                update_Table(getVyberZariadenia(ChoiceBoxTypzariadenia), getVyberskladu(ChoiceBoxSklad));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "niekde nastala chyba");
            }
        }
    }

    // zatial napic
    @FXML
    void getSelected(MouseEvent event) {

        try {
            MDE mde = tabulka.getSelectionModel().getSelectedItem();
            System.out.println(mde.getDatum_odoslania());
            LocalDate localDate1 = LocalDate.parse(mde.getDatum_odoslania());
            LocalDate localDate2 = LocalDate.parse(mde.getZaruka());

            if (mde.getWIFI() == "Ano") {
                CheckboxWifi.setSelected(true);
            } else {
                CheckboxWifi.setSelected(false);
            }
            ChoiceBoxSklad1.setValue(getVyberskladu(ChoiceBoxSklad));
            TFNazov.setText(mde.getNazov());
            TFTyp.setText(mde.getTyp());
            TFPocet.setText(mde.getPocet());
            TAPoznamka.setText(mde.getPoznamka());
            TFSeriove.setText(mde.getSC());
            DFOdoslanienafili.setValue(localDate1);
            DFZaruka.setValue(localDate2);
            TFCF.setText(mde.getCF());
            TFIP.setText(mde.getIP());
            TFMAC.setText(mde.getMAC());
            index = mde.getID();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @FXML
    void OnClickOprava(ActionEvent event) throws IOException, SQLException {
        try {

            ObservableList<MDE> mde = tabulka.getSelectionModel().getSelectedItems();
            String TAtxt = "", Opravacez = "", ID = "";

            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpravaScene.fxml"));
            Parent root = (Parent) loader.load();
            OpravaSceneController opravaScene = loader.getController();

            TAtxt = mde.get(0).getZazcinnosti();
            ID = mde.get(0).getID();
            Opravacez = mde.get(0).getOpravacez();

            opravaScene.initData(ID, TAtxt, Opravacez,ChoiceBoxTypzariadenia.getValue());
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.showAndWait();

            update_Table(ChoiceBoxTypzariadenia.getValue(), ChoiceBoxSklad.getValue());
        } catch (Exception e) {

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
        CheckboxWifi.setSelected(false);
        TFCF.clear();
        TFMAC.clear();
        TFIP.clear();

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
        ChoiceBoxTypzariadenia.setValue("MDE");
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

        OLtable = JDBMySQLConnection.getMDE(choiceZariadenie, choiceSklad);

        ColumTyp.setCellValueFactory(new PropertyValueFactory<>("Typ"));
        ColumSC.setCellValueFactory(new PropertyValueFactory<>("SC"));
        ColumIP.setCellValueFactory(new PropertyValueFactory<>("IP"));
        ColumMac.setCellValueFactory(new PropertyValueFactory<>("MAC"));
        ColumWifi.setCellValueFactory(new PropertyValueFactory<>("WIFI"));
        ColumCF.setCellValueFactory(new PropertyValueFactory<>("CF"));
        ColumNazov.setCellValueFactory(new PropertyValueFactory<>("Nazov"));
        ColumPocet.setCellValueFactory(new PropertyValueFactory<>("Pocet"));
        ColumZaruka.setCellValueFactory(new PropertyValueFactory<>("Zaruka"));
        ColumDatumodoslania.setCellValueFactory(new PropertyValueFactory<>("Datum_odoslania"));
        ColumPoznamka.setCellValueFactory(new PropertyValueFactory<>("Poznamka"));

        tabulka.setItems(OLtable);

        customiseFactory(ColumZaruka);
    }

    private void customiseFactory(TableColumn<MDE, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<MDE, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);
                    TableRow<MDE> currentRow = getTableRow();
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
