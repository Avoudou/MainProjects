
		package mainProjectPentris;

		import java.util.Timer;
		import java.util.TimerTask;

		public class PentrisMain {

			public static void main(String[] args) {
				GameLogic game = new GameLogic(5,12);
				GameLogic game2 = new GameLogic(5,12);

				PentrisFrame runGame = new PentrisFrame(game, game2);

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
