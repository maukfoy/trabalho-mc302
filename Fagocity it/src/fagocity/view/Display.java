package fagocity.view;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

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
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		
		frame.add(canvas);
		MouseController mouseController = new MouseController();
		canvas.addMouseMotionListener(mouseController); /* Permite a detecçao do movimento do mouse*/
		canvas.addMouseListener(mouseController); /* Permite a detecçao dos cliques */
			
		frame.pack();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	/* Deixa o mouse invisível */
	public static void hideCursor() {
		/* Imagem de um cursor transparente */
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		/* Cria um cursor transparente */
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		/* Coloca o cursor no JFrame */
		frame.getContentPane().setCursor(blankCursor);
	}
	
	/* Deixa o mouse como visível */
	public static void showCursor() {
		frame.getContentPane().setCursor(Cursor.getDefaultCursor());
	}
	
	
}
