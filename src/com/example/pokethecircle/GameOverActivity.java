package com.example.pokethecircle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        

    }
    
    class playListener implements OnClickListener
    {

    	  public void onClick(View v)
    	    {
    		  Intent gameIntent = new Intent(GameOverActivity.this,GameActivity.class);
  	      	  startActivity(gameIntent);
    	    }
    }
	
}
