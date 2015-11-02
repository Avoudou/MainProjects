package graphicsPentris;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PentrisGameBoard {
	/** determines the boards X-dimension */
	private int x;
	/** determines the boards Y-dimension */
	private int y;
	/** Defines game Board */
	private int[][] pentrisGameBoard;
	/** Defines default X coordinate for new shapes insertions */
	private final int defaultXpoint = 5;
	/** Defines default Y coordinate for new shapes insertions */
	private final int defaultYpoint = 4;
	/** Defines/keeps track X coordinate for current active shape */
	private int shapeCoordX = defaultXpoint;
	/** Defines/keeps track Y coordinate for current active shape */
	private int shapeCoordY = defaultYpoint;
	/** Defines the current active shape */
	private int[][] shapeActive = { { 0, 0, 0 }, { 0, 0, 0 } };
	/** remembers the previous rotation of the shape */
	private int[][] shapeActiveBeforeRot;
	/** timer that defines Y-axis movement time intervals */
	private Timer yAxisMovetimer;
	/** Task assignment of yAxisMovetimer */
	private TimerTask yAxisMechanism;
	/**
	 * Integer value to adjust counting in X-axis from the middle of the chosen
	 * shape
	 */
	int shapeMidPosition = (int) (shapeActive[0].length - 1) / 2;
	/**
	 * boolean to determine when to stop the games on x-Axis when the game is
	 * over
	 */
	private boolean stopXaxisMovement;
	/**
	 * keeps track of the rows deleted in each move(used for board ordering and
	 * score)
	 */
	private int deleteCounter = 0;
	private int score = 0;
	Shapes pentominoShapes = new Shapes();
	/**
	 * defines the point the game finishes starting from the top of the board,
	 * should be at least BIG as the maximum y-length of the shapes that going
	 * be used
	 */
	private final int gameOverLimit = 5;
	// TODO fix gameOverLimit to set automatically in constructor
	/**Boolean that graphs use to show game over*/
	private boolean gameOver= false;

	/**
	 * Constructor makes a game board for pentris of the given
	 * coordinates,marking the boarder with integer value "99" and starts the
	 * game
	 */
	public PentrisGameBoard(int xCoord, int yCoord) {
		x = xCoord + 2;
		y = yCoord + gameOverLimit + 1;
		pentrisGameBoard = new int[y][x];
		for (int i = 0; i < x; i++) {
			this.pentrisGameBoard[y - 1][i] = 99;
		}
		for (int j = 0; j < y; j++) {
			this.pentrisGameBoard[j][0] = 99;
			this.pentrisGameBoard[j][x - 1] = 99;
		}
		setNewRandomShape(pentominoShapes.givePentrisData());

	}

	/**
	 * inserts a desired shape into the board its center matching given shape
	 * coordinates and moving it into the board if the coordinates given don't
	 * match the board width and height(top side)
	 */
	public void insertShapeIntoPos() {

		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}
		if (shapeCoordX - shapeMidPosition + shapeActive[0].length > pentrisGameBoard[0].length - 1) {
			shapeCoordX = pentrisGameBoard[0].length - shapeActive[0].length
					+ shapeMidPosition - 1;
		}
		if (shapeCoordY < shapeActive.length - 1) {
			shapeCoordY = shapeActive.length - 1;
		}

		for (int shapeIndexY = shapeActive.length - 1; shapeIndexY >= 0; shapeIndexY--) {
			for (int shapeIndexX = 0; shapeIndexX <= shapeActive[0].length - 1; shapeIndexX++) {
				if (shapeActive[shapeIndexY][shapeIndexX] != 0) {
					pentrisGameBoard[shapeCoordY + shapeIndexY
							- shapeActive.length + 1][shapeCoordX
							- shapeMidPosition + shapeIndexX] = shapeActive[shapeIndexY][shapeIndexX];

				}
			}
		}

	}

	/**
	 * Deletes chosen shape from position given by x-shape,y-shape coordinates
	 * in the game board
	 */
	public void deleteShapeFromPos() {

		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}
		if (shapeCoordX - shapeMidPosition + shapeActive[0].length > pentrisGameBoard[0].length - 1) {
			shapeCoordX = pentrisGameBoard[0].length - shapeActive[0].length
					+ shapeMidPosition - 1;
		}
		if (shapeCoordY < shapeActive.length - 1) {
			shapeCoordY = shapeActive.length - 1;
		}

		for (int shapeIndexY = shapeActive.length - 1; shapeIndexY >= 0; shapeIndexY--) {
			for (int shapeIndexX = 0; shapeIndexX <= shapeActive[0].length - 1; shapeIndexX++)
				if (shapeActive[shapeIndexY][shapeIndexX] != 0) {
					pentrisGameBoard[shapeCoordY + shapeIndexY
							- shapeActive.length + 1][shapeCoordX
							- shapeMidPosition + shapeIndexX] = 0;
				}
		}
	}

	/**
	 * starts game by inserting shapes on top and moving them down until they
	 * hit something , stops game if gameOver
	 */
	public void startGameTimer() {
		yAxisMovetimer = new Timer();
		yAxisMechanism = new TimerTask() {

			@Override
			public void run() {
				moveShapeDownStep();
				printPentrisBoard();

				System.out.println("score" + " : " + score);

			}
		};
		yAxisMovetimer.schedule(yAxisMechanism, 0, 1000);
	}

	/**
	 * Moves a shape one step(index) down to the y axis if the move is valid and
	 * ends the game if board is filled
	 */
	public void moveShapeDownStep() {
		deleteShapeFromPos();
		shapeCoordY++;
		if (checkShapePlacement()) {
			insertShapeIntoPos();

		} else {
			shapeCoordY--;
			insertShapeIntoPos();
			deleteFullRowsMechanism();
			score = score + (int) Math.pow(deleteCounter, 2);
			deleteCounter = 0;
			shapeCoordY = defaultYpoint;
			shapeCoordX = defaultXpoint;
			setNewRandomShape(pentominoShapes.givePentrisData());
			if (gameOver()) {
				System.out.println("gameOver");
				yAxisMovetimer.cancel();
				stopXaxisMovement = true;
				gameOver=true;
			}

		}

	}

	/**
	 * boolean to determine the end of the game(returns true when board is
	 * filled more than a certain point)
	 */
	public boolean gameOver() {

		for (int shapeIndexY = 0; shapeIndexY < gameOverLimit; shapeIndexY++) {
			for (int shapeIndexX = 1; shapeIndexX < pentrisGameBoard[0].length - 1; shapeIndexX++)
				if (pentrisGameBoard[shapeIndexY][shapeIndexX] != 0) {

					return true;
				}

		}

		return false;
	}

	/**
	 * Moves a shape one step(index) Left to the x axis if the move is valid,or
	 * its previous position if not
	 */
	public void moveShapeLeftStep() {

		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}
		deleteShapeFromPos();
		shapeCoordX--;
		if (checkShapePlacement()) {
			insertShapeIntoPos();
		} else {
			shapeCoordX++;
			insertShapeIntoPos();
		}
	}

	/**
	 * Moves a shape one step(index) right to the x axis if the move is valid,or
	 * its previous position if not
	 */
	public void moveShapeRightStep() {

		if (shapeCoordX - shapeMidPosition + shapeActive[0].length > pentrisGameBoard[0].length - 1) {
			shapeCoordX = pentrisGameBoard[0].length - shapeActive[0].length
					+ shapeMidPosition - 1;
		}
		deleteShapeFromPos();
		shapeCoordX++;
		if (checkShapePlacement()) {
			insertShapeIntoPos();

		} else {
			shapeCoordX--;
			insertShapeIntoPos();
		}

	}

	/***/
	public void rotateShape90DegreesMechanism() {
		deleteShapeFromPos();
		shapeActiveBeforeRot = ArraysMethods.deepCopy2DArray(shapeActive);
		rotActiveShape90();
		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}
		if (shapeCoordX - shapeMidPosition + shapeActive[0].length > pentrisGameBoard[0].length - 1) {
			shapeCoordX = pentrisGameBoard[0].length - shapeActive[0].length
					+ shapeMidPosition - 1;
		}
		if (shapeCoordY < shapeActive.length - 1) {
			shapeCoordY = shapeActive.length - 1;
		}
		if (checkShapePlacement()) {
			insertShapeIntoPos();
		} else {

			shapeActive = shapeActiveBeforeRot;
			insertShapeIntoPos();
		}

	}

	/** rotates the active shape as 2d-array */
	public void rotActiveShape90() {
		int[][] shapeRot = new int[shapeActive[0].length][shapeActive.length];
		for (int i = 0; i < shapeRot.length; i++) {
			for (int j = 0; j < shapeRot[0].length; j++) {
				shapeRot[i][j] = shapeActive[shapeRot[0].length - j - 1][i];

			}
		}
		shapeActive = shapeRot;
	}

	/**
	 * Returns True if a shape can be placed in given x , y coordinates(checks
	 * that no index position not already occupied)
	 */
	public boolean checkShapePlacement() {

		if (shapeCoordX - shapeMidPosition < 1) {
			shapeCoordX = 1 + shapeMidPosition;
		}
		if (shapeCoordX - shapeMidPosition + shapeActive[0].length > pentrisGameBoard[0].length - 1) {
			shapeCoordX = pentrisGameBoard[0].length - shapeActive[0].length
					+ shapeMidPosition - 1;
		}
		if (shapeCoordY < shapeActive.length - 1) {
			shapeCoordY = shapeActive.length - 1;
		}

		for (int shapeIndexY = 0; shapeIndexY < shapeActive.length; shapeIndexY++) {
			for (int shapeIndexX = 0; shapeIndexX < shapeActive[0].length; shapeIndexX++) {
				if (shapeActive[shapeIndexY][shapeIndexX] != 0
						&& pentrisGameBoard[shapeCoordY + shapeIndexY
								- shapeActive.length + 1][shapeCoordX
								- shapeMidPosition + shapeIndexX] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/** Deletes the full rows from the board and moves floating elements down */
	public void deleteFullRowsMechanism() {
		for (int i = pentrisGameBoard.length - 2; i >= 0; i--) {
			if (checkIfRowFull(i)) {
				deleteCounter++;
				for (int j = 1; j < pentrisGameBoard[0].length - 1; j++) {
					pentrisGameBoard[i][j] = 0;

				}
				copyBoardDown(i);
				deleteFullRowsMechanism();
			}
		}
	}

	/** checks if a given row is full or not */
	public boolean checkIfRowFull(int rowCord) {
		int[] x = pentrisGameBoard[rowCord];
		if (ArraysMethods.arrayMinValue(x) != 0) {
			return true;

		}
		return false;
	}

	/** checks if a given row is empty or not */
	public boolean checkIfRowEmpty(int rowCord) {
		int[] x = pentrisGameBoard[rowCord];
		if (ArraysMethods.arrayMaxValue(x) == 0) {
			return true;

		}
		return false;
	}

	/** sets the shape of input as active shape of the object */
	public void setActiveShape(int[][] chooseShape) {
		shapeActive = chooseShape;
		shapeMidPosition = (int) (shapeActive[0].length - 1) / 2;
		;
	}

	/**
	 * pick a random shape from an ArrayList database and sets it as Active
	 * shape
	 */
	public void setNewRandomShape(ArrayList<int[][]> shapeDatabase) {
		int listSize = shapeDatabase.size();
		int[][] shapeToChoose = shapeDatabase.get((int) ((listSize) * Math
				.random()));

		setActiveShape(shapeToChoose);

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
				if (pentrisGameBoard[i][j] == 0) {
					System.out.print(" " + pentrisGameBoard[i][j] + " ");
				} else {
					System.out.print(pentrisGameBoard[i][j] + " ");
				}
			}
		}

	}

	public boolean xAxisMovement() {
		return stopXaxisMovement;
	}
/**Used to  move the shapes that need to fall down after the player accomplishes a full row*/
	public void copyBoardDown(int yCoordRowDeleted) {
		for (int y = yCoordRowDeleted; y >= 1; y--)
			for (int j = 1; j < pentrisGameBoard[0].length - 1; j++) {

				pentrisGameBoard[y][j] = pentrisGameBoard[y - 1][j];

			}
	}
/** exports the current state of the game board as 2D array  to be represented with graphics*/
	public int[][] exportPentrisGraphics() {
		int[][] graphicRep = ArraysMethods.deepCopy2DArray(pentrisGameBoard);
		return graphicRep;
	}
/**returns the number of rows from the top of the board that  if filled game finishes*/
	public int getGameOverLimit() {
		return gameOverLimit;
	}
	/**method to tell the panel to  draw gameOver picture */
	public boolean graphGameOver(){
		return gameOver;
	}
	/** returns the score value of the gameBoard  multiplied by 100*/
	public int getScoreForGraphs(){
		return score*100;
	}
}
