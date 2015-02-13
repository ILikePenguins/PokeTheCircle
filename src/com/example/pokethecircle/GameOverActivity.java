package com.example.pokethecircle;

import game.HighScore;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class GameOverActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        
        Button  playButton = (Button)findViewById(R.id.btnPlayAgain);
        playButton.setOnClickListener(new playListener());
        
        TextView score = (TextView)findViewById(R.id.tvFinalScore);
        score.setText("Score: "+getIntent().getExtras().getString("score"));
        
        HighScore highScore= new HighScore(this);
        highScore.saveScore(Integer.parseInt(getIntent().getExtras().getString("score").toString()));
        
        TextView tvHighScore = (TextView)findViewById(R.id.tvHighScore);
        tvHighScore.setText("High Score: "+highScore.getHigh_score());
    }
    
    class playListener implements OnClickListener
    {
    	  public void onClick(View v)
    	    {
    		  Intent gameIntent = new Intent(GameOverActivity.this,GameActivity.class);
  	      	  startActivity(gameIntent);
    	    }
    }
    
    
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) 
	    {
	    	//return to homescreen
	    	Intent homeIntent = new Intent(GameOverActivity.this,StartActivity.class);
	      	startActivity(homeIntent);
	    }
	    return super.onKeyDown(keyCode, event);
	}
    
	
}
