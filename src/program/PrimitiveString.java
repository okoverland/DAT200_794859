package program;

import java.util.Arrays;

public class PrimitiveString {
	
	public static String intArrayString(int[] array) {
		
		StringBuilder string = new StringBuilder();
		string.append("[");
		Arrays.stream(array)
			.forEach(i -> {
				string.append(i);
				string.append(", ");
			} );
		string.setLength(string.length()-2);
		string.append("]");
		
		return string.toString();
	}

}
