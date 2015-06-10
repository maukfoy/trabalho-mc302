

package fagocity.controller;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import fagocity.controller.Interfaces.ControllerSingleton;


public class AudioPlayer implements ControllerSingleton {
	
private static AudioPlayer audioPlayer = null;
	
	public static AudioPlayer getInstance() {
		if (audioPlayer == null)
			audioPlayer = new AudioPlayer();
		return audioPlayer;
	}
	
	private AudioPlayer(){
		
	}
	
	private String path = "src/fagocity/model/assets/sounds/";
	
	/* Toca um audio sem deixar em loop */
	public void playAudio(String name) {
	    try {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(path + name + ".wav")));
	        clip.start();
	    }
	    catch (Exception e) {
	        e.printStackTrace(System.out);
	    }
	}
	
	/* Deixa um audio em loop */
	public void playAudio(String name, String loop) {
	    try {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(path + name + ".wav")));
	        clip.start();
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        Thread.sleep(Long.MAX_VALUE);
	    }
	    catch (Exception e) {
	        e.printStackTrace(System.out);
	    }
	}

}
