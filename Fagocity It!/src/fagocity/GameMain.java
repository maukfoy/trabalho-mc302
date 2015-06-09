package fagocity;

import java.awt.Canvas;

import fagocity.controller.GameController;
import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;
import fagocity.view.GameView;

@SuppressWarnings("serial")
public class GameMain extends Canvas implements Runnable {
	private static final int MilisToSecs = 1000;
	private GameView view;
	private GameController controller;
	private boolean running;
	private Thread thread;
	
	public static void main(String[] args) {
		new GameMain();
	}
	
	public GameMain() {
		/* Cria o Model, View e o Controller */
		this.view = GameView.getInstance("Fagocity It!");
		this.controller = GameController.getInstance();
		
		/* Inicializa o jogo no menu principal */
		GameStatus.status = STATUS.Menu;
		
		/* Cria a Thread */
		this.thread = new Thread(this);
	    this.thread.start();
	}
	
	public void run() {
		long lastTime = System.currentTimeMillis();
		final double amountOfTicks = 60.0;
		double ms  = MilisToSecs / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		running = true;
		/* Game Loop */
		while(running) {
			long now = System.currentTimeMillis();
			delta += (now - lastTime) / ms;
			lastTime = now;
			if(delta >= 1) {
				controller.update();
				updates++;
				delta--;
			}
			view.render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
	}
}