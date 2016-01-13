package mainProjectPentris;




import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * This is the implementation of the methods that manipulate the board in order for the game to be played.
 * 
 * @author Adeline Mekic
 * @author Alexios Voudouris
 * @author Evelina Masliankova
 * @author Esther Kemper
 * @author Matiss Baldonis
 * @author Tom Conneely
 */
public class GameLogic {
	
	private int optiIndex=0; 
	
	
	
	/**
	 * A Board object containing an array, representing the playfield
	 */
	private Board aBoard = new Board();
	/**
	 * A Shapes object, containing a list of the Shapes and their flips
	 */
	private Shapes shapeList = new Shapes();
	/**
	 * A 2D array, representing the active shape in the game
	 */
	private int[][] activeShape;

	private int score = 0;
	/** determines the boards X-dimension */
	private int x;
	/** determines the boards Y-dimension */
	private int y;
	/** Defines default X coordinate for new shapes insertions */
	private int defaultXpoint;
	/** Defines default Y coordinate for new shapes insertions */
	private int defaultYpoint;
	/**
	 * Defines/keeps track X coordinate for current active shapes with reference to its middle
	 */
	private int shapeCoordX = defaultXpoint;
	/**
	 * Defines/keeps track Y coordinate for current active shape with reference to its bottom row
	 */
	private int shapeCoordY = defaultYpoint;
	/** remembers the previous rotation of the shape */
	private int[][] shapeActiveBeforeRot;
	/** timer that defines Y-axis movement time intervals */
	private Timer yAxisMovetimer;
	/** Task assignment of yAxisMovetimer */
	private TimerTask yAxisMechanism;
	/**
	 * Integer value to adjust counting in X-axis from the middle of the chosen shape
	 */
	int shapeMidPosition;
	/**
	 * boolean to determine when to stop the games on x-Axis when the game is over
	 */
	private boolean stopXaxisMovement;
	/**
	 * keeps track of the rows deleted in each move(used for board ordering and score)
	 */
	private int deleteCounter = 0;
	/**
	 * defines the point the game finishes starting from the top of the board, should be at least BIG as the maximum
	 * y-length of the shapes that going be used
	 */
	private final int gameOverLimit = 5;

	/** Boolean that graphs use to show game over */
	private boolean gameOver = false;

	private boolean optiBoard = false;

	/**
	 * CONSTRUCTOR makes a game board for pentris of the given coordinates,marking the boarder with integer value "99"
	 * and starts the game
	 */
	public GameLogic(int xCoord, int yCoord) {
		System.out.println("con");
		x = xCoord + 2;
		y = yCoord + gameOverLimit + 1;
		
		defaultXpoint = (int) x / 2;
		defaultYpoint = gameOverLimit - 1;
		shapeCoordX = defaultXpoint;
		shapeCoordY = defaultYpoint;
		aBoard.setPentrisGameBoard(new int[y][x]);
		int[][] currBoard = aBoard.getBoard();
		for (int i = 0; i < x; i++) {
			currBoard[y - 1][i] = 99;
		}
		for (int j = 0; j < y; j++) {
			currBoard[j][0] = 99;
			currBoard[j][x - 1] = 99;
		}
		setNewRandomShape(shapeList.givePentrisData());

		shapeMidPosition = (int) ((activeShape[0].length) / 2);

	}

	public GameLogic(String a) {
		optiBoard = true;
		x = 5 + 2;
		y = 12 + gameOverLimit + 1;
		defaultXpoint = (int) x / 2;
		defaultYpoint = gameOverLimit - 1;
		shapeCoordX = defaultXpoint;
		shapeCoordY = defaultYpoint;
		aBoard.setPentrisGameBoard(new int[y][x]);
		int[][] currBoard = aBoard.getBoard();
		for (int i = 0; i < x; i++) {
			currBoard[y - 1][i] = 99;
		}
		for (int j = 0; j < y; j++) {
			currBoard[j][0] = 99;
			currBoard[j][x - 1] = 99;
		}
		setOptidata(shapeList.giveOptiData());

		shapeMidPosition = (int) ((activeShape[0].length) / 2);

	}

	/**
	 * starts game by inserting shapes on top and moving them down until they hit something , stops game if gameOver
	 */
	public void startGameTimer() {
		yAxisMovetimer = new Timer();
		yAxisMechanism = new TimerTask() {

			@Override
			public void run() {
				fallDownShape();
				printPentrisBoard();

				System.out.println("score" + " : " + score);

			}
		};
		yAxisMovetimer.schedule(yAxisMechanism, 0, 500);
	}

