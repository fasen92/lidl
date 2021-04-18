public class Osoba {
    String ID,Meno,Priezvisko,Datum,Akcia;

    public Osoba(String ID, String Meno, String Priezvisko, String Datum, String Akcia) {
        this.ID =ID;
        this.Meno = Meno;
        this.Priezvisko = Priezvisko;
        this.Datum = Datum;
        this.Akcia = Akcia;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getMeno() {
        return Meno;
    }

    public void setMeno(String meno) {
        Meno = meno;
    }

    public String getPriezvisko() {
        return Priezvisko;
    }

    public void setPriezvisko(String priezvysko) {
        Priezvisko = priezvysko;
    }

    public String getDatum() {
        return Datum;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }

    public String getAkcia() {
        return Akcia;
    }

    public void setAkcia(String akcia) {
        Akcia = akcia;
    }

    
}
