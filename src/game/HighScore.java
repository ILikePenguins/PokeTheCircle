package game;

import android.content.Context;
import android.content.SharedPreferences;

public class HighScore 
{
	public static final String PREFS_NAME = "MyPrefsFile";
	private int high_score;


	private SharedPreferences settings;
	
	public HighScore(Context context)
	{
		settings = context.getSharedPreferences(PREFS_NAME, 0);
		getCurrentHighScore();
		//System.out.println("score?");
	}
	public int getHigh_score() {
		return high_score;
	}
	
	public void saveScore(int last_score)
	{
		// We need an Editor object to make preference changes.
	      // All objects are from android.context.Context
		if(last_score>high_score)
		  {
			
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putInt("score", last_score);
	
		    // Commit the edits!
		    editor.commit();
		    high_score=last_score;
		   // System.out.println("saved");
		  }
	}
	
	public void getCurrentHighScore()
	{
		// Restore preferences
		//System.out.println("get");
		high_score= settings.getInt("score", 0);
		//System.out.println("got score: "+ high_score);
		
	}
}
