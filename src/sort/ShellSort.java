package sort;

import java.util.ArrayList;

public class ShellSort {
	/*
	 * Gjør en k-sort av ei liste.
	 * 
	 * En k-sort sorterer hvert k-ende element. For eksempel en 5-sort gjør det slik
	 * at hvert 5. element er sortert.
	 */
	public static <T extends Comparable<? super T>> void Ksorter(ArrayList<T> lista, int k) {
		for (int a = 0; a < k; a++) {
			for (int i = a+k; i < lista.size(); i += k) {
				int j = i;
				while (j-k >= 0 && lista.get(j).compareTo(lista.get(j - k)) < 0) {
					T temp = lista.get(j);
					lista.set(j, lista.get(j - k));
					lista.set(j - k, temp);
					j -= k;
				}
			} 
		}
	}
	
	/*
	 * ShellSort. Idéen er å bruke Ksorter metoden for å flytte lave verdier mange hakk
	 * mot starten og høye tall mange hakk mot slutten i én operasjon i stedet for
	 * mange slik som InsertionSort ville gjort.
	 * 
	 * Kjøretid
	 * Worst case O(n^2), Average case O(n*sqrt(n)). Dette er ikke intuitivt og denne
	 * algoritmen er svært vanskelig å analysere korrekt selv om den er bygd på
	 * en enkel idé.
	 * 
	 * Dette regnes som den mest effektive av de enkle sorteringsalgoritmene.
	 */
	public static <T extends Comparable<? super T>> void shellSort(ArrayList<T> lista) {
		for (int k=lista.size()/2;k>1;k/=2) {
			if (k % 2 == 0) k += 1;
			Ksorter(lista, k);
		}
		Ksorter(lista, 1);
	}

	public static void main(String[] args) {
		ArrayList<Integer> talliste = new ArrayList<>();
		talliste.add(10);
		talliste.add(5);
		talliste.add(30);
		talliste.add(80);
		talliste.add(50);
		talliste.add(40);
		talliste.add(75);
		talliste.add(80);
		talliste.add(90);
		talliste.add(40);
		talliste.add(25);
		System.out.println(talliste);
		shellSort(talliste);
		System.out.println(talliste);
		
	}
	
}
