package fagocity.model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class HUDModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int score = 0;
	private static int highscore;
	private static BufferedImage heartImage;
	private static String dataPath = "src/fagocity/model/assets/data/";
	private static String highscoreFile = "highscore.txt";
	private static String spritesPath = "src/fagocity/model/assets/sprites/";
	private static String heartFile = "heart64x64.png";
	private static Double fagocityStreak = 0.0;
	private static int defaultPointsTillBoss = 4200;

	/* Salva o highscore no disco */
	public static void saveHighscore() throws IOException {
		if(score > highscore) {
			File file = new File(dataPath + highscoreFile);
			FileWriter writer = new FileWriter(file);
			PrintWriter printer = new PrintWriter(writer);
			
			printer.println(score);
			printer.close();
		}
	}
	
	/* Carrega o highscore do disco */
	public static void loadHighscore() {
		try {
			File file = new File(dataPath + highscoreFile);
			FileReader reader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(reader);
			HUDModel.highscore = Integer.parseInt(bufReader.readLine());
			reader.close();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	/* Carrega o coração que simboliza as vidas */
	public static void loadHeart() {
		String path = spritesPath + heartFile;
		File file = new File(path);
		try {
			heartImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadHUD() {
		loadHighscore();
		loadHeart();
	}

	public static int getScore() {
		return score;
	}
	
	public static void setScore(int score) {
		HUDModel.score = score;
	}
	
	public static int getHighScore() {
		return highscore;
	}
	
	public static BufferedImage getHeartImage() {
		return heartImage;
	}

	public static void setFagocityStreak(double fagocityStreak) {
		HUDModel.fagocityStreak = fagocityStreak;		
	}
	
	public static Double getFagocityStreak() {
		return HUDModel.fagocityStreak;
	}
	public static int getDefaultPointsTillBoss()
	{
		return defaultPointsTillBoss;
	}
}