	/**
	 * inserts a desired shape into the board its center matching given shape coordinates and moving it into the board
	 * if the coordinates given don't match the board width and height(top side)
	 */
	public void addShape() {
		shapeMidPosition = (int) ((activeShape[0].length) / 2);
		int[][] currBoard = aBoard.getBoard();
		conditionBounds(currBoard);

		for (int shapeIndexY = activeShape.length - 1; shapeIndexY >= 0; shapeIndexY--) {
			for (int shapeIndexX = 0; shapeIndexX <= activeShape[0].length - 1; shapeIndexX++) {
				if (activeShape[shapeIndexY][shapeIndexX] != 0) {
					currBoard[shapeCoordY + shapeIndexY - activeShape.length + 1][shapeCoordX - shapeMidPosition
							+ shapeIndexX] = activeShape[shapeIndexY][shapeIndexX];

				}
			}
		}

	}

	/**
	 * Deletes chosen shape from position given by x-shape,y-shape coordinates in the game board
	 */
	public void removeShape() {
		shapeMidPosition = (int) ((activeShape[0].length) / 2);
		int[][] currBoard = aBoard.getBoard();
		conditionBounds(currBoard);

		for (int shapeIndexY = activeShape.length - 1; shapeIndexY >= 0; shapeIndexY--) {
			for (int shapeIndexX = 0; shapeIndexX <= activeShape[0].length - 1; shapeIndexX++)
				if (activeShape[shapeIndexY][shapeIndexX] != 0) {
					currBoard[shapeCoordY + shapeIndexY - activeShape.length + 1][shapeCoordX - shapeMidPosition
							+ shapeIndexX] = 0;
				}
		}
	}

	private void conditionBounds(int[][] currBoard) {
		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}

