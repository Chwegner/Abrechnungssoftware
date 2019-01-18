package Abrechnungssoftware.DB;



public class helperClass {
    int id,auftrag_id,auftrag_intervall_id,kunden_id,tage,korrektur_tage;
    String grund,von,bis,intervall_von,intervall_bis;

    helperClass(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuftrag_id() {
        return auftrag_id;
    }

    public void setAuftrag_id(int auftrag_id) {
        this.auftrag_id = auftrag_id;
    }

    public int getAuftrag_intervall_id() {
        return auftrag_intervall_id;
    }

    public void setAuftrag_intervall_id(int auftrag_intervall_id) {
        this.auftrag_intervall_id = auftrag_intervall_id;
    }

    public int getKunden_id() {
        return kunden_id;
    }

    public void setKunden_id(int kunden_id) {
        this.kunden_id = kunden_id;
    }

    public int getTage() {
        return tage;
    }

    public void setTage(int tage) {
        this.tage = tage;
    }

    public int getKorrektur_tage() {
        return korrektur_tage;
    }

    public void setKorrektur_tage(int korrektur_tage) {
        this.korrektur_tage = korrektur_tage;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        this.von = von;
    }

    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }

    public String getIntervall_von() {
        return intervall_von;
    }

    public void setIntervall_von(String intervall_von) {
        this.intervall_von = intervall_von;
    }

    public String getIntervall_bis() {
        return intervall_bis;
    }

    public void setIntervall_bis(String intervall_bis) {
        this.intervall_bis = intervall_bis;
    }
}
