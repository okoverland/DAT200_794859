package oving7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectTest {
	
	public static void main(String[] args) {
		
		List<String> strings = new ArrayList<>();
		Map<Integer, String> maps = new HashMap<>();
		
		strings.add("test1");
		strings.add("test2");
		strings.add("test3");
		
		for (int i = 0; i < strings.size(); i++) {
			maps.put((int)(i*Math.exp(2)), strings.get(i));
		}
		
		strings.stream().forEach(System.out::println);
		System.out.println();		
		System.out.println(maps.get(0));
		String theString = strings.get(0);
		theString = "test100";
		System.out.println();		
		System.out.println(maps.get(0));
		
		
		
	}

}
