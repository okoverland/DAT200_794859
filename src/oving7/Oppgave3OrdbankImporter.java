package oving7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Oppgave3OrdbankImporter {
	
	public static String sortString(String value) {
		
		char[] valueAsChars = value.toCharArray();
		Arrays.sort(valueAsChars);
		return String.valueOf(valueAsChars);
		
	}
	
	/**
	 * Henter inn en ordbank fil (fullform_bm.txt) og reduserer denne til
	 * en hash-tabell.
	 * 
	 * @author Ole K. Øverland
	 * 
	 */
	
	public static void importTextFile() {

		String basePath = new File("").getAbsolutePath();
		
		Path outputPath = Paths.get(basePath + File.separator + "src" 
				+ File.separator + "serialized" + File.separator + "dictionary.txt");
		
		Path inputPath = Paths.get(basePath + File.separator + "src" 
				+ File.separator + "serialized" + File.separator + "fullform_bm.txt");
		
		List<String> dictionary = null;
		
		try (Stream<String> inputFileStream = Files.lines(inputPath)){
			
			dictionary =
				inputFileStream	.map(line -> line.split("\t")[2].trim().toLowerCase())
								.distinct()
								.filter(word -> 1 < word.length() )
								.filter(word -> !word.contains(" "))
								.filter(word -> !word.contains("-"))
								.filter(word -> !word.contains("'"))
								.filter(word -> !word.contains("."))
								//.limit(20)
								.collect(Collectors.toCollection(ArrayList::new) );
			
		     
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (dictionary != null) {
			
			try (FileOutputStream fos = new FileOutputStream(new File(outputPath.toString())) ) {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

				for (String s : dictionary) {
						bw.write(s);
						bw.newLine();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		} else {
			System.out.println("There was a problem importing dictionary.");
		}
		
	}
	
	public static MittHashMap<String,String> getDictionary() {
		String basePath = new File("").getAbsolutePath();
		
		Path inputPath = Paths.get(basePath + File.separator + "src" 
				+ File.separator + "serialized" + File.separator + "dictionary.txt");
		
		if (Files.exists(inputPath)) {
			try (FileInputStream fis = new FileInputStream(inputPath.toFile()) ) {				
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				
				MittHashMap<String,String> theMap = new MittHashMap<>(1000000);
				String line = null;
				
				while ( (line = br.readLine()) != null ) {
					theMap.putSingle(sortString(line), line);
				}
				return theMap;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return null;
	}
	
	public static void main(String[] args) {
		
		//importTextFile(); Kjøres kun ved ny liste
		
		MittHashMap<String, String> listen = getDictionary();

		if (listen != null) {
			
			String input = null;
			List<String> getList;
			
			try (Scanner in = new Scanner(System.in)) {
				
				System.out.printf("Finn anagram basert på norsk ordbank bokmåls ordliste. Avslutt med tomt linjeskift.%n%n");
				do {
				getList = null;
				System.out.printf("%s ", "Finn anagram for ord:");
				input = in.nextLine();
				input.trim();
				input = input.toLowerCase();
				
				if (input.equals("")) break;
				
				getList = listen.get(sortString(input));
				
				if (getList != null) {
					
					if (getList.size() < 2) {
						System.out.printf("Det finnes ingen anagrammer for \"%s\"!%n%n", input);
					} else {
						getList.remove(input);
						getList.stream()
								.forEach(word -> System.out.printf("%s  ", word));
						System.out.println();
						System.out.println();
					}
				
				} else {
					System.out.printf("Ordet \"%s\" finnes ikke!%n%n", input);
				}
				
				} while(true);
				
			}
		} else {
			System.out.println("Det oppstod et problem med å laste ordliste.");
		}
	}
}
