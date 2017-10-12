package oving6;

import java.util.List;
import java.util.Scanner;
import program.Utskrift;
import student.Student;

public class Oppgave2c {
	
	public static void main(String[] args) {
		
		List<Student> studentene = Utskrift.lesInnStudenter("studentliste_oving_9.ser");
		if ( studentene == null ) return;
				
		AVLTre<Integer, Student> listen = new AVLTre<>();
		
		for (Student student : studentene) {
			listen.put(student.getStudentnummer(), student);
		}
		
		System.out.println("AVL-Treet:");
		listen.skrivUtTre();
				
		Scanner in = new Scanner(System.in);
		String nameSearch;
		System.out.println();
		System.out.printf("Hent ut etternavn: ");
		
		nameSearch = in.nextLine();
		
		listen.values()
				.stream()
				.filter(e -> e.getEtternavn().toLowerCase().equals(nameSearch.toLowerCase()))
				.forEach(System.out::println);
		System.out.println();
		
		System.out.printf("Innholder listen %s? ", studentene.get(3).getEtternavn());
		System.out.printf("%b%n", listen.containsValue(studentene.get(3)));
		System.out.printf("Innholder listen %s? ", studentene.get(1).getEtternavn());
		System.out.printf("%b%n", listen.containsValue(studentene.get(1)));
	}

}
