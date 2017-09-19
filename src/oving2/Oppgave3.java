package oving2;

public class Oppgave3 {

	public static void main(String[] args) {

		Pair<String> stringPair = new Pair<>("string1", "string2");
		Pair<Integer> integerPair = new Pair<>(1, 2);
		
		System.out.printf("Object 1: %s\nObject 2: %s\n", stringPair.getObject1(), stringPair.getObject2());
		System.out.println();
		System.out.printf("Object 1: %s\nObject 2: %s\n", integerPair.getObject1(), integerPair.getObject2());

		
	}

}
