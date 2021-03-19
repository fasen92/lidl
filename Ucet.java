public class Ucet {
    private int id;
    private String heslo, meno, priezvisko, sklad, rola;

    public Ucet(int id, String heslo, String meno, String priezvisko, String sklad, String rola) {
        this.id = id;
        this.heslo = heslo;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.sklad = sklad;
        this.rola = rola;
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

    
    
}
