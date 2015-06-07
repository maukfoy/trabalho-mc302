package fagocity.controller;

import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;

public class MenuController {
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
		if (GameStatus.status == STATUS.Menu && mouseOver (mx, my, 82, 270, 190, 65 ))
		{	
			GameController.initialConditions();
			GameStatus.status = STATUS.Fagocity;
			HUDController.setInitialTime(System.currentTimeMillis());
		}
		/*Botão Quit*/
		if (GameStatus.status == STATUS.Menu && mouseOver (mx, my, 82, 702, 190, 65 ))
		{
			System.exit(1);
		}
		
		/*Botão Help*/
		if (GameStatus.status == STATUS.Menu && mouseOver (mx, my, 82, 489 , 190, 65 ))
		{
			GameStatus.status = STATUS.Help;
		}
		
		/*Botão Back da tela Help*/
		if (GameStatus.status == STATUS.Help && mouseOver (mx, my, 864, 900, 190, 65 ) )
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