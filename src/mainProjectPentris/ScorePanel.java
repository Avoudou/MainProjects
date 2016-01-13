package mainProjectPentris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	
	private Scores scores=ReadAndWriteHighScores.readHighScores("highscores.txt");
	

	public ScorePanel() {
	
		

	}

	@Override
	public void paintComponent(Graphics scoreGraphics) {
		super.paintComponent(scoreGraphics);
		
		
		//scoreGraphics.drawRect(0, 0, 200, 200);
		ArrayList<Score> listdata = scores.getListScore();
		
		Font usedFont = new Font("default", Font.BOLD, 16);
		
		scoreGraphics.setColor(Color.RED);
		
		setFont(usedFont);
		for(int i=0;i<Math.min(4,listdata.size());i++)
		scoreGraphics.drawString(" "+listdata.get(i).getName()+"    "+listdata.get(i).getScore(), 60,50+20*i);
		
		}
	}


