package program;

import java.security.SecureRandom;

public class Randomizer {
	
	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 
	 * @param bound 	Upper exclusive bound of returned int
	 * @return 			int between 0 and bound - 1
	 */
	public static int getRandomInt(int bound) {
		
		return random.nextInt(bound);	
	}
	/**
	 * 
	 * @return int 		from all possible ints
	 */
	public static int getRandomInt() {
		
		return random.nextInt();
	}
	
	/**
	 * 
	 * @param arraySize		Size of return array
	 * @param bound			Upper bound if ints exclusive
	 * @return			    int[] with ints from 0 to bound-1
	 */
	
	public static int[] getRandomIntArray(int arraySize, int bound) {
		
		int[] ints = new int[arraySize];
		
		for (int i = 0; i < ints.length; i++) {
			ints[i] = random.nextInt(bound);
		}
		
		return ints;
		
	}
	/**
	 * 
	 * @return			d6 dice roll
	 */
	public static int getD6() {
		return random.nextInt(6) + 1;
	}
}
