package sound;

import com.example.pokethecircle.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sound 
{
	  private SoundPool soundPool;
	  private int soundID;
	  boolean loaded = false;
	  private static AudioManager am;
	
	
	public Sound(Context c)
	{
		
	    soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener());
	    soundID = soundPool.load(c, R.raw.boop, 1);
	    am = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
	}
	
	public class OnLoadCompleteListener implements android.media.SoundPool.OnLoadCompleteListener
	{
		 public void onLoadComplete(final SoundPool soundPool, int sampleId,
		          int status)
		 {
			 	System.out.println("loaded!!!");
		        loaded = true;
		        
		        int volume_level= am.getStreamVolume(AudioManager.STREAM_MUSIC);
		        soundPool.play(soundID, volume_level, volume_level, 1, 0, 1f);
		        new Thread(new Runnable()
		        {
	        	     public void run()
	        	     {
	        	         try 
	        	         {
	        	        	 	Thread.sleep(500);
	        	                soundPool.release();
	        	         } 
	        	         catch (InterruptedException e)
	        	         {
	        	        	 e.printStackTrace(); 
	        	         }
	        	     }
		        }).start();
		}
	}
	
	public boolean isLoaded() 
	{
		return loaded;
	}
		
	}
	



