public class Zariadenie {
    String Typ,Nazov,Opravacez,Poznamka,Sklad;

    public Zariadenie(String typ, String nazov, String opravacez, String poznamka, String sklad) {
        Typ = typ;
        Nazov = nazov;
        Opravacez = opravacez;
        Poznamka = poznamka;
        Sklad = sklad;
    }

    /*public Zariadenie(String string, String string2, String string3, String string4, String string5) {
    }*/

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ = typ;
    }

    public String getNazov() {
        return Nazov;
    }

    public void setNazov(String nazov) {
        Nazov = nazov;
    }

    public String getOpravacez() {
        return Opravacez;
    }

    public void setOpravacez(String opravacez) {
        Opravacez = opravacez;
    }

    public String getPoznamka() {
        return Poznamka;
    }

    public void setPoznamka(String poznamka) {
        Poznamka = poznamka;
    }

    public String getSklad() {
        return Sklad;
    }

    public void setSklad(String sklad) {
        Sklad = sklad;
    }

    
}
