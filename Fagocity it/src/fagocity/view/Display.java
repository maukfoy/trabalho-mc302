package fagocity.view;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

import fagocity.controller.MouseController;

public class Display {
	private static JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
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
		Dimension canvasDimension = new Dimension(width, height);
		canvas.setPreferredSize(canvasDimension);
		canvas.setMinimumSize(canvasDimension);
		canvas.setMaximumSize(canvasDimension);
		
		frame.add(canvas);
		
		MouseController mouseController = new MouseController();
		canvas.addMouseMotionListener(mouseController); /* Permite a detecçao do movimento do mouse*/
		canvas.addMouseListener(mouseController); /* Permite a detecçao dos cliques */
			
		frame.pack();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
}
