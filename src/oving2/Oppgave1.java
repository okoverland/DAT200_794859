package oving2;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import program.Utskrift;
import student.Student;

public class Oppgave1 {
	
	public static List<Student> firstNameList(List<Student> students, String nameSearch) {
		
		return students
				.stream()
				.filter(s -> s.getFornavn().toLowerCase().equals(nameSearch.toLowerCase()))
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		
		List<Student> studentene = Utskrift.lesInnStudenter("studentliste_oving_9.ser");
		
		for (Student s: studentene) {
			System.out.println(s);
		}
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		String nameSearch; //hent ut dette navnet
		
		System.out.println();
		System.out.printf("Hent ut fornavn: ");
		
		nameSearch = in.nextLine();
		
		System.out.println();
		
		studentene = firstNameList(studentene, nameSearch);
		
		if (studentene.isEmpty()) {
			System.out.printf("\n\n--- Fant ikke \"%s\" i listen ---", nameSearch);
		} else {
			for (Student s: studentene) {
				System.out.println(s);
			}
		}
		
	}

}
