package lesere;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import personer.Student;

public class StudentLeser {
	
	private Student lesStudent(String innStreng) {
		String[] komponenter = innStreng.split("\t");
		try {
			int id = Integer.parseInt(komponenter[0]);
			int fodselsaar = Integer.parseInt(komponenter[3]);
			int aarskurs = Integer.parseInt(komponenter[5]);
			Student studenten = new Student(id, komponenter[2], komponenter[1], fodselsaar);
			studenten.setStudieprogram(komponenter[4]);
			studenten.setAarskurs(aarskurs);
			return studenten;
		} catch (NumberFormatException e) {
			System.err.println("Strengen : " + innStreng + " er i feil format!");
			return null;
		}
	}
	
	public List<Student> lesStudenterFraFil() {
		
		try (Scanner filleser = new Scanner(new File(getFilePath()))){
			List<Student> resultat = new ArrayList<>();
			String linje;
			StudentLeser leser = new StudentLeser();
			while (filleser.hasNextLine()) {
				linje = filleser.nextLine();
				Student studenten = leser.lesStudent(linje);
				resultat.add(studenten);
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
				+ File.separator + "serialized" + File.separator + "studenter.txt");
		return filePath.toString();
	}
	
}
