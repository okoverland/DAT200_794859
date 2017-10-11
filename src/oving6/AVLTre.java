package oving6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import emner.Emne;
import util.Pair;

/*
 * Binærtre som holder seg balansert. Fungerer korrekt for put, men har ikke
 * implementert rotasjonssjekker for remove. Rotasjon for remove er oppgave 2g) i
 * øving 6.
 * 
 */
public class AVLTre <K extends Comparable<? super K>, V> implements SortedMap<K, V> {
	private class Binaertrenode {
		K nokkel;
		V verdi;
		int hoyde;			// Trenger dette for å holde treet balansert
		Binaertrenode venstrebarn;
		Binaertrenode hoyrebarn;
		Binaertrenode forelder;
		
		public Binaertrenode(K key, V value) {
			nokkel = key;
			verdi = value;
			hoyde = 1;
			venstrebarn = null;
			hoyrebarn = null;
			forelder = null;
		}
		public boolean bladNode() {
			if (venstrebarn == null && hoyrebarn == null) return true;
			return false;
		}
	}
	
	private Binaertrenode rot = null;
	
	/*
	 * Kjøretid O(1) for selve programmet, O(n) for søppeltømmeren.
	 */
	@Override
	public void clear() {
		rot = null;
	}

	/*
	 * Kjøretid som get(key), O(log(n))
	 */
	@Override
	public boolean containsKey(Object key) {
		if (get(key) == null) return false;
		return true;
	}

	/*
	 * Må traversere treet for å sjekke om verdien ligger i det. O(n) kjøretid.
	 */
	@Override
	public boolean containsValue(Object value) {
		if (isEmpty()) throw new NullPointerException("Treet er tomt!");
				
		return values().stream().anyMatch(v -> v.equals(value));

	}
	

	
	/*
	 * Kjøretid O(log(n))
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
	
	/*
	 * Kjøretid O(log(n))
	 */
	public K getNextKey(K key) {
		if (rot == null) return null;
		return getNextKey(key, rot);
	}
	