		if (shapeCoordX - shapeMidPosition + activeShape[0].length > currBoard[0].length - 1) {
			shapeCoordX = currBoard[0].length - activeShape[0].length + shapeMidPosition - 1;
		}
		if (shapeCoordY < activeShape.length - 1) {
			shapeCoordY = activeShape.length - 1;
		}
	}

	/**
	 * Moves a shape one step(index) down to the y axis if the move is valid and ends the game if board is filled
	 */
	public void fallDownShape() {
		shapeMidPosition = (int) ((activeShape[0].length) / 2);
		removeShape();
		shapeCoordY++;
		if (shapeColission()) {
			addShape();

		} else if (optiBoard) {

			shapeCoordY--;
			addShape();
			deleteFullRowsMechanism();
			score = score + (int) Math.pow(deleteCounter, 2);
			deleteCounter = 0;
			shapeCoordY = defaultYpoint;
			shapeCoordX = defaultXpoint;
			if(shapeList.giveOptiData().size() > 0){
			setOptidata(shapeList.giveOptiData());
			}
			if (shapeList.giveOptiData().size() == 0) {
				System.out.println("Finished");
				yAxisMovetimer.cancel();
				stopXaxisMovement = true;
				deleteFullRowsMechanism();
				score = score + (int) Math.pow(deleteCounter, 2);
				deleteCounter = 0;
			}
		}

		else {

			shapeCoordY--;
			addShape();
			deleteFullRowsMechanism();
			score = score + (int) Math.pow(deleteCounter, 2);
			deleteCounter = 0;
			shapeCoordY = defaultYpoint;
			shapeCoordX = defaultXpoint;

			setNewRandomShape(shapeList.givePentrisData());
			if (gameOver()) {
				if(score>0){
				nameWindow(score*100);}
				System.out.println("gameOver");
				yAxisMovetimer.cancel();
				stopXaxisMovement = true;
				gameOver = true;
			}

		}

	}
	private void nameWindow(int score){
		JFrame scoreFrame= new JFrame();
		scoreFrame.setSize(400	, 50);
		scoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		scoreFrame.setTitle("Give Name");
		scoreFrame.setResizable(false);
		NamePanel aMenu= new NamePanel(scoreFrame,score);
		scoreFrame.add(aMenu);
		scoreFrame.setVisible(true);
		
	}
	

	public void scoreWindow() {
		JFrame scoreFrame= new JFrame();
		scoreFrame.setSize(400	, 400);
		scoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		scoreFrame.setTitle("Scores Pentris");
		scoreFrame.setResizable(false);
		ScorePanel aScorePanel= new ScorePanel();
		scoreFrame.add(aScorePanel);
		scoreFrame.setVisible(true);
	}

	/**
	 * Moves a shape one step(index) Left to the x axis if the move is valid,or its previous position if not
	 */
	public void moveLeft() {
		shapeMidPosition = (int) ((activeShape[0].length) / 2);
		if (shapeCoordY >= gameOverLimit) {
			if (shapeCoordX - shapeMidPosition < 1) {
				shapeCoordX = 1 + shapeMidPosition;
			}
			removeShape();
			shapeCoordX--;
			if (shapeColission()) {
				addShape();
			} else {
				shapeCoordX++;
				addShape();
			}
		}
	}

	/**
	 * Moves a shape one step(index) right to the x axis if the move is valid,or its previous position if not
	 */
	public void moveRight() {
		shapeMidPosition = (int) ((activeShape[0].length) / 2);
		if (shapeCoordY >= gameOverLimit) {

			int[][] currBoard = aBoard.getBoard();
			if (shapeCoordX - shapeMidPosition + activeShape[0].length > currBoard[0].length - 1) {
				shapeCoordX = currBoard[0].length - activeShape[0].length + shapeMidPosition - 1;
			}
			removeShape();
			shapeCoordX++;
			if (shapeColission()) {
				addShape();

			} else {
				shapeCoordX--;
				addShape();
			}
		}
	}

	/**
	 * Rotates the shape in its current position IF THE NEW POSITION IS VALID(need change so it wont rotate in the final
	 * step)
	 */
	public void rotateShape90DegreesMechanism() {

		removeShape();
		shapeActiveBeforeRot = ArraysMethods.deepCopy2DArray(activeShape);
		rotActiveShape90();
		int shapeXOldCoord = shapeCoordX;
		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}
		int[][] currBoard = aBoard.getBoard();
		if (shapeCoordX - shapeMidPosition + activeShape[0].length > currBoard[0].length - 1) {
			shapeCoordX = currBoard[0].length - activeShape[0].length + shapeMidPosition - 1;
		}
		if (shapeCoordY < activeShape.length - 1) {
			shapeCoordY = activeShape.length - 1;
		}
		if (shapeColission()) {
			addShape();
		} else {

			activeShape = shapeActiveBeforeRot;
			shapeCoordX = shapeXOldCoord;
			addShape();
		}
	}

	/** rotates the active shape as 2d-array */
	public void rotActiveShape90() {
		int[][] shapeRot = new int[activeShape[0].length][activeShape.length];
		for (int i = 0; i < shapeRot.length; i++) {
			for (int j = 0; j < shapeRot[0].length; j++) {
				shapeRot[i][j] = activeShape[shapeRot[0].length - j - 1][i];

			}
		}
		activeShape = shapeRot;
	}

	/**
	 * Returns True if a shape can be placed in given x , y coordinates(checks that no index position not already
	 * occupied)
	 */
	public boolean shapeColission() {
		shapeMidPosition = (int) ((activeShape[0].length) / 2);

		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}
		int[][] currBoard = aBoard.getBoard();
		if (shapeCoordX - shapeMidPosition + activeShape[0].length > currBoard[0].length - 1) {
			shapeCoordX = currBoard[0].length - activeShape[0].length + shapeMidPosition - 1;
		}
		if (shapeCoordY < activeShape.length - 1) {
			shapeCoordY = activeShape.length - 1;
		}

		for (int shapeIndexY = 0; shapeIndexY < activeShape.length; shapeIndexY++) {
			for (int shapeIndexX = 0; shapeIndexX < activeShape[0].length; shapeIndexX++) {
				if (activeShape[shapeIndexY][shapeIndexX] != 0
						&& currBoard[shapeCoordY + shapeIndexY - activeShape.length + 1][shapeCoordX - shapeMidPosition
								+ shapeIndexX] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/** Deletes the full rows from the board and moves floating elements down */
	public void deleteFullRowsMechanism() {
		int[][] currBoard = aBoard.getBoard();
		for (int i = currBoard.length - 2; i >= 0; i--) {
			if (fullLine(i)) {
				deleteCounter++;
				for (int j = 1; j < currBoard[0].length - 1; j++) {
					currBoard[i][j] = 0;

				}
				copyBoardDown(i);
				deleteFullRowsMechanism();
			}
		}
	}

	/** checks if a given row is full or not */
	public boolean fullLine(int rowCord) {
		int[][] currBoard = aBoard.getBoard();
		int[] x = currBoard[rowCord];
		if (ArraysMethods.arrayMinValue(x) != 0) {
			return true;

		}
		return false;
	}

	/** checks if a given row is empty or not */
	public boolean checkIfRowEmpty(int rowCord) {
		int[] x = aBoard.getBoard()[rowCord];
		if (ArraysMethods.arrayMaxValue(x) == 0) {
			return true;

		}
		return false;
	}

	/** sets the shape of input as active shape of the object */
	public void setActiveShape(int[][] chooseShape) {
		activeShape = chooseShape;
		shapeMidPosition = (int) (activeShape[0].length - 1) / 2;
		;
	}

	/**
	 * pick a random shape from an ArrayList database and sets it as Active shape
	 */
	public void setNewRandomShape(ArrayList<int[][]> shapeDatabase) {
		int listSize = shapeDatabase.size();
		int[][] shapeToChoose = shapeDatabase.get((int) ((listSize) * Math.random()));

		setActiveShape(shapeToChoose);

	}

	public void setOptidata(ArrayList<int[][]> shapeDatabase) {
		// System.out.println("called opti");
		int[][] shapeToChoose = shapeDatabase.get(1);
		// ArraysMethods.print2DArray(shapeToChoose);
		shapeCoordX=(shapeList.optiCoords().get(optiIndex));;
		optiIndex++;
		System.out.println(optiIndex);
		setActiveShape(shapeToChoose);
		shapeDatabase.remove(1);
		

	}

	/**
	 * boolean to determine the end of the game(returns true when board is filled more than a certain point)
	 */
	public boolean gameOver() {

		for (int shapeIndexY = 0; shapeIndexY < gameOverLimit; shapeIndexY++) {
			int[][] currBoard = aBoard.getBoard();
			for (int shapeIndexX = 1; shapeIndexX < currBoard[0].length - 1; shapeIndexX++)
				if (currBoard[shapeIndexY][shapeIndexX] != 0) {

					return true;
				}

		}

		return false;
	}

	/** returns the x dimension of the gameBoard to represent graphically */
	public int getGraphX() {
		return x;
	}

	/** returns the y dimension of the gameBoard to represent graphically */
	public int getGraphY() {
		return y - gameOverLimit;
	}

	/** sets the coordinate in x-axis of where the chosen shape is/will appear */
	public void setshapecoordX(int start) {
		shapeCoordX = start;
	}

	/** sets the coordinate in y-axis of where the chosen shape is/will appear */
	public void setshapecoordY(int start) {
		shapeCoordY = start;
	}

	/** print the pentris game board accordingly so is understandable in console */
	public void printPentrisBoard() {
		for (int i = 0; i < y; i++) {
			System.out.println();
			for (int j = 0; j < x; j++) {
				int[][] currBoard = aBoard.getBoard();
				if (currBoard[i][j] == 0) {
					System.out.print(" " + currBoard[i][j] + " ");
				} else {
					System.out.print(currBoard[i][j] + " ");
				}
			}
		}

	}

	public boolean xAxisMovement() {
		return stopXaxisMovement;
	}

	/**
	 * Used to move the shapes that need to fall down after the player accomplishes a full row
	 */
	public void copyBoardDown(int yCoordRowDeleted) {
		for (int y = yCoordRowDeleted; y >= 1; y--) {
			int[][] currBoard = aBoard.getBoard();
			for (int j = 1; j < currBoard[0].length - 1; j++) {

				currBoard[y][j] = currBoard[y - 1][j];

			}
		}
	}

	/**
	 * exports the current state of the game board as 2D array to be represented with graphics
	 */
	public int[][] exportPentrisGraphics() {
		int[][] currBoard = aBoard.getBoard();
		int[][] graphicRep = ArraysMethods.deepCopy2DArray(currBoard);
		return graphicRep;
	}

	/**
	 * returns the number of rows from the top of the board that if filled game finishes
	 */
	public int getGameOverLimit() {
		return gameOverLimit;
	}

	/** method to tell the panel to draw gameOver picture */
	public boolean graphGameOver() {
		return gameOver;
	}

	/** returns the score value of the gameBoard multiplied by 100 */
	public int getScoreForGraphs() {
		return score * 100;
	}

	public int getPlayingDimX() {
		return x - 2;
	}

	public int getPlayingDimY() {
		return y - gameOverLimit - 1;
	}
}