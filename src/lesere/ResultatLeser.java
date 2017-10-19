package lesere;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import emner.Eksamensresultat;
import emner.Emne;
import personer.Student;

public class ResultatLeser {
	
	private List<Student> studentene;
	private List<Emne> emnene;
	private List<Eksamensresultat> resultatene;
	private Map<Integer, Student> studentMapByID;
	private Map<Integer, Student> studentMapByFullName;
	private Map<Integer, Emne> emneMap;
	
	public ResultatLeser() {
		studentene = new StudentLeser().lesStudenterFraFil();
		emnene = new EmneLeser().lesEmnerFraFil();
		resultatene = lesResultaterFraFil();
		studentMapByID = null;
		studentMapByFullName = null;
		emneMap = null;

	}
	
	/*
	 * Oppgave 2 b) O(1), Minne O(n), men laster ikke f�r brukt
	 */
	private Student finnStudent(int id) {
		
		if (studentMapByID == null) studentMapByID = mapStudentsByID();		
		return studentMapByID.get(id);
		
	}

	/*
	 *Oppgave 2 b) O(1), Minne O(n), men laster ikke f�r brukt
	 */
	private Emne finnEmne(String emnekode) {
		
		if (emneMap == null) emneMap = mapEmner();
		return emneMap.get(emnekode.hashCode());
		
	}
	
	private Student finnStudentNavn(String navn) {
		
		if (studentMapByID == null) studentMapByFullName = mapStudentsByName();		
		return studentMapByFullName.get(navn.hashCode());
		
	}
	
	public void preloadHashMaps() {
		if (studentMapByID == null) studentMapByID = mapStudentsByID();
		if (emneMap == null) emneMap = mapEmner();
		if (studentMapByID == null) studentMapByFullName = mapStudentsByName();
	}
	
	private HashMap<Integer, Student> mapStudentsByID() {
		return studentene.stream().collect(HashMap::new, (map,student) -> map.put(student.getID(), student), HashMap::putAll);
	}
	
	private HashMap<Integer, Emne> mapEmner() {
		return emnene.stream().collect(HashMap::new, (map, emne) -> map.put(emne.getEmnekode().hashCode(), emne), HashMap::putAll);
	}
	
	private HashMap<Integer, Student> mapStudentsByName() {
		return studentene.stream().collect(HashMap::new, (map,student) -> map.put(student.getNavnestreng().hashCode(), student), HashMap::putAll);
	}
	
	private void getStudentInfo(Student student) {
		
		System.out.printf("%-50s %s%n", "Emne", "Karakter");
		resultatene.stream()
					.filter(r -> r.getStudenten().equals(student))
					.forEach(r -> System.out.printf("%-50s %4c%n", r.getEmnet().toString(), r.getKarakter()));
	}
	
	private void getEmneInfo(Emne emnet) {
		
		System.out.printf("%-50s %s%n", "Student", "Karakter");
		resultatene.stream()
					.filter(r -> r.getEmnet().equals(emnet))
					.forEach(r -> System.out.printf("%-50s %4c%n", r.getStudenten().getNavnestreng(), r.getKarakter()));
	}
	
	public void skrivUtStudenterSortertPaaNavn() {
		
		studentene.stream()
					.sorted( (s1, s2) -> s1.getNavnestreng().compareTo(s2.getNavnestreng()))
					.forEach( s -> System.out.println(s.getNavnestreng()) );
	}
	
	public void getEmne(String emnekode) {
		
		Emne emnet = finnEmne(emnekode);
		
		if (emnet != null) {
			System.out.println(emnet.toString());
			getEmneInfo(emnet);
			return;
		}
		
		System.out.printf("Fant ikke emnet \"%s\"!%n", emnekode);		
	}
	
	public void getStudent(String student) {
		
		Student studenten = finnStudentNavn(student);
		if (studenten != null) {
			System.out.println(studenten.toString());
			getStudentInfo(studenten);
			return;
		}
		
		System.out.printf("Fant ikke studenten \"%s\"!%n", student);
		
	}
	
	public void printInputMenu() {
		System.out.printf("%n%s%n%s%n%s%n%s", 
				"1 - Skriv ut alle studenter",
				"2 - Skriv ut info om student",
				"3 - Finn emne",
				"Velg menyelement (tomt for � avslutte): "
		);
	}
	
	private Eksamensresultat lesResultat(String innStreng) {
		String[] komponenter = innStreng.split("\t");
		Student student = finnStudent(Integer.parseInt(komponenter[0]));
		Emne emnet = finnEmne(komponenter[1]);
		Eksamensresultat resultatet = new Eksamensresultat(student, emnet, komponenter[2].charAt(0));
		
		return resultatet;
	}
	
	private List<Eksamensresultat> lesResultaterFraFil() {
				
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
