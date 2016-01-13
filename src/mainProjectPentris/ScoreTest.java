package mainProjectPentris;

import javax.swing.JFrame;

public class ScoreTest {
	public static void main(String[] args) {
		
	JFrame scoreFrame= new JFrame();
	scoreFrame.setSize(400	, 400);
	scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	scoreFrame.setTitle("Scores Pentris");
	scoreFrame.setResizable(false);
	ScorePanel aScorePanel= new ScorePanel();
	scoreFrame.add(aScorePanel);
	scoreFrame.setVisible(true);
}
	}
