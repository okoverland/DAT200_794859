package oving6;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import emner.Emne;
import util.Pair;

/*
 * Naivt binært søketre: holder seg ikke balansert!
 */
public class BinaertSoketre <K extends Comparable<? super K>, V> implements SortedMap<K, V> {
	private class Binaertrenode {
		K nokkel;
		V verdi;
		Binaertrenode venstrebarn;
		Binaertrenode hoyrebarn;
		Binaertrenode forelder;
		int nivaa;
		
		public Binaertrenode(K key, V value) {
			nokkel = key;
			verdi = value;
			venstrebarn = null;
			hoyrebarn = null;
			forelder = null;
			nivaa = 0;
		}
		
		public boolean bladNode() {
			if (venstrebarn == null && hoyrebarn == null) return true;
			return false;
		}
		
	}
	
	private Binaertrenode rot = null;
			
	@Override
	public void clear() {
		rot = null;
	}

	@Override
	public boolean containsKey(Object key) {
		if (get(key) == null) return false;
		return true;
	}

	/*
	 * Må traversere treet til man finner det. O(n). Ikke implementert!
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Kjøretid O(høyden til treet)
	 */
	@Override
	public V get(Object key) {
		if (rot == null) return null;
		K nokkel = (K)key;
		return get(nokkel, rot);
	}
	
