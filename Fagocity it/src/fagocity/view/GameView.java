package fagocity.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JPanel;

import fagocity.model.Actor;
import fagocity.model.GameStatus;
import fagocity.model.GameModel;
import fagocity.model.GameStatus.STATUS;
import fagocity.model.HUDModel;

public class GameView extends JPanel {
	private static final long serialVersionUID = 3856930242116209479L;
	private static final int WIDTH = getScreenWidth();
	private static final int HEIGHT = getScreenHeight();
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	private static HUDView hud;
	
	public GameView(String title) {
		this.display = new Display(title, WIDTH, HEIGHT);
		
		/* Da load no Highscore do disco */
		try {
			HUDModel.loadHighscore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		/* Desenha todos actors */
		renderActors();
		
		/* Desenha o HUD */
		HUDView.render(g);
		
		/*se nao estiver no modo jogavel, da render no menu ou help*/
		if (GameStatus.status != STATUS.Fagocity)
			MenuView.render(g);
		
		/* Finaliza os desenhos */
		bs.show();
		g.dispose();
	}
	
	private void renderActors() {
		/* Desenha todos actors */	
		ArrayList <Actor> ActorsList = GameModel.getActorsList();
		for(int i = 0; i < GameModel.getActorsList().size(); i++ ) {
			Actor actor = ActorsList.get(i);
			g.setColor(actor.getColor());
			g.fillOval((int)actor.getX(),(int) actor.getY(),(int) actor.getRadius(),(int) actor.getRadius());
		}
	}
	
	/* Getters e setters */
	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
	
	public static int getScreenWidth() {
	    return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	public static int getScreenHeight() {
	    return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	public static HUDView getHUD() {
		return hud;
	}
}


