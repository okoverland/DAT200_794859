package oving6;

import java.util.List;
import java.util.Scanner;
import program.Utskrift;
import student.Student;

public class Oppgave2c {

	public static void main(String[] args) {
		
		List<Student> studentene = Utskrift.lesInnStudenter();
		if ( studentene == null ) return;
		
		
		AVLTre<Integer, Student> listen = new AVLTre<>();
		
		studentene
			.stream()
			.forEach( student -> listen.put(student.getStudentnummer(), student) );
		
		listen.skrivUtTre();
		
		
		Scanner in = new Scanner(System.in);
		
		String nameSearch; //hent ut dette navnet
		
		System.out.println();
		System.out.printf("Hent ut etternavn: ");
		
		nameSearch = in.nextLine();
		
		System.out.println();
		
		
		if (listen.isEmpty()) {
			System.out.printf("\n\n--- Fant ikke \"%s\" i listen ---", nameSearch);
		} else {
			for (Student s: studentene) {
				System.out.println(s);
			}
		}
		*/

	}

}
