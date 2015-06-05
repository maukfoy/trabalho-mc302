package fagocity.view;

import java.awt.Color;
import java.awt.Graphics;

import fagocity.model.HUDModel;

public class HUDView {
	private static final int width = GameView.getScreenWidth();
	private static final int height = GameView.getScreenHeight();
	
	public static void render(Graphics g) {
		/* Desenha o Score */
		g.setColor(Color.white);
		String score = "Score: " + String.valueOf(HUDModel.getScore());
		String highscore = "Highscore: " + String.valueOf(HUDModel.getHighScore());
		g.drawString(score, width/100, height/50);
		g.drawString(highscore, width/100 + 75, height/50);
	}

}
