
import processing.core.PApplet;

/*
 * Edit and Update By Nalongsone Danddank
 * ICS499-01
 * Student ID: 14958950
 * email: nalongsone.danddank@my.metrostate.edu
 * */
/**
 * The GraphicsMain class will extend PApplet. It should have the methods
 * main(), settings(), setup() and draw().
 * 
 * In setup(), you should call the TicTacToe constructor. In the draw() method,
 * you should display the board and handle the events/clicks
 * 
 * 
 */
public class GraphicsMain extends PApplet {

	// declare any variables you need
	TicTacToe ticTacToe;
	Box[] boxes = new Box[9];
	int clickControls[] = new int[9];

	public static void main(String[] args) {
		PApplet.main("GraphicsMain");
	}

	public void settings() {
		size(500, 500);
	}

	public void setup() {
		// TODO: create the tic tac toe board.
		// create and initilize every box by fixed size.
		for (int i = 0; i < 9; i++) {
			boxes[i] = new Box(100 + 100 * (i % 3), 100 + 100 * (i / 3));
		}
		// Create new game (it will intialize to empty board)
		ticTacToe = new TicTacToe();
		textSize(26);
		fill(50);
		text("Well Come To TIC-TAC-TOE", 100, 50);
	}

	public void draw() {
		// TODO: handle the events
		background(200);
		textSize(26);
		fill(50);
		text("Well Come To TIC-TAC-TOE", 100, 50);
		text("Mr.X  Vs.  Ms.O", 180, 80);
		for (int i = 0; i < 9; i++) {
			// build, update and display every box.
			boxes[i].drawBox(mouseX, mouseY);
			boxes[i].updateBox(i);
		}
		// Keep checking for the winner to display a message
		// X or O or draw
		// if the game get winner or done all box.
		// stop the click event and print the result.
		String checking = ticTacToe.checkWinner();
		if (!checking.equals("None")) {
			for (int j = 0; j < 9; j++) {
				clickControls[j] = 10000;
			}
			if (checking.equals("draw")) {
				text("Game Over!!!", 100, 450);
			} else if (checking.equals("X")) {
				text("Mr.X, You Win!", 100, 450);
			} else if (checking.equals("O")) {
				text("Mr.O, You Win!", 100, 450);
			}
		} else {
			text(ticTacToe.getTurn(), mouseX, mouseY);
		}
	}

	// based on where the user clicked, update the display
	// place X or O on the board
	public void mouseClicked() {
		// click each mouse clicked position to handle the event and update the Board
		// then turn user to another.
		for (int i = 0; i < 9; i++) {
			if ((mouseX > clickControls[i] + 100 + (100 * (i % 3)) && mouseX < clickControls[i] + 200 + (100 * (i % 3)))
					&& (mouseY > clickControls[i] + 100 + (100 * (i / 3))
							&& mouseY < clickControls[i] + 200 + (100 * (i / 3)))) {
				ticTacToe.getBoard()[i] = ticTacToe.getTurn();
				if (ticTacToe.getTurn().equals("X")) {
					ticTacToe.setTurn("O");
				} else {
					ticTacToe.setTurn("X");
				}
				clickControls[i] = -10000;
			}
		}

	}

	private class Box {
		/**
		 * Box for display each number and O or X .
		 */
		int rectX, rectY; // Position of square Box
		int rectSize = 100;
		int rectHighlight;

		public Box(int X, int Y) {
			rectHighlight = color(251);
			rectX = X;
			rectY = Y;
		}

		public void drawBox(int mouseXBox, int mouseYBox) {
			fill(rectHighlight);
			rect(rectX, rectY, rectSize, rectSize);
		}

		public void updateBox(int i) {
			fill(0);
			textSize(60);
			String str = ticTacToe.getBoard()[i];
			text(str, rectX + 45, rectY + 60);
		}
	}
}
