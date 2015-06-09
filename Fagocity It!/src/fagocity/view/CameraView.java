package fagocity.view;

import java.awt.Graphics2D;

import fagocity.controller.CameraController;

public class CameraView {
	private CameraController cameraController;
	
	public CameraView (CameraController cameraController){
		this.cameraController = cameraController;
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
