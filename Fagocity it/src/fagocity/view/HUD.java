package fagocity.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import fagocity.controller.PlayerController;
import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.Player;

/* Utiliza o pattern DAO */
public class HUD implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int score;
	private static int highscore;
	private String fileName = "highscore.txt";
	private static long initialTime;
	private static long currentTime;
	private static int playerInitialRadius;
	
	HUD(int initialScore) throws Exception {
		HUD.score = initialScore;
		initialTime = System.currentTimeMillis();
		playerInitialRadius = Player.defaultRadius;
		loadHighscore();
	}
	
	public static void update() {
		Player player = PlayerController.getPlayer();
		if(player != null) {
			currentTime = System.currentTimeMillis();
			score = 107 * (player.getRadius() - playerInitialRadius) + (int)(currentTime/70 - initialTime/70);
		}
	}
	
	/* Salva o highscore no disco */
	public void saveHighscore() throws IOException {
		if(score > highscore) {
			File file = new File(fileName);
			FileWriter writer = new FileWriter(file);
			PrintWriter printer = new PrintWriter(writer);
			
			printer.println(score);		
			printer.close();
		}
	}
	
	/* Carrega o highscore do disco */
	public void loadHighscore() throws Exception {
		File file = new File(fileName);
		FileReader reader = new FileReader(file);
		BufferedReader bufReader = new BufferedReader(reader);
		HUD.highscore = Integer.parseInt(bufReader.readLine());
		reader.close();
	}

	public static int getScore() {
		return score;
	}
	
	public static int getHighScore() {
		return highscore;
	}
	

}
