package oving5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 *  a) Skriv en generisk metode som fjerner duplikater fra en liste og returnerer listen uten
 *  duplikatene. Den returnerte listen trenger ikke å være i samme rekkefølge som den opprinnelige,
 *  men må inneholde de samme elementene minus duplikatene. Ikke bruk Collections, men kode fra
 *  Canvas er ok.
 */


public class Oppgave2 {
	
	public static <T> List<T> removeDuplicates(List<T> array) {
		
		return array
			 .stream()
			 .distinct()
			 .collect(Collectors.toList());	
	}
	
	public static void main(String[] arg0) {
		
		
		List<Integer> array = Arrays.asList(1, 1, 6, 8, 4, 8, 1, 2);
		
		System.out.printf("%-18s%s%n", "Original liste:", array.toString());
		array = removeDuplicates(array);
		System.out.printf("%-18s%s%n", "Unik liste:", array.toString());
		
	}
	
}
