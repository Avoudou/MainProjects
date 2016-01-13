package mainProjectPentris;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class NamePanel extends JPanel {
	

		private JTextField Input;
		private JFrame tempFrame;
		private Scores scores=ReadAndWriteHighScores.readHighScores("highscores.txt");
		int score;

		public NamePanel(JFrame aframe,int score) {
			this.score=score;
			tempFrame=aframe;
			setLayout(new GridLayout(1, 2));
			
			add(new JLabel("name:"));
			Input = new JTextField();
			add(Input);
		
			JButton startButton = new JButton("save");
			startButton.addActionListener(new StartButtonListener());
			add(startButton);
		}

		class StartButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked");
				Score aScore= new Score(Input.getText(), score);
				scores.addScore(aScore);
				ReadAndWriteHighScores.writeHighScores("highscores.txt", scores);
				tempFrame.dispose();

					
					System.out.println();
				}
			}

}
