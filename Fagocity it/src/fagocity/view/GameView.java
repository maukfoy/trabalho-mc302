package fagocity.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import fagocity.model.GameModel;

@SuppressWarnings("serial")
public class GameView extends JFrame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = WIDTH / 4 * 3; // Força aspect ratio 4:3 baseado no WIDTH
	
	private JPanel screen;
	private GameModel model;
	
	public GameView(GameModel model) {
		this.model = model;
		SetMainWindow();
	}
	
	/* Cria a janela principal */
	private void SetMainWindow() {
		setTitle("Fagocity It!");
		screen = new JPanel();
		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		screen.setBackground(Color.RED);
		add(screen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
	/* Metodo que renderiza */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if( bs == null )
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.fillRect((int) 800, (int) 600, 32, 32);
		
		g.dispose();
		bs.show();
	}
}
