package oving5;

import java.util.ArrayList;
import java.util.Arrays;

public class Oppgave1 {

	public static void main(String[] args) {
		
		int[] array1_init = { 35, 44, 5, 43, 32, 65, 20, 2, 15, 46 };
		int[] array2_init = { 2, 6, 9, 7, 12, 17, 18, 26, 22, 35 };
		
		ArrayList<Integer> array1 = new ArrayList<>();
		ArrayList<Integer> array2 = new ArrayList<>();
				
		Arrays.stream(array1_init).forEach(i -> array1.add(i));
		Arrays.stream(array2_init).forEach(i -> array2.add(i));
		
		System.out.println("Array 1");
		System.out.printf("%-10s%s%n", "Usortert" ,program.PrimitiveString.intArrayString(array1_init));
		sort.ShellSort.Ksorter(array1, 5);
		System.out.printf("%-10s%s%n", "5-sort", array1);
		sort.ShellSort.Ksorter(array1, 3);
		System.out.printf("%-10s%s%n", "3-sort", array1);
		
		System.out.println();
		
		System.out.println("Array 2");
		System.out.printf("%-10s%s%n", "Usortert" ,program.PrimitiveString.intArrayString(array2_init));
		sort.ShellSort.Ksorter(array2, 5);
		System.out.printf("%-10s%s%n", "5-sort", array2);
		sort.ShellSort.Ksorter(array2, 3);
		System.out.printf("%-10s%s%n", "3-sort", array2);
		
	}

}