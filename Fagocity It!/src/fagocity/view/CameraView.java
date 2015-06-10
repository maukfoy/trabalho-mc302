package fagocity.view;

import java.awt.Graphics2D;

import fagocity.controller.CameraController;
import fagocity.view.Interfaces.ViewSingleton;

public class CameraView implements ViewSingleton {
	private CameraController cameraController;
	
	private static CameraView camera = null;
	
	public static CameraView getInstance() {
		if (camera == null)
			camera = new CameraView();
		return camera;
	}
	
	private CameraView (){
		cameraController = CameraController.getInstance();
	}
	
	/*determina o inicio da camera na tela apos translada-la*/
	public void CameraBeginning (Graphics2D g2d)
	{
		g2d.translate(cameraController.getTX(), cameraController.getTY());
	}
	
	/*determina o fim da camera na tela apos translada-la*/
	public void CameraEnding (Graphics2D g2d)
	{
		g2d.translate(-cameraController.getTX(), -cameraController.getTY());

	}	

}
