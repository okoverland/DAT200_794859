package oving3;

import java.util.Iterator;

public class BufferedArrayListDemo {

	public static void main(String[] args) {
		
		BufferedArrayList<Integer> listen = new BufferedArrayList<>();
		
		System.out.println("New <Ineger> list:");
		listen.printStatus();
		
		System.out.println("add(5), add(6), add(7):");
		listen.add(5);
		listen.add(6);
		listen.add(7);
		listen.printStatus();
		
		System.out.println("addFirst(9):");
		listen.addFirst(9);
		listen.printStatus();
		
		System.out.println("addFirst(4), addFirst(12):");
		listen.addFirst(4);
		listen.addFirst(12);
		listen.printStatus();
		
		System.out.println("addFirst(15):");
		listen.addFirst(15);
		listen.printStatus();
		
		System.out.println("addLast(33), addLast(25), addLast(17), addLast(65), addLast(1):");
		listen.addLast(33);
		listen.addLast(25);
		listen.addLast(17);
		listen.addLast(65);
		listen.addLast(1);
		listen.printStatus();
		
		System.out.println("add(3, 3):");
		listen.add(3, 3);
		listen.printStatus();
		
		System.out.println("add(0, 8):");
		listen.add(0, 8);
		listen.printStatus();
		
		System.out.println("add(11, 11):");
		listen.add(11, 11);
		listen.printStatus();
		
		System.out.println("remove(11):");
		listen.remove(11);
		listen.printStatus();
		
		System.out.println("remove(3):");
		listen.remove(3);
		listen.printStatus();
		
		System.out.print("contains(7): ");
		if ( listen.contains(7) ) {
			System.out.print("True\n");
		} else System.out.print("False\n");
		listen.printStatus();
		
		System.out.print("contains(8): ");
		if ( listen.contains(8) ) {
			System.out.print("True\n");
		} else System.out.print("False\n");
		listen.printStatus();
		
		System.out.print("contains(1): ");
		if ( listen.contains(1) ) {
			System.out.print("True\n");
		} else System.out.print("False\n");
		listen.printStatus();
		
		System.out.print("contains(4): ");
		if ( listen.contains(4) ) {
			System.out.print("True\n");
		} else System.out.print("False\n");
		listen.printStatus();
		
		System.out.print("indexOf(33): ");
		System.out.print(listen.indexOf(33) +"\n");
		listen.printStatus();
		
		System.out.print("Each item printed using iterator:");
		Iterator<Integer> itr = listen.iterator();
		while (itr.hasNext()) {
			System.out.print(" " + itr.next());
		}
		System.out.println();
		listen.printStatus();
		
		System.out.print("Each item printed using lambda: ");
		listen.forEach( elementet -> System.out.print(elementet + " ") );
		System.out.println();
		listen.printStatus();
		
		System.out.println("clear():");
		listen.clear();
		listen.printStatus();
				
	}

}
