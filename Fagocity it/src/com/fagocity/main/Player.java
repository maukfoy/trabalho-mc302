package com.fagocity.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends ObjetoJogo {
	
	Handler handler;
	
	/* Construtor */
	public Player(int x, int y, ID id, Handler handler) {
		/* Seta a posição inicial do jogador */
		super(x, y, id);
		this.handler = handler;
	}
	
	/* Método que atualiza as informações do objeto */
	public void tick() {
		x += velX;
		y += velY;
		
		/*limita a liberdade do jogador para dentro dos limites da tela*/
		x = Fagocity.limita (x, 0, Fagocity.LARGURA - 37);
		y = Fagocity.limita (y, 0, Fagocity.ALTURA - 60);
		
		handler.addObjeto(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.1f, handler));
		
		collision();

	}
	
	/* Renderiza o objeto */
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	private void collision(){
		for (int i = 0; i < handler.obj.size(); i++){
			
			ObjetoJogo tempObject = handler.obj.get(i);
			
			if (tempObject.getID() == ID.BasicEnemy){
				if (getBounds().intersects(tempObject.getBounds())){
					//collision code
					HUD.vida -= 2;
				}
			}
		}
	}
}