	private K getNextKey(K key, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			K kandidat = getNextKey(key, noden.venstrebarn);
			if (kandidat.compareTo(noden.nokkel) < 0) {
				return kandidat;
			} else return noden.nokkel;
		} else {
			if (noden.hoyrebarn != null) {
				return getNextKey(key, noden.hoyrebarn);
			} else return null;
		}
	}

	/*
	 * Kjøretid O(1)
	 */
	@Override
	public boolean isEmpty() {
		if (rot == null) return true;
		return false;
	}

	/*
	 * Kjøretid O(log(n))
	 * 
	 */
	@Override
	public V put(K key, V value) {
		System.out.println("Setter inn nøkkel: " + key);
		if (key == null) throw new IllegalArgumentException("Dette søketreet tillater ikke null nøkler");
		if (value == null) throw new IllegalArgumentException("Dette søketreet tillater ikke null verdier");
		if (rot == null) {
			rot = new Binaertrenode(key, value);
		} else {
			return put(key, value, rot);
		}
		return null;
	}
	
	private V put(K key, V value, Binaertrenode noden) {
		V verdi;
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				verdi = put(key, value, noden.venstrebarn);
			} else {
				noden.venstrebarn = new Binaertrenode(key, value);
				noden.venstrebarn.forelder = noden;
				verdi =  null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				verdi = put(key, value, noden.hoyrebarn);
			} else {
				noden.hoyrebarn = new Binaertrenode(key, value);
				noden.hoyrebarn.forelder = noden;
				verdi =  null;
			}
		} else {
			verdi = noden.verdi;
			noden.verdi = value;
		}
		sjekkNodeHoyde(noden);
		return verdi;
	}
	
	// Hjelpemetode for å redusere mengden kode for rotasjonene.
	private int hoyde(Binaertrenode noden) {
		if (noden == null) return 0;
		return noden.hoyde;
	}
	
	// Regner ut ny høyde for noden og sjekker om den må roteres
	private void sjekkNodeHoyde(Binaertrenode noden) {
		int venstrehoyde = hoyde(noden.venstrebarn);
		int hoyrehoyde = hoyde(noden.hoyrebarn);
		if (venstrehoyde > (hoyrehoyde + 1)) {
			if (hoyde(noden.venstrebarn.venstrebarn) > hoyde(noden.venstrebarn.hoyrebarn)) {
				roterMedVenstreBarn(noden);
			} else {
				dobbeltVenstre(noden);
			}
		}
		if (hoyrehoyde > (venstrehoyde + 1)) {
			if (hoyde(noden.hoyrebarn.hoyrebarn) > hoyde(noden.hoyrebarn.venstrebarn)) {
				roterMedHoyreBarn(noden);
			} else {
				dobbeltHoyre(noden);
			}
		}
		noden.hoyde = Math.max(venstrehoyde, hoyrehoyde) + 1;		
	}
	
	// Regner ut høyde til noden uten å sjekke for rotasjoner. Brukes fra rotasjonsmetodene
	private void sjekkNodeHoydeUtenRotasjon(Binaertrenode noden) {
		int venstrehoyde = hoyde(noden.venstrebarn);
		int hoyrehoyde = hoyde(noden.hoyrebarn);
		noden.hoyde = Math.max(venstrehoyde, hoyrehoyde) + 1;		
	}
	
	private void roterMedVenstreBarn(Binaertrenode noden) {
		Binaertrenode venstrebarn = noden.venstrebarn;
		Binaertrenode forelder = noden.forelder;
		System.out.println("Roterer " + noden.nokkel + " med " + venstrebarn.nokkel);
		if (forelder != null) {
			if (forelder.venstrebarn == noden) {
				forelder.venstrebarn = venstrebarn;
			} else forelder.hoyrebarn = venstrebarn;
		} else {
			rot = venstrebarn;
		}
		Binaertrenode subtre2 = venstrebarn.hoyrebarn;
		venstrebarn.hoyrebarn = noden;
		noden.venstrebarn = subtre2;
		noden.forelder = venstrebarn;
		venstrebarn.forelder = forelder;
		sjekkNodeHoydeUtenRotasjon(noden);
		sjekkNodeHoydeUtenRotasjon(venstrebarn);
	}

	private void roterMedHoyreBarn(Binaertrenode noden) {
		Binaertrenode hoyrebarn = noden.hoyrebarn;
		Binaertrenode forelder = noden.forelder;
		System.out.println("Roterer " + noden.nokkel + " med " + hoyrebarn.nokkel);
		if (forelder != null) {
			if (forelder.venstrebarn == noden) {
				forelder.venstrebarn = hoyrebarn;
			} else forelder.hoyrebarn = hoyrebarn;
		} else {
			rot = hoyrebarn;
		}
		Binaertrenode subtre2 = hoyrebarn.venstrebarn;
		hoyrebarn.venstrebarn = noden;
		noden.hoyrebarn = subtre2;
		noden.forelder = hoyrebarn;
		hoyrebarn.forelder = forelder;
		sjekkNodeHoydeUtenRotasjon(noden);
		sjekkNodeHoydeUtenRotasjon(hoyrebarn);
	}

	private void dobbeltVenstre(Binaertrenode noden) {
		roterMedHoyreBarn(noden.venstrebarn);
		roterMedVenstreBarn(noden);
	}

	private void dobbeltHoyre(Binaertrenode noden) {
		roterMedVenstreBarn(noden.hoyrebarn);
		roterMedHoyreBarn(noden);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Kjøretid O(log(n))
	 */
	@Override
	public V remove(Object key) {
		if (rot == null) return null;
		K nokkel = (K)key;
		return remove(nokkel, rot);
	}
	
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
			V fjernetVerdi = null;
			// Tilfelle 1, noden er en bladnode
			if (noden.venstrebarn == null && noden.hoyrebarn == null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = null;
				} else {
					noden.forelder.hoyrebarn = null;
				}
				justerTrehoyde(noden.forelder);
			}
			// Tilfelle 2, noden har ett barn
			if (noden.venstrebarn != null && noden.hoyrebarn == null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.venstrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.venstrebarn;
				}				
				justerTrehoyde(noden.forelder);
			}
			if (noden.venstrebarn == null && noden.hoyrebarn != null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.hoyrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.hoyrebarn;
				}				
				justerTrehoyde(noden.forelder);
			}
			if (noden.venstrebarn != null && noden.hoyrebarn != null) {
				// Tilfelle 3, noden har to barn
				Binaertrenode minsteIHoyreSubtre = finnMinste(noden.hoyrebarn);
				fjernetVerdi = noden.verdi;
				noden.verdi = minsteIHoyreSubtre.verdi;
				remove(minsteIHoyreSubtre.nokkel, minsteIHoyreSubtre);
				justerTrehoyde(minsteIHoyreSubtre.forelder);
			}
			return fjernetVerdi;
		}
	}
	
	// Justerer trehøyde etter en remove, går automatisk oppover i treet og
	// oppdaterer høydene ved behov.
	private void justerTrehoyde(Binaertrenode noden) {
		while (noden != null) {
			int venstrehoyde = hoyde(noden.venstrebarn);
			int hoyrehoyde = hoyde(noden.hoyrebarn);
			noden.hoyde = Math.max(venstrehoyde, hoyrehoyde) + 1;
			noden = noden.forelder;
		}
	}

	/*
	 * Kjøretid O(log(n))
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
		
		class ValuesAsCollection {
			
			private Collection<V> listen;
			
			public ValuesAsCollection() {
				listen = new ArrayList<>();
				addValues(rot);
			}
			
			public void addValues(Binaertrenode noden) {
				listen.add(noden.verdi);
				if (noden.venstrebarn != null) addValues(noden.venstrebarn);
				if (noden.hoyrebarn != null) addValues(noden.hoyrebarn);
			}
			
			public Collection<V> getCollection() {
				return listen;
			}
		}
		
		ValuesAsCollection coll = new ValuesAsCollection();
		
		return coll.getCollection();
	}
		
	/*
	 * Metode for å skrive ut et tre. Brukes for å teste trestrukturen.
	 */
	
	public void skrivUtTre() {
		skrivUtTre(rot);
	}
	
	private void skrivUtTre(Binaertrenode noden) {
		if (noden == null) return;
		System.out.print("Nokkel: " + noden.nokkel + " verdi: " + noden.verdi + " ");
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

	
	// Oppgave 2 d) Pre-order iterator. Bruker koden fra iterator() og bytter ut stack med en queue.
	
	public Iterator<Pair<K,V>> preOrderIterator() {
		return new PreOrderTreeIterator();
	}
	
	private class PreOrderTreeIterator implements Iterator<Pair<K,V>> {
		
		private class QElement {
			Binaertrenode noden;
			int teller;
			
			public QElement(Binaertrenode noden, int teller) {
				this.noden = noden;
				this.teller = teller;
			}
		}
		
		private Queue<QElement> koen;
		
		public PreOrderTreeIterator() {
			koen = new LinkedList<>();
			koen.add(new QElement(rot, 0));
		}
		
		@Override
		public boolean hasNext() {
			if (koen.isEmpty()) return false;
			return true;
		}

		@Override
		public Pair<K, V> next() {
			while (!koen.isEmpty()) {
				QElement elementet = koen.remove();
				if (elementet.teller == 0) {
					elementet.teller++;
					koen.add(elementet);
					if (elementet.noden.venstrebarn != null) {
						koen.add(new QElement(elementet.noden.venstrebarn, 0));
					}
					continue;
				}
				if (elementet.teller == 1) {
					elementet.teller++;
					if (!(elementet.noden.hoyrebarn == null)) koen.add(new QElement(elementet.noden.hoyrebarn, 0));
					return new Pair<>(elementet.noden.nokkel, elementet.noden.verdi);					
				}
			}
			return null;
		}
		
	}
	
	/*
	 * Iterator laget for testing. Returnerer et egendefinert Pair objekt heller enn
	 * den indre klassen i Map som returneres av entrySet metoden. Dette er en
	 * InOrder iterator.
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
		AVLTre<String, Emne> emneliste = new AVLTre<>();
		emneliste.put(testemne.getEmnekode(), testemne);
		emneliste.put("MAT100", new Emne("MAT100", "Matematikk 1", 'H'));
		emneliste.put("MAT200", new Emne("MAT200", "Matematikk 2", 'V'));
		emneliste.put("ELE210", new Emne("ELE210", "Datamaskinarkitektur", 'H'));
		emneliste.put("DAT200", new Emne("DAT200", "Algoritmer", 'H'));
		System.out.println();
		emneliste.skrivUtTre();
		System.out.println();
		System.out.println("In-order iterator:");
		Iterator<Pair<String, Emne>> iter = emneliste.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println();
		System.out.println("Pre-order iterator (oppgave 2d):");
		Iterator<Pair<String, Emne>> iterPre = emneliste.preOrderIterator();
		while (iterPre.hasNext()) {
			System.out.println(iterPre.next());
		}
		System.out.println();
		System.out.println(emneliste.get("ELE210"));
		System.out.println(emneliste.get("ELE230"));
		System.out.println();
		emneliste.skrivUtTre();		
	}
}
