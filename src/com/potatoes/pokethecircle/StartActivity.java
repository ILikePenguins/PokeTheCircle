package com.potatoes.pokethecircle;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity 
{
	
	 protected void onCreate(Bundle savedInstanceState)
	    {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			Button startButton = (Button) findViewById(R.id.btnStart);
			startButton.setOnClickListener(new StartListener());
		
			Button howToButton = (Button) findViewById(R.id.btnHowTo);
			howToButton.setOnClickListener(new HowToListener());
	    }
	 
	    class StartListener implements OnClickListener
	    {

	    	  public void onClick(View v)
	    	    {
	    		  Intent gameIntent = new Intent(StartActivity.this,GameActivity.class);
	  	      	  startActivity(gameIntent);
	    	    }
	    }
	    
	    class HowToListener implements OnClickListener
	    {

	    	  public void onClick(View v)
	    	    {
	    		  Intent howToIntent = new Intent(StartActivity.this,HowToPlayActivity.class);
	  	      	  startActivity(howToIntent);
	    	    }
	    }
	    
	
		    


}
