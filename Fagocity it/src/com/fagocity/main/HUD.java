package com.fagocity.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int vida = 100;

	
	public void tick(){
		/*define os limites da barra de vida*/ 
		vida = Fagocity.limita (vida, 0, 100);
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, vida * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	}
	
}
