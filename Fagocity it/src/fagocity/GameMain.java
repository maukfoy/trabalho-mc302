package fagocity;

import javax.swing.SwingUtilities;

import fagocity.model.GameModel;
import fagocity.view.GameFrame;

public class GameMain implements Runnable {
	
	GameFrame frame;
	GameModel model = new GameModel();
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new GameMain());
	}
	
	public void run() {
		frame = new GameFrame(model);
		frame.setVisible(true);
	}
	
}
