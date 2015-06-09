package fagocity.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fagocity.controller.GameController;
import fagocity.controller.HUDController;
import fagocity.controller.SpawnController;
import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.HUDModel;
import fagocity.model.Player;

public class HUDView {
	private final int width;
	private final int height;
	private long FagocityStreakTextDuration = 3000; //em milisegundos
	private long lastFagocityStreakTime = 0;
	private GameView view;
	private GameModel model;
	private SpawnController spawnController;
	private HUDModel hudModel;
	private GameController controller;
	private HUDController hudController;
	private Player player;
	private int streaksPassed = 0;
	
	public HUDView (GameView view, GameModel model, GameController controller)
	{
		this.view = view;
		this.model = model;
		this.controller = controller;
		
		hudModel = model.getHudModel();
		spawnController = controller.getSpawnController();
		hudController = controller.getHudController();
		player = (Player) controller.getPlayer();

		
		
		width = view.getScreenWidth();
		height = view.getScreenHeight();
	}
	
	public void render(Graphics g) {
		/* Desenha o Score */
		Font fnt = new Font ("arial", 1, 17);
		g.setFont (fnt);
		g.setColor(Color.DARK_GRAY);
		String score = "Score: " + String.valueOf(hudModel.getScore());
		String highscore = "Highscore: " + String.valueOf(hudModel.getHighScore());
		g.drawString(score, width/100, height/50);
		g.drawString(highscore, width/100 + 150, height/50);
		
		/*
		/* Desenha os corações de vida */
		BufferedImage image = hudModel.getHeartImage();
		for(int i = 0; i < player.getLifes(); i++) {
			if(playerAlive())
				g.drawImage(image, width/100 + (int)(image.getWidth()*0.5*i) + 10*i , height/30, (int)(image.getWidth()*0.5), (int)(image.getHeight()*0.5), null);
		}
		
		/* Desenha a barra de Fagocity Streak */
		double fagocityStreak = hudModel.getFagocityStreak();
		/* Parte externa */
		g.setColor(Color.DARK_GRAY);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(1));
		g.drawRect((int)(width/2 - width/10 -1), (int)(height/100 -1), (int)(width/5 +1), (int)(height/40 +1));
		/* Parte interna */
		g.setColor(Color.green);
		g.fillRect((int)(width/2 - width/10), (int)(height/100), (int)((width/5) * fagocityStreak), (int)(height/40));
		
		/* Mostra o numero de streaks */
		String streaks = "Fagocity Streaks: " + streaksPassed;
		g.setColor(Color.DARK_GRAY);
		g.drawString(streaks, (int)(width/2 - width/10) + (int)(width/5 +1) +20 , (int)(height/35));
		
		
		/* Mostra o texto de Fagocity Streak */
		if(fagocityStreakTriggered())
			showFagocityStreakText(g);
	}
	
	/* Verifica se o player está vivo */
	private boolean playerAlive() {
		ArrayList<Actor> list = model.getActorsList();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof Player)
				return true;
		}
		return false;
	}
	
	/* Mostra o texto fagocity Streak por alguns segundos na tela */
	private void showFagocityStreakText(Graphics g) {		
		String text = "FAGOCITY STREAK!";
		Font font = new Font ("arial", 1, 50);
		FontMetrics fm = g.getFontMetrics ( font );
		int sw = fm.stringWidth ( text );
		g.setFont ( font );
		g.setColor(spawnController.generateRandomColor());
		g.drawString ( text , ( width + sw ) / 2 - sw , (height/100 -1) + 120 );
	}
	/* Marca que o Fagocity Streak deve ser iniciado */
	public void fagocityStreakTrigger() {
		lastFagocityStreakTime = System.currentTimeMillis();
	}
	
	/* Verifica se o fagocity streak deve ser exibido da tela */
	private boolean fagocityStreakTriggered() {
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastFagocityStreakTime < FagocityStreakTextDuration)
			return true;
		else
			return false;
	}
	public void setStreaksPassed (int streaks){
		this.streaksPassed = streaks;
	}

}
