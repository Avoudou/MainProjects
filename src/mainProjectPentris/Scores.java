package mainProjectPentris;
import java.util.ArrayList;
import java.util.List;

public class Scores {

  private ArrayList<Score> scores= new ArrayList<Score>();

  public Scores(ArrayList<Score> aList) {
	  scores=aList;
    
  }

  public Score getScores(int index) {
    return scores.get(index);
  }

  public void addScore(Score  score) {
	  for (int i = 0; i < scores.size(); i++) {
	      if (score.getScore() > scores.get(i).getScore()) {
	        scores.add(i, score);
	        return;
	      }
	    }
//	  scores.add(score);
	    scores.add(scores.size(), score);
	  }

	  
   
	  
  
  public ArrayList<Score> getListScore(){
	  return scores;
  }
}
