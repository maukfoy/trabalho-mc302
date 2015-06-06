package fagocity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.HUDModel;
import fagocity.model.Player;

public class HUDView {
	private static final int width = GameView.getScreenWidth();
	private static final int height = GameView.getScreenHeight();
	
	public static void render(Graphics g) {
		/* Desenha o Score */
		Font fnt = new Font ("arial", 1, 17);
		g.setFont (fnt);
		g.setColor(Color.white);
		String score = "Score: " + String.valueOf(HUDModel.getScore());
		String highscore = "Highscore: " + String.valueOf(HUDModel.getHighScore());
		g.drawString(score, width/100, height/50);
		g.drawString(highscore, width/100 + 150, height/50);
		
		/* Desenha os corações de vida */
		BufferedImage image = HUDModel.getHeartImage();
		for(int i = 0; i < Player.lives; i++) {
			if(playerAlive())
				g.drawImage(image, width/100 + (int)(image.getWidth()*0.5*i) + 10*i , height/30, (int)(image.getWidth()*0.5), (int)(image.getHeight()*0.5), null);
		}
	}
	
	/* Verifica se o player está vivo */
	private static boolean playerAlive() {
		ArrayList<Actor> list = GameModel.getActorsList();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof Player)
				return true;
		}
		return false;
	}

}
