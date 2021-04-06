public class Scanner {
    
        String SC,Nazov,Pocet,Zaruka,	Poznamka,Datum_odoslania,Typ,Sklad;
        
        public Scanner(String Typ,String Nazov,String Pocet,String SC,String Datum_odoslania,String Zaruka,String Poznamka) {
            this.Typ =Typ;
            this.SC =SC;
            this.Nazov = Nazov;
            this.Pocet = Pocet;
            this.Zaruka= Zaruka;
            this.Datum_odoslania= Datum_odoslania;
            this.Poznamka = Poznamka;
            
        }

        public Scanner(String Sklad,String Typ,String Nazov,String Pocet,String SC,String Datum_odoslania,String Zaruka,String Poznamka) {
            this.Typ =Typ;
            this.SC =SC;
            this.Nazov = Nazov;
            this.Pocet = Pocet;
            this.Zaruka= Zaruka;
            this.Datum_odoslania= Datum_odoslania;
            this.Poznamka = Poznamka;
            this.Sklad = Sklad;
            
        }
        public void setSklad(String sklad) {
            Sklad = sklad;
        }
        public String getSklad() {
            return Sklad;
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
