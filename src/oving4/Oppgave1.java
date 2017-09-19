package oving4;


/*
  	Lag en rekursiv metode som finner det n-te elementet A(n) i formelen A(n) = A(n-1) + 1/n, A(1)=1. 
  	Metoden skal ta tallet n som parameter.
 */

public class Oppgave1 {
	
	// oppgave a)
	public static double rekursivN(double n) {
		if ( n < 1 ) throw new IllegalArgumentException("n is only defined for 1 <= n");
		if ( n == 1 ) return 1;
		return ( rekursivN(n-1) + (1/n) );
	}

/*
 	Oppgave b)
 	Rekurensligning:
 	To tester O(1). Hvis n større enn 1, kalles algoritmen seg selv med n-1 og 1/n som parameter.
 	
 	T(n) = T(n-1) + 1/n + 1, T(1) = 1
 	
 	Oppgave c)
 	T(n-1) = T(n-2) + 1/n + 1
 	>> T(n) = ( T(n-2) + 1/n + 1 ) + 1/n + 1 = T(n-2) + 2/n + 2
 	
 	T(n-2) = T(n-3) + 1/n + 1
 	>> T(n) = ( T(n-3) + 1/n + 1 ) + 2/n + 2 = T(n-3) + 3/n + 3
 	...
 	T(n) = T(n-k) + k/n + k
 	>> setter k= n-1 for å finne T(1)
 	T(n) = T(n-(n-1) + (n-1)/n + (n-1) = T(1) + (n-1)/n + n -1 = 1 + (n-1)/n + n - 1 = n^2 - n + n^2 = 2n^2 - n
 	 	
 	Kjøretid: T(n) = 2n^2 - n >> O(n) = n^2
 	
 */
	
	// oppgave d)
	
	public static double iterativN (double n) {
		
		if ( n < 1 ) throw new IllegalArgumentException("n is only defined for 1 <= n"); 
		
		double value = 1;
		while ( 1 < n) { value += 1/n--; }
		return value;
	}
	
	/*
	 	Oppgave e)
	 	Hvis n= 1, kjøretid O(1). Hvis n større enn 1, begynner algorimen på n og går igjennom alle n til n = 1.   
	 	Kjøretid = O(n)
	 */
	
	public static void main(String[] args) {
		
		int[] n = { 1, 2, 3, 10, 50, 100, 1000, 3333, 5555, 9999 };
				
			System.out.printf("%1$-4s%2$10s%3$10s%n", "n", "Rekursiv", "Iterativ");
		for (int e : n) { 
			System.out.printf("%1$-4s%2$10.4f%3$10.4f%n", e, rekursivN(e), iterativN(e));
		}
				
	}

}
