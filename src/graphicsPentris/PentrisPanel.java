package graphicsPentris;

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
	/**Stores game to represent*/
	private PentrisGameBoard thisGame;
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

	public PentrisPanel(PentrisGameBoard pickGame) {
		thisGame = pickGame;
		addKeyListener(new CustomKeyListener());
		setFocusable(true);
		String imagePathBack = "PentrisTextures/back1.jpg";
		String imagePathGameBoardBack = "PentrisTextures/back2.jpg";
		String imagePathBoarder = "PentrisTextures/boarder.jpg";
		String imagePatheShape = "PentrisTextures/block1.jpg";
		String imagePatheGameOver = "PentrisTextures/gameOver.png";
		String imagePathScoreWindow = "PentrisTextures/scoreWindow.png";
		backround = new ImageIcon(imagePathBack);
		boardBackround = new ImageIcon(imagePathGameBoardBack);
		boardBoarder = new ImageIcon(imagePathBoarder);
		shapeImage = new ImageIcon(imagePatheShape);
		gameOver = new ImageIcon(imagePatheGameOver);
		scoreWindow = new ImageIcon(imagePathScoreWindow);
	}

	public void paintComponent(Graphics pentrisGraphics) {
		super.paintComponent(pentrisGraphics);

		pentrisGraphics.drawImage(backround.getImage(), 0, 0, backroundXDim,
				backroundYDim, null);
		pentrisGraphics.drawImage(boardBackround.getImage(), boardXCoord,
				boardYCoord, boardXDim, boardYDim, null);

		showPentrisBoard(pentrisGraphics);
		showGameOver(pentrisGraphics);
		showScoreAppear(pentrisGraphics);
	}

	public void fixBackroundSize(int x, int y) {
		backroundXDim = x;
		backroundYDim = y;

	}

	public void fixBordPlaceAndSize() {
		boardXDim = thisGame.getGraphX() * basicBoardElement;
		boardYDim = thisGame.getGraphY() * basicBoardElement;
		boardXCoord = (backroundXDim - (thisGame.getGraphX() * basicBoardElement)) / 2;
		boardYCoord = (backroundYDim - (thisGame.getGraphY() * basicBoardElement)) / 2;

	}

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
		}

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
		// add 100 pixels to the score string to make it appear correctly in
		// place
		pentrisGraphics.drawString("SCORE: " + thisGame.getScoreForGraphs(),
				boardXCoord + (boardXDim - scoreWindowXDim) / 2 + 100,
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

}
