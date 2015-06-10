package fagocity.controller;

import fagocity.controller.Interfaces.ControllerSingleton;
import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;

public class MenuController implements ControllerSingleton {
	private int mx, my;
	private HUDController hud;
	private MouseController mouse;
	
	private static MenuController menuController = null;
	
	public static MenuController getInstance() {
		if (menuController == null)
			menuController = new MenuController();
		return menuController;
	}
	
	private MenuController ()
	{
		this.mouse = MouseController.getInstance();
		this.hud = HUDController.getInstance();

	}
	
	public void update(){
		click();
	}
	
	public void click ()
	{	

		mx = mouse.getClickX ();
		my = mouse.getClickY ();
		
		/*Botão Play*/
		if (GameStatus.status == STATUS.Menu && mouseOver (mx, my, 82, 270, 190, 65 ))
		{	
			GameStatus.status = STATUS.Fagocity;
			hud.setInitialTime(System.currentTimeMillis());
			new Thread( new BackgroundMusicLoop(GameController.getInstance())).start();
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
	public boolean mouseOver (int mx, int my, int x, int y, int largura, int altura)
	{
		if ( (mx > x) && (mx < x + largura) && (my > y) && (my < y + altura) )
			return true;
		else
			return false;
	}
	
}