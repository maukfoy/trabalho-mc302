package fagocity.controller;

import fagocity.model.GameStatus;
import fagocity.model.HUDModel;
import fagocity.model.Player;
import fagocity.model.GameStatus.STATUS;
import fagocity.view.HUDView;

public class HUDController {

	private static long initialTime = 0;
	private static long currentTime;
	private static int score = 0;
	private static double fagocityStreak = 0;
	private static int currentKillStreak = 0;
	private static int minimumKillStreak = 2;
	private static int streaksPassed = 0;
	private static int totalFagocitedRadius = 0; // soma do raio dos inimigos fagocitados
	
	public static void update() {
		Player player = PlayerController.getPlayer();
		if(player != null) {
			if(GameStatus.status == STATUS.Fagocity) {
				currentTime = System.currentTimeMillis();
				score = 1*(streaksPassed+1)*totalFagocitedRadius*totalFagocitedRadius + (int)(currentTime/50 - initialTime/50)*(streaksPassed+1);
			}
			else
				score = 0;
			HUDModel.setScore(score);
		}
	}
	
	public static void setInitialTime(long initialTime) {
		HUDController.initialTime = initialTime;
	}
	
	public static void updateFagocityStreak() {
		currentKillStreak++;
		
		fagocityStreak = (double)(currentKillStreak / ((double)minimumKillStreak + (double)streaksPassed));
		System.out.println("streak: " +fagocityStreak);
		
		HUDModel.setFagocityStreak(fagocityStreak);
		
		/* Se o streak estiver completo */
		if(fagocityStreak >= 1) {
			HUDView.fagocityStreakTrigger();
			streaksPassed++;
			resetFagocityStreak();
			Player player = PlayerController.getPlayer();
			player.setGrowingRadius(Player.defaultRadius - player.getRadius());
			PlayerController.playPlayerSound("StreakSound");
		}
	}
	
	public static void resetFagocityStreak() {
		currentKillStreak = 0;
		HUDModel.setFagocityStreak(0);
	}

	public static void setTotalFagocitedRadius(int radius) {
		totalFagocitedRadius += radius;		
	}
	
	public static int getStreaksPassed() {
		return streaksPassed;
	}
	
}
