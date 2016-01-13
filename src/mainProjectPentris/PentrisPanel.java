package mainProjectPentris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PentrisPanel extends JPanel {

	// LIST OF IMAGES USED FOR GAME REPRESENTATION
	/** stores Game background image */
	private ImageIcon backround;
	/** Stores game-board background */
	private ImageIcon boardBackround;
	/** stores image for the game boards boarder squares */
	private ImageIcon boardBoarder;
	/** stores image Game Over message */
	private ImageIcon gameOver;
	/** stores image scoreWindow frame */
	private ImageIcon scoreWindow;
	/** Stores Main Menu Basic Image */
	private ImageIcon mainPentrisMenu;
	/** stores instructions window image */
	private ImageIcon instructionWindow;
	/** stores image for the Shape squares */
	private ImageIcon shapePImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeXImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeFImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeVImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeYImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeWImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeIImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeTImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeUImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeNImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeLImage;
	/** stores image for the Shape squares */
	private ImageIcon shapeZImage;

	// PENTRIS BOARD TO REPRESENT
	/** Stores game to represent */
	private GameLogic thisGame;
	private GameLogic secondGame;

	// BASIC COORDINATES DEFINITIONS
	/**
	 * Dimensions of the basic squares that the shapes and game board array is consisting of
	 */
	private final int basicBoardElement = 40;
	/** Dimension for the background during the game-play of the game */
	private int backroundXDim;
	/** Dimension for the background during the game-play of the game */
	private int backroundYDim;
	/** Dimension for the game board background during the game-play of the game */
	private int boardXDim;
	/** Dimension for the game board background during the game-play of the game */
	private int boardYDim;
	/** where the game board will appear */
	private int boardXCoord;
	/** where the game board will appear */
	private int boardYCoord;
	
	private int distanceBoards= 320;
	boolean secondPlayer= false;

	private boolean mainMenuPentris = true;
	private boolean keyISPRESSED = false;

	//AudioTrack moveSound= new AudioTrack("Sounds/moveSound.wav");
	
	
	public PentrisPanel(GameLogic pickGame, GameLogic pickGame2) {
		thisGame = pickGame;
		secondGame = pickGame2;
		addKeyListener(new CustomKeyListener());
		setFocusable(true);

		// List of images used for the game representation
		backround = new ImageIcon("PentrisTextures/back1.jpg");
		boardBackround = new ImageIcon("PentrisTextures/back2.jpg");
		boardBoarder = new ImageIcon("PentrisTextures/boarder.jpg");
		gameOver = new ImageIcon("PentrisTextures/gameOver.png");
		scoreWindow = new ImageIcon("PentrisTextures/scoreWindow.png");
		mainPentrisMenu = new ImageIcon("PentrisTextures/mainPentrisMenu.jpg");
		instructionWindow = new ImageIcon("PentrisTextures/instructionBox.png");
		// shape Images
		shapePImage = new ImageIcon("PentrisTextures/block1.jpg");
		shapeXImage = new ImageIcon("PentrisTextures/block2.jpg");
		shapeFImage = new ImageIcon("PentrisTextures/block3.jpg");
		shapeVImage = new ImageIcon("PentrisTextures/block4.jpg");
		shapeYImage = new ImageIcon("PentrisTextures/block5.jpg");
		shapeWImage = new ImageIcon("PentrisTextures/block6.jpg");
		shapeIImage = new ImageIcon("PentrisTextures/block7.jpg");
		shapeTImage = new ImageIcon("PentrisTextures/block8.jpg");
		shapeUImage = new ImageIcon("PentrisTextures/block9.jpg");
		shapeNImage = new ImageIcon("PentrisTextures/block10.jpg");
		shapeLImage = new ImageIcon("PentrisTextures/block11.jpg");
		shapeZImage = new ImageIcon("PentrisTextures/block12.jpg");

	}

	/** All Pentris graphics are drawn here */
	@Override
	public void paintComponent(Graphics pentrisGraphics) {
		super.paintComponent(pentrisGraphics);

		if (mainMenuPentris) {
			// draws main menu interface
			pentrisGraphics.drawImage(mainPentrisMenu.getImage(), 0, 0, backroundXDim, backroundYDim, null);
		} else {
			// Draw game-board backgrounds
			pentrisGraphics.drawImage(backround.getImage(), 0, 0, backroundXDim, backroundYDim, null);
			pentrisGraphics.drawImage(boardBackround.getImage(), boardXCoord, boardYCoord, boardXDim, boardYDim, null);
			if(secondPlayer){
			pentrisGraphics.drawImage(boardBackround.getImage(), boardXCoord+distanceBoards, boardYCoord, boardXDim, boardYDim, null);
			}
			showScoreAppear(pentrisGraphics,thisGame,0);
			if(secondPlayer){
			showScoreAppear(pentrisGraphics, secondGame, 70);
			}
			showInstructions(pentrisGraphics);
			showPentrisBoard(pentrisGraphics);
			if(secondPlayer){
			showPentrisBoard2(pentrisGraphics);
			}

			showGameOver(pentrisGraphics,thisGame,0);
			if(secondPlayer){
			showGameOver(pentrisGraphics,secondGame,distanceBoards);
			}
		}
	}

	/** Fixes the game play background to match the JFrame(or desired) size */
	public void fixBackroundSize(int x, int y) {
		backroundXDim = x;
		backroundYDim = y;

	}

	/** Fixes key dimensions and coordinates for the gameBoard to be represented */
	public void fixBordPlaceAndSize() {
		boardXDim = thisGame.getGraphX() * basicBoardElement;
		boardYDim = thisGame.getGraphY() * basicBoardElement;
		boardXCoord = (backroundXDim - (thisGame.getGraphX() * basicBoardElement)) / 2;
		boardYCoord = (backroundYDim - (thisGame.getGraphY() * basicBoardElement)) / 2;

	}

	/** Key Listener for pentris game */
	class CustomKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && !keyISPRESSED) {
				
				
				thisGame.moveRight();

			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && !keyISPRESSED) {
				
				
				thisGame.moveLeft();

			}
			if (e.getKeyCode() == KeyEvent.VK_UP && !keyISPRESSED) {
				
				
				thisGame.rotateShape90DegreesMechanism();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && !keyISPRESSED) {
				
				
				thisGame.fallDownShape();
			}
			if (e.getKeyCode() == KeyEvent.VK_D && !keyISPRESSED) {
				
				
				secondGame.moveRight();

			}
			if (e.getKeyCode() == KeyEvent.VK_A && !keyISPRESSED) {
				
				
				secondGame.moveLeft();

			}
			if (e.getKeyCode() == KeyEvent.VK_W && !keyISPRESSED) {
				
				
				secondGame.rotateShape90DegreesMechanism();
			}
			if (e.getKeyCode() == KeyEvent.VK_S && !keyISPRESSED) {
				
				
				secondGame.fallDownShape();
			}
			if (e.getKeyCode() == KeyEvent.VK_1) {
				
				
					thisGame.scoreWindow();

				

			}

			// Starts the game
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (mainMenuPentris == true) {
					resetBoards();
					secondPlayer = false;
					mainMenuPentris = false;
					thisGame.startGameTimer();

				}

			}
			if (e.getKeyCode() == KeyEvent.VK_INSERT) {
				if (mainMenuPentris == true) {
					resetBoards();
					secondPlayer = true;
					mainMenuPentris = false;
					thisGame.startGameTimer();
					secondGame.startGameTimer();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE){

					setMainMenuInterface();
					thisGame = new GameLogic(thisGame.getPlayingDimX(), thisGame.getPlayingDimY());

				}
			if (e.getKeyCode() == KeyEvent.VK_HOME){

				
				thisGame = new GameLogic("optimal");
				secondPlayer = false;
				mainMenuPentris = false;
				thisGame.startGameTimer();

			}
		}

		// need for no exceptions
		public void keyReleased(KeyEvent e) {

		}
	}

	/**
	 * Attaches a window showing the games score at the bottom-mid of the game board
	 */
	private void showScoreAppear(Graphics pentrisGraphics,GameLogic aBoard,int xDifference) {
		final int scoreWindowXDim = 200;
		final int scoreWindowYDim = 80;
		pentrisGraphics.drawImage(scoreWindow.getImage(), boardXCoord - scoreWindowXDim - 50, boardYCoord + boardYDim
				+ 20-200+xDifference, scoreWindowXDim, scoreWindowYDim, null);
		Font usedFont = new Font("default", Font.BOLD, 16);
		pentrisGraphics.setColor(Color.RED);
		setFont(usedFont);

		// add 60 pixels to the score string to make it appear correctly in
		// place
		pentrisGraphics.drawString("SCORE: " + aBoard.getScoreForGraphs(),boardXCoord - scoreWindowXDim - 50+30,
				boardYDim + boardYCoord + 20-200+xDifference + scoreWindowYDim / 2);
	}

	/** shows instructions image */
	private void showInstructions(Graphics pentrisGraphics) {
		final int scoreWindowXDim = 300;
		final int scoreWindowYDim = 300;
		pentrisGraphics.drawImage(instructionWindow.getImage(), boardXCoord - scoreWindowXDim - 50, boardYCoord + 40,
				scoreWindowXDim, scoreWindowYDim, null);
	}

	/** Displays gameOver message */
	private void showGameOver(Graphics pentrisGraphics,GameLogic aBoard, int xDim) {
		 final int gameOverMessageXDim = 200;
		final int gameOverMessageYDim = 50;

		if (aBoard.graphGameOver()) {
			pentrisGraphics.drawImage(gameOver.getImage(), xDim+boardXCoord + (boardXDim - gameOverMessageXDim) / 2,
					boardYCoord, gameOverMessageXDim, gameOverMessageYDim, null);

		}
	}

	/** Displays the game board with graphics in JFrame */
	private void showPentrisBoard(Graphics pentrisGraphics) {
		int[][] thisBoardState = thisGame.exportPentrisGraphics();

		/**
		 * defines what part of the pentris 2dArray is not going to be shown (space to insert shapes and determine
		 * GameOver)
		 */
		int invisibleSqrs = thisGame.getGameOverLimit();

		for (int i = invisibleSqrs - 1; i < thisBoardState.length; i++) {
			for (int j = 0; j < thisBoardState[0].length; j++) {

				if (thisBoardState[i][j] == 15) {
					pentrisGraphics.drawImage(shapePImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 25) {
					pentrisGraphics.drawImage(shapeXImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 30) {
					pentrisGraphics.drawImage(shapeFImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 40) {
					pentrisGraphics.drawImage(shapeVImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 45) {
					pentrisGraphics.drawImage(shapeYImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 50) {
					pentrisGraphics.drawImage(shapeWImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 70) {
					pentrisGraphics.drawImage(shapeIImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 75) {
					pentrisGraphics.drawImage(shapeZImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 80) {
					pentrisGraphics.drawImage(shapeTImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 18) {
					pentrisGraphics.drawImage(shapeUImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 11) {
					pentrisGraphics.drawImage(shapeNImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 12) {
					pentrisGraphics.drawImage(shapeLImage.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (i == invisibleSqrs - 1) {
					pentrisGraphics.drawImage(boardBoarder.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}

				if (thisBoardState[i][j] == 99) {
					pentrisGraphics.drawImage(boardBoarder.getImage(), boardXCoord + j * basicBoardElement, boardYCoord
							+ i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}

			}

		}

	}

	private void showPentrisBoard2(Graphics pentrisGraphics) {
		int[][] thisBoardState = secondGame.exportPentrisGraphics();

		/**
		 * defines what part of the pentris 2dArray is not going to be shown (space to insert shapes and determine
		 * GameOver)
		 */
		int invisibleSqrs = secondGame.getGameOverLimit();

		for (int i = invisibleSqrs - 1; i < thisBoardState.length; i++) {
			for (int j = 0; j < thisBoardState[0].length; j++) {
				// TODO 320 =magic number =distance between main board
				if (thisBoardState[i][j] == 15) {
					pentrisGraphics.drawImage(shapePImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 25) {
					pentrisGraphics.drawImage(shapeXImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 30) {
					pentrisGraphics.drawImage(shapeFImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 40) {
					pentrisGraphics.drawImage(shapeVImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}

				if (thisBoardState[i][j] == 45) {
					pentrisGraphics.drawImage(shapeYImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 50) {
					pentrisGraphics.drawImage(shapeWImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 70) {
					pentrisGraphics.drawImage(shapeIImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 75) {
					pentrisGraphics.drawImage(shapeZImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 80) {
					pentrisGraphics.drawImage(shapeTImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 18) {
					pentrisGraphics.drawImage(shapeUImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 11) {
					pentrisGraphics.drawImage(shapeNImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 12) {
					pentrisGraphics.drawImage(shapeLImage.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (i == invisibleSqrs - 1) {
					pentrisGraphics.drawImage(boardBoarder.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}

				if (thisBoardState[i][j] == 99) {
					pentrisGraphics.drawImage(boardBoarder.getImage(), boardXCoord + j * basicBoardElement + distanceBoards,
							boardYCoord + i * basicBoardElement - invisibleSqrs * basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}

			}

		}
	}

	public void setMainMenuInterface() {
		mainMenuPentris = true;
	}
	public void resetBoards(){
		secondGame=new GameLogic(secondGame.getPlayingDimX(), secondGame.getPlayingDimY());
		thisGame =new GameLogic(thisGame.getPlayingDimX(), thisGame.getPlayingDimY());
	}

}
