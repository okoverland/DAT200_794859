package oving4;

import java.util.Scanner;

import exceptions.IllegaInputException;

public class Oppgave2 {
	
	static int moves = 0;
			
	// helper method
	static void hanoiTower(int discs) throws IllegaInputException {
		if ( discs < 1 ) throw new IllegaInputException();
		System.out.printf("Hanois Tårn løst for %d skiver:%n%n", discs);
		hanoiTower(discs, 'A', 'C', 'B');
	}
	
	/**
	 * Method to solve Tower of Hanoi
	 * 
	 * @author Ole K. Øverland, 794859
	 * @param discs 	Number of discs to solve
	 * @param startPeg  Letter of first peg
	 * @param auxPeg    Letter of auxiliary peg
	 * @param endPeg	Letter of peg where to move discs
	 */
	
	static void hanoiTower(int discs, char startPeg, char auxPeg, char endPeg) {
		if ( discs == 0 ) return;
		hanoiTower(discs - 1, startPeg, endPeg, auxPeg); 
		System.out.printf("Steg %d: Flytter %s fra %s til %s%n", ++moves, discs, startPeg, endPeg );
		hanoiTower(discs - 1, auxPeg, startPeg, endPeg);
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("Skriv inn antall skiver: ");
			int discs = in.nextInt();
			System.out.println();
						
			hanoiTower(discs);
			
		} catch (Exception e) {
			System.err.println("Antall skiver må være flere enn 0!");
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
}

/* Oppgave b)
	
	Algortitmen kaller seg selv med (n-1) to ganger, dvs. T(n) = 2 * T(n-1) + 1, T(1) = 1
	
	T(n-1) = 2 * T(n-2) + 1 gir T(n) = 2 * (2 * T(n-2) + 1) + 1 = 4 * T(n-2) + 3
	T(n-2) = 2 * T(n-3) + 1 gir T(n) = 4 * (2 * T(n-3) + 1) + 3 = 8 * T(n-3) + 7
	T(n-3) = 2 * T(n-4) + 1 gir T(n) = 8 * (2 * T(n-4) + 1) + 7 = 16 * T(n-4) + 15
	...
	T(n) = 2^k * T(n-k) + 2^k - 1, setter k = (n-1) for å få T(1) = 1
	T(n) = 2^(n-1) * 1 + 2^(n-1) - 1 = 2 * 2^(n-1) - 1 = 2 * ( 2^n / 2^1 ) - 1 = 2^n - 1
	
	Oppgave c)
	O(2^n)
	 
 */

