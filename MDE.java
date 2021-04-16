public class MDE {
    String SC, Nazov, Pocet, Zaruka, Poznamka, Datum_odoslania, Typ, CF, IP, MAC, WIFI, Sklad, ID,Zazcinnosti,Opravacez;

    public MDE(String ID, String Typ, String Nazov, String Pocet, String SC, String MAC, String IP, String WIFI,
            String CF,String Zazcinnosti, String Datum_odoslania, String Zaruka, String Poznamka,String Opravacez) {
        this.Typ = Typ;
        this.SC = SC;
        this.Nazov = Nazov;
        this.Pocet = Pocet;
        this.Zaruka = Zaruka;
        this.Datum_odoslania = Datum_odoslania;
        this.Poznamka = Poznamka;
        this.CF = CF;
        this.MAC = MAC;
        this.IP = IP;
        this.WIFI = WIFI;
        this.ID = ID;
        this.Zazcinnosti = Zazcinnosti;
        this.Opravacez = Opravacez;

    }
    public String getOpravacez() {
        return Opravacez;
    }
    public void setOpravacez(String opravacez) {
        Opravacez = opravacez;
    }
    public String getZazcinnosti() {
        return Zazcinnosti;
    }
    public void setZazcinnosti(String zazcinnosti) {
        Zazcinnosti = zazcinnosti;
    }
    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public void setSklad(String sklad) {
        Sklad = sklad;
    }

    public String getSklad() {
        return Sklad;
    }

    public void setMAC(String mAC) {
        MAC = mAC;
    }

    public String getMAC() {
        return MAC;
    }

    public void setIP(String iP) {
        IP = iP;
    }

    public String getIP() {
        return IP;
    }

    public void setWIFI(String wIFI) {
        WIFI = wIFI;
    }

    public String getWIFI() {
        return WIFI;
    }

    public void setCF(String cF) {
        CF = cF;
    }

    public String getCF() {
        return CF;
    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ = typ;
    }

    public String getSC() {
        return SC;
    }

    public void setSC(String sC) {
        SC = sC;
    }

    public String getNazov() {
        return Nazov;
    }

    public void setNazov(String nazov) {
        Nazov = nazov;
    }

    public String getZaruka() {
        return Zaruka;
    }

    public void setZaruka(String zaruka) {
        Zaruka = zaruka;
    }

    public String getDatum_odoslania() {
        return Datum_odoslania;
    }

    public void setDatum_odoslania(String datum_odoslania) {
        Datum_odoslania = datum_odoslania;
    }

    public String getPoznamka() {
        return Poznamka;
    }

    public void setPoznamka(String poznamka) {
        Poznamka = poznamka;
    }

    public String getPocet() {
        return Pocet;
    }

    public void setPocet(String pocet) {
        Pocet = pocet;
    }
}
