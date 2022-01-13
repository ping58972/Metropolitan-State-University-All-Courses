import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMain {

	public static void main(String[] args)
	{
		//1. Create new game (it will intialize to empty board)
		TicTacToe ttt = new TicTacToe();
		
		//2. print the board
		System.out.println("Welcome to 3x3 Tic Tac Toe.");
		ttt.printBoard();

		System.out.println("X will play first. Enter a slot number to place X in:");

		
		//3.  get the current board information
		String winner = ttt.getWinner();
		String[] board = ttt.getBoard();
		
		
		//4. As long as the winner is "None", we should keep playing the game
		//   The game stops when winner = X or winner = Y or winner = draw
		Scanner in = new Scanner(System.in);
		while (winner.equals("None")) {
			String turn = ttt.getTurn();
			 
			int numInput;
			
		// Exception handling.
		// numInput will take input from user like from 1 to 9.
		// If it is not in range from 1 to 9.
		// then it will show you an error "Invalid input."
			try
			 {
				numInput = in.nextInt();
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input; re-enter slot number:");
				continue;
			}
			
			
			String current_value = board[numInput - 1];
			String given_value = String.valueOf(numInput);
			boolean current_and_given_are_equal = current_value.equals(given_value);
			
			
			// This game has two player x and O.
			// Here is the logic to decide the turn.
			if (current_and_given_are_equal) {
				// update the board position with X or O
				board[numInput - 1] = turn;

				if (turn.equals("X")) {
					ttt.setTurn("O");
				}
				else {
					ttt.setTurn("X");
				}

				ttt.printBoard();
				winner = ttt.checkWinner();
			}
			else {
				System.out.println("Slot already taken; re-enter slot number:");
			}
		}
		
		// If no one win or lose from both player x and O.
		// then here is the logic to print "draw".
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thanks for playing.");
		}
		// For winner -to display Congratulations! message.
		else {
			System.out.println(	"Congratulations! " + winner + "'s have won! Thanks for playing.");
		}
	}

}
