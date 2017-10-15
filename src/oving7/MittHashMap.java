package oving7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Eksempel HashMap implementasjon, uferdig.
 * 
 * Legg merke til at denne i motsetning til AVLTre implementerer
 * Map og ikke SortedMap samt at den ikke stiller noen
 * betingelse om sammenliknbarhet på nøkkelen.
 */
public class MittHashMap <K, V> implements Map<K, List<V>> {
	public static final double FYLLINGSGRAD = 0.7;
	
	// Representerer et nøkkel - verdi par. Sammenliknes
	// på nøkkelen.
	private class Innslag {
		K nokkel;
		List<V> verdier;
		
		public Innslag(K key) {
			verdier = new ArrayList<>(2);
			nokkel = key;
		}
		
		public Innslag(K key, V value) {
			this(key);
			verdier.add(value);
		}
		
		public Innslag(K key, List<V> values) {
			this(key);
			for (V value : values) verdier.add(value);
		}
		
		@Override public int hashCode() {
			return nokkel.hashCode();
		}
		
		@Override public boolean equals(Object o) {
			return nokkel.equals(o);
		}
		public void addValue(V value) {
			verdier.add(value);
		}
	}
	
	private Object[] elementene;	// Array som inneholder elementene
	private int antallElementer;	// Antall elementer i lista nå
	
	/*
	 * På samme måte som en ArrayList trenger også et HashMap
	 * en startkapasitet
	 */
	public MittHashMap(int startkapasitet) {
		elementene = new Object[startkapasitet];
		antallElementer = 0;
		for (int i=0;i<elementene.length;i++) {
			elementene[i] = null;
		}
	}

	/*
	 * Kjøretid O(tabellstørrelse) siden den setter alle 
	 * referansene til null
	 */
	@Override
	public void clear() {
		antallElementer = 0;
		for (int i=0;i<elementene.length;i++) {
			elementene[i] = null;
		}
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Entry<K, List<V>>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Kjøretid O(1) best og average case, O(n) worst case på grunn av kvadratiskProving
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<V> get(Object key) {
		K nokkel = (K)key;
		int verdi = beregnPosisjon(nokkel);
		if (elementene[verdi] != null) {
			Innslag tempInnslag = (MittHashMap<K, V>.Innslag) elementene[verdi]; 
			return tempInnslag.verdier;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (antallElementer == 0) return true; 
		return false;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public V putSingle(K key, V value) {
		if (antallElementer >= (elementene.length)*FYLLINGSGRAD) {
			utvidLista();
		}
		int posisjon = beregnPosisjon(key);
		
		if (elementene[posisjon] == null) {
			elementene[posisjon] = new Innslag(key, value);
			antallElementer++;
		} else {
			Innslag tempInnslag = (MittHashMap<K, V>.Innslag) elementene[posisjon];
			tempInnslag.addValue(value);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V> put(K key, List<V> values) {
		if (antallElementer >= (elementene.length)*FYLLINGSGRAD) {
			utvidLista();
		}
		int posisjon = beregnPosisjon(key);
		
		if (elementene[posisjon] == null) {
			elementene[posisjon] = new Innslag(key, values);
			antallElementer++;
		} else {
			Innslag tempInnslag = (MittHashMap<K, V>.Innslag) elementene[posisjon];
			for (V value : values) {
				tempInnslag.addValue(value);
			}
		}
		return null;
	}
	

	@Override
	public void putAll(Map<? extends K, ? extends List<V>> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<V> remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return antallElementer;
	}

	@Override
	public Collection<List<V>> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int beregnPosisjon(K nokkel) {
		
		return Math.abs(nokkel.hashCode()) % elementene.length;
	}
	
	/*
	 * Kjøretid typisk O(1), worst case O(n)
	 */
	private int kvadratiskProving(K nokkel) {
		int forsoksplass = (beregnPosisjon(nokkel)) % elementene.length;
		int i = 0;
		Innslag innslaget = (Innslag)elementene[forsoksplass];
		while (elementene[forsoksplass] != null && !innslaget.nokkel.equals(nokkel)) {
			i++;
			// Fra beviset: For tabellengde som er et primtall så er de første
			// tabellengde/2 plassene unike.
			if (i > elementene.length/2) throw new RuntimeException("For full!");
			forsoksplass += 2*i-1;
			forsoksplass %= elementene.length;
			innslaget = (Innslag)elementene[forsoksplass];
		}
		return forsoksplass;
	}
	
	/*
	 * Utvider tabellen til størrelse lik det første primtallet høyere enn
	 * det dobbelte av gammel størrelse
	 * 
	 * Engelsk: Rehashing.
	 * 
	 * Kjøretid O(lengde på gammel liste)
	 * Ved 70% fylling O(n)
	 */
	@SuppressWarnings("unchecked")
	private void utvidLista() {
		// Lagrer referanse til gammel liste
		Object[] gammelListe = elementene;
		
		// Lager ny liste
		int nyAntallElementer = elementene.length*2 + 1;
		while (!erPrimtall(nyAntallElementer)) nyAntallElementer+=2;
		
		// Erstatter gammel liste med tom ny liste i hovedstrukturen slik at jeg kan bruke
		// put() metoden
		elementene = new Object[nyAntallElementer];
		antallElementer = 0;
		Innslag tempInnslag;
		
		for (Object o : gammelListe) {
			if (o != null) {
				tempInnslag = (MittHashMap<K, V>.Innslag) o;
				put(tempInnslag.nokkel,tempInnslag.verdier);
			}
		}
		
	}
	
	private boolean erPrimtall(int tall) {
		for (int i=2;i<=Math.sqrt(tall);i++) {
			if (tall % i == 0) return false;
		}
		return true;
	}
	
	public void printTable() {
		for (int i=0;i<elementene.length;i++) {
			Innslag innslag = (MittHashMap<K, V>.Innslag) elementene[i];
			System.out.print(i + ": ");
			if (innslag == null) {
				System.out.println("null");
			} else {
				System.out.println(innslag.nokkel + ", " + innslag.verdier);
			}
		}
	}
	
	public static void main(String[] args) {
		MittHashMap<Integer, String> test = new MittHashMap<>(7);
		test.putSingle(1, "en");
		test.putSingle(2, "to");
		test.putSingle(30, "tretti");
		test.putSingle(14, "fjorten");
		test.putSingle(10, "ti");
		test.putSingle(2, "två");
		test.putSingle(13, "tretten");
		test.putSingle(25, "tjuefem");
		test.putSingle(9, "ni");
		System.out.println("get(2): " + test.get(2));
		System.out.println("get(12): " + test.get(12));
		System.out.println("get(13): " + test.get(13));
		System.out.println("get(30): " + test.get(30));
		System.out.println("get(9): " + test.get(9));
		System.out.println("Størrelse: " + test.size());
		test.printTable();
	}

}
