package graphicsPentris;

import javax.swing.JFrame;

public class PentrisFrame extends JFrame {
	private PentrisPanel represPanel;
	private PentrisGameBoard gameRepresented;
	private final int xAxisSize = 1000;
	private final int yAxisSize = 1000;
	

	public PentrisFrame(PentrisGameBoard chooseGame) {
		gameRepresented = chooseGame;
		setSize(xAxisSize, yAxisSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Space Pentris");
		represPanel = new PentrisPanel(gameRepresented);
		add(represPanel);
		represPanel.fixBackroundSize(xAxisSize, yAxisSize);
		represPanel.fixBordPlaceAndSize();
		
		
		setVisible(true);
	}

	public void refreshRatePetris() {
		represPanel.repaint();
	}

}
