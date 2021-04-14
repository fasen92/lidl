import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Ucet {
    private int id, prvyLogin;
    private String heslo, meno, priezvisko, sklad, rola;
    private Button button;

    public Ucet(int id, String heslo, String meno, String priezvisko, String rola, String sklad, int prvyLogin) {
        this.id = id;
        this.heslo = heslo;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.sklad = sklad;
        this.rola = rola;
        this.prvyLogin = prvyLogin;
        this.button = new Button("Detail");

        button.setOnAction(e -> {
            SingletonDetailZam x = SingletonDetailZam.getInstance();
            Ucet ucet1 = new Ucet(this.id, this.heslo, this.meno, this.priezvisko, this.rola, this.sklad, this.prvyLogin);
            x.setUcet(ucet1);

            Parent DetailZamParent;
            try {
                DetailZamParent = FXMLLoader.load(getClass().getResource("DetailZam.fxml"));
                Scene DetailZamScene = new Scene(DetailZamParent);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(DetailZamScene);
                window.show();

            } catch (IOException e1) {

                e1.printStackTrace();
            }

        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getSklad() {
        return sklad;
    }

    public void setSklad(String sklad) {
        this.sklad = sklad;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getPrvyLogin() {
        return prvyLogin;
    }

    public void setPrvyLogin(int prvyLogin) {
        this.prvyLogin = prvyLogin;
    }

}
