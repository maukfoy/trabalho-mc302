package fagocity;


import fagocity.controller.GameController;
import fagocity.model.GameModel;
import fagocity.view.GameView;

public class GameMain implements Runnable {
	private GameView view;
	private GameModel model;
	private GameController controller;
	private boolean running;
	private Thread thread;
	
	public static void main(String[] args) {
		new GameMain();
	}
	
	GameMain() {
		/* Cria o Model e o View */
		this.model = new GameModel();
		this.view = new GameView();
		this.controller = new GameController();
		
		/* Abre a janela principal */
		this.view.createMainWindow();
		
		/* Cria a Thread */
		this.thread = new Thread(this);
	    this.thread.start();
	}

	
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns  = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		running = true;
		/* Game Loop */
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				/* CHAMAR AQUI O CONTROLLER PARA FAZER OS TICKS */
				//tick();
				updates++;
				delta--;
			}
			/* CHAMAR AQUI O VIEW PARA EXIBIR A TELA */
			//render();
			frames ++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}
		}
	}
	
}
