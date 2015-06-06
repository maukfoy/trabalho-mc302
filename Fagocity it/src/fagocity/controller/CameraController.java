package fagocity.controller;

import fagocity.model.Actor;
import fagocity.view.GameView;

public class CameraController {
	
	private static double tx = 0, ty = 0;
	
	/*calcula os parametros de translacao de camera, deixando o player no centro da tela sempre*/
	public static void update (Actor player)
	{
		tx = -player.getX() - player.getRadius()/2 + GameView.getScreenWidth()/2;
		ty = -player.getY() - player.getRadius()/2 + GameView.getScreenHeight()/2;		
	}
	
	public static double getTX ()
	{
		return tx;
	}
	
	public static double getTY ()
	{
		return ty;
	}

}
