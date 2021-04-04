public class MP {
    String SC,Nazov,Pocet,Zaruka,Poznamka,Datum_odoslania,Typ,CF,PIN,PUK,IMEI,Typtel,Telcislo,SIM;
    
    public MP(String Typ,String Nazov,String Pocet,String SC,String Typtel,String IMEI,String SIM,String Telcislo,String PUK,String PIN,String CF,String Datum_odoslania,String Zaruka,String Poznamka) {
        this.Typ =Typ;
        this.SC =SC;
        this.Nazov = Nazov;
        this.Pocet = Pocet;
        this.Zaruka= Zaruka;
        this.Datum_odoslania= Datum_odoslania;
        this.Poznamka = Poznamka;
        this.CF = CF;
        this.PIN = PIN;
        this.PUK = PUK;
        this.IMEI =IMEI;
        this.Typtel = Typtel;
        this.Telcislo =Telcislo;
        this.SIM = SIM;
        
    }
    public void setPIN(String pIN) {
        PIN = pIN;
    }
    public String getPIN() {
        return PIN;
    }
    public void setPUK(String pUK) {
        PUK = pUK;
    }
    public String getPUK() {
        return PUK;
    }
    public void setIMEI(String iMEI) {
        IMEI = iMEI;
    }
    public String getIMEI() {
        return IMEI;
    }
    public void setSIM(String sIM) {
        SIM = sIM;
    }
    public String getSIM() {
        return SIM;
    }
    public void setTelcislo(String telcislo) {
        Telcislo = telcislo;
    }
    public String getTelcislo() {
        return Telcislo;
    }
    public void setTyptel(String typtel) {
        Typtel = typtel;
    }
    public String getTyptel() {
        return Typtel;
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
