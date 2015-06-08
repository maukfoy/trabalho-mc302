package fagocity.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ColorBuff {
	private String spritesPath = "src/fagocity/model/assets/sprites/";
	private String speedBuffFile = "ColorBuff.png";
	private BufferedImage colorBuffImage;
	int x, y;
	int width, height;
	int radius;
	static long duration = 5000;
	private static Color currentColorBuff = null;
	
	public ColorBuff(int x, int y) {
		loadSprite();
		this.x = x;
		this.y = y;
		this.width = colorBuffImage.getWidth();
		this.height = colorBuffImage.getHeight();
		this.radius = (int) (colorBuffImage.getHeight() / 2);
	}
	
	/* Carrega o coração que simboliza a o buff de vida */
	public void loadSprite() {
		String path = spritesPath + speedBuffFile;
		File file = new File(path);
		try {
			colorBuffImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite() {
		return colorBuffImage;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public static long getDuration() {
		return duration;
	}

	public static Color getCurrentColorBuff() {
		return currentColorBuff;
	}

	public static void setCurrentColorBuff(Color color) {
		currentColorBuff = color;
		
	}
}