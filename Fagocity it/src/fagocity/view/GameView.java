package fagocity.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fagocity.model.GameModel;


public class GameView extends JPanel {
	private static final long serialVersionUID = 3856930242116209479L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = WIDTH / 4 * 3; // Força aspect ratio 4:3 baseado no WIDTH
	
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	private GameModel model;
	
	public GameView(GameModel model) {
		this.model = model;
		this.display = new Display("Fagocity It!", WIDTH, HEIGHT);
	}
	
	/* Metodo que renderiza */
	public void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		/* Limpa a tela */
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		
		/* Desenha o fundo da tela */
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		/* Desenha o player */
		/* POSTERIORMENTE ESSA PARTE QUE DESENHA O PLAYER VAI TER QUE SER
		 * COLOCADO EM OUTRA PARTE O CODIGO QUE VAI CUIDAR DE DESENHAR TODO O RESTO
		 * DOS OBJETOS DO JOGO. INCLUSIVE O PLAYER
		 */
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("avatar.png"));
			g.drawImage(image, (WIDTH/2 - image.getWidth()/2), (HEIGHT/2 - image.getHeight()/2), image.getWidth(), image.getHeight(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Finaliza os desenhos */
		bs.show();
		g.dispose();
	}
}
