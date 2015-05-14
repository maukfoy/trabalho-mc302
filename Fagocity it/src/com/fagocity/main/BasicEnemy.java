package com.fagocity.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends ObjetoJogo {
	
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		/*velocidade do inimigo*/
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		/*faz inimigo voltar quando bater na parede (limites da tela)*/
		if (y <= 0 || y >= Fagocity.ALTURA - 32) velY *= -1;
		if (x <= 0 || x >= Fagocity.LARGURA - 16) velX *= -1;
		
		handler.addObjeto(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.08f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect (x, y, 16, 16);
	}

}
