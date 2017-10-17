package lesere;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import emner.Eksamensresultat;
import emner.Emne;
import personer.Student;

public class ResultatLeser {
	
	private List<Student> studentene;
	private List<Emne> emnene;
	private List<Eksamensresultat> resultatene;
	private HashMap
	
	public ResultatLeser() {
		studentene = new StudentLeser().lesStudenterFraFil();
		emnene = new EmneLeser().lesEmnerFraFil();
		resultatene = new ArrayList<>();
	}
	
	/*
	 * O(n) kjøretid placeholder. Implementer en mer effektiv versjon her!
	 */
	private Student finnStudent(int id) {
		for (Student studenten: studentene) {
			if (studenten.getID() == id) return studenten;
		}
		return null;
	}

	/*
	 * O(n) kjøretid placeholder. Implementer en mer effektiv versjon her!
	 */
	private Emne finnEmne(String emnekode) {
		for (Emne emnet: emnene) {
			if (emnet.getEmnekode().equals(emnekode)) return emnet;
		}
		return null;
	}
	
	public void skrivUtStudenterSortertPaaNavn() {
		studentene.stream()
					.sorted( (s1, s2) -> s1.getNavnestreng().compareTo(s2.getNavnestreng()))
					.forEach(System.out::println);
	}
	
	
	
	private Eksamensresultat lesResultat(String innStreng) {
		String[] komponenter = innStreng.split("\t");
		Student student = finnStudent(Integer.parseInt(komponenter[0]));
		Emne emnet = finnEmne(komponenter[1]);
		Eksamensresultat resultatet = new Eksamensresultat(student, emnet, komponenter[2].charAt(0));
		
		return resultatet;
	}
	
	public List<Eksamensresultat> lesResultaterFraFil() {
				
		try (Scanner filleser = new Scanner(new File(getFilePath()))){
			List<Eksamensresultat> resultat = new ArrayList<>();
			String linje;
			
			while (filleser.hasNextLine()) {
				linje = filleser.nextLine();
				Eksamensresultat resultatet = lesResultat(linje);
				resultat.add(resultatet);
			}
			return resultat;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private String getFilePath() {
		String basePath = new File("").getAbsolutePath();
		Path filePath = Paths.get(basePath + File.separator + "src" 
				+ File.separator + "serialized" + File.separator + "karakterer.txt");
		return filePath.toString();
	}

	public static void main(String[] args) {
		
		ResultatLeser leseren = new ResultatLeser();
		leseren.resultatene = leseren.lesResultaterFraFil();
		leseren.resultatene.stream().forEach(System.out::println);
	}
}
