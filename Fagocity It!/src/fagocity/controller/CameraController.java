package fagocity.controller;

import fagocity.controller.Interfaces.ControllerSingleton;
import fagocity.model.Actor;
import fagocity.view.GameView;

public class CameraController implements ControllerSingleton {
	
	private static CameraController cameraController = null;
	
	private double tx = 0;
	private double ty = 0;
	
	private CameraController() {
		
	}
	
	public static CameraController getInstance() {
		if (cameraController == null)
			cameraController = new CameraController();
		return cameraController;
	}
	
	/*calcula os parametros de translacao de camera, deixando o player no centro da tela sempre*/
	public void update (Actor player)
	{
		tx = -player.getX() - player.getRadius()/2 + GameView.getScreenWidth()/2;
		ty = -player.getY() - player.getRadius()/2 + GameView.getScreenHeight()/2;		
	}
	
	public double getTX() {
		return tx;
	}
	
	public double getTY() {
		return ty;
	}

}
