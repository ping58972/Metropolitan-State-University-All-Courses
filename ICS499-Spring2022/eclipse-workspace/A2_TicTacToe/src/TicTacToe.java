import java.util.Arrays;

public class TicTacToe {

	// for holding the players turn
	private String turn;

	// for representing the board
	private String[] board;

	// for holding the winner information (X or O or draw or none)
	private String winner;

	/**
	 * Default Constructor For TicTacToe Class
	 */
	public TicTacToe() {
		this.setTurn("X");
		this.setWinner("None");

		String[] board = new String[9];
		for (int a = 0; a < 9; a++) {
			board[a] = String.valueOf(a + 1);
		}
		this.setBoard(board);

	};

	/**
	 * Overloaded Constructor For TicTacToe Class
	 */
	public TicTacToe(String a_turn, String[] a_board, String a_winner) {
		turn = a_turn;
		board = a_board;
		winner = a_winner;
	}

	/**
	 * Set method for the variable turn
	 */
	public void setTurn(String a_turn) {
		turn = a_turn;
	}

	/**
	 * Set method for the variable board
	 */
	public void setBoard(String[] a_board) {
		board = a_board;
	}

	/**
	 * Set method for the variable winner
	 */
	public void setWinner(String a_winner) {
		winner = a_winner;
	}

	/**
	 * Get method for the variable turn
	 */
	public String getTurn() {
		return turn;
	}

	/**
	 * Get method for the variable board
	 */
	public String[] getBoard() {
		return board;
	}

	/**
	 * Get method for the variable winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * method for getting the winner return value can be X or O or 'draw' or 'null'
	 * 
	 * @return
	 */
	public String checkWinner() {
		for (int a = 0; a < 8; a++) {
			String line = null;

			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			// For X winner
			if (line.equals("XXX")) {
				return "X";
			}

			// For O winner
			else if (line.equals("OOO")) {
				return "O";
			}
		}

		// check if all the slots are full and if there is no winner
		// return draw
		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8) {
				return "draw";
			}
		}

		// To enter the X Or O at the exact place on board.
//			System.out.println(	turn + "'s turn; enter a slot number to place "	+ turn + " in:");
		return "None";
	}

	/**
	 * For printing the board
	 */
	public void printBoard() {
		System.out.println("|---|---|---|");
		System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
		System.out.println("|---|---|---|");
	}

	/**
	 * Returns the String representation of TicTacToe object
	 */
	public String toString() {
		System.out.println("Winner: " + this.winner);
		System.out.println("Current Turn: " + this.turn);
		this.printBoard();
		return "";
	}

}