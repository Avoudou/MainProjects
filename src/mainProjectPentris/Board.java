package mainProjectPentris;

public class Board {
	/** Defines game Board */
	private int[][] pentrisGameBoard;

	public Board() {
	}

	public int[][] getBoard() {
		return pentrisGameBoard;
	}

	public void setPentrisGameBoard(int[][] pentrisGameBoard) {
		this.pentrisGameBoard = pentrisGameBoard;
	}
}