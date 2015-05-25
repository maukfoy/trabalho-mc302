package fagocity.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	
<<<<<<< HEAD
	private static final int WIDTH = 800;
	private static final int HEIGHT = WIDTH / 4 * 3; // Força aspect ratio 4:3 baseado no WIDTH
=======
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
>>>>>>> origin/master
	
	private JPanel screen;

<<<<<<< HEAD
	public GameView(GameModel model) {
		this.model = model;
		SetMainWindow();
	}
	
	/* Cria a janela principal */
	public void SetMainWindow() {
		setTitle("Fagocity It!");
		screen = new JPanel();
		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		screen.setBackground(Color.RED);
		add(screen);
		
		JPanel screen1 = new JPanel();
		screen1.setPreferredSize(new Dimension(70, HEIGHT));
		screen1.setBackground(Color.BLACK);
		screen.add(screen1);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
=======
	public GameView() {
		this.visible = false;
	}
	
	/* Cria a janela principal */
	public void createMainWindow() {
		if(this.visible == false) {
			screen = new JPanel();
			screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			screen.setBackground(Color.BLUE);
			add(screen);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			pack();
			setLocationRelativeTo(null);
			this.visible = true;
			this.setVisible(true);
		}
>>>>>>> origin/master
	}
}
