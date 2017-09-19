package oving3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
 * Ønsker: Å lage en implementasjon av ei liste basert på arrays
 *  Array har en fast størrelse, lister kan vokse ved behov
 */
public class MinArrayList <T> implements List<T> {

	private Object[] elementene;	// Array som inneholder elementene
	private int antallElementer;	// Antall elementer i lista nå
	
	/*
	 * Lager en arraylist med startkapasitet 10
	 */
	public MinArrayList() {
		elementene = new Object[10];
		antallElementer = 0;
	}
	
	/*
	 * Lager en arraylist med oppgitt startkapasitet. Denne kan brukes om man veit
	 * omtrent hvor mange elementer lista vil inneholde slik at den ikke trenger å øke
	 * kapasiteten underveis.
	 */
	public MinArrayList(int startkapasitet) {
		elementene = new Object[startkapasitet];
		antallElementer = 0;
	}
	
	/*
	 * Kjøretid: O(n)
	 */
	private void okKapasitet() {
		okKapasitet(elementene.length * 2);
	}
	
	private void okKapasitet(int nyKapasitet) {
		// Lager ny array med dobbel størrelse
		Object[] nyArray = new Object[nyKapasitet];
		
		// Kopierer innholdet fra den gamle til den nye
		for (int i=0;i<elementene.length;i++) {
			nyArray[i] = elementene[i];
		}
		
		// Oppdaterer referansen
		elementene = nyArray;		
	}
	
	/*
	 * Add: Legger til et element på slutten av lista
	 * 
	 * Kjøretid: hvis array er full: okKapasitet, som er O(n)
	 * Ellers: O(1)
	 * 
	 * Amortized Analysis
	 * Hva er kjøretid i gjennomsnittstilfelle, noen man finenr ut ved å se på
	 * kjøretida til flere av samme operasjon etter hverandre.
	 * 
	 * Kjøretida til n add-operasjoner:
	 * - Kreve én dobling O(n)
	 * - kreve n andre add operasjoner O(1*n) = O(n)
	 * - total kjøretid O(n)
	 * - Regner da kjøretida for én add operasjon som O(n)/n = O(1)
	 * 
	 */
	@Override
	public boolean add(T e) {
		if (antallElementer >= elementene.length) {
			okKapasitet();
		}
		elementene[antallElementer] = e;
		antallElementer++;
		return true;
	}

	/*
	 * Kjøretid O(n - index). Siden index i gjennomsnitt er rundt midten av lista blir
	 * gjennomsnittlig kjøretid O(n/2) som er O(n)
	 * 
	 */
	@Override
	public void add(int index, T element) {
		if (antallElementer >= elementene.length) {
			okKapasitet();
		}

		// Flytt elementene
		for (int i=antallElementer - 1;i>=index;i--) {
			elementene[i+1] = elementene[i];
		}
		
		// Setter inn elementet
		elementene[index] = element;
		antallElementer++;
	}
	
	/*
	 * Kjøretid O(n)
	 */
	public void addFirst(T element) {
		add(0, element);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Eksempel på metode som blir mer effektiv med en egen implementasjon
	 * 
	 * Antall elementer i c: m
	 * Antall elementer i this: n
	 * 
	 * Kjøretid naiv: O(m * n), Omega(m) ekvivalent med O(n^2)
	 * 
	 * Kjøretid O(n) + O(n) + O(m) = O(m + n) ekvivalent med O(n)
	 * 
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		/* Naiv versjon:
		for (T objekt: c) {
			add(index, objekt);
		}*/
		
		int forflytning = c.size();
		if (antallElementer + forflytning > elementene.length) {
			okKapasitet(antallElementer*2 + forflytning);
		}
		
		for (int i=antallElementer - 1;i>=index;i--) {
			elementene[i+forflytning] = elementene[i];
		}

		for (T objekt: c) {
			elementene[index] = objekt;
			index++;
		}
		
		return true;
	}

	/*
	 * Lager en ny array, kjøretid O(1)
	 */
	@Override
	public void clear() {
		elementene = new Object[elementene.length];
		antallElementer = 0;
	}

	/*
	 * Bruker indexOF implementasjonen her, O(n)
	 */
	@Override
	public boolean contains(Object o) {
		int resultat = indexOf(o);
		if (resultat != -1) return true;
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Kjøretid O(1)
	 * 
	 * (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public T get(int index) {
		Object element = elementene[index];
		T resultat = (T)element;
		return resultat;
	}

	/*
	 * Sekvensielt søk (Engelsk Sequensial search), algoritme fra FinnElement eksemplet, med
	 * kjøretid O(n)
	 */
	@Override
	public int indexOf(Object o) {
		for (int index = 0;index < antallElementer;index++) {
			if (elementene[index].equals(o)) return index;
		}
		return -1;
	}

	// Kjøretid O(1)
	@Override
	public boolean isEmpty() {
		if (antallElementer == 0) return true;
		return false;
	}

	/*
	 * Lager et objekt av en indre iteratorklasse.
	 * 
	 * Spesifisert av Iterable<T> grensesnittet.
	 */
	@Override
	public Iterator<T> iterator() {
		return new MinArrayListIterator<>();
	}

	/*
	 * Som indexOf bortsett fra at den går i motsatt retning.
	 */
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Kjøretid 
	 * indexOF: O(n)
	 * remove(index): O(n)
	 * Total O(n) + O(n) = O(n)
	 */
	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index == -1) return false;
		remove(index);
		return true;
	}

	/*
	 * Kjøretid O(antallElementer-index)
	 * 
	 * Regnes som O(n)
	 */
	@Override
	public T remove(int index) {
		T element = (T)elementene[index];
		for (int i=index;i<antallElementer - 1;i++) {
			elementene[i] = elementene[i+1];
		}
		antallElementer--;
		return element;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Kjøretid O(1)
	 * 
	 */
	@Override
	public T set(int index, T element) {
		T resultat = (T)elementene[index];
		elementene[index] = element;
		return resultat;
	}

	/*
	 * Kjøretid O(1)
	 */
	@Override
	public int size() {
		return antallElementer;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * Kjøretid O(n) pga. kopiering av data
	 * Minnebruk O(n) pga. at den lager ei ny liste
	 */
	@Override
	public Object[] toArray() {
		Object[] resultat = new Object[antallElementer];
		for (int i=0;i<antallElementer;i++) {
			resultat[i] = elementene[i];
		}
		return resultat;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Iterator for MyArrayList, definert som en ikke-statisk indre klasse
	 * siden objekter av iteratorklassen naturlig tilhører objekter av
	 * listeklassen, samt at objekter av iteratorklasser ofte trenger
	 * tilgang til den interne implementasjonen av samlingsobjektet de
	 * skal gå gjennom.
	 */
	public class MinArrayListIterator <T> implements Iterator<T> {

		private int posisjon; // Iteratoren sin posisjon i lista.
		
		public MinArrayListIterator() {
			posisjon = 0;
		}
		
		@Override
		public boolean hasNext() {
			if (posisjon < antallElementer) return true;
			return false;
		}

		@Override
		public T next() {
			T element = (T)elementene[posisjon];
			posisjon++;
			return element;
		}

	}

	
}
