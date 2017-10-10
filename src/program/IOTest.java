package program;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

public class IOTest {

	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("C:\\Users\\ole.PROBYLOKAL\\Downloads\\A3_PLAN_A1.pdf");
		
		if (Files.exists(path)) {
			System.out.printf("%-15s %s%n", "Filen:", path);
			System.out.printf("%-15s %s%n", "Filbanen:", path.getParent());
			System.out.printf("%-15s %s%n", "Filsystem:", path.getFileSystem());
			System.out.printf("%-15s %s%n", "Er en dir?", Files.isDirectory(path));
			System.out.printf("%-15s %s%s%n", "Størrelse:", (Files.size(path)/1024), " Kb");
			System.out.printf("%-15s %s%n", "Sist endret:", Files.getLastModifiedTime(path));
			if (path.getFileSystem().toString().contains("Windows")) {
				Runtime.getRuntime().exec( "CMD /C START " + path ); // Only for Windows
			}
			System.out.println("Other contents of directory:");
			DirectoryStream<Path> dirStream = Files.newDirectoryStream(path.getParent());
			dirStream.forEach(e -> System.out.println(e.getFileName()));
			
		}

	}

}
