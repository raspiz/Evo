/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Sound
 * Purpose: A generic class that will load a sound and allow start and stop functions.
 * There is also a function that will return a boolean value of whether the sound is currently playing
*****************************************************************************/

package evo;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	private Clip clip;
	String mySound;


	public Sound()
	{
		mySound = "soundForest.wav";
	loadSound(mySound);
	//clip.start();

	}


public void loadSound(String pSound)
{
	 try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource(pSound);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         
      } 
	 catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } 
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      } 
	 catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	public void startSound()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	
	}
	public void stopSound()
	{
		clip.stop();
	}
	public boolean isRunning()
	{
		return clip.isRunning();
	}
}