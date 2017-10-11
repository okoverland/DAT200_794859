package program;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import student.Student;
/*
 * Modifisert til å ta inn default fil hvis den ekisterer, Ole K. ØVerland 2017-01-11
 */
public class Utskrift {
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Student> lesInnStudenter(String filename) {
		
		ArrayList<Student> lista = null;
		Object o;
		File fila = null;
		
		if (filename != "") {
			try {
				String basePath = new File("").getAbsolutePath();
				Path defaultFile = Paths.get(basePath + File.separator + "src" + File.separator + "serialized" + File.separator + filename);
				fila = new File(defaultFile.toString());
			} catch (Exception e) {
				System.err.println("Oppgitt fil \"" + filename + "\" eksisterer ikke!");
			} 
			 
		} else {
			JFileChooser fildialog = new JFileChooser();
			int valg = fildialog.showOpenDialog(null);
			if (valg == JFileChooser.APPROVE_OPTION) {
			fila = fildialog.getSelectedFile();
			}
		}
		
		if (fila != null) {
			try (FileInputStream filstrom = new FileInputStream(fila);
					ObjectInputStream objectstrom = new ObjectInputStream(filstrom)) {
				o = objectstrom.readObject();
				if (o instanceof ArrayList<?>) {
					lista = (ArrayList<Student>)o;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
				
		return lista;
	}

	public static void main(String[] args) {
		ArrayList<Student> studentene = lesInnStudenter("per");
				
		studentene.stream().forEach(System.out::println);
	}

}
