package javaFx;
	
import javafx.application.Application;
import javafx.stage.Stage;
import fractals.SierpinskiCarpet;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class RunSierpinksiCarpet extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			SierpinskiCarpet grafikk = new SierpinskiCarpet(500);
			
			root.setCenter(grafikk.getCanvas());
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
