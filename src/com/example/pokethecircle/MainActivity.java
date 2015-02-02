package com.example.pokethecircle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity 
{
	
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_main);
        Button  startButton = (Button)findViewById(R.id.btnStart);
        startButton.setOnClickListener(new StartListener());
        
        TextView score = (TextView)findViewById(R.id.tvHowTo);
        score.setText("Many circles will appear on the screen," +
        		"\ntry to poke them before they shrink and disappear!" +
        		"\nBe careful though, as time goes on," +
        		"\nthe circles will call their circle buddies and shrink " +
        		"even faster." +
        		"\n\nCan you poke them all?");
    }
    
    class StartListener implements OnClickListener
    {

    	  public void onClick(View v)
    	    {
    		  Intent gameIntent = new Intent(MainActivity.this,GameActivity.class);
  	      	  startActivity(gameIntent);
    	    }
    }
  
}

