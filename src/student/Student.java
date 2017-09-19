package student;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {
	private int studentnummer;
	private String fornavn;
	private String etternavn;
	private String studieprogram;
	private int aarskurs;
	
	private static int nesteStudentnummer = 1;
	
	public Student(String fornavn, String etternavn, String studieretning) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.studieprogram = studieretning;
		studentnummer = nesteStudentnummer;
		nesteStudentnummer++;
		aarskurs = 1;
	}
	
	public Student(int id, String fornavn, String etternavn, String studieretning, int aarskurs) {
		this.studentnummer = id;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.studieprogram = studieretning;
		this.aarskurs = aarskurs;
		if (nesteStudentnummer <= id) nesteStudentnummer = id+1;
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

	public String getStudieprogram() {
		return studieprogram;
	}

	public void setStudieprogram(String studieprogram) {
		this.studieprogram = studieprogram;
	}

	public int getAarskurs() {
		return aarskurs;
	}

	public void setAarskurs(int aarskurs) {
		this.aarskurs = aarskurs;
	}

	public int getStudentnummer() {
		return studentnummer;
	}
	
	public void setStudentnummer(int studentnummer) {
		this.studentnummer = studentnummer;
		if (studentnummer >= nesteStudentnummer) nesteStudentnummer = studentnummer+1;
	}
	
	@Override public String toString() {
		return studentnummer + ": " + etternavn + ", " + fornavn + ": " + studieprogram + " " + aarskurs;
	}
}
