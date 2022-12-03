package minigame_zip.Omok;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameSound {

	public static void HitBallSound(){
		try{
			File sound = new File("sounds//hitsball.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			
			
		}catch(Exception e){}
	}

}

