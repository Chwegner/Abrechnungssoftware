package Abrechnungssoftware.Verarbeitung;

public class Kunde {
	int id;
	String
	firma, name, vorname, strasse, hausnummer, ort, plz, telefon, fax, web, email,
	anrede, apName, apVorname, apTelefon, apEmail;
	double
	stundenSatz ;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setWeb(String web){
		this.web = web;
	}
	public String getWeb(){
		return web;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApName() {
		return apName;
	}
	public void setAnrede(String anrede){
		this.anrede = anrede;
	}
	public String getAnrede(){
		return anrede;
	}
	public void setApName(String apName) {
		this.apName = apName;
	}
	public String getApVorname() {
		return apVorname;
	}
	public void setApVorname(String apVorname) {
		this.apVorname = apVorname;
	}
	public String getApTelefon() {
		return apTelefon;
	}
	public void setApTelefon(String apTelefon) {
		this.apTelefon = apTelefon;
	}
	public String getApEmail() {
		return apEmail;
	}
	public void setApEmail(String apEmail) {
		this.apEmail = apEmail;
	}
	public double getStundenSatz() {
		return stundenSatz;
	}
	public void setStundenSatz(double stundenSatz) {
		this.stundenSatz = stundenSatz;
	}
	
	
}
