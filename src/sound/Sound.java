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
	    //set up listener
	    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener());
	    //load sound
	    soundID = soundPool.load(c, R.raw.boop, 1);
	    //needed to get users current sound level
	    am = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
	}
	
	public class OnLoadCompleteListener implements android.media.SoundPool.OnLoadCompleteListener
	{
		 public void onLoadComplete(final SoundPool soundPool, int sampleId,
		          int status)
		 {
		        loaded = true;
		        int volume_level=0;
		        switch( am.getRingerMode() )
		        {
		        case AudioManager.RINGER_MODE_NORMAL:
		        	//get current volume level of user
		        	volume_level = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		           break;
		        case AudioManager.RINGER_MODE_SILENT:
		        case AudioManager.RINGER_MODE_VIBRATE:
		        	volume_level=0;
		           break;
		        }
		        
		        
		        //play sound
		        soundPool.play(soundID, volume_level, volume_level, 1, 0, 1f);
		        
		        new Thread(new Runnable()
		        {
	        	     public void run()
	        	     {
	        	         try 
	        	         {		//release resources after sound plays
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
	



