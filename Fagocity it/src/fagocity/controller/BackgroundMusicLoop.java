package fagocity.controller;

/* Singleton */
public class BackgroundMusicLoop implements Runnable {
	
	public BackgroundMusicLoop() {
		
	}
	
	@Override
	public void run() {
		AudioPlayer.playAudio("Soundtrack", "loop");
	}
}
