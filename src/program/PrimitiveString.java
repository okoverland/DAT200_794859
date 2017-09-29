package program;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveString {
	
	public static String intArrayString(int[] array) {
		
		StringBuilder string = new StringBuilder();
		string.append("[");
		IntStream.of(array)
			.forEach(i -> {
				string.append(i);
				string.append(", ");
			} );
		string.setLength(string.length()-2);
		string.append("]");
		
		return string.toString();
	}
	
	public static String intArrayString(Integer[] array) {
		
		StringBuilder string = new StringBuilder();
		string.append("[");
		Stream.of(array)
			.forEach(i -> {
				string.append(i);
				string.append(", ");
			} );
		string.setLength(string.length()-2);
		string.append("]");
		
		return string.toString();
	}

}
