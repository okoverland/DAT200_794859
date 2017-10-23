package oving7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import personer.Person;

public class ObjectTest {
	
	public static void main(String[] args) {
		
		List<Person> persons = new ArrayList<>();
		Map<Integer, Person> maps = new HashMap<>();
		
		persons.add(new Person(11, "Ola", "Nordmann", 23));
		persons.add(new Person(22, "Per", "Svenske", 54));
		persons.add(new Person(33, "Åge", "Hansen", 26));
		
		for (int i = 0; i < persons.size(); i++) {
			maps.put(persons.get(i).getID(), persons.get(i));
		}
		
		persons.stream().forEach(System.out::println);
		System.out.println();		
		System.out.println(maps.get(11));
		
		Person thePerson = persons.get(0);
		thePerson.setEtternavn("Norske");
		
		System.out.println();		
		System.out.println(maps.get(11));
		
		
		
	}

}
