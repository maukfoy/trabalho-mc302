package fagocity.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JPanel;

import fagocity.model.Actor;
import fagocity.model.GameMode;
import fagocity.model.GameModel;
import fagocity.model.GameMode.STATUS;

public class GameView extends JPanel {
	private static final long serialVersionUID = 3856930242116209479L;
	private static final int WIDTH = getScreenWidth();
	private static final int HEIGHT = getScreenHeight();
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	private static HUD hud;
	
	public GameView(String title) {
		this.display = new Display(title, WIDTH, HEIGHT);
		
		/* Cria o HUD */
		try {
			hud = new HUD(0);
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
		
		/*no modo de jogo*/
		if (GameMode.status == STATUS.Fagocity){
			/* Desenha todos actors */	
			ArrayList <Actor> ActorsList = GameModel.getActorsList();
			for(int i = 0; i < GameModel.getActorsList().size(); i++ ) {
				Actor actor = ActorsList.get(i);
				g.setColor(actor.getColor());
				g.fillOval((int)actor.getX(),(int) actor.getY(),(int) actor.getRadius(),(int) actor.getRadius());
			}
			
			/* Desenha o Score */
			g.setColor(Color.white);
			String score = "Score: " + String.valueOf(HUD.getScore());
			g.drawString(score, WIDTH/100, HEIGHT/50);
			
		}		
		else
			MenuView.render(g);
		
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
	
	public static int getScreenWidth() {
	    return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	public static int getScreenHeight() {
	    return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	public static HUD getHUD() {
		return hud;
	}
}


