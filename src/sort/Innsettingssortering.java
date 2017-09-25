package sort;

import java.util.ArrayList;
import java.util.List;

public class Innsettingssortering {
	/*
	 * En enkel sorteringsalgoritme: Innsettingssortering
	 * 
	 * Kjøretid:
	 *  Worst case O(n^2)
	 *  While-loopen må tilbake til start hver gang, lista er sortert i motsatt rekkefølge
	 *  
	 *  Best case O(n)
	 *  Lista er allerede sortert på det man ønsker å sortere på.
	 *  
	 *  Average case O(n^2)
	 */
	public static <T extends Comparable<? super T>> void sorter(List<T> lista) {
		for (int i=1;i<lista.size();i++) {
			int j = i;
			while (j > 0 && lista.get(j).compareTo(lista.get(j-1)) < 0) {
				T temp = lista.get(j);
				lista.set(j, lista.get(j-1));
				lista.set(j-1, temp);
				j--;
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> talliste = new ArrayList<>();
		talliste.add(56);
		talliste.add(6);
		talliste.add(66);
		talliste.add(26);
		talliste.add(34);
		talliste.add(3);
		sorter(talliste);
		System.out.println(talliste);
		
	}
}
