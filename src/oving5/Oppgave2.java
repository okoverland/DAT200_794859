package oving5;

import java.util.ArrayList;
import java.util.List;

/*
 *  a) Skriv en generisk metode som fjerner duplikater fra en liste og returnerer listen uten
 *  duplikatene. Den returnerte listen trenger ikke å være i samme rekkefølge som den opprinnelige,
 *  men må inneholde de samme elementene minus duplikatene. Ikke bruk Collections, men kode fra
 *  Canvas er ok.
 */


public class Oppgave2 {
	
	public static <T> List<T> removeDuplicates(List<T> array) {
		
		List<T> uniqueList = new ArrayList<>();
		
		array.stream()
			 .distinct()
			 .forEach(e -> uniqueList.add(e));
		
		return uniqueList;
		
	}
	
	public static void main(String[] arg0) {
		
		List<Integer> array = new ArrayList<>();
		
		array.add(1);
		array.add(1);
		array.add(6);
		array.add(8);
		array.add(4);
		array.add(8);
		array.add(1);
		array.add(2);
		
		System.out.printf("%-18s%s%n", "Original liste:", array.toString());
		array = removeDuplicates(array);
		System.out.printf("%-18s%s%n", "Unik liste:", array.toString());
		
	}
	
}
