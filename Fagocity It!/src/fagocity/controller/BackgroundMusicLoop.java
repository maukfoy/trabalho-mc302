package fagocity.controller;

/* Singleton */
public class BackgroundMusicLoop implements Runnable {
	private GameController controller;
	
	public BackgroundMusicLoop(GameController controller) {
		this.controller = controller;
		
	}
	
	@Override
	public void run() {
		controller.getAudioPlayer().playAudio("Soundtrack", "loop");
	}
}
