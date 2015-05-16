package tutorial.realtutsgml.wave;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	
	private Fagocity fagocity;
	private Spawn spawnner;
	
	public Menu (Fagocity fagocity, Spawn spawnner)
	{
		this.fagocity = fagocity;
		this.spawnner = spawnner;
	}
	
	public void mousePressed (MouseEvent e)
	{
		int mx = e.getX ();
		int my = e.getY ();
		
		/*Botão Play*/
		if (fagocity.estadoJogo == ESTADO.Menu && mouseOver (mx, my, 300, 190, 200, 64 ))
		{
			fagocity.estadoJogo = ESTADO.Fagocity;
			spawnner.CriaJogador();		
		}
		
		/*Botão Quit*/
		if (fagocity.estadoJogo == ESTADO.Menu && mouseOver (mx, my, 300, 450, 200, 64 ))
		{
			System.exit(1);
		}
		
		/*Botão Help*/
		if (fagocity.estadoJogo == ESTADO.Menu && mouseOver (mx, my, 300, 320, 200, 64 ))
		{
			fagocity.estadoJogo = ESTADO.Help;
		}
		
		/*Botão Back da tela Help*/
		if (fagocity.estadoJogo == ESTADO.Help && mouseOver (mx, my, 300, 450, 200, 64 ) )
		{
			fagocity.estadoJogo = ESTADO.Menu;
			return;
		}
		
	}
	
	/*verifica se as coordenadas dadas do mouse estao dentro dos limites da caixa do botao*/
	private boolean mouseOver (int mx, int my, int x, int y, int largura, int altura)
	{
		if ( (mx > x) && (mx < x + largura) && (my > y) && (my < y + altura) )
			return true;
		else
			return false;
	}
	
	public void tick ()
	{
		
	}
	
	public void render (Graphics g)
	{
		if (fagocity.estadoJogo == ESTADO.Menu)
		{
		Font fnt = new Font ("arial", 1, 50);
		
		g.setFont (fnt);
		g.setColor (Color.white);
		g.drawString ("Menu", 335, 70);
		
		/*botoes do menu*/
		g.drawRect (300, 190, 200, 64);
		g.drawString ("Play", 345, 240);
		
		g.drawRect (300, 320, 200, 64);
		g.drawString ("Help", 345, 370);
	
		g.drawRect (300, 450, 200, 64);
		g.drawString ("Quit", 345, 500);
		}
		/*janela help*/
		else if (fagocity.estadoJogo == ESTADO.Help)
		{
			Font fnt = new Font ("arial", 1, 50);
			Font fnt2 = new Font ("arial", 1, 30);
			
			g.setFont (fnt);
			g.setColor (Color.white);
			g.drawString ("Menu", 335, 70);
			
			g.drawRect (300, 450, 200, 64);
			g.drawString ("Back", 340, 500);
			
			g.setFont (fnt2);
			g.drawString ("WASD move o personagem", 200, 250);
		}

	}

}
