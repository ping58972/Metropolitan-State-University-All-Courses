
import processing.core.PApplet;

/**
 * The GraphicsMain class will extend PApplet.
 *  It should have the methods main(), settings(), setup() and draw(). 
 * 
 *  In setup(), you should call the TicTacToe constructor. 
 *  In the draw() method, you should display the board and handle the events/clicks
 *  
 * 
 */
public class GraphicsMain extends PApplet {
	
	// declare any variables you need
	 
	

	public static void main(String[] args) {

		PApplet.main("GraphicsMain");

	}

	public void settings()
	{
		size(800, 800);
	}

	public void setup()
	{
		// TODO: create the tic tac toe board.
		// Use the rect() to draw the board
		
		background(255); 
		fill(255, 0, 0, 30);
	}

	public void draw() {
	
		// TODO:  handle the events
		// based on where the user clicked, update the display
		// place X or O on the board
		
		// Keep checking for the winner to display a message
		// X or O or draw 
	}

	

}