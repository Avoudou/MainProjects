package mainProjectPentris;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.net.ssl.StandardConstants;
import javax.swing.JFrame;

public class PentrisFrame extends JFrame {
	private PentrisPanel represPanel;
	private GameLogic gameRepresented;
	private GameLogic gameRepresented2;
	
	private final int xAxisSize = 1100;
	private final int yAxisSize = 700;
	
	public PentrisFrame(GameLogic chooseGame,GameLogic chooseGame2) {
		gameRepresented = chooseGame;
		gameRepresented2= chooseGame2;
		setSize(xAxisSize, yAxisSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Space Pentris");
		//setResizable(false);
		represPanel = new PentrisPanel(gameRepresented,gameRepresented2);

		getContentPane().add(represPanel);

		represPanel.fixBackroundSize(xAxisSize, yAxisSize);
		represPanel.fixBordPlaceAndSize();
		
		
		setVisible(true);
	}

	public void refreshRatePetris() {
		represPanel.repaint();
	}

}
