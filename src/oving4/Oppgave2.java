package oving4;

import java.util.Scanner;

import exceptions.IllegaInputException;

public class Oppgave2 {
	
	static int moves = 0;
			
	// helper method
	static void hanoiTower(int disks) throws IllegaInputException {
		if ( disks < 1 ) throw new IllegaInputException();
		System.out.printf("Hanois Tårn løst for %d skiver:%n%n", disks);
		hanoiTower(disks, 'A', 'C', 'B');
	}
	// recursive method
	static void hanoiTower(int disks, char startPeg, char auxPeg, char endPeg) {
		if ( disks == 0 ) return;
		hanoiTower(disks - 1, startPeg, endPeg, auxPeg); 
		System.out.printf("Steg %d: Flytter %s fra %s til %s%n", ++moves, disks, startPeg, endPeg );
		hanoiTower(disks - 1, auxPeg, startPeg, endPeg);
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("Skriv inn antall skiver: ");
			int disks = in.nextInt();
			System.out.println();
						
			hanoiTower(disks);
			
		} catch (Exception e) {
			System.err.println("Antall skiver må være flere enn 0!");
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
}
