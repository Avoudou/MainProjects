package graphicsPentris;
import java.util.Timer;
import java.util.TimerTask;

public class GraficsTest {

	public static void main(String[] args) {
		PentrisGameBoard game = new PentrisGameBoard(6, 12);
		PentrisFrame runGame = new PentrisFrame(game);
		game.startGameTimer();

		Timer refreshTimer = new Timer();
		TimerTask refreshRate = new TimerTask() {

			@Override
			public void run() {
				runGame.refreshRatePetris();

			}
		};
		refreshTimer.schedule(refreshRate, 0, 16);

		

}
}