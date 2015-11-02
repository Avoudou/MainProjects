package graphicsPentris;

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
	
	//LIST OF IMAGES  USED FOR GAME REPRESENTATION
	/** stores Game background image */
	private ImageIcon backround;
	/**Stores game-board background*/
	private ImageIcon boardBackround;
	/**stores image for the game boards boarder squares*/
	private ImageIcon boardBoarder;
	/**stores image for the Shape squares*/
	private ImageIcon shapeImage;
	/**stores image Game Over message*/
	private ImageIcon gameOver;
	/**stores image scoreWindow frame*/
	private ImageIcon scoreWindow;
	/** Stores Main Menu Basic Image*/
	private ImageIcon mainPentrisMenu;
	/**Stores game to represent*/
	private PentrisGameBoard thisGame;
	
	//BASIC COORDINATES DEFINITIONS
	/**Dimensions of the basic squares that the shapes and game board array is consisting of*/
	private final int basicBoardElement = 60;
	/**Dimension for the  background during the game-play of the game*/
	private int backroundXDim;
	/**Dimension for the background during the game-play of the game*/
	private int backroundYDim;
	/**Dimension for the game board  background during the game-play of the game*/
	private int boardXDim;
	/**Dimension for the game board background during the game-play of the game*/
	private int boardYDim;
	/** where the game board will appear */
	private int boardXCoord;
	/** where the game board will appear */
	private int boardYCoord;
	
	private boolean mainMenuPentris=true;

	
	
	public PentrisPanel(PentrisGameBoard pickGame) {
		thisGame = pickGame;
		addKeyListener(new CustomKeyListener());
		setFocusable(true);
		
		//List of images used for the  game representation

		String imagePathGameBoardBack = "PentrisTextures/back2.jpg";
		String imagePathBoarder = "PentrisTextures/boarder.jpg";
		String imagePatheShape = "PentrisTextures/block1.jpg";
		String imagePatheGameOver = "PentrisTextures/gameOver.png";
		String imagePathScoreWindow = "PentrisTextures/scoreWindow.png";
		backround = new ImageIcon("PentrisTextures/back1.jpg");
		boardBackround = new ImageIcon(imagePathGameBoardBack);
		boardBoarder = new ImageIcon(imagePathBoarder);
		shapeImage = new ImageIcon(imagePatheShape);
		gameOver = new ImageIcon(imagePatheGameOver);
		scoreWindow = new ImageIcon(imagePathScoreWindow);
		mainPentrisMenu= new ImageIcon("PentrisTextures/mainPentrisMenu.jpg");
	}
	
	
	/** All pentris graphics run here */
	@Override
	public void paintComponent(Graphics pentrisGraphics) {
		super.paintComponent(pentrisGraphics);
		
		if(mainMenuPentris){
			//draws main menu interface
			pentrisGraphics.drawImage(mainPentrisMenu.getImage(), 0, 0, backroundXDim,
					backroundYDim, null);
		}
		else{
		//Draw game-board backgrounds
		pentrisGraphics.drawImage(backround.getImage(), 0, 0, backroundXDim,
				backroundYDim, null);
		pentrisGraphics.drawImage(boardBackround.getImage(), boardXCoord,
				boardYCoord, boardXDim, boardYDim, null);
		showScoreAppear(pentrisGraphics);
		//draw board
		showPentrisBoard(pentrisGraphics);
		//draw board foregrounds
		showGameOver(pentrisGraphics);
		}
	}

	/** Fixes the game play background to match the JFrame(or desired) size */
	public void fixBackroundSize(int x, int y) {
		backroundXDim = x;
		backroundYDim = y;

	}
	/**Fixes key dimensions and coordinates for the gameBoard to be represented*/
	public void fixBordPlaceAndSize() {
		boardXDim = thisGame.getGraphX() * basicBoardElement;
		boardYDim = thisGame.getGraphY() * basicBoardElement;
		boardXCoord = (backroundXDim - (thisGame.getGraphX() * basicBoardElement)) / 2;
		boardYCoord = (backroundYDim - (thisGame.getGraphY() * basicBoardElement)) / 2;

	}
