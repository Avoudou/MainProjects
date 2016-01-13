package mainProjectPentris;
import java.io.*;
import java.util.*;

public class ReadAndWriteHighScores {

  public static Scores readHighScores(String highscoresFilename) {
    ArrayList<Score> scores = new ArrayList<Score>();
    try {
      Scanner scanner = new Scanner(new File(highscoresFilename));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if(line.length()==0){
        	break;
        }
        String[] nameAndScore = line.split(" ");
        String name = nameAndScore[0];
        System.out.println(Arrays.toString(nameAndScore));
        int score = Integer.parseInt(nameAndScore[1]) ;
        Score scoreEntry = new Score(name, score);
        scores.add(scoreEntry);
      }
      scanner.close();
      return new Scores(scores);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void writeHighScores(String highscoresFilename, Scores scores) {
    try {
      FileWriter fileWriter = new FileWriter(highscoresFilename);
      PrintWriter printWriter = new PrintWriter(fileWriter);
      ArrayList<Score> scoreEntries = scores.getListScore();
      for (int i = 0; i < scoreEntries.size(); i++) {
        Score scoreEntry = scoreEntries.get(i);
        printWriter.println(scoreEntry.getName() + " " + scoreEntry.getScore());
      }
      printWriter.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
