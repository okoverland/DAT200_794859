package sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Flettesortering, engelsk Merge Sort
 * 
 */
public class MergeSort {

	/*
	 * Kombinerer to sorterte lister til én sortert liste
	 * 
	 * Kjøretid O(m + n)
	 * 
	 * Minnebruk O(m + n)
	 */
	public static <T extends Comparable<? super T>> List<T> kombinerSorterteLister(List<T> list1, List<T> list2) {
		List<T> resultat = new ArrayList<>();
		
		Iterator<T> liste1ref = list1.iterator();
		Iterator<T> liste2ref = list2.iterator();
		
		T liste1element = liste1ref.next();
		T liste2element = liste2ref.next();
		
		do {
			if (liste1element.compareTo(liste2element) <= 0) {
				resultat.add(liste1element);
				if (!liste1ref.hasNext()) {
					resultat.add(liste2element);
					break;
				}
				liste1element = liste1ref.next();
			} else {
				resultat.add(liste2element);
				if (!liste2ref.hasNext()) {
					resultat.add(liste1element);
					break;
				}
				liste2element = liste2ref.next();
			}
		} while (true);
		
		while (liste1ref.hasNext()) {
			resultat.add(liste1ref.next());
		}
		
		while (liste2ref.hasNext()) {
			resultat.add(liste2ref.next());
		}
		return resultat;
	}
	
	/*
	 * MergeSort algoritmen bruker kombiner sorterte lister sammen med splitt-og-hersk
	 * teknikken for å sortere lister. Idéen er:
	 * - Splitt lista i to
	 * - Sorter hver halvpart for seg
	 * - Kombiner de to
	 * 
	 * Kjøretid Theta(n*log(n))
	 * 
	 * Fordeler:
	 *  - Garantert n*log(n) kjøretid
	 *  - Enkel å parallellisere
	 *  - Stabil
	 *  
	 * Ulemper:
	 *  - Minnebruk: Min versjon O(n*log(n)) minne. Mulig å lage en versjon som bruker bare
	 *    to arrays som får O(n) minnebruk.
	 *  - Stor tidskonstant på grunn av kopiering av elementer
	 */
	public static <T extends Comparable<? super T>> List<T> mergeSort(List<T> elementene) {
		
		if (elementene.size() < 10) {
			Innsettingssortering.sorter(elementene);
			return elementene;
		}
		
		/*
		 * Kjøretid subList
		 *  O(1) tid på en ArrayList
		 *  O(tid det tar å navigere), i praksis O(n) på en linkedlist
		 */
		List<T> forsteDel = elementene.subList(0, elementene.size()/2);
		List<T> andreDel = elementene.subList(elementene.size()/2, elementene.size());
		
		forsteDel = mergeSort(forsteDel);
		andreDel = mergeSort(andreDel);
		
		List<T> resultat = kombinerSorterteLister(forsteDel, andreDel);
		
		return resultat;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> liste1 = new ArrayList<>();
		liste1.add(6);
		liste1.add(10);
		liste1.add(11);
		liste1.add(18);
		liste1.add(23);
		liste1.add(35);
		ArrayList<Integer> liste2 = new ArrayList<>();
		liste2.add(8);
		liste2.add(10);
		liste2.add(19);
		liste2.add(39);
		liste2.add(43);
		List<Integer> kombinert = kombinerSorterteLister(liste1, liste2);
		System.out.println(kombinert);

		List<Integer> talliste = new ArrayList<>();
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
		talliste = mergeSort(talliste);
		System.out.println(talliste);

	}
}
