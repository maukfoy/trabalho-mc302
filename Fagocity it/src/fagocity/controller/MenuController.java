package fagocity.controller;

import tutorial.realtutsgml.wave.ESTADO;
import fagocity.model.GameMode;
import fagocity.model.GameMode.STATUS;
import fagocity.view.GameView;

public class MenuController {
	
	private static int width = GameView.getScreenWidth();
	private static int height = GameView.getScreenHeight();
	private static int mx, my;
	
	public static void update(){
		click();
	}
	
	public static void click ()
	{	
		mx = MouseController.getClickX ();
		my = MouseController.getClickY ();
		
		/*Bot�o Play*/
		if (GameMode.status == STATUS.Menu && mouseOver (mx, my, width/23, 5*height/20, width/10, height/17 ))
		{
			GameMode.status = STATUS.Fagocity;
			//GameController.initialConditions();
		}
		/*Bot�o Quit*/
		if (GameMode.status == STATUS.Menu && mouseOver (mx, my, width/23, 13*height/20, width/10, height/17 ))
		{
			System.exit(1);
		}
		
		/*Bot�o Help*/
		if (GameMode.status == STATUS.Menu && mouseOver (mx, my, width/23, 9*height/20 , width/10, height/17 ))
		{
			GameMode.status = STATUS.Help;
		}
		
		/*Bot�o Back da tela Help*/
		if (GameMode.status == STATUS.Help && mouseOver (mx, my, 22*width/50, 40*height/50, width/10, height/17 ) )
		{
			GameMode.status = STATUS.Menu;
			return;
		}
		
	}
	
	/*verifica se as coordenadas dadas do mouse estao dentro dos limites da caixa do botao*/
	public static boolean mouseOver (int mx, int my, int x, int y, int largura, int altura)
	{
		if ( (mx > x) && (mx < x + largura) && (my > y) && (my < y + altura) )
			return true;
		else
			return false;
	}
	
}