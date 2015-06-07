package fagocity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fagocity.controller.SpawnController;
import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.HUDModel;
import fagocity.model.Player;

public class HUDView {
	private static final int width = GameView.getScreenWidth();
	private static final int height = GameView.getScreenHeight();
	private static long FagocityStreakTextDuration = 3000; //em milisegundos
	private static long lastFagocityStreakTime = 0;
	
	public static void render(Graphics g) {
		/* Desenha o Score */
		Font fnt = new Font ("arial", 1, 17);
		g.setFont (fnt);
		g.setColor(Color.DARK_GRAY);
		String score = "Score: " + String.valueOf(HUDModel.getScore());
		String highscore = "Highscore: " + String.valueOf(HUDModel.getHighScore());
		g.drawString(score, width/100, height/50);
		g.drawString(highscore, width/100 + 150, height/50);
		
		/*
		/* Desenha os corações de vida */
		BufferedImage image = HUDModel.getHeartImage();
		for(int i = 0; i < Player.lives; i++) {
			if(playerAlive())
				g.drawImage(image, width/100 + (int)(image.getWidth()*0.5*i) + 10*i , height/30, (int)(image.getWidth()*0.5), (int)(image.getHeight()*0.5), null);
		}
		
		/* Desenha a barra de Fagocity Streak */
		double fagocityStreak = HUDModel.getFagocityStreak();
		/* Parte externa */
		g.setColor(Color.DARK_GRAY);
		g.drawRect((int)(width/2 - width/10 -1), (int)(height/100 -1), (int)(width/5 +1), (int)(height/40 +1));
		/* Parte interna */
		g.setColor(Color.green);
		g.fillRect((int)(width/2 - width/10), (int)(height/100), (int)((width/5) * fagocityStreak), (int)(height/40));
	
		/* Mostra o texto de Fagocity Streak */
		if(fagocityStreakTriggered())
			showFagocityStreakText(g);
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
	
	/* Mostra o texto fagocity Streak por alguns segundos na tela */
	private static void showFagocityStreakText(Graphics g) {		
		String text = "FAGOCITY STREAK!";
		Font font = new Font ("arial", 1, 50);
		FontMetrics fm = g.getFontMetrics ( font );
		int sw = fm.stringWidth ( text );
		g.setFont ( font );
		g.setColor(SpawnController.generateRandomColor());
		g.drawString ( text , ( width + sw ) / 2 - sw , (height/100 -1) + 120 );
	}
	/* Marca que o Fagocity Streak deve ser iniciado */
	public static void fagocityStreakTrigger() {
		lastFagocityStreakTime = System.currentTimeMillis();
	}
	
	/* Verifica se o fagocity streak deve ser exibido da tela */
	private static boolean fagocityStreakTriggered() {
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastFagocityStreakTime < FagocityStreakTextDuration)
			return true;
		else
			return false;
	}

}
