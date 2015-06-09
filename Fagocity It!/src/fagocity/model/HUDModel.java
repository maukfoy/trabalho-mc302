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
	private  int score = 0;
	private  int highscore;
	private  BufferedImage heartImage;
	private  String dataPath = "src/fagocity/model/assets/data/";
	private  String highscoreFile = "highscore.txt";
	private  String spritesPath = "src/fagocity/model/assets/sprites/";
	private  String heartFile = "heart64x64.png";
	private  Double fagocityStreak = 0.0;
	private  int defaultPointsTillBoss = 4200;

	/* Salva o highscore no disco */
	public  void saveHighscore() throws IOException {
		if(score > highscore) {
			File file = new File(dataPath + highscoreFile);
			FileWriter writer = new FileWriter(file);
			PrintWriter printer = new PrintWriter(writer);
			
			printer.println(score);
			printer.close();
		}
	}
	
	/* Carrega o highscore do disco */
	public  void loadHighscore() {
		try {
			File file = new File(dataPath + highscoreFile);
			FileReader reader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(reader);
			this.highscore = Integer.parseInt(bufReader.readLine());
			reader.close();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	/* Carrega o coração que simboliza as vidas */
	public  void loadHeart() {
		String path = spritesPath + heartFile;
		File file = new File(path);
		try {
			heartImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  void loadHUD() {
		loadHighscore();
		loadHeart();
	}

	public  int getScore() {
		return score;
	}
	
	public  void setScore(int score) {
		this.score = score;
	}
	
	public  int getHighScore() {
		return highscore;
	}
	
	public  BufferedImage getHeartImage() {
		return heartImage;
	}

	public void setFagocityStreak(double fagocityStreak) {
		this.fagocityStreak = fagocityStreak;		
	}
	
	public Double getFagocityStreak() {
		return this.fagocityStreak;
	}
	public int getDefaultPointsTillBoss()
	{
		return defaultPointsTillBoss;
	}
}
