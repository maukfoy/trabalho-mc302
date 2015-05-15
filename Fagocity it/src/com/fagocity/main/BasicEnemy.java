package com.fagocity.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Fagocity extends Canvas implements Runnable {
	private static final long serialVersionUID = 2929390961970147223L;
	
	/* Tamanho da janela do jogo */
	public static final int LARGURA = 800, ALTURA = 600;
	
	private Thread thread;
	private  boolean rodando = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawnner;
	
	/*******************/
	/*INICO DO VIDEO 9*/
	/******************/
	/*public enum ESTADO
	{
		Menu, Game;
	}
	public ESTADO estadoJogo = ESTADO.Menu;*/ //2:30 video 9
	
	
	public Fagocity() 
	{
		handler = new Handler();
		hud = new HUD();
		spawnner = new Spawn (handler);
		
		/* Cria o objeto que recebe as informações do teclado */
		this.addKeyListener(new Input(handler));
		
		/* Cria a janela */
		new Janela(ALTURA, LARGURA, "Fagocity It!", this);
				
		spawnner.CriaJogador();
	}
	
	/**********/
	/* Thread */
	/**********/
	/* Método que inicia o thread */
	public synchronized void start() 
	{
		thread = new Thread(this);
		thread.start();
		rodando = true;
	}
	
	/* Método que interrompe o thread */
	public synchronized void stop()
	{
		try {
			thread.join();
			rodando = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Método com as ações de atualizar, renderizar
	 * e mostrar FPS realizadas pelo thread */
	public void run() 
	{
		this.requestFocus(); //nao necessita clicar na tela para input funcionar
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(rodando){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(rodando)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	/* Chama método para atualizar informações de objetos */
	private void tick() 
	{
		handler.tick();
		hud.tick();
		spawnner.tick();
	}
	
	/* Método que renderiza */
	private void render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
		if( bs == null ) 
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA, ALTURA);
		
		handler.render(g);
		hud.render(g);
		
		
		g.dispose();
		bs.show();
	}
	
	/*PENSAR EM LOCAL MAIS ADEQUADO PARA ESSA FUNÇÃO*/
	/*limita a liberdade do objeto*/
	public static float limita (float var, float min, float max)
	{
		if (var >= max)
			return max;
		else if (var <= min)
			return min;
		else
			return var;
	}

	public static void main(String[] args)
	{		
		new Fagocity();
	}

}