	private V get(K key, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				return get(key, noden.venstrebarn);
			} else {
				return null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				return get(key, noden.hoyrebarn);
			} else {
				return null;
			}
		} else {
			return noden.verdi;
		}

	}

	@Override
	public boolean isEmpty() {
		if (rot == null) return true;
		return false;
	}

	/*
	 * Binære søketrær: Venstre barn inneholder alle nøkler lavere enn noden, høyre
	 * barn inneholder alle nøkler høyere enn noden. Tillater ikke duplikater.
	 * 
	 * Kjøretid O(høyden til treet)
	 * 
	 */
	@Override
	public V put(K key, V value) {
		if (key == null) throw new IllegalArgumentException("Dette søketreet tillater ikke null nøkler");
		if (value == null) throw new IllegalArgumentException("Dette søketreet tillater ikke null verdier");
		if (rot == null) {
			rot = new Binaertrenode(key, value);
		} else {
			return put(key, value, rot);
		}
		return null;
	}
	
	// Rekursiv metode som finner riktig plass i treet.
	private V put(K key, V value, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				return put(key, value, noden.venstrebarn);
			} else {
				noden.venstrebarn = new Binaertrenode(key, value);
				noden.venstrebarn.forelder = noden;
				noden.venstrebarn.nivaa = noden.nivaa + 1;
				return null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				return put(key, value, noden.hoyrebarn);
			} else {
				noden.hoyrebarn = new Binaertrenode(key, value);
				noden.hoyrebarn.forelder = noden;
				noden.hoyrebarn.nivaa = noden.nivaa + 1;
				return null;
			}
		} else {
			V verdi = noden.verdi;
			noden.verdi = value;
			return verdi;
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Fjerner en node. O(høyde) kjøretid for å finne det man skal fjerne og så
	 * O(1) tid for å fjerne det.
	 */
	@Override
	public V remove(Object key) {
		if (rot == null) return null;
		K nokkel = (K)key;
		return remove(nokkel, rot);
	}
	
	/*
	 * Rekursiv metode som fjerner et element. Den går nedover i treet til den finner
	 * noden som skal fjernes. Når noden skal fjernes er det tre alternativer:
	 * - Noden er en bladnode og kan fjernes uten problemer
	 * - Noden har kun ett barn. Forelder kan referere direkte til dette barnet.
	 * - Noden har to barn. Erstatter noden med laveste node i høyre subtre og sletter
	 *   denne i stedet. Laveste node i et subtre har ingen venstrebarn.
	 */
	private V remove(K key, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				return remove(key, noden.venstrebarn);
			} else {
				return null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				return remove(key, noden.hoyrebarn);
			} else {
				return null;
			}
		} else {
			V fjernetVerdi = noden.verdi;
			// Tilfelle 1, noden er en bladnode
			if (noden.bladNode()) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = null;
				} else {
					noden.forelder.hoyrebarn = null;
				}
			}
			// Tilfelle 2, noden har ett barn
			if (noden.venstrebarn != null && noden.hoyrebarn == null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.venstrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.venstrebarn;
				}
				noden.venstrebarn.nivaa--;
			}
			if (noden.venstrebarn == null && noden.hoyrebarn != null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.hoyrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.hoyrebarn;
				}	
				noden.hoyrebarn.nivaa--;
			}
			// Tilfelle 3, noden har to barn
			if (noden.venstrebarn != null && noden.hoyrebarn != null) {
				Binaertrenode minsteIHoyreSubtre = finnMinste(noden.hoyrebarn);
				noden.nokkel = minsteIHoyreSubtre.nokkel;
				noden.verdi = minsteIHoyreSubtre.verdi;
				remove(minsteIHoyreSubtre.nokkel, minsteIHoyreSubtre);
			}
			return fjernetVerdi;
		}
	}

	/*
	 * Typisk O(høyden til treet)
	 */
	public V finnMinste() {
		if (rot == null) return null;
		return finnMinste(rot).verdi;
	}
	
	private Binaertrenode finnMinste(Binaertrenode noden) {
		if (noden.venstrebarn == null) {
			return noden;
		} else return finnMinste(noden.venstrebarn);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comparator<? super K> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K firstKey() {
		if (rot == null) return null;
		return finnMinste(rot).nokkel;
	}

	@Override
	public SortedMap<K, V> headMap(K toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K lastKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Metode for å skrive ut et tre. Brukes for å teste trestrukturen.
	 */
	public void skrivUtTre() {
		skrivUtTre(rot);
	}
	
	private void skrivUtTre(Binaertrenode noden) {
		if (noden == null) return;
		System.out.print("Nokkel: " + noden.nokkel + " verdi: " + noden.verdi + " nivå: " + noden.nivaa + " ");
		if (noden.venstrebarn != null) {
			System.out.print("Venstre: " + noden.venstrebarn.nokkel + " ");
		}
		if (noden.hoyrebarn != null) {
			System.out.print("Høyre: " + noden.hoyrebarn.nokkel + " ");			
		}
		System.out.println();
		skrivUtTre(noden.venstrebarn);
		skrivUtTre(noden.hoyrebarn);
	} 
	
	//Oppgave 2a
	private int leaves;
	
	public int getAntallBladnoder() {
		if (rot == null) throw new NullPointerException("Søketreet er tomt!");
		leaves = 0;
		recursiveLeaves(rot);
		return leaves;
	}
	
	private void recursiveLeaves(Binaertrenode node) {
		if (node.bladNode()) leaves++;
		if (node.venstrebarn != null) recursiveLeaves(node.venstrebarn);
		if (node.hoyrebarn != null) recursiveLeaves(node.hoyrebarn);
		return;
		
	}
	//Oppgave 2b
	public void skrivUtNivaa(int n) {
		if (rot == null) throw new NullPointerException("Søketreet er tomt!");
		if (n < 0) throw new IllegalArgumentException("Det finnes ikke nivå lavere enn 0!");
		skrivUtNivaa(n, rot);
	}
	
	private void skrivUtNivaa(int n, Binaertrenode node) {
		if (node.nivaa == n) {
			System.out.printf("%s%n", node.verdi);
			return;
		}
		if (node.bladNode()) return;
		if (node.venstrebarn != null) skrivUtNivaa(n, node.venstrebarn);
		if (node.hoyrebarn != null) skrivUtNivaa(n, node.hoyrebarn);	
		
	}
	
	
	/*
	 * Iterator laget for testing. Returnerer et egendefinert Pair objekt heller enn
	 * den indre klassen i Map som returneres av entrySet metoden.
	 */
	public Iterator<Pair<K, V>> iterator() {
		return new TreeIterator();
	}

	private class TreeIterator implements Iterator<Pair<K, V>> {

		private class StabelElement {
			Binaertrenode noden;
			int teller;
			
			public StabelElement(Binaertrenode noden, int teller) {
				this.noden = noden;
				this.teller = teller;
			}
		}
		
		private Stack<StabelElement> stabelen;
		
		public TreeIterator() {
			stabelen = new Stack<>();
			stabelen.push(new StabelElement(rot, 0));
		}
		
		@Override
		public boolean hasNext() {
			if (stabelen.isEmpty()) return false;
			return true;
		}

		@Override
		public Pair<K, V> next() {
			while (!stabelen.isEmpty()) {
				StabelElement elementet = stabelen.pop();
				if (elementet.teller == 0) {
					elementet.teller++;
					stabelen.push(elementet);
					if (elementet.noden.venstrebarn != null) {
						stabelen.push(new StabelElement(elementet.noden.venstrebarn, 0));
					}
					continue;
				}
				if (elementet.teller == 1) {
					elementet.teller++;
					if (!(elementet.noden.hoyrebarn == null)) stabelen.push(new StabelElement(elementet.noden.hoyrebarn, 0));
					return new Pair<>(elementet.noden.nokkel, elementet.noden.verdi);					
				}
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		Emne testemne = new Emne("DAT100", "Objektorientert programmering", 'V');
		BinaertSoketre<String, Emne> emneliste = new BinaertSoketre<>();
		emneliste.put(testemne.getEmnekode(), testemne);
		emneliste.put("MAT100", new Emne("MAT100", "Matematikk 1", 'H'));
		emneliste.put("MAT200", new Emne("MAT200", "Matematikk 2", 'V'));
		emneliste.put("ELE210", new Emne("ELE210", "Datamaskinarkitektur", 'H'));
		emneliste.put("DAT200", new Emne("DAT200", "Algoritmer", 'H'));
		
		System.out.println("Iterator over søketre: ");
		Iterator<Pair<String, Emne>> iter = emneliste.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println();
		
		System.out.println("Prøver å hente ELE210: " + emneliste.get("ELE210"));
		System.out.println("Prøver å hente ELE230: " + emneliste.get("ELE230"));
		System.out.println();
		
		System.out.println("skrivUtTre(): ");
		emneliste.skrivUtTre();		
		System.out.println("Antall bladnoder: " + emneliste.getAntallBladnoder());
		System.out.println();
		
		emneliste.remove("MAT100");
		System.out.println("Fjerner MAT100:");
		iter = emneliste.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println();
		
		System.out.println("Prøver å hente ELE210: " + emneliste.get("ELE210"));
		System.out.println("Prøver å hente ELE230: " + emneliste.get("ELE230"));
		System.out.println("Legger til AAA100 ... ");
		emneliste.put("AAA100", new Emne("AAA100", "Annanas Algoritme Analyse", 'V'));
		
		System.out.println();
		
		emneliste.skrivUtTre();
		System.out.println("Antall bladnoder: " + emneliste.getAntallBladnoder());
		System.out.println();
		
		System.out.println("Skriver ut nivå 0: ");
		emneliste.skrivUtNivaa(0);
		System.out.println("Skriver ut nivå 1: ");
		emneliste.skrivUtNivaa(1);
		System.out.println("Skriver ut nivå 2: ");
		emneliste.skrivUtNivaa(2);
		System.out.println("Skriver ut nivå 3: ");
		emneliste.skrivUtNivaa(3);
		System.out.println();
		
		
	}
}
