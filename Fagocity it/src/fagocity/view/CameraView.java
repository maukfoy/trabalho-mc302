package fagocity.view;

import java.awt.Graphics2D;

import fagocity.controller.CameraController;

public class CameraView {
	
	/*determina o inicio da camera na tela apos translada-la*/
	public static void CameraBeginning (Graphics2D g2d)
	{
		g2d.translate(CameraController.getTX(), CameraController.getTY());
	}
	
	/*determina o fim da camera na tela apos translada-la*/
	public static void CameraEnding (Graphics2D g2d)
	{
		g2d.translate(-CameraController.getTX(), -CameraController.getTY());

	}	

}
