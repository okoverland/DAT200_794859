package oving3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BufferedArrayList <T> implements List<T> {
	
	private Object [] theElements;
	private int index;
	private int elements;
	
	BufferedArrayList(int buffer) {
		if ( buffer < 1 ) {
			buffer = 1;
			System.err.println("Buffer less than 1 illegal. Buffer set to 1.");
		}
		
		theElements = new Object[buffer + 7];
		index = buffer;
		elements = 0;		
	}
	
	BufferedArrayList() {
		this(3);
	}
	
	private void growArrayFront() {
		growArrayFront(theElements.length * 2);
	}
	// Kjøretid: O(n)
	private void growArrayFront(int size) {
		Object[] newArray = new Object[size];
		int newIndex = size/2;
		for ( int i = index; i < theElements.length; i++) {
			newArray[i + newIndex] = theElements[i];
		}
		index = newIndex;
		theElements = newArray;
	}
	
	private void checkSizeFront() {
		if ( index == 0 ) { growArrayFront(); }
	}
	
	private void growArrayEnd() {
		growArrayEnd( theElements.length * 2 );
	}
	// Kjøretid: O(n)
	private void growArrayEnd(int size) {
		Object[] newArray = new Object[size];
		for ( int i = index; i < theElements.length; i++ ) {
			newArray[i] = theElements[i];
		}
		theElements = newArray;
	}
	
	private void checkSizeEnd() {
		if ( elements >= (theElements.length - index) ) {
			growArrayEnd();
		}
	}
	
	// Kjøretid: O(1) hvis ledig bak. Økning er O(n)
	@Override
	public boolean add(T e) {
		checkSizeEnd();
		theElements[index + elements++] = e;
		return true;
	}
	
	// kJøretid: O(n/2) = O(n), hvis array må økes, O(2n) = O(n)
	@Override
	public void add(int index, T e) {
		int tempIndex = index + this.index;
		
		if ( index < elements/2 ) { // shift indexes towards front
			checkSizeFront();
			for (int i = this.index; i < tempIndex; i++) {
				theElements[i-1] = theElements[i];
			}
			this.index--;
			
		} else { // shift indexes towards end
			checkSizeEnd();
			for (int i = elements + this.index -1; i >= tempIndex; i--) {
				theElements[i+1] = theElements[i];
			}
		}
		
		theElements[index+this.index] = e;
		elements++;
	}
	
	// Kjøretid: O(1)
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		return (T)theElements[this.index + index];
	}
	
	// Kjøretid O(n/2) = O(n)
	@Override
	public T remove(int index) {
		
		T e = get(index);
		int tempIndex = index + this.index;
		
		if ( index < elements/2 ) { //shift from front
			for (int i = tempIndex; i > this.index; i--) {
				theElements[i] = theElements[i-1];
			}
			theElements[this.index++] = null;
		} else {  // shift from end
			for (int i = tempIndex; i < (theElements.length-tempIndex); i++) {
				theElements[i] = theElements[i+1];
			}
			theElements[tempIndex+elements] = null;
		}
		elements--;
		return e;
	}

	// Kjøretid: O(1)
	@Override
	public int size() {
		return elements;
	}

	public void addFirst(T e) {
		add(0, e);
	}
	
	public void addLast(T e) {
		add(e);
	}
	// HER SLUTTER OBLIGATORISK

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		theElements = new Object[10];
		index = 3;
		elements = 0;		
	}

	@Override
	public boolean contains(Object o) {
		
		for (int i = index; i < (index+elements); i++) {
			if ( theElements[i].equals(o) ) return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = index; i < (index+elements); i++) {
			if ( theElements[i].equals(o) ) return (i-index);
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		
		return ( elements == 0 );
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() {
		return ( new LocalIterator() );
	}

	@Override
	public int lastIndexOf(Object o) {
		int lastIndex = -1;
		for (int i = index; i < (index+elements); i++) {
			if ( theElements[i].equals(o) ) lastIndex = (i-index);
		}
		return lastIndex;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if ( -1 < index ) {
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T set(int index, T e) {
		T removedElement = get(index);
		theElements[index+this.index] = e;
		return removedElement;
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	// method to analyze class
	public void printStatus() {
		System.out.print("Array size: " + theElements.length + ", ");
		System.out.print("Index at: " + index + ", ");
		System.out.print("Elements: " + elements + "\n");
		System.out.print("[");
		for (Object i : theElements) {
			if (i == null) {System.out.printf(" -");}
			else { System.out.printf(" %s", i); }
		}
		System.out.print(" ]\n\n");	
	}
	
	// inner class iterator
	@SuppressWarnings("rawtypes")
	private class LocalIterator implements Iterator {

		int current = index;
			
		@Override
		public boolean hasNext() {
			return current < (elements+index);
		}

		@Override
		public Object next() {
			return theElements[current++];
		}		
	}
	
	// inner class ListItrator
	private class LocalListIterator implements ListIterator {
		int current = index;

		@Override
		public void add(Object o) {
			this.add(o);
		}

		@Override
		public boolean hasNext() {
			return current < ( elements + index );
		}

		@Override
		public boolean hasPrevious() {
			return current > index;
		}

		@Override
		public Object next() {
			return theElements[current++];
		}

		@Override
		public int nextIndex() {
			if ( this.hasNext() ) return (current);
			else return elements;
		}

		@Override
		public Object previous() {
			return theElements[--current];
		}

		@Override
		public int previousIndex() {
			if ( this.hasPrevious() ) return (current -1);
			else return -1;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(Object arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
