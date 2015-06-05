package fagocity.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class HUDModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int score = 0;
	private static int highscore;
	private static String path = "src/fagocity/model/assets/data/";
	private static String fileName = "highscore.txt";

	/* Salva o highscore no disco */
	public static void saveHighscore() throws IOException {
		if(score > highscore) {
			File file = new File(path + fileName);
			FileWriter writer = new FileWriter(file);
			PrintWriter printer = new PrintWriter(writer);
			
			printer.println(score);		
			printer.close();
		}
	}
	
	/* Carrega o highscore do disco */
	public static void loadHighscore() throws Exception {
		File file = new File(path + fileName);
		FileReader reader = new FileReader(file);
		BufferedReader bufReader = new BufferedReader(reader);
		HUDModel.highscore = Integer.parseInt(bufReader.readLine());
		reader.close();
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
}
