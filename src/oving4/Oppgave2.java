package oving4;

public class Oppgave2 {
	
	static String stack = "ABC";
			
	// Start metode 
	public static void hanoiTower(int stackSize) {
		if ( stackSize < 1 ) throw new IllegalArgumentException("Antall skiver m� v�re flere enn 0.");
		System.out.printf("Hanois T�rn l�ser for %d skiver:%n%n", stackSize);
		hanoiTower(stackSize, 1, 3);
	}
	
	// Rekursiv metode
	
	//metoden flytter n� fra A til C, m� skrives om til � flytte til B
	public static void hanoiTower(int stackSize, int startStack, int endStack) {
		if ( stackSize == 0 ) return;
		
		int middleStack = 6 - endStack - startStack;
		hanoiTower(stackSize - 1, startStack, middleStack);
		
		System.out.printf("Flytter %s fra %s til %s%n", stackSize, stack.charAt(startStack-1), stack.charAt(endStack-1) );
		
		hanoiTower(stackSize - 1, middleStack, endStack);
	}

	public static void main(String[] args) {
		
		hanoiTower(3);

	}

}
