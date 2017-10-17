package oving7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MittHashMap <K, V> implements Map<K, List<V>>, Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final double FYLLINGSGRAD = 0.7;
	
	private class Innslag implements Serializable{
		
		private static final long serialVersionUID = 1L;
		K nokkel;
		List<V> verdier;
		
		public Innslag(K key, V value) {
			nokkel = key;
			verdier = new ArrayList<>();
			verdier.add(value);
		}
		
		public Innslag(K key, List<V> values) {
			nokkel = key;
			verdier = new ArrayList<>();
			verdier.addAll(values);
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
		
		public void addValues(List<V> values) {
			verdier.addAll(values);
		}
		
		public void removeValue(V value) {
			if (verdier.contains(value)) {
				verdier.remove(value);
			}
		}
		
	}
	
	private Object[] elementene;	// Array som inneholder elementene
	private int antallElementer;	// Antall elementer i lista nå
	
	
	public MittHashMap(int startkapasitet) {
		elementene = new Object[startkapasitet];
		antallElementer = 0;
		for (int i=0;i<elementene.length;i++) {
			elementene[i] = null;
		}
	}

	
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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<V> get(Object key) {
		K nokkel = (K)key;
		int posisjon = beregnPosisjon(nokkel);
		//System.out.println("Nøkkel: "+ nokkel + " | Posisjon: " + posisjon); //Debug
		if (elementene[posisjon] != null) {
			Innslag tempInnslag = (Innslag) elementene[posisjon];
			if (tempInnslag.nokkel.equals(nokkel)) {
				return tempInnslag.verdier;
			} else {
				posisjon = kvadratiskLeting(nokkel);
				//System.out.println("kvadratiskLeting Posisjon: " + posisjon); //Debug
				if (posisjon != -1) {
					tempInnslag = (Innslag) elementene[posisjon];
					//System.out.println("Nøkkel: "+ nokkel + " | Posisjon: " + posisjon); //Debug
					return tempInnslag.verdier;
				} else return null;
			}			
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
		Innslag tempInnslag = null;
		
		if (elementene[posisjon] == null) {
			elementene[posisjon] = new Innslag(key, value);
			antallElementer++;
		} else {
			tempInnslag = (Innslag) elementene[posisjon];
			
			if (tempInnslag.nokkel.equals(key)) {
				tempInnslag.addValue(value);
				return tempInnslag.verdier.get(tempInnslag.verdier.size()-1);
			} else {
				posisjon = kvadratiskProving(key);
				if (elementene[posisjon] == null) {
					elementene[posisjon] = new Innslag(key, value);
					antallElementer++;
				} else {
					tempInnslag = (Innslag) elementene[posisjon];
					tempInnslag.addValue(value);
					return tempInnslag.verdier.get(tempInnslag.verdier.size()-1);
				}
			}
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
			Innslag tempInnslag = (Innslag) elementene[posisjon];
			tempInnslag.addValues(values);
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
		
		return Math.abs(nokkel.hashCode() % elementene.length);
	}
	
	/*
	 * Kjøretid typisk O(1), worst case O(n)
	 */
	private int kvadratiskProving(K nokkel) {
		int forsoksplass = beregnPosisjon(nokkel);
		int i = 0;
		Innslag innslaget = (Innslag) elementene[forsoksplass];
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
	
	private int kvadratiskLeting(K nokkel) {
		int forsoksplass = beregnPosisjon(nokkel);
		int i = 0;
		Innslag innslaget = (Innslag) elementene[forsoksplass];
		while (elementene[forsoksplass] != null && !innslaget.nokkel.equals(nokkel)) {
			i++;
			if (i > elementene.length/2) return -1;
			forsoksplass += 2*i-1;
			forsoksplass %= elementene.length;
			innslaget = (Innslag)elementene[forsoksplass];
		}
		if (elementene[forsoksplass] != null) {
			return forsoksplass;
		}
		return -1;
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
		System.out.println("Utvider listen...");
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
				tempInnslag = (Innslag) o;
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
