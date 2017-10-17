package lesere;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import emner.Emne;


public class EmneLeser {
	
	public Emne lesEmne(String innStreng) {
		String[] komponenter = innStreng.split("\t");
		Emne emnet = new Emne(komponenter[0], komponenter[1], komponenter[2].charAt(0));
		emnet.setStudiepoeng(Integer.parseInt(komponenter[3]));
		return emnet;
	}
	
	public List<Emne> lesEmnerFraFil() {
		List<Emne> resultat = new ArrayList<Emne>();
		
		try (Scanner filleser = new Scanner(new File(getFilePath()))){
			String linje;
			EmneLeser leser = new EmneLeser();
			while (filleser.hasNextLine()) {
				linje = filleser.nextLine();
				Emne emnet = leser.lesEmne(linje);
				resultat.add(emnet);
			}
			return resultat;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	public String getFilePath() {
		String basePath = new File("").getAbsolutePath();
		Path filePath = Paths.get(basePath + File.separator + "src" 
				+ File.separator + "serialized" + File.separator + "emner.txt");
		return filePath.toString();
	}

}
