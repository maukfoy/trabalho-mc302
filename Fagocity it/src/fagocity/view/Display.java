package fagocity.view;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import fagocity.controller.MouseController;

public class Display {
	private JFrame frame;
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
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		
		frame.add(canvas);
		canvas.addMouseListener(new MouseController());
		frame.pack();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
}
