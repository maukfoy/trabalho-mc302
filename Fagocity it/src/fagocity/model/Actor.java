package fagocity.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import fagocity.model.interfaces.IActor;

public abstract class Actor implements IActor {
	public int x, y;
	private double velX, velY;
	private BufferedImage image;
	
	public Actor(int x, int y, String type) {
		this.x = x;
		this.y = y;
		
		/* Guarda a imagem do actor na memoria */
		String path = "assets/sprites/" + type + ".png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Getters and setters */
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public BufferedImage getImage() {
		return this.image;
	}
}