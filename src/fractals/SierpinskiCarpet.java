package fractals;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * Lag en rekursiv metode som tegner opp et Sierpinski Carpet.
 */

public class SierpinskiCarpet {
	
	private Canvas canvas;
	private GraphicsContext g;
	
	// Constructor
	public SierpinskiCarpet(int size) {
		canvas = new Canvas(size, size);
		g = canvas.getGraphicsContext2D();
		g.setFill(Color.WHITE);
		g.fillRect(0, 0, size, size);
		g.setFill(Color.BLACK);
		
		drawCarpet(size/3, size/3, size/3);
	}
	
	// recursive method
	private void drawCarpet(int size, int xPos, int yPos) {
		
		if (size < 1 )  {
			return;	// End condition
		}
		
		g.fillRect(xPos, yPos, size, size);
		
		drawCarpet( size/3, xPos - 2 * size / 3, 	yPos - 2 * size / 3 ); // upper left
		drawCarpet( size/3, xPos + size / 3, 	 	yPos - 2 * size / 3 ); // upper center
		drawCarpet( size/3, xPos + 4 * size / 3, 	yPos - 2 * size / 3 ); // upper right
		drawCarpet( size/3, xPos - 2 * size / 3, 	yPos + 4 * size / 3	); // lower left
		drawCarpet( size/3, xPos + size / 3, 		yPos + 4 * size / 3 ); // lower center
		drawCarpet( size/3, xPos + 4 * size / 3, 	yPos + 4 * size / 3 ); // lower right
		drawCarpet( size/3, xPos - 2 * size / 3,	yPos + size / 3 	); // middle left
		drawCarpet( size/3, xPos + 4 * size / 3, 	yPos + size / 3 	); // middle right
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

}
