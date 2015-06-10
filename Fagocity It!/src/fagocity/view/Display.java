package fagocity.view;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import fagocity.controller.MouseController;
import fagocity.view.Interfaces.ViewSingleton;

public class Display implements ViewSingleton{
	private static JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;
	
	private static Display display = null;
	
	public static Display getInstance(String title) {
		if (display == null)
			display = new Display(title);
		return display;
	}
	
	private Display(String title) {
		this.title = title;
		this.width = GameView.getScreenWidth();
		this.height = GameView.getScreenHeight();;
		createDisplay();
	}  
	
	private void createDisplay() {
		/* Cria o JFrame */
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		
		/* Cria o Canvas */
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		
		frame.add(canvas);
		canvas.addMouseMotionListener(MouseController.getInstance()); /* Permite a detecçao do movimento do mouse*/
		canvas.addMouseListener(MouseController.getInstance()); /* Permite a detecçao dos cliques */
			
		frame.pack();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
}
