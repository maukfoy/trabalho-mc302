package fagocity.controller;

import fagocity.model.Actor;
import fagocity.view.GameView;

public class CameraController {
	private GameView view;
	
	public CameraController (GameView view)
	{
		this.view = view;
	}
	
	private double tx = 0, ty = 0;
	
	/*calcula os parametros de translacao de camera, deixando o player no centro da tela sempre*/
	public void update (Actor player)
	{
		tx = -player.getX() - player.getRadius()/2 + view.getScreenWidth()/2;
		ty = -player.getY() - player.getRadius()/2 + view.getScreenHeight()/2;		
	}
	
	public double getTX ()
	{
		return tx;
	}
	
	public double getTY ()
	{
		return ty;
	}

}
