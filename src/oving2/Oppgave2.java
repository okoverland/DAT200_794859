package oving2;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Iterator;

public class Oppgave2 {
	
	public static <T> Collection<T> listSection(Collection<? extends T> lhs, Collection<? extends T> rhs) {
		
		Collection<T> section = new ArrayList<>();;
		
		//Iterator<T> iterator = lhs.iterator();
		
		lhs.forEach(e -> { if (rhs.contains(e)) section.add(e); });
		/*
		while ( iterator.hasNext() ) {
			T element = iterator.next();
						
			if ( rhs.contains(element) ) {
				section.add(element);
			}
		}*/
		
		return section;
	}
}
