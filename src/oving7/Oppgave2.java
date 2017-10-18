package oving7;

import java.util.Scanner;

import lesere.ResultatLeser;

public class Oppgave2 {
		
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			
			ResultatLeser rl = new ResultatLeser();
			String input = "";
			
			do {
				rl.printInputMenu();
				input = scanner.nextLine();
				System.out.println();
				
				switch (input) {
				
				case "1" : 	{
								rl.skrivUtStudenterSortertPaaNavn();
								break;
							}
				
				case "2" : 	{
								System.out.printf("Skriv inn studentnavn: ");
								input = scanner.nextLine();
								rl.getStudent(input);
								input = " ";
								break;
							}
				
				case "3" : 	{
								System.out.printf("Skriv inn emnekode: ");
								input = scanner.nextLine();
								rl.getEmne(input);
								input = " ";
								break;
							}
							
				default : 		break;
				
				}
				
			} while (!input.equals(""));	
		}
	}
}
