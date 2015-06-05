package fagocity.controller;

import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;
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
		/*O MX E MY TAO SEMPRE ZERO, POR MAIS QUE ESTEJA DANDO UPDATE AQUI PORRA*/
		mx = MouseController.getClickX ();
		my = MouseController.getClickY ();
		
		/*Botão Play*/
		if (GameStatus.status == STATUS.Menu && mouseOver (mx, my, width/23, 5*height/20, width/10, height/17 ))
		{	
			GameController.initialConditions();
			GameStatus.status = STATUS.Fagocity;
			HUDController.setInitialTime(System.currentTimeMillis());
		}
		/*Botão Quit*/
		if (GameStatus.status == STATUS.Menu && mouseOver (mx, my, width/23, 13*height/20, width/10, height/17 ))
		{
			System.exit(1);
		}
		
		/*Botão Help*/
		if (GameStatus.status == STATUS.Menu && mouseOver (mx, my, width/23, 9*height/20 , width/10, height/17 ))
		{
			GameStatus.status = STATUS.Help;
		}
		
		/*Botão Back da tela Help*/
		if (GameStatus.status == STATUS.Help && mouseOver (mx, my, 22*width/50, 40*height/50, width/10, height/17 ) )
		{
			GameStatus.status = STATUS.Menu;
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