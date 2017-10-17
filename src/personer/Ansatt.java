package personer;

// Oppgave b) med tillegg.
public class Ansatt extends Person {
	private int ansattnummer;
	private String stilling;
	private int aarslonn;

	public Ansatt(int ID, String fornavn, String etternavn, int fodselsaar) {
		super(ID, fornavn, etternavn, fodselsaar);
		
		// Setter default verdier for egenskapene til en ansatt
		ansattnummer = ID;
		stilling = "Udefinert";
		aarslonn = 400000;
	}

	public int getAnsattnummer() {
		return ansattnummer;
	}

	/*
	 * Gjennom � returnere Ansatt i stedet for void i denne og de andre
	 * setterne i Ansatt klassen kan man selv velge hvilke egenskaper
	 * man �nsker � sette i en ansatt uten � lage en mengde ulike
	 * overlagrete konstrukt�rer. Se eksempler p� hvordan
	 * dette gj�res i main metoden.
	 */
	public Ansatt setAnsattnummer(int ansattnummer) {
		this.ansattnummer = ansattnummer;
		return this;
	}

	public String getStilling() {
		return stilling;
	}

	public Ansatt setStilling(String stilling) {
		this.stilling = stilling;
		return this;
	}

	public int getAarslonn() {
		return aarslonn;
	}

	public Ansatt setAarslonn(int aarslonn) {
		this.aarslonn = aarslonn;
		return this;
	}

	// Oppgave f)
	@Override public String toString() {
		return "Ansatt " + ansattnummer + ": " +
				getNavnestreng() + 
				" har stillingen " + stilling +
				" har �rsl�nn " + aarslonn;
	}
}
