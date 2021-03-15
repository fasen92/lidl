public class Pbv {
    String Sc,Nazov,Zaruka,	Datum_prijatia,	Datum_odoslania,Miesto_pouzivania;
    
    public Pbv(String Sc,String nazov,String Zaruka,String Datum_prijatia,String Datum_odoslania,String Miesto_pouzivania) {
        this.Sc =Sc;
        this.Nazov = nazov;
        this.Zaruka= Zaruka;
        this.Datum_prijatia = Datum_prijatia;
        this.Datum_odoslania= Datum_odoslania;
        this.Miesto_pouzivania = Miesto_pouzivania;
    }
    public String getSc() {
        return Sc;
    }
    public void setSc(String sc) {
        Sc = sc;
    }
    public String getZaruka() {
        return Zaruka;
    }
    public void setZaruka(String zaruka) {
        Zaruka = zaruka;
    }
    public String getDatum_prijatia() {
        return Datum_prijatia;
    }
    public void setDatum_prijatia(String datum_prijatia) {
        Datum_prijatia = datum_prijatia;
    }
    public String getDatum_odoslania() {
        return Datum_odoslania;
    }
    public void setDatum_odoslania(String datum_odoslania) {
        Datum_odoslania = datum_odoslania;
    }
    public String getMiesto_pouzivania() {
        return Miesto_pouzivania;
    }
    public void setMiesto_pouzivania(String miesto_pouzivania) {
        Miesto_pouzivania = miesto_pouzivania;
    }
    public String getNazov() {
        return Nazov;
    }
    public void setNazov(String nazov) {
        Nazov = nazov;
    }
}
