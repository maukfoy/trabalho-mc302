package com.fagocity.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	/*static pois o jogador so tem uma vida*/
	public static float vida = 100;
	
	public void tick()
	{
		/*define os limites da barra de vida*/ 
		vida = Fagocity.limita (vida, 0, 100);
		
	}
	
	public void render(Graphics g)
	{
		/*vida total inicial*/
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		
		/*quantidade restante de vida*/
		g.setColor(Color.green);
		g.fillRect(15, 15, (int) vida * 2, 32);
		
		/*borda da barra*/
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
	}
	
}
