package oving2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import program.Utskrift;
import student.Student;

public class Oppgave1 {
	
	public static List<Student> firstNameList(List<Student> students, String nameSearch) {
		
		List<Student> nyListe = new ArrayList<Student>();
		
		students.forEach( (s) -> { if ( s.getFornavn().equals(nameSearch) ) nyListe.add(s); } );
		/*
		for (int i = 0; i < students.size(); i++) {
			if ( students.get(i).getFornavn().equals(nameSearch) ) nyListe.add(students.get(i));
		}*/
		return nyListe;
	}

	public static void main(String[] args) {
		
		List<Student> studentene = Utskrift.lesInnStudenter();
		
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
