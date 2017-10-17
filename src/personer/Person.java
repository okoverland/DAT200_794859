package personer;

import java.util.ArrayList;

/*
 * Oppgave d, markerte metoder og variabler fra andre deloppgaver
 */
public class Person {
	private int ID;
	private String fornavn;
	private String etternavn;
	private int fodselsaar;
	private ArrayList<Hendelse> hendelser; // Oppgave h)
	
	/*
	 * Konstruktøren var ikke spesifisert i oppgaveteksten. Dette er bare
	 * en måte den kan gjøres på. Poenget er at man må ha en metode
	 * å sette alle egenskapene på selv de som ikke har settere.
	 */
	public Person(int ID, String fornavn, String etternavn, int fodselsaar) {
		this.ID = ID;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fodselsaar = fodselsaar;
		hendelser = new ArrayList<>();
	}
	
	// Trengs for oppgave j
	public ArrayList<Hendelse> getHendelser() {
		return hendelser;
	}
	
	// Trengs for oppgave k
	public boolean invitertTilHendelse(Hendelse hendelsen) {
		return hendelser.contains(hendelsen);
	}
	
	// Oppgave k
	public void leggTilHendelse(Hendelse hendelsen) {
		hendelser.add(hendelsen);
		if (!hendelsen.personInvitert(this)) hendelsen.leggTilPerson(this);
	}
	
	public String getFornavn() {
		return fornavn;
	}
	
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getFodselsaar() {
		return fodselsaar;
	}
	
	@Override public String toString() {
		return "Person " + ID + ": " + fornavn + " " + etternavn + 
				" født i " + fodselsaar;
	}
	
	public String getNavnestreng() {
		return fornavn + " " + etternavn;
	}
}