/**Key Listener for  pentris game*/
	class CustomKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				thisGame.moveShapeRightStep();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				thisGame.moveShapeLeftStep();
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				thisGame.rotateShape90DegreesMechanism();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				thisGame.moveShapeDownStep();
			}
			// Starts the game
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (mainMenuPentris == true) {
					mainMenuPentris = false;
					thisGame.startGameTimer();
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				if (thisGame.graphGameOver()) {

					setMainMenuInterface();
					thisGame = new PentrisGameBoard(thisGame.getPlayingDimX(), thisGame.getPlayingDimY()) ;

				}
		}

		// need for no exceptions
		public void keyReleased(KeyEvent e) {
		}
	}

	/**
	 * Attaches a window showing the games score at the bottom-mid of the game
	 * board
	 */
	private void showScoreAppear(Graphics pentrisGraphics) {
		final int scoreWindowXDim = 300;
		final int scoreWindowYDim = 80;
		pentrisGraphics.drawImage(scoreWindow.getImage(), boardXCoord
				+ (boardXDim - scoreWindowXDim) / 2, boardYCoord + boardYDim,
				scoreWindowXDim, scoreWindowYDim, null);
		Font  usedFont= new Font("default", Font.BOLD, 16);
			setFont(usedFont);
			
		  
		// add 60 pixels to the score string to make it appear correctly in
			// place
		pentrisGraphics.drawString("SCORE: " + thisGame.getScoreForGraphs(),
				boardXCoord + (boardXDim - scoreWindowXDim) / 2 + 90,
				boardYCoord + boardYDim + scoreWindowYDim / 2);
	}



	/** Displays gameOver message */
	private void showGameOver(Graphics pentrisGraphics) {
		final int gameOverMessageXDim= 200;
		final int gameOverMessageYDim= 50;
		
		if (thisGame.graphGameOver()) {
			pentrisGraphics.drawImage(gameOver.getImage(), boardXCoord
					+ (boardXDim - gameOverMessageXDim) / 2, boardYCoord, gameOverMessageXDim, gameOverMessageYDim, null);

		}
	}
/**Displays the game board with graphics in JFrame*/
	private void showPentrisBoard(Graphics pentrisGraphics) {
		int[][] thisBoardState = thisGame.exportPentrisGraphics();

		/**
		 * defines what part of the pentris 2dArray is not going to be shown
		 * (space to insert shapes and determine GameOver)
		 */
		int invisibleSqrs = thisGame.getGameOverLimit();

		for (int i = invisibleSqrs - 1; i < thisBoardState.length; i++) {
			for (int j = 0; j < thisBoardState[0].length; j++) {
				if (i == invisibleSqrs - 1) {
					pentrisGraphics.drawImage(boardBoarder.getImage(),
							boardXCoord + j * basicBoardElement, boardYCoord
									+ i * basicBoardElement - invisibleSqrs
									* basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] != 0 && thisBoardState[i][j] != 99
						&& i != invisibleSqrs - 1) {
					pentrisGraphics.drawImage(shapeImage.getImage(),
							boardXCoord + j * basicBoardElement, boardYCoord
									+ i * basicBoardElement - invisibleSqrs
									* basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}
				if (thisBoardState[i][j] == 99) {
					pentrisGraphics.drawImage(boardBoarder.getImage(),
							boardXCoord + j * basicBoardElement, boardYCoord
									+ i * basicBoardElement - invisibleSqrs
									* basicBoardElement, basicBoardElement,
							basicBoardElement, null);
				}

			}

		}
	}
	
	public void setMainMenuInterface(){
		 mainMenuPentris= true;
	}

}
