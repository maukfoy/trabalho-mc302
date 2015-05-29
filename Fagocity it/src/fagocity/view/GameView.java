package fagocity.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import fagocity.model.GameModel;
import fagocity.model.interfaces.IActor;

public class GameView extends JPanel {
	private static final long serialVersionUID = 3856930242116209479L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = WIDTH / 4 * 3;
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	public GameView(String title) {
		this.display = new Display(title, WIDTH, HEIGHT);
	}
	
	/* Metodo que renderiza */
	public void render() {
		/* Cria o Buffer Strategy, caso não exista - Triple Buffering */
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
		
		/* Desenha todos actors */
		ArrayList <IActor> ActorsList = GameModel.getActorsList();
		for(int i = 0; i < GameModel.getActorsList().size(); i++ ) {
			IActor actor = ActorsList.get(i);
			BufferedImage image = (BufferedImage) actor.getImage();
			g.drawImage(image, actor.getX(), actor.getY(), (int)(image.getWidth()*1), (int)(image.getHeight()*1), null);
		}
		
		/* Finaliza os desenhos */
		bs.show();
		g.dispose();
	}
	
	/* Getters e setters */
	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
}


