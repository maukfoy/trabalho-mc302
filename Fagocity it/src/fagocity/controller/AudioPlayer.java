package fagocity.controller;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	/* Toca um audio sem deixar em loop */
	public static void playAudio(String name) {
		String path = "src/fagocity/model/assets/sounds/";
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
	public static void playAudio(String name, String loop) {
		String path = "src/fagocity/model/assets/sounds/";
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
