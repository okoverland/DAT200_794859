package oving3;

import java.util.Iterator;

/*
 * Demonstrasjon av bruk av ArryaList implementasjonen
 */
public class MinArrayListDemo {

	public static void main(String[] args) {
		// Lager en MinArrayList med typeparameter String
		MinArrayList<String> strengliste = new MinArrayList<>();
		strengliste.add("En");
		strengliste.add("tre");
		strengliste.add("fire");
		strengliste.add("fem");
		strengliste.add("ergg");
		strengliste.add("tradgade");
		strengliste.add("fiadgre");
		strengliste.add("En");
		strengliste.add("tre");
		strengliste.add("fire");
		strengliste.add("fem");
		strengliste.add("ergg");
		strengliste.add("tradgade");
		strengliste.add("fiadgre");
		strengliste.remove(3);
		
		/* List<T> grensesnittet utvider Iterable<T> grensesnittet.
		 * det formelle kravet for å bruke en for each løkke er at man
		 * utvider Iterable<T>. Denne fungerer så lenge iteratoren
		 * er korrekt laget.
		 */
		for (String streng: strengliste) {
			System.out.println(streng);
		}
		
		// Ekvevalent formulering av for each løkka som viser hvordan
		// iteratoren faktisk brukes av for each løkka.
		String streng;
		Iterator<String> strengiterator = strengliste.iterator();
		while (strengiterator.hasNext()) {
			streng = strengiterator.next();
			System.out.println(streng);			
		}
	}

}
