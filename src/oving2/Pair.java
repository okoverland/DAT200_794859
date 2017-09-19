package oving2;

public class Pair<T> {
	
	private T o1;
	private T o2;
	
	Pair(T o1, T o2) {
		this.o1 = o1;
		this.o2 = o2;
	}
	
	Pair(T o1) {
		this(o1, null);
	}
	
	Pair() {
		this(null, null);
	}
	
	
	public T getObject1() {
		return o1;
	}

	public void setObject1(T o1) {
		this.o1 = o1;
	}

	public T getObject2() {
		return o2;
	}

	public void setObject2(T o2) {
		this.o2 = o2;
	}
	
}
